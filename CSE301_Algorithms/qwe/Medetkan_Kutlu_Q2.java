/**
 * @author Medetkan Kutlu 20170808068
 * I did it myself
 */

import java.util.Random;

public class Medetkan_Kutlu_Q2 {
    public static void main(String[] args) {

        /*
        int[] WN = {3, 4, 2, 6, 1, 9, 8, 8, 5};
        int[][] WE = {{0, 1, 5, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 6, 2, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 9, 3, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 6, 4},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};

         */


        Object[] temp = generate_complete_binary_tree(20);
        int[] WN = (int[]) temp[0];
        int[][] WE = (int[][]) temp[1];


        int minWeightGreedy = greedy(WN, WE, 0);
        System.out.println("Minimum total weight (greedy): " + minWeightGreedy);
        System.out.println("Greedy does not solve optimally.");

        long startTime = System.nanoTime();
        int minWeightRecursive = recursive(WN, WE, 0);
        long endTime = System.nanoTime();
        double recursiveRunningTime = (double) (endTime - startTime) / 1000000;

        System.out.println("Minimum total weight (recursive): " + minWeightRecursive);

        startTime = System.nanoTime();
        int minWeightDP = dynamic(WN, WE, 0);
        endTime = System.nanoTime();
        double dynamicRunningTime = (double) (endTime - startTime) / 1000000;

        System.out.println("Minimum total weight (dynamic): " + minWeightDP);

        System.out.println("\n ***** Running Times ***** ");
        System.out.println("Running time in ms (dynamic): " + dynamicRunningTime);
        System.out.println("Running time in ms (recursive): " + recursiveRunningTime);

    }

    public static Object[] generate_complete_binary_tree(int size) {

        int[] WN = new int[size];

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            WN[i] = rand.nextInt(20) + 1;
        }
        //return WN;

        int[][] WE = new int[size][size];


        Random rand1 = new Random();
        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < size) {
                WE[i][left] = rand1.nextInt(20) + 1;
            }
            if (right < size) {
                WE[i][right] = rand1.nextInt(20) + 1;
            }
        }

        return new Object[]{WN, WE};
    }


    public static int greedy(int[] WN, int[][] WE, int root) {

        int minTotalWeight = WN[root];

        while (true) {
            int left = 2 * root + 1;
            int right = 2 * root + 2;

            if (left >= WN.length || right >= WN.length) {
                break;
            }

            if (WE[root][left] + WN[left] < WE[root][right] + WN[right]) {
                root = left;
                minTotalWeight += WE[root][left] + WN[left];
            } else {
                root = right;
                minTotalWeight += WE[root][right] + WN[right];
            }
        }
        return minTotalWeight;
    }


    public static int recursive(int[] WN, int[][] WE, int root) {

        if (2 * root + 1 >= WN.length && 2 * root + 2 >= WN.length) {
            return WN[root];
        }

        int minTotalWeight = Integer.MAX_VALUE;

        int left = 2 * root + 1;
        if (left < WN.length) {
            minTotalWeight = Math.min(minTotalWeight, WE[root][left] + recursive(WN, WE, left));
        }

        int right = 2 * root + 2;
        if (right < WN.length) {
            minTotalWeight = Math.min(minTotalWeight, WE[root][right] + recursive(WN, WE, right));
        }

        minTotalWeight += WN[root];
        return minTotalWeight;
    }

    public static int dynamic(int[] WN, int[][] WE, int root) {

        int[] minWeights = new int[WN.length];
        for (int i = 0; i < WN.length; i++) {
            minWeights[i] = Integer.MAX_VALUE;
        }

        minWeights[root] = WN[root];

        for (int i = 0; i < WN.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < WN.length) {
                minWeights[left] = Math.min(minWeights[left], minWeights[i] + WE[i][left] + WN[left]);
            }

            if (right < WN.length) {
                minWeights[right] = Math.min(minWeights[right], minWeights[i] + WE[i][right] + WN[right]);
            }
        }

        int minWeight = Integer.MAX_VALUE;
        for (int i = Math.floorDiv(WN.length, 2); i < WN.length; i++) {
            minWeight = Math.min(minWeight, minWeights[i]);
        }

        return minWeight;
    }
}
