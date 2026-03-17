#include <iostream>
#include "graph.h"

using namespace std;
using namespace myApp;

int main() {
	myApp::Graph graph(4);

  graph.addEdge(0, 1);
  graph.addEdge(0, 2);
  graph.addEdge(1, 2);
  graph.addEdge(2, 0);
  graph.addEdge(2, 3);

  graph.printMatrix();
}

