package com.team.merchantassistant.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface MerchantUserMapper {
    /**
     * 根据openid查询id
     * @param openid 微信小程序的唯一标识
     * @return id
     */
    @Select("select id from merchant_user where openid=#{openid}")
    Integer findIdByOpenid(@Param("openid") String openid);

    /**
     * 添加用户
     * @param openid 微信小程序的唯一标识
     */
    @Insert("insert into merchant_user(openid) values(#{openid})")
    void addClientUser(@Param("openid") String openid);

    /**
     * 据openid查询w_id
     * @param openid 微信小程序的唯一标识
     * @return w_id
     */
    @Select("select w_id from merchant_user where openid=#{openid}")
    Integer findWIdByOpenId(@Param("openid") String openid);

    /**
     * 更新w_id
     * @param wId web用户的标识符
     */
    @Update("update merchant_user set w_id=#{w_id}")
    void updateWId(@Param("w_id") Integer wId);
}
