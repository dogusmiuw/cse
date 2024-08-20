package Algorithms.DynamicProgramming.TopDown;

public class LongestCommonSubsequence {

    public static void main(String[] args) {

        String string1 = "AGGTAB";
        String string2 = "GXTXAYB";

        int length = getLCSLength(string1, string2);

        System.out.println("Length of longest common subsequence is " + length);

    }


    private static int getLCSLength (String string1, String string2) { // Dynamic Programming Memoization (Top Down Approach) Solution

        int[][] lcs = new int[string1.length() + 1][string2.length() + 1];  // create table

        for (int i = 0; i <= string1.length(); i++)  // fill table
            for (int j = 0; j <= string2.length(); j++)
                lcs[i][j] = -1;

        return getLCSLength(string1, string2, string1.length(), string2.length(), lcs);

    }

    private static int getLCSLength (String string1, String string2, int m, int n, int[][] lcs) {

        if (m == 0 || n == 0) // base case
            return 0;

        if (lcs[m][n] != -1) // return already calculated length (memory control)
            return lcs[m][n];

        // not calculated length yet
        if (string1.charAt(m - 1) == string2.charAt(n - 1))
            return lcs[m][n] = 1 + getLCSLength(string1, string2, m - 1, n - 1, lcs);
        else
            return lcs[m][n] = Math.max(getLCSLength(string1, string2, m - 1, n, lcs), getLCSLength(string1, string2, m, n - 1, lcs));

    }


}
