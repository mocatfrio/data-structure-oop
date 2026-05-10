#include <iostream>
using namespace std;

class BinaryTree {
private:
    struct Node { int data; Node* left; Node* right; Node(int val): data(val), left(nullptr), right(nullptr) {} };
    Node* root;
    void inorder(Node* n) { if(!n) return; inorder(n->left); cout << n->data << " "; inorder(n->right); }
public:
    BinaryTree() : root(nullptr) {}
    void setRoot(int val) { root = new Node(val); }
    void print() { inorder(root); cout << "\n"; }
};

int main() { BinaryTree bt; bt.setRoot(1); bt.print(); return 0; }
