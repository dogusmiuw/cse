
public class ReverseArrayRecursion {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};

        reverse(arr, 0, arr.length - 1);
        print(arr);
    }

    private static void reverse(int[] arr, int i, int j) {
        if (i < j) {
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            reverse(arr, i + 1, j - 1);
        }
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
}
