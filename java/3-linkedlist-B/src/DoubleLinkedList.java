class DNode {
  int data;
  DNode prev;
  DNode next;

  public DNode(int data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }
}

public class DoubleLinkedList {
    private DNode head, tail;
    private int size;

    public DoubleLinkedList (){
      this.head = this.tail = null;
      this. int size = 0;
    }

    public int getSize() {
      return size;
    }

    public boolean isEmpty() {
      return head == null;
    }
  
    public void insertAtBeginnning(int data) {
    DNode newNode = new DNode(data);
    if (head == null) {
      head = tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode
    }

    
  }

  public void insertAtEnd(intData){
    DNode newNode = new DNode(data);
    if (tail == null){
      tail = head = newNode;
    } else {
      newNode.next=tail
    }
  }
  

  public void insertAtPos(int data, int pos) {
    DNode newDat = new DNode(data);

    // cari
    DNode current = head;
    for (int i = 0; i < pos; i++) {
      current = current.next;
    }

    newNode.next = current;
    newDat.prev = current.prev;
  }


public dF() {
  if (isEmpty()){
    System.out.println("listkosong");
    return;
  }

  system.out.println("forward: <-");
  DNode current=head;
  while(current!=null){
    system.out.print()

    if 
  }
  
}
