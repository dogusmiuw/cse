import java.util.Arrays;

/**
 * @author : Gülsüm Simge Bozdoğan 20170808027
 * @author : Büşra Zenbilci 20170808054
 * @since  : 18-November-2022
*/
public class Question2 {
    public static void main(String[] args) {
        final int START_LENGTH = 10000;
        final int INCREMENT_AMOUNT = 10000;
        final int END_LENGTH = 100000;
        executeSamples(START_LENGTH, INCREMENT_AMOUNT, END_LENGTH);
    }

    public static boolean contains(final int[] arr, final int key) {
        for (int element : arr) {
            if (element == key) {
                return true;
            }
        }

        return false;
    }

    public static void generateRandomArray(int[] arr, int n) {
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

    public static void executeSamples(int startLength, int incrementAmount, int endLength) {
        for (int i = startLength; i <= endLength; i += incrementAmount) {
            int[] arr = new int[i];
            generateRandomArray(arr, i);
            long startTime = System.nanoTime();
            hungarianQuicksort(arr, 0, i - 1);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Time taken for " + i + " elements: " + (double) duration / 100_000 + " ms");
        }
    }

    public static int partition(int[] array, int first, int last) {

        int i = first;
        int j = last;

        int pivotIndex = first;

        while (i < j) {
            while (array[pivotIndex] <= array[j]) {
                if (i == j) {
                    return pivotIndex;
                }
                j--;
            }
            swap(array, pivotIndex, j);
            pivotIndex = j;

            while (array[pivotIndex] >= array[i]) {
                if (i == j) {
                    return pivotIndex;
                }
                i++;
            }
            swap(array, pivotIndex, i);
            pivotIndex = i;
        }
        return pivotIndex;
    }

    public static int[] hungarianQuicksort(int[] array, int first, int last) {

        int pivotIndex = partition(array, first, last);

        if (first + 1 <= pivotIndex) {
            hungarianQuicksort(array, first, pivotIndex - 1);
        }

        if (pivotIndex + 2 <= last) {
            hungarianQuicksort(array, pivotIndex + 1, last);
        }

        return array;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}