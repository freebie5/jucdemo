package org.sy.jucdemo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author john
 * @Date 2021/2/28 17:23
 * @Version 1.0
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
        int nThreads = 5;
        ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000*5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 finish");
            }
        });
        System.out.println("main finish");
        pool.shutdown();
    }

}
