package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.FavoriteFolder;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 收藏夹服务层接口
 * @date 2024/7/19 20:07:30
 */
public interface FavoriteFolderService extends IService<FavoriteFolder> {
    R<List<FavoriteFolder>> getFavoriteFolder();
}
