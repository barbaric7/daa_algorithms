import java.util.Scanner;

public class fibonacci {

    int recFibo(int n) {
        if (n <= 1) {
            return n;
        }
        return recFibo(n - 1) + recFibo(n - 2);
    }

    int memoFibo(int[] dp, int n) {
        if (n <= 1) {
            return n;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        return dp[n] = memoFibo(dp, n - 1) + memoFibo(dp, n - 2);
    }

    int tabuFibo(int n) {
        if (n <= 1) {
            return n;
        }

        int first = 0;
        int second = 1;

        for (int i = 2; i <= n; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }

        return second;
    }

    int takeValidInput() {
        Scanner sc = new Scanner(System.in);
        int target;

        while (true) {
            if (sc.hasNextInt()) {
                target = sc.nextInt();
                
                if (target >= 0) {
                    return target; 
                } else {
                    System.out.println("Invalid input!");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                sc.next();
            }
        }
    }

    public static void main(String[] args) {

        fibonacci obj = new fibonacci();

        System.out.println("\n1. Find using recursion");
        System.out.println("2. Find using Memoization");
        System.out.println("3. Find using Tabulation");
        System.out.print("Enter your choice: ");

        int choice = obj.takeValidInput();

        System.out.print("Enter n: ");
        int n = obj.takeValidInput();

        switch (choice) {

            case 1:
                System.out.println("Result: " + obj.recFibo(n));
                break;

            case 2:
                int[] dp = new int[n + 1];
                for (int i = 0; i <= n; i++) {
                    dp[i] = -1;
                }
                System.out.println("Result: " + obj.memoFibo(dp, n));
                break;

            case 3:
                System.out.println("Result: " + obj.tabuFibo(n));
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }
}