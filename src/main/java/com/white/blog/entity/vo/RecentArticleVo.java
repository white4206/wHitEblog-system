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
 * @description 近期文章视图层实体类
 * @date 2024/7/16 20:46:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentArticleVo {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String abstractText;
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

    public RecentArticleVo(BlogArticle blogArticle) {
        this.id = blogArticle.getId();
        this.title = blogArticle.getTitle();
        this.abstractText = blogArticle.getAbstractText();
        this.commentNum = blogArticle.getCommentNum();
        this.viewNum = blogArticle.getViewNum();
        this.favNum = blogArticle.getFavNum();
        this.likeNum = blogArticle.getLikeNum();
    }
}
