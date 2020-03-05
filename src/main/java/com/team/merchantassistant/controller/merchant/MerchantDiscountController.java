package com.team.merchantassistant.controller.merchant;

import com.team.merchantassistant.service.merchant.MerchantDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName MerchantDiscountController
 * @Description TODO
 * @Author mai
 * @Date 2020/3/4 16:10
 **/
@RestController
@RequestMapping("/merchant/discount")
public class MerchantDiscountController {
    @Autowired
    private MerchantDiscountService merchantDiscountService;

    /**
     * 商户发放优惠券
     * @param authorization token
     * @param money 优惠券的金额
     * @return 是否处理成功的结果
     */
    @PostMapping("/add")
    public Map<String, Object> merchantDiscountAddController(@RequestHeader("authorization") String authorization,
                                                             @RequestParam("money") BigDecimal money) {
        return merchantDiscountService.merchantDiscountAddService(authorization, money);
    }

}
