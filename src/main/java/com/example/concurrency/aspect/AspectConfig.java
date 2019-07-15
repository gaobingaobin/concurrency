package com.example.concurrency.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {
    /**
     * within 匹配包/类型
     */
    @Pointcut("within(com.example.concurrency.controller.TestController)")
    public void matchType(){}

    /**
     * 匹配对象 this ，target, bean
     */
    @Pointcut("this(com.example.concurrency.controller.TestController)")
    public void matchTypeThis(){}
    /**
     * 匹配对象 target,
     */
    @Pointcut("target(com.example.concurrency.controller.TestController)")
    public void matchTypeTarget(){}
    /**
     * 匹配对象 bean, 匹配所有以Controller结尾的bean
     */
    @Pointcut("bean(*Controller)")
    public void matchTypeBean(){}

    @Before("matchType()")
    public void before(){
        System.out.println("aop 测试");
    }
}
