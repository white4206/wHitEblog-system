package com.white.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 热门文章视图层实体类
 * @date 2024/7/12 11:56:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 浏览量
     */
    private Long viewNum;
}
