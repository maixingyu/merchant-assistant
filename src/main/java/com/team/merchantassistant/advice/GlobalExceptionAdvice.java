package com.team.merchantassistant.advice;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.team.merchantassistant.utils.ResultsUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @ClassName globalExceptionAdvice
 * @Description 异常的的类型自行探索，不要去网上百度，印象不够深刻，捕获到的异常及时抛出
 *              以便提高提开发效率，值得注意的是异常捕获原则是：子类优先原则
 * @Author mai
 * @Date 2019/12/22 21:55
 **/
@RestControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * 运行时异常
     * @param e 异常类
     * @return 异常信息
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Map<String,Object> handleRuntimeException(RuntimeException e){
        e.printStackTrace();
        return ResultsUtils.runtimeException();
    }

    //授权失效异常
    @ExceptionHandler(value = JWTDecodeException.class)
    public Map<String,Object> handleJWTDecodeException(JWTDecodeException e){
        e.printStackTrace();
        return ResultsUtils.jwtDecodeException();
    }

    //空指针异常
    @ExceptionHandler(value = NullPointerException.class)
    public Map<String,Object> handleNullPointerException(NullPointerException e){
        e.printStackTrace();
        return ResultsUtils.nullPointerException();
    }

    //算数异常
    @ExceptionHandler(value = ArithmeticException.class)
    public Map<String,Object> handleArithmeticException(ArithmeticException e){
        e.printStackTrace();
        return ResultsUtils.arithmeticException();
    }

}
