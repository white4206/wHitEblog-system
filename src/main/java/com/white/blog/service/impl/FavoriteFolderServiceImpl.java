package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.FavoriteFolder;
import com.white.blog.mapper.FavoriteFolderMapper;
import com.white.blog.service.FavoriteFolderService;
import com.white.blog.utils.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 收藏夹服务层实现类
 * @date 2024/7/19 20:09:21
 */
@Service
public class FavoriteFolderServiceImpl extends ServiceImpl<FavoriteFolderMapper, FavoriteFolder> implements FavoriteFolderService {
    @Override
    public R<List<FavoriteFolder>> getFavoriteFolder() {
        LambdaQueryWrapper<FavoriteFolder> qw = new LambdaQueryWrapper<>();
        qw.eq(FavoriteFolder::getUserId, ShiroUtils.getCurrentUser().getId());
        return R.success(this.list(qw));
    }
}
