package com.example.concurrency.controller;

import com.example.concurrency.model.User;
import com.example.concurrency.redis.utils.RedisUtils;
import com.example.concurrency.result.CodeMsg;
import com.example.concurrency.result.Result;
import com.example.concurrency.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@Controller
public class TestController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(String k) {
        return redisUtils.get(k);
    }

    @RequestMapping("/set")
    @ResponseBody
    public boolean set(String k,String v) {
        return redisUtils.set(k,v);
    }

    @GetMapping("/success")
    @ResponseBody
    public Result<String> getSuccess(){
        return Result.success("hello");
    }

    @GetMapping("/error")
    @ResponseBody
    public Result<String> getError(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @GetMapping("/getUser")
    @ResponseBody
    public Result<User> getUserById(){
        User user = userService.getById(1);
        return Result.success(user);
    }
    @GetMapping("/setUser")
    @ResponseBody
    public Result<Boolean> setUser(){
        Boolean flag = userService.tx();
        return Result.success(flag);
    }

}
