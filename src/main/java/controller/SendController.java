package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmq.sender.FirstSender;

import java.util.UUID;

@RestController
public class SendController {
    @Autowired
    private FirstSender firstSender;

    @RequestMapping("/send")
    public String send(String message) {
        String uuid = UUID.randomUUID().toString();
        firstSender.send(uuid, message);
        return uuid;
    }
}
