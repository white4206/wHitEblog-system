package com.white.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @description 用户信息实体类
 * @date 2024/7/6 17:23:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 用户信息id */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 性别
     */
    private Short gender;
    /**
     * 个人简介
     */
    private String description;
    /**
     * 所在地区
     */
    private String area;
    /**
     * 出生日期
     */
    private LocalDate birthday;
    /**
     * 我的身份
     */
    private String identity;
    /**
     * 总访问量
     */
    private Long viewNum;
    /**
     * 粉丝数
     */
    private Long fansNum;
    /**
     * 排名
     */
    private Long rankData;
    /**
     * 关注数
     */
    private Long attentionNum;
    /**
     * 获赞数
     */
    private Long likedNum;
    /**
     * 收藏数
     */
    private Long favNum;
    /**
     * 评论数
     */
    private Long commentNum;
    /**
     * [0-非创始人,1-创始人]
     */
    private Short isFounder;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
