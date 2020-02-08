package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.MerchantOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MerchantOrderMapper {
    /**
     * 商户查看自己店内的订单信息
     * @param mId 关联商户信息的外键
     * @return 订单信息列表
     */
    @Select("select id,order_number,money,time from merchant_order where m_id=#{m_id}")
    List<MerchantOrder> findOrderByMid(@Param("m_id") Integer mId);

    /**
     * 根据客户的openid产需订单信息
     * @param openid 微信小程序端的唯一标识
     * @return 订单信息
     */
    @Select("select id,order_number,money,time from merchant_order where openid=#{openid}")
    List<MerchantOrder> findOrderByOpenid(@Param("openid") String openid);

    /**
     * 添加订单
     * @param merchantOrder 订单信息
     */
    @Insert("insert into merchant_order(order_number,money,time,openid,m_id) " +
            "values(#{o.orderNumber},#{o.money},#{o.time},#{o.openid},#{o.mId})")
    void addOrder(@Param("o") MerchantOrder merchantOrder);
}
