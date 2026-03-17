import java.util.Random;
import java.util.Scanner;

class mergeSort extends Thread {

    String[] arr;
    int left, right;
    int depth;

    static final int MAX = 7;

    mergeSort(String[] arr, int left, int right, int depth) {
        this.arr = arr;
        this.left = left;
        this.right = right;
        this.depth = depth;
    }

    public void run() {
        mergeSortParallel(arr, left, right, depth);
    }

    static void mergeSortParallel(String[] arr, int left, int right, int depth) {

        if (left < right) {

            int mid = (left + right) / 2;

            if (depth < MAX) {

                mergeSort t1 = new mergeSort(arr, left, mid, depth + 1);
                mergeSort t2 = new mergeSort(arr, mid + 1, right, depth + 1);

                t1.start();
                t2.start();

                try {
                    t1.join();
                    t2.join();
                } catch (Exception e) {}

            } else {
                mergeSortNormal(arr, left, mid);
                mergeSortNormal(arr, mid + 1, right);
            }

            merge(arr, left, mid, right);
        }
    }

    static void mergeSortNormal(String[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortNormal(arr, left, mid);
            mergeSortNormal(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(String[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0)
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }
}


public class merge_sort_binary_search {

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

    public static void main(String[] args) throws Exception {

        int n = 10000000;
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = randomString(3);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string to search: ");
        String key = sc.next();

        long start = System.currentTimeMillis();

        mergeSort ms = new mergeSort(arr, 0, n - 1, 0);
        ms.start();
        ms.join();


        long end = System.currentTimeMillis();
        System.out.println("Merge Sort Time = " + (end - start) + " ms");

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