package com.example.concurrency.example;

import com.example.concurrency.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@NoThreadSafe
public class CountExample6 {

    /** 请求总数*/
    private static int clientTotal = 5000;
    /** 同时并发执行的线程数*/
    private static int ThreadTotal = 200;

    private static AtomicBoolean isHappend = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        /** 信号量*/
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",isHappend.get());


    }
   /** 方法只执行一次*/
    private static void test(){
        if(isHappend.compareAndSet(false,true)){
            log.info("execute");
        }
    }
}
