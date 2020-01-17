package com.team.merchantassistant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定义返回的结果集键值对
 */
@AllArgsConstructor
@Getter
public enum ResultsMenu {
    REQUEST_SUCCESS(100, "请求成功"),
    REQUEST_FAILURE(101, "请求失败"),
    REQUEST_TIME_OUT(102,"请求超时"),
    USER_FAILURE(103,"用户名或密码不正确"),
    USER_IS_BIND(104,"该用户已绑定账号"),
    GOODS_NAME_EXITS(105,"货物已存在"),
    GOODS_QUANTITY_INSUFFICIENT(106,"库存不足"),
    RUNTIME_EXCEPTION(150,"系统异常"),
    ARITHMETIC_EXCEPTION(1101,"算数异常"),
    NULL_POINTER_EXCEPTION(1102,"空指针异常"),
    AUTHORIZATION_FAILURE(1501,"授权失败"),
    AUTHORIZATION_TIMEOUT_FAILURE(1502,"授权信息已过时"),
    AUTHORIZATION_VERIFY_FAILURE(1503,"授权信息无效");
    private int code;
    private String msg;
}
