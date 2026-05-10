#include <iostream>
#include <vector>
#include <list>
using namespace std;

const int TABLE_SIZE = 10;
vector<list<int>> hashTable(TABLE_SIZE);

void insertHash(int key) {
    int index = key % TABLE_SIZE;
    hashTable[index].push_back(key);
}

void displayHash() {
    for (int i = 0; i < TABLE_SIZE; i++) {
        cout << i << " --> ";
        for (int key : hashTable[i]) cout << key << " ";
        cout << "\n";
    }
}

int main() {
    insertHash(15); insertHash(25); insertHash(35);
    displayHash(); return 0;
}
