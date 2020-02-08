package com.team.merchantassistant.service.merchant;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.MerchantStaff;
import com.team.merchantassistant.common.GlobalConstants;
import com.team.merchantassistant.mapper.MerchantStaffMapper;
import com.team.merchantassistant.utils.FileUtils;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientStaffService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/14 19:39
 **/
@Service
public class MerchantStaffService {
    @Autowired
    private MerchantStaffMapper merchantStaffMapper;
    @Value("${file.imgPath}")
    private String imgPath;

    /**
     * 获取初始化员工页面信息
     *
     * @param authorization token
     * @return 员工信息列表
     */
    public Map<String, Object> merchantStaffIndexService(String authorization) {
        //获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        //获得员工信息列表
        List<MerchantStaff> merchantStaffList = merchantStaffMapper.findStaffByOpenid(openid);
        return ResultsUtils.successWhitData("staffList", merchantStaffList);
    }

    /**
     * 添加员工信息
     *
     * @param authorization token
     * @param name          姓名
     * @param sex           性别
     * @param tel           电话
     * @param idCard        身份证
     * @param address       地址
     * @param remarks       备注
     * @param file          头像
     * @return 添加是否成功标识信息
     */
    public Map<String, Object> merchantStaffAddService(String authorization, String name, String sex, String tel, String idCard, String address,
                                                     String remarks, MultipartFile file) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        //随机生成头像的前缀名
        String prefix = FileUtils.getRandomPrefix(32);
        //保存头像并获得全名
        String avatar = FileUtils.saveFileReturnFileName(prefix, imgPath, file);
        MerchantStaff merchantStaff = new MerchantStaff(null, avatar, name, sex, tel, idCard, address, remarks, openid);
        //将所有的信息保存到库
        merchantStaffMapper.addStaff(merchantStaff);
        return ResultsUtils.success();
    }

    /**
     * 修改员工头像
     *
     * @param id   员工唯一标识
     * @param file 头像
     * @return 是否修改成功标识
     */
    public Map<String, Object> merchantStaffUpdateAvatarService(Integer id, MultipartFile file) {
        //随机生成头像的前缀名
        String prefix = FileUtils.getRandomPrefix(32);
        //保存头像并获得全名
        String avatar = FileUtils.saveFileReturnFileName(prefix, imgPath, file);
        merchantStaffMapper.updateAvatar(avatar, id);
        return ResultsUtils.success();
    }

    /**
     * 修改员工除头像以外的其他信息
     *
     * @param id    员工信息的唯一标识
     * @param flag  修改某个属性的标识
     * @param value 要修改的属性的新的内容
     * @return 是否修改成功的标识
     */
    public Map<String, Object> merchantStaffUpdateOtherService(Integer id, String flag, String value) {

        switch (flag) {
            case GlobalConstants.NAME:
                merchantStaffMapper.updateName(value, id);
                break;
            case GlobalConstants.TEL:
                merchantStaffMapper.updateTel(value, id);
                break;
            case GlobalConstants.ID_CARD:
                merchantStaffMapper.updateIdCard(value, id);
                break;
            case GlobalConstants.ADDRESS:
                merchantStaffMapper.updateAddress(value, id);
                break;
            case GlobalConstants.REMARKS:
                merchantStaffMapper.updateRemarks(value, id);
                break;
            case GlobalConstants.SEX:
                merchantStaffMapper.updateSex(value, id);
                break;
            default:
                break;
        }
        return ResultsUtils.success();
    }

    /**
     * 删除某个员工
     *
     * @param id 员工信息的唯一标识
     * @return 是否删除成功的标识
     */
    public Map<String, Object> merchantStaffDeleteService(Integer id) {
        merchantStaffMapper.deleteStaffById(id);
        return ResultsUtils.success();
    }
}
