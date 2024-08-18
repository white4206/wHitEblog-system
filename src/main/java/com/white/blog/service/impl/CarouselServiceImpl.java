package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.BlogArticleTag;
import com.white.blog.entity.Carousel;
import com.white.blog.mapper.CarouselMapper;
import com.white.blog.service.CarouselService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 轮播图服务层实现类
 * @date 2024/6/21 22:28:28
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
    @Override
    public R<List<Carousel>> getCarousel() {
        LambdaQueryWrapper<Carousel> qw = new LambdaQueryWrapper<>();
        // 按照排序字段升序查询轮播图
        qw.orderByAsc(Carousel::getSort);
        // 查询启用的轮播图
        qw.eq(Carousel::getStatus, 1);
        return R.success(this.list(qw));
    }
}
