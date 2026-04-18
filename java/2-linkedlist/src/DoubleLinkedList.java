public class DoubleLinkedList {
  public static void main(String args[]){

  }
  
}

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
class DuadoubleLinkedlist{
  private DNode head;
  private DNode Tail;
  private int size;

  public DuadoubleLinkedlist(){
    this.head = null;
    this.Tail = null;
    this.size = 0;
  }

  public void insertawal(int data) {
    DNode newNode = new DNode(data);
    if (head == null) {
      head = Tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    size++;
  }
  
  public void insertAtEnd(int data) {
    DNode newNode = new DNode (data);

    if (Tail == null){
    head = Tail = newNode;
  } else {
    newNode = Tail;
    Tail.next = newNode;
  }
  {
    size++;
    System.out.println ("Insert di belakang: ");
  }
    
  }
}