package com.example.concurrency.single;


import java.util.HashSet;
import java.util.Set;

public class TestSingle {


    public static void main(String[] args) throws InterruptedException {
        Set<SingleExample> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                set.add(SingleExample.getInstance());
            }).start();
        }
        Thread.sleep(10000);
        System.out.println("----开始输出测试结果");
        for (SingleExample s:set) {
            System.out.println(s);
        }
    }
}
