package org.sy.jucdemo.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author john
 * @Date 2021/2/27 21:38
 * @Version 1.0
 */
public class CyclicBarrierDemo {

    private static int count = 5;

    /**
     * barrier 障碍，屏障
     * new CyclicBarrier(n)，实例化CyclicBarrier的时候指定一个整形n
     * 每次调用await，n减1，调用await方法的线程柱塞
     * 当n==0的时候，所有柱塞的线程同时执行
     *
     */
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(count);

    public static void main(String[] args) {

        Thread[] tArr = new Thread[5];
        for(int i=0;i<count;i++) {
            int index = i;
            tArr[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000*5);
                        cyclicBarrier.await();
                        System.out.println("t"+index+" finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            },"t"+i);
        }
        for(int j=0;j<count;j++) {
            tArr[j].start();
        }
        System.out.println("main finish");
    }

}
