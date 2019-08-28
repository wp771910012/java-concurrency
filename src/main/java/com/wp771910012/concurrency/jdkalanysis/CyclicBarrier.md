# CyclicBarrier #

## 简介 ##
CyclicBarrier可以使一定数量的线程反复地在栅栏位置处汇集。当线程到达栅栏位置时将调用await方法，这个方法将阻塞直到所有线程都到达栅栏位置。如果所有线程都到达栅栏位置，那么栅栏将打开，此时所有的线程都将被释放，而栅栏将被重置以便下次使用。

## 构造方法 ##

* CyclicBarrier(int parties), 其参数表示屏障拦截的线程数量，每个线程使用await()方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。

* CyclicBarrier(int parties, Runnable barrierAction)，用于线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景。

## 应用示例 ##
[跨线程执行控制](CyclicBarrierTest.java)