import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
            int[] arrBubble = generateRandomArray(50000);
            int[] arrSelection = new int[50000];
            System.arraycopy(arrBubble, 0, arrSelection, 0, 0);
            long bubblestartTime = System.currentTimeMillis();
            bubbleSort(arrBubble);
            long bubbleEndTime = System.currentTimeMillis();
            System.out.println("BubbleSort:" + (bubbleEndTime-bubblestartTime));

            long selectionstartTime = System.currentTimeMillis();
            selectionSort(arrSelection);
            long selectionEndTime = System.currentTimeMillis();
            System.out.println("SelectionSort:" + (selectionEndTime-selectionstartTime));



        }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int k = 0; k < arr.length - 1 - i; k++) {
                if (arr[k] > arr[k + 1]) {
                    int temp = arr[k];
                    arr[k] = arr[k + 1];
                    arr[k + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int k = i+1; k < arr.length; k++) {
                if(arr[minIndex]>arr[k])
                    minIndex = k;
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

}
