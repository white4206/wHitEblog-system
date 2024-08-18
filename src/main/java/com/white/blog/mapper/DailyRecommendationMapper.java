package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.DailyRecommendation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 今日推荐表映射接口
 * @date 2024/6/21 19:54:25
 */
@Mapper
public interface DailyRecommendationMapper extends BaseMapper<DailyRecommendation> {
}
