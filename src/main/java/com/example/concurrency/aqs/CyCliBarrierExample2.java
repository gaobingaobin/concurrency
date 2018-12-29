package com.example.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CyCliBarrierExample2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i <10 ; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()-> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
        executorService.shutdown();

    }

    private static void test(int i) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready  " ,i);
        try {
            cyclicBarrier.await(2000,TimeUnit.MILLISECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("{} is continue ", i );

    }

}
