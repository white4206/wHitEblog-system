package com.white.blog.entity;

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
 * @description 身份标签实体类
 * @date 2024/7/11 16:24:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("identity_tag")
public class IdentityTag implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 标签id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
}
