package com.example.concurrency.reflectionTest;

/**
 * @Description
 * @Author gaobin
 * @Date 2022-05-09 10:21
 */
public class Excel implements OfficeAble{


    @Override
    public void start() {
        System.out.println("excel");

    }
}
