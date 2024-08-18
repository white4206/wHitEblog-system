package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.ToDoList;
import com.white.blog.mapper.ToDoListMapper;
import com.white.blog.service.ToDoListService;
import com.white.blog.utils.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 待办事项服务层实现类
 * @date 2024/6/22 14:42:38
 */
@Service
public class ToDoListServiceImpl extends ServiceImpl<ToDoListMapper, ToDoList> implements ToDoListService {
    @Override
    public R<List<ToDoList>> getToDoList() {
        LambdaQueryWrapper<ToDoList> qw = new LambdaQueryWrapper<>();
        qw.eq(ToDoList::getUserId, ShiroUtils.getCurrentUser().getId());
        qw.eq(ToDoList::getUserId, 1);
        // 按结束日期降序排序查询待办事项
        qw.orderByDesc(ToDoList::getFinishDate);
        return R.success(this.list(qw));
    }
}
