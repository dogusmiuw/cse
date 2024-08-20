import java.util.Random;
import java.util.ArrayList;

/**
 * @author : Gülsüm Simge Bozdoğan 20170808027
 * @author : Büşra Zenbilci 20170808054
 * @since  : 18-November-2022
*/
public class Question1 {

    public static void main(String[] args) {
        final int START_LENGTH = 10000;
        final int INCREMENT_AMOUNT = 10000;
        final int END_LENGTH = 100000;
        executeSamples(START_LENGTH, INCREMENT_AMOUNT, END_LENGTH);
    }
    
     public static boolean contains(final int[] arr, final int key) {
        for(int element: arr) {
            if(element == key) {
                return true;
            }
        }
        return false;
    }

    public static void generateRandomArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            arr[i] = -1;
            int randomNumber = (int) (Math.random() * n);
            if(contains(arr, randomNumber)) {
                i--;
                continue;
            }
            arr[i] = randomNumber;
        }
    }

    public static int[] removeRandomElement(int[] arr) {
        ArrayList<Integer> arrList = new ArrayList<Integer>();
       
        for (int el : arr)
        {
            arrList.add(el);
        }
        
        arrList.remove((int) (Math.random() * arr.length));
        
        int length = arrList.size();
        int[] modifiedArr = new int[length];
        
        for(int i = 0; i < length; i++) {
            modifiedArr[i] = arrList.get(i);
        }
        
        return modifiedArr;
    }
    
    static void executeSamples(int startLength, int incrementAmount, int endLength) {
        for (int i = startLength; i <= endLength; i += incrementAmount) {
            int[] arr = new int[i];
            generateRandomArray(arr, i);
            arr = removeRandomElement(arr);
            long startTime = System.nanoTime();
            findMissing(arr, 0, arr.length - 1);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Time taken for " + i + " elements: " + (double) duration / 100_000 + " ms");
        }
    }
    
    public static int partition(int[] array, int l, int r){
        Random random = new Random();
        int i = random.nextInt(l, r + 1);
        swap(array, i, r);
        int pivot = array[r];
        i = l;

        for (int j = l; j < r; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, r);
        return i;
    }

    public static int findMissing(int[] array, int l, int r) {
        if (l > r) {
            if (r >= 0) {
                return array[r] + 1;
            } else {
                return 1;
            }
        }
        int pivotIndex = partition(array, l, r);
        if (array[pivotIndex] == pivotIndex + 2) {
            return findMissing(array, l, pivotIndex - 1);
        } else {
            return findMissing(array, pivotIndex + 1, r);
        }
    }


    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(min, max + 1);
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}