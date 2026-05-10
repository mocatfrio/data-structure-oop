#include <iostream>
#include <vector>
using namespace std;

class GraphUndirected {
private:
    int V;
    vector<vector<int>> adj;
public:
    GraphUndirected(int V) : V(V), adj(V) {}
    void addEdge(int u, int v) { adj[u].push_back(v); adj[v].push_back(u); }
    void printGraph() {
        for (int v = 0; v < V; ++v) {
            cout << "\n Adjacency list of vertex " << v << "\n head ";
            for (auto x : adj[v]) cout << "-> " << x;
            cout << "\n";
        }
    }
};

int main() {
    GraphUndirected g(5);
    g.addEdge(0, 1); g.addEdge(0, 4); g.addEdge(1, 2);
    g.printGraph(); return 0;
}
