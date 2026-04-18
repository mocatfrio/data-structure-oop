public class SingleLinkedList {
  SingleLinkedList list = new SingleLinkedList();
  //list.insertAtbeginning(20);
  //list.display();
  
}

class Node {
  int data;
  Node next;

  public Node(int data) {
    this.data = data;
    this.next = null;
  }
}

class SingleLinkedListMeow {
  private Node head;
  private int size;

  public int getSize()
  {
    return size;
  }

  public SingleLinkedListMeow() {
    this.head = null;
    this.size = 0;
  }

  public void insertAtbeginning(int data) {
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
    size++;
    System.out.println("Berhadil ditambah");
  }

  public void insertAtEnd(int data) {
    Node newNode = new Node(data);
    if (head == null) {
      head = newNode;
    }
    else {
      Node current = head;
    }

}

  public boolean isEmpty() {
    //eturn head = null;
  }

  public void display() {
    if (isEmpty()) {
      System.out.println("List kosong!");
      return;
    }
  }
  }