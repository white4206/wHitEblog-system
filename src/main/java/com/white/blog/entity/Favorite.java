package com.white.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @description 收藏实体类
 * @date 2024/7/19 17:40:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("favorite")
public class Favorite implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /**
     * 收藏id
     */
    private Long id;
    /**
     * 内容id
     */
    private Long contentId;
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
}
