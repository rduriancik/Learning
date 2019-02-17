package chapterApps;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by robert on 24.9.2016.
 */
public class AccountWithoutSync {

    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Create and launch 100 threads
        for (int i = 0; i < 100; i++) {
            executor.execute(new AddPennyTask());
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("What is balance? " + account.getBalance());
    }

    // A thread for adding a penny to the account
    private static class AddPennyTask implements Runnable {
        public void run() {
            account.deposit(1);
        }
    }

    // An inner class for account
    private static class Account {
        private int balance = 0;

        public int getBalance() {
            return this.balance;
        }

        public void deposit(int amount) {
            int newBalance = balance + amount;

            // This delay is deliberately added to magnify the data-corruption
            // problem and make it easy to see
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
            }

            balance = newBalance;
        }
    }
}
