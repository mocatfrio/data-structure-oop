#include <iostream>
using namespace std;

#define MAX 100
int queue_arr[MAX];
int front = -1, rear = -1;

void enqueue(int data) {
    if (rear >= MAX - 1) return;
    if (front == -1) front = 0;
    queue_arr[++rear] = data;
}
void dequeue() {
    if (front == -1 || front > rear) return;
    front++;
}
void display() {
    if (front == -1 || front > rear) return;
    for (int i = front; i <= rear; i++) cout << queue_arr[i] << " ";
    cout << "\n";
}

int main() {
    enqueue(10); enqueue(20); display(); dequeue(); display(); return 0;
}
