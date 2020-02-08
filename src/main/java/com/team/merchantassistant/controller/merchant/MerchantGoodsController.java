package com.team.merchantassistant.controller.merchant;

import com.team.merchantassistant.bean.MerchantGoods;
import com.team.merchantassistant.bean.MerchantOperateGoods;
import com.team.merchantassistant.service.merchant.MerchantGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName ClientGoodsController
 * @Description TODO
 * @Author mai
 * @Date 2020/1/16 18:32
 **/
@RestController
@RequestMapping("/merchant/goods")
public class MerchantGoodsController {
    @Autowired
    private MerchantGoodsService merchantGoodsService;

    /**
     * 获得商品信息
     * @param authorization token
     * @return 货物信息列表
     */
    @GetMapping("/index")
    public Map<String, Object> merchantGoodsIndexController(@RequestHeader("authorization") String authorization){
        return merchantGoodsService.merchantGoodsIndexService(authorization);
    }

    /**
     * 添加货物信息
     * @param authorization token
     * @param merchantGoods 货物信息
     * @return 是否成功的标识
     */
    @PostMapping("/add")
    public Map<String,Object> merchantGoodsAddController(@RequestHeader("authorization") String authorization,
                                                       @RequestBody MerchantGoods merchantGoods){
        return merchantGoodsService.merchantGoodsAddService(authorization, merchantGoods);
    }

    /**
     * 初始化货物增删信息列表
     * @param id 货物id
     * @return 货物增删信息列表
     */
    @GetMapping("/operate/{id}")
    public Map<String,Object> merchantGoodsOperateController(@PathVariable("id") Integer id){
        return merchantGoodsService.merchantGoodsOperateService(id);
    }

    /**
     * 消耗库存
     *
     * @param merchantOperateGoods 库存操作信息
     * @return 操作是否成功
     */
    @PostMapping("/reduce")
    public Map<String,Object> merchantGoodsReduceController(MerchantOperateGoods merchantOperateGoods){
        return merchantGoodsService.merchantGoodsReduceService(merchantOperateGoods);
    }



    /**
     * 增加库存
     * @param merchantOperateGoods 库存操作信息
     * @return 操作是否成功
     */
    @PostMapping("/increase")
    public Map<String,Object> merchantGoodsIncreaseController(MerchantOperateGoods merchantOperateGoods){
        return merchantGoodsService.merchantGoodsIncreaseService(merchantOperateGoods);
    }

}
