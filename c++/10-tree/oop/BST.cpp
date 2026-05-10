#include <iostream>
using namespace std;

class BST {
private:
    struct Node { int data; Node *left, *right; Node(int val): data(val), left(nullptr), right(nullptr) {} };
    Node* root;
    Node* insert(Node* node, int data) {
        if (!node) return new Node(data);
        if (data < node->data) node->left = insert(node->left, data);
        else node->right = insert(node->right, data);
        return node;
    }
    void inorder(Node* n) { if(!n) return; inorder(n->left); cout << n->data << " "; inorder(n->right); }
public:
    BST() : root(nullptr) {}
    void insert(int data) { root = insert(root, data); }
    void print() { inorder(root); cout << "\n"; }
};

int main() { BST bst; bst.insert(50); bst.insert(30); bst.insert(70); bst.print(); return 0; }
