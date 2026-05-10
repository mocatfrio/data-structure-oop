#include <iostream>
#include <vector>
using namespace std;

class GraphWeighted {
private:
    struct Edge { int to, weight; };
    int V;
    vector<vector<Edge>> adj;
public:
    GraphWeighted(int V) : V(V), adj(V) {}
    void addEdge(int u, int v, int w) {
        adj[u].push_back({v, w});
        adj[v].push_back({u, w});
    }
    void printGraph() {
        for (int v = 0; v < V; ++v) {
            cout << "Node " << v << " makes an edge with \n";
            for (auto it = adj[v].begin(); it != adj[v].end(); ++it) {
                cout << "\tNode " << it->to << " with edge weight = " << it->weight << "\n";
            }
        }
    }
};

int main() {
    GraphWeighted g(5);
    g.addEdge(0, 1, 10); g.addEdge(0, 4, 20);
    g.printGraph(); return 0;
}
