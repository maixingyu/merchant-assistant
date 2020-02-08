package com.team.merchantassistant.service.merchant;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantGoods;
import com.team.merchantassistant.bean.MerchantOperateGoods;
import com.team.merchantassistant.mapper.MerchantGoodsMapper;
import com.team.merchantassistant.mapper.MerchantOperateGoodsMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientGoodsService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/16 18:33
 **/
@Service
public class MerchantGoodsService {
    @Autowired
    private MerchantGoodsMapper merchantGoodsMapper;
    @Autowired
    private MerchantOperateGoodsMapper merchantOperateGoodsMapper;

    /**
     * 获得货物信息
     *
     * @param authorization token
     * @return 商品信息列表
     */
    public Map<String, Object> merchantGoodsIndexService(String authorization) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<MerchantGoods> merchantGoodsList = merchantGoodsMapper.findGoods(openid);
        return ResultsUtils.successWhitData("goodsList", merchantGoodsList);
    }

    /**
     * 添加货物信息
     *
     * @param authorization token
     * @param merchantGoods         货物信息
     * @return 是否成功的标识
     */
    public Map<String, Object> merchantGoodsAddService(String authorization, MerchantGoods merchantGoods) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        merchantGoods.setOpenid(openid);
        //查询该货物名是否存在
        if (merchantGoodsMapper.findIdByOpenidAndName(merchantGoods) == null) {
            merchantGoodsMapper.addGoods(merchantGoods);
            return ResultsUtils.success();
        } else {
            return ResultsUtils.goodsNameExits();
        }
    }

    /**
     * 初始化货物增删信息列表
     *
     * @param id 货物id
     * @return 货物增删信息列表
     */
    public Map<String, Object> merchantGoodsOperateService(Integer id) {
        MerchantGoods merchantGoods = merchantGoodsMapper.findGoodsById(id);
        return ResultsUtils.successWhitData("goods", merchantGoods);
    }

    /**
     * 消耗库存
     *
     * @param merchantOperateGoods 库存操作信息
     * @return 操作是否成功
     */
    @Transactional
    public Map<String, Object> merchantGoodsReduceService(MerchantOperateGoods merchantOperateGoods) {
        //获得库存数量
        Integer totalQuantity = merchantGoodsMapper.findGoodsQuantityById(merchantOperateGoods.getGId());
        //查询库存是否足够
        if (totalQuantity >= merchantOperateGoods.getQuantity()) {
            //添加记录到货物操作表
            merchantOperateGoodsMapper.addOperateGoods(merchantOperateGoods);
            //减少相对应的数量
            merchantGoodsMapper.updateGoodsReduceQuantityById(merchantOperateGoods.getQuantity(), merchantOperateGoods.getGId());
            return ResultsUtils.success();
        } else {
            return ResultsUtils.goodsQuantityInsufficient();
        }
    }

    /**
     * 增加库存
     *
     * @param merchantOperateGoods 库存操作信息
     * @return 操作是否成功
     */
    @Transactional
    public Map<String, Object> merchantGoodsIncreaseService(MerchantOperateGoods merchantOperateGoods) {
        //添加记录到货物操作表
        merchantOperateGoodsMapper.addOperateGoods(merchantOperateGoods);
        //增加相对应的数量
        merchantGoodsMapper.updateGoodsIncreaseQuantityById(merchantOperateGoods.getQuantity(), merchantOperateGoods.getGId());
        return ResultsUtils.success();
    }

}
