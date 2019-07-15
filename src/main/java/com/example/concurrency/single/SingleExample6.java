package com.example.concurrency.single;

public class SingleExample6 {
//    private static SingleExample6 instance;
    private SingleExample6(){}
    private static class InnerClass{
        private static final SingleExample6 instance = new SingleExample6();
    }
    public static SingleExample6 getInstance(){
        return InnerClass.instance;
    }
    public Object readResole(){
        return InnerClass.instance;
    }
}
