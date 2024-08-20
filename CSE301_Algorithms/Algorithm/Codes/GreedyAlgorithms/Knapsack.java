package Algorithms.GreedyAlgorithms;

import java.util.Arrays;

public class Knapsack {

    // Fractional Knapsack Problem

    private record KnapsackSolution(double[] items, double maxProfit) {
        public static void print(KnapsackSolution knapsackSolution) {
            System.out.println("*******    Maximum profit of knapsack  =  " + knapsackSolution.maxProfit + "    *******");
            System.out.println("*******    Items in knapsack are     *******");
            printArray(knapsackSolution.items);
        }
    }


    public static void main(String[] args) {

        int[] weights = {10, 40, 20, 30};
        int[] profits = {60, 40, 100, 120};
        int capacity = 50;

        double maxValue = getMaxProfit(weights, profits, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);


        System.out.println("\n             ********************\n");
        KnapsackSolution knapsackSolution= getKnapsackSolution(weights, profits, capacity);

        KnapsackSolution.print(knapsackSolution);

    }

    public static int getMaxProfit (int[] weights, int[] profits, int capacity) {

        int maxProfit = 0;

        int[] avgProfits = new int[profits.length];

        for (int i = 0; i < profits.length; i++)
            avgProfits[i] = profits[i] / weights[i];

        int[] sortedAvgProfit = avgProfits.clone();

        Arrays.sort(sortedAvgProfit);

        for (int i = sortedAvgProfit.length - 1; i >= 0; i--) {

            int index = findIndex(avgProfits, sortedAvgProfit[i]);

            if (weights[index] <= capacity) {
                maxProfit += profits[index];
                capacity -= weights[index];
            } else {
                maxProfit += (capacity * sortedAvgProfit[i]);
                break;
            }

        }

        return maxProfit;

    }

    public static KnapsackSolution getKnapsackSolution (int[] weights, int[] profits, int capacity) {

        double[] items = new double[weights.length];
        int maxProfit = 0;

        int[] avgProfits = new int[profits.length];

        for (int i = 0; i < profits.length; i++)
            avgProfits[i] = profits[i] / weights[i];

        int[] sortedAvgProfit = avgProfits.clone();

        Arrays.sort(sortedAvgProfit);

        for (int i = sortedAvgProfit.length - 1; i >= 0; i--) {

            int index = findIndex(avgProfits, sortedAvgProfit[i]);

            if (weights[index] <= capacity) {
                items[index] = 1;
                maxProfit+= profits[index];
                capacity -= weights[index];
            } else {
                items[index] = (double) capacity / weights[index];
                maxProfit += (capacity * sortedAvgProfit[i]);
                break;
            }

        }

        return new KnapsackSolution(items, maxProfit);

    }

    public static int findIndex(int[] arr, int value) {

        for (int i = 0; i < arr.length; i++)
            if (arr[i] == value)
                return i;

        return -1;

    }

    public static void printArray(double[] array) {

        for (double element : array)
            System.out.print(round(element,2) + " ");

        System.out.println();

    }

    private static double round(double num, int digits) {

        double n = Double.longBitsToDouble(Double.doubleToLongBits(num) + 1);
        double p = Math.pow(10, digits);

        return Math.round(n * p) / p;

    }

}
