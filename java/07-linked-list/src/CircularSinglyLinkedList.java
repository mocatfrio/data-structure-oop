public class CircularSinglyLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node tail; // Hanya menyimpan tail agar insert di awal/akhir lebih efisien

    public CircularSinglyLinkedList() {
        this.tail = null;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public int deleteAtBeginning() {
        if (tail == null) {
            throw new RuntimeException("List is empty");
        }
        int data = tail.next.data;
        if (tail.next == tail) { // Hanya ada 1 elemen
            tail = null;
        } else {
            tail.next = tail.next.next;
        }
        return data;
    }

    public int deleteAtEnd() {
        if (tail == null) {
            throw new RuntimeException("List is empty");
        }
        int data = tail.data;
        if (tail.next == tail) {
            tail = null;
        } else {
            Node current = tail.next;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = tail.next;
            tail = current;
        }
        return data;
    }

    public void display() {
        if (tail == null) {
            System.out.println("Circular Singly Linked List: empty");
            return;
        }
        Node current = tail.next;
        System.out.print("Circular Singly: ");
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != tail.next);
        System.out.println("(head)");
    }

    public static void main(String[] args) {
        CircularSinglyLinkedList list = new CircularSinglyLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.display(); // 5 -> 10 -> 20 -> (head)

        list.deleteAtBeginning();
        list.display(); // 10 -> 20 -> (head)

        list.deleteAtEnd();
        list.display(); // 10 -> (head)
    }
}
