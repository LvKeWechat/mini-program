package com.my.mini.program.threads;

/**
 * @author creditease
 *
 *
 * ojbect wait/notify 只能解决两个线程的等待通知机制，如果是大于两个线程如何实现等待通知机制lock condition
 */
public class TowThread {

    private Object lock=new Object();





    public void print1(boolean flag) throws InterruptedException {

        int[] arr={1,2,3,4,5,6,7,8,9,10};
        synchronized (lock){
            if(flag){
                lock.wait();
            }
            for(int i:arr){
                System.out.println(i);
                lock.notify();
                lock.wait();
            }
            lock.notify();
        }

    }


    public void print2() throws InterruptedException {
        String [] arr={"A","B","C","D","E","F","G","H","I","J"};
        synchronized (lock){
            for(String s:arr){
                System.out.println(s);
                lock.notify();
                lock.wait();
            }
            lock.notify();
        }

    }

    public static void main(String[] args){
        final TowThread towThread=new TowThread();
        Thread t1=new Thread(new Runnable() {
            public void run() {
                try{
                    towThread.print1(true);
                }catch (Exception e){

                }

            }
        });

        Thread t2=new Thread(new Runnable() {
            public void run() {
                try{
                    towThread.print2();
                }catch (Exception e){

                }

            }
        });

        t1.start();
        t2.start();

    }


}
