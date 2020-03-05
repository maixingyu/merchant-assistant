package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.MerchantDiscount;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName MerchantDiscountMapper
 * @Description TODO
 * @Author mai
 * @Date 2020/3/4 16:14
 **/
@Mapper
public interface MerchantDiscountMapper {

    /**
     * 添加优惠券记录
     * @param money 金额
     * @param openid 会员的openid 小程序的唯一标识
     * @param mId 商户的外键
     */
    @Insert("insert into merchant_discount(money,openid,m_id) values(#{money},#{openid},#{m_id})")
    void addDiscount(@Param("money")BigDecimal money,@Param("openid") String openid,@Param("m_id") Integer mId);

    /**
     * 根据openid和mId查询优惠券
     * @param openid 客户的openid小程序的唯一标识
     * @param mId 商户的外键
     * @return 优惠券的信息列表
     */
    @Select("select id,money from merchant_discount where openid=#{openid} and m_id=#{m_id}")
    List<MerchantDiscount> findDiscountByOpenidAndMId(@Param("openid") String openid,@Param("m_id") Integer mId);

    /**
     * 根据id删除优惠券
     * @param id 优惠券唯一标识
     */
    @Delete("delete from merchant_discount where id=#{id}")
    void deleteDiscount(@Param("id") Integer id);
}
