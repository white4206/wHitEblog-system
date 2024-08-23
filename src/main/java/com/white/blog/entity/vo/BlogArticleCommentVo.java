package com.white.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章评论视图层实体类
 * @date 2024/8/18 22:56:03
 */
public class BlogArticleCommentVo {
    /**
     * 评论id
     */
    private Long id;
    /**
     * 父级id [0-一级]
     */
    private Long parentId;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 作者id
     */
    private Long userId;
    /**
     * 作者
     */
    private String author;
    /**
     * 回复id
     */
    private Long replyId;
    /**
     * 回复用户
     */
    private String replyUser;
    /**
     * 点赞数
     */
    private Long likeNum;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
