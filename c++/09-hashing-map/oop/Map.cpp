#include <iostream>
#include <unordered_map>
#include <string>
using namespace std;

class MyMap {
private:
    unordered_map<string, int> mapData;
public:
    void put(string key, int value) { mapData[key] = value; }
    void display() {
        for (auto const& [key, val] : mapData) {
            cout << key << ": " << val << "\n";
        }
    }
};

int main() {
    MyMap m; m.put("Alice", 20); m.put("Bob", 25); m.display(); return 0;
}
