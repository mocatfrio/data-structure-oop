#include <iostream>
using namespace std;

struct Node { int data; Node* left; Node* right; };

Node* createNode(int data) { return new Node{data, nullptr, nullptr}; }

void inorder(Node* root) {
    if (!root) return;
    inorder(root->left);
    cout << root->data << " ";
    inorder(root->right);
}

int main() {
    Node* root = createNode(1);
    root->left = createNode(2);
    root->right = createNode(3);
    inorder(root); cout << "\n"; return 0;
}
