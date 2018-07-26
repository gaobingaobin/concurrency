package com.example.concurrency.example;

import com.example.concurrency.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class CountExample4 {


    private static AtomicReference<Integer> count = new AtomicReference<>(0);
    public static void main(String[] args) {

        count.compareAndSet(0,2);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);

        log.info("count:{}",count);


    }


}
