package org.sy.jucdemo.threadpool;

import java.util.concurrent.*;

/**
 * @Author john
 * @Date 2021/2/27 23:54
 * @Version 1.0
 */
public class ThreadPoolExecutorDemo {

    private static int count = 10;

    private static int corePoolSize = 5;
    private static int maximumPoolSize = 10;
    private static long keepAliveTime = 20;
    private static TimeUnit unit = TimeUnit.SECONDS;
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

    public static void main(String[] args) {

        for(int i=0;i<5;i++) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        print(index);
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        for(int i=0;i<5;i++) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        print(index);
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
    }

    private static void print(int index) {
        synchronized (pool) {
            long taskCount = pool.getTaskCount();
            long completedTaskCount = pool.getCompletedTaskCount();
            long largestPoolSize = pool.getLargestPoolSize();
            long poolSize = pool.getPoolSize();
            long activeCount = pool.getActiveCount();
            System.out.println(index+"======================================================");
            System.out.println("??????????????????????????????"+taskCount);
            System.out.println("??????????????????????????????"+completedTaskCount);
            System.out.println("???????????????????????????????????????"+largestPoolSize);
            System.out.println("?????????????????????????????????"+poolSize);
            System.out.println("?????????????????????"+activeCount);
        }

    }

}
