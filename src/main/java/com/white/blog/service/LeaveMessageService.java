package com.white.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.blog.common.R;
import com.white.blog.entity.vo.LeaveMessageVo;
import com.white.blog.entity.LeaveMessage;

import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 留言服务层接口
 * @date 2024/6/21 22:49:19
 */
public interface LeaveMessageService extends IService<LeaveMessage> {
    R<List<LeaveMessageVo>> getLeaveMessage();
}
