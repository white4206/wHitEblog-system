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
 * @description 博客文章视图层实体类
 * @date 2024/6/22 11:57:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogArticleVo {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 作者
     */
    private String author;
    /**
     * 作者id
     */
    private Long authorId;
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
     * 点赞数
     */
    private Long likeNum;
    /**
     * 点踩数
     */
    private Long dislikeNum;

    public BlogArticleVo(BlogArticle blogArticle, String author) {
        this.id = blogArticle.getId();
        this.author = author;
        this.authorId = blogArticle.getAuthorId();
        this.title = blogArticle.getTitle();
        this.cover = blogArticle.getCover();
        this.abstractText = blogArticle.getAbstractText();
        this.createTime = blogArticle.getCreateTime();
        this.likeNum = blogArticle.getLikeNum();
        this.dislikeNum = blogArticle.getDislikeNum();
    }
}
