package Algorithms.DynamicProgramming.BottomUp;

public class Fibonacci {

    public static void main(String[] args) {

        int n = 10;

        int fibonacciNumber = getFibonacciNumber(n);

        System.out.println("Fibonacci number for n = " + n + " is " + fibonacciNumber);


    }

    public static int getFibonacciNumber (int n) {  //  Dynamic Programming Tabulation (Bottom Up Approach) Solution

        int[] fib = new int[n + 1];  // create table

        fib[0] = 0; // base case
        fib[1] = 1; // base case

        for (int i = 2; i <= n; i++) // fill table
            fib[i] = fib[i - 1] + fib[i - 2];

        return fib[n]; // return result

    }

}
