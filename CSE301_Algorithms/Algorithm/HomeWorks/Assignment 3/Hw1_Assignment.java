import java.util.Arrays;
import java.util.Random;

public class Hw1_Assignment {
	
	public static int[] sort(int[] a, int k) {
		MinHeap minHeap=new MinHeap(k+1);
		int[] result = new int[a.length];
		int counter=0;
		
		for (int i = 0; i <= k && i<a.length; i++) {
			minHeap.add(a[i]);
		}
		
		for (int j = k+1; j < a.length; j++) {
			result[counter]=minHeap.poll();
			minHeap.add(a[j]);
			counter++;
		}
		
		while (!minHeap.isEmpty()) {
			result[counter] = minHeap.poll();
			counter++;
		}
		
		return result;
		
	}
	
	private static boolean contains(int[] a, int b) {
		for (int i = 0; i < a.length; i++) {
			if(a[i]==b)
				return true;
		}
		return false;
	}
	
	public static int[] fillArray (int length, long seed) {
		int[] a= new int[length];
		Random rnd =new Random(seed);
		
		for (int i = 0; i < a.length; i++) {
			int random=rnd.nextInt(length+1);
			if(!contains(a,random)) {
				a[i]=random;
			}else
				i--;
		}
		return a;
	}
	
	public static boolean findk(int[] arr,int k) {
		int temp[] = Arrays.copyOf(arr, arr.length);
          
           
        Arrays.sort(temp);
          
        
        for (int i = 0; i<arr.length; i++)
        {
            int j = Arrays.binarySearch(temp,arr[i]);
              
            if (Math.abs(i - j) > k)
                return false;
        }
          
        return true;   
	}
	
	public static void main(String[] args)
    {
		
		int a[]=fillArray(100, 12345678l);
		
		
		int k=0;
		long beginTime=System.nanoTime();
		for (int i = 1; i <= a.length; i++) {
			if(findk(a,i)) {
				k = i;
				break;
			}
		}
		System.out.println(k);
		long endTime=System.nanoTime();
        System.out.println("Running Times for finding K Value : " + ((double) (endTime - beginTime)) + " s");
        
        System.out.println("-------------------------------------");
        
        beginTime=System.nanoTime();
		int b[]=sort(a, k);
		endTime=System.nanoTime();
		//System.out.println(Arrays.toString(b));
        System.out.println("Running Times for K-Sorted : " + ((double) (endTime - beginTime)) + " s");
        System.out.println("-------------------------------------");
        
        beginTime=System.nanoTime();
        QuickSort.quickSort(a, 0, a.length - 1);
        endTime=System.nanoTime();
	    //QuickSort.printArray(a, a.length);
        System.out.println("Running Times for QuickSort : " + ((double) (endTime - beginTime)) + " s");
        
        
        
    }
}
