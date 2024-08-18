package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.BlogArticle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 博客文章表映射接口
 * @date 2024/6/20 20:41:47
 */
@Mapper
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    @Insert("insert into blog_article_tag_relation (article_id, tag_id) values (#{articleId},#{tagId})")
    Boolean insertArticleTagRelation(Long articleId, Long tagId);

    @Select("select tag_id from blog_article_tag_relation where article_id=#{articleId};")
    Long[] selectTagIdByArticleId(Long articleId);

}
