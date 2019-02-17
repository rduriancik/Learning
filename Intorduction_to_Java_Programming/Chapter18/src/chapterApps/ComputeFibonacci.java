package chapterApps;

import java.util.Scanner;

/**
 * Created by robert on 14.01.16.
 */
public class ComputeFibonacci {
    public static void main(String[] args){
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an index for a Fibonacci number: ");
        int index = input.nextInt();

        // Find and display the Fibonacci number
        System.out.println("The Fibonacci number at index "
                + index + " is " + fib(index));
    }

    /** The method for finding the Fibonacci number */
    public static int fib(int index){
        if(index == 0){
            return 0;
        } else if(index == 1){
            return 1;
        } else {
            return fib(index - 1) + fib(index - 2);
        }
    }
}
