// Linked list implementation in C++

#include <bits/stdc++.h>
#include <iostream>
using namespace std;

// Creating a node with Class

// class Node
// {
// public:
//   int value;
//   Node *next;
// };

// Creating a node with Struct

struct node
{
  int value;
  struct node *previous;
  struct node *next;
};

int main()
{
  node *head; // This will be the unchanging first node

  head = new node; // Now root points to a node struct
  head->next = 0;  // The node root points to has its next pointer
                   //  set equal to a null pointer
  head->value = 5; // By using the -> operator, you can modify the node
                   //  a pointer (root in this case) points to.
}
