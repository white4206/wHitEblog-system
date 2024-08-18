package com.white.blog.controller;

import com.white.blog.entity.BlogArticle;
import com.white.blog.entity.Carousel;
import com.white.blog.entity.DailyRecommendation;
import com.white.blog.entity.RelatedLink;
import com.white.blog.entity.vo.*;
import com.white.blog.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.white.blog.common.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 通用控制层类
 * @date 2024/6/16 23:16:14
 */

@Slf4j
@Tag(name = "通用接口")
@RestController
public class CommonController {

    @Autowired
    private DailyRecommendationService dailyRecommendationService;

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private RelatedLinkService relatedLinkService;

    @Autowired
    private BlogArticleService blogArticleService;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private UserService userService;

    /**
     * @param message
     * @return R<String>
     * @name: unauthorized
     * @author: white_
     * @description: 权限认证拦截接口
     * @date: 2024/4/21 21:00:28
     */
    @Operation(summary = "权限认证拦截接口,前端无需调用")
    @GetMapping("/unauthorized")
    public R<String> unauthorized(HttpServletResponse response, @RequestParam String message) {
        log.error("权限认证失败,返回失败信息");
        switch (message) {
            case "unauthorized" -> {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return R.error("用户操作未授权");
            }
            case "expired" -> {
                return R.success("登录已过期,请重新登录", HttpServletResponse.SC_UNAUTHORIZED);
            }
            default -> {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return R.error("未知错误,请联系管理员");
            }
        }
    }

    /**
     * @param null 无参方法
     * @return R<String>
     * @name: test
     * @author: white_
     * @description: 请求测试接口
     * @date: 2024/6/17 15:17:00
     */
    @Operation(summary = "请求测试")
    @RequestMapping("/test")
    public R<String> test() {
        log.info("接口测试");
        return R.success("后端服务已启动");
    }

    /**
     * @param null 无参方法
     * @return R<DailyRecommendation>
     * @name: getDailyRecommendation
     * @author: white_
     * @description: 获取今日推荐内容接口
     * @date: 2024/6/21 22:32:59
     */
    @Operation(summary = "获取今日推荐内容")
    @GetMapping("/getDailyRecommendation")
    public R<DailyRecommendation> getDailyRecommendation() {
        return dailyRecommendationService.getDailyRecommendation();
    }

    /**
     * @param null 无参方法
     * @return R<List < Carousel>>
     * @name: getCarousel
     * @author: white_
     * @description: 获取轮播图接口
     * @date: 2024/6/21 22:33:08
     */
    @Operation(summary = "获取轮播图")
    @GetMapping("/getCarousel")
    public R<List<Carousel>> getCarousel() {
        return carouselService.getCarousel();
    }

    /**
     * @param null 无参方法
     * @return R<List < RelatedLink>>
     * @name: getRelatedLink
     * @author: white_
     * @description: 获取相关链接接口
     * @date: 2024/6/21 23:43:09
     */
    @Operation(summary = "获取相关链接")
    @GetMapping("/getRelatedLink")
    public R<List<RelatedLink>> getRelatedLink() {
        return relatedLinkService.getRelatedLink();
    }

    /**
     * @param null 无参方法
     * @return R<BlogArticle>
     * @name: getHomePageNotice
     * @author: white_
     * @description: 获取主页资讯接口
     * @date: 2024/6/22 13:27:50
     */
    @Operation(summary = "获取主页资讯")
    @GetMapping("/getHomePageNotice")
    public R<BlogNoticeArticleVo> getHomePageNotice() {
        return blogArticleService.getHomePageNotice();
    }

    /**
     * @param file
     * @return R<String>
     * @name: imageUpload
     * @author: white_
     * @description: 富文本编辑器图片上传接口
     * @date: 2024/7/6 14:11:24
     */
    @Operation(summary = "富文本编辑器图片上传")
    @PostMapping("/imageUpload")
    public R<String> imageUpload(MultipartFile file) throws IOException {
        return imageUploadService.imageUpload(file);
    }

    /**
     * @param null 无参方法
     * @return R<String>
     * @name: getAuthorRecommended
     * @author: white_
     * @description: 获取作者推荐接口
     * @date: 2024/7/12 14:28:05
     */
    @Operation(summary = "获取作者推荐")
    @GetMapping("/getAuthorRecommended")
    public R<List<AuthorRecommendedVo>> getAuthorRecommended() {
        return userService.getAuthorRecommended();
    }

    /**
     * @param null 无参方法
     * @return R<List < PlatformAnnouncementVo>>
     * @name: getPlatformAnnouncement
     * @author: white_
     * @description: 获取平台公告接口
     * @date: 2024/7/19 18:48:21
     */
    @Operation(summary = "获取平台公告")
    @GetMapping("/getPlatformAnnouncement")
    public R<List<PlatformAnnouncementVo>> getPlatformAnnouncement() {
        return blogArticleService.getPlatformAnnouncement();
    }
}

