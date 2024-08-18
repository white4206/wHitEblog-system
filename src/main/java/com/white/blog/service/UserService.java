package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.IdentityTag;
import com.white.blog.entity.User;
import com.white.blog.entity.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户服务层接口
 * @date 2024/6/17 15:55:44
 */
public interface UserService extends IService<User> {
    R<String> login(HttpServletRequest request, User user);

    R<String> register(User user, String code);

    R<String> logout(HttpServletResponse response);

    R<String> getCode(String username, String email) throws Exception;

    R<String> updateAvatar(MultipartFile file) throws IOException;

    R<UserInfoVo> getUserInfo();

    R<UserInfoDetailVo> getUserInfoDetail();

    R<UserDataVo> getUserData(Long authorId) throws IOException;

    R<String> updateUserInfoDetail(UserInfoDetailVo userInfoDetailVo);

    R<List<IdentityTag>> getIdentityTag();

    R<List<AuthorRecommendedVo>> getAuthorRecommended();

}
