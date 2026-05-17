#include <iostream>
#include <vector>
#include <limits>
using namespace std;

class Vertex {
private:
    int id;
    int color;

public:
    Vertex(int i) {
        id = i;
        color = 0;
    }

    int getId() {
        return id;
    }

    int getColor() {
        return color;
    }

    void setColor(int c) {
        color = c;
    }
};

class Graph {
private:
    int V;
    vector<vector<int>> graph;
    vector<Vertex> vertices;

    int getValidInput(string message) {
        int value;

        while (true) {
            cout << message;
            cin >> value;

            if (cin.fail() || value <= 0) {
                cout << "Invalid input! Enter a positive number.\n";
                cin.clear();
                cin.ignore(1000, '\n');
            } else {
                return value;
            }
        }
    }

    bool isSafeColor(int v, int c) {
        for (int neighbor : graph[v]) {
            if (vertices[neighbor].getColor() == c) {
                return false;
            }
        }
        return true;
    }

    bool solveColoringMain(int m, int v) {

        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {

            if (isSafeColor(v, c)) {

                vertices[v].setColor(c);

                if (solveColoringMain(m, v + 1))
                    return true;

                vertices[v].setColor(0);
            }
        }

        return false;
    }

public:
    Graph() {
        V = getValidInput("Enter number of vertices: ");

        graph.resize(V);

        for (int i = 0; i < V; i++) {
            vertices.push_back(Vertex(i));
        }
    }

    void createGraph() {

        int E;
        int maxEdges = (V * (V - 1)) / 2;

        while (true) {
            cout << "Enter number of edges (max " << maxEdges << "): ";
            cin >> E;

            if (cin.fail() || E < 0 || E > maxEdges) {
                cout << "Invalid input! Enter value between 0 and " << maxEdges << ".\n";
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
            } else {
                break;
            }
        }

        cout << "Enter edges (src dest):\n";

        for (int i = 0; i < E; i++) {
            int s, d;
            cin >> s >> d;

            if (cin.fail() || s < 0 || s >= V || d < 0 || d >= V) {
                cout << "Invalid edge! Try again.\n";
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
                i--;
            } else {
                graph[s].push_back(d);
                graph[d].push_back(s);
            }
        }
    }

    void solveColoring() {

        int m = getValidInput("Enter number of colors: ");

        bool result = solveColoringMain(m, 0);

        if (!result) {
            cout << "No solution exists\n";
        } else {
            cout << "Color assignment:\n";
            for (int i = 0; i < V; i++) {
                cout << "Vertex " << i << " -> Color " << vertices[i].getColor() << endl;
            }
        }

        findMinColors();
    }

    void findMinColors() {

        for (int i = 0; i < V; i++) {
            vertices[i].setColor(0);
        }

        for (int m = 1; m <= V; m++) {

            if (solveColoringMain(m, 0)) {

                cout << "\nMinimum colors required: " << m << endl;

                cout << "Color assignment:\n";
                for (int i = 0; i < V; i++) {
                    cout << "Vertex " << i << " -> Color " << vertices[i].getColor() << endl;
                }

                return;
            }
        }
    }
};

int main() {

    Graph g;

    g.createGraph();

    g.solveColoring();

    return 0;
}