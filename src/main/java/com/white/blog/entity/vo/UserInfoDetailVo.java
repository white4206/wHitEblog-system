package com.white.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户信息详情视图层实体类
 * @date 2024/7/6 17:11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDetailVo {
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
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
     * [0-非创始人,1-创始人]
     */
    private Short isFounder;
}
