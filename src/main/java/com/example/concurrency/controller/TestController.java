package com.example.concurrency.controller;

import com.example.concurrency.model.User;
import com.example.concurrency.redis.utils.RedisUtils;
import com.example.concurrency.result.CodeMsg;
import com.example.concurrency.result.Result;
import com.example.concurrency.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@Controller
@Api(description = "测试")
public class TestController {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    @ResponseBody
    @ApiOperation(value = "redis get方法")
    public Object get(String k) {
        return redisUtils.get(k);
    }

    @PostMapping("/set")
    @ResponseBody
    @ApiOperation(value = "redis set方法")
    public boolean set(String k,String v) {
        return redisUtils.set(k,v);
    }

    @GetMapping("/success")
    @ResponseBody
    @ApiOperation(value = "success")
    public Result<String> getSuccess(){
        return Result.success("hello");
    }

    @GetMapping("/error")
    @ResponseBody
    @ApiOperation(value = "error")
    public Result<String> getError(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @GetMapping("/getUser")
    @ResponseBody
    @ApiOperation(value = "getUser")
    public Result<User> getUserById(){
        User user = userService.getById(1);
        return Result.success(user);
    }
    @GetMapping("/setUser")
    @ResponseBody
    @ApiOperation(value = "setUser")
    public Result<Boolean> setUser(){
        Boolean flag = userService.tx();
        return Result.success(flag);
    }

    @GetMapping("/forward")
    @ApiOperation(value = "链接转发")
    public String forward() {
        return "redirect:https://www.baidu.com";
    }

}
