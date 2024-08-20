package Algorithms.DynamicProgramming.TopDown;

public class MatrixChainMultiplication {

    public static void main(String[] args) {


        int[] matrixDimensions1 = {5, 1, 20, 5, 10};

        int minMultiplications1 = getMinMultiplicationCost(matrixDimensions1);

        System.out.println("Min multiplications: " + minMultiplications1);



    }

    public static int getMinMultiplicationCost(int[] matrixDimensions) {  // Dynamic Programming Memoization (Top Down Approach) Solution

        int[][] minMultiplications = new int[matrixDimensions.length][matrixDimensions.length]; // create table

        for (int i = 0; i < matrixDimensions.length; i++) // fill table diagonally with 0s because there are no multiplications
            minMultiplications[i][i] = 0;

        return getMinMultiplicationCost(matrixDimensions, minMultiplications, 1, matrixDimensions.length - 1);

    }

    private static int getMinMultiplicationCost(int[] matrixDimensions, int[][] minMultiplications, int i, int j) {

        if (minMultiplications[i][j] > 0) // minMultiplications[i][j] is already calculated (memory control)
            return minMultiplications[i][j];

        if (i == j) // base case
            minMultiplications[i][j] = 0;
        else { // recursive case (not calculated yet)

            minMultiplications[i][j] = Integer.MAX_VALUE;

            for (int k = i; k <= j - 1; k++) {

                int minMultiplicationsCount = getMinMultiplicationCost(matrixDimensions, minMultiplications, i, k) + getMinMultiplicationCost(matrixDimensions, minMultiplications, k + 1, j) + matrixDimensions[i - 1] * matrixDimensions[k] * matrixDimensions[j];

                if (minMultiplicationsCount < minMultiplications[i][j])
                    minMultiplications[i][j] = minMultiplicationsCount;

            }

        }

        return minMultiplications[i][j];

    }

}
