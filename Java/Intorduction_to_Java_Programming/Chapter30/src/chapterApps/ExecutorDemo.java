package chapterApps;

import java.util.concurrent.*;

/**
 * Created by robert on 22.9.2016.
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        // Create a fixed thread pool with maximum three threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit runnable tasks to the executor
        executor.execute(new PrintChar('a', 100));
        executor.execute(new PrintChar('b', 100));
        executor.execute(new PrintNum(100));

        // Shutdown the executor
        executor.shutdown();
    }
}