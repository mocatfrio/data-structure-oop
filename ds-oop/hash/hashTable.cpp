#include <iostream>
#include <list>
#include <vector>

using namespace std;

class HashTable {
private:
    vector<list<int>> table;
    int size;

    // Fungsi hash untuk menentukan indeks
    int hashFunction(int key) {
        return key % size;
    }

public:
    // Konstruktor untuk membuat hash table dengan ukuran tertentu
    HashTable(int size) {
        this->size = size;
        table.resize(size);
    }

    // Menambahkan elemen ke hash table
    void insert(int key) {
        int index = hashFunction(key);
        table[index].push_back(key);
        cout << "Inserted: " << key << " at index " << index << endl;
    }

    // Mencari elemen di hash table
    bool search(int key) {
        int index = hashFunction(key);
        for (int element : table[index]) {
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    // Menghapus elemen dari hash table
    void remove(int key) {
        int index = hashFunction(key);
        table[index].remove(key);
        cout << "Removed: " << key << " from index " << index << endl;
    }

    // Menampilkan isi hash table
    void display() {
        for (int i = 0; i < size; i++) {
            cout << "Index " << i << ": ";
            for (int element : table[i]) {
                cout << element << " -> ";
            }
            cout << "NULL" << endl;
        }
    }
};

int main() {
    HashTable ht(7); // Membuat hash table dengan ukuran 7

    // Operasi pada hash table
    ht.insert(10);
    ht.insert(20);
    ht.insert(15);
    ht.insert(7);
    ht.insert(22);

    ht.display();

    cout << "\nSearch 15: " << (ht.search(15) ? "Found" : "Not Found") << endl;
    cout << "Search 5: " << (ht.search(5) ? "Found" : "Not Found") << endl;

    ht.remove(15);
    ht.display();

    return 0;
}
