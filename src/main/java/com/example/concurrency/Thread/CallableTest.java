package com.example.concurrency.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description
 * @Author gaobin
 * @Date 2022-05-05 20:45
 */
public class CallTest implements Callable {
    @Override
    public Object call() throws Exception {
        return "Hello World";
    }

    public static void main(String[] args) {
        FutureTask<String> task = new FutureTask<String>(new CallTest());
        new Thread(task).start();

        try {
            String result = task.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
