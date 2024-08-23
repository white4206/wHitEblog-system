package com.white.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticleComment;
import com.white.blog.entity.vo.BlogArticleCommentVo;
import com.white.blog.mapper.BlogArticleCommentMapper;
import com.white.blog.service.BlogArticleCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description
 * @date 2024/8/19 00:56:00
 */
@Service
public class BlogArticleCommentServiceImpl extends ServiceImpl<BlogArticleCommentMapper, BlogArticleComment> implements BlogArticleCommentService {

    @Override
    public R<List<BlogArticleCommentVo>> getBlogArticleComment(Long articleId) {
        return null;
    }
}
