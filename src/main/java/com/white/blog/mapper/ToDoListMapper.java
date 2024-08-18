package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.ToDoList;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 待办事项表映射接口
 * @date 2024/6/22 14:37:39
 */
@Mapper
public interface ToDoListMapper extends BaseMapper<ToDoList> {
}
