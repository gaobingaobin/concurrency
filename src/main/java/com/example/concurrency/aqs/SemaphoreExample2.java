package com.example.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample2 {
    private static int clientTotal = 20;
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <clientTotal ; i++) {
            final int threadNum = i;
            executorService.execute(()-> {
                try {
//                    if(semaphore.tryAcquire()){ //尝试获取一个许可
//                        if(semaphore.tryAcquire(5000,TimeUnit.MILLISECONDS)){ //设置尝试获取的时间
                    if(semaphore.tryAcquire(2,5000,TimeUnit.MILLISECONDS)){ //尝试获取一个许可
                        test(threadNum);
                        semaphore.release(2);//释放一个许可
                    }

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
