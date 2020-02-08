package com.team.merchantassistant.service.merchant;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantSupplier;
import com.team.merchantassistant.mapper.MerchantSupplierMapper;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientSupplierService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/15 19:16
 **/
@Service
public class MerchantSupplierService {
    @Autowired
    private MerchantSupplierMapper merchantSupplierMapper;

    /**
     * 初始化供应商首页
     * @param authorization token
     * @return 供应商信息列表
     */
    public Map<String, Object> merchantSupplierIndexService(String authorization) {
        //获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<MerchantSupplier> merchantSupplierList = merchantSupplierMapper.findSupplierByOpenid(openid);
        return ResultsUtils.successWhitData("supplierList", merchantSupplierList);
    }

    /**
     * 添加供应商信息
     * @param authorization token
     * @param merchantSupplier 供应商信息
     * @return 是否成功标识
     */
    public Map<String, Object> merchantSupplierAddService(String authorization, MerchantSupplier merchantSupplier) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        merchantSupplier.setOpenid(openid);
        merchantSupplierMapper.addSupplier(merchantSupplier);
        return ResultsUtils.success();
    }

    /**
     * 更新供应商信息
     * @param merchantSupplier 新的供应商信息
     * @return 是否成功的标识
     */
    public Map<String, Object> merchantSupplierAlterService(MerchantSupplier merchantSupplier){
        merchantSupplierMapper.alterSupplierById(merchantSupplier);
        return ResultsUtils.success();
    }
    
    /**
     * 删除供应商信息
     * @param id 供应商信息的唯一标识
     * @return 是否成功的标识
     */
    public Map<String,Object> merchantSupplierDeleteService(Integer id){
        merchantSupplierMapper.deleteSupplierById(id);
        return ResultsUtils.success();
    }

    /**
     * 获取供应商的名字
     * @param authorization token
     * @return 供应商名字数组
     */
    public Map<String,Object> merchantSupplierNameService(String authorization){
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        List<String> supplierNameList= merchantSupplierMapper.findSupplierNameByOpenid(openid);
        return ResultsUtils.successWhitData("supplierNameList",supplierNameList);
    }
}
