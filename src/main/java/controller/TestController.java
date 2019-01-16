package controller;

import com.example.concurrency.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@Controller
public class TestController {
    @Autowired
    private RedisUtils redisUtils;

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
}
