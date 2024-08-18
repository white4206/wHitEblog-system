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
 * @description 轮播图实体类
 * @date 2024/6/21 22:19:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("carousel")
public class Carousel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 轮播图id */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 轮播图
     */
    private String image;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * [0-禁用,1-启用]
     */
    private Short status;
}
