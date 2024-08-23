package com.white.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.IpAddress;
import com.white.blog.mapper.IpAddressMapper;
import com.white.blog.service.IpAddressService;
import com.white.blog.utils.IPUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description
 * @date 2024/8/21 00:13:11
 */
@Service
public class IpAddressServiceImpl extends ServiceImpl<IpAddressMapper, IpAddress> implements IpAddressService {

    @Override
    public R<IpAddress> getIpAddress(HttpServletRequest request) {

        // 获取访问者IP
        String ip = IPUtils.getClientIP(request);

        IpAddress ipAddress = new IpAddress();
        // 存储访问者IP
        ipAddress.setIp(ip);
        // 获取并存储访问者IP属地
        ipAddress.setAddress(IPUtils.getCityInfoByMemorySearch(ip));
        // 获取并存储访问者访问时间
        ipAddress.setAccessTime(LocalDateTime.now());
        // 获取并存储访问者访问设备
        ipAddress.setAccessOs(IPUtils.getOsName(request));

        // 存储访问信息进数据库
        this.save(ipAddress);

        return R.success(ipAddress);
    }
}
