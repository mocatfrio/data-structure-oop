// graph.h

#ifndef GRAPH_H
#define GRAPH_H

#include <iostream>
using namespace std;

namespace myApp {
	class Graph {
		private:
			int totalVertices;
			bool** adjMatrix;
				
		public:
			// initialization 
			Graph(int total_vertices) { 
				totalVertices = total_vertices;
				adjMatrix = new bool*[total_vertices];
				for (int i = 0; i < total_vertices; i++) {
					adjMatrix[i] = new bool[total_vertices];
					for (int j = 0; j < total_vertices; j++) {
						adjMatrix[i][j] = false;
					}
				}
			}

			void addEdge(int source_id, int dest_id) {
				adjMatrix[source_id][dest_id] = true;
			}

			void removeEdge(int source_id, int dest_id) {
				adjMatrix[source_id][dest_id] = true;
			}

			void printMatrix() {
				for (int i = 0; i < totalVertices ; i++) {
					cout << i << " : ";
					for (int j = 0; j < totalVertices; j++) {
						cout << adjMatrix[i][j] << " ";
					}
					cout << "\n";
				}
			}
	};
};

#endif