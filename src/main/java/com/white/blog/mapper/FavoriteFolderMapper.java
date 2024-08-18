package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.FavoriteFolder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 收藏夹表映射接口
 * @date 2024/7/19 18:35:21
 */
@Mapper
public interface FavoriteFolderMapper extends BaseMapper<FavoriteFolder> {
}
