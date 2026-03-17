import java.util.Random;
import java.util.Scanner;

class quickSort extends Thread {

    String[] arr;
    int left, right;
    int depth;

    static final int MAX = 7;

    quickSort(String[] arr, int left, int right, int depth) {
        this.arr = arr;
        this.left = left;
        this.right = right;
        this.depth = depth;
    }

    public void run() {
        quickSortParallel(arr, left, right, depth);
    }

    static void quickSortParallel(String[] arr, int left, int right, int depth) {

        if (left < right) {

            int pivotIndex = partition(arr, left, right);

            if (depth < MAX) {

                quickSort t1 = new quickSort(arr, left, pivotIndex - 1, depth + 1);
                quickSort t2 = new quickSort(arr, pivotIndex + 1, right, depth + 1);

                t1.start();
                t2.start();

                try {
                    t1.join();
                    t2.join();
                } catch (Exception e) {}

            } else {
                quickSortNormal(arr, left, pivotIndex - 1);
                quickSortNormal(arr, pivotIndex + 1, right);
            }
        }
    }

    static void quickSortNormal(String[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSortNormal(arr, left, pivotIndex - 1);
            quickSortNormal(arr, pivotIndex + 1, right);
        }
    }

    static int partition(String[] arr, int left, int right) {

        String pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
    }
}


public class quick_sort_binary_search {

    static Random random = new Random();
    static String letters = "abcdefghijklmnopqrstuvwxyz";

    static String randomString(int len) {
        String s = "";
        for (int i = 0; i < len; i++) {
            s += letters.charAt(random.nextInt(letters.length()));
        }
        return s;
    }

    static int binarySearch(String[] arr, String key) {

        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = arr[mid].compareTo(key);

            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    static String getValidInput(Scanner sc) {

    String key;

    while (true) {
        System.out.print("Enter string to search (3 lowercase letters): ");
        key = sc.next();

        if (key.length() == 3 && key.matches("[a-z]+")) {
            return key;
        }

        System.out.println("Invalid input. Try again.");
        }
    }


    public static void main(String[] args) throws Exception {

        int n = 1000000;
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = randomString(3);
        }

        Scanner sc = new Scanner(System.in);
        String key = getValidInput(sc);

        long start = System.currentTimeMillis();

        quickSort qs = new quickSort(arr, 0, n - 1, 0);
        qs.start();
        qs.join();

        long end = System.currentTimeMillis();
        System.out.println("Quick Sort Time = " + (end - start) + " ms");

        long s = System.nanoTime();

        int index = binarySearch(arr, key);

        long e = System.nanoTime();

        if (index != -1)
            System.out.println("Element found at index " + index);
        else
            System.out.println("Element not found");

        System.out.println("Binary Search Time = " + (e - s) + " ns");

        sc.close();
    }
}
