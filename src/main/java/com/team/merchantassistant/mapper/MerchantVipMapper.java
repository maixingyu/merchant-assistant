package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.MerchantVip;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MerchantVipMapper {

    /**
     * 商户查看自己店内的vip信息
     * @param mId 关联商户信息的外键
     * @return vip信息列表
     */
    @Select("select id,name,age,sex from merchant_vip where m_id=#{m_id}")
    List<MerchantVip> findVipByMid(@Param("m_id") Integer mId);

    /**
     * 根据m_id查询openid
     * @param mId 关联商户信息的外键
     * @return openid
     */
    @Select("select openid from merchant_vip where m_id=#{m_id}")
    List<MerchantVip> findOpenidByMid(@Param("m_id") Integer mId);

    /**
     * 根据openid和m_id查询id
     * @param openid 微信小程序的唯一标识
     * @param mId 关联商户信息的外键
     * @return id
     */
    @Select("select id from merchant_vip where openid=#{openid} and m_id=#{m_id}")
    Integer findVipByOpenidAndMid(@Param("openid") String openid,@Param("m_id") Integer mId);

    /**
     * 添加vip信息
     * @param merchantVip vip信息
     */
    @Insert("insert into merchant_vip(name,age,sex,openid,m_id) values(#{v.name},#{v.age},#{v.sex},#{v.openid},#{v.mId})")
    void addVip(@Param("v") MerchantVip merchantVip);
}
