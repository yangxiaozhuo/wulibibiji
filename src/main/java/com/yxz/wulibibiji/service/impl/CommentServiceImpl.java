package com.yxz.wulibibiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.entity.Comment;
import com.yxz.wulibibiji.service.CommentService;
import com.yxz.wulibibiji.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author Yang
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2022-11-18 22:24:23
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




