package com.white.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 今日推荐实体类
 * @date 2024/6/21 17:17:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("daily_recommendation")
public class DailyRecommendation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /** 今日推荐id */
    private Long id;
    /**
     * 今日推荐标题
     */
    private String dailyTitle;
    /**
     * 今日推荐按钮文本
     */
    private String btnText;
    /**
     * 标题
     */
    private String title;
    /**
     * 封面
     */
    private String cover;
    /**
     * 描述
     */
    private String description;
    /**
     * 地址
     */
    private String url;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
