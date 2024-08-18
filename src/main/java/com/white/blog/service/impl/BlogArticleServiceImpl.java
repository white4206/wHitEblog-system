package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticle;
import com.white.blog.entity.User;
import com.white.blog.entity.vo.*;
import com.white.blog.mapper.BlogArticleMapper;
import com.white.blog.mapper.BlogArticleTagMapper;
import com.white.blog.mapper.UserInfoMapper;
import com.white.blog.mapper.UserMapper;
import com.white.blog.service.BlogArticleService;
import com.white.blog.utils.JsoupUtils;
import com.white.blog.utils.SaveFile;
import com.white.blog.utils.ShiroUtils;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章服务层实现类
 * @date 2024/6/20 21:01:57
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Value("${wHitEblog.uploadPath}")
    private String uploadPath;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogArticleMapper blogArticleMapper;

    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;

    @Autowired
    private SaveFile saveFile;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public R<List<SearchBlogArticleVo>> searchArticle(Long categoryId, String keyword, Long authorId, String querySelect, Integer pageNum, Integer pageCount) {
        IPage<BlogArticle> page = new Page<>(pageNum, pageCount);
        LambdaQueryWrapper<BlogArticle> qw = new LambdaQueryWrapper<>();
        // 查询某作者的文章
        qw.eq(authorId != null, BlogArticle::getAuthorId, authorId);
        // 未删除帖子
        qw.eq(BlogArticle::getDeleteStatus, 0);
        // 不需审核帖子 审核通过帖子
        qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getStatus, 1).or().eq(BlogArticle::getStatus, 2));
        // 公开帖子 保护帖子
        qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getPublicStatus, 1).or().eq(BlogArticle::getPublicStatus, 2));
        // 分类id,可选
        qw.eq(categoryId != null, BlogArticle::getCategoryId, categoryId);
        // 关键词,可选
        qw.and(keyword != null && !keyword.isEmpty(), queryWrapper -> queryWrapper.like(BlogArticle::getTitle, keyword)
                .or(blogArticleLambdaQueryWrapper -> blogArticleLambdaQueryWrapper.like(BlogArticle::getContent, keyword)));
        switch (querySelect) {
            case "default":
                qw.orderByAsc(BlogArticle::getCreateTime);
                break;
            case "latest":
                qw.orderByDesc(BlogArticle::getCreateTime);
                break;
            case "hot":
                qw.orderByDesc(BlogArticle::getCreateTime);
                break;
        }
        List<SearchBlogArticleVo> searchBlogArticleVoList = new ArrayList<>();
        this.list(page, qw).forEach(blogArticle -> {
            // 查询用户信息
            User user = userMapper.selectById(blogArticle.getAuthorId());
            List<String> viewerImageList = new ArrayList<>();
            for (Element img : JsoupUtils.getElementsBySelector(blogArticle.getContent(), "img")) {
                viewerImageList.add(img.attr("src"));
            }
            if (viewerImageList.size() > 3) viewerImageList = viewerImageList.subList(0, 2);
            SearchBlogArticleVo searchBlogArticleVo = new SearchBlogArticleVo(blogArticle, viewerImageList, user.getNickname());
            searchBlogArticleVoList.add(searchBlogArticleVo);
        });
        return R.success(searchBlogArticleVoList).add("total", page.getTotal());
    }

    @Override
    public R<List<BlogArticleVo>> getArticle(Long tagId, Integer pageNum, Integer pageCount) {
        IPage<BlogArticle> page = new Page<>(pageNum, pageCount);
        LambdaQueryWrapper<BlogArticle> qw = new LambdaQueryWrapper<>();
        // 未删除帖子
        qw.eq(BlogArticle::getDeleteStatus, 0);
        // 不需审核帖子 审核通过帖子
        qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getStatus, 1).or().eq(BlogArticle::getStatus, 2));
        // 公开帖子 保护帖子
        qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getPublicStatus, 1).or().eq(BlogArticle::getPublicStatus, 2));
        // 子查询语句
        qw.inSql(tagId != null, BlogArticle::getId, "select article_id from blog_article_tag_relation where tag_id=" + tagId);
        // 按发布时间降序查询
        qw.orderByDesc(BlogArticle::getCreateTime);

        List<BlogArticleVo> blogArticleVoList = new ArrayList<>();
        this.list(page, qw).forEach(blogArticle -> {
            // 查询用户信息
            User user = userMapper.selectById(blogArticle.getAuthorId());
            BlogArticleVo blogArticleVo = new BlogArticleVo(blogArticle, user.getNickname());
            blogArticleVoList.add(blogArticleVo);
        });
        return R.success(blogArticleVoList).add("total", page.getTotal());
    }

    @Override
    public R<BlogNoticeArticleVo> getHomePageNotice() {
        LambdaQueryWrapper<BlogArticle> qw = new LambdaQueryWrapper<>();
        // 未删除资讯
        qw.eq(BlogArticle::getDeleteStatus, 0);
        // 公开资讯
        qw.eq(BlogArticle::getPublicStatus, 1);
        // 查询资讯分类下的文章
        qw.eq(BlogArticle::getCategoryId, 2L);
        // 按创建时间降序排列
        qw.orderByDesc(BlogArticle::getCreateTime);
        // 只查一个
        qw.last("limit 1");
        return R.success(new BlogNoticeArticleVo(this.getOne(qw)));
    }

    @Override
    public R<Map<String, Object>> getArticleDetail(Long articleId) {
        BlogArticle blogArticle = this.getById(articleId);

        // 热门文章查询条件构造
        LambdaQueryWrapper<BlogArticle> qw = new LambdaQueryWrapper<>();
        qw.orderByDesc(BlogArticle::getViewNum);
        qw.last("limit 5");
        List<HotArticleVo> hotArticleVoList = new ArrayList<>();
        this.list(qw).forEach(item -> {
            hotArticleVoList.add(new HotArticleVo(item.getId(), item.getTitle(), item.getViewNum()));
        });

        AuthorInfoVo authorInfoVo = new AuthorInfoVo(userMapper.selectById(blogArticle.getAuthorId()),
                userInfoMapper.selectByUserId(blogArticle.getAuthorId()));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("articleDetail", blogArticle);
        resultMap.put("authorInfo", authorInfoVo);
        resultMap.put("hotArticleList", hotArticleVoList);
        resultMap.put("tagList", blogArticleTagMapper.selectBatchIds(Arrays.asList(
                blogArticleMapper.selectTagIdByArticleId(articleId))));
        return R.success(resultMap);
    }

    @Override
    public R<Long> publishArticle(Long[] tagIds, BlogArticle blogArticle, MultipartFile file) throws IOException {

        if (file != null)
            blogArticle.setCover(saveFile.saveFile("/articleCover", file));
        blogArticle.setAuthorId(ShiroUtils.getCurrentUser().getId());
        blogArticle.setCreateTime(LocalDateTime.now());
        // 审核状态设置为 - 不需要审核
        Short status = 2;
        blogArticle.setStatus(status);
        if (this.save(blogArticle)) {
            for (Long tagId : tagIds) {
                blogArticleMapper.insertArticleTagRelation(blogArticle.getId(), tagId);
            }
            return R.success(blogArticle.getId());
        } else
            return R.error("发布失败", 402);
    }

    @Override
    public R<EditArticleDetailVo> getEditArticleDetailById(Long articleId) {
        if (Objects.equals(this.getById(articleId).getAuthorId(), ShiroUtils.getCurrentUser().getId())) {
            EditArticleDetailVo editArticleDetailVo = new EditArticleDetailVo(this.getById(articleId),
                    blogArticleTagMapper.getByArticleId(articleId));
            return R.success(editArticleDetailVo);
        } else
            return R.error("您无权修改该文章，请创作自己的文章", 402);
    }

    @Override
    public R<Long> editArticleDetail(Long[] tagIds, BlogArticle blogArticle, MultipartFile file) throws IOException {
        if (Objects.equals(this.getById(blogArticle).getAuthorId(), ShiroUtils.getCurrentUser().getId())) {
            if (file != null)
                blogArticle.setCover(saveFile.saveFile("/articleCover", file));
            // 设置修改时间
            blogArticle.setUpdateTime(LocalDateTime.now());
            // 审核状态设置为 - 不需要审核;
            Short status = 2;
            blogArticle.setStatus(status);
            if (this.updateById(blogArticle)) {
                blogArticleTagMapper.deleteByArticleId(blogArticle.getId());
                for (Long tagId : tagIds) {
                    blogArticleMapper.insertArticleTagRelation(blogArticle.getId(), tagId);
                }
                return R.success(blogArticle.getId());
            } else {
                return R.error("修改失败");
            }
        } else
            return R.error("您无权修改该文章，请创作自己的文章");
    }

    @Override
    public R<List<PlatformAnnouncementVo>> getPlatformAnnouncement() {
        LambdaQueryWrapper<BlogArticle> qw = new LambdaQueryWrapper<>();

        // 未删除帖子
        qw.eq(BlogArticle::getDeleteStatus, 0);
        // 不需审核帖子 审核通过帖子
        qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getStatus, 1).or().eq(BlogArticle::getStatus, 2));
        // 公开帖子 保护帖子
        qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getPublicStatus, 1).or().eq(BlogArticle::getPublicStatus, 2));
        // 分类id,可选
        qw.eq(BlogArticle::getCategoryId, 3);
        qw.orderByDesc(BlogArticle::getCreateTime);
        qw.last("limit 3");

        List<PlatformAnnouncementVo> platformAnnouncementVoList = new ArrayList<>();

        this.list(qw).forEach(platformAnnouncement -> {
            platformAnnouncementVoList.add(new PlatformAnnouncementVo(platformAnnouncement));
        });
        return R.success(platformAnnouncementVoList);
    }

    @Override
    public R<List<RecentArticleVo>> getRecentArticle() {
        LambdaQueryWrapper<BlogArticle> qw = new LambdaQueryWrapper<>();

        // 未删除帖子
        qw.eq(BlogArticle::getDeleteStatus, 0);
        // 不需审核帖子 审核通过帖子
        qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getStatus, 1).or().eq(BlogArticle::getStatus, 2));
        // 公开帖子 保护帖子
        qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getPublicStatus, 1).or().eq(BlogArticle::getPublicStatus, 2));
        // 分类id,可选
        qw.eq(BlogArticle::getCategoryId, 3);
        qw.orderByDesc(BlogArticle::getCreateTime);
        qw.last("limit 3");

        List<RecentArticleVo> recentArticleVoList = new ArrayList<>();

        this.list(qw).forEach(recentArticle -> {
            recentArticleVoList.add(new RecentArticleVo(recentArticle));
        });
        return R.success(recentArticleVoList);
    }

    @Override
    public R<List<ContentArticleVo>> getContentArticle(String status, Integer year, Integer month, Long category,
                                                       String keyword, Integer pageNum, Integer pageCount) {
        IPage<BlogArticle> page = new Page<>(pageNum, pageCount);
        LambdaQueryWrapper<BlogArticle> qw = new LambdaQueryWrapper<>();
        switch (status) {
            case "total":
                qw.eq(BlogArticle::getDeleteStatus, 0);
                break;
            case "public":
                qw.ne(BlogArticle::getPublicStatus, 0);
                break;
            case "private":
                qw.eq(BlogArticle::getPublicStatus, 0);
                break;
            case "underReview":
                qw.and(queryWrapper -> queryWrapper.eq(BlogArticle::getStatus, -1).or().eq(BlogArticle::getStatus, 0));
                break;
            case "drafts":
                break;
            case "recycleBin":
                qw.eq(BlogArticle::getDeleteStatus, 1);
                break;
        }
        if (year != null && year != 0) {
            LocalDateTime startDateTime;
            LocalDateTime endDateTime;
            if (month == null || month == 0) {
                // 创建指定日期和时间
                startDateTime = LocalDateTime.of(year, 1, 1, 0, 0, 0);
                endDateTime = LocalDateTime.of(year, 12, 31, 23, 59, 59);
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                startDateTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
                endDateTime = LocalDateTime.of(year, month, 31, 23, 59, 59);
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                startDateTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
                endDateTime = LocalDateTime.of(year, month, 30, 23, 59, 59);
            } else if (month == 2 && (year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                startDateTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
                endDateTime = LocalDateTime.of(year, month, 29, 23, 59, 59);
            } else {
                startDateTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
                endDateTime = LocalDateTime.of(year, month, 28, 23, 59, 59);
            }
            qw.between(BlogArticle::getCreateTime, startDateTime, endDateTime);
        }
        // 关键词,可选
        qw.like(keyword != null && !keyword.isEmpty(), BlogArticle::getTitle, keyword);
        qw.or(keyword != null && !keyword.isEmpty(), blogArticleLambdaQueryWrapper -> blogArticleLambdaQueryWrapper.like(
                keyword != null && !keyword.isEmpty(), BlogArticle::getContent, keyword));
        qw.eq(BlogArticle::getAuthorId, ShiroUtils.getCurrentUser().getId());

        List<ContentArticleVo> contentArticleVoList = new ArrayList<>();
        this.list(page, qw).forEach(contentArticle -> {
            contentArticleVoList.add(new ContentArticleVo(contentArticle));
        });
        return R.success(contentArticleVoList).add("total", page.getTotal());
    }

    @Override
    public R<List<UserBlogArticleVo>> getUserArticle(Long authorId, Short status, String sortBy, Integer pageNum, Integer pageCount) {
        Page<BlogArticle> page = new Page<>(pageNum, pageCount);

        if (authorId == null)
            // authorId = ShiroUtils.getCurrentUser().getId();
            authorId = 1L;
        LambdaQueryWrapper<BlogArticle> qw = new LambdaQueryWrapper<>();

        qw.eq(BlogArticle::getAuthorId, authorId);

        if (status == 1) qw.ne(BlogArticle::getPublicStatus, 0);
        else if (status == 0) qw.eq(BlogArticle::getPublicStatus, status);
        else if (status == -1) qw.eq(BlogArticle::getStatus, status);

        if (sortBy != null && sortBy.equals("createTime")) qw.orderByDesc(BlogArticle::getCreateTime);
        else if (sortBy != null && sortBy.equals("viewNum")) qw.orderByDesc(BlogArticle::getViewNum);

        List<UserBlogArticleVo> blogArticleVoList = new ArrayList<>();
        blogArticleMapper.selectList(page, qw).forEach(blogArticle -> {
            blogArticleVoList.add(new UserBlogArticleVo(blogArticle));
        });
        return R.success(blogArticleVoList).add("total", page.getTotal());
    }
}