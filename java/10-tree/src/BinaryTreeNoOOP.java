
public class BinaryTreeNoOOP {
    static int[] tree = new int[100];
    static boolean[] exists = new boolean[100];
    
    public static void setRoot(int val) {
        tree[0] = val; exists[0] = true;
    }
    public static void setLeft(int parentIdx, int val) {
        int leftIdx = 2 * parentIdx + 1;
        tree[leftIdx] = val; exists[leftIdx] = true;
    }
    public static void setRight(int parentIdx, int val) {
        int rightIdx = 2 * parentIdx + 2;
        tree[rightIdx] = val; exists[rightIdx] = true;
    }
    public static void inorder(int idx) {
        if(!exists[idx]) return;
        inorder(2 * idx + 1);
        System.out.print(tree[idx] + " ");
        inorder(2 * idx + 2);
    }
    public static void main(String[] args) {
        setRoot(1); setLeft(0, 2); setRight(0, 3);
        inorder(0); System.out.println();
    }
}
