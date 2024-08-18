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
 * @description 相关链接实体类
 * @date 2024/6/21 23:32:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("related_link")
public class RelatedLink implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 留言id */
    private Long id;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 图标
     */
    private String icon;
    /**
     * 标题
     */
    private String title;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * [0-隐藏,1-显示]
     */
    private Short status;
    /**
     * 备注
     */
    private String remark;

}
