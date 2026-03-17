import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

class search extends Thread {

    String[] arr;
    int start, end;
    String key;
    int choice;
    AtomicBoolean found;
    List<Integer> pos = new ArrayList<>();

    search(String[] arr, int start, int end, String key, int choice, AtomicBoolean found) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.key = key;
        this.choice = choice;
        this.found = found;
    }

    public void run() {

        for (int i = start; i < end; i++) {

            if (choice == 0 && found.get()) {
                return;
            }

            if (arr[i].equals(key)) {
                pos.add(i);

                if (choice == 0) {
                    found.set(true);
                    return;
                }
            }
        }
    }
}


class linearSearch {

    String[] arr;
    String key;
    int choice;
    List<Integer> pos = new ArrayList<>();

    linearSearch(String[] arr, String key, int choice) {
        this.arr = arr;
        this.key = key;
        this.choice = choice;
    }

    public void search_key() {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(key)) {
                pos.add(i);
                if(choice==0){
                    break;
                }
            }
        }

    }
}


public class linear_search_element {

    private static String alphabets = "abcdefghijklmnopqrstuvwxyz";
    private static Random random = new Random();

    public static String generateAlphaString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(alphabets.length());
            sb.append(alphabets.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        int n = 1000000;
        String[] randomArray = new String[n];

        for (int i = 0; i < n; i++) {
            randomArray[i] = generateAlphaString(3);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string to search: ");
        String key = sc.nextLine();
        System.out.print("Multiple Occurence / Single Occurence (Type 1/0): ");
        int choice = sc.nextInt();


        AtomicBoolean found = new AtomicBoolean(false);

        search t1 = new search(randomArray, 0, n/5, key, choice, found);
        search t2 = new search(randomArray, n/5, 2*n/5, key, choice, found);
        search t3 = new search(randomArray, 2*n/5, 3*n/5, key, choice, found);
        search t4 = new search(randomArray, 3*n/5, 4*n/5, key, choice, found);
        search t5 = new search(randomArray, 4*n/5, n, key, choice, found);


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

        List<Integer> allThreadResults = new ArrayList<>();
        allThreadResults.addAll(t1.pos);
        allThreadResults.addAll(t2.pos);
        allThreadResults.addAll(t3.pos);
        allThreadResults.addAll(t4.pos);
        allThreadResults.addAll(t5.pos);

        if (!allThreadResults.isEmpty()) {
            System.out.println("Threaded search found occurrences at: " + allThreadResults);
        } else {
            System.out.println("String Not Found in threading.");
        }
        System.out.println("Time taken = " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();

        
        linearSearch l1 = new linearSearch(randomArray, key, choice);
        l1.search_key();
        System.out.println("Sequential search found occurrences at: " + l1.pos);

        endTime = System.currentTimeMillis();

        System.out.println("Time taken = " + (endTime - startTime) + " ms");

        sc.close();
    }
}
