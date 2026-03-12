#include <iostream>
#include <string>
#include <queue>
#include <cmath>
using namespace std;

class TreeNode
{
public:
  string data;
  TreeNode *left;
  TreeNode *right;

  TreeNode(string value) : data(value), left(nullptr), right(nullptr) {}
};

class BinaryTree
{
private:
  TreeNode *root;
  TreeNode *current;

  void clear(TreeNode *node)
  {
    if (node)
    {
      clear(node->left);
      clear(node->right);
      delete node;
    }
  }

  void preorder(TreeNode *node)
  {
    if (node)
    {
      cout << node->data << " ";
      preorder(node->left);
      preorder(node->right);
    }
  }

  void inorder(TreeNode *node)
  {
    if (node)
    {
      inorder(node->left);
      cout << node->data << " ";
      inorder(node->right);
    }
  }

  void postorder(TreeNode *node)
  {
    if (node)
    {
      postorder(node->left);
      postorder(node->right);
      cout << node->data << " ";
    }
  }

  TreeNode *find(TreeNode *node, const string &val, TreeNode *&parent)
  {
    if (!node)
      return nullptr;
    if (node->data == val)
      return node;

    parent = node;
    TreeNode *found = find(node->left, val, parent);
    if (!found)
    {
      parent = node;
      found = find(node->right, val, parent);
    }
    return found;
  }

  int getSize(TreeNode *node)
  {
    if (!node)
      return 0;
    return 1 + getSize(node->left) + getSize(node->right);
  }

  int getHeight(TreeNode *node)
  {
    if (!node)
      return -1;
    return 1 + max(getHeight(node->left), getHeight(node->right));
  }

  int totalPathLength(TreeNode *node, int depth = 0)
  {
    if (!node)
      return 0;
    return depth + totalPathLength(node->left, depth + 1) + totalPathLength(node->right, depth + 1);
  }

  void printTree(TreeNode *node, const string &prefix = "", bool isLeft = true, bool isRoot = false)
  {
    if (node != nullptr)
    {
      cout << prefix;

      if (!isRoot)
      {
        cout << (isLeft ? "├── " : "└── ");
      }

      cout << node->data << endl;

      string newPrefix = prefix + (isLeft ? "│   " : "    ");

      if (node->left || node->right)
      {
        if (node->left)
          printTree(node->left, newPrefix, true);
        else if (node->right)
          cout << newPrefix << "├── [null]" << endl;

        if (node->right)
          printTree(node->right, newPrefix, false);
        else if (node->left)
          cout << newPrefix << "└── [null]" << endl;
      }
    }
  }

public:
  BinaryTree() : root(nullptr), current(nullptr) {}

  void create()
  {
    clear(root);
    root = nullptr;
    current = nullptr;
    cout << "Tree created.\n";
  }

  void clear()
  {
    clear(root);
    root = nullptr;
    current = nullptr;
    cout << "Tree cleared.\n";
  }

  bool empty()
  {
    return root == nullptr;
  }

  void insert(const string &parentVal, const string &val, bool isLeft)
  {
    TreeNode *newNode = new TreeNode(val);
    if (!root)
    {
      root = newNode;
      current = root;
      cout << "Inserted root: " << val << "\n";
    }
    else
    {
      TreeNode *parent = nullptr;
      TreeNode *node = find(root, parentVal, parent);
      if (!node)
      {
        cout << "Parent not found.\n";
        delete newNode;
        return;
      }
      if (isLeft)
      {
        if (node->left)
          delete node->left;
        node->left = newNode;
      }
      else
      {
        if (node->right)
          delete node->right;
        node->right = newNode;
      }
      cout << "Inserted " << val << " under " << parentVal << (isLeft ? " (left)\n" : " (right)\n");
    }
  }

  void find(const string &val)
  {
    TreeNode *parent = nullptr;
    TreeNode *node = find(root, val, parent);
    if (node)
    {
      current = node;
      cout << "Found node: " << current->data << "\n";
      if (parent)
        cout << "Parent: " << parent->data << "\n";
      if (current->left)
        cout << "Left Child: " << current->left->data << "\n";
      if (current->right)
        cout << "Right Child: " << current->right->data << "\n";
    }
    else
    {
      cout << "Node not found.\n";
    }
  }

  void update(const string &newVal)
  {
    if (current)
    {
      current->data = newVal;
      cout << "Node updated to: " << newVal << "\n";
    }
    else
    {
      cout << "No current node selected.\n";
    }
  }

  void retrieve()
  {
    if (current)
    {
      cout << "Current node data: " << current->data << "\n";
    }
    else
    {
      cout << "No current node selected.\n";
    }
  }

  void deleteSubtree()
  {
    if (!current)
    {
      cout << "No current node selected.\n";
      return;
    }

    TreeNode *parent = nullptr;
    find(root, current->data, parent);

    if (parent)
    {
      if (parent->left == current)
        parent->left = nullptr;
      else if (parent->right == current)
        parent->right = nullptr;
    }
    else
    {
      root = nullptr;
    }

    clear(current);
    current = nullptr;

    cout << "Subtree deleted.\n";
  }

  void characteristic()
  {
    int size = getSize(root);
    int height = getHeight(root);
    double avgLen = size > 0 ? (double)totalPathLength(root) / size : 0.0;

    cout << "Size: " << size << "\n";
    cout << "Height: " << height << "\n";
    cout << "Average Path Length: " << avgLen << "\n";
  }

  void traverse(const string &method)
  {
    if (method == "preorder")
      preorder(root);
    else if (method == "inorder")
      inorder(root);
    else if (method == "postorder")
      postorder(root);
    else
      cout << "Invalid traversal method.\n";
    cout << "\n";
  }
  void printTree()
  {
    cout << "Tree structure:\n";
    printTree(root, "", false);
  }
};

int main()
{
  BinaryTree tree;

  tree.create();
  tree.insert("", "A", true); // Insert root
  tree.insert("A", "B", true);
  tree.insert("A", "C", false);
  tree.insert("B", "D", true);
  tree.insert("B", "E", false);
  tree.insert("C", "F", true);
  tree.printTree();
  
  cout << "Traversals:\n";
  cout << "Inorder: ";
  tree.traverse("inorder");

  cout << "Preorder: ";
  tree.traverse("preorder");

  cout << "Postorder: ";
  tree.traverse("postorder");

  tree.find("B");
  tree.retrieve();
  tree.update("B-updated");
  tree.retrieve();

  tree.characteristic();

  tree.deleteSubtree();
  tree.characteristic();

  return 0;
}
