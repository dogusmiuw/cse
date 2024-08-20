package Algorithms.DynamicProgramming.BottomUp;

public class LongestCommonSubsequence {

    private record LCS(int[][] table, int length, String subsequence) {
        public static void print(LCS lcs) {

            System.out.println("*******    Length of longest common subsequence length  =  " + lcs.length + "    *******");

            System.out.println("*******    LCS Table is     *******");
            printArray(lcs.table);

            System.out.println("*******    Longest common subsequence  =  " + lcs.subsequence  +  "    *******");

        }

    }

    public static void main(String[] args) {

        String string1 = "AGGTAB";
        String string2 = "GXTXAYB";

        LCS lcs = getLCS(string1, string2);

        LCS.print(lcs);

    }

    private static LCS getLCS(String string1, String string2) { // Dynamic Programming Tabulation (Bottom Up Approach) Solution

        int[][] lcs = new int[string1.length() + 1][string2.length() + 1]; // create table

        for (int i = 0; i <= string1.length(); i++) // fill table

            for (int j = 0; j <= string2.length(); j++) {

                if (i == 0 || j == 0) // base case
                    lcs[i][j] = 0;
                else if (string1.charAt(i - 1) == string2.charAt(j - 1)) // if characters match
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                else // if characters don't match
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);

            }

        int longestCommonSubsequenceLength = lcs[string1.length()][string2.length()]; // get length of a longest common subsequence

        String subsequence = getSubsequence(lcs, string1, string2); // get subsequence

        return new LCS(lcs, longestCommonSubsequenceLength, subsequence); // return LCS object

    }

    // print LCS result
    private static String getSubsequence(int[][] lcs, String string1, String string2) {

        StringBuilder lcsString = new StringBuilder();

        int i = string1.length();
        int j = string2.length();

        while (i > 0 && j > 0) {

            if (string1.charAt(i - 1) == string2.charAt(j - 1)) {

                lcsString.insert(0, string1.charAt(i - 1));
                i--;
                j--;

            }
            else if (lcs[i - 1][j] > lcs[i][j - 1])
                i--;
            else
                j--;

        }

        return lcsString.toString();

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
