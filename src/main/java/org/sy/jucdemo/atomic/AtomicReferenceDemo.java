package org.sy.jucdemo.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author john
 * @Date 2021/2/28 18:27
 * @Version 1.0
 */
public class AtomicReferenceDemo {

    private static AtomicReference<User> reference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        User uu = new User("john");
        reference.set(uu);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User("tom1");
                User u1 = reference.get();
                System.out.println("t1:"+u1.getName());
                boolean flag = reference.compareAndSet(uu, user);
                System.out.println("t1:"+flag);
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                User user = new User("tom2");
                User u2 = reference.get();
                System.out.println("t2:"+u2.getName());
                boolean flag = reference.compareAndSet(uu, user);
                System.out.println("t2:"+flag);
            }
        },"t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(reference.get().getName());

    }

    static class User {

        public User() {

        }

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

    }

}
