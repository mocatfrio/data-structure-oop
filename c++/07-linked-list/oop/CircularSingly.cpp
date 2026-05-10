#include <iostream>
using namespace std;

class CircularSinglyLinkedList {
private:
    struct Node { int data; Node* next; Node(int val): data(val), next(nullptr) {} };
    Node* tail;
public:
    CircularSinglyLinkedList() : tail(nullptr) {}
    void insertAtEnd(int data) {
        Node* newNode = new Node(data);
        if (!tail) { tail = newNode; tail->next = tail; return; }
        newNode->next = tail->next; tail->next = newNode; tail = newNode;
    }
    void display() {
        if (!tail) return;
        Node* curr = tail->next;
        do { cout << curr->data << " -> "; curr = curr->next; } while (curr != tail->next);
        cout << "(head)\n";
    }
};

int main() {
    CircularSinglyLinkedList list;
    list.insertAtEnd(10); list.insertAtEnd(20);
    list.display(); return 0;
}
