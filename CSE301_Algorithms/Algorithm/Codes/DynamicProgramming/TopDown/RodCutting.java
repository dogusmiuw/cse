package Algorithms.DynamicProgramming.TopDown;

public class RodCutting {

    public static void main(String[] args) {

        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int rodLength = 5;
        int maxPrice = maxPriceTopDown(prices, rodLength);
        System.out.println("Max price for rod length " + rodLength + " is " + maxPrice);

    }

    public static int maxPriceTopDown (int[] prices, int rodLength) { // Dynamic Programming Memoization (Top Down Approach) Solution

        int[] maxPrices = new int[rodLength + 1]; // create array to store max prices

        for (int i = 0; i <= rodLength; i++) // create table
            maxPrices[i] = Integer.MIN_VALUE;


        return maxPriceTopDown(prices, rodLength, maxPrices);

    }

    private static int maxPriceTopDown(int[] prices, int rodLength, int[] maxPrices) {

        if (maxPrices[rodLength] >= 0)    // maxPrices[rodLength] is already calculated (memory control)
            return maxPrices[rodLength];

        int maxPrice;

        if (rodLength == 0) // base case
            maxPrice = 0;
        else { // recursive case (not calculated yet)

            maxPrice = Integer.MIN_VALUE;

            for (int i = 0; i < rodLength; i++)
                maxPrice = Math.max(maxPrice, prices[i] + maxPriceTopDown(prices, rodLength - i - 1, maxPrices));

        }

        maxPrices[rodLength] = maxPrice;

        return maxPrice;

    }

}
