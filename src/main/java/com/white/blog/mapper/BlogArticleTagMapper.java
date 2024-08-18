package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.BlogArticleTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章标签表映射接口
 * @date 2024/6/21 16:27:16
 */
@Mapper
public interface BlogArticleTagMapper extends BaseMapper<BlogArticleTag> {

    @Select("select tag_id from blog_article_tag_relation where article_id=#{articleId};")
    Long[] getByArticleId(Long articleId);

    @Delete("delete from blog_article_tag_relation where article_id=#{articleId};")
    Long deleteByArticleId(Long articleId);
}
