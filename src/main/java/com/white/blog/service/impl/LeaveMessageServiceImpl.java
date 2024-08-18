package com.white.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.blog.common.R;
import com.white.blog.entity.LeaveMessage;
import com.white.blog.entity.User;
import com.white.blog.entity.vo.LeaveMessageVo;
import com.white.blog.mapper.LeaveMessageMapper;
import com.white.blog.mapper.UserMapper;
import com.white.blog.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 留言服务层实现类
 * @date 2024/6/21 22:49:52
 */
@Service
public class LeaveMessageServiceImpl extends ServiceImpl<LeaveMessageMapper, LeaveMessage> implements LeaveMessageService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R<List<LeaveMessageVo>> getLeaveMessage() {
        LambdaQueryWrapper<LeaveMessage> qw = new LambdaQueryWrapper<>();
        // 按照留言时间降序查询留言
        qw.orderByDesc(LeaveMessage::getCreateTime);
        List<LeaveMessageVo> leaveMessageVoList = new ArrayList<LeaveMessageVo>();
        this.list(qw).forEach(leaveMessage -> {
            // 查询用户
            User user = userMapper.selectById(leaveMessage.getUserId());
            // 设置返回数据
            LeaveMessageVo leaveMessageVo = new LeaveMessageVo(leaveMessage, user.getNickname(), user.getAvatar());
            leaveMessageVoList.add(leaveMessageVo);
        });
        return R.success(leaveMessageVoList);
    }
}
