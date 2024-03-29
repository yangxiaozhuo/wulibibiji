package com.yxz.wulibibiji.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yxz.wulibibiji.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yang
 * @description 针对表【message】的数据库操作Mapper
 * @createDate 2023-01-14 00:18:19
 * @Entity com.yxz.wulibibiji.entity.Message
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询消息列表
     */
    List<Message> selectListPage(@Param("offset") long offset, @Param("size") long size
            , @Param("ew") Wrapper wrapper, @Param("my_id") String my_id);


    /**
     * 查询通知列表
     */
    List<Message> getNoticeList(@Param("email") String my_id);

    /**
     * 查询某个主题下最新的通知
     */
    Message selectLatestNotice(@Param("userId")String userId, @Param("topic")String topic);

}




