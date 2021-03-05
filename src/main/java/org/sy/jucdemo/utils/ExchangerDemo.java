package org.sy.jucdemo.utils;

import java.util.concurrent.Exchanger;

/**
 * @Author john
 * @Date 2021/2/27 21:58
 * @Version 1.0
 */
public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String from = exchanger.exchange("i am t1");//交换数据
                    System.out.println("i am t1,"+from);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String from = exchanger.exchange("i am t2");
                    System.out.println("i am t2,"+from);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        //
        t1.start();
        t2.start();

    }

}
