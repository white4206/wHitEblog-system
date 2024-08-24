package com.white.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.white.blog.entity.BlogArticleComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章评论视图层实体类
 * @date 2024/8/18 22:56:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
     * 作者昵称
     */
    private String nickname;
    /**
     * 作者头像
     */
    private String avatar;
    /**
     * 回复id
     */
    private Long replyId;
    /**
     * 回复昵称
     */
    private String replyNickname;
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
    /**
     * 回复子评论
     */
    private List<BlogArticleCommentVo> subComments;

    public BlogArticleCommentVo(BlogArticleComment blogArticleComment) {
        this.id = blogArticleComment.getId();
        this.parentId = blogArticleComment.getParentId();
        this.articleId = blogArticleComment.getArticleId();
        this.content = blogArticleComment.getContent();
        this.userId = blogArticleComment.getUserId();
        this.replyId = blogArticleComment.getReplyId();
        this.likeNum = blogArticleComment.getLikeNum();
        this.createTime = blogArticleComment.getCreateTime();
        this.updateTime = blogArticleComment.getUpdateTime();
    }
}
