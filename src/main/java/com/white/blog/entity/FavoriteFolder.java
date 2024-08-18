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
 * @description 收藏夹实体类
 * @date 2024/7/19 17:37:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("favorite_folder")
public class FavoriteFolder implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /**
     * 收藏夹id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 名称
     */
    private String name;
    /**
     * 总数
     */
    private Integer total;
    /**
     * [0-非默认,1-默认]
     */
    private Short isDefault;
    /**
     * [0-私有,1-公开]
     */
    private Short isPrivate;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
