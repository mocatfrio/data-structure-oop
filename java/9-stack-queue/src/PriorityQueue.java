public class PriorityQueue {
    private int[] heap;
    private int size;
    private int maxSize;
    private boolean isMinHeap;

    public PriorityQueue(int capacity, boolean isMinHeap) {
        this.maxSize = capacity;
        this.heap = new int[maxSize];
        this.size = 0;
        this.isMinHeap = isMinHeap;
    }

    public PriorityQueue(int capacity) {
        this(capacity, true);
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private boolean compare(int a, int b) {
        return isMinHeap ? a < b : a > b;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(int value) {
        if (size >= maxSize) {
            System.out.println("Queue penuh!");
            return;
        }
        heap[size] = value;
        int current = size;
        size++;

        while (current > 0 && compare(heap[current], heap[parent(current)])) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int poll() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return -1;
        }
        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return root;
    }

    private void heapify(int i) {
        int best = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && compare(heap[left], heap[best])) {
            best = left;
        }
        if (right < size && compare(heap[right], heap[best])) {
            best = right;
        }
        if (best != i) {
            swap(i, best);
            heapify(best);
        }
    }

    public int peek() {
        if (isEmpty()) return -1;
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return;
        }
        System.out.print("Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== MIN PRIORITY QUEUE ===\n");
        PriorityQueue minPQ = new PriorityQueue(10, true);
        minPQ.insert(30);
        minPQ.insert(10);
        minPQ.insert(20);
        minPQ.insert(5);
        minPQ.display();
        System.out.println("Poll: " + minPQ.poll());
        System.out.println("Poll: " + minPQ.poll());
        minPQ.display();

        System.out.println("\n=== MAX PRIORITY QUEUE ===\n");
        PriorityQueue maxPQ = new PriorityQueue(10, false);
        maxPQ.insert(30);
        maxPQ.insert(10);
        maxPQ.insert(20);
        maxPQ.insert(5);
        maxPQ.display();
        System.out.println("Poll: " + maxPQ.poll());
        maxPQ.display();
    }
}
