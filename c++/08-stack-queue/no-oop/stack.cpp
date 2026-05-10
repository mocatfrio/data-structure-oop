#include <iostream>
using namespace std;

#define MAX 100
int stack_arr[MAX];
int top = -1;

void push(int data) {
    if (top >= MAX - 1) return;
    stack_arr[++top] = data;
}
void pop() {
    if (top < 0) return;
    top--;
}
void display() {
    for (int i = 0; i <= top; i++) cout << stack_arr[i] << " ";
    cout << "\n";
}

int main() {
    push(10); push(20); display(); pop(); display(); return 0;
}
