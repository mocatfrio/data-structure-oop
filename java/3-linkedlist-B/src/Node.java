Class Node {
  int data;
  Node next;

  public class Node(int data) {
    this.data=data;
    this.next=null;
  }
}

Class SingleLinkedList {
  private int size;
  private Node head;

  public class SingleLinkedList() {
    this.size = 0;
    this.head = null;
  }

  public int getsize() {
    return size;
  }

  public boolean isEmpty() {
    return head == null;
  }
  public void insertAtBeginnning( int data ){
  Node newNode = newNode (data);
  newNode.next= head;
  head = newNode;
  size++;
  System.out.println("Insert di awal: " +data);
  }
  public void disokay(){
  if(isEmpty()){
    system.out.println("list kosong:")
    return;

    System.out.printIln("List: ";
    Node current = head;  
    current = head;
      while(current!=null) {
        System.out.print(current.data);
      }

      if (current.next != null) {
        System.out.print( " -> ");
      }
    )
  }
  }


}

public class SingleLinkedList {
  public static void main(String[] args) {
    System.out.print
  }
  
}
