package com.white.blog.controller;

import com.white.blog.common.R;
import com.white.blog.entity.IdentityTag;
import com.white.blog.entity.ToDoList;
import com.white.blog.entity.User;
import com.white.blog.entity.vo.*;
import com.white.blog.service.BlogArticleService;
import com.white.blog.service.ToDoListService;
import com.white.blog.service.UserService;
import com.white.blog.utils.aop.MultiRequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户控制层类
 * @date 2024/6/17 15:46:09
 */
@Slf4j
@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param user
     * @return R<String>
     * @name: login
     * @author: white_
     * @description: 用户登录接口
     * @date: 2024/6/22 14:39:39
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public R<String> login(HttpServletRequest request, @RequestBody User user) {
        return userService.login(request, user);
    }

    /**
     * @param user
     * @param code
     * @return R<String>
     * @name: register
     * @author: white_
     * @description: 用户注册接口
     * @date: 2024/7/6 14:09:37
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public R<String> register(@MultiRequestBody User user, @MultiRequestBody String code) {
        return userService.register(user, code);
    }

    /**
     * @param username
     * @param email
     * @return R<String>
     * @name: sendCode
     * @author: white_
     * @description: 发送验证码接口
     * @date: 2024/7/6 14:09:24
     */
    @Operation(summary = "发送验证码")
    @GetMapping("/getCode")
    public R<String> sendCode(@RequestParam String username, @RequestParam String email) throws Exception {
        return userService.getCode(username, email);
    }

    /**
     * @param file
     * @return R<String>
     * @name: updateAvatar
     * @author: white_
     * @description: 更新头像接口
     * @date: 2024/7/6 14:58:23
     */
    @Operation(summary = "更新头像")
    @PutMapping("/updateAvatar")
    public R<String> updateAvatar(MultipartFile file) throws IOException {
        return userService.updateAvatar(file);
    }

    /**
     * @param null 无参方法
     * @return R<UserInfoVo>
     * @name: getUserInfo
     * @author: white_
     * @description: 获取用户信息接口
     * @date: 2024/7/11 16:29:35
     */
    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public R<UserInfoVo> getUserInfo() {
        return userService.getUserInfo();
    }

    /**
     * @param null 无参方法
     * @return R<UserInfoDetailVo>
     * @name: getUserInfoDetail
     * @author: white_
     * @description: 获取用户详细信息接口
     * @date: 2024/7/11 16:29:45
     */
    @Operation(summary = "获取用户详细信息")
    @GetMapping("/getUserInfoDetail")
    public R<UserInfoDetailVo> getUserInfoDetail() {
        return userService.getUserInfoDetail();
    }

    /**
     * @param authorId
     * @return R<UserDataVo>
     * @name: getUserData
     * @author: white_
     * @description: 获取用户数据接口
     * @date: 2024/7/11 16:29:55
     */
    @Operation(summary = "获取用户数据")
    @GetMapping("/getUserData")
    public R<UserDataVo> getUserData(Long authorId) throws IOException {
        return userService.getUserData(authorId);
    }

    /**
     * @param response
     * @return R<String>
     * @name: logout
     * @author: white_
     * @description: 用户登出接口
     * @date: 2024/7/5 19:31:37
     */
    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public R<String> logout(HttpServletResponse response) {
        return userService.logout(response);
    }

    /**
     * @param null 无参方法
     * @return R<List < IdentityTag>>
     * @name: getIdentityTag
     * @author: white_
     * @description: 获取身份标签接口
     * @date: 2024/7/11 16:40:09
     */
    @Operation(summary = "获取身份标签")
    @GetMapping("/getIdentityTag")
    public R<List<IdentityTag>> getIdentityTag() {
        return userService.getIdentityTag();
    }

    /**
     * @param userInfoDetailVo
     * @return R<String>
     * @name: updateUserInfoDetail
     * @author: white_
     * @description: 修改用户信息接口
     * @date: 2024/7/11 16:30:14
     */
    @Operation(summary = "修改用户信息")
    @PutMapping("/updateUserInfoDetail")
    public R<String> updateUserInfoDetail(@RequestBody UserInfoDetailVo userInfoDetailVo) {
        return userService.updateUserInfoDetail(userInfoDetailVo);
    }

}
