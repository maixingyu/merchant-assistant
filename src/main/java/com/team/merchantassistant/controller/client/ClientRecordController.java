package com.team.merchantassistant.controller.client;

import com.team.merchantassistant.bean.Record;
import com.team.merchantassistant.service.client.ClientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName ClientRecordController
 * @Description TODO
 * @Author mai
 * @Date 2020/1/13 16:33
 **/
@RestController
@RequestMapping("/client/record")
public class ClientRecordController {
    @Autowired
    private ClientRecordService clientRecordService;

    /**
     * 获取记账首页信息
     *
     * @param authorization token
     * @return 记账首页信息
     */
    @GetMapping("/index")
    public Map<String, Object> clientIndexRecordController(@RequestHeader("authorization") String authorization) {
        return clientRecordService.clientIndexRecordService(authorization);
    }

    /**
     * 更新某条记账记录
     *
     * @param record 记账记录
     * @return 是否成功的标识
     */
    @PutMapping("/alter")
    public Map<String, Object> clientUpdateRecordController(@RequestBody Record record) {
        return clientRecordService.clientUpdateRecordService(record);
    }

    /**
     * 删除某一条记账记录
     *
     * @param id 记账记录的唯一标识
     * @return 是否成功的标识
     */
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> clientDeleteRecordController(@PathVariable("id") Integer id) {
        return clientRecordService.clientDeleteRecordService(id);
    }

    /**
     * 获得某个微信小程序用户的所有记账记录
     *
     * @param authorization token
     * @return 记账记录列表
     */
    @GetMapping("/all")
    public Map<String, Object> clientAllRecordController(@RequestHeader("authorization") String authorization) {
        return clientRecordService.clientAllRecordService(authorization);
    }

    /**
     * 添加记账记录
     *
     * @param authorization token
     * @param record        记账记录信息
     * @return 是否成功标识
     */
    @PostMapping("/add")
    public Map<String, Object> clientAddRecordController(@RequestHeader("authorization") String authorization, @RequestBody Record record) {
        return clientRecordService.clientAddRecordService(authorization, record);
    }

    /**
     * 获取小程序统计记账记录的信息
     * @param authorization token
     * @param category 类型
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 记账记录信息列表
     */
    @GetMapping("/statistics/{category}/{startDate}/{endDate}")
    public Map<String, Object> clientStatisticsRecordController(@RequestHeader("authorization") String authorization,
                                                                @PathVariable("category") String category,
                                                                @PathVariable("startDate") String startDate,
                                                                @PathVariable("endDate") String endDate) {
        return clientRecordService.clientStatisticsRecordService(authorization, category, startDate, endDate);
    }
}
