#include <iostream>
using namespace std;

class CircularDoublyLinkedList {
private:
    struct Node { int data; Node* prev; Node* next; Node(int val): data(val), prev(nullptr), next(nullptr) {} };
    Node* head;
public:
    CircularDoublyLinkedList() : head(nullptr) {}
    void insertAtEnd(int data) {
        Node* newNode = new Node(data);
        if (!head) { head = newNode; head->next = head; head->prev = head; return; }
        Node* tail = head->prev;
        tail->next = newNode; newNode->prev = tail; newNode->next = head; head->prev = newNode;
    }
    void display() {
        if (!head) return;
        Node* curr = head;
        do { cout << curr->data << " <-> "; curr = curr->next; } while (curr != head);
        cout << "(head)\n";
    }
};

int main() {
    CircularDoublyLinkedList list;
    list.insertAtEnd(10); list.insertAtEnd(20); list.display(); return 0;
}
