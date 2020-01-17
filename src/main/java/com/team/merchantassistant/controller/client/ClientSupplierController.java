package com.team.merchantassistant.controller.client;

import com.team.merchantassistant.bean.Supplier;
import com.team.merchantassistant.service.client.ClientSupplierService;
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
@RequestMapping("/client/supplier")
public class ClientSupplierController {
    @Autowired
    private ClientSupplierService clientSupplierService;

    /**
     * 初始化供应商首页
     * @param authorization token
     * @return 供应商信息列表
     */
    @GetMapping("/index")
    public Map<String,Object> clientSupplierIndexController(@RequestHeader("authorization") String authorization){
        return clientSupplierService.clientSupplierIndexService(authorization);
    }

    /**
     * 添加供应商信息
     * @param authorization token
     * @param supplier 供应商信息
     * @return 是否成功标识
     */
    @PostMapping("/add")
    public Map<String,Object> clientSupplierAddController(@RequestHeader("authorization") String authorization,
                                                          @RequestBody Supplier supplier){
        return clientSupplierService.clientSupplierAddService(authorization, supplier);
    }

    /**
     * 更新供应商信息
     * @param supplier 新的供应商信息
     * @return 是否成功的标识
     */
    @PutMapping("/alter")
    public Map<String,Object> clientSupplierAlterController(@RequestBody Supplier supplier){
        return clientSupplierService.clientSupplierAlterService(supplier);
    }

    /**
     * 删除供应商信息
     * @param id 供应商信息的唯一标识
     * @return 是否成功的标识
     */
    @DeleteMapping("/delete/{id}")
    public Map<String,Object> clientSupplierDeleteController(@PathVariable("id") Integer id){
        return clientSupplierService.clientSupplierDeleteService(id);
    }

    /**
     * 获取供应商的名字
     * @param authorization token
     * @return 供应商名字数组
     */
    @GetMapping("/getName")
    public Map<String,Object> clientSupplierNameController(@RequestHeader("authorization") String authorization){
        return clientSupplierService.clientSupplierNameService(authorization);
    }
}
