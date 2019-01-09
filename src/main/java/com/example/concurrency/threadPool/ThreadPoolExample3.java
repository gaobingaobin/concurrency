package com.example.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
//        service.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.info("schedule run ");
//            }
//        },1,TimeUnit.SECONDS);
//        service.shutdown();
        //循环调度
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run ");
            }
        },1,3,TimeUnit.SECONDS);
        //定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run ");
            }
        },new Date(),5000);
    }
}
