package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.DailyRecommendation;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 今日推荐服务层接口
 * @date 2024/6/21 19:55:51
 */
public interface DailyRecommendationService extends IService<DailyRecommendation> {
   R<DailyRecommendation> getDailyRecommendation();
}
