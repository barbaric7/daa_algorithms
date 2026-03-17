import java.util.Random;

class search extends Thread{
    int[] arr;
    int start, end;
    int min, max;

    search(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public void run() {
        min = arr[start];
        max = arr[start];

        for (int i = start; i < end; i++) {
            if (arr[i] < min)
                min = arr[i];

            if (arr[i] > max)
                max = arr[i];
        }
    }
}

class linearSearch{

    int[] arr;
    int start, end;
    int min, max;

    linearSearch(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public void min_max() {
        min = arr[start];
        max = arr[start];

        for (int i = start; i < end; i++) {
            if (arr[i] < min)
                min = arr[i];

            if (arr[i] > max)
                max = arr[i];
        }
    }

}

public class min_max_element {
    public static void main(String[] args) throws Exception {

        int n = 1000000;
        int[] arr = new int[n];
        
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(2000000);
        }

        
        search t1 = new search(arr, 0, n / 5);
        search t2 = new search(arr, n/5, 2*n/5);
        search t3 = new search(arr, 2*n/5, 3*n/5);
        search t4 = new search(arr, 3*n/5, 4*n/5);
        search t5 = new search(arr, 4*n/5, n);
        
        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        long endTime = System.currentTimeMillis();

        int min = Math.min(t1.min, Math.min(t2.min, Math.min(t3.min, Math.min(t4.min, t5.min))));
        int max = Math.max(t1.max, Math.max(t2.max, Math.max(t3.max, Math.max(t4.max, t5.max))));
        

        System.out.println("Min of t1 is " + t1.min + " Max of t1 is " + t1.max);
        System.out.println("Min of t2 is " + t2.min + " Max of t2 is " + t2.max);
        System.out.println("Min of t3 is " + t3.min + " Max of t3 is " + t3.max);
        System.out.println("Min of t4 is " + t4.min + " Max of t4 is " + t4.max);
        System.out.println("Min of t5 is " + t5.min + " Max of t5 is " + t5.max);

        System.out.println("Minimum = " + min);
        System.out.println("Maximum = " + max);


        System.out.println("Time taken = " + (endTime - startTime) + " ms");

        
        startTime = System.currentTimeMillis();

        linearSearch a1 = new linearSearch(arr, 0, n);

        a1.min_max();
        endTime = System.currentTimeMillis();

        System.out.println("Min of Array is " + a1.min + " Max of Array is " + a1.max);

        System.out.println("Time taken = " + (endTime - startTime) + " ms");

    }
}
