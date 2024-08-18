package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.ToDoList;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 待办事项服务层接口
 * @date 2024/6/22 14:42:04
 */
public interface ToDoListService extends IService<ToDoList> {
    R<List<ToDoList>> getToDoList();
}
