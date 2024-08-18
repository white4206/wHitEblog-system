package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户表映射接口
 * @date 2024/6/17 15:54:23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
