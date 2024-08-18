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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 待办事项实体类
 * @date 2024/6/22 14:26:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("to_do_list")
public class ToDoList implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 待办事项id */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate finishDate;
    /**
     * [-1-已过期,0-待完成,1-已完成]
     */
    private Short status;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * [0-未删除,1-已删除]
     */
    private Short deleteStatus;
}
