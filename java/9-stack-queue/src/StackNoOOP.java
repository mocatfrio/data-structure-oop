public class StackNoOOP {
    static int[] stack = new int[100];
    static int top = -1;
    static int MAX_SIZE = 100;

    public static void push(int value) {
        if (top >= MAX_SIZE - 1) {
            System.out.println("Stack Overflow!");
            return;
        }
        stack[++top] = value;
        System.out.println("Push: " + value);
    }

    public static int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        int value = stack[top--];
        System.out.println("Pop: " + value);
        return value;
    }

    public static int peek() {
        if (top < 0) return -1;
        return stack[top];
    }

    public static boolean isEmpty() {
        return top < 0;
    }

    public static boolean isFull() {
        return top >= MAX_SIZE - 1;
    }

    public static int size() {
        return top + 1;
    }

    public static void display() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
            return;
        }
        System.out.print("Stack: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    public static void clear() {
        top = -1;
    }

    public static int search(int value) {
        for (int i = top; i >= 0; i--) {
            if (stack[i] == value) return top - i + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=== STACK TANPA OOP ===\n");
        push(10);
        push(20);
        push(30);
        push(40);
        display();
        System.out.println("Size: " + size());
        System.out.println("Peek: " + peek());
        pop();
        pop();
        display();
    }
}
