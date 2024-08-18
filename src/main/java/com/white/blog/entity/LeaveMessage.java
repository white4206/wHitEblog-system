package com.white.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 留言实体类
 * @date 2024/6/21 22:45:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("leave_message")
public class LeaveMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 留言id */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 内容
     */
    private String content;
    /**
     * 留言时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
