package chapterApps;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by robert on 30.9.2016.
 */
public class ParallelMax {

    public static void main(String[] args) {
        // Create a list
        final int N = 9000000;
        int[] list = new int[N];
        for (int i = 0; i < list.length; ++i) {
            list[i] = i;
        }

        long time = System.currentTimeMillis();
        System.out.println("\nThe maximal number is " + max(list));
        time = System.currentTimeMillis() - time;
        System.out.println("The number of processors is " + Runtime.getRuntime().availableProcessors());
        System.out.println("Time is " + time + " milliseconds.");
    }

    public static int max(int[] list) {
        RecursiveTask<Integer> mainTask = new MaxTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(mainTask);
    }

    private static class MaxTask extends RecursiveTask<Integer> {
        private final static int THRESHOLD = 1000;
        private int[] list;
        private int low;
        private int high;

        public MaxTask(int[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Integer compute() {
            if (high - low < THRESHOLD) {
                int max = list[0];
                for (int i = low; i < high; ++i) {
                    if (list[i] > max) {
                        max = list[i];
                    }
                }
                return new Integer(max);
            } else {
                int mid = (low + high) / 2;
                RecursiveTask<Integer> left = new MaxTask(list, low, mid);
                RecursiveTask<Integer> right = new MaxTask(list, mid, high);

                right.fork();
                left.fork();
                return new Integer(Math.max(left.join().intValue(), right.join().intValue()));
            }
        }
    }
}
