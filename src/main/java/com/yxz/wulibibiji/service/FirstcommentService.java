package com.yxz.wulibibiji.service;

import com.yxz.wulibibiji.dto.FirstcommentDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Firstcomment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Yang
* @description 针对表【firstComment】的数据库操作Service
* @createDate 2022-12-07 11:45:17
*/
public interface FirstcommentService extends IService<Firstcomment> {

    Result queryFirstComment(Integer current,Integer articleId);

    Result likeFirstComment(Long id);

    Result createArticle(FirstcommentDTO firstcommentDTO);
}
