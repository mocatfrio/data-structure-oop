#include <iostream>
using namespace std;

class DoublyLinkedList {
private:
    struct Node {
        int data; Node* prev; Node* next;
        Node(int val) : data(val), prev(nullptr), next(nullptr) {}
    };
    Node* head; Node* tail;
public:
    DoublyLinkedList() : head(nullptr), tail(nullptr) {}
    void insertAtEnd(int data) {
        Node* newNode = new Node(data);
        if (!tail) { head = tail = newNode; return; }
        tail->next = newNode; newNode->prev = tail; tail = newNode;
    }
    void display() {
        Node* curr = head;
        while (curr) { cout << curr->data << " <-> "; curr = curr->next; }
        cout << "null\n";
    }
};

int main() {
    DoublyLinkedList list;
    list.insertAtEnd(10); list.insertAtEnd(20);
    list.display();
    return 0;
}
