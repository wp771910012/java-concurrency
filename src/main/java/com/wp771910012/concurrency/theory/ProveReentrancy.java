package com.wp771910012.concurrency.theory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 证明锁的可重进入 , 如果内部锁是不可重入的 method1 永远无法获得锁
 * @Author wangpeng
 * @Date 2019-07-05 16:33
 */
public class ProveReentrancy {
    public static Object lock = new Object();
    public static ReentrantLock reentrantLock = new ReentrantLock();

    public void method1() throws InterruptedException {
        synchronized (lock) {
            System.out.println("entry method1");
            method2();
            Thread.sleep(5000L);
            System.out.println("out method1");
        }

    }

    public void method2() throws InterruptedException {
        synchronized (lock) {
            System.out.println("entry method2");
            Thread.sleep(2000L);
            System.out.println("out method2");
        }
    }


    public void method3() throws InterruptedException {
        System.out.println("entry method3");
        reentrantLock.lock();
        method4();
        Thread.sleep(5000L);
        reentrantLock.unlock();
        System.out.println("out method3");
    }

    public void method4() throws InterruptedException {
        System.out.println("entry method4");
        reentrantLock.lock();
        Thread.sleep(5000L);
        reentrantLock.unlock();
        System.out.println("out method4");
    }



    public static void main(String[] args) throws InterruptedException {
        final ProveReentrancy proveReentrancy = new ProveReentrancy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,100L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        threadPoolExecutor.submit(()->{
            try {
                proveReentrancy.method3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
