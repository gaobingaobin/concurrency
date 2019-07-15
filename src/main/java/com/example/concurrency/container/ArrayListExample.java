package com.example.concurrency.container;

import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        final int N= 10000000;
        long startTime = System.currentTimeMillis();
        for (int i=0;i<N;i++){
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法前"+(endTime-startTime));
        list = new ArrayList<>(N);
        long startTime1 = System.currentTimeMillis();
//        list.ensureCapacity(N);
        for (int i = 0; i <N ; i++) {
             list.add(i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法后"+(endTime1-startTime1));
    }
}
