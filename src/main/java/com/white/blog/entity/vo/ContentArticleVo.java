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
 * @description 内容管理文章视图层实体类
 * @date 2024/7/16 22:27:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentArticleVo {
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
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 浏览数
     */
    private Long viewNum;
    /**
     * 评论数
     */
    private Long commentNum;
    /**
     * 收藏数
     */
    private Long favNum;

    public ContentArticleVo(BlogArticle blogArticle) {
        this.id = blogArticle.getId();
        this.title = blogArticle.getTitle();
        this.cover = blogArticle.getCover();
        this.createTime = blogArticle.getCreateTime();
        this.viewNum = blogArticle.getViewNum();
        this.commentNum = blogArticle.getCommentNum();
        this.favNum = blogArticle.getFavNum();
    }
}
