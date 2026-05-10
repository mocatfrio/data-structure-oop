public class CircularDoublyLinkedList {

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

    public CircularDoublyLinkedList() {
        this.head = null;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node tail = head.prev;
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node tail = head.prev;
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
        }
    }

    public int deleteAtBeginning() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        int data = head.data;
        if (head.next == head) {
            head = null;
        } else {
            Node tail = head.prev;
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        return data;
    }

    public int deleteAtEnd() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        Node tail = head.prev;
        int data = tail.data;
        if (head.next == head) {
            head = null;
        } else {
            Node newTail = tail.prev;
            newTail.next = head;
            head.prev = newTail;
        }
        return data;
    }

    public void displayForward() {
        if (head == null) {
            System.out.println("Circular Doubly: empty");
            return;
        }
        Node current = head;
        System.out.print("Forward: (tail) <-> ");
        do {
            System.out.print(current.data + " <-> ");
            current = current.next;
        } while (current != head);
        System.out.println("(head)");
    }

    public void displayBackward() {
        if (head == null) {
            System.out.println("Circular Doubly: empty");
            return;
        }
        Node current = head.prev; // Tail
        System.out.print("Backward: (head) <-> ");
        do {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        } while (current != head.prev);
        System.out.println("(tail)");
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.displayForward();  // (tail) <-> 5 <-> 10 <-> 20 <-> (head)
        list.displayBackward(); // (head) <-> 20 <-> 10 <-> 5 <-> (tail)

        list.deleteAtEnd();
        list.displayForward();  // (tail) <-> 5 <-> 10 <-> (head)

        list.deleteAtBeginning();
        list.displayForward();  // (tail) <-> 10 <-> (head)
    }
}
