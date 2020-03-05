package com.team.merchantassistant.service.merchant;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantVip;
import com.team.merchantassistant.mapper.MerchantDiscountMapper;
import com.team.merchantassistant.mapper.MerchantUserMapper;
import com.team.merchantassistant.mapper.MerchantVipMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MerchantDiscountService
 * @Description TODO
 * @Author mai
 * @Date 2020/3/4 16:12
 **/
@Service
public class MerchantDiscountService {
    @Autowired
    private MerchantUserMapper merchantUserMapper;
    @Autowired
    private MerchantVipMapper merchantVipMapper;
    @Autowired
    private MerchantDiscountMapper merchantDiscountMapper;

    /**
     * 商户发放优惠券
     *
     * @param authorization token
     * @param money 优惠券的金额
     * @return 处理结果
     */
    @Transactional
    public Map<String, Object> merchantDiscountAddService(String authorization, BigDecimal money) {
        //获得该商户的openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        //使用openid查询出商家的m_id
        Integer mId = merchantUserMapper.findIdByOpenid(openid);
        //根据m_id查询出这个商户的所有会员
        List<MerchantVip> merchantVipList = merchantVipMapper.findOpenidByMid(mId);
        //为每一位会员添加一张优惠券
        for (MerchantVip merchantVip : merchantVipList) {
            merchantDiscountMapper.addDiscount(money, merchantVip.getOpenid(), mId);
        }
        return ResultsUtils.success();
    }
}
