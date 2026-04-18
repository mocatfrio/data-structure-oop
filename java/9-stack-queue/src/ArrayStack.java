public class ArrayStack {
    private int[] stack;
    private int top;
    private int maxSize;

    public ArrayStack(int size) {
        this.maxSize = size;
        this.stack = new int[maxSize];
        this.top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack Overflow!");
            return;
        }
        stack[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        return stack[top--];
    }

    public int peek() {
        if (isEmpty()) return -1;
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public int size() {
        return top + 1;
    }

    public void display() {
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

    public void displayVisual() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
            return;
        }
        System.out.println("+-------+");
        for (int i = top; i >= 0; i--) {
            String label = (i == top) ? " <- TOP" : "";
            System.out.printf("| %3d   |%s%n", stack[i], label);
            System.out.println("+-------+");
        }
    }

    public void clear() {
        top = -1;
    }

    public int search(int value) {
        for (int i = top; i >= 0; i--) {
            if (stack[i] == value) return top - i + 1;
        }
        return -1;
    }

    public int capacity() {
        return maxSize;
    }

    public static void main(String[] args) {
        System.out.println("=== ARRAY STACK ===\n");
        ArrayStack stack = new ArrayStack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();
        stack.displayVisual();
        System.out.println("Peek: " + stack.peek());
        stack.pop();
        stack.display();
    }
}
