package com.yxz.wulibibiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.wulibibiji.dto.FirstcommentDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Firstcomment;

/**
 * @author Yang
 * @description 针对表【firstComment】的数据库操作Service
 * @createDate 2022-12-07 11:45:17
 */
public interface FirstcommentService extends IService<Firstcomment> {

    Result queryNewFirstComment(Integer current, Integer articleId);

    Result queryHotFirstComment(Integer current, Integer articleId);

    Result likeFirstComment(long id);

    Result createFirstComment(FirstcommentDTO firstcommentDTO);

    Result detailComment(Long id);
}
