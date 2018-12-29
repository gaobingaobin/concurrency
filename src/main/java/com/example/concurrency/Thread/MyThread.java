package com.example.concurrency.Thread;

public class MyThread extends Thread {
    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0 ;i <100 ; i++){
            System.out.println(getName()+ "  "+ i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread tt = new MyThread("gaobin");
                tt.start();
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                MyThread jt = new MyThread("被Join的线程");
                jt.start();
                jt.join();
            }
            System.out.println(Thread.currentThread().getName() + "   " + i);
        }
        /**
         * 设置为后台线程
         */
//         tt.setDaemon(true);

    }
}
