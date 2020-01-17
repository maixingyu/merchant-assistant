package com.team.merchantassistant.service.client;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.Goods;
import com.team.merchantassistant.bean.OperateGoods;
import com.team.merchantassistant.mapper.GoodsMapper;
import com.team.merchantassistant.mapper.OperateGoodsMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientGoodsService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/16 18:33
 **/
@Service
public class ClientGoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private OperateGoodsMapper operateGoodsMapper;

    /**
     * 获得货物信息
     *
     * @param authorization token
     * @return 商品信息列表
     */
    public Map<String, Object> clientGoodsIndexService(String authorization) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<Goods> goodsList = goodsMapper.findGoods(openid);
        return ResultsUtils.successWhitData("goodsList", goodsList);
    }

    /**
     * 添加货物信息
     *
     * @param authorization token
     * @param goods         货物信息
     * @return 是否成功的标识
     */
    public Map<String, Object> clientGoodsAddService(String authorization, Goods goods) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        goods.setOpenid(openid);
        //查询该货物名是否存在
        if (goodsMapper.findIdByOpenidAndName(goods) == null) {
            goodsMapper.addGoods(goods);
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
    public Map<String, Object> clientGoodsOperateService(Integer id) {
        Goods goods = goodsMapper.findGoodsById(id);
        return ResultsUtils.successWhitData("goods", goods);
    }

    /**
     * 消耗库存
     *
     * @param operateGoods 库存操作信息
     * @return 操作是否成功
     */
    @Transactional
    public Map<String, Object> clientGoodsReduceService(OperateGoods operateGoods) {
        //获得库存数量
        Integer totalQuantity = goodsMapper.findGoodsQuantityById(operateGoods.getGId());
        //查询库存是否足够
        if (totalQuantity >= operateGoods.getQuantity()) {
            //添加记录到货物操作表
            operateGoodsMapper.addOperateGoods(operateGoods);
            //减少相对应的数量
            goodsMapper.updateGoodsReduceQuantityById(operateGoods.getQuantity(), operateGoods.getGId());
            return ResultsUtils.success();
        } else {
            return ResultsUtils.goodsQuantityInsufficient();
        }
    }

    /**
     * 增加库存
     *
     * @param operateGoods 库存操作信息
     * @return 操作是否成功
     */
    @Transactional
    public Map<String, Object> clientGoodsIncreaseService(OperateGoods operateGoods) {
        //添加记录到货物操作表
        operateGoodsMapper.addOperateGoods(operateGoods);
        //增加相对应的数量
        goodsMapper.updateGoodsIncreaseQuantityById(operateGoods.getQuantity(), operateGoods.getGId());
        return ResultsUtils.success();
    }

}
