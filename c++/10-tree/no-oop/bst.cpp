#include <iostream>
using namespace std;

struct Node { int data; Node *left, *right; };

Node* insertBST(Node* root, int data) {
    if (!root) return new Node{data, nullptr, nullptr};
    if (data < root->data) root->left = insertBST(root->left, data);
    else root->right = insertBST(root->right, data);
    return root;
}

void inorder(Node* root) {
    if (!root) return;
    inorder(root->left); cout << root->data << " "; inorder(root->right);
}

int main() {
    Node* root = nullptr;
    root = insertBST(root, 50); insertBST(root, 30); insertBST(root, 70);
    inorder(root); cout << "\n"; return 0;
}
