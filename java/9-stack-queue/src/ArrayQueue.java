public class ArrayQueue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int maxSize;

    public ArrayQueue(int capacity) {
        this.maxSize = capacity;
        this.queue = new int[maxSize];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue penuh!");
            return;
        }
        rear = (rear + 1) % maxSize;
        queue[rear] = value;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return -1;
        }
        int value = queue[front];
        front = (front + 1) % maxSize;
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) return -1;
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public int size() {
        return size;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return;
        }
        System.out.print("Queue: ");
        int count = 0;
        int i = front;
        while (count < size) {
            System.out.print(queue[i] + " ");
            i = (i + 1) % maxSize;
            count++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== ARRAY QUEUE ===\n");
        ArrayQueue queue = new ArrayQueue(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();
        System.out.println("Front: " + queue.peek());
        System.out.println("Dequeue: " + queue.dequeue());
        queue.display();
    }
}
