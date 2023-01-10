package com.yxz.wulibibiji.dto;

import lombok.Data;

/**
 * @author yangxiaozhuo
 * @date 2023/01/10
 */
@Data
public class SonCommentDTO {

    private Integer sonCommentParentId;

    private String sonCommentReplyId;

    private String sonCommentContent;

}

