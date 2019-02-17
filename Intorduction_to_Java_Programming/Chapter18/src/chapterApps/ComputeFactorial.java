package chapterApps;

import java.util.Scanner;

/**
 * Created by robert on 12.01.16.
 */
public class ComputeFactorial {
    public static void main(String[] args){
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a non negative integer: ");
        int n = input.nextInt();

        // Display factorial
        System.out.println("Factorial of " + n + " is " + factorial(n));
    }

    /**
     * Return the factorial for the specified number
     * @param n number
     * @return factorial for n
     */
    public static int factorial(int n){
        if (n == 0){
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
