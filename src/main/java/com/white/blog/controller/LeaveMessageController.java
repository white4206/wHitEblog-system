package com.white.blog.controller;

import com.white.blog.common.R;
import com.white.blog.entity.vo.LeaveMessageVo;
import com.white.blog.service.LeaveMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 留言控制层类
 * @date 2024/7/19 18:47:37
 */
@Tag(name = "留言接口")
@RestController
@RequestMapping("/leaveMessage")
public class LeaveMessageController {
    @Autowired
    private LeaveMessageService leaveMessageService;

    /**
     * @param null 无参方法
     * @return R<List < LeaveMessage>>
     * @name: getLeaveMessage
     * @author: white_
     * @description: 获取留言接口
     * @date: 2024/6/21 22:57:04
     */
    @Operation(summary = "获取留言")
    @GetMapping("/getLeaveMessage")
    public R<List<LeaveMessageVo>> getLeaveMessage() {
        return leaveMessageService.getLeaveMessage();
    }
}
