#include <iostream>
#include <vector>
using namespace std;

void addEdge(vector<int> adj[], int u, int v) {
    adj[u].push_back(v); // Directed: only u to v
}

void printGraph(vector<int> adj[], int V) {
    for (int v = 0; v < V; ++v) {
        cout << "\n Adjacency list of vertex " << v << "\n head ";
        for (auto x : adj[v]) cout << "-> " << x;
        cout << "\n";
    }
}

int main() {
    int V = 5;
    vector<int> adj[V];
    addEdge(adj, 0, 1);
    addEdge(adj, 0, 4);
    addEdge(adj, 1, 2);
    addEdge(adj, 1, 3);
    printGraph(adj, V);
    return 0;
}
