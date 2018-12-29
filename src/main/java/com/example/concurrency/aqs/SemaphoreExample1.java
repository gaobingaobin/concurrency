package com.example.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample1 {
    private static int clientTotal = 20;
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <clientTotal ; i++) {
            final int threadNum = i;
            executorService.execute(()-> {
                try {
                    semaphore.acquire();
                    test(threadNum);
                    semaphore.release();
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
