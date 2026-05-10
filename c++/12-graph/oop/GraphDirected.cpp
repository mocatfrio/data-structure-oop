#include <iostream>
#include <vector>
using namespace std;

class GraphDirected {
private:
    int V;
    vector<vector<int>> adj;
public:
    GraphDirected(int V) : V(V), adj(V) {}
    void addEdge(int u, int v) { adj[u].push_back(v); }
    void printGraph() {
        for (int v = 0; v < V; ++v) {
            cout << "\n Adjacency list of vertex " << v << "\n head ";
            for (auto x : adj[v]) cout << "-> " << x;
            cout << "\n";
        }
    }
};

int main() {
    GraphDirected g(5);
    g.addEdge(0, 1); g.addEdge(0, 4); g.addEdge(1, 2); g.addEdge(1, 3);
    g.printGraph(); return 0;
}
