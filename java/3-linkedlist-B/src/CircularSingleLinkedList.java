class node {
  int data;
  node next;

  public node(int data) {
    this.data = data;
    this.next = null;

  }
}

public class CircularSingleLinkedList {
  private node head;
  private node tail;
  private int size;

  public CircularSingleLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
   
  public int getsize() {
    return size;
  }

  public boolean isemtpty() {
    return head == null;
  }

  public void insertAtBeginning(int data) {
    node newnode = new node(data);
    if (head == null) {
      
      head = tail = newnode;
      newnode.next = newnode;
    }
    else {
      newnode.next = head;
      tail.next = newnode;
      head = newnode;
    }
    size++;
  }
  public void insertAtEnd(int data) {
    node newnode = new node(data);
    if (head == null) {
      
      head = tail = newnode;
      newnode.next = newnode;
    }
    else {
      newnode.next = head;
      tail.next = newnode;
      tail = newnode;
    }
    size++;
  }

  public void insertatposition(int data, int position) {
    if (position < 0 || position > size) {
      System.err.println("error");
      return;
    }

    if (position == 0) {

    }
    if (position == size) {
    
  }
  // cnode newnode = ne cnode(data);
  // cnode cur = head;
  // for (int i = 0; i<position-1;i++){
  //   cur = cur.next;

  // }
  // newnode.next = cur.next;

}

  public void endAtBeginning() {
    // if (isEmpty()) {
    // System.out.printIn
  public void deleteByValue(int value) {
    
  }
  
}

  
}
