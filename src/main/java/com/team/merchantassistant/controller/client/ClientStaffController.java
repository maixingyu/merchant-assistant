package com.team.merchantassistant.controller.client;

import com.team.merchantassistant.service.client.ClientStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName ClientStaffController
 * @Description TODO
 * @Author mai
 * @Date 2020/1/14 19:18
 **/
@RestController
@RequestMapping("/client/staff")
public class ClientStaffController {
    @Autowired
    private ClientStaffService clientStaffService;

    /**
     * 获取初始化员工页面信息
     *
     * @param authorization token
     * @return 员工信息列表
     */
    @GetMapping("/index")
    public Map<String, Object> clientStaffIndexController(@RequestHeader("authorization") String authorization) {
        return clientStaffService.clientStaffIndexService(authorization);
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
    @PostMapping("/add")
    public Map<String, Object> clientStaffAddController(@RequestHeader("authorization") String authorization,
                                                        @RequestParam("name") String name, @RequestParam("sex") String sex,
                                                        @RequestParam("tel") String tel, @RequestParam("idCard") String idCard,
                                                        @RequestParam("address") String address, @RequestParam("remarks") String remarks,
                                                        @RequestParam("file") MultipartFile file) {
        return clientStaffService.clientStaffAddService(authorization, name, sex, tel, idCard, address, remarks, file);
    }

    /**
     * 修改员工头像
     *
     * @param id   员工唯一标识
     * @param file 头像
     * @return 是否修改成功标识
     */
    @PostMapping("/alterAvatar")
    public Map<String, Object> clientStaffUpdateAvatarController(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile file) {
        return clientStaffService.clientStaffUpdateAvatarService(id, file);
    }

    /**
     * 修改员工除头像以外的其他信息
     *
     * @param id    员工信息的唯一标识
     * @param flag  修改某个属性的标识
     * @param value 要修改的属性的新的内容
     * @return 是否修改成功的标识
     */
    @PutMapping("/alterOther/{id}/{flag}/{value}")
    public Map<String, Object> clientStaffUpdateOtherController(@PathVariable("id") Integer id, @PathVariable("flag") String flag,
                                                                @PathVariable("value") String value) {
        return clientStaffService.clientStaffUpdateOtherService(id, flag, value);
    }

    /**
     * 删除某个员工
     *
     * @param id 员工信息的唯一标识
     * @return 是否删除成功的标识
     */
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> clientStaffDeleteController(@PathVariable("id") Integer id) {
        return clientStaffService.clientStaffDeleteService(id);
    }

}