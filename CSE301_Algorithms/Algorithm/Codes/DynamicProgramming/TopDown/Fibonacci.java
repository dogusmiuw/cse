package Algorithms.DynamicProgramming.TopDown;

import java.util.Arrays;

public class Fibonacci {

    public static void main(String[] args) {

        int n = 10;

        int fibonacciNumber = fibonacciTopDown(n);

        System.out.println("Top Down Fibonacci number for n = " + n + " is " + fibonacciNumber);



    }

    public static int fibonacciTopDown(int n) {  // Dynamic Programming Memoization (Top Down Approach) Solution

        int[] fibonacciNumbers = new int[n + 1]; // Create table

        Arrays.fill(fibonacciNumbers, -1); // Fill Array with -1

        fibonacciNumbers[0] = 0; // Base case
        fibonacciNumbers[1] = 1; // Base case

        return fibonacciTopDown(n, fibonacciNumbers); // return result

    }

    public static int fibonacciTopDown(int n, int[] fibonacciNumbers) {

        if (fibonacciNumbers[n] == -1)  // if fib[n] is not calculated yet (memory control)
            fibonacciNumbers[n] = fibonacciTopDown(n - 1, fibonacciNumbers) + fibonacciTopDown(n - 2, fibonacciNumbers);

        return fibonacciNumbers[n]; // return already calculated fibonacci number

    }

}
