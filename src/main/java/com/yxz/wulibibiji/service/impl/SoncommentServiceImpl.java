package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.SensitiveUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.SonCommentDTO;
import com.yxz.wulibibiji.entity.Soncomment;
import com.yxz.wulibibiji.mapper.SoncommentMapper;
import com.yxz.wulibibiji.service.FirstcommentService;
import com.yxz.wulibibiji.service.SoncommentService;
import com.yxz.wulibibiji.utils.SystemConstants;
import com.yxz.wulibibiji.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.yxz.wulibibiji.utils.RedisConstants.SON_COMMENT_LIKED_KEY;

/**
 * @author Yang
 * @description 针对表【sonComment】的数据库操作Service实现
 * @createDate 2022-12-07 11:45:17
 */
@Service
public class SoncommentServiceImpl extends ServiceImpl<SoncommentMapper, Soncomment> implements SoncommentService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SoncommentMapper soncommentMapper;

    @Autowired
    private FirstcommentService firstcommentService;

    @Override
    public Result querySonComment(Integer current, Integer firstCommentId) {
        QueryWrapper<Soncomment> wrapper = new QueryWrapper<>();
        wrapper.eq("son_comment_parent_id", firstCommentId).orderByDesc("son_comment_created_time");
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
        Double score = stringRedisTemplate.opsForZSet().score(key, userId);
        if (score == null) {
            //3.如果未点赞，可以点赞
            boolean isSuccess = update().setSql("son_comment_like_count = son_comment_like_count + 1").eq("son_comment_id", id).update();
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().add(key, userId, System.currentTimeMillis());
            }
        } else {
            //已点赞 可以取消
            boolean isSuccess = update().setSql("son_comment_like_count = son_comment_like_count - 1").eq("son_comment_id", id).update();
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().remove(key, userId);
            }
        }
        return Result.ok();
    }

    @Override
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
        if (this.save(soncomment) && firstcommentService.update().
                setSql("first_comment_count = first_comment_count + 1").
                eq("first_comment_id", soncomment.getSonCommentParentId()).
                update()) {
            return Result.ok();
        } else {
            return Result.fail("系统错误");
        }
    }

    private void isSonCommentLiked(Soncomment soncomment) {
        //1获取登录用户
        if (UserHolder.getUser() == null) {
            return;
        }
        String userId = UserHolder.getUser().getEmail();
        String key = SON_COMMENT_LIKED_KEY + soncomment.getSonCommentId();
        Double score = stringRedisTemplate.opsForZSet().score(key, userId);
        soncomment.setLiked(score != null);
    }
}




