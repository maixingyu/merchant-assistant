package com.team.merchantassistant.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerUserMapper {
    /**
     * 根据openid查询id
     * @param openid 微信小程序的唯一标识
     * @return id
     */
    @Select("select id from customer_user where openid=#{openid}")
    Integer findIdByOpenid(@Param("openid") String openid);

    /**
     * 添加用户
     * @param openid 微信小程序的唯一标识
     */
    @Insert("insert into customer_user(openid) values(#{openid})")
    void addClientUser(@Param("openid") String openid);
}
