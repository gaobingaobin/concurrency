package com.example.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
@Slf4j
public class ForkJoinExample extends RecursiveTask<Integer> {
    private static final int threshold = 2;
    private int start;
    private int end;


    public ForkJoinExample(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end-start)<=threshold;
        if(canCompute){
            for (int i = start; i <=end; i++) {
               sum += i;
            }
        }else {
            //如果任务大于阈值，就分割成两个子任务计算
            int middle = (end+start)/2;
            ForkJoinExample leftTask = new ForkJoinExample(start,middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle+1,end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult+rightResult;

        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinExample forkJoinExample = new ForkJoinExample(1,100);
        Future<Integer> result = forkJoinPool.submit(forkJoinExample);
        try {
            log.info("result:{}", result.get());
        }catch (Exception e){
            log.info("exception :{}",e);
        }
    }
}
