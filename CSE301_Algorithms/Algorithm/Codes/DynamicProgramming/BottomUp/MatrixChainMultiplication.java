package Algorithms.DynamicProgramming.BottomUp;

public class MatrixChainMultiplication {

    private record MinMatrixMultiplication(int[][] costTable, int[][] parenthesisTable, int minMultiplication, String optimalParenthesization) {

        public static void print(MinMatrixMultiplication minMatrixMultiplication) {

            System.out.println("*******    Minimum number of matrix multiplications  =  " + minMatrixMultiplication.minMultiplication + "    *******");

            System.out.println("*******    Cost Table is     *******");
            printArray(minMatrixMultiplication.costTable);

            System.out.println("*******    Parenthesis Table is     *******");
            printArray(minMatrixMultiplication.parenthesisTable);

            System.out.println("*******    Optimal Parenthesization is     *******");
            System.out.println(minMatrixMultiplication.optimalParenthesization);

        }

    }

    public static void main(String[] args) {

        System.out.println("\n        ******************************\n");

        int[] matrixDimensions1 = {5, 1, 20, 5, 10};

        MinMatrixMultiplication minMatrixMultiplication1 = getMinMatrixMultiplication(matrixDimensions1);

        MinMatrixMultiplication.print(minMatrixMultiplication1);

        System.out.println("\n        ******************************\n");


        int[] matrixDimensions2 = {30, 35, 15, 5, 10, 20, 25};

        MinMatrixMultiplication minMatrixMultiplication2 = getMinMatrixMultiplication(matrixDimensions2);

        MinMatrixMultiplication.print(minMatrixMultiplication2);

        System.out.println("\n        ******************************\n");


        int[] matrixDimensions3 = {30, 35, 15, 5, 10, 20, 25};

        MinMatrixMultiplication minMatrixMultiplication3 = getMinMatrixMultiplication(matrixDimensions3);

        MinMatrixMultiplication.print(minMatrixMultiplication3);


    }

    public static MinMatrixMultiplication getMinMatrixMultiplication(int[] matrixDimensions) { // Dynamic Programming Tabulation (Bottom Up Approach) Solution

        int[][] minMultiplications = new int[matrixDimensions.length][matrixDimensions.length]; // create table
        int[][] minMultiplicationsIndexes = new int[matrixDimensions.length][matrixDimensions.length];

        for (int i = 0; i < matrixDimensions.length; i++)  // fill diagonal with 0s because there are no multiplications
            minMultiplications[i][i] = 0;

        for (int chainLength = 2; chainLength < matrixDimensions.length; chainLength++) { // fill table

            for (int i = 1; i < matrixDimensions.length - chainLength + 1; i++) {

                int j = i + chainLength - 1;

                minMultiplications[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {

                    int minMultiplicationsCount = minMultiplications[i][k] + minMultiplications[k + 1][j] + matrixDimensions[i - 1] * matrixDimensions[k] * matrixDimensions[j];

                    if (minMultiplicationsCount < minMultiplications[i][j]) {
                        minMultiplications[i][j] = minMultiplicationsCount;
                        minMultiplicationsIndexes[i][j] = k;
                    }

                }

            }

        }

        int minMultiplicationsCount = minMultiplications[1][matrixDimensions.length - 1];

        String optimalParenthesization = getOptimalParenthesization(minMultiplicationsIndexes, 1, matrixDimensions.length - 1);

        return new MinMatrixMultiplication(minMultiplications, minMultiplicationsIndexes, minMultiplicationsCount, optimalParenthesization);

    }

    private static String getOptimalParenthesization(int[][] kTable, int i, int j) { // getKTable() method returns the k table which is used to print the optimal parenthesization

        if (i == j)
            return "M" + i;

        return "(" + getOptimalParenthesization(kTable, i, kTable[i][j]) + getOptimalParenthesization(kTable, kTable[i][j] + 1, j) + ")";

    }

    // print 2d array method
    private static void printArray(int[][] array) {

        for (int[] row : array) {

            for (int column : row)
                System.out.print(column + " ");

            System.out.println();

        }

    }

}
