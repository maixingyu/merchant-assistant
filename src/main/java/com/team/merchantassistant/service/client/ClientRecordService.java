package com.team.merchantassistant.service.client;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.Record;
import com.team.merchantassistant.common.RecordResult;
import com.team.merchantassistant.mapper.RecordMapper;
import com.team.merchantassistant.utils.DateUtils;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ClientRecordService {
    @Resource
    private RecordMapper recordMapper;

    /**
     * 获取记账首页信息
     *
     * @param authorization token
     * @return 记账首页信息
     */
    public Map<String, Object> clientIndexRecordService(String authorization) {
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
        List<Record> todayList = recordMapper.findRecordByOpenidAndDate(today, openid);
        for (Record record : todayList) {
            record.setTime(record.getTime().substring(0, 5));
        }
        //获得某个商户昨日记账记录
        RecordResult yesterdayResult = new RecordResult("昨日", DateUtils.getYesterdayMe(),
                recordMapper.totalDayMoneyByOpenidAndDate(yesterday, openid));
        //获得某个商户一周记账记录
        RecordResult weekResult = new RecordResult("本周", DateUtils.getWeekStartMe() + "-" + DateUtils.getWeekEndMe(),
                recordMapper.totalIntervalMoneyByOpenidAndDate(weekStart, weekEnd, openid));
        //获得某个商户一月记账记录
        RecordResult monthResult = new RecordResult("本月", DateUtils.getMonthStartMe() + "-" + DateUtils.getMonthEndMe(),
                recordMapper.totalIntervalMoneyByOpenidAndDate(monthStart, monthEnd, openid));
        //获得某个商户一年记账记录
        RecordResult yearResult = new RecordResult("本年", DateUtils.getYearStartMe() + "-" + DateUtils.getYearEndMe(),
                recordMapper.totalIntervalMoneyByOpenidAndDate(yearStart, yearEnd, openid));
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
     * @param record 记账记录
     * @return 是否成功的标识
     */
    public Map<String, Object> clientUpdateRecordService(Record record) {
        recordMapper.updateRecord(record);
        return ResultsUtils.success();
    }

    /**
     * 删除某一条记账记录
     *
     * @param id 记账记录的唯一标识
     * @return 是否成功的标识
     */
    public Map<String, Object> clientDeleteRecordService(Integer id) {
        recordMapper.deleteRecordById(id);
        return ResultsUtils.success();
    }

    /**
     * 获得某个微信小程序用户的所有记账记录
     *
     * @param authorization token
     * @return 记账记录
     */
    public Map<String, Object> clientAllRecordService(String authorization) {
        //从JWT中获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<Record> recordList = recordMapper.findRecordByOpenid(openid);
        for (Record record : recordList) {
            record.setTime(record.getTime().substring(0, 5));
        }
        return ResultsUtils.successWhitData("recordList", recordList);
    }

    /**
     * 添加记账记录
     *
     * @param authorization token
     * @param record        记账记录信息
     * @return 是否成功标识
     */
    public Map<String, Object> clientAddRecordService(String authorization, Record record) {
        //从JWT中获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        record.setOpenid(openid);
        recordMapper.addRecord(record);
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
    public Map<String, Object> clientStatisticsRecordService(String authorization, String category, String startDate, String endDate) {
        //从JWT中获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<Record> recordList=new ArrayList<>();
        if (startDate.length() == 4 && endDate.length() == 4) {
            //如果上传来的日期格式为2020
            recordList=recordMapper.findRecordByOpenidAndCategoryAndDate(openid,category,DateUtils.getFirstDayOfYear(startDate),DateUtils.getLastDayOfYear(endDate));
        } else if (startDate.length() == 7 && endDate.length() == 7) {
            //如果上传来的日期格式为2020-10
            recordList=recordMapper.findRecordByOpenidAndCategoryAndDate(openid,category,DateUtils.getFirstDayOfMonth(startDate),DateUtils.getLastDayOfMonth(endDate));
        } else {
            //如果上传来的日期格式为2020-10-11
            recordList = recordMapper.findRecordByOpenidAndCategoryAndDate(openid, category, startDate, endDate);
        }
        //查询记录总和的钱
        BigDecimal total=new BigDecimal(Double.toString(0.00));
        for (Record record : recordList) {
            total=total.add(record.getMoney());
        }
        return ResultsUtils.successWhitData("total",total,"dataList",recordList);
    }
}
