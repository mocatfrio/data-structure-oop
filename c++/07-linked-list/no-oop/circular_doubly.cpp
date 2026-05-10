#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* prev;
    Node* next;
};

void insertAtEnd(Node*& head, int data) {
    Node* newNode = new Node{data, nullptr, nullptr};
    if (!head) {
        head = newNode;
        head->next = head;
        head->prev = head;
        return;
    }
    Node* tail = head->prev;
    tail->next = newNode;
    newNode->prev = tail;
    newNode->next = head;
    head->prev = newNode;
}

void display(Node* head) {
    if (!head) return;
    Node* curr = head;
    do {
        cout << curr->data << " <-> ";
        curr = curr->next;
    } while (curr != head);
    cout << "(head)\n";
}

int main() {
    Node* head = nullptr;
    insertAtEnd(head, 10);
    insertAtEnd(head, 20);
    display(head);
    return 0;
}
