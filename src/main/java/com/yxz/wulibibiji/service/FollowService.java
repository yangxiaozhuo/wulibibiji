package com.yxz.wulibibiji.service;

import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Follow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Yang
* @description 针对表【follow】的数据库操作Service
* @createDate 2023-01-11 23:04:21
*/
public interface FollowService extends IService<Follow> {

    Result follow(String userId, boolean isFollow);

    Result isFollowed(String userId);
}
