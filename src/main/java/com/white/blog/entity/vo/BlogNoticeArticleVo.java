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
 * @description 博客资讯文章视图层实体类
 * @date 2024/6/22 13:58:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogNoticeArticleVo {
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

    public BlogNoticeArticleVo(BlogArticle blogArticle) {
        this.id = blogArticle.getId();
        this.title = blogArticle.getTitle();
        this.cover = blogArticle.getCover();
        this.abstractText = blogArticle.getAbstractText();
    }
}
