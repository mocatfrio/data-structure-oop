
public class QueueNoOOP {
    static int[] queue = new int[100];
    static int front = -1, rear = -1;
    
    public static void enqueue(int val) {
        if (rear >= 99) return;
        if (front == -1) front = 0;
        queue[++rear] = val;
    }
    public static void dequeue() {
        if (front == -1 || front > rear) return;
        front++;
    }
    public static void display() {
        if (front == -1 || front > rear) return;
        for(int i=front; i<=rear; i++) System.out.print(queue[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        enqueue(10); enqueue(20); display(); dequeue(); display();
    }
}
