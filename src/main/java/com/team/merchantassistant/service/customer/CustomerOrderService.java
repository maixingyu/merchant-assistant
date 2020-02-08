package com.team.merchantassistant.service.customer;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantOrder;
import com.team.merchantassistant.mapper.MerchantOrderMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomerOrderService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/24 22:38
 **/
@Service
public class CustomerOrderService {
    @Autowired
    private MerchantOrderMapper merchantOrderMapper;

    public Map<String,Object> customerOrderIndexService(String authorization){
        //获得openid
        String openid= JWT.decode(authorization).getClaim("openid").asString();
        //根据客户端的openid查询他的订单列表
        List<MerchantOrder> merchantOrderList=merchantOrderMapper.findOrderByOpenid(openid);
        return ResultsUtils.successWhitData("merchantOrderList",merchantOrderList);
    }
}
