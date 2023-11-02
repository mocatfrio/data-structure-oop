#include <bits/stdc++.h>
using namespace std;

class Graph
{
    // no of vertex
    int total_vertex; 

    // pointer to an array containing adjacency list 
    vector<list<int>> adjacency_list;

    public:
        // constructor 
        Graph(int total_vertex) {
            this->total_vertex = total_vertex;
            adjacency_list.resize(total_vertex);
        };
        // add edge to graph
        void addEdge(int vertex_id, int neighbor_id) {
            adjacency_list[vertex_id].push_back(neighbor_id);
        }
        void breadthFirstSearch(int source_vertex);



}