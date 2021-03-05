package org.sy.jucdemo.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author john
 * @Date 2021/3/1 22:10
 * @Version 1.0
 */
public class ReentrantLockDemo {

    private static ReentrantLock lock = new ReentrantLock();

    private static int count = 10;

    private static int sum = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread[] tArr = new Thread[count];
        for(int i=0;i<count;i++) {
            tArr[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<1000;j++) {
                        add2();
                    }
                }
            },"t"+i);
        }
        for(int j=0;j<count;j++) {
            tArr[j].start();
        }
        for(int k=0;k<count;k++) {
            tArr[k].join();
        }
        System.out.println("sum:"+sum);
        System.out.println("main finish");
    }

    private static void add() {
        lock.lock();
        try {
            sum++;
        } finally {
            lock.unlock();
        }
    }

    private static synchronized void add2() {
        sum++;
    }

    private static void add3() {
        synchronized (ReentrantLockDemo.class) {
            sum++;
        }
    }

}
