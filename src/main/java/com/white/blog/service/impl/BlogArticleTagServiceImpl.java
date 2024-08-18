package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticleTag;
import com.white.blog.mapper.BlogArticleTagMapper;
import com.white.blog.service.BlogArticleTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章标签服务层实现类
 * @date 2024/6/21 16:31:41
 */
@Service
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagMapper, BlogArticleTag> implements BlogArticleTagService {
    @Override
    public R<List<BlogArticleTag>> getArticleTag() {
        LambdaQueryWrapper<BlogArticleTag> qw = new LambdaQueryWrapper<>();
        // 按照排序字段升序查询博客文章标签
        qw.orderByAsc(BlogArticleTag::getSort);
        return R.success(this.list(qw));
    }
}
