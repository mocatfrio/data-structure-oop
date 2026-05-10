public class DoublyLinkedList {

    private static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public int deleteAtBeginning() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        int data = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        return data;
    }

    public int deleteAtEnd() {
        if (tail == null) {
            throw new RuntimeException("List is empty");
        }
        int data = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        return data;
    }

    public void displayForward() {
        Node current = head;
        System.out.print("Forward: null <- ");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " <-> " : ""));
            current = current.next;
        }
        System.out.println(" -> null");
    }

    public void displayBackward() {
        Node current = tail;
        System.out.print("Backward: null <- ");
        while (current != null) {
            System.out.print(current.data + (current.prev != null ? " <-> " : ""));
            current = current.prev;
        }
        System.out.println(" -> null");
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.displayForward();  // null <- 5 <-> 10 <-> 20 -> null
        list.displayBackward(); // null <- 20 <-> 10 <-> 5 -> null

        list.deleteAtEnd();
        list.displayForward();  // null <- 5 <-> 10 -> null

        list.deleteAtBeginning();
        list.displayForward();  // null <- 10 -> null
    }
}
