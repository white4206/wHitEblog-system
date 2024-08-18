package com.white.blog.controller;

import com.white.blog.common.R;
import com.white.blog.entity.Favorite;
import com.white.blog.entity.FavoriteFolder;
import com.white.blog.entity.vo.FavoriteVo;
import com.white.blog.service.FavoriteFolderService;
import com.white.blog.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 收藏控制层类
 * @date 2024/7/19 18:39:05
 */
@Slf4j
@Tag(name = "收藏接口")
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private FavoriteFolderService favoriteFolderService;

    @Operation(summary = "获取用户收藏夹")
    @GetMapping("/getFavoriteFolder")
    public R<List<FavoriteFolder>> getFavoriteFolder() {
        return favoriteFolderService.getFavoriteFolder();
    }

    @Operation(summary = "获取用户收藏内容")
    @GetMapping("/getFavorite")
    public R<List<FavoriteVo>> getFavorite(@RequestParam Long folderId) {
        return favoriteService.getFavorite(folderId);
    }

}
