package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.Supplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SupplierMapper {

    /**
     * 根据openid查询供应商信息
     *
     * @param openid 微信小程序用户的唯一标识
     * @return List<Supplier>
     */
    @Select("select * from supplier where openid=#{openid}")
    List<Supplier> findSupplierByOpenid(@Param("openid") String openid);

    /**
     * 根据openid查询供应商信息
     * @param openid 微信小程序用户的唯一标识
     * @return List<String>
     */
    @Select("select name from supplier where openid=#{openid}")
    List<String> findSupplierNameByOpenid(@Param("openid") String openid);

    /**
     * 添加供应商信息
     * @param supplier 供应商信息
     */
    @Insert("insert into supplier(name,tel,address,openid) values(#{s.name},#{s.tel},#{s.address},#{s.openid})")
    void addSupplier(@Param("s") Supplier supplier);

    /**
     * 更新供应商信息
     * @param supplier 新的供应商信息
     */
    @Update("update supplier set name=#{s.name},tel=#{s.tel},address=#{s.address} where id=#{s.id}")
    void alterSupplierById(@Param("s") Supplier supplier);

    /**
     * 删除供应商信息
     * @param id 供应商的唯一标识
     */
    @Delete("delete from supplier where id=#{id}")
    void deleteSupplierById(@Param("id") Integer id);
}
