package com.team.merchantassistant.service.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.team.merchantassistant.bean.WebUser;
import com.team.merchantassistant.config.RestTemplateConfig;
import com.team.merchantassistant.mapper.ClientUserMapper;
import com.team.merchantassistant.mapper.WebUserMapper;
import com.team.merchantassistant.utils.JwtUtils;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName ClientUserService
 * @Description TODO
 * @Author mai
 * @Date 2020/1/13 11:44
 **/
@Service
public class ClientUserService {
    @Autowired
    private RestTemplateConfig restTemplate;
    @Resource
    private ClientUserMapper clientUserMapper;
    @Resource
    private WebUserMapper webUserMapper;
    @Value("${weChat.appId}")
    private String appId;
    @Value("${weChat.appSecret}")
    private String appSecret;

    /**
     * 微信小程序端登录
     *
     * @param code 小程序返回腾讯服务端返回的code
     * @return authorization
     */
    public Map<String, Object> clientLoginService(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String grant_type = "authorization_code";
        //设置请求body
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("appid", appId);
        paramMap.add("secret", appSecret);
        paramMap.add("js_code", code);
        paramMap.add("grant_type", grant_type);
        //设置请求header
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
        JSONObject jsonObject = JSON.parseObject(response.getBody());
        //获得openid
        String openid = jsonObject.getString("openid");
        //查询openid是否已经在数据库中存在，如果不存在则添加
        if (clientUserMapper.findIdByOpenid(openid) == null) {
            clientUserMapper.addClientUser(openid);
        }
        //返回authorization
        String authorization = JwtUtils.getClientAuthorization(openid, jsonObject.getString("session_key"));
        return ResultsUtils.successWhitData("authorization", authorization);
    }

    /**
     * 查询该小程序用户是否绑定
     * @param authorization token
     * @return 是否授权的标识
     */
    public Map<String, Object> clientIsBindService(String authorization) {
        //从authorization中获得openid
        String openid = JWT.decode(authorization).getClaim("openid").asString();
        //如果没有绑定过web端账号返回false，否则返回true
        if (clientUserMapper.findAIdByOpenId(openid) == null) {
            return ResultsUtils.successWhitData("bindFlag", false);
        } else {
            return ResultsUtils.successWhitData("bindFlag", true);
        }
    }

    /**
     * 小程序绑定web用户
     * @param username web端的用户名
     * @param password web端的密码
     * @param authorization token
     * @return 绑定的结果
     */
    public Map<String,Object> clientBindService(String username,String password,String authorization){
        //查询要绑定的用户名或者密码是否正确
        WebUser webUser=webUserMapper.findAdminUserByNameAndPwd(username, password);
        if (webUser==null){
            return ResultsUtils.userFailure();
        }else{
            if (webUser.getCId()!=null){
                //如果用户已经绑定过了
                return ResultsUtils.userIsBind();
            }else{
                //从authorization中获得openid
                String openid = JWT.decode(authorization).getClaim("openid").asString();
                Integer cId=clientUserMapper.findIdByOpenid(openid);
                Integer wId=webUser.getId();
                //更新web_user表中的c_id
                webUserMapper.updateCId(cId);
                //更新client_user表中的w_id
                clientUserMapper.updateWId(wId);
                return ResultsUtils.success();
            }
        }
    }
}
