package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.DailyRecommendation;
import com.white.blog.mapper.DailyRecommendationMapper;
import com.white.blog.service.DailyRecommendationService;
import org.springframework.stereotype.Service;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 今日推荐服务层实现类
 * @date 2024/6/21 19:56:20
 */
@Service
public class DailyRecommendationServiceImpl extends ServiceImpl<DailyRecommendationMapper, DailyRecommendation>
        implements DailyRecommendationService {

    @Override
    public R<DailyRecommendation> getDailyRecommendation() {
        LambdaQueryWrapper<DailyRecommendation> qw = new LambdaQueryWrapper<>();
        // 按照创建时间降序查询今日推荐
        qw.orderByDesc(DailyRecommendation::getCreateTime);
        // 只查一个
        qw.last("limit 1");
        return R.success(this.getOne(qw));
    }
}
