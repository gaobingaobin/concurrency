package com.example.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample {
    private static int clientTotal = 20;
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <clientTotal ; i++) {
            final int threadNum = i;
            executorService.execute(()-> {
                try {
                    semaphore.acquire(3); //获取的量和信号量相等，几乎同一时间获取三个，此时没有其他线程可以获取·，必须等到三个都执行完释放后才可继续执行，其实现在就和单线程差不多
                    test(threadNum);
                    semaphore.release(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
        executorService.shutdown();

    }

    private static void test(int i) throws Exception{
        log.info("输入 {} " , i);
        Thread.sleep(1000);

    }

}
