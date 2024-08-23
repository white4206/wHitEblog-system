package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.IpAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description IP地址表映射接口
 * @date 2024/8/21 00:13:48
 */
@Mapper
public interface IpAddressMapper extends BaseMapper<IpAddress> {
}
