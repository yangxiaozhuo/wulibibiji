package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.Event.EventProducer;
import com.yxz.wulibibiji.dto.Event;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Follow;
import com.yxz.wulibibiji.service.FollowService;
import com.yxz.wulibibiji.mapper.FollowMapper;
import com.yxz.wulibibiji.utils.UserHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yxz.wulibibiji.utils.RabbitConstants.TOPIC_FOLLOW;
import static com.yxz.wulibibiji.utils.RabbitConstants.TOPIC_LIKE;

/**
 * @author Yang
 * @description 针对表【follow】的数据库操作Service实现
 * @createDate 2023-01-11 23:04:21
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Autowired
    private EventProducer eventProducer;

    @Override
    public Result follow(String userId, boolean isFollow) {
        boolean haveFollowed = isFollow(userId);
        if (isFollow && !haveFollowed) {
            //关注，并且确定还没关注
            Follow follow = new Follow(null, UserHolder.getUser().getEmail(), userId, DateUtil.date());
            if (save(follow)) {
                sentMq(userId);
                return Result.ok("关注成功");
            } else {
                return Result.fail("关注失败");
            }
        } else if (!isFollow && haveFollowed) {
            //取关，并且确定已经关注
            QueryWrapper<Follow> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", UserHolder.getUser().getEmail()).eq("follow_user_id", userId);
            if (remove(wrapper)) {
                return Result.ok("取关成功");
            } else {
                return Result.fail("取关失败");
            }
        } else {
            return Result.fail("系统异常");
        }
    }

    @Override
    public Result isFollowed(String userId) {
        return Result.ok(isFollow(userId));
    }

    public void sentMq(String userid) {
        Event event = new Event(TOPIC_FOLLOW, UserHolder.getUser().getEmail(), "user", userid, userid);
        eventProducer.fireEvent(event);
    }

    private boolean isFollow(String userId) {
        Follow one = query().eq("user_id", UserHolder.getUser().getEmail()).eq("follow_user_id", userId).one();
        if (one == null) {
            return false;
        } else {
            return true;
        }
    }
}




