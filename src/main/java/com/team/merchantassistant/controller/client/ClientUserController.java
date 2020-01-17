package com.team.merchantassistant.controller.client;

import com.team.merchantassistant.service.client.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName ClientUserController
 * @Description TODO
 * @Author mai
 * @Date 2020/1/13 11:45
 **/
@RestController
@RequestMapping("/client/user")
public class ClientUserController {
    @Autowired
    private ClientUserService clientUserService;

    /**
     * 微信小程序端登录
     * @param code 小程序返回腾讯服务端返回的code
     * @return authorization
     */
    @PostMapping("/login")
    public Map<String,Object> clientLoginController(@RequestParam("code") String code){
        return clientUserService.clientLoginService(code);
    }

    /**
     * 查询该小程序用户是否绑定
     * @param authorization token
     * @return 是否授权的标识
     */
    @PostMapping("/isBind")
    public Map<String,Object> clientIsBindController(@RequestHeader("authorization") String authorization){
        return clientUserService.clientIsBindService(authorization);
    }

    /**
     * 小程序绑定web用户
     * @param username web端的用户名
     * @param password web端的密码
     * @param authorization token
     * @return 绑定的结果
     */
    @PostMapping("/bind")
    public Map<String,Object> clientBindController(@RequestParam("username") String username,@RequestParam("password") String password,
                                                   @RequestHeader("authorization") String authorization){
        return clientUserService.clientBindService(username, password, authorization);
    }
}
