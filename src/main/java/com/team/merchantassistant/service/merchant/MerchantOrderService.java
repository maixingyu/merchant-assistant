package com.team.merchantassistant.service.merchant;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantOrder;
import com.team.merchantassistant.mapper.MerchantOrderMapper;
import com.team.merchantassistant.mapper.MerchantUserMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MerchantOrderService {
    @Autowired
    private MerchantOrderMapper merchantOrderMapper;
    @Resource
    private MerchantUserMapper merchantUserMapper;
    /**
     * 初始化商户端查看自己店铺订单的页面数据
     * @param authorization token
     * @return 订单信息信息列表
     */
    public Map<String,Object> merchantOrderIndexService(String authorization){
        //获得openid
        String openid= JWT.decode(authorization).getClaim("openid").asString();
        //根据openid查询商铺的id
        Integer mId=merchantUserMapper.findIdByOpenid(openid);
        //根据商户的id号查询店铺会员信息
        List<MerchantOrder> merchantOrderList=merchantOrderMapper.findOrderByMid(mId);
        return ResultsUtils.successWhitData("merchantOrderList",merchantOrderList);
    }
}
