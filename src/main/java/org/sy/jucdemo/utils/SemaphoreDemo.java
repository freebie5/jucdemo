package org.sy.jucdemo.utils;

import java.util.concurrent.Semaphore;

/**
 * @Author john
 * @Date 2021/2/27 21:48
 * @Version 1.0
 */
public class SemaphoreDemo {

    /**
     * 线程数
     */
    private static int count = 100;

    /**
     * 同时并发数
     */
    private static int concurrent = 5;

    private static Semaphore semaphore = new Semaphore(concurrent);

    public static void main(String[] args) {
        Thread[] tArr = new Thread[count];
        for(int i=0;i<count;i++) {
            int index = i;
            tArr[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//获取执行许可
                        Thread.sleep(1000*5);
                        System.out.println("t"+index+" finish");
                        semaphore.release();//释放执行许可
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t"+i);
        }
        for(int j=0;j<count;j++) {
            tArr[j].start();
        }
        System.out.println("main finish");
    }

}
