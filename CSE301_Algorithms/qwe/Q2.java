/**
 * I did it alone.
 *
 * @author Medetkan Kutlu
 * @since 20.11.2022
 */


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q2 {

    public static void main(String[] args) {

        // Creates list of 1 to n
        List<Integer> list = IntStream.range(1, 20000 + 1)
                .boxed()
                .collect(Collectors.toList());

        //Shuffles List
        Collections.shuffle(list);

        //Converts list to array
        Integer[] arr = new Integer[list.size()];
        arr = list.toArray(arr);

        //System.out.println("Random list: "+list);

        long startTime = System.nanoTime();
        QuickSort(arr, 0, arr.length - 1);
        long stopTime = System.nanoTime();


        long elapsedTime = stopTime - startTime;
        double nanoToMillis = elapsedTime / (double) 1e6;

        System.out.println("10K: " + nanoToMillis);

    }


    public static void QuickSort(Integer[] arr, int low, int high) {

        if (low < high) {

            int pi = Partition(arr, low, high);
            QuickSort(arr, low, pi - 1);
            QuickSort(arr, pi + 1, high);
        }
    }


    public static int Partition(Integer[] arr, int low, int high) {

        int pivot = arr[low];
        int i = low;
        int j = high;


        while (j > i) {
            while (j > i || arr[j] > pivot) {
                if (arr[j] <= pivot) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    i++;
                    break;
                }
                j--;
            }

            while (j > i || arr[i] <= pivot) {
                if (arr[i] > pivot) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    j--;
                    break;
                }
                i++;
                if (i >= arr.length) {
                    break;
                }
            }
        }


        return j;
    }
}

