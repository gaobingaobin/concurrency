package com.example.concurrency.aqs;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CountDownLatchExample {

    /** 同时并发执行的线程数*/
    private static int ThreadTotal = 200;

    private static ConcurrentSkipListMap<Integer,Integer> list = new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(ThreadTotal);
        for(int i=0;i<ThreadTotal;i++){
            final int count = i;
            executorService.execute(() ->{
                try {
                    update(count);
                }catch (Exception e){
                    log.error("exception",e);
                }finally {
                    countDownLatch.countDown();

                }
            });
        }
        //设置等待时间，不需要等到减到0 直接执行后面的操作
        countDownLatch.await(1000,TimeUnit.MILLISECONDS);
        executorService.shutdown();
        log.info("count:{}",list.size());


    }
   /** 方法只执行一次*/
    private static void update(int i) throws Exception{
        Thread.sleep(100);
        list.put(i,i);
        log.info("value :{}",i);
    }
}
