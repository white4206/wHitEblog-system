package com.white.blog.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.white.blog.entity.BlogArticle;
import com.white.blog.entity.User;
import com.white.blog.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 作者信息视图层实体类
 * @date 2024/7/12 11:27:02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInfoVo {
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
    /**
     * 访问数
     */
    private Long viewNum;
    /**
     * 粉丝数
     */
    private Long fansNum;
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

    public AuthorInfoVo(User user, UserInfo userInfo) {
        this.avatar = user.getAvatar();
        this.authorId = user.getId();
        this.nickname = user.getNickname();
        this.description = userInfo.getDescription();
        this.viewNum = userInfo.getViewNum();
        this.fansNum = userInfo.getFansNum();
        this.likedNum = userInfo.getLikedNum();
        this.favNum = userInfo.getFavNum();
        this.commentNum = userInfo.getCommentNum();
    }
}
