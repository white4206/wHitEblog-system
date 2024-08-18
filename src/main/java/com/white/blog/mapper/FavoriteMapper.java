package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 收藏表映射接口
 * @date 2024/7/19 18:34:33
 */
@Mapper
public interface FavoriteMapper extends BaseMapper<Favorite> {
}
