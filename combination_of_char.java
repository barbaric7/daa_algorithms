import java.util.Scanner;

class CombinationGenerator {
    String chars;
    
    CombinationGenerator(String chars) {
        this.chars = chars;
    }
    
    public void generateCombinations() {
        char[] arr = chars.toCharArray();
        permute(arr, 0, arr.length - 1);
    }
    
    private void permute(char[] arr, int start, int end) {
        if (start == end) {
            System.out.println(new String(arr));
        } else {
            for (int i = start; i <= end; i++) {
                char temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
                
                permute(arr, start + 1, end);
                
                temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
            }
        }
    }
}

public class combination_of_char {
    
    static String getValidInput(Scanner sc) {
        String key;
        while (true) {
            System.out.print("Enter string: ");
            key = sc.next();
            
            if (key.matches("[a-z]+") && key.length() == key.chars().distinct().count()) {
                return key;
            }
            
            System.out.println("Invalid input. Try again.");
        }


    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String input = getValidInput(sc);
        
        long startTime = System.currentTimeMillis();
        
        CombinationGenerator cg = new CombinationGenerator(input);
        cg.generateCombinations();
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time taken = " + (endTime - startTime) + " ms");
        
        sc.close();
    }
}