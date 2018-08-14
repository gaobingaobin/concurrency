package com.example.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample {

    /** 修饰代码块*/
    public void test1(int j){
        synchronized (this){
            for(int i = 0;i < 10; i++){
              log.info("test1 - {} {}", j,i);
            }
        }
    }
    /** 修饰方法*/
    public synchronized void test2(int j){
        for(int i = 0;i < 10; i++){
            log.info("test2 - {} {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample example = new SynchronizedExample();
        SynchronizedExample example1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //验证 synchronized 修饰代码块作用于当前对象， 同步执行
//        executorService.execute(()->{
//            example.test1(1);
//        });
//        executorService.execute(()->{
//            example.test1(2);
//        });
        //验证 synchronized 修饰方法块作用于当前对象， 同步执行
//        executorService.execute(()->{
//            example.test2(1);
//        });
//        executorService.execute(()->{
//            example.test2(2);
//        });
        /** 两个对象交替执行，说明同步只作用当前对象*/
        executorService.execute(()->{
            example.test1(1);
        });
        executorService.execute(()->{
            example1.test1(2);
        });

    }
}
