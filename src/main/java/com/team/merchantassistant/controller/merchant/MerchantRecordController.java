package com.team.merchantassistant.controller.merchant;

import com.team.merchantassistant.bean.MerchantRecord;
import com.team.merchantassistant.service.merchant.MerchantRecordService;
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
@RequestMapping("/merchant/record")
public class MerchantRecordController {
    @Autowired
    private MerchantRecordService merchantRecordService;

    /**
     * 获取记账首页信息
     *
     * @param authorization token
     * @return 记账首页信息
     */
    @GetMapping("/index")
    public Map<String, Object> merchantIndexRecordController(@RequestHeader("authorization") String authorization) {
        return merchantRecordService.merchantIndexRecordService(authorization);
    }

    /**
     * 更新某条记账记录
     *
     * @param merchantRecord 记账记录
     * @return 是否成功的标识
     */
    @PutMapping("/alter")
    public Map<String, Object> merchantUpdateRecordController(@RequestBody MerchantRecord merchantRecord) {
        return merchantRecordService.merchantUpdateRecordService(merchantRecord);
    }

    /**
     * 删除某一条记账记录
     *
     * @param id 记账记录的唯一标识
     * @return 是否成功的标识
     */
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> merchantDeleteRecordController(@PathVariable("id") Integer id) {
        return merchantRecordService.merchantDeleteRecordService(id);
    }

    /**
     * 获得某个微信小程序用户的所有记账记录
     *
     * @param authorization token
     * @return 记账记录列表
     */
    @GetMapping("/all")
    public Map<String, Object> merchantAllRecordController(@RequestHeader("authorization") String authorization) {
        return merchantRecordService.merchantAllRecordService(authorization);
    }

    /**
     * 添加记账记录
     *
     * @param authorization token
     * @param merchantRecord        记账记录信息
     * @return 是否成功标识
     */
    @PostMapping("/add")
    public Map<String, Object> merchantAddRecordController(@RequestHeader("authorization") String authorization,
                                                           @RequestBody MerchantRecord merchantRecord) {
        return merchantRecordService.merchantAddRecordService(authorization, merchantRecord);
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
    public Map<String, Object> merchantStatisticsRecordController(@RequestHeader("authorization") String authorization,
                                                                @PathVariable("category") String category,
                                                                @PathVariable("startDate") String startDate,
                                                                @PathVariable("endDate") String endDate) {
        return merchantRecordService.merchantStatisticsRecordService(authorization, category, startDate, endDate);
    }
}
