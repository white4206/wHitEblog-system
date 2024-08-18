package com.white.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.white.blog.entity.BlogArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 搜索博客文章视图层实体类
 * @date 2024/7/24 18:29:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBlogArticleVo {
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
     * 摘要
     */
    private String abstractText;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 预览图片 x3
     */
    private List<String> viewerImageList;
    /**
     * 浏览量
     */
    private Long viewNum;
    /**
     * 点赞数
     */
    private Long likeNum;
    /**
     * 评论数
     */
    private Long commentNum;

    public SearchBlogArticleVo(BlogArticle blogArticle, List<String> viewerImageList, String author) {
        this.id = blogArticle.getId();
        this.author = author;
        this.authorId = blogArticle.getAuthorId();
        this.title = blogArticle.getTitle();
        this.abstractText = blogArticle.getAbstractText();
        this.createTime = blogArticle.getCreateTime();
        this.viewerImageList = viewerImageList;
        this.viewNum = blogArticle.getViewNum();
        this.likeNum = blogArticle.getLikeNum();
        this.commentNum = blogArticle.getCommentNum();
    }
}
