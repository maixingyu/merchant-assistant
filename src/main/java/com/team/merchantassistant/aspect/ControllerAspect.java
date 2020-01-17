package com.team.merchantassistant.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author mai
 * @date 2019/10/4
 * @time 0:43
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {
    /**
     * execution():表达式的主体
     * public:表示方法的修饰符
     * 第一个“ * ”符号:表示返回值的类型任意
     * com.team.merchantassistant.controller:AOP所切的服务的包名，即，需要进行横切的业务类
     * 包名后面的“..”:表示当前包及子包
     * 第二个“*”:表示类名，*即所有类
     * .*(..):表示任何方法名，括号表示参数，两个点表示任何参数类型
     */
    @Pointcut("execution(public * com.team.merchantassistant.controller..*.*(..))")
    public void log(){
    }
    @Before("log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request=attributes.getRequest();
        log.info("==================================start======================================");
        //url
        log.info("url={}",request.getRequestURL());
        //method
        log.info("method={}",request.getMethod());
        //ip
        log.info("ip={}",request.getRemoteAddr());
        //类方法
        log.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        log.info("args={}", Arrays.asList(joinPoint.getArgs()));
    }
    @After("log()")
    public void afterLog(){
        log.info("该请求处理完毕");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void afterReturning(Object object){
        log.info("response={}",object.toString());
        log.info("===================================end=======================================");
    }
}
