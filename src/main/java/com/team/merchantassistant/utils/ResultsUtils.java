package com.team.merchantassistant.utils;

import com.team.merchantassistant.common.ResultsMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResultUtils
 * @Description 这是一个结果集返回类,默认情况下它只有success()和successWhitData()方法,
 *              开发者可以根据自己的错误类再自定义方法,但值得注意的是返回的code和msg必须
 *              来自ResultsMenu类的枚举类型
 * @Author mai
 * @Date 2019/12/22 20:33
 **/
public class ResultsUtils {
    /**
     * 请求处理成功
     * @return 成功标识码和提示
     */
    public static Map<String,Object> success(){
        Map<String,Object> result=new HashMap<>();
        result.put("code", ResultsMenu.REQUEST_SUCCESS.getCode());
        result.put("msg",ResultsMenu.REQUEST_SUCCESS.getMsg());
        return result;
    }

    /**
     * 数据请求成功
     * @param key 键
     * @param value 值
     * @return 成功标识码和提示以及数据data
     */
    public static Map<String,Object> successWhitData(String key,Object value){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.REQUEST_SUCCESS.getCode());
        result.put("msg",ResultsMenu.REQUEST_SUCCESS.getMsg());
        Map<String,Object> data=new HashMap<>();
        result.put("data",data);
        data.put(key,value);
        return result;
    }

    public static Map<String,Object> successWhitData(String key1,Object value1,String key2,Object value2){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.REQUEST_SUCCESS.getCode());
        result.put("msg",ResultsMenu.REQUEST_SUCCESS.getMsg());
        Map<String,Object> data=new HashMap<>();
        result.put("data",data);
        data.put(key1,value1);
        data.put(key2,value2);
        return result;
    }

    /**
     * 用户名或密码错误
     * @return 标识符和提示
     */
    public static Map<String,Object> userFailure(){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.USER_FAILURE.getCode());
        result.put("msg",ResultsMenu.USER_FAILURE.getMsg());
        return result;
    }

    /**
     * 该用户已绑定
     * @return 标识符和提示
     */
    public static Map<String,Object> userIsBind(){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.USER_IS_BIND.getCode());
        result.put("msg",ResultsMenu.USER_IS_BIND.getMsg());
        return result;
    }

    /**
     * 货物已经存在
     * @return 标识符和提示
     */
    public static Map<String,Object> goodsNameExits(){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.GOODS_NAME_EXITS.getCode());
        result.put("msg",ResultsMenu.GOODS_NAME_EXITS.getMsg());
        return result;
    }

    /**
     * 货物已经存在
     * @return 标识符和提示
     */
    public static Map<String,Object> goodsQuantityInsufficient(){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.GOODS_QUANTITY_INSUFFICIENT.getCode());
        result.put("msg",ResultsMenu.GOODS_QUANTITY_INSUFFICIENT.getMsg());
        return result;
    }

    /**
     * 未知错误结果集
     * @return 标识符和提示
     */
    public static Map<String,Object> runtimeException(){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.RUNTIME_EXCEPTION.getCode());
        result.put("msg",ResultsMenu.RUNTIME_EXCEPTION.getMsg());
        return result;
    }

    /**
     * 授权失效
     * @return 标识符和提示
     */
    public static Map<String,Object> jwtDecodeException(){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.AUTHORIZATION_VERIFY_FAILURE.getCode());
        result.put("msg",ResultsMenu.AUTHORIZATION_VERIFY_FAILURE.getMsg());
        return result;
    }

    public static Map<String,Object> nullPointerException(){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.NULL_POINTER_EXCEPTION.getCode());
        result.put("msg",ResultsMenu.NULL_POINTER_EXCEPTION.getMsg());
        return result;
    }

    /**
     * 算数错误结果集
     * @return 标识符和提示
     */
    public static Map<String,Object> arithmeticException(){
        Map<String,Object> result=new HashMap<>();
        result.put("code",ResultsMenu.ARITHMETIC_EXCEPTION.getCode());
        result.put("msg",ResultsMenu.ARITHMETIC_EXCEPTION.getMsg());
        return result;
    }
}
