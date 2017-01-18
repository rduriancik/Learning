    package chapterApps;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by robert on 24.9.2016.
 */
public class AccountWIthSemaphores {

    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Create and launch 100 threads
        for(int i = 0; i < 100; ++i) {
            executor.execute(new AddPennyTask());
        }

        executor.shutdown();

        while (!executor.isTerminated()){}

        System.out.println("What is balance? " + account.getBalance());
    }

    // A thread for adding a penny to the account
    private static class AddPennyTask implements Runnable {
        public void run() {
            account.deposit(1);
        }
    }

    // An inner class for Account
    private static class Account {
        // Create a semaphore
        private static Semaphore semaphore = new Semaphore(1);
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            try {
                semaphore.acquire(); // Acquire a permit
                int newBalance = balance + amount;

                // This delay is deliberately added to magnify the
                // data-corruption problem and make it easy to see.
                Thread.sleep(5);

                balance = newBalance;
            } catch (InterruptedException ex) {
            } finally {
                semaphore.release(); // Release a permit
            }
        }
    }
}
