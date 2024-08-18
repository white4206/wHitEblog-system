package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticleTag;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章标签服务层接口
 * @date 2024/6/21 16:30:12
 */
public interface BlogArticleTagService extends IService<BlogArticleTag> {
    R<List<BlogArticleTag>> getArticleTag();
}
