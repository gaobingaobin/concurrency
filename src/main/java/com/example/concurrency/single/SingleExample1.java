package com.example.concurrency.single;

import com.example.concurrency.annoations.ThreadSafe;

/**
 * 线程安全的同步懒汉单例模式实现方式
 */
@ThreadSafe
public class SingleExample1 {
    //一个静态实例
    private static SingleExample1 singleExample;
    private SingleExample1(){}

    /**
     * @des 同步静态方法，防止并发导致的出现两个或者多个实例
     * @return
     */
    public static synchronized SingleExample1 getInstance(){
        if(singleExample==null){
            singleExample = new SingleExample1();
        }
        return singleExample;
    }
}
