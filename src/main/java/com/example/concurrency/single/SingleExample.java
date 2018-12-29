package com.example.concurrency.single;

import com.example.concurrency.annoations.NoThreadSafe;

/**
 * 不是线程安全的懒汉单例模式实现方式
 */
@NoThreadSafe
public class SingleExample {
    //一个静态实例
    private static SingleExample singleExample;
    private SingleExample(){}
    public static SingleExample getInstance(){
        if(singleExample==null){
            singleExample = new SingleExample();
        }
        return singleExample;
    }
}
