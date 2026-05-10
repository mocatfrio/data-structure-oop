public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return (2 * i) + 1; }
    private int rightChild(int i) { return (2 * i) + 2; }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(int value) {
        if (size == capacity) throw new IllegalStateException("Heap penuh!");
        
        // Masukkan elemen di posisi terakhir
        heap[size] = value;
        int current = size;
        size++;

        // Heapify-Up (perbaiki posisi jika elemen baru lebih kecil dari parent)
        while (current != 0 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
    
    public int extractMin() {
        if (size <= 0) return Integer.MAX_VALUE;
        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return root;
    }

    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(3);
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.insert(15);
        minHeap.insert(5);
        minHeap.insert(4);
        minHeap.insert(45);

        System.out.println("Extract Min: " + minHeap.extractMin()); // 1
        System.out.println("Extract Min: " + minHeap.extractMin()); // 2
        System.out.println("Extract Min: " + minHeap.extractMin()); // 3
    }
}
