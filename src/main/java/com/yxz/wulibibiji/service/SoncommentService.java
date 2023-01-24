package com.yxz.wulibibiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.SonCommentDTO;
import com.yxz.wulibibiji.entity.Soncomment;

/**
 * @author Yang
 * @description 针对表【sonComment】的数据库操作Service
 * @createDate 2022-12-07 11:45:17
 */
public interface SoncommentService extends IService<Soncomment> {

    Result querySonComment(Integer current, Integer firstCommentId);

    Result likeSonComment(long id);

    Result createSonComment(SonCommentDTO soncommentDTO);

    Result detailSonComment(Long id);
}
