#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next;
};

void insertAtEnd(Node*& tail, int data) {
    Node* newNode = new Node{data, nullptr};
    if (!tail) {
        tail = newNode;
        tail->next = tail;
        return;
    }
    newNode->next = tail->next;
    tail->next = newNode;
    tail = newNode;
}

void display(Node* tail) {
    if (!tail) return;
    Node* curr = tail->next;
    do {
        cout << curr->data << " -> ";
        curr = curr->next;
    } while (curr != tail->next);
    cout << "(head)\n";
}

int main() {
    Node* tail = nullptr;
    insertAtEnd(tail, 10);
    insertAtEnd(tail, 20);
    display(tail);
    return 0;
}
