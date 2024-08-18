package com.white.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.white.blog.entity.BlogArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户博客文章视图层实体类
 * @date 2024/7/7 11:21:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBlogArticleVo {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 封面
     */
    private String cover;
    /**
     * 摘要
     */
    private String abstractText;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 评论数
     */
    private Long commentNum;
    /**
     * 浏览量
     */
    private Long viewNum;
    /**
     * 收藏数
     */
    private Long favNum;
    /**
     * 点赞数
     */
    private Long likeNum;

    public UserBlogArticleVo(BlogArticle blogArticle) {
        this.id = blogArticle.getId();
        this.title = blogArticle.getTitle();
        this.cover = blogArticle.getCover();
        this.abstractText = blogArticle.getAbstractText();
        this.createTime = blogArticle.getCreateTime();
        this.commentNum = blogArticle.getCommentNum();
        this.viewNum = blogArticle.getViewNum();
        this.favNum = blogArticle.getFavNum();
        this.likeNum = blogArticle.getLikeNum();
    }
}
