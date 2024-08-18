package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.Favorite;
import com.white.blog.entity.vo.FavoriteVo;
import com.white.blog.mapper.BlogArticleMapper;
import com.white.blog.mapper.FavoriteMapper;
import com.white.blog.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description
 * @date 2024/7/19 20:16:25
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
    @Autowired
    private BlogArticleMapper blogArticleMapper;

    @Override
    public R<List<FavoriteVo>> getFavorite(Long folderId) {
        LambdaQueryWrapper<Favorite> qw = new LambdaQueryWrapper<>();
        qw.eq(Favorite::getFolderId, folderId);
        List<FavoriteVo> favoriteVoList = new ArrayList<>();
        this.list(qw).forEach(favorite -> {
            if (favorite.getType() == 0)
                favoriteVoList.add(new FavoriteVo(favorite, blogArticleMapper.selectById(favorite.getContentId())
                        .getTitle()));
        });
        return R.success(favoriteVoList);
    }
}
