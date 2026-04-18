public class Deque {
    private int[] deque;
    private int front;
    private int rear;
    private int size;
    private int maxSize;

    public Deque(int capacity) {
        this.maxSize = capacity;
        this.deque = new int[maxSize];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public void insertFront(int value) {
        if (isFull()) {
            System.out.println("Deque penuh!");
            return;
        }
        if (front == -1) {
            front = rear = 0;
        } else if (front == 0) {
            front = maxSize - 1;
        } else {
            front--;
        }
        deque[front] = value;
        size++;
    }

    public void insertRear(int value) {
        if (isFull()) {
            System.out.println("Deque penuh!");
            return;
        }
        if (front == -1) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % maxSize;
        }
        deque[rear] = value;
        size++;
    }

    public int deleteFront() {
        if (isEmpty()) {
            System.out.println("Deque kosong!");
            return -1;
        }
        int value = deque[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % maxSize;
        }
        size--;
        return value;
    }

    public int deleteRear() {
        if (isEmpty()) {
            System.out.println("Deque kosong!");
            return -1;
        }
        int value = deque[rear];
        if (front == rear) {
            front = rear = -1;
        } else if (rear == 0) {
            rear = maxSize - 1;
        } else {
            rear--;
        }
        size--;
        return value;
    }

    public int getFront() {
        if (isEmpty()) return -1;
        return deque[front];
    }

    public int getRear() {
        if (isEmpty()) return -1;
        return deque[rear];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public int size() {
        return size;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Deque kosong!");
            return;
        }
        System.out.print("Deque: ");
        int i = front;
        int count = 0;
        while (count < size) {
            System.out.print(deque[i] + " ");
            i = (i + 1) % maxSize;
            count++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== DEQUE ===\n");
        Deque dq = new Deque(5);
        dq.insertRear(10);
        dq.insertRear(20);
        dq.insertFront(5);
        dq.insertFront(1);
        dq.display();
        System.out.println("Front: " + dq.getFront());
        System.out.println("Rear: " + dq.getRear());
        System.out.println("Delete Front: " + dq.deleteFront());
        System.out.println("Delete Rear: " + dq.deleteRear());
        dq.display();
    }
}
