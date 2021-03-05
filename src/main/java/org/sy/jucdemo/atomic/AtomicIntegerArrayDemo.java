package org.sy.jucdemo.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author john
 * @Date 2021/2/28 17:45
 * @Version 1.0
 */
public class AtomicIntegerArrayDemo {

    private static int count = 10;

    private static AtomicIntegerArray integerArray = new AtomicIntegerArray(count);

    private static int[] all = new int[count];

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

    public static void add() {
        for (int i=0;i<count;i++) {
            integerArray.addAndGet(i, 1);
//            all[i]++;
        }
    }

    public static void print() {
        for(int i=0;i<count;i++) {
            System.out.println(integerArray.get(i));
//            System.out.println(all[i]);
        }
    }

}
