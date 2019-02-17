package afterChapterApps;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by robert on 21.01.16.
 */
public class BigIntegerFactorial {
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a non negative integer: ");
        int n = input.nextInt();

        // Display factorial
        System.out.println("Factorial of " + n + " is " + factorial(n));
    }

    public static BigInteger factorial(int n) {
        return factorial(n, BigInteger.ONE);
    }

    public static BigInteger factorial(int n, BigInteger result) {
        if (n == 0) {
            return result;
        } else {
            return factorial(n - 1, result.multiply(BigInteger.valueOf(n)));
        }
    }
}
