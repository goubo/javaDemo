package com.bobo.javaDemo.look;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock test
 *
 * @author BO
 * @date 2021-02-19 15:15
 * @since 2021/2/19
 **/
public class ReentrantLockTest {
    public static void main(String[] args) {
        int size = 60;
        //非公平锁
        ReentrantLock reentrantLock = new ReentrantLock();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size);
        MyThread myThread = new MyThread(cyclicBarrier, reentrantLock);

        for (int i = 0; i < size; i++) {
            ThreadUtil.execute(new Thread(myThread, "t" + i));
        }
    }
}

class MyThread implements Runnable {
    ReentrantLock lock;
    CyclicBarrier cyclicBarrier;

    public MyThread(CyclicBarrier cyclicBarrier, ReentrantLock lock) {
        this.cyclicBarrier = cyclicBarrier;
        this.lock = lock;

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + ":准备抢锁锁");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        lock.lock();
        try {
            System.out.print(Thread.currentThread().getName() + ":抢到了锁");
            Thread.sleep(1000);
            System.out.print(Thread.currentThread().getName() + ":准备解锁");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + ":已经解锁");
        }
    }
}
