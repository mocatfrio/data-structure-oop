#include <iostream>
using namespace std;

class SinglyLinkedList {
private:
    struct Node {
        int data;
        Node* next;
        Node(int val) : data(val), next(nullptr) {}
    };
    Node* head;
public:
    SinglyLinkedList() : head(nullptr) {}
    void insertAtEnd(int data) {
        Node* newNode = new Node(data);
        if (!head) { head = newNode; return; }
        Node* curr = head;
        while (curr->next) curr = curr->next;
        curr->next = newNode;
    }
    void display() {
        Node* curr = head;
        while (curr) { cout << curr->data << " -> "; curr = curr->next; }
        cout << "null\n";
    }
};

int main() {
    SinglyLinkedList list;
    list.insertAtEnd(10);
    list.insertAtEnd(20);
    list.display();
    return 0;
}
