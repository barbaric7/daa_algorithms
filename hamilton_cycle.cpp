#include <iostream>
#include <vector>
using namespace std;

bool isCycle = false;

class Edge {
public:
    int src;
    int dest;

    Edge(int s, int d) {
        src = s;
        dest = d;
    }
};

int getValidInput(string message) {

    int value;

    while (true) {

        cout << message;
        cin >> value;

        if (cin.fail() || cin.peek() != '\n' || value <= 0) {

            cout << "Invalid input! Enter a valid number.\n";

            cin.clear();
            cin.ignore(1000, '\n');

        } else {
            return value;
        }
    }
}

bool isAdjacent(vector<vector<Edge>>& graph, int u, int v) {

    for (Edge e : graph[u]) {
        if (e.dest == v)
            return true;
    }

    return false;
}

bool isSafeHam(int v, vector<vector<Edge>>& graph, vector<int>& path, int pos) {

    if (!isAdjacent(graph, path[pos - 1], v))
        return false;

    for (int i = 0; i < pos; i++) {
        if (path[i] == v)
            return false;
    }

    return true;
}

bool solveHam(vector<vector<Edge>>& graph, vector<int>& path, int pos, int V) {

    if (pos == V) {

        if (isAdjacent(graph, path[pos - 1], path[0])) {

            cout << "\nHamiltonian Cycle : ";

            for (int i = 0; i < V; i++) {
                cout << path[i] << " ";
            }

            cout << path[0] << endl;

            isCycle = true;
        }

        return isCycle;
    }

    for (int v = 2; v <= V; v++) {

        if (isSafeHam(v, graph, path, pos)) {

            path[pos] = v;

            solveHam(graph, path, pos + 1, V);

            path[pos] = -1;
        }
    }

    return isCycle;
}

vector<vector<Edge>> createGraph(int V) {

    vector<vector<Edge>> graph(V + 1);

    int E;
    int maxEdges = (V * (V - 1)) / 2;

    while (true) {

        cout << "Enter number of edges (max " << maxEdges << ") : ";
        cin >> E;

        if (cin.fail() || cin.peek() != '\n' || E < 0 || E > maxEdges) {

            cout << "Invalid input! Enter value between 0 and " << maxEdges << ".\n";

            cin.clear();
            cin.ignore(1000, '\n');

        } else {
            break;
        }
    }

    cout << "Enter edges (src dest) :\n";

    for (int i = 0; i < E; i++) {

        int s, d;

        cin >> s >> d;

        if (cin.fail() || cin.peek() != '\n' ||
            s <= 0 || s > V ||
            d <= 0 || d > V ||
            s == d) {

            cout << "Invalid edge! Try again.\n";

            cin.clear();
            cin.ignore(1000, '\n');

            i--;

        } else {

            graph[s].push_back(Edge(s, d));
            graph[d].push_back(Edge(d, s));
        }
    }

    return graph;
}

int main() {

    cout<< "\n"<<endl;
    
    cout << "===== Hamiltonian Cycle Detection =====\n\n";

    int V = getValidInput("Enter number of vertices : ");

    vector<vector<Edge>> graph = createGraph(V);

    vector<int> path(V + 1, -1);

    path[0] = 1;

    if (!solveHam(graph, path, 1, V)) {
        cout << "\nNo Hamiltonian Cycle exists\n";
    }

    return 0;
}