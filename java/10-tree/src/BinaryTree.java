import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * BinaryTree - Implementasi Binary Tree dengan berbagai traversal
 *
 * Traversal Methods:
 * - Inorder (Left, Root, Right)
 * - Preorder (Root, Left, Right)
 * - Postorder (Left, Right, Root)
 * - Level Order (BFS)
 */
public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(int data) {
        this.root = new TreeNode(data);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    // ==================== TRAVERSAL METHODS ====================

    /**
     * Inorder Traversal: Left -> Root -> Right
     * Untuk BST, menghasilkan urutan terurut ascending
     */
    public void inorder() {
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + " ");
            inorderRec(node.right);
        }
    }

    /**
     * Preorder Traversal: Root -> Left -> Right
     * Berguna untuk menyalin tree atau membuat prefix expression
     */
    public void preorder() {
        System.out.print("Preorder: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    /**
     * Postorder Traversal: Left -> Right -> Root
     * Berguna untuk menghapus tree atau membuat postfix expression
     */
    public void postorder() {
        System.out.print("Postorder: ");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(TreeNode node) {
        if (node != null) {
            postorderRec(node.left);
            postorderRec(node.right);
            System.out.print(node.data + " ");
        }
    }

    /**
     * Level Order Traversal (BFS)
     * Mengunjungi node level per level dari atas ke bawah
     */
    public void levelOrder() {
        System.out.print("Level Order: ");
        if (root == null) {
            System.out.println();
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println();
    }

    // ==================== TREE PROPERTIES ====================

    /**
     * Menghitung tinggi tree
     * Tinggi = jumlah edge dari root ke leaf terdalam
     */
    public int height() {
        return heightRec(root);
    }

    private int heightRec(TreeNode node) {
        if (node == null) {
            return -1; // atau 0 tergantung definisi
        }
        int leftHeight = heightRec(node.left);
        int rightHeight = heightRec(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Menghitung jumlah node dalam tree
     */
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    /**
     * Menghitung jumlah leaf nodes (node tanpa anak)
     */
    public int countLeaves() {
        return countLeavesRec(root);
    }

    private int countLeavesRec(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    /**
     * Mengecek apakah tree kosong
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Mencari nilai maksimum dalam tree
     */
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree kosong!");
        }
        return findMaxRec(root);
    }

    private int findMaxRec(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int maxVal = node.data;
        int leftMax = findMaxRec(node.left);
        int rightMax = findMaxRec(node.right);

        if (leftMax > maxVal) maxVal = leftMax;
        if (rightMax > maxVal) maxVal = rightMax;

        return maxVal;
    }

    /**
     * Mencari nilai minimum dalam tree
     */
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree kosong!");
        }
        return findMinRec(root);
    }

    private int findMinRec(TreeNode node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        int minVal = node.data;
        int leftMin = findMinRec(node.left);
        int rightMin = findMinRec(node.right);

        if (leftMin < minVal) minVal = leftMin;
        if (rightMin < minVal) minVal = rightMin;

        return minVal;
    }

    /**
     * Insert node secara level order (complete binary tree)
     */
    public void insert(int data) {
        TreeNode newNode = new TreeNode(data);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.left == null) {
                current.left = newNode;
                return;
            } else {
                queue.add(current.left);
            }

            if (current.right == null) {
                current.right = newNode;
                return;
            } else {
                queue.add(current.right);
            }
        }
    }

    /**
     * Menampilkan tree secara visual
     */
    public void displayVisual() {
        System.out.println("\nVisual Tree:");
        displayVisualRec(root, "", true);
    }

    private void displayVisualRec(TreeNode node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.data);
            displayVisualRec(node.left, prefix + (isLast ? "    " : "│   "), false);
            displayVisualRec(node.right, prefix + (isLast ? "    " : "│   "), true);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== BINARY TREE ===\n");

        BinaryTree tree = new BinaryTree();

        // Insert nodes level by level
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        for (int val : values) {
            tree.insert(val);
        }

        System.out.println("Tree struktur (Complete Binary Tree):");
        System.out.println("         1");
        System.out.println("       /   \\");
        System.out.println("      2     3");
        System.out.println("     / \\   / \\");
        System.out.println("    4   5 6   7");

        tree.displayVisual();

        System.out.println("\n--- Traversals ---");
        tree.inorder();
        tree.preorder();
        tree.postorder();
        tree.levelOrder();

        System.out.println("\n--- Properties ---");
        System.out.println("Height: " + tree.height());
        System.out.println("Total nodes: " + tree.countNodes());
        System.out.println("Leaf nodes: " + tree.countLeaves());
        System.out.println("Max value: " + tree.findMax());
        System.out.println("Min value: " + tree.findMin());
    }
}
