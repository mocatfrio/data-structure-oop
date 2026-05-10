#include <iostream>
#include <vector>
using namespace std;

struct Edge { int to, weight; };

void addEdge(vector<Edge> adj[], int u, int v, int w) {
    adj[u].push_back({v, w});
    adj[v].push_back({u, w});
}

void printGraph(vector<Edge> adj[], int V) {
    for (int v = 0; v < V; ++v) {
        cout << "Node " << v << " makes an edge with \n";
        for (auto it = adj[v].begin(); it != adj[v].end(); ++it) {
            cout << "\tNode " << it->to << " with edge weight = " << it->weight << "\n";
        }
    }
}

int main() {
    int V = 5;
    vector<Edge> adj[V];
    addEdge(adj, 0, 1, 10);
    addEdge(adj, 0, 4, 20);
    printGraph(adj, V);
    return 0;
}
