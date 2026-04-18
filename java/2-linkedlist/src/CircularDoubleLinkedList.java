class CDNode {
  int data;
  CDNode prev;
  CDNode next;

  public CDNode(int data){
    this.data =data;
    this.prev = null;
    this.next = null;
  }
}

class CCDL {
  private CDNode head;
  private CDNode tail;
  private int size;

  public CCDL() {
    this.head = null;
    this.tail = null;
    this.size = 0;

  }

  public int getsize() {
    return size;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void insertAtBeginning(int data) {
    CDNode newNode = new CDNode(data);

    if (isEmpty()) {
      head = tail = newNode;
      newNode.next = newNode;
      newNode.prev = newNode;
    } else {
      newNode.next = head;
      newNode.prev = tail;
      head.prev = newNode;
      tail.next = newNode;
      head = newNode;
    }

    size++;
  }
}