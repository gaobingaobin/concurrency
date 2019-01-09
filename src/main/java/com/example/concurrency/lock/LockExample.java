package com.example.concurrency.lock;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * @Author gaobin
 * @Description  ReentrantLock  1，可指定公平锁还是非公平锁， 2 提供一个conditions类 可分组唤醒需要唤醒的线程 2 能够提供中断等待线程的机制， lock.lockInterruptibly()
 **/
@Slf4j
@ThreadSafe
public class LockExample {

    /** 请求总数*/
    private static int clientTotal = 5000;
    /** 同时并发执行的线程数*/
    private static int ThreadTotal = 200;

    private static List<Integer> list = new ArrayList<>();

    private final static Lock lock = new ReentrantLock();

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
        lock.lock();
        try {
            list.add(i);
        }finally {
            lock.unlock();

        }

    }
}
