package com.wp771910012.lock;

/**
 * @Description 证明锁的可重进入 , 如果内部锁是不可重入的 method1 永远无法获得锁
 * @Author wangpeng
 * @Date 2019-07-05 16:33
 */
public class ProveReentrancy {
    public static Object lock = new Object();

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


    public static void main(String[] args) throws InterruptedException {
        ProveReentrancy proveReentrancy = new ProveReentrancy();
        proveReentrancy.method1();
    }

}
