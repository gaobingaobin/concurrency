package com.example.concurrency.concurrent;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {

    /** 请求总数*/
    private static int clientTotal = 5000;
    /** 同时并发执行的线程数*/
    private static int ThreadTotal = 200;

    private static CopyOnWriteArraySet<Integer> list = new CopyOnWriteArraySet<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        /** 信号量*/
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",list.size());


    }
   /** 方法只执行一次*/
    private static void update(int i){
        list.add(i);
    }
}
