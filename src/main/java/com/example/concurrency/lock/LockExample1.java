package com.example.concurrency.lock;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @Author gaobin
 * @Description  ReentrantLock
 **/
@Slf4j
@ThreadSafe
public class LockExample1 {

    private static Map<String,String> map = new HashMap<>();

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public String get(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }
    public void put(String key,String value){
        writeLock.lock();
        try{
            map.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }
}
