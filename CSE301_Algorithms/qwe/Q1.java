/**
 * I did it alone.
 *
 * @author Medetkan Kutlu
 * @since 20.11.2021
 */



import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q1 {

    public static void main(String[] args) {


        //Creates array size of 1 to n
        List<Integer> list = IntStream.range(1, 100000 + 1)
                .boxed()
                .collect(Collectors.toList());


        //System.out.println("List without remove: " + list);

        //Selects random one element
        Random rand = new Random();
        int randomIndex = rand.nextInt(list.size());
        //System.out.println("Picked Element: " + list.get(randomIndex));

        //System.out.println("List Size Before Remove: " + list.size());

        //Removes from the list picked element
        list.remove(randomIndex);

        //System.out.println("List Size After Remove: " + list.size());

        //Converts the list to array
        Integer[] arr = new Integer[list.size()];
        arr = list.toArray(arr);


        //System.out.println("List After Remove: " + list);


        long startTime = System.nanoTime();
        AbsentPatient(arr, 0, arr.length - 1);
        long stopTime = System.nanoTime();


        //System.out.println("Removed Element: " + AbsentPatient(arr, 0, arr.length - 1));
        System.out.println("Time(ms): " + (stopTime - startTime) / (double) 1000000);


    }


    public static int AbsentPatient(Integer[] arr2, int low, int high) {


        if (low > high) {
            return low + 1;
        } else {
            int pivot = (int) Math.ceil((((low + high + 2) / 2)));
            int pi = Partition(arr2, low, high, pivot);

            if (pi + 1 < pivot)
                return AbsentPatient(arr2, low, pi);
            else
                return AbsentPatient(arr2, pi + 1, high);
        }
    }

    public static int Partition(Integer[] arr, int low, int high, int pivot) {
        int i = low - 1;

        for (int j = low; j <= high; j++) {

            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return i;
    }


}
