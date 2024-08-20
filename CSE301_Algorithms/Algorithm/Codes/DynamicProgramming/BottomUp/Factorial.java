package Algorithms.DynamicProgramming.BottomUp;

public class Factorial {

    public static void main(String[] args) {

        int n = 5;

        int result = getFactorialNumber(n);

        System.out.println("Factorial of " + n + " is " + result);

    }

    public static int getFactorialNumber (int n) { // Dynamic Programming Tabulation (Bottom Up Approach) Solution

        int[] factorialNumbers = new int[n + 1]; // create array to store factorial numbers

        factorialNumbers[0] = 1; // base case
        factorialNumbers[1] = 1; // base case

        for (int i = 2; i <= n; i++)
            factorialNumbers[i] = i * factorialNumbers[i - 1]; // fill array with factorial numbers

        return factorialNumbers[n]; // return result

    }



}
