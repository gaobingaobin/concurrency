package com.example.concurrency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.concurrency.rabbitmq.sender.FirstSender;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrencyApplicationTests {
    @Autowired
    private FirstSender firstSender;
    @Test
    public void contextLoads() {
        String uuid = UUID.randomUUID().toString();
        firstSender.send1(uuid, "你好！");
    }

}
