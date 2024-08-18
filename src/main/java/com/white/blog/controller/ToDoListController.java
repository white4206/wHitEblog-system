package com.white.blog.controller;

import com.white.blog.common.R;
import com.white.blog.entity.ToDoList;
import com.white.blog.service.ToDoListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 待办事项控制层类
 * @date 2024/7/19 17:56:33
 */
@Tag(name = "待办事项接口")
@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;

    /**
     * @param null 无参方法
     * @return R<List < ToDoList>>
     * @name: getToDoList
     * @author: white_
     * @description: 获取用户待办事项接口
     * @date: 2024/7/5 19:31:02
     */
    @Operation(summary = "获取用户待办事项")
    @GetMapping("/getToDoList")
    public R<List<ToDoList>> getToDoList() {
        return toDoListService.getToDoList();
    }
}
