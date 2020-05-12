package com.blog.framework.mapper;

import com.blog.framework.model.UserModel;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * blog
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public interface UserMapper extends Mapper<UserModel>, MySqlMapper<UserModel> {


    /**
     * 更新用户信息
     *
     * @param userModel 用户信息
     * @return int
     */
    @Update(" update t_user set username = #{username},sex = #{sex},show_flag = #{showflag}," +
            " head_photo = #{headPhoto},website_name = #{websiteName},website_address = #{websiteAddress}," +
            " website_introduction = #{websiteIntroduction},website_logo = #{websiteLogo} " +
            " where id = #{id}")
    int updateUserInfo(UserModel userModel);
}
