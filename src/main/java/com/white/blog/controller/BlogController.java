package com.white.blog.controller;

import com.white.blog.common.R;
import com.white.blog.entity.BlogArticle;
import com.white.blog.entity.BlogArticleTag;
import com.white.blog.entity.vo.*;
import com.white.blog.service.BlogArticleCommentService;
import com.white.blog.service.BlogArticleService;
import com.white.blog.service.BlogArticleTagService;
import com.white.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客控制层类
 * @date 2024/6/19 23:00:49
 */
@Slf4j
@Tag(name = "用户接口")
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogArticleService blogArticleService;

    @Autowired
    private BlogArticleTagService blogArticleTagService;

    @Autowired
    private BlogArticleCommentService blogArticleCommentService;

    /**
     * @param categoryId
     * @param keyword
     * @param pageNum
     * @param pageCount
     * @return R<List < BlogArticle>>
     * @name: getArticle
     * @author: white_
     * @description: 搜索文章接口, 可选参数-1 分类id , 可选参数-2 关键词 , 可选参数-3 作者id
     * @date: 2024/6/21 19:48:12
     */
    @Operation(summary = "搜索文章")
    @GetMapping("/searchArticle")
    public R<List<SearchBlogArticleVo>> searchArticle(Long categoryId, String keyword, Long authorId, @RequestParam String querySelect,
                                                      @RequestParam Integer pageNum, @RequestParam Integer pageCount) {
        return blogArticleService.searchArticle(categoryId, keyword, authorId, querySelect, pageNum, pageCount);
    }

    /**
     * @param null 无参方法
     * @return R<List < BlogArticleTag>>
     * @name: getArticleTag
     * @author: white_
     * @description: 获取文章标签接口
     * @date: 2024/6/21 19:48:28
     */
    @Operation(summary = "获取文章标签")
    @GetMapping("/getArticleTag")
    public R<List<BlogArticleTag>> getArticleTag() {
        return blogArticleTagService.getArticleTag();
    }

    /**
     * @param tagId
     * @param pageNum
     * @param pageCount
     * @return R<List < BlogArticle>>
     * @name: getArticleByTag
     * @author: white_
     * @description: 获取文章接口, 可选参数-1 标签id
     * @date: 2024/6/21 19:48:52
     */
    @Operation(summary = "获取文章")
    @GetMapping("/getArticle")
    public R<List<BlogArticleVo>> getArticle(Long tagId, @RequestParam Integer pageNum, @RequestParam Integer pageCount) {
        return blogArticleService.getArticle(tagId, pageNum, pageCount);
    }

    /**
     * @param articleId
     * @return R<BlogArticle>
     * @name: getArticleDetail
     * @author: white_
     * @description: 获取博客文章详情信息接口
     * @date: 2024/6/22 15:53:08
     */
    @Operation(summary = "获取博客文章详细信息")
    @GetMapping("/getArticleDetail")
    public R<Map<String, Object>> getArticleDetail(@RequestParam Long articleId) {
        return blogArticleService.getArticleDetail(articleId);
    }

    /**
     * @param tagIds
     * @param blogArticle
     * @param file
     * @return R<Long>
     * @name: publishArticle
     * @author: white_
     * @description: 发布博客文章接口
     * @date: 2024/7/6 14:11:52
     */
    @Operation(summary = "发布博客文章")
    @PostMapping("/publishArticle")
    public R<Long> publishArticle(Long[] tagIds, BlogArticle blogArticle, MultipartFile file) throws IOException {
        return blogArticleService.publishArticle(tagIds, blogArticle, file);
    }

    /**
     * @param articleId
     * @return R<EditArticleDetail>
     * @name: getEditArticleDetailById
     * @author: white_
     * @description: 编辑博客信息回显接口
     * @date: 2024/7/15 00:33:29
     */
    @Operation(summary = "编辑博客信息回显")
    @GetMapping("/getEditArticleDetailById")
    public R<EditArticleDetailVo> getEditArticleDetailById(@RequestParam Long articleId) {
        return blogArticleService.getEditArticleDetailById(articleId);
    }

    @Operation(summary = "编辑博客文章")
    @PutMapping("/editArticle")
    public R<Long> editArticle(Long[] tagIds, BlogArticle blogArticle, MultipartFile file) throws IOException {
        return blogArticleService.editArticleDetail(tagIds, blogArticle, file);
    }

    /**
     * @param authorId
     * @param status
     * @param sortBy
     * @return R<List < UserBlogArticleVo>>
     * @name: getUserArticle
     * @author: white_
     * @description: 获取用户文章接口
     * @date: 2024/7/11 16:30:04
     */
    @Operation(summary = "获取用户文章")
    @GetMapping("/getUserArticle")
    public R<List<UserBlogArticleVo>> getUserArticle(Long authorId, Short status, String sortBy, @RequestParam Integer pageNum,
                                                     @RequestParam Integer pageCount) {
        return blogArticleService.getUserArticle(authorId, status, sortBy, pageNum, pageCount);
    }

    /**
     * @param null 无参方法
     * @return R<List < RecentArticleVo>>
     * @name: getRecentArticle
     * @author: white_
     * @description: 获取近期文章接口
     * @date: 2024/7/19 18:06:23
     */
    @Operation(summary = "获取近期文章")
    @GetMapping("/getRecentArticle")
    public R<List<RecentArticleVo>> getRecentArticle() {
        return blogArticleService.getRecentArticle();
    }

    /**
     * @param status
     * @param year
     * @param month
     * @param category
     * @param keyword
     * @param pageNum
     * @param pageCount
     * @return R<List < ContentArticleVo>>
     * @name: getContentArticle
     * @author: white_
     * @description: 获取内容管理文章
     * @date: 2024/7/19 18:06:34
     */
    @Operation(summary = "获取内容管理文章")
    @GetMapping("/getContentArticle")
    public R<List<ContentArticleVo>> getContentArticle(String status, Integer year, Integer month, Long category, String keyword,
                                                       @RequestParam Integer pageNum, @RequestParam Integer pageCount) {
        return blogArticleService.getContentArticle(status, year, month, category, keyword, pageNum, pageCount);
    }

    @Operation(summary = "获取文章评论内容")
    @GetMapping("/getArticleComment")
    public R<List<BlogArticleCommentVo>> getArticleComment(Long articleId) {
        return blogArticleCommentService.getBlogArticleComment(articleId);
    }
}
