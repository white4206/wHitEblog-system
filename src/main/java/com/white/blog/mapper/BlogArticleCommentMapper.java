package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticleComment;
import com.white.blog.entity.vo.BlogArticleCommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章评论表映射接口
 * @date 2024/8/19 00:47:34
 */
@Mapper
public interface BlogArticleCommentMapper extends BaseMapper<BlogArticleComment> {
}
