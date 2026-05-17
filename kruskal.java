import java.util.*;

class Edge {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class kruskal {

    static int find(int parent[], int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    static void union(int parent[], int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }

    static void kruskalAlgo(List<Edge> edges, int n) {

        if (edges.size() == 0) {
            System.out.println("No edges present. MST not possible.");
            return;
        }

        Collections.sort(edges, (a, b) -> a.w - b.w);

        int parent[] = new int[n];

        for (int i = 0; i < n; i++)
            parent[i] = i;

        int count = 0, cost = 0;

        System.out.println("\nEdges in MST:");

        for (Edge e : edges) {

            int u = e.u;
            int v = e.v;

            int rootU = find(parent, u);
            int rootV = find(parent, v);

            if (rootU != rootV) {

                System.out.println((char)('a' + u) + " -> " + (char)('a' + v)
                        + " = " + e.w);

                cost += e.w;
                union(parent, rootU, rootV);
                count++;

                if (count == n - 1)
                    break;
            }
        }

        if (count != n - 1) {
            System.out.println("\nGraph is not connected. MST not possible.");
        } else {
            System.out.println("Total Cost = " + cost);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n;

        while (true) {
            System.out.print("Enter number of nodes (>=2): ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 2) break;
                else System.out.println("Number of nodes must be at least 2.");
            } else {
                System.out.println("Invalid input. Enter an integer.");
                sc.next();
            }
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int w;

                while (true) {
                    System.out.print("Weight " + (char)('a'+i) + "->" + (char)('a'+j) + ": ");

                    if (sc.hasNextInt()) {
                        w = sc.nextInt();

                        if (w >= 0) break;
                        else System.out.println("Weight cannot be negative.");
                    } else {
                        System.out.println("Invalid input. Enter an integer.");
                        sc.next();
                    }
                }

                if (w != 0) {
                    edges.add(new Edge(i, j, w));
                }
            }
        }

        kruskalAlgo(edges, n);

        sc.close();
    }
}





