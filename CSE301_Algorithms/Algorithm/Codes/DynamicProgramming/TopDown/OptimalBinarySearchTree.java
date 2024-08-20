package Algorithms.DynamicProgramming.TopDown;

public class OptimalBinarySearchTree {

    public static void main(String[] args) {


        int[] keys = {10, 12, 20};
        int[] frequencies = {34, 8, 50};

        int result = getMinBSTCost(keys, frequencies);

        System.out.println("Optimal Binary Search Tree cost is " + result);



    }

    private static int getMinBSTCost(int[] keys, int[] frequencies) {

        int n = keys.length;

        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            cost[i][i] = frequencies[i];
        }

        return getMinBSTCost(keys, frequencies, cost, 0, n - 1);

    }

    private static int getMinBSTCost(int[] keys, int[] frequencies, int[][] cost, int i, int j) {

        if (cost[i][j] != 0)
            return cost[i][j];

        int sum = sum(frequencies, i, j);

        int min = Integer.MAX_VALUE;

        for (int r = i; r <= j; r++) {

            int c = ((r > i) ? getMinBSTCost(keys, frequencies, cost, i, r - 1) : 0) +
                    ((r < j) ? getMinBSTCost(keys, frequencies, cost, r + 1, j) : 0) +
                    sum;

            if (c < min)
                min = c;

        }

        cost[i][j] = min;

        return min;

    }

    private static int sum(int[] frequencies, int i, int j) {

        int sum = 0;

        for (int k = i; k <= j; k++)
            sum += frequencies[k];

        return sum;

    }


}
