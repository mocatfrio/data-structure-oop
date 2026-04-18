public class CircularSingleLinkedList {
  
}

class Node {
  int data;
  Node next;
  
  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

class CircularLinkedList {
  private Node head = null;
  private Node tail = null;
  private int size = 0;

  public void insertAtBeginnning(int data) {
    Node newNode = new Node(data);

    if (head == null) {
      head = newNode;
      newNode.next = head;
    } else {
      Node temp = head;
      while (temp.next != head) {
        temp = temp.next;
      }
      newNode.next = head;
      temp.next = newNode;
      head = newNode;
    }

  }
  
  public void deleteAtBeginning() {
    if (isEmpty()) {
      System.out.println("List kosong!");
      return;
    }

    int deleteData = tail.data;

    if (head == tail) {
      head = tail = null;
    } else {
      head.next = head;
      tail.next = head;
    }

    size--;
    System.out.println("Hapus data di awal " + deleteData);
  }

  public void display{
    if (isEmpty()) {
      System.out.println("List kosong ");
      return;
    }

    Node temp = node;

  }
}