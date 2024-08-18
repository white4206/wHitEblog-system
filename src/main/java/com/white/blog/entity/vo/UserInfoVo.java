package com.white.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户信息视图层实体类
 * @date 2024/7/6 16:36:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 粉丝数
     */
    private Long fansNum;
    /**
     * 关注数
     */
    private Long attentionNum;
    /**
     * 获赞数
     */
    private Long likedNum;
}
