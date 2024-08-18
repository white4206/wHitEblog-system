package com.white.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户数据视图层实体类
 * @date 2024/7/6 17:01:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataVo {
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 访问量
     */
    private Long viewNum;
    /**
     * 排名
     */
    private Long rankData;
    /**
     * 粉丝数
     */
    private Long fansNum;
    /**
     * 个人简介
     */
    private String description;
    /**
     * 上次登录Ip属地
     */
    private String lastLoginIpAddress;
    /**
     * 注册时间
     */
    private LocalDateTime registerTime;
}
