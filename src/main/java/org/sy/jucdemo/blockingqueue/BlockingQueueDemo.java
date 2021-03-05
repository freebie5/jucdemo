package org.sy.jucdemo.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author john
 * @Date 2021/2/28 19:11
 * @Version 1.0
 */
public class BlockingQueueDemo {

    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    private static final int productTime = 1000*1;
    private static final int consumerTime = 1000*2;

    private static final int count = 1000;

    public static void main(String[] args) {

        Thread product = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<20;i++) {
                    try {
                        Thread.sleep(productTime);
                        queue.put(i);
                        System.out.println("product->"+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"product");

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {
                    try {
                        Thread.sleep(consumerTime);
                        int i = queue.take();
                        System.out.println("consumer->"+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"consumer");

        product.start();
        consumer.start();
    }

}
