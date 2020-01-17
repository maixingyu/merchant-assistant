package com.team.merchantassistant.controller.client;

import com.team.merchantassistant.bean.Goods;
import com.team.merchantassistant.bean.OperateGoods;
import com.team.merchantassistant.service.client.ClientGoodsService;
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
@RequestMapping("/client/goods")
public class ClientGoodsController {
    @Autowired
    private ClientGoodsService clientGoodsService;

    /**
     * 获得商品信息
     * @param authorization token
     * @return 货物信息列表
     */
    @GetMapping("/index")
    public Map<String, Object> clientGoodsIndexController(@RequestHeader("authorization") String authorization){
        return clientGoodsService.clientGoodsIndexService(authorization);
    }

    /**
     * 添加货物信息
     * @param authorization token
     * @param goods 货物信息
     * @return 是否成功的标识
     */
    @PostMapping("/add")
    public Map<String,Object> clientGoodsAddController(@RequestHeader("authorization") String authorization,
                                                       @RequestBody Goods goods){
        return clientGoodsService.clientGoodsAddService(authorization, goods);
    }

    /**
     * 初始化货物增删信息列表
     * @param id 货物id
     * @return 货物增删信息列表
     */
    @GetMapping("/operate/{id}")
    public Map<String,Object> clientGoodsOperateController(@PathVariable("id") Integer id){
        return clientGoodsService.clientGoodsOperateService(id);
    }

    /**
     * 消耗库存
     *
     * @param operateGoods 库存操作信息
     * @return 操作是否成功
     */
    @PostMapping("/reduce")
    public Map<String,Object> clientGoodsReduceController(OperateGoods operateGoods){
        return clientGoodsService.clientGoodsReduceService(operateGoods);
    }



    /**
     * 增加库存
     * @param operateGoods 库存操作信息
     * @return 操作是否成功
     */
    @PostMapping("/increase")
    public Map<String,Object> clientGoodsIncreaseController(OperateGoods operateGoods){
        return clientGoodsService.clientGoodsIncreaseService(operateGoods);
    }

}
