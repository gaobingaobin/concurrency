package com.example.concurrency.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.concurrency.rabbitmq.sender.FirstSender;


import java.util.UUID;
/**
* @Description:
* @author gaobin
* @createDate 2019/1/16 17:34
*/
@RestController
@Api(description = "消息发送")
public class SendController {
    @Autowired
    private FirstSender firstSender;

    @PostMapping("/send")
    @ApiOperation(value = "发送消息")
    public String send(String message) {
        String uuid = UUID.randomUUID().toString();
        firstSender.send1(uuid, message);
        return uuid;
    }



}
