package com.team.merchantassistant.service.merchant;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantRecord;
import com.team.merchantassistant.common.RecordResult;
import com.team.merchantassistant.mapper.MerchantRecordMapper;
import com.team.merchantassistant.utils.DateUtils;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientRecordService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/13 16:47
 **/
@Service
public class MerchantRecordService {
    @Autowired
    private MerchantRecordMapper merchantRecordMapper;

    /**
     * 获取记账首页信息
     *
     * @param authorization token
     * @return 记账首页信息
     */
    public Map<String, Object> merchantIndexRecordService(String authorization) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        String today = DateUtils.getToday();
        String yesterday = DateUtils.getYesterday();
        String weekStart = DateUtils.getWeekStart();
        String weekEnd = DateUtils.getWeekEnd();
        String monthStart = DateUtils.getMonthStart();
        String monthEnd = DateUtils.getMonthEnd();
        String yearStart = DateUtils.getYearStart();
        String yearEnd = DateUtils.getYearEnd();
        //获得某个商户今日记账记录
        List<MerchantRecord> todayList = merchantRecordMapper.findRecordByOpenidAndDate(today, openid);
        for (MerchantRecord merchantRecord : todayList) {
            merchantRecord.setTime(merchantRecord.getTime().substring(0, 5));
        }
        //获得某个商户昨日记账记录
        RecordResult yesterdayResult = new RecordResult("昨日", DateUtils.getYesterdayMe(),
                merchantRecordMapper.totalDayMoneyByOpenidAndDate(yesterday, openid));
        //获得某个商户一周记账记录
        RecordResult weekResult = new RecordResult("本周", DateUtils.getWeekStartMe() + "-" + DateUtils.getWeekEndMe(),
                merchantRecordMapper.totalIntervalMoneyByOpenidAndDate(weekStart, weekEnd, openid));
        //获得某个商户一月记账记录
        RecordResult monthResult = new RecordResult("本月", DateUtils.getMonthStartMe() + "-" + DateUtils.getMonthEndMe(),
                merchantRecordMapper.totalIntervalMoneyByOpenidAndDate(monthStart, monthEnd, openid));
        //获得某个商户一年记账记录
        RecordResult yearResult = new RecordResult("本年", DateUtils.getYearStartMe() + "-" + DateUtils.getYearEndMe(),
                merchantRecordMapper.totalIntervalMoneyByOpenidAndDate(yearStart, yearEnd, openid));
        Map<String, Object> map = new HashMap<>();
        map.put("yesterday", yesterdayResult);
        map.put("week", weekResult);
        map.put("month", monthResult);
        map.put("year", yearResult);
        return ResultsUtils.successWhitData("todayList", todayList, "totalList", map);
    }

    /**
     * 更新某条记账记录
     *
     * @param merchantRecord 记账记录
     * @return 是否成功的标识
     */
    public Map<String, Object> merchantUpdateRecordService(MerchantRecord merchantRecord) {
        merchantRecordMapper.updateRecord(merchantRecord);
        return ResultsUtils.success();
    }

    /**
     * 删除某一条记账记录
     *
     * @param id 记账记录的唯一标识
     * @return 是否成功的标识
     */
    public Map<String, Object> merchantDeleteRecordService(Integer id) {
        merchantRecordMapper.deleteRecordById(id);
        return ResultsUtils.success();
    }

    /**
     * 获得某个微信小程序用户的所有记账记录
     *
     * @param authorization token
     * @return 记账记录
     */
    public Map<String, Object> merchantAllRecordService(String authorization) {
        //从JWT中获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<MerchantRecord> merchantRecordList = merchantRecordMapper.findRecordByOpenid(openid);
        for (MerchantRecord merchantRecord : merchantRecordList) {
            merchantRecord.setTime(merchantRecord.getTime().substring(0, 5));
        }
        return ResultsUtils.successWhitData("recordList", merchantRecordList);
    }

    /**
     * 添加记账记录
     *
     * @param authorization token
     * @param merchantRecord        记账记录信息
     * @return 是否成功标识
     */
    public Map<String, Object> merchantAddRecordService(String authorization, MerchantRecord merchantRecord) {
        //从JWT中获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        merchantRecord.setOpenid(openid);
        merchantRecordMapper.addRecord(merchantRecord);
        return ResultsUtils.success();
    }

    /**
     * 获取小程序统计记账记录的信息
     * @param authorization token
     * @param category 类型
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 记账记录信息列表
     */
    public Map<String, Object> merchantStatisticsRecordService(String authorization, String category, String startDate, String endDate) {
        //从JWT中获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<MerchantRecord> merchantRecordList =new ArrayList<>();
        if (startDate.length() == 4 && endDate.length() == 4) {
            //如果上传来的日期格式为2020
            merchantRecordList = merchantRecordMapper.findRecordByOpenidAndCategoryAndDate(openid,category,DateUtils.getFirstDayOfYear(startDate),DateUtils.getLastDayOfYear(endDate));
        } else if (startDate.length() == 7 && endDate.length() == 7) {
            //如果上传来的日期格式为2020-10
            merchantRecordList = merchantRecordMapper.findRecordByOpenidAndCategoryAndDate(openid,category,DateUtils.getFirstDayOfMonth(startDate),DateUtils.getLastDayOfMonth(endDate));
        } else {
            //如果上传来的日期格式为2020-10-11
            merchantRecordList = merchantRecordMapper.findRecordByOpenidAndCategoryAndDate(openid, category, startDate, endDate);
        }
        //查询记录总和的钱
        BigDecimal total=new BigDecimal(Double.toString(0.00));
        for (MerchantRecord merchantRecord : merchantRecordList) {
            total=total.add(merchantRecord.getMoney());
        }
        return ResultsUtils.successWhitData("total",total,"dataList", merchantRecordList);
    }
}
