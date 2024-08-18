package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticle;
import com.white.blog.entity.vo.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章服务层接口
 * @date 2024/6/20 20:41:07
 */
public interface BlogArticleService extends IService<BlogArticle> {
    R<List<SearchBlogArticleVo>> searchArticle(Long categoryId, String keyword, Long authorId, String querySelect, Integer pageNum, Integer pageCount);

    R<List<BlogArticleVo>> getArticle(Long tagId,  Integer pageNum, Integer pageCount);

    R<BlogNoticeArticleVo> getHomePageNotice();

    R<Map<String, Object>> getArticleDetail(Long articleId);

    R<Long> publishArticle(Long[] tagIds, BlogArticle blogArticle, MultipartFile file) throws IOException;

    R<EditArticleDetailVo> getEditArticleDetailById(Long articleId);

    R<Long> editArticleDetail(Long[] tagIds, BlogArticle blogArticle, MultipartFile file) throws IOException;

    R<List<PlatformAnnouncementVo>> getPlatformAnnouncement();

    R<List<RecentArticleVo>> getRecentArticle();

    R<List<ContentArticleVo>> getContentArticle(String status, Integer year, Integer month, Long category, String keyword,
                                                Integer pageNum, Integer pageCount);

    R<List<UserBlogArticleVo>> getUserArticle(Long authorId, Short status, String sortBy, Integer pageNum, Integer pageCount);

}
