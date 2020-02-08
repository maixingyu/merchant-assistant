package com.team.merchantassistant.controller.customer;

import com.team.merchantassistant.bean.MerchantOrder;
import com.team.merchantassistant.bean.MerchantVip;
import com.team.merchantassistant.service.customer.CustomerShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName CustomerVipController
 * @Description TODO
 * @Author mai
 * @Date 2020/1/24 22:33
 **/
@RestController
@RequestMapping("/customer/shop")
public class CustomerShopController {
    @Autowired
    private CustomerShopService customerShopService;

    /**
     * 初始化客户端获取商户端的信息列表
     *
     * @return 商户端信息列表
     */
    @GetMapping("/index")
    public Map<String, Object> customerShopIndexController() {
        return customerShopService.customerShopIndexService();
    }

    /**
     * 查询该用户是否是该店铺的会员
     *
     * @param authorization 小程序的唯一标识
     * @param mId           店铺的id
     * @return 是否是会员的标识
     */
    @GetMapping("/isVip/{mId}")
    public Map<String, Object> customerShopIsVipController(@RequestHeader("authorization") String authorization,
                                                           @PathVariable("mId") Integer mId) {
        return customerShopService.customerShopIsVipService(authorization, mId);
    }

    /**
     * 开通vip
     *
     * @param authorization token
     * @param merchantVip   开通vip的信息
     * @return 是否成功的结果
     */
    @PostMapping("/addVip")
    public Map<String, Object> customerShopAddVipController(@RequestHeader("authorization") String authorization, MerchantVip merchantVip) {
        return customerShopService.customerShopAddVipService(authorization, merchantVip);
    }

    /**
     * 顾客自助支付
     *
     * @param authorization token
     * @param merchantOrder 订单信息
     * @return 是否添加记录成功
     */
    @PostMapping("/addOrder")
    public Map<String, Object> customerShopAddOrderController(@RequestHeader("authorization") String authorization, MerchantOrder merchantOrder) {
        return customerShopService.customerShopAddOrderService(authorization, merchantOrder);
    }
}
