package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.SensitiveUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.SonCommentDTO;
import com.yxz.wulibibiji.entity.Firstcomment;
import com.yxz.wulibibiji.entity.Soncomment;
import com.yxz.wulibibiji.entity.User;
import com.yxz.wulibibiji.mapper.SoncommentMapper;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.FirstcommentService;
import com.yxz.wulibibiji.service.SoncommentService;
import com.yxz.wulibibiji.service.UserService;
import com.yxz.wulibibiji.utils.SystemConstants;
import com.yxz.wulibibiji.utils.UserHolder;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yxz.wulibibiji.utils.RedisConstants.SON_COMMENT_LIKED_KEY;

/**
 * @author Yang
 * @description 针对表【sonComment】的数据库操作Service实现
 * @createDate 2022-12-07 11:45:17
 */
@Service
public class SoncommentServiceImpl extends ServiceImpl<SoncommentMapper, Soncomment> implements SoncommentService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private SoncommentMapper soncommentMapper;

    @Autowired
    private FirstcommentService firstcommentService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Override
    @DS("slave")
    public Result querySonComment(Integer current, Integer firstCommentId) {
        QueryWrapper<Soncomment> wrapper = new QueryWrapper<>();
        wrapper.eq("son_comment_parent_id", firstCommentId).orderByAsc("son_comment_created_time");
        IPage<Soncomment> sonCommentIPage = soncommentMapper.listJoinInfoPages(new Page<>(current, SystemConstants.MAX_PAGE_SIZE), wrapper);
        sonCommentIPage.getRecords().forEach(soncomment -> {
            this.isSonCommentLiked(soncomment);
        });
        return Result.ok(sonCommentIPage);
    }

    @Override
    public Result likeSonComment(long id) {
        //1获取登录用户
        String userId = UserHolder.getUser().getEmail();
        //2.判断当前用户是否已经点赞
        String key = SON_COMMENT_LIKED_KEY + id;
        Double score = redissonClient.getScoredSortedSet(key).getScore(userId);
        if (score == null) {
            //3.如果未点赞，可以点赞
            boolean isSuccess = update().setSql("son_comment_like_count = son_comment_like_count + 1").eq("son_comment_id", id).update();
            if (isSuccess) {
                redissonClient.getScoredSortedSet(key).add(System.currentTimeMillis(), userId);
            }
        } else {
            //已点赞 可以取消
            boolean isSuccess = update().setSql("son_comment_like_count = son_comment_like_count - 1").eq("son_comment_id", id).update();
            if (isSuccess) {
                redissonClient.getScoredSortedSet(key).remove(userId);
            }
        }
        return Result.ok();
    }

    @Override
    @Transactional
    public Result createSonComment(SonCommentDTO soncommentDTO) {
        List<FoundWord> foundAllSensitive = SensitiveUtil.getFoundAllSensitive(soncommentDTO.getSonCommentContent());
        if (!foundAllSensitive.isEmpty()) {
            return Result.fail("评论内容中含有以下违禁词 " + foundAllSensitive.toString() + " ,请修改后发布");
        }
        Soncomment soncomment = new Soncomment(soncommentDTO.getSonCommentParentId(),
                UserHolder.getUser().getEmail(),
                soncommentDTO.getSonCommentReplyId(),
                soncommentDTO.getSonCommentContent(),
                DateUtil.date());
        Firstcomment firstcomment = firstcommentService.getById(soncomment.getSonCommentParentId());
        firstcomment.setFirstCommentCount(firstcomment.getFirstCommentCount() + 1);
        if (this.save(soncomment)
                && firstcommentService.updateById(firstcomment)
                && articleService.update().setSql("article_comment_count = article_comment_count + 1").
                eq("article_id", firstcomment.getFirstCommentArticleId()).update()) {
            return Result.ok();
        } else {
            return Result.fail("系统错误");
        }
    }

    @Override
    @DS("slave")
    public Result detailSonComment(Long id) {
        Soncomment soncomment = getById(id);
        if (soncomment == null) {
            return Result.fail("没有这条评论");
        }
        isSonCommentLiked(soncomment);
        User user = userService.getById(soncomment.getSonCommentUserId());
        if (soncomment.getSonCommentReplyUserId() != null) {
            User replyUser = userService.getById(soncomment.getSonCommentReplyUserId());
            if (replyUser != null) {
                soncomment.setCommentReplyAvatar(replyUser.getAvatar());
                soncomment.setCommentReplyName(replyUser.getNickname());
            }
        }
        soncomment.setCommentUserAvatar(user.getAvatar());
        soncomment.setCommentUserName(user.getNickname());
        return Result.ok(soncomment);
    }

    private void isSonCommentLiked(Soncomment soncomment) {
        //1获取登录用户
        if (UserHolder.getUser() == null) {
            return;
        }
        String userId = UserHolder.getUser().getEmail();
        String key = SON_COMMENT_LIKED_KEY + soncomment.getSonCommentId();
        Double score = redissonClient.getScoredSortedSet(key).getScore(userId);
        soncomment.setLiked(score != null);
    }
}




