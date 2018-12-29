package com.example.concurrency.single;

import com.example.concurrency.annoations.ThreadSafe;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;

/**
 * 线程安全的 双重检测锁机制 懒汉单例模式实现方式
 */
@ThreadSafe
public class SingleExample3 {
    private static SingleExample3 singleExample;
    private SingleExample3(){}
    public static SingleExample3 getInstance() {
        return InnerClass.single;
    }

    /**
     * 使用静态内部类，既能保证懒加载模式，又能保证线程安全
     */
    private static class InnerClass{
        private static final SingleExample3 single = new SingleExample3();
    }

    /**
     * 重写此方法，防止反序列化破坏单例机制，
     * @return
     */
    public Object readResole(){
        return InnerClass.single;
    }

}
