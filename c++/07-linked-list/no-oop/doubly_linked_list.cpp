#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* prev;
    Node* next;
};

void insertAtEnd(Node*& head, Node*& tail, int data) {
    Node* newNode = new Node{data, nullptr, nullptr};
    if (!head) {
        head = tail = newNode;
        return;
    }
    tail->next = newNode;
    newNode->prev = tail;
    tail = newNode;
}

void displayForward(Node* head) {
    while (head) {
        cout << head->data << " <-> ";
        head = head->next;
    }
    cout << "null\n";
}

int main() {
    Node* head = nullptr;
    Node* tail = nullptr;
    insertAtEnd(head, tail, 10);
    insertAtEnd(head, tail, 20);
    displayForward(head);
    return 0;
}
