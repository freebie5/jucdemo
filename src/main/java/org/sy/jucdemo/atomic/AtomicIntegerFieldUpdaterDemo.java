package org.sy.jucdemo.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author john
 * @Date 2021/2/28 18:39
 * @Version 1.0
 */
public class AtomicIntegerFieldUpdaterDemo {

    /**
     * 原子更新User类的age字段
     */
    private static AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    private static User user = new User(0,"john");

    private static int count = 10;

    public static void main(String[] args) throws InterruptedException {

        Thread[] tArr = new Thread[count];
        for(int i=0;i<count;i++) {
            tArr[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    add();
                }
            },"t"+i);
        }
        for(int j=0;j<count;j++) {
            tArr[j].start();
        }
        for(int k=0;k<count;k++) {
            tArr[k].join();
        }
        print();
    }

    private static void add() {
        for(int i=0;i<1000;i++) {
            updater.getAndIncrement(user);
        }
    }

    private static void print() {
        System.out.println(updater.get(user));
    }

    static class User {

        public volatile int age;
        public volatile String name;

        public User() {
        }

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
