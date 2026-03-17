#include <iostream>
#include <unordered_map>
#include <string>

using namespace std;

class Map {
private:
    unordered_map<string, int> map;

public:
    // Menambahkan pasangan key-value ke map
    void insert(const string &key, int value) {
        map[key] = value;
        cout << "Inserted: {" << key << ": " << value << "}" << endl;
    }

    // Mencari nilai berdasarkan key
    void search(const string &key) {
        if (map.find(key) != map.end()) {
            cout << "Found: {" << key << ": " << map[key] << "}" << endl;
        } else {
            cout << "Key \"" << key << "\" not found!" << endl;
        }
    }

    // Menghapus pasangan key-value berdasarkan key
    void remove(const string &key) {
        if (map.erase(key)) {
            cout << "Removed key \"" << key << "\"" << endl;
        } else {
            cout << "Key \"" << key << "\" not found!" << endl;
        }
    }

    // Menampilkan semua pasangan key-value dalam map
    void display() {
        if (map.empty()) {
            cout << "Map kosong!" << endl;
            return;
        }
        cout << "Isi map:" << endl;
        for (const auto &pair : map) {
            cout << "{" << pair.first << ": " << pair.second << "}" << endl;
        }
    }
};

int main() {
    Map myMap;

    // Operasi pada map
    myMap.insert("Alice", 25);
    myMap.insert("Bob", 30);
    myMap.insert("Charlie", 35);

    myMap.display();

    cout << "\nSearch for key \"Bob\":" << endl;
    myMap.search("Bob");

    cout << "\nSearch for key \"Eve\":" << endl;
    myMap.search("Eve");

    cout << "\nRemove key \"Alice\":" << endl;
    myMap.remove("Alice");
    myMap.display();

    cout << "\nRemove key \"Eve\":" << endl;
    myMap.remove("Eve");

    return 0;
}
