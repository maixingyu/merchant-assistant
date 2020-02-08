package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.MerchantSupplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantSupplierMapper {

    /**
     * 根据openid查询供应商信息
     *
     * @param openid 微信小程序用户的唯一标识
     * @return List<Supplier>
     */
    @Select("select * from merchant_supplier where openid=#{openid}")
    List<MerchantSupplier> findSupplierByOpenid(@Param("openid") String openid);

    /**
     * 根据openid查询供应商信息
     * @param openid 微信小程序用户的唯一标识
     * @return List<String>
     */
    @Select("select name from merchant_supplier where openid=#{openid}")
    List<String> findSupplierNameByOpenid(@Param("openid") String openid);

    /**
     * 添加供应商信息
     * @param merchantSupplier 供应商信息
     */
    @Insert("insert into merchant_supplier(name,tel,address,openid) values(#{s.name},#{s.tel},#{s.address},#{s.openid})")
    void addSupplier(@Param("s") MerchantSupplier merchantSupplier);

    /**
     * 更新供应商信息
     * @param merchantSupplier 新的供应商信息
     */
    @Update("update merchant_supplier set name=#{s.name},tel=#{s.tel},address=#{s.address} where id=#{s.id}")
    void alterSupplierById(@Param("s") MerchantSupplier merchantSupplier);

    /**
     * 删除供应商信息
     * @param id 供应商的唯一标识
     */
    @Delete("delete from merchant_supplier where id=#{id}")
    void deleteSupplierById(@Param("id") Integer id);
}
