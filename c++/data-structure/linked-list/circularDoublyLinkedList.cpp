#include <iostream>

using namespace std;

// Kelas Node
class Node
{
public:
  int data;
  Node *next;
  Node *prev;

  Node(int data)
  {
    this->data = data;
    this->next = this->prev = nullptr;
  }
};

// Kelas Circular Doubly Linked List
class CircularDoublyLinkedList
{
private:
  Node *head;

public:
  CircularDoublyLinkedList()
  {
    head = nullptr;
  }

  void insertAtFirst(int data)
  {
    Node *newNode = new Node(data);
    if (!head)
    {
      head = newNode;
      head->next = head;
      head->prev = head;
    }
    else
    {
      Node *tail = head->prev;
      newNode->next = head;
      newNode->prev = tail;
      head->prev = newNode;
      tail->next = newNode;
      head = newNode;
    }
  }

  void insertAtLast(int data)
  {
    Node *newNode = new Node(data);
    if (!head)
    {
      head = newNode;
      head->next = head;
      head->prev = head;
    }
    else
    {
      Node *tail = head->prev;
      tail->next = newNode;
      newNode->prev = tail;
      newNode->next = head;
      head->prev = newNode;
    }
  }

  void insertAtMiddle(int data)
  {
    if (!head)
    { // Jika list kosong, buat node pertama
      insertAtLast(data);
      return;
    }

    Node *slow = head;
    Node *fast = head;

    while (fast->next != head && fast->next->next != head)
    {
      slow = slow->next;
      fast = fast->next->next;
    }

    Node *newNode = new Node(data);
    newNode->next = slow->next;
    newNode->prev = slow;
    slow->next->prev = newNode;
    slow->next = newNode;
  }

  void deleteFirst()
  {
    if (!head)
    {
      cout << "List kosong!" << endl;
      return;
    }

    Node *tail = head->prev;
    Node *toDelete = head;

    if (head == head->next)
    {
      head = nullptr;
    }
    else
    {
      head = head->next;
      head->prev = tail;
      tail->next = head;
    }
    delete toDelete;
  }

  void deleteLast()
  {
    if (!head)
    {
      cout << "List kosong!" << endl;
      return;
    }

    Node *tail = head->prev;
    if (head == tail)
    {
      delete head;
      head = nullptr;
      return;
    }

    Node *newTail = tail->prev;
    newTail->next = head;
    head->prev = newTail;
    delete tail;
  }

  void deleteMiddle()
  {
    if (!head)
    { // Jika list kosong
      cout << "List kosong!" << endl;
      return;
    }

    if (head->next == head)
    {
      delete head;
      head = nullptr;
      return;
    }

    Node *slow = head;
    Node *fast = head;

    while (fast->next != head && fast->next->next != head)
    {
      slow = slow->next;
      fast = fast->next->next;
    }

    Node *temp = slow->next;
    slow->next = temp->next;
    temp->next->prev = slow;
    delete temp;
  }

  void display()
  {
    if (!head)
    {
      cout << "List kosong!" << endl;
      return;
    }
    Node *temp = head;
    do
    {
      cout << temp->data << " <-> ";
      temp = temp->next;
    } while (temp != head);
    cout << "(kembali ke head)" << endl;
  }

  ~CircularDoublyLinkedList()
  {
    if (!head)
      return;

    Node *temp = head;
    Node *nextNode;

    do
    {
      nextNode = temp->next;
      delete temp;
      temp = nextNode;
    } while (temp != head);
    head = nullptr;
  }
};

int main()
{
  CircularDoublyLinkedList cdll;

  cdll.insertAtLast(10);
  cdll.insertAtLast(20);
  cdll.insertAtLast(30);
  cdll.insertAtFirst(5);

  return 0;
}
