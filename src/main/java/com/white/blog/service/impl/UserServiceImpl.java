package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.*;
import com.white.blog.entity.vo.*;
import com.white.blog.mapper.*;
import com.white.blog.service.UserService;
import com.white.blog.utils.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户服务层实现类
 * @date 2024/6/17 15:57:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private GenerateCode generateCode;

    @Autowired
    private EmailSend emailSend;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private SaveFile saveFile;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private FavoriteFolderMapper favoriteFolderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdentityTagMapper identityTagMapper;

    @Override
    public R<String> login(HttpServletRequest request, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User result = this.getOne(queryWrapper);

        if (result == null)
            return R.error("登陆失败, 用户名不存在");
        else {
            String password = MD5Utils.getMD5Password(user.getPassword(), result.getSalt());
            if (!result.getPassword().equals(password)) {
                return R.error("登录失败, 密码错误");
            } else if (result.getStatus() != 1)
                return R.error("登录失败, 账号状态不允许");
            else {
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", result.getId());
                claims.put("username", user.getUsername());
                claims.put("timestamp", System.currentTimeMillis());

                redisUtils.set("wCacheUser:" + result.getUsername(), result,
                        (long) 1000 * 60 * 60 * 24 * 7);

                result.setLastLoginIp(IPUtils.getClientIP(request));
                result.setLastLoginTime(LocalDateTime.now());
                this.updateById(result);

                return R.success(JWTUtils.getToken(claims));
            }
        }
    }

    @Override
    public R<String> register(User user, String code) {
        if (redisUtils.get("wCode:" + user.getUsername() + user.getEmail()) == null)
            return R.error("验证码已过期");
        else {
            if (code.equals(redisUtils.get("wCode:" + user.getUsername() + user.getEmail()).toString())) {
                // 查询用户名是否重复
                QueryWrapper<User> qw = new QueryWrapper<>();
                qw.eq("username", user.getUsername());
                if (this.getOne(qw) == null) {
                    // 生成随机盐值
                    String salt = UUID.randomUUID().toString();
                    // 设置用户盐值及加密后的密码
                    user.setSalt(salt);
                    user.setPassword(MD5Utils.getMD5Password(user.getPassword(), salt));
                    // 设置用户注册时间
                    user.setRegisterTime(LocalDateTime.now());
                    // 随机生成用户昵称
                    user.setNickname("用户" + UUID.randomUUID().toString().substring(0, 8));
                    if (this.save(user)) {
                        // 保存注册用户信息
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUserId(user.getId());

                        userInfoMapper.insert(userInfo);
                        FavoriteFolder favoriteFolder = new FavoriteFolder();
                        favoriteFolder.setCreateTime(LocalDateTime.now());
                        favoriteFolder.setIsDefault((short) 1);
                        favoriteFolder.setName("我的收藏夹");
                        favoriteFolder.setUserId(user.getId());
                        favoriteFolderMapper.insert(favoriteFolder);
                        redisUtils.remove("wCode:" + user.getUsername() + user.getEmail());
                        return R.success("注册成功");
                    } else {
                        redisUtils.remove("wCode:" + user.getUsername() + user.getEmail());
                        return R.error("注册失败");
                    }
                } else {
                    redisUtils.remove("wCode:" + user.getUsername() + user.getEmail());
                    return R.error("用户名已存在");
                }
            } else
                return R.error("验证码错误");
        }
    }

    @Override
    public R<String> logout(HttpServletResponse response) {
        SecurityUtils.getSubject().logout();
        Cookie cookie = new Cookie("User-Token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return R.success("登出成功");
    }

    @Override
    public R<String> getCode(String username, String email) throws Exception {
        if (redisUtils.get("wCode:" + username + email) != null)
            return R.error("获取验证码过于频繁，请稍后重试", 601);
        else {
            // 随机生成6位验证码
            String code = generateCode.CreateRandomCode();
            Context context = new Context();
            context.setVariable("code", code);
            // 设置邮件发送内容
            // 读取html文件, 并动态赋值
            String content = templateEngine.process("codeEmail", context);
            emailSend.send(email, "验证码 - wHitE | 博客 Blog", content);
            redisUtils.set("wCode:" + username + email, code, 60 * 2L);
            return R.success("验证码发送成功，请及时查看");
        }
    }

    @Override
    public R<String> updateAvatar(MultipartFile file) throws IOException {
        User user = new User();
        user.setUpdateTime(LocalDateTime.now());
        user.setId(ShiroUtils.getCurrentUser().getId());
        user.setAvatar(saveFile.saveFile("/avatar", file));
        if (this.updateById(user))
            return R.success("头像上传成功");
        else
            return R.error("头像上传失败");
    }

    @Override
    public R<UserInfoVo> getUserInfo() {
        UserInfoVo userInfoVo = new UserInfoVo();
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getId, ShiroUtils.getCurrentUser().getId());
        User user = this.getOne(qw);
        userInfoVo.setId(user.getId());
        userInfoVo.setAvatar(user.getAvatar());
        userInfoVo.setNickname(user.getNickname());

        LambdaQueryWrapper<UserInfo> qw_ = new LambdaQueryWrapper<>();
        qw_.eq(UserInfo::getUserId, ShiroUtils.getCurrentUser().getId());
        UserInfo userInfo = userInfoMapper.selectOne(qw_);
        userInfoVo.setFansNum(userInfo.getFansNum());
        userInfoVo.setAttentionNum(userInfo.getAttentionNum());
        userInfoVo.setLikedNum(userInfo.getLikedNum());
        return R.success(userInfoVo);
    }

    @Override
    public R<UserInfoDetailVo> getUserInfoDetail() {
        UserInfoDetailVo userInfoDetailVo = new UserInfoDetailVo();
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getId, ShiroUtils.getCurrentUser().getId());
        User user = this.getOne(qw);
        userInfoDetailVo.setAvatar(user.getAvatar());
        userInfoDetailVo.setUsername(user.getUsername());
        userInfoDetailVo.setNickname(user.getNickname());

        LambdaQueryWrapper<UserInfo> qw_ = new LambdaQueryWrapper<>();
        qw_.eq(UserInfo::getUserId, ShiroUtils.getCurrentUser().getId());
        UserInfo userInfo = userInfoMapper.selectOne(qw_);
        userInfoDetailVo.setGender(userInfo.getGender());
        userInfoDetailVo.setDescription(userInfo.getDescription());
        userInfoDetailVo.setArea(userInfo.getArea());
        userInfoDetailVo.setBirthday(userInfo.getBirthday());
        userInfoDetailVo.setIdentity(userInfo.getIdentity());
        userInfoDetailVo.setIsFounder(userInfo.getIsFounder());
        return R.success(userInfoDetailVo);
    }

    @Override
    public R<UserDataVo> getUserData(Long authorId) throws IOException {
        if (authorId == null)
            // authorId = ShiroUtils.getCurrentUser().getId();
            authorId = 1L;
        UserDataVo userDataVo = new UserDataVo();
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getId, authorId);
        User user = this.getOne(qw);
        userDataVo.setAvatar(user.getAvatar());
        // 查询IP属地
        userDataVo.setLastLoginIpAddress(IPUtils.getCityInfoByMemorySearch(user.getLastLoginIp()));
        userDataVo.setNickname(user.getNickname());
        userDataVo.setRegisterTime(user.getRegisterTime());

        LambdaQueryWrapper<UserInfo> qw_ = new LambdaQueryWrapper<>();
        qw_.eq(UserInfo::getUserId, authorId);
        UserInfo userInfo = userInfoMapper.selectOne(qw_);
        userDataVo.setViewNum(userInfo.getViewNum());
        userDataVo.setRankData(userInfo.getRankData());
        userDataVo.setFansNum(userInfo.getFansNum());
        userDataVo.setDescription(userInfo.getDescription());
        return R.success(userDataVo);
    }

    @Override
    public R<String> updateUserInfoDetail(UserInfoDetailVo userInfoDetailVo) {
        if (userInfoDetailVo.getNickname() != null) {
            User user = new User();
            user.setId(ShiroUtils.getCurrentUser().getId());
            user.setNickname(userInfoDetailVo.getNickname());
            user.setUpdateTime(LocalDateTime.now());
            if (this.updateById(user))
                return R.success("修改成功");
            else
                return R.error("修改失败");
        } else if (userInfoDetailVo.getGender() != null || userInfoDetailVo.getDescription() != null
                || userInfoDetailVo.getArea() != null || userInfoDetailVo.getBirthday() != null
                || userInfoDetailVo.getIdentity() != null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUpdateTime(LocalDateTime.now());
            userInfo.setUserId(ShiroUtils.getCurrentUser().getId());
            userInfo.setGender(userInfoDetailVo.getGender());
            userInfo.setDescription(userInfoDetailVo.getDescription());
            userInfo.setArea(userInfoDetailVo.getArea());
            userInfo.setBirthday(userInfoDetailVo.getBirthday());
            userInfo.setIdentity(userInfoDetailVo.getIdentity());
            if (userInfoMapper.updateByUserId(userInfo))
                return R.success("修改成功");
            else
                return R.error("修改失败");
        }
        return R.error("修改失败");
    }

    @Override
    public R<List<IdentityTag>> getIdentityTag() {
        LambdaQueryWrapper<IdentityTag> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(IdentityTag::getSort);
        return R.success(identityTagMapper.selectList(qw));
    }

    @Override
    public R<List<AuthorRecommendedVo>> getAuthorRecommended() {
        LambdaQueryWrapper<UserInfo> qw = new LambdaQueryWrapper<>();
        qw.orderByDesc(UserInfo::getViewNum);
        qw.last("limit 5");

        List<AuthorRecommendedVo> authorRecommendedVoList = new ArrayList<>();

        userInfoMapper.selectList(qw).forEach(userInfo -> {
            authorRecommendedVoList.add(new AuthorRecommendedVo(userMapper.selectById(userInfo.getUserId()), userInfo));
        });

        return R.success(authorRecommendedVoList);
    }
}
