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
 * @description 平台公告视图层实体类
 * @date 2024/7/16 20:08:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatformAnnouncementVo {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    public PlatformAnnouncementVo(BlogArticle blogArticle) {
        this.id = blogArticle.getId();
        this.title = blogArticle.getTitle();
        this.createTime = blogArticle.getCreateTime();
    }
}
