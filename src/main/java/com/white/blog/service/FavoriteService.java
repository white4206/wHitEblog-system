package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.Favorite;
import com.white.blog.entity.vo.FavoriteVo;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 收藏服务层接口
 * @date 2024/7/19 20:15:08
 */
public interface FavoriteService extends IService<Favorite> {
    R<List<FavoriteVo>> getFavorite(Long folderId);
}
