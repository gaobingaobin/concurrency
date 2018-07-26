package com.example.concurrency.example;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class CountExample5 {


    private static AtomicIntegerFieldUpdater<CountExample5> updater = AtomicIntegerFieldUpdater.newUpdater(CountExample5.class,"count");
    @Getter
    private volatile int count = 100;
    private static  CountExample5 countExample5 = new CountExample5();
    public static void main(String[] args) {
        if(updater.compareAndSet(countExample5,100,120)){
            log.info("update success 1 count:{}",countExample5.getCount());
        }
        if(updater.compareAndSet(countExample5,100,120)){
            log.info("update success 2 count:{}",countExample5.getCount());
        }else {
            log.info("update success 3 count:{}",countExample5.getCount());
        }




    }


}
