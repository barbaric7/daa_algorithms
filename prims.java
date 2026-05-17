import java.util.Scanner;

public class prims {

    static final int MAX = 9999;

    static int minKey(int key[], boolean visited[], int n) {

        int min = MAX, index = -1;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && key[i] < min) {
                min = key[i];
                index = i;
            }
        }

        return index;
    }

    static void primsAlgo(int graph[][], int n) {

        int key[] = new int[n];
        int parent[] = new int[n];
        boolean visited[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            key[i] = MAX;
            visited[i] = false;
            parent[i] = -1;
        }

        key[0] = 0; // start from node 'a'

        for (int count = 0; count < n - 1; count++) {

            int u = minKey(key, visited, n);
            visited[u] = true;

            for (int v = 0; v < n; v++) {

                if (!visited[v] && graph[u][v] != MAX &&
                        graph[u][v] < key[v]) {

                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        int cost = 0;

        System.out.println("\nEdges in MST:");

        for (int i = 1; i < n; i++) {
            System.out.println((char)('a' + parent[i]) + " -> " + (char)('a' + i)
                    + " = " + graph[i][parent[i]]);
            cost += graph[i][parent[i]];
        }

        System.out.println("Total Cost = " + cost);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int graph[][] = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {

                if (i == j) {
                    graph[i][j] = 0;
                    continue;
                }

                System.out.print("Weight " + (char)('a'+i) + "->" + (char)('a'+j) + ": ");
                int w = sc.nextInt();

                if (w == 0) {
                    graph[i][j] = MAX;
                    graph[j][i] = MAX;
                } else {
                    graph[i][j] = w;
                    graph[j][i] = w;
                }
            }
        }

        primsAlgo(graph, n);

        sc.close();
    }
}