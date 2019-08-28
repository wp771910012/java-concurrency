package com.wp771910012.concurrency.jdkalanysis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 通过 CyclicBarrier 达到 A 方法线程 与 B 方法线程必须各执行一次才能进入下次循环的目前
 * @Author wangpeng
 * @Date 2019-08-25 15:25
 */
public class CyclicBarrierTest {

    private final int loopTimes;

    public CyclicBarrierTest(int loopTimes) {
        this.loopTimes = loopTimes;
    }

    CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
        System.out.println("barrier broken");
    });

    void methodA(){
        try {
            for (int i = 0 ; i < loopTimes ; i++) {
                cyclicBarrier.await();
                System.out.println("proceed A method");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    void methodB(){
        try {
            for (int i = 0 ; i < loopTimes ; i++) {
                cyclicBarrier.await();
                System.out.println("proceed B method");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 100, TimeUnit.SECONDS  ,new ArrayBlockingQueue<>(1000));
        threadPoolExecutor.submit(()->{
            cyclicBarrierTest.methodA();
        });

        threadPoolExecutor.submit(()->{
            cyclicBarrierTest.methodB();
        });
    }

}
