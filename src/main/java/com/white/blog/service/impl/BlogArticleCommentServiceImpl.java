package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticleComment;
import com.white.blog.entity.vo.BlogArticleCommentVo;
import com.white.blog.mapper.BlogArticleCommentMapper;
import com.white.blog.service.BlogArticleCommentService;
import com.white.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private UserService userService;

    @Override
    public R<List<BlogArticleCommentVo>> getBlogArticleComment(Integer pageNum, Integer pageCount, Long articleId) {

        Long subCount = 0L;

        IPage<BlogArticleComment> page = new Page<>(pageNum, pageCount);

        LambdaQueryWrapper<BlogArticleComment> qw = new LambdaQueryWrapper<>();
        // 查询指定文章的评论
        qw.eq(BlogArticleComment::getArticleId, articleId);
        // 先查询一级评论
        qw.eq(BlogArticleComment::getParentId, 0);
        // 按最新时间查询
        qw.orderByDesc(BlogArticleComment::getCreateTime);

        List<BlogArticleCommentVo> blogArticleCommentVoList = new ArrayList<>();

        for (BlogArticleComment blogArticleComment : this.list(page, qw)) {
            // 创建视图层对象并查询作者和回复者的昵称
            BlogArticleCommentVo blogArticleCommentVo = new BlogArticleCommentVo(blogArticleComment);
            blogArticleCommentVo.setNickname(userService.getById(blogArticleComment.getUserId()).getUsername());
            blogArticleCommentVo.setAvatar(userService.getById(blogArticleComment.getUserId()).getAvatar());

            LambdaQueryWrapper<BlogArticleComment> subQw = new LambdaQueryWrapper<>();
            subQw.eq(BlogArticleComment::getArticleId, blogArticleCommentVo.getArticleId());
            // 查询当前一级评论下的子评论
            subQw.eq(BlogArticleComment::getParentId, blogArticleComment.getId());
            // 按最新时间查询
            subQw.orderByDesc(BlogArticleComment::getCreateTime);

            List<BlogArticleCommentVo> subBlogArticleCommentVoList = new ArrayList<>();
            for (BlogArticleComment subBlogArticleComment : this.list(subQw)) {
                // 创建视图层对象并查询作者和回复者的昵称
                BlogArticleCommentVo subBlogArticleCommentVo = new BlogArticleCommentVo(subBlogArticleComment);
                subBlogArticleCommentVo.setNickname(userService.getById(subBlogArticleComment.getUserId()).getUsername());
                subBlogArticleCommentVo.setReplyNickname(userService.getById(subBlogArticleComment.getReplyId()).getUsername());
                subBlogArticleCommentVo.setAvatar(userService.getById(subBlogArticleComment.getReplyId()).getAvatar());
                subBlogArticleCommentVoList.add(subBlogArticleCommentVo);
            }

            blogArticleCommentVo.setSubComments(subBlogArticleCommentVoList);
            blogArticleCommentVoList.add(blogArticleCommentVo);

            // 记录子评论数量并累加
            subCount += this.count(subQw);
        }

        return R.success(blogArticleCommentVoList).add("total", page.getTotal() + subCount);
    }
}
