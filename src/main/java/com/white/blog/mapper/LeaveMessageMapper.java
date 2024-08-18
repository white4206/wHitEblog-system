package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.LeaveMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 留言表映射接口
 * @date 2024/6/21 22:45:04
 */
@Mapper
public interface LeaveMessageMapper extends BaseMapper<LeaveMessage> {
}
