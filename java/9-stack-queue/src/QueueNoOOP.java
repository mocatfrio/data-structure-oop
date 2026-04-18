public class QueueNoOOP {
    static int[] queue = new int[100];
    static int front = 0;
    static int rear = -1;
    static int size = 0;
    static int MAX_SIZE = 100;

    public static void enqueue(int value) {
        if (size >= MAX_SIZE) {
            System.out.println("Queue penuh!");
            return;
        }
        rear = (rear + 1) % MAX_SIZE;
        queue[rear] = value;
        size++;
        System.out.println("Enqueue: " + value);
    }

    public static int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return -1;
        }
        int value = queue[front];
        front = (front + 1) % MAX_SIZE;
        size--;
        System.out.println("Dequeue: " + value);
        return value;
    }

    public static int peek() {
        if (isEmpty()) return -1;
        return queue[front];
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static boolean isFull() {
        return size == MAX_SIZE;
    }

    public static int size() {
        return size;
    }

    public static void display() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return;
        }
        System.out.print("Queue: ");
        int count = 0;
        int i = front;
        while (count < size) {
            System.out.print(queue[i] + " ");
            i = (i + 1) % MAX_SIZE;
            count++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== QUEUE TANPA OOP ===\n");
        enqueue(10);
        enqueue(20);
        enqueue(30);
        enqueue(40);
        display();
        System.out.println("Size: " + size());
        System.out.println("Front: " + peek());
        dequeue();
        dequeue();
        display();
    }
}
