package org.sy.jucdemo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Author john
 * @Date 2021/2/28 21:27
 * @Version 1.0
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountTask task = new CountTask(1, 100);
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> result = pool.submit(task);
        Integer sum = result.get();
        System.out.println(sum);
    }

    static class CountTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 10;

        private int start;

        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            int sum = 0;

            boolean flag = (end - start) <= THRESHOLD;
            if(flag) {
                for(int i=start;i<=end;i++) {
                    sum = sum + i;
                }
            } else {
                int mid = (start + end) / 2;
                CountTask leftTask = new CountTask(start, mid);
                CountTask rightTask = new CountTask(mid+1, end);
                leftTask.fork();
                rightTask.fork();
                sum = leftTask.join() + rightTask.join();
            }

            return sum;
        }
    }

}
