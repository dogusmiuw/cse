package Algorithms.DynamicProgramming.BottomUp;

public class Knapsack {

    // 0-1 Knapsack Problem

    private record OptimalKnapsack(int[][] table, int maxProfit, int[] items) {
        public static void print(OptimalKnapsack knapsackSolution) {

            System.out.println("*******    Maximum profit of knapsack  =  " + knapsackSolution.maxProfit + "    *******");

            System.out.println("*******    Items in knapsack are     *******");
            printArray(knapsackSolution.items);

            System.out.println("*******    Knapsack Table is     *******");
            printArray(knapsackSolution.table);

        }

    }

    public static void main(String[] args) {


        int[] weights = {1, 2, 3, 4, 5 , 6,  7,  8,  9,  10};
        int[] profits =  {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int knapsackCapacity = 4;

        OptimalKnapsack knapsackSolution = getKnapsackSolution(weights, profits, knapsackCapacity);

        OptimalKnapsack.print(knapsackSolution);

        System.out.println("\n             ********************\n");

        int[] weights2 = {3, 4, 6, 5};
        int[] profits2 = {2, 3, 1, 4};
        int knapsackCapacity2 = 8;

        OptimalKnapsack knapsackSolution2 = getKnapsackSolution(weights2, profits2, knapsackCapacity2);

        OptimalKnapsack.print(knapsackSolution2);

        System.out.println("\n             ********************\n");

        int[] weights3 =  {1, 2, 3};
        int[] profits3 =  {10, 15, 40};
        int knapsackCapacity3 = 6;

        OptimalKnapsack knapsackSolution3 = getKnapsackSolution(weights3, profits3, knapsackCapacity3);

        OptimalKnapsack.print(knapsackSolution3);

        System.out.println("\n             ********************\n");

        int[] weights4 =  {2,1,3,2};
        int[] profits4 =  {12,10,20,15};
        int knapsackCapacity4 = 5;

        OptimalKnapsack knapsackSolution4 = getKnapsackSolution(weights4, profits4, knapsackCapacity4);

        OptimalKnapsack.print(knapsackSolution4);

    }

    public static OptimalKnapsack getKnapsackSolution(int[] weights, int[] values, int knapsackCapacity) { // Dynamic Programming Tabulation (Bottom Up Approach) Solution

        int[][] table = new int[weights.length + 1][knapsackCapacity + 1];  // create table

        for (int i = 0; i <= weights.length; i++) { // fill table

            for (int j = 0; j <= knapsackCapacity; j++) {

                if (i == 0 || j == 0)  // base case
                    table[i][j] = 0;
                else if (weights[i - 1] <= j) // if weight of item is less than or equal to capacity of knapsack
                    table[i][j] = Math.max(values[i - 1] + table[i - 1][j - weights[i - 1]], table[i - 1][j]);
                else // if weight of item is greater than capacity of knapsack
                    table[i][j] = table[i - 1][j];

            }

        }

        int maxProfit = table[weights.length][knapsackCapacity]; // get max profit

        int[] items = getItems(table, weights, knapsackCapacity); // get the items that make up the max profit

        return new OptimalKnapsack(table, maxProfit, items);

    }


    private static int[] getItems (int[][] table, int[] weights, int knapsackCapacity) {


        int[] items = new int[weights.length];
        int i = weights.length;
        int j = knapsackCapacity;
        int k = 0;

        while (i > 0 && j > 0) {

            if (table[i][j] != table[i - 1][j]) {
                items[k++] = i;
                j = j - weights[i - 1];
            }

            i--;

        }

        for (int l = 0; l < items.length; l++) {

            if (items[l] == 0) {
                int[] newItems = new int[l];
                System.arraycopy(items, 0, newItems, 0, l);
                items = newItems;
                break;
            }

        }

        return items;

    }


    //print array method
    private static void printArray(int[] array) {

        for (int element : array)
            System.out.print(element + " ");

        System.out.println();

    }

    //print array method
    private static void printArray(int[][] array) {

        for (int[] row : array) {

            for (int element : row)
                System.out.print(element + " ");

            System.out.println();

        }

    }


}
