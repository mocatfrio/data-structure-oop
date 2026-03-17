#include <iostream>
#include <vector>
#include <stack>
#include <chrono>
using namespace std;
using namespace chrono;

const int MAX_NODES = 100000; // Bisa diperbesar
vector<int> graph[MAX_NODES];
bool visited_recursive[MAX_NODES];

// === DFS Rekursif ===
void dfs_recursive(int node)
{
  visited_recursive[node] = true;
  for (int neighbor : graph[node])
  {
    if (!visited_recursive[neighbor])
    {
      dfs_recursive(neighbor);
    }
  }
}

// === DFS Non-Rekursif ===
void dfs_non_recursive(int start)
{
  vector<bool> visited_local(MAX_NODES, false);
  stack<int> s;
  s.push(start);

  while (!s.empty())
  {
    int node = s.top();
    s.pop();
    if (!visited_local[node])
    {
      visited_local[node] = true;
      for (auto it = graph[node].rbegin(); it != graph[node].rend(); ++it)
      {
        if (!visited_local[*it])
        {
          s.push(*it);
        }
      }
    }
  }
}

// === Buat Graph Linear Besar ===
void generate_linear_graph(int n)
{
  for (int i = 0; i < n - 1; ++i)
  {
    graph[i].push_back(i + 1);
    graph[i + 1].push_back(i);
  }
}

// === Main ===
int main()
{
  int n = MAX_NODES;
  generate_linear_graph(n);

  cout << "Testing DFS Rekursif..." << endl;
  auto start1 = high_resolution_clock::now();
  dfs_recursive(0);
  auto end1 = high_resolution_clock::now();
  auto duration1 = duration_cast<milliseconds>(end1 - start1);
  cout << "Waktu DFS Rekursif: " << duration1.count() << " ms" << endl;

  cout << "Testing DFS Non-Rekursif..." << endl;
  auto start2 = high_resolution_clock::now();
  dfs_non_recursive(0);
  auto end2 = high_resolution_clock::now();
  auto duration2 = duration_cast<milliseconds>(end2 - start2);
  cout << "Waktu DFS Non-Rekursif: " << duration2.count() << " ms" << endl;

  return 0;
}
