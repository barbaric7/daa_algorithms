import java.util.Scanner;

public class dijkstra {

    static final int MAX = 9999;

    // checks array and returns the smallest unvisited node

    static int minDistance(int dist[], boolean visited[], int n) {

        int min = MAX, index = -1;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }

        return index;
    }

    static void printPath(int parent[], int j) {

        if (parent[j] == -1) {
            System.out.print((char)('a' + j));
            return;
        }

        printPath(parent, parent[j]);
        System.out.print("->" + (char)('a' + j));
    }

    static void dijkstraAlgo(int graph[][], int n, int src, int target) {

        int dist[] = new int[n];
        boolean visited[] = new boolean[n];
        int parent[] = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = MAX;
            visited[i] = false;
            parent[i] = -1;
        }

        dist[src] = 0;

        for (int count = 0; count < n - 1; count++) {

            int u = minDistance(dist, visited, n);
            visited[u] = true;
            
            // updating neighbour distances
            
            for (int v = 0; v < n; v++) {

                if (!visited[v] && graph[u][v] != MAX &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        System.out.print("\nShortest Path: ");
        printPath(parent, target);

        System.out.println("\nCost = " + dist[target]);
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

        System.out.print("\nEnter source node: ");
        char s = sc.next().charAt(0);

        System.out.print("Enter target node: ");
        char t = sc.next().charAt(0);

        int src = s - 'a';
        int target = t - 'a';

        dijkstraAlgo(graph, n, src, target);

        sc.close();
    }
}