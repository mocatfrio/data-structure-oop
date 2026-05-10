public class SinglyLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public int deleteAtBeginning() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        int data = head.data;
        head = head.next;
        return data;
    }

    public int deleteAtEnd() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        if (head.next == null) {
            int data = head.data;
            head = null;
            return data;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        int data = current.next.data;
        current.next = null;
        return data;
    }

    public void display() {
        Node current = head;
        System.out.print("Singly Linked List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.display(); // 5 -> 10 -> 20 -> null

        list.deleteAtEnd();
        list.display(); // 5 -> 10 -> null

        list.deleteAtBeginning();
        list.display(); // 10 -> null
    }
}
