package com.example.concurrency.single;

import com.example.concurrency.annoations.ThreadSafe;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;

/**
 * 线程安全的 使用枚举的懒汉单例模式实现方式
 */
@ThreadSafe
public enum SingleExample4 {
    //实例
    SINGLE_EXAMPLE_4;
    public static SingleExample4 getInstance() {
        return SINGLE_EXAMPLE_4;
    }



}
