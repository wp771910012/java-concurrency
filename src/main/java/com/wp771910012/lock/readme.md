# 锁 #

## 内部锁 ##

java 提供了强制原子性的内部锁机制: synchronized 块. 
包含两部分:
```
synchronized(锁对象的引用){
    锁保护的代码块
}
```
synchronzied 修饰方法时,锁保护的代码块则是整个方法体,锁就是该方法所在的实例本身,
static sychronzied 锁则是 class 对象.

每个 java 对象都可以隐式地扮演一个用于同步的锁的角色,这些内置的锁被称作为内部锁
(intrinsic locks) 或监视器锁  (monitor locks).

内部锁在 java 中扮演了互斥锁 (mutual exclusion lock) 的角色,意味着至多只有一个
线程可以拥有锁.

## 重进入( Reentrancy )

当一个线程请求其他线程已经占有的锁时,请求线程将被阻塞,然而它试图获得它占有的锁时,请
求会成功.重进入意味着所有的请求式基于每线程,而不是基于每调用.
[证明](ProveReentrancy.java)  