package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.MerchantGoods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantGoodsMapper {
    /**
     * 根据openid查询货物列表
     * @param openid 微信小程序用户的唯一标识
     * @return List<Goods>
     */
    @Select("select id,name,quantity,unit from merchant_goods where openid=#{openid}")
    List<MerchantGoods> findGoods(@Param("openid") String openid);

    /**
     * 添加货物
     * @param merchantGoods 货物信息
     */
    @Insert("insert into merchant_goods(name,quantity,unit,openid) values(#{g.name},#{g.quantity},#{g.unit},#{g.openid})")
    void addGoods(@Param("g") MerchantGoods merchantGoods);

    /**
     * 根据货物名和openid查询货物是否存在
     * @param merchantGoods 货物信息
     * @return 用户id
     */
    @Select("select id from merchant_goods where name=#{g.name} and openid=#{g.openid}")
    Integer findIdByOpenidAndName(@Param("g") MerchantGoods merchantGoods);

    /**
     * 一对多查询    多
     * @param id 货物信息的id
     * @return Goods
     */
    @Select("select id,name,quantity,unit from merchant_goods where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(property = "merchantOperateGoodsList",column = "id",
                    many = @Many(select = "com.team.merchantassistant.mapper.MerchantOperateGoodsMapper.findOperateGoodsByGId"))
    })
    MerchantGoods findGoodsById(@Param("id") Integer id);

    /**
     * 根据id查询数量
     * @param id 货物的唯一标识
     * @return 该货物的数量
     */
    @Select("select quantity from merchant_goods where id=#{id}")
    Integer findGoodsQuantityById(@Param("id") Integer id);

    /**
     * 根据id减少货物数量
     * @param quantity 要减少的数量
     * @param id 货物的唯一标识
     */
    @Update("update merchant_goods set quantity=quantity-#{reduce_quantity} where id=#{id}")
    void updateGoodsReduceQuantityById(@Param("reduce_quantity") Integer quantity,@Param("id") Integer id);

    /**
     * 根据id增加货物数量
     * @param quantity 要增加的数量
     * @param id 货物的唯一标识
     */
    @Update("update merchant_goods set quantity=quantity+#{increase_quantity} where id=#{id}")
    void updateGoodsIncreaseQuantityById(@Param("increase_quantity") Integer quantity,@Param("id") Integer id);
}
