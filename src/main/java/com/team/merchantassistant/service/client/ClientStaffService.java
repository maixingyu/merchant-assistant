package com.team.merchantassistant.service.client;

import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.Staff;
import com.team.merchantassistant.common.GlobalConstants;
import com.team.merchantassistant.mapper.StaffMapper;
import com.team.merchantassistant.utils.FileUtils;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClientStaffService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/14 19:39
 **/
@Service
public class ClientStaffService {
    @Resource
    private StaffMapper staffMapper;
    @Value("${file.imgPath}")
    private String imgPath;

    /**
     * 获取初始化员工页面信息
     *
     * @param authorization token
     * @return 员工信息列表
     */
    public Map<String, Object> clientStaffIndexService(String authorization) {
        //获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        //获得员工信息列表
        List<Staff> staffList = staffMapper.findStaffByOpenid(openid);
        return ResultsUtils.successWhitData("staffList", staffList);
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
    public Map<String, Object> clientStaffAddService(String authorization, String name, String sex, String tel, String idCard, String address,
                                                     String remarks, MultipartFile file) {
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        //随机生成头像的前缀名
        String prefix = FileUtils.getRandomPrefix(32);
        //保存头像并获得全名
        String avatar = FileUtils.saveFileReturnFileName(prefix, imgPath, file);
        Staff staff = new Staff(null, avatar, name, sex, tel, idCard, address, remarks, openid);
        //将所有的信息保存到库
        staffMapper.addStaff(staff);
        return ResultsUtils.success();
    }

    /**
     * 修改员工头像
     *
     * @param id   员工唯一标识
     * @param file 头像
     * @return 是否修改成功标识
     */
    public Map<String, Object> clientStaffUpdateAvatarService(Integer id, MultipartFile file) {
        //随机生成头像的前缀名
        String prefix = FileUtils.getRandomPrefix(32);
        //保存头像并获得全名
        String avatar = FileUtils.saveFileReturnFileName(prefix, imgPath, file);
        staffMapper.updateAvatar(avatar, id);
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
    public Map<String, Object> clientStaffUpdateOtherService(Integer id, String flag, String value) {

        switch (flag) {
            case GlobalConstants.NAME:
                staffMapper.updateName(value, id);
                break;
            case GlobalConstants.TEL:
                staffMapper.updateTel(value, id);
                break;
            case GlobalConstants.ID_CARD:
                staffMapper.updateIdCard(value, id);
                break;
            case GlobalConstants.ADDRESS:
                staffMapper.updateAddress(value, id);
                break;
            case GlobalConstants.REMARKS:
                staffMapper.updateRemarks(value, id);
                break;
            case GlobalConstants.SEX:
                staffMapper.updateSex(value, id);
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
    public Map<String, Object> clientStaffDeleteService(Integer id) {
        staffMapper.deleteStaffById(id);
        return ResultsUtils.success();
    }
}
