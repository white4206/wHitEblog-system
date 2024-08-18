package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.RelatedLink;
import com.white.blog.mapper.RelatedLinkMapper;
import com.white.blog.service.RelatedLinkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 相关链接服务层实现类
 * @date 2024/6/21 23:39:45
 */
@Service
public class RelatedLinkServiceImpl extends ServiceImpl<RelatedLinkMapper, RelatedLink> implements RelatedLinkService {
    @Override
    public R<List<RelatedLink>> getRelatedLink() {
        LambdaQueryWrapper<RelatedLink> qw = new LambdaQueryWrapper<>();
        // 按照排序字段降序查询相关链接
        qw.orderByAsc(RelatedLink::getSort);
        // 查询显示的相关链接
        qw.eq(RelatedLink::getStatus, 1);
        // 查询相关链接
        return R.success(this.list(qw));
    }
}
