package com.team.merchantassistant.controller.merchant;

import com.team.merchantassistant.service.merchant.MerchantOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName MerchantOrderController
 * @Description TODO
 * @Author mai
 * @Date 2020/1/23 20:05
 **/
@RestController
@RequestMapping("/merchant/order")
public class MerchantOrderController {
    @Autowired
    private MerchantOrderService merchantOrderService;

    /**
     * 初始化商户端查看自己店铺订单的页面数据
     * @param authorization token
     * @return 订单信息信息列表
     */
    @GetMapping("/index")
    public Map<String,Object> merchantOrderIndexService(@RequestHeader("authorization") String authorization){
        return merchantOrderService.merchantOrderIndexService(authorization);
    }
}
