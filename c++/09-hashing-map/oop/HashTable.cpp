#include <iostream>
#include <vector>
#include <list>
using namespace std;

class HashTable {
private:
    int capacity;
    vector<list<int>> table;
public:
    HashTable(int size) : capacity(size), table(size) {}
    void insert(int key) { table[key % capacity].push_back(key); }
    void display() {
        for (int i = 0; i < capacity; i++) {
            cout << i << " --> ";
            for (int key : table[i]) cout << key << " ";
            cout << "\n";
        }
    }
};

int main() {
    HashTable ht(10); ht.insert(15); ht.insert(25); ht.display(); return 0;
}
