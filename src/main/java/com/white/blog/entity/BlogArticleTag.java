package com.white.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章标签实体类
 * @date 2024/6/21 16:14:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_article_tag")
public class BlogArticleTag implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 标签id */
    private Long id;
    /** 名称 */
    private String name;
    /** 排序 */
    private Integer sort;
}
