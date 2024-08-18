package com.white.blog.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.white.blog.entity.LeaveMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 留言视图层实体类
 * @date 2024/6/21 23:02:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveMessageVo {
    /**
     * 留言id
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 内容
     */
    private String content;
    /**
     * 留言时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    public LeaveMessageVo(LeaveMessage leaveMessage, String nickname, String avatar) {
        this.id = leaveMessage.getId();
        this.nickname = nickname;
        this.avatar = avatar;
        this.content = leaveMessage.getContent();
        this.createTime = leaveMessage.getCreateTime();
    }
}
