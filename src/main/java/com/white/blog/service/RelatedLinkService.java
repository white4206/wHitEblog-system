package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.RelatedLink;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 相关链接服务层接口
 * @date 2024/6/21 23:39:07
 */
public interface RelatedLinkService extends IService<RelatedLink> {
    R<List<RelatedLink>> getRelatedLink();
}
