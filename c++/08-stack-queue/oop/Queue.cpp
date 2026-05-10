#include <iostream>
using namespace std;

class Queue {
private:
    struct Node { int data; Node* next; Node(int val): data(val), next(nullptr) {} };
    Node *front, *rear;
public:
    Queue() : front(nullptr), rear(nullptr) {}
    void enqueue(int data) {
        Node* newNode = new Node(data);
        if (!rear) { front = rear = newNode; return; }
        rear->next = newNode; rear = newNode;
    }
    void dequeue() {
        if (!front) return;
        Node* temp = front; front = front->next;
        if (!front) rear = nullptr;
        delete temp;
    }
    void display() {
        Node* curr = front;
        while (curr) { cout << curr->data << " "; curr = curr->next; }
        cout << "\n";
    }
};

int main() {
    Queue q; q.enqueue(10); q.enqueue(20); q.display(); q.dequeue(); q.display(); return 0;
}
