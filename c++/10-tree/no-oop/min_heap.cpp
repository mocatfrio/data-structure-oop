#include <iostream>
#include <vector>
using namespace std;
vector<int> heap;
void insertHeap(int data) { heap.push_back(data); }
int main() { insertHeap(10); cout << "Min Heap No OOP: " << heap[0] << "\n"; return 0; }
