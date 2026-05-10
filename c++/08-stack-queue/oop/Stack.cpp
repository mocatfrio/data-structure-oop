#include <iostream>
#include <vector>
using namespace std;

class Stack {
private:
    vector<int> elements;
public:
    void push(int data) { elements.push_back(data); }
    void pop() { if (!elements.empty()) elements.pop_back(); }
    void display() {
        for (int e : elements) cout << e << " ";
        cout << "\n";
    }
};

int main() {
    Stack s; s.push(10); s.push(20); s.display(); s.pop(); s.display(); return 0;
}
