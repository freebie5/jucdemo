package org.sy.jucdemo.collection;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author john
 * @Date 2021/2/28 21:42
 * @Version 1.0
 */
public class ConcurrentHashMapDemo {

    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap();
//    private static HashMap<Integer, Integer> map = new HashMap<>();

    private static int count = 1000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] tArr = new Thread[1000];
        for(int i=0;i<count;i++) {
            final int index = i;
            tArr[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(index, index);
                }
            }, "t"+i);
        }
        for(int j=0;j<count;j++) {
            tArr[j].start();
        }
        for(int k=0;k<count;k++) {
            tArr[k].join();
        }
        System.out.println(map.size());
    }

}
