package com.blog.framework.vo.comment;


import com.blog.framework.common.PageBean;
import com.blog.framework.common.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * TABLE_NAME:(t_comment)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {


    /**
     * 评论和回复数
     */
    @ApiModelProperty(value = "评论和回复数")
    private Long count;

    /**
     * 评论和回复
     */
    @ApiModelProperty(value = "评论和回复")
    private PageBean<Detail> detail;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {
        /**
         * 评论id
         */
        @ApiModelProperty(value = "评论id")
        private Long id;

        /**
         * 评论内容
         */
        @ApiModelProperty(value = "评论内容")
        private String content;

        /**
         * 评论用户id
         */
        @ApiModelProperty(value = "评论用户id")
        private Long userId;

        /**
         * 回复目标id
         */
        @ApiModelProperty(value = "回复目标id")
        private Long replyId;

        /**
         * 用户名
         */
        @ApiModelProperty(value = "用户名")
        private String userName;

        /**
         * 标签
         */
        @ApiModelProperty(value = "标签")
        private List<String> labels;

        /**
         * 头像
         */
        @ApiModelProperty(value = "头像")
        private String headPhoto;

        /**
         * 评论时间
         */
        @JsonFormat(pattern = DateUtil.DATE_TIME)
        @ApiModelProperty(value = "评论时间")
        private Date createTime;

        /**
         * 对评论的回复
         */
        @ApiModelProperty(value = "对评论的回复")
        private List<ReplyVo> child;

    }

}