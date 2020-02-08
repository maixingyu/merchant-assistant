package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.WebUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WebUserMapper {

    /**
     * 根据用户名和密码查询web端用户
     *
     * @param username 用户名
     * @param password 密码
     * @return WebUser
     */
    @Select("select id,username,password,m_id from web_user where username=#{username} and password=#{password}")
    WebUser findAdminUserByNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 更新m_id
     *
     * @param mId 小程序中商户的主键
     */
    @Update("update web_user set m_id=#{m_id}")
    void updateCId(@Param("m_id") Integer mId);

    /**
     * 查找已经绑定到微信小程序的商户信息
     *
     * @return 商户的信息列表
     */
    @Select("select id,username,m_id from web_user where m_id is not null")
    List<WebUser> findWebUserIsBind();

}
