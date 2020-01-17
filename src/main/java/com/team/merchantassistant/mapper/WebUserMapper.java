package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.WebUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WebUserMapper {

    /**
     * 根据用户名和密码查询web端用户
     * @param username 用户名
     * @param password 密码
     * @return WebUser
     */
    @Select("select id,username,password,c_id from web_user where username=#{username} and password=#{password}")
    WebUser findAdminUserByNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 更新c_id
     * @param cId 小程序用户中的主键
     */
    @Update("update web_user set c_id=#{c_id}")
    void updateCId(@Param("c_id") Integer cId);

}
