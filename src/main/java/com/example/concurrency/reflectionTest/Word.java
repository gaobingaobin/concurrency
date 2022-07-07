package com.example.concurrency.reflectionTest;

/**
 * @Description
 * @Author gaobin
 * @Date 2022-05-09 10:09
 */
public class Word implements OfficeAble{
    @Override
    public void start() {
        System.out.println("word");
    }
}
