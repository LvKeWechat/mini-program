package com.my.mini.program.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author creditease
 *
 * 如果是两个线程交替输出可以用 object-wait/notify 等待通知机制
 *
 * 三个线程交替输出 如何保证必须线程1 第一个运行
 *
 * 多个线程交替输出[1.......100]
 *
 * 第一种方式 使用lock接口的条件变量
 *
 *
 *
 *
 */
public class ThreeThread {
    private Lock lock= new ReentrantLock();
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();
    private volatile int i=1;


    public void print1(boolean flag){
        lock.lock();
        try{
            if(flag){
                c1.await();
            }
            for(int i=1;i<=10;i++){
                System.out.println(i);
                c2.signal();
                c1.await();
            }
            c2.signal();


        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

    public void print2(boolean flag){
        String[] sts={"A","B","C","D","E","F","G","H","I","J"};
        lock.lock();
        try{
            if(flag){
                c2.await();
            }
            for(String a:sts){
                System.out.println(a);
                c3.signal();
                c2.await();
            }
            c3.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

    public void print3(){
        String[] sts={"0A","0B","0C","0D","0E","0F","0G","0H","0I","0J"};
        lock.lock();
        try{
            for(String b:sts){
                System.out.println(b);
                c1.signal();
                c3.await();
            }
            c1.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreeThread test=new ThreeThread();

        Thread t1=new Thread(new Runnable() {
            public void run() {
                test.print1(true);
            }
        });

        Thread t2=new Thread(new Runnable() {
            public void run() {
                test.print2(true);
            }
        });
        Thread t3=new Thread(new Runnable() {
            public void run() {
                test.print3();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        //Thread.currentThread().sleep(10000000);


        //CountDownLatch countDownLatch=new CountDownLatch(4);

        //countDownLatch.countDown();

        //countDownLatch.await();

    }
}
