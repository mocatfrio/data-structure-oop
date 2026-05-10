
public class MinHeapNoOOP {
    static int[] heap = new int[100];
    static int size = 0;
    
    public static void insert(int val) {
        if (size >= 100) return;
        heap[size] = val;
        int curr = size++;
        while (curr > 0 && heap[curr] < heap[(curr-1)/2]) {
            int temp = heap[curr];
            heap[curr] = heap[(curr-1)/2];
            heap[(curr-1)/2] = temp;
            curr = (curr-1)/2;
        }
    }
    public static void main(String[] args) {
        insert(10); insert(5); insert(15);
        System.out.println("Min: " + heap[0]);
    }
}
