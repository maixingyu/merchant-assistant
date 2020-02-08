package com.team.merchantassistant.controller.merchant;

import com.team.merchantassistant.bean.MerchantSupplier;
import com.team.merchantassistant.service.merchant.MerchantSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName ClientSupplierController
 * @Description 供应商的Controller类
 * @Author mai
 * @Date 2020/1/15 19:01
 **/
@RestController
@RequestMapping("/merchant/supplier")
public class MerchantSupplierController {
    @Autowired
    private MerchantSupplierService merchantSupplierService;

    /**
     * 初始化供应商首页
     * @param authorization token
     * @return 供应商信息列表
     */
    @GetMapping("/index")
    public Map<String,Object> merchantSupplierIndexController(@RequestHeader("authorization") String authorization){
        return merchantSupplierService.merchantSupplierIndexService(authorization);
    }

    /**
     * 添加供应商信息
     * @param authorization token
     * @param merchantSupplier 供应商信息
     * @return 是否成功标识
     */
    @PostMapping("/add")
    public Map<String,Object> merchantSupplierAddController(@RequestHeader("authorization") String authorization,
                                                          @RequestBody MerchantSupplier merchantSupplier){
        return merchantSupplierService.merchantSupplierAddService(authorization, merchantSupplier);
    }

    /**
     * 更新供应商信息
     * @param merchantSupplier 新的供应商信息
     * @return 是否成功的标识
     */
    @PutMapping("/alter")
    public Map<String,Object> merchantSupplierAlterController(@RequestBody MerchantSupplier merchantSupplier){
        return merchantSupplierService.merchantSupplierAlterService(merchantSupplier);
    }

    /**
     * 删除供应商信息
     * @param id 供应商信息的唯一标识
     * @return 是否成功的标识
     */
    @DeleteMapping("/delete/{id}")
    public Map<String,Object> merchantSupplierDeleteController(@PathVariable("id") Integer id){
        return merchantSupplierService.merchantSupplierDeleteService(id);
    }

    /**
     * 获取供应商的名字
     * @param authorization token
     * @return 供应商名字数组
     */
    @GetMapping("/getName")
    public Map<String,Object> merchantSupplierNameController(@RequestHeader("authorization") String authorization){
        return merchantSupplierService.merchantSupplierNameService(authorization);
    }
}
