// Program to print BFS traversal from a given 
// source vertex. BFS(int s) traverses vertices 
// reachable from s. 

#include<iostream> 
#include <list> 
#include <bits/stdc++.h>

using namespace std; 

#define INF 2 << 22

// This class represents a directed graph using 
// adjacency list representation 
class Graph 
{ 
	int V; // No. of vertices 

	// Pointer to an array containing adjacency 
	// lists 
	list<int> *adj; 
public: 
	Graph(int V); // Constructor 

	// function to add an edge to graph 
	void addEdge(int v, int w); 

	// prints BFS traversal from a given source s 
	void BFS(int s); 
	void BFS(int s, int d);
	void printShortestDistance(int s, int dest, int *pred, int *dist);
}; 




Graph::Graph(int V) 
{ 
	this->V = V; 
	adj = new list<int>[V]; 
} 

void Graph::addEdge(int v, int w) 
{ 
	adj[v].push_back(w); // Add w to v’s list. 
} 


void showq(list <int> gq) 
{ 
    list <int> g = gq; 
    int i = 0;
    int lanjut = 1;
    if (lanjut==1) return;
    
    while (!g.empty()) 
    { 
        i++;
        cout << '\t' << (g.front()+1); 
        g.pop_front();
    } 
    if (i==0) cout << "\tempty";
	cout << '\n'; 
} 


void findPath(int const &startNode, int const &endNode, int* &parent){
    if( startNode == endNode || endNode == -1){
        printf("%d\n", startNode);
    }
    else{
        findPath(startNode, parent[endNode], parent);
        printf("%d\n", endNode);
    }
}


void Graph::printShortestDistance(int s, int dest, int *pred, int *dist)
{

    cout << "\nPath searching :\n";
 
    // vector path stores the shortest path
    vector<int> path;
    int crawl = dest;
    path.push_back(crawl);
    while (pred[crawl] != -1) {
        path.push_back(pred[crawl]);
        crawl = pred[crawl];
    }
 
    // distance from source is in distance array
    cout << "Shortest path length is : "
         << dist[dest];
 
    // printing path from source to destination
    cout << "\nPath is::\n";
    for (int i = path.size() - 1; i >= 0; i--)
        cout << path[i] << " ";
}

void Graph::BFS(int s, int d){

    int *dist = new int[V];
    int *parent = new int[V];

    for(int v = 0; v < V; ++v){
        dist[v] = INF;
        parent[v] = -1;
    }

    dist[s] = 0;
    queue<int> Q;
    Q.push( s );


    while( !Q.empty() ){
        int u = Q.front();
        Q.pop();

        cout << u << " ";

        list<int>::iterator it;
        for(it = adj[u].begin(); it != adj[u].end(); ++it){
            if( dist[*it] == INF ){
                Q.push(*it);
                dist[*it] = dist[u] + 1;
                parent[*it] = u;
            }
        }
    }


    // Print all the reachable nodes with distance from current nodes.
    printf("\n");
    for(int v = 0; v < V; ++v){
        if(dist[v] != INF){
            printf("jarak V%d -> V%d: %d\n", s, v, dist[v]);
        }else{
            printf("jarak V%d -> V%d: No Path\n", s, v);
        }
    }


    // Print the parent array.
    for(int v = 0; v < V; ++v){
        printf("parent of V%d: V%d\n", v, parent[v]);
    }

   printShortestDistance(s, d, parent, dist);

}




void Graph::BFS(int s) 
{ 
	// Mark all the vertices as not visited 
	bool *visited = new bool[V]; 
	for(int i = 0; i < V; i++) 
		visited[i] = false; 

	// Create a queue for BFS 
	list<int> queue; 

	// Mark the current node as visited and enqueue it 
	visited[s] = true; 
	queue.push_back(s); 

	// 'i' will be used to get all adjacent 
	// vertices of a vertex 
	list<int>::iterator i; 

	while(!queue.empty()) 
	{ 
		// Dequeue a vertex from queue and print it 
		s = queue.front(); 
		cout << "\n (V" << (s+0)<< ") "; 
	  	showq(queue); 
 
		queue.pop_front(); 

		showq(queue);			

		// Get all adjacent vertices of the dequeued 
		// vertex s. If a adjacent has not been visited, 
		// then mark it visited and enqueue it 
		for (i = adj[s].begin(); i != adj[s].end(); ++i) 
		{ 
 
			if (!visited[*i]) 
			{ 
  	  	        
			   visited[*i] = true; 
				queue.push_back(*i); 
 	          // showq(queue);
			} 
		} 


	} 
} 

// Driver program to test methods of graph class 
int main() 
{ 
	// Create a graph given in the above diagram 

   Graph g(5);     
   g.addEdge(0, 1); 
   g.addEdge(0, 2); 
   g.addEdge(1, 0); 
   g.addEdge(1, 2);
   g.addEdge(1, 3);
   g.addEdge(1, 4);
   g.addEdge(2, 0);
   g.addEdge(2, 1); 
   g.addEdge(2, 4);
   g.addEdge(3, 1);
   g.addEdge(3, 4); 
   g.addEdge(4, 2);
   g.addEdge(4, 1);
   g.addEdge(4, 3);

	
/*	   Graph g(5);     
   g.addEdge(4, 2); 
   g.addEdge(4, 3); 
   g.addEdge(2, 1); 
   g.addEdge(2, 3); 
   g.addEdge(1, 3); 
   g.addEdge(0, 1); 
*/

	/*
   Graph g(5);     
   g.addEdge(4, 2); 
   g.addEdge(4, 3); 
   g.addEdge(2, 1); 
   g.addEdge(2, 3);
   g.addEdge(2, 4);
   g.addEdge(3, 4);
   g.addEdge(3, 2);
   g.addEdge(3, 1); 
   g.addEdge(1, 3);
   g.addEdge(1, 2);
   g.addEdge(1, 0); 
   g.addEdge(0, 1); 
   */

        int awal = 0;
	cout << "Following is Breadth First Traversal "
		<< "(starting from vertex V" << awal << ") \n"; 
	g.BFS(awal); 

	cout << "\n";

        g.BFS(awal, 4);

	return 0; 

} 


