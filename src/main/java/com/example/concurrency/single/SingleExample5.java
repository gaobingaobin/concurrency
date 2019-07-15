package com.example.concurrency.single;

public class SingleExample5 {
    private volatile static SingleExample5 instance;
    private SingleExample5(){}
    public static SingleExample5 getInstance(){
        if(instance==null){
            synchronized(SingleExample5.class){
                if (instance==null){
                    instance = new SingleExample5();
                }
            }
        }
        return instance;
    }

}
