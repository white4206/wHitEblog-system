package com.white.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.blog.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description 用户信息表映射接口
 * @date 2024/7/6 17:22:09
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    Boolean updateByUserId(UserInfo userInfo);

    @Select("select id, user_id, gender, description, area, birthday, identity, view_num, fans_num, rank_data," +
            " attention_num, liked_num, fav_num, comment_num, is_founder from user_info where user_id=#{userId};")
    UserInfo selectByUserId(Long userId);
}
