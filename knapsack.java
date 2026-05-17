import java.util.*;

class Item {
    static int counter = 0;
    int no;
    float profit;
    float weight;
    float ratio;

    Item() {
        counter++;
        no = counter;
    }
}

public class knapsack {

    static Scanner sc = new Scanner(System.in);

    static float getValidFloat(String message) {
        float value;

        while (true) {
            System.out.print(message);

            try {
                value = sc.nextFloat();

                if (value < 0) {
                    System.out.println("Invalid input! Enter a positive number.");
                } else {
                    return value;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a valid number.");
                sc.next();
            }
        }
    }

    static char getValidChoice(String message) {
        char choice;

        while (true) {
            System.out.print(message);
            choice = sc.next().charAt(0);

            if (choice == 'y' || choice == 'Y' || choice == 'n' || choice == 'N') {
                return choice;
            } else {
                System.out.println("Invalid input! Enter y or n.");
            }
        }
    }

    static void input(ArrayList<Item> collection) {
        boolean take = true;

        while (take) {
            Item n = new Item();

            n.profit = getValidFloat("\nEnter profit: ");
            n.weight = getValidFloat("Enter weight: ");

            if (n.weight == 0) {
                System.out.println("Weight cannot be zero!");
                continue;
            }

            n.ratio = n.profit / n.weight;
            System.out.println("Profit/Weight Ratio: " + n.ratio);

            collection.add(n);

            char flag = getValidChoice("Add more items? (y/n): ");
            if (flag == 'n' || flag == 'N') {
                take = false;
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Item> collection = new ArrayList<>();

        input(collection);

        Collections.sort(collection, new Comparator<Item>() {
            public int compare(Item a, Item b) {

                int cmp = Float.compare(b.ratio, a.ratio);

                if (cmp == 0) {
                    return Float.compare(b.weight, a.weight);
                }

                return cmp;
            }
        });

        ArrayList<Item> bag = new ArrayList<>();

        float size = getValidFloat("\nEnter the size of bag: ");

        for (Item i : collection) {
            if (size == 0) {
                System.out.println("\nBag is full!");
                break;
            }

            if (i.weight <= size) {
                bag.add(i);
                size -= i.weight;
            } else {
                System.out.println("\nItem " + i.no + " too large! Skipping...");
            }
        }

        float totalProfit = 0;
        float totalWeight = 0;

        System.out.println("\nSelected Items:");

        for (Item i : bag) {
            System.out.println("\nItem No: " + i.no);
            System.out.println("Profit  : " + i.profit);
            System.out.println("Weight  : " + i.weight);

            totalProfit += i.profit;
            totalWeight += i.weight;
        }

        System.out.println("\nTotal Profit : " + totalProfit);
        System.out.println("Total Weight : " + totalWeight);
    }
}