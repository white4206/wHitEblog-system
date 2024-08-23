package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticleComment;
import com.white.blog.entity.vo.BlogArticleCommentVo;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章评论服务层接口
 * @date 2024/8/19 00:48:53
 */
public interface BlogArticleCommentService extends IService<BlogArticleComment> {
    R<List<BlogArticleCommentVo>> getBlogArticleComment(Long articleId);
}
