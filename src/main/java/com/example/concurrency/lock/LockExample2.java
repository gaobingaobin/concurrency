package com.example.concurrency.lock;

import java.util.concurrent.locks.StampedLock;

public class LockExample2 {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) { // an exclusively locked method
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }
    //乐观读锁案例
    double distanceFromOrigin() { // A read-only method
        //获取一个乐观读锁案例
        long stamp = sl.tryOptimisticRead();
        //将两个字段读入本地局部变量
        double currentX = x, currentY = y;
        //检测发出一个乐观读锁的同时是否有其他写锁发生
        if (!sl.validate(stamp)) {
            //如果没有，再获取一个悲观读锁
            stamp = sl.readLock();
            try {
                //将两个变量读入本地局部变量
                currentX = x;
                currentY = y;
            } finally {
                //释放读锁
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
    //悲观读锁案例
    void moveIfAtOrigin(double newX, double newY) { // upgrade
        // Could instead start with optimistic, not read mode
        long stamp = sl.readLock();
        try {
            //循环，检查当前状态是否符合
            while (x == 0.0 && y == 0.0) {
                //将读锁转换为写锁
                long ws = sl.tryConvertToWriteLock(stamp);
                //确认转换是否成功
                if (ws != 0L) {
                    //替换票据
                    stamp = ws;
                    //改变状态
                    x = newX;
                    y = newY;
                    break;
                } else { //转换不成功
                    //显式释放读锁·
                    sl.unlockRead(stamp);
                    //直接获取写锁 后再通过循环再试
                    stamp = sl.writeLock();
                }
            }
        } finally {
            //释放读锁或者写锁
            sl.unlock(stamp);
        }
    }
}
