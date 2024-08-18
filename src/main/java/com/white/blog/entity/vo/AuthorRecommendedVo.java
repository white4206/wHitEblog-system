package com.white.blog.entity.vo;

import com.white.blog.entity.User;
import com.white.blog.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 作者推荐视图层实体类
 * @date 2024/7/12 14:29:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRecommendedVo {
    /**
     * 头像
     */
    private String avatar;
    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 简介
     */
    private String description;

    public AuthorRecommendedVo(User user, UserInfo userInfo) {
        this.avatar = user.getAvatar();
        this.authorId = userInfo.getId();
        this.nickname = user.getNickname();
        this.description = userInfo.getDescription();
    }
}
