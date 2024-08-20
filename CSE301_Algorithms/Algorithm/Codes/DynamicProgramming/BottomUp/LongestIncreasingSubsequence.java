package Algorithms.DynamicProgramming.BottomUp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[] array = {10, 22, 9, 33, 21, 50, 41, 60, 80};

        System.out.println("Length of longest increasing subsequence is: " + getLongestIncreasingSubsequence(array));


    }

    public static int getLongestIncreasingSubsequence(int[] array) {

        int[] lis = new int[array.length];

        Arrays.fill(lis,1);

        for ( int i = 0 ; i < array.length ; i++ )
            for ( int j = 0 ; j < i ; j++ )
                if ( array[i] > array[j] )
                    lis[i] = Math.max(lis[i], lis[j] + 1 );


        int max = lis[ array.length - 1 ];

        for ( int element : lis)
            if (element > max)
                max = element;

        return max;

    }

}
