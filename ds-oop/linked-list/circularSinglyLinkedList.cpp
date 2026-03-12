#include <iostream>

using namespace std;

class Node {
public:
    int data;
    Node* next;

    Node(int data) {
        this->data = data;
        this->next = nullptr;
    }
};

class CircularSinglyLinkedList
{
private:
  Node *head;

public:
  CircularSinglyLinkedList()
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
    }
    else
    {
      Node *temp = head;
      while (temp->next != head)
      {
        temp = temp->next;
      }
      newNode->next = head;
      temp->next = newNode;
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
    }
    else
    {
      Node *temp = head;
      while (temp->next != head)
      {
        temp = temp->next;
      }
      temp->next = newNode;
      newNode->next = head;
    }
  }

  void insertAtMiddle(int data, int position)
  {
    if (!head)
    {
      cout << "List kosong." << endl;
      return;
    }

    Node *newNode = new Node(data);
    Node *temp = head;
    int count = 1;

    while (count < position && temp->next != head)
    {
      temp = temp->next;
      count++;
    }

    newNode->next = temp->next;
    temp->next = newNode;
  }

  void deleteFirst()
  {
    if (!head)
    {
      cout << "List kosong." << endl;
      return;
    }

    if (head->next == head)
    {
      delete head;
      head = nullptr;
    }
    else
    {
      Node *temp = head;
      Node *last = head;

      while (last->next != head)
      {
        last = last->next;
      }

      head = head->next;
      last->next = head;
      delete temp;
    }
  }

  void deleteLast()
  {
    if (!head)
    {
      cout << "List kosong." << endl;
      return;
    }

    if (head->next == head)
    {
      delete head;
      head = nullptr;
    }
    else
    {
      Node *temp = head;
      Node *prev = nullptr;

      while (temp->next != head)
      {
        prev = temp; // Perbaikan: gunakan prev untuk menyimpan node sebelumnya
        temp = temp->next;
      }

      prev->next = head; // Perbaikan: prev->next harus menunjuk ke head
      delete temp;
    }
  }

  void deleteMiddle(int position)
  {
    if (!head)
    {
      cout << "List kosong." << endl;
      return;
    }

    Node *temp = head;
    Node *prev = nullptr;
    int count = 1;

    if (head->next == head && position == 1) // Perbaikan: tambahkan kondisi untuk satu node
    {
      delete head;
      head = nullptr;
      return;
    }

    if (position == 1)
    {
      Node *last = head;
      while (last->next != head)
      {
        last = last->next;
      }
      last->next = head->next;
      Node *toDelete = head;
      head = head->next;
      delete toDelete;
      return;
    }

    while (count < position && temp->next != head)
    {
      prev = temp;
      temp = temp->next;
      count++;
    }

    if (temp == head)
    {
      cout << "Posisi melebihi jumlah node dalam list." << endl;
      return;
    }

    prev->next = temp->next;
    delete temp;
  }

  void display()
  {
    if (!head)
    {
      cout << "List kosong." << endl;
      return;
    }
    Node *temp = head;
    // tracing
    do
    {
      cout << temp->data << " -> ";
      temp = temp->next;
    } while (temp != head);
    cout << "(kembali ke head)" << endl;
  }

  ~CircularSinglyLinkedList()
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
  CircularSinglyLinkedList csll;
  csll.insertAtLast(10);
  csll.insertAtLast(20);
  csll.display();

  cout << "\nInsert at Last" << endl;
  csll.insertAtLast(30);
  csll.display();

  cout << "\nInsert at First" << endl;
  csll.insertAtFirst(40);
  csll.display();

  cout << "\nInsert at Middle" << endl;
  csll.insertAtMiddle(50, 3);
  csll.display();

  cout << "\nDelete First" << endl;
  csll.deleteFirst();
  csll.display();

  cout << "\nDelete Last" << endl;
  csll.deleteLast();
  csll.display();

  cout << "\nDelete Middle" << endl;
  csll.deleteMiddle(2);
  csll.display();

  return 0;
}
