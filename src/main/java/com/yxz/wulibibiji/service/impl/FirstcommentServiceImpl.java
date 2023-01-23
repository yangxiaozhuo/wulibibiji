package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.SensitiveUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.Event.EventProducer;
import com.yxz.wulibibiji.dto.Event;
import com.yxz.wulibibiji.dto.FirstcommentDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Firstcomment;
import com.yxz.wulibibiji.mapper.FirstcommentMapper;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.FirstcommentService;
import com.yxz.wulibibiji.utils.SystemConstants;
import com.yxz.wulibibiji.utils.UserHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.yxz.wulibibiji.utils.RabbitConstants.TOPIC_COMMENT;
import static com.yxz.wulibibiji.utils.RabbitConstants.TOPIC_LIKE;
import static com.yxz.wulibibiji.utils.RedisConstants.FIRST_COMMENT_LIKED_KEY;

/**
 * @author Yang
 * @description 针对表【firstComment】的数据库操作Service实现
 * @createDate 2022-12-07 11:45:17
 */
@Service
public class FirstcommentServiceImpl extends ServiceImpl<FirstcommentMapper, Firstcomment> implements FirstcommentService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private FirstcommentMapper firstcommentMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public Result queryNewFirstComment(Integer current, Integer articleId) {
        QueryWrapper<Firstcomment> wrapper = new QueryWrapper<>();
        wrapper.eq("first_comment_article_id", articleId).orderByDesc("first_comment_created_time");
        IPage<Firstcomment> firstCommentIPage = firstcommentMapper.listJoinInfoPages(new Page<>(current, SystemConstants.MAX_PAGE_SIZE), wrapper);
        firstCommentIPage.getRecords().forEach(firstcomment -> {
            this.isFirstCommentLiked(firstcomment);
        });
        return Result.ok(firstCommentIPage);
    }

    @Override
    public Result queryHotFirstComment(Integer current, Integer articleId) {
        QueryWrapper<Firstcomment> wrapper = new QueryWrapper<>();
        wrapper.eq("first_comment_article_id", articleId).orderByDesc("first_comment_like_count");
        IPage<Firstcomment> firstCommentIPage = firstcommentMapper.listJoinInfoPages(new Page<>(current, SystemConstants.MAX_PAGE_SIZE), wrapper);
        firstCommentIPage.getRecords().forEach(firstcomment -> {
            this.isFirstCommentLiked(firstcomment);
        });
        return Result.ok(firstCommentIPage);
    }

    @Override
    public Result likeFirstComment(long id) {
        //1获取登录用户
        String userId = UserHolder.getUser().getEmail();
        //2.判断当前用户是否已经点赞
        String key = FIRST_COMMENT_LIKED_KEY + id;
        Double score = stringRedisTemplate.opsForZSet().score(key, userId);
        if (score == null) {
            //3.如果未点赞，可以点赞
            boolean isSuccess = update().setSql("first_comment_like_count = first_comment_like_count + 1").eq("first_comment_id", id).update();
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().add(key, userId, System.currentTimeMillis());
            }
        } else {
            //已点赞 可以取消
            boolean isSuccess = update().setSql("first_comment_like_count = first_comment_like_count - 1").eq("first_comment_id", id).update();
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().remove(key, userId);
            }
        }
        return Result.ok();
    }

    @Override
    public Result createFirstComment(FirstcommentDTO firstcommentDTO) {
        List<FoundWord> foundAllSensitive = SensitiveUtil.getFoundAllSensitive(firstcommentDTO.getFirstCommentContent());
        if (!foundAllSensitive.isEmpty()) {
            return Result.fail("评论内容中含有以下违禁词 " + foundAllSensitive.toString() + " ,请修改后发布");
        }
        Firstcomment firstcomment = new Firstcomment(firstcommentDTO.getFirstCommentArticleId(),
                UserHolder.getUser().getEmail(),
                firstcommentDTO.getFirstCommentContent()
        );
        firstcomment.setFirstCommentCreatedTime(DateUtil.date());
        boolean save = this.save(firstcomment);
        if (save && articleService.update().
                setSql("article_comment_count = article_comment_count + 1").
                eq("article_id", firstcommentDTO.getFirstCommentArticleId()).
                update()) {
            sentMq(String.valueOf(firstcommentDTO.getFirstCommentArticleId()),articleService.getById(firstcommentDTO.getFirstCommentArticleId()).getArticleUserId());
            return Result.ok();
        } else {
            return Result.fail("系统错误");
        }
    }
    public void sentMq(String articleid, String userid) {
        Event event = new Event(TOPIC_COMMENT, UserHolder.getUser().getEmail(), "article", articleid, userid);
        eventProducer.fireEvent(event);
    }

    private void isFirstCommentLiked(Firstcomment firstcomment) {
        //1获取登录用户
        if (UserHolder.getUser() == null) {
            return;
        }
        String userId = UserHolder.getUser().getEmail();
        String key = FIRST_COMMENT_LIKED_KEY + firstcomment.getFirstCommentId();
        Double score = stringRedisTemplate.opsForZSet().score(key, userId);
        firstcomment.setLiked(score != null);
    }
}




