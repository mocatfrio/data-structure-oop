#include <iostream>
#include <vector>
#include <string>
using namespace std;

struct KeyValuePair {
    string key;
    int value;
};

vector<KeyValuePair> simpleMap;

void put(string key, int value) {
    for (auto& pair : simpleMap) {
        if (pair.key == key) { pair.value = value; return; }
    }
    simpleMap.push_back({key, value});
}

void displayMap() {
    for (const auto& pair : simpleMap) cout << pair.key << ": " << pair.value << "\n";
}

int main() {
    put("Alice", 20); put("Bob", 25); put("Alice", 21);
    displayMap(); return 0;
}
