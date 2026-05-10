
public class StackNoOOP {
    static int[] stack = new int[100];
    static int top = -1;
    
    public static void push(int val) {
        if (top >= 99) return;
        stack[++top] = val;
    }
    public static void pop() {
        if (top < 0) return;
        top--;
    }
    public static void display() {
        for(int i=0; i<=top; i++) System.out.print(stack[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        push(10); push(20); display(); pop(); display();
    }
}
