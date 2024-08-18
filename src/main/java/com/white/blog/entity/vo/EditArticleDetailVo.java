package com.white.blog.entity.vo;

import com.white.blog.entity.BlogArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 编辑信息回显显示层实体类
 * @date 2024/7/13 11:44:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditArticleDetailVo {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 标签列表
     */
    private Long[] tagIds;
    /**
     * 标题
     */
    private String title;
    /**
     * 封面
     */
    private String cover;
    /**
     * 内容
     */
    private String content;
    /**
     * 摘要
     */
    private String abstractText;
    /**
     * [-1-审核不通过,0-待审核,1-审核通过,2-不需审核]
     */
    private Short publicStatus;

    public EditArticleDetailVo(BlogArticle blogArticle, Long[] tagIds) {
        this.id = blogArticle.getId();
        this.tagIds = tagIds;
        this.title = blogArticle.getTitle();
        this.cover = blogArticle.getCover();
        this.content = blogArticle.getContent();
        this.abstractText = blogArticle.getAbstractText();
        this.publicStatus = blogArticle.getPublicStatus();
    }
}
