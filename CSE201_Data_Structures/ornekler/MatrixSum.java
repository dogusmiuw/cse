
public class MatrixSum {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        
        System.out.println(matrixSumRecursive(arr, arr.length));
        System.out.println(matrixSum(arr));
    }
    
    public static int matrixSum(int[][] arr) {
        int total = 0;
        
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                total += arr[i][j];
            }
        }
        return total;
    }
    
    /**
     * @param arr matrix
     * @param n matrix dimension length
     * @return sum
     */
    public static int matrixSumRecursive(int[][] arr, int n) {
        if(n == 1) return arr[0][0];
        
        int edges = 0;
        for(int i =0 ; i < n; i++)
            edges += arr[i][n-1] + arr[n-1][i];
        edges -= arr[n-1][n-1];
        return edges + matrixSumRecursive(arr, n-1);
    }
}
