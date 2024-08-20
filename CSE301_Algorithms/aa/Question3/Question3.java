import java.util.Random;

/**
 * @author : Gülsüm Simge Bozdoğan 20170808027
 * @author : Büşra Zenbilci 20170808054
 * @since  : 19-November-2022
*/
public class Question3 {
    void sort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    void shiftArrayElementsByK(int[]arr, int k) {
        int length = arr.length;

        for(int i = 0; i < k; i++){
            int j, last;
            last = arr[length-1];

            for(j = length-1; j > 0; j--){
                arr[j] = arr[j-1];
            }

            arr[0] = last;
        }
    }

    boolean contains(final int[] arr, final int key) {
        for (int element : arr) {
            if (element == key) {
                return true;
            }
        }

        return false;
    }

    void generateRandomArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            arr[i] = -1;
            int randomNumber = (int) (Math.random() * n);
            if (contains(arr, randomNumber)) {
                i--;
                continue;
            }
            arr[i] = randomNumber;
        }
    }

    void executeSamples(int[] arr, int[] shiftAmounts) {
        for(int shiftAmount: shiftAmounts) {
            int[] tempArr = arr.clone();
            this.shiftArrayElementsByK(tempArr, shiftAmount);
            long startTime = System.nanoTime();
            this.sort(tempArr);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Time taken for " + shiftAmount + " elements: " + (double) duration / 100_000 + " ms");
        }  
    }

    public static void main(String args[])
    {
        Question3 ob = new Question3();
        int N = (int) Math.pow(2, 16);
        int[] arr = new int[N];
        ob.generateRandomArray(arr, N);
  
        int shiftAmounts[] = { 1, N / 16, 2 * N / 16, 3 * N / 16, 4 * N / 16, 5 * N / 16, 6 * N / 16, 7 * N / 16, 8 * N / 16, 9 * N / 16, 10 * N / 16, 11 * N / 16, 12 * N / 16, 13 * N / 16, 14 * N / 16, 15 * N / 16, N - 1 };
        ob.executeSamples(arr, shiftAmounts);
    }
}