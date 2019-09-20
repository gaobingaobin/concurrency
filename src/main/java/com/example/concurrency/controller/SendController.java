package com.example.concurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SendController {
    @Autowired
    private FirstSender firstSender;

    @RequestMapping("/send")
    public String send(String message) {
        String uuid = UUID.randomUUID().toString();
        firstSender.send1(uuid, message);
        return uuid;
    }

}
