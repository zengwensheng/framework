package com.zws.concurrent.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 *
 * condition
 *     synchronized锁配合的线程等待（Object.wait）与线程通知(Object.notify),
 *     那么对于JDK1.5 的 java.util.concurrent.locks.ReentrantLock 锁，
 *     JDK也为我们提供了与此功能相应的类java.util.concurrent.locks.Condition。
 *     Condition与重入锁是通过lock.newCondition()方法产生一个与当前重入锁绑定的Condtion实例，我们通知该实例来控制线程的等待与通知。
 *
 *      //使当前线程加入 await() 等待队列中，并释放当锁，当其他线程调用signal()会重新请求锁。与Object.wait()类似。
 *     void await() throws InterruptedException;
 *
 *     //调用该方法的前提是，当前线程已经成功获得与该条件对象绑定的重入锁，否则调用该方法时会抛出IllegalMonitorStateException。
 *     //调用该方法后，结束等待的唯一方法是其它线程调用该条件对象的signal()或signalALL()方法。等待过程中如果当前线程被中断，该方法仍然会继续等待，同时保留该线程的中断状态。
 *     void awaitUninterruptibly();
 *
 *     // 调用该方法的前提是，当前线程已经成功获得与该条件对象绑定的重入锁，否则调用该方法时会抛出IllegalMonitorStateException。
 *     //nanosTimeout指定该方法等待信号的的最大时间（单位为纳秒）。若指定时间内收到signal()或signalALL()则返回nanosTimeout减去已经等待的时间；
 *     //若指定时间内有其它线程中断该线程，则抛出InterruptedException并清除当前线程的打断状态；若指定时间内未收到通知，则返回0或负数。
 *     long awaitNanos(long nanosTimeout) throws InterruptedException;
 *
 *     //与await()基本一致，唯一不同点在于，指定时间之内没有收到signal()或signalALL()信号或者线程中断时该方法会返回false;其它情况返回true。
 *     boolean await(long time, TimeUnit unit) throws InterruptedException;
 *
 *    //适用条件与行为与awaitNanos(long nanosTimeout)完全一样，唯一不同点在于它不是等待指定时间，而是等待由参数指定的某一时刻。
 *     boolean awaitUntil(Date deadline) throws InterruptedException;
 *
 *     //唤醒一个在 await()等待队列中的线程。与Object.notify()相似
 *     void signal();
 *
 *    //唤醒 await()等待队列中所有的线程。与object.notifyAll()相似
 *     void signalAll();
 *
 *
 */
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal"); // 1
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal ~ "); // 3
            reentrantLock.unlock();
        }).start();
    }
}
