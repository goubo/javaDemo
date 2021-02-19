package com.bobo.javaDemo.look;

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
        int size = 10;
        //非公平锁
        boolean fair = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size);
        MyThread myThread = new MyThread(cyclicBarrier, fair);
        for (int i = 0; i < size; i++) {
            new Thread(myThread, "t" + i).start();
        }
    }
}

class MyThread implements Runnable {
    ReentrantLock lock;
    CyclicBarrier cyclicBarrier;

    public MyThread(CyclicBarrier cyclicBarrier, boolean fair) {
        this.cyclicBarrier = cyclicBarrier;
        lock = new ReentrantLock(fair);

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":准备抢锁锁");
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
