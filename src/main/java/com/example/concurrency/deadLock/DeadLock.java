package com.example.concurrency.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author gaobin
 * @Description  一个简单死锁的实例
 *
 *
 **/


@Slf4j
public class DeadLock implements Runnable {
    private static Object o1 = new Object(),o2= new Object();
    public int flag = 1;

    @Override
    public void run() {
        log.info("flag is {}",flag);
      if(flag==0){
          synchronized (o1){
              try {
                  log.info("o1");
                  Thread.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              synchronized (o2){
                  log.info("flag 0");
              }
          }

      }
        if(flag==1){
            synchronized (o2){
                try {
                    log.info("o2");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    log.info("flag 1");
                }
            }

        }

    }

    public static void main(String[] args) {
        DeadLock lock1 = new DeadLock();
        DeadLock lock2 = new DeadLock();
        lock1.flag = 0;
        lock2.flag = 1;
        // lock1 lock2都是可执行状态，但是jvm线程调度先执行那个线程是不确定的
        new Thread(lock1).start();
        new Thread(lock2).start();
//        lock1.run();
//        lock2.run();
    }
}
