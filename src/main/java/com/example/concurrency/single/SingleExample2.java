package com.example.concurrency.single;

import com.example.concurrency.annoations.ThreadSafe;

/**
 * 线程安全的 双重检测锁机制 懒汉单例模式实现方式
 */
@ThreadSafe
public class SingleExample2 {
    //一个共享实例，状态变化会通知其他线程，禁止指令重排序
    private static volatile SingleExample2 singleExample;
    private SingleExample2(){}

    /**
     * @des 同步代码块，双重检测锁机制 防止并发导致的出现两个或者多个实例
     * @return
     */
    public static synchronized SingleExample2 getInstance(){
        if (singleExample==null){
            synchronized (SingleExample2.class){
                if(singleExample==null){
                    singleExample = new SingleExample2();
                }
            }

        }

        return singleExample;
    }
}
