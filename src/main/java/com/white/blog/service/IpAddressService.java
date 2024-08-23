package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.IpAddress;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description IP地址服务层接口
 * @date 2024/8/21 00:12:16
 */
public interface IpAddressService extends IService<IpAddress> {
    R<IpAddress> getIpAddress(HttpServletRequest request);
}
