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
import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description IP地址实体类
 * @date 2024/8/21 00:07:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ip_address")
public class IpAddress implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /**
     * ip id
     */
    private Long id;
    /**
     * ip
     */
    private String ip;
    /**
     * 归属地
     */
    private String address;
    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime accessTime;
    /**
     * 访问设备
     */
    private String accessOs;
}

