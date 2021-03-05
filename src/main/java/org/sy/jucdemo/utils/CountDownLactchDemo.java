package org.sy.jucdemo.utils;

import java.util.concurrent.CountDownLatch;

/**
 * @Author john
 * @Date 2021/2/27 19:34
 * @Version 1.0
 */
public class CountDownLactchDemo {

    private static int count = 5;
    /**
     * latch门闩
     * new CountDownLatch(int n)，实例化CountDownLatch的时候指定一个初始化整数n。
     * 每次调用countDown的时候，n减少1。
     * 调用await的线程会被柱塞，当n==0时，才会继续执行
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(count);


    public static void main(String[] args) throws InterruptedException {

        Thread[] tArr = new Thread[count];
        for(int i=0;i<count;i++) {
            final int index = i;
            tArr[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000*5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t"+index+" finish");
                    countDownLatch.countDown();
                }
            }, "t"+i);
        }
        for(int j=0;j<count-1;j++) {
            tArr[j].start();
        }

        countDownLatch.await();
        System.out.println("main finish");
    }

}
