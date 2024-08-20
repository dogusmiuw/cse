package Algorithms.DynamicProgramming.BottomUp;

public class RodCutting {

    private record OptimalRodCutting(int[] costTable, int[] cutTable, int maxRevenue, int[] cuts) {

        public static void print(OptimalRodCutting optimumRodCutting) {

            System.out.println("*******    Maximum revenue of rod cutting  =  " + optimumRodCutting.maxRevenue + "    *******");

            System.out.println("*******    Cost Table is     *******");
            printArray(optimumRodCutting.costTable);

            System.out.println("*******    Optimal cuts are     *******");
            printArray(optimumRodCutting.cuts);

            System.out.println("*******    Cut table is     *******");
            printArray(optimumRodCutting.cutTable);

        }

    }

    public static void main(String[] args) {

        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int rodLength = 9;

        OptimalRodCutting optimalRodCutting = OptimalRodCutting(prices, rodLength);

        OptimalRodCutting.print(optimalRodCutting);


        System.out.println("\n        ******************************\n");

        System.out.println("Maximum cost are: "  + getMaxPrice(prices, rodLength));


    }


    public static OptimalRodCutting OptimalRodCutting(int[] prices, int rodLength) { // Dynamic Programming Tabulation (Bottom Up Approach) Solution

        int[] costTable = new int[rodLength + 1];  // stores the maximum price for each rod length
        int[] cutTable = new int[rodLength + 1];  // stores from which length the cutting operation should start for optimal cutting operation.

        for (int i = 1; i <= rodLength; i++) { // fill table

            int maxPrice = Integer.MIN_VALUE;
            int cut = 0;

            for (int j = 1; j <= i; j++) {

                if (maxPrice < prices[j - 1] + costTable[i - j]) {
                    maxPrice = prices[j - 1] + costTable[i - j];
                    cut = j;
                }

            }

            costTable[i] = maxPrice;
            cutTable[i] = cut;

        }

        int maxRevenue = costTable[rodLength];

        int[] cuts = getOptimalCuts(cutTable, rodLength);

        return new OptimalRodCutting(costTable, cutTable, maxRevenue, cuts);

    }

    public static int[] getOptimalCuts(int[] cutTable, int rodLength) {

        int[] cuts = new int[rodLength];
        int i = rodLength;
        int k = 0;

        while (i > 0) {

            cuts[k++] = cutTable[i];
            i = i - cutTable[i];

        }

        for (int j = 0; j < cuts.length; j++) { // remove 0s from array and return only the optimal cuts
            if (cuts[j] == 0) {
                int[] newCost = new int[j];
                System.arraycopy(cuts, 0, newCost, 0, j);
                cuts = newCost;
                break;
            }
        }

        return cuts;

    }


    private static int getMaxPrice(int[] prices, int rodLength) { // return only maximum price

        int[] maxPrices = new int[rodLength + 1];  // create table

        maxPrices[0] = 0; // base case

        for (int i = 1; i <= rodLength; i++) { // fill table

            int maxPrice = Integer.MIN_VALUE;

            // System.out.println("************************  I = " + i + "  **********************");
            for (int j = 0; j < i; j++) {
                // System.out.println(" ( Array[" + j + "] = " + prices[j] + " ) + ( Max[" + (i - j - 1) + "] = " + maxPrices[i - j - 1] + " )");
                maxPrice = Math.max(maxPrice, prices[j] + maxPrices[i - j - 1]);
            }

            maxPrices[i] = maxPrice;

        }

        // printArray(maxPrices);
        return maxPrices[rodLength];

    }


    //print array method
    private static void printArray(int[] array) {

        for (int element : array)
            System.out.print(element + " ");

        System.out.println();

    }


}
