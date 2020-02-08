package com.team.merchantassistant.service.merchant;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantVip;
import com.team.merchantassistant.mapper.MerchantUserMapper;
import com.team.merchantassistant.mapper.MerchantVipMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MerchantVipService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/23 18:43
 **/
@Service
public class MerchantVipService {
    @Autowired
    private MerchantVipMapper merchantVipMapper;
    @Autowired
    private MerchantUserMapper merchantUserMapper;

    /**
     * 初始化商户端查看自己店铺会员的页面数据
     * @param authorization token
     * @return 会员信息列表
     */
    public Map<String,Object> merchantVipIndexService(String authorization){
        //获得openid
        String openid= JWT.decode(authorization).getClaim("openid").asString();
        //根据openid查询商铺的id
        Integer mId=merchantUserMapper.findIdByOpenid(openid);
        //根据商户的id号查询店铺会员信息
        List<MerchantVip> merchantVipList=merchantVipMapper.findVipByMid(mId);
        return ResultsUtils.successWhitData("merchantVipList",merchantVipList);
    }
}
