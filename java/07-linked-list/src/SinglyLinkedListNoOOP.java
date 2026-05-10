
public class SinglyLinkedListNoOOP {
    static int[] data = new int[100];
    static int[] next = new int[100];
    static int head = -1;
    static int freeList = 0;
    
    static {
        for (int i = 0; i < 99; i++) next[i] = i + 1;
        next[99] = -1;
    }
    
    public static void insertAtEnd(int val) {
        if (freeList == -1) return;
        int newNode = freeList;
        freeList = next[freeList];
        data[newNode] = val;
        next[newNode] = -1;
        
        if (head == -1) {
            head = newNode;
            return;
        }
        int curr = head;
        while (next[curr] != -1) curr = next[curr];
        next[curr] = newNode;
    }
    
    public static void display() {
        int curr = head;
        while (curr != -1) {
            System.out.print(data[curr] + " -> ");
            curr = next[curr];
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        insertAtEnd(10); insertAtEnd(20); display();
    }
}
