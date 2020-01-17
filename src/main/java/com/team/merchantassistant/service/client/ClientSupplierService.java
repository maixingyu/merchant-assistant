package com.team.merchantassistant.service.client;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.Supplier;
import com.team.merchantassistant.mapper.SupplierMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientSupplierService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/15 19:16
 **/
@Service
public class ClientSupplierService {
    @Resource
    private SupplierMapper supplierMapper;

    /**
     * 初始化供应商首页
     * @param authorization token
     * @return 供应商信息列表
     */
    public Map<String, Object> clientSupplierIndexService(String authorization) {
        //获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<Supplier> supplierList = supplierMapper.findSupplierByOpenid(openid);
        return ResultsUtils.successWhitData("supplierList", supplierList);
    }

    /**
     * 添加供应商信息
     * @param authorization token
     * @param supplier 供应商信息
     * @return 是否成功标识
     */
    public Map<String, Object> clientSupplierAddService(String authorization, Supplier supplier) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        supplier.setOpenid(openid);
        supplierMapper.addSupplier(supplier);
        return ResultsUtils.success();
    }

    /**
     * 更新供应商信息
     * @param supplier 新的供应商信息
     * @return 是否成功的标识
     */
    public Map<String, Object> clientSupplierAlterService(Supplier supplier){
        supplierMapper.alterSupplierById(supplier);
        return ResultsUtils.success();
    }
    
    /**
     * 删除供应商信息
     * @param id 供应商信息的唯一标识
     * @return 是否成功的标识
     */
    public Map<String,Object> clientSupplierDeleteService(Integer id){
        supplierMapper.deleteSupplierById(id);
        return ResultsUtils.success();
    }

    /**
     * 获取供应商的名字
     * @param authorization token
     * @return 供应商名字数组
     */
    public Map<String,Object> clientSupplierNameService(String authorization){
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<String> supplierNameList= supplierMapper.findSupplierNameByOpenid(openid);
        return ResultsUtils.successWhitData("supplierNameList",supplierNameList);
    }
}
