package com.team.merchantassistant.controller.merchant;

import com.team.merchantassistant.service.merchant.MerchantVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName MerchantVipController
 * @Description TODO
 * @Author mai
 * @Date 2020/1/23 18:43
 **/
@RestController
@RequestMapping("/merchant/vip")
public class MerchantVipController {
    @Autowired
    private MerchantVipService merchantVipService;

    /**
     * 初始化商户端查看自己店铺会员的页面数据
     * @param authorization token
     * @return 会员信息列表
     */
    @GetMapping("/index")
    public Map<String,Object> merchantVipIndexController(@RequestHeader("authorization") String authorization){
        return merchantVipService.merchantVipIndexService(authorization);
    }
}
