package com.team.merchantassistant.controller.customer;

import com.team.merchantassistant.service.customer.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName CustomerOrderController
 * @Description TODO
 * @Author mai
 * @Date 2020/1/24 22:33
 **/
@RestController
@RequestMapping("/customer/order")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;
    @GetMapping("/index")
    public Map<String,Object> customerOrderIndexService(@RequestHeader("authorization") String authorization){
        return customerOrderService.customerOrderIndexService(authorization);
    }
}
