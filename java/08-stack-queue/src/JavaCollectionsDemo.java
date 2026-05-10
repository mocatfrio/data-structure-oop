import java.util.*;

public class JavaCollectionsDemo {
    public static void main(String[] args) {
        System.out.println("=== JAVA STACK (ArrayDeque) ===");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack: " + stack);
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack after pop: " + stack);

        System.out.println("\n=== JAVA QUEUE (LinkedList) ===");
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("Queue: " + queue);
        System.out.println("Peek: " + queue.peek());
        System.out.println("Poll: " + queue.poll());
        System.out.println("Queue after poll: " + queue);

        System.out.println("\n=== JAVA DEQUE ===");
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(10);
        deque.addLast(30);
        deque.addFirst(5);
        deque.addLast(40);
        System.out.println("Deque: " + deque);
        System.out.println("First: " + deque.peekFirst());
        System.out.println("Last: " + deque.peekLast());
        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println("Remove last: " + deque.removeLast());
        System.out.println("Deque after: " + deque);

        System.out.println("\n=== JAVA PRIORITY QUEUE ===");
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        minPQ.offer(30);
        minPQ.offer(10);
        minPQ.offer(20);
        System.out.println("Min PQ: " + minPQ);
        System.out.print("Poll order: ");
        while (!minPQ.isEmpty()) {
            System.out.print(minPQ.poll() + " ");
        }
        System.out.println();

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        maxPQ.offer(30);
        maxPQ.offer(10);
        maxPQ.offer(20);
        System.out.print("Max PQ poll order: ");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.poll() + " ");
        }
        System.out.println();
    }
}
