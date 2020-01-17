package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.OperateGoods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateGoodsMapper {

    /**
     * 一对多查询   一
     * @param gId 外键 货物信息的id
     * @return 货物的增删信息列表
     */
    @Select("select id,quantity,date,flag,supplier_name,g_id from operate_goods where g_id=#{g_id}")
    List<OperateGoods> findOperateGoodsByGId(@Param("g_id") Integer gId);

    /**
     * 添加货物操作信息
     * @param operateGoods 货物操作的信息
     */
    @Insert("insert into operate_goods(quantity,date,flag,supplier_name,g_id) values(#{o.quantity},#{o.date},#{o.flag},#{o.supplierName},#{o.gId})")
    void addOperateGoods(@Param("o") OperateGoods operateGoods);
}
