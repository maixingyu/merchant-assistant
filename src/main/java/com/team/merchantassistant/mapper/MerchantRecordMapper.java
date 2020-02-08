package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.MerchantRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantRecordMapper {

    /**
     * 根据openid和日期查找某一天的记账记录
     *
     * @param date   日期
     * @param openid 微信小程序的唯一标识
     * @return List<Record>
     */
    @Select("select id,category,typename,money,date,time,remarks from merchant_record where date=#{date} and openid=#{openid} order by time desc")
    List<MerchantRecord> findRecordByOpenidAndDate(@Param("date") String date, @Param("openid") String openid);

    /**
     * 根据openid和时间统计某一天的记账金额数目
     *
     * @param date   日期
     * @param openid 微信小程序的唯一标识
     * @return 字符串类型的金额
     */
    @Select("select sum(money) from merchant_record where date=#{date} and openid=#{openid}")
    String totalDayMoneyByOpenidAndDate(@Param("date") String date, @Param("openid") String openid);

    /**
     * 根据openid和时间统计某时间段内的记账金额数目
     *
     * @param start  开始时间
     * @param end    结束时间
     * @param openid 微信小程序的唯一标识
     * @return 字符串类型的金额
     */
    @Select("select sum(money) from merchant_record where date >= #{start} and date <= #{end} and openid=#{openid}")
    String totalIntervalMoneyByOpenidAndDate(@Param("start") String start, @Param("end") String end, @Param("openid") String openid);

    /**
     * 根据id更新记账记录
     *
     * @param merchantRecord 记账信息
     */
    @Update("update merchant_record set category=#{r.category},typename=#{r.typename},money=#{r.money},date=#{r.date}," +
            "time=#{r.time},remarks=#{r.remarks} where id=#{r.id}")
    void updateRecord(@Param("r") MerchantRecord merchantRecord);

    /**
     * 根据id删除记账记账
     *
     * @param id 记账记录的唯一标识
     */
    @Delete("delete from merchant_record where id=#{id}")
    void deleteRecordById(@Param("id") Integer id);

    /**
     * 根据openid查询记账记录
     *
     * @param openid 微信小程序用户的唯一标识
     * @return List<Record>
     */
    @Select("select id,category,typename,money,date,time,remarks from merchant_record where openid=#{openid} order by concat(date,time) desc")
    List<MerchantRecord> findRecordByOpenid(@Param("openid") String openid);

    /**
     * 添加record记录
     * @param merchantRecord 记账记录信息
     */
    @Insert("insert into merchant_record(category,typename,money,date,time,remarks,openid) " +
            "values(#{r.category},#{r.typename},#{r.money},#{r.date},#{r.time},#{r.remarks},#{r.openid})")
    void addRecord(@Param("r") MerchantRecord merchantRecord);

    /**
     * 根据openid，category和日期查询一定区间内的记账记录
     * @param openid 微信小程序用户的唯一标识
     * @param category 类别
     * @param startDay 开始日期
     * @param endDay 结束日期
     * @return List<Record>
     */
    @Select("select typename,money from merchant_record where openid=#{openid} and category =#{category} and date>=#{startDay} and date<=#{endDay}")
    List<MerchantRecord> findRecordByOpenidAndCategoryAndDate(@Param("openid") String openid, @Param("category") String category,
                                                              @Param("startDay") String startDay, @Param("endDay") String endDay);
}
