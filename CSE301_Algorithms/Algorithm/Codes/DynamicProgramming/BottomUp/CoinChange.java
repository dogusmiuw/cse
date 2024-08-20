package Algorithms.DynamicProgramming.BottomUp;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {


        int[] coins = {1, 3, 5};
        int amount = 39;

        System.out.println("Minimum number of coins required to make change for " + amount + " is: " + getMinExchanges(coins, amount));


        int[] coins2 = {1, 2, 5};
        int amount2 = 11;

        System.out.println("Minimum number of coins required to make change for " + amount2 + " is: " + getMinExchanges(coins2, amount2));

        int[] coins3 = {2};
        int amount3 = 3;

        System.out.println("Minimum number of coins required to make change for " + amount3 + " is: " + getMinExchanges(coins3, amount3));

        int[] coins4 = {1};
        int amount4 = 0;

        System.out.println("Minimum number of coins required to make change for " + amount4 + " is: " + getMinExchanges(coins4, amount4));

        int[] coins5 = {1};
        int amount5 = 1;

        System.out.println("Minimum number of coins required to make change for " + amount5 + " is: " + getMinExchanges(coins5, amount5));


        int[] coins6 = {1};
        int amount6 = 2;

        System.out.println("Minimum number of coins required to make change for " + amount6 + " is: " + getMinExchanges(coins6, amount6));


    }


    public static int getMinExchanges(int[] coins, int amount) {

        int[] minExchanges = new int[amount + 1];

        Arrays.fill(minExchanges, amount + 1);
        minExchanges[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int coin : coins)
                if (coin <= i)
                    minExchanges[i] = Math.min(minExchanges[i], minExchanges[i - coin] + 1);

        }

        return minExchanges[amount] == amount + 1 ? -1 : minExchanges[amount];

    }


    // write minimum coin exchange problem with recursion

    public static int getMinExchangesRecursion(int[] coins, int amount) {

        if (amount == 0)
            return 0;

        int min = Integer.MAX_VALUE;

        for (int coin : coins) {

            if (coin <= amount) {

                int res = getMinExchangesRecursion(coins, amount - coin);

                if (res != Integer.MAX_VALUE && res + 1 < min)
                    min = res + 1;

            }

        }

        return min;

    }


    // write minimum coin exchange problem with greedy algoirthm

    public static int getMinExchangesGreedy(int[] coins, int amount) {

        int min = 0;

        for (int i = coins.length - 1; i >= 0; i--) {

            if (coins[i] <= amount) {

                int res = amount / coins[i];
                min += res;
                amount -= res * coins[i];

            }

        }

        return amount == 0 ? min : -1;

    }


    public static void printArray(int[] array) {

        for (int element : array)
            System.out.print(element + ", ");

        System.out.println();

    }

}
