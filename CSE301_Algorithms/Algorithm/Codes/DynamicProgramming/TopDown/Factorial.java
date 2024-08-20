package Algorithms.DynamicProgramming.TopDown;

public class Factorial {

    public static void main(String[] args) {

        int n = 5;

        int result = getFactorialNumber(n);

        System.out.println("Factorial of " + n + " is " + result);

    }

    public static int getFactorialNumber (int n) { // Dynamic Programming Memoization (Top Down Approach) Solution

        int[] factorialNumbers = new int[n + 1]; // create array to store factorial numbers

        for (int i = 0; i <= n; i++)  // fill array with -1
            factorialNumbers[i] = -1;

        factorialNumbers[0] = 1; // base case
        factorialNumbers[1] = 1; // base case

        return getFactorialNumber(n, factorialNumbers);

    }

    public static int getFactorialNumber (int n, int[] factorialNumbers) {

        if (factorialNumbers[n] == -1) // if factorial number is not calculated yet  (memory control)
            factorialNumbers[n] = n * getFactorialNumber(n - 1, factorialNumbers);

        return factorialNumbers[n]; // return already calculated factorial number

    }

}
