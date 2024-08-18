package com.white.blog.entity.vo;

import com.white.blog.entity.Favorite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 收藏视图层实体类
 * @date 2024/7/19 21:03:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteVo {
    /**
     * 收藏id
     */
    private Long id;
    /**
     * 内容id
     */
    private Long contentId;
    /**
     * 内容标题
     */
    private String contentTitle;
    /**
     * 名称
     */
    private Long folderId;
    /**
     * [0-博客文章,1-下载]
     */
    private Short type;
    /**
     * 收藏时间
     */
    private LocalDateTime collectionTime;

    public FavoriteVo(Favorite favorite, String contentTitle) {
        this.id = favorite.getId();
        this.contentId = favorite.getContentId();
        this.contentTitle = contentTitle;
        this.folderId = favorite.getFolderId();
        this.type = favorite.getType();
        this.collectionTime = favorite.getCollectionTime();
    }
}
