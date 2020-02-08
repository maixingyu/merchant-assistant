package com.team.merchantassistant.service.customer;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantOrder;
import com.team.merchantassistant.bean.MerchantVip;
import com.team.merchantassistant.bean.WebUser;
import com.team.merchantassistant.mapper.MerchantOrderMapper;
import com.team.merchantassistant.mapper.MerchantVipMapper;
import com.team.merchantassistant.mapper.WebUserMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomerShopService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/24 22:33
 **/
@Service
public class CustomerShopService {
    @Autowired
    private WebUserMapper webUserMapper;
    @Autowired
    private MerchantVipMapper merchantVipMapper;
    @Autowired
    private MerchantOrderMapper merchantOrderMapper;

    /**
     * 初始化客户端获取商户端的信息列表
     *
     * @return 商户端信息列表
     */
    public Map<String, Object> customerShopIndexService() {
        List<WebUser> webUserList = webUserMapper.findWebUserIsBind();
        return ResultsUtils.successWhitData("webUserList", webUserList);
    }

    /**
     * 查询该用户是否是该店铺的会员
     *
     * @param authorization 小程序的唯一标识
     * @param mId           店铺的id
     * @return 是否是会员的标识
     */
    public Map<String, Object> customerShopIsVipService(String authorization, Integer mId) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        Integer id = merchantVipMapper.findVipByOpenidAndMid(openid, mId);
        if (id == null) {
            return ResultsUtils.successWhitData("vipFlag", false);
        } else {
            return ResultsUtils.successWhitData("vipFlag", true);
        }
    }

    /**
     * 开通vip
     *
     * @param authorization token
     * @param merchantVip   开通vip的信息
     * @return 是否成功的结果
     */
    public Map<String, Object> customerShopAddVipService(String authorization, MerchantVip merchantVip) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        merchantVip.setOpenid(openid);
        merchantVipMapper.addVip(merchantVip);
        return ResultsUtils.success();
    }

    /**
     * 顾客自助支付
     *
     * @param authorization token
     * @param merchantOrder 订单信息
     * @return 是否添加记录成功
     */
    public Map<String, Object> customerShopAddOrderService(String authorization, MerchantOrder merchantOrder) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        merchantOrder.setOpenid(openid);
        merchantOrder.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        merchantOrderMapper.addOrder(merchantOrder);
        return ResultsUtils.success();
    }

}
