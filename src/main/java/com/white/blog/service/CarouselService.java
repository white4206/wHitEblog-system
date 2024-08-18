package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.Carousel;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 轮播图服务层接口
 * @date 2024/6/21 22:27:23
 */
public interface CarouselService extends IService<Carousel> {
    R<List<Carousel>> getCarousel();
}
