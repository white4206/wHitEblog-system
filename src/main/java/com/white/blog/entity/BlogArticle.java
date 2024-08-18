package com.white.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章实体类
 * @date 2024/6/20 20:43:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_article")
public class BlogArticle implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 文章id */
    private Long id;
    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 分类id
     */
    private Long categoryId;
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
     * 内容
     */
    private String content;
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
     * 点赞数
     */
    private Long likeNum;
    /**
     * 点踩数
     */
    private Long dislikeNum;
    /**
     * 收藏数
     */
    private Long favNum;
    /**
     * 分享数
     */
    private Long shareNum;
    /**
     * 浏览量
     */
    private Long viewNum;
    /**
     * 评论数
     */
    private Long commentNum;
    /**
     * [0-未删除,1-删除]
     */
    private Short deleteStatus;
    /**
     * [0-私有,1-公开,2-保护]
     */
    private Short publicStatus;
    /**
     * [-1-审核不通过,0-待审核,1-审核通过,2-不需审核]
     */
    private Short status;
}
