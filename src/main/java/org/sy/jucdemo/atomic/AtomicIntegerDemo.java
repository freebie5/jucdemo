package org.sy.jucdemo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author john
 * @Date 2021/2/28 17:32
 * @Version 1.0
 */
public class AtomicIntegerDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static int all = 0;

    private static int count = 10;

    private static final int type = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread[] tArr = new Thread[count];
        for(int i=0;i<count;i++) {
            tArr[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<1000;j++) {
                        add();
                    }
                }
            }, "t"+i);
        }
        for(int j=0;j<count;j++) {
            tArr[j].start();
        }
        for(int j=0;j<count;j++) {
            //等待所有线程执行完
            tArr[j].join();
        }
        print();
    }

    private static void add() {
        switch (type) {
            case 0:
                atomicInteger.addAndGet(1);
                break;
            case 1:
                all++;
                break;
            default:

                break;
        }

    }

    private static void print() {
        switch (type) {
            case 0:
                System.out.println("result:"+atomicInteger.get());
                break;
            case 1:
                System.out.println(all);
                break;
            default:

                break;
        }

    }

}
