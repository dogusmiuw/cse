import java.util.Scanner;

public class HW3_Assignment {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the bag capacity of the thief: ");
		int n = sc.nextInt();
		sc.close();
		int[] weights={1,4,5};
		
		long start = System.nanoTime();
		System.out.println("The bag capacity of the thief is " + n
				+ " kg. The smallest number of items he can steal using this bag capacity is " + recursiveAlg(n,weights));
		long end = System.nanoTime() - start;
		System.out.println("\tRecursive Algorithm's Running Time: " + (double) end / 1000);
		
		
		
		start = System.nanoTime();
		System.out.println("The bag capacity of the thief is " + n
				+ " kg. The smallest number of items he can steal using this bag capacity is " + dynamicProgrammingAlg(n,weights));
		end = System.nanoTime() - start;
		
		System.out.println("\tDynamic Programming Algorithm's Running Time: " + (double) end / 1000);
		
	}

	public static int recursiveAlg(int n, int[] weights) {
		if (n < 1)
			return 0;
		int object = Integer.MAX_VALUE;
		for (int w : weights) {
			if (w <= n) {
				int result = recursiveAlg(n - w, weights);

				if (result != Integer.MAX_VALUE) {
					object = Math.min(object, result + 1);
				}
			}
		}
		return object;
	}
	
	public static int dynamicProgrammingAlg(int n, int[] weights)
    {
		int[] dp = new int[n+1];
		
		for (int i = 1; i <= n; i++)
        {
            dp[i] = Integer.MAX_VALUE;
            int result = Integer.MAX_VALUE;

            for (int w: weights)
            {
                if (i - w >= 0) {
                    result = dp[i - w];
                }

                if (result != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], result + 1);
                }
            }
        }

        return dp[n];
    }

}
