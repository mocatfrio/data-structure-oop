/**
 * AVLTree - Self-balancing Binary Search Tree
 *
 * Properti AVL:
 * - Adalah BST
 * - Perbedaan tinggi antara subtree kiri dan kanan (balance factor) <= 1
 * - Balance factor = height(left) - height(right)
 *
 * Keuntungan:
 * - Semua operasi O(log n) guaranteed (tidak ada worst case O(n))
 *
 * Rotasi untuk menyeimbangkan:
 * - Left Rotation (RR case)
 * - Right Rotation (LL case)
 * - Left-Right Rotation (LR case)
 * - Right-Left Rotation (RL case)
 */
class AVLNode {
    int data;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    // ==================== HELPER METHODS ====================

    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // ==================== ROTATIONS ====================

    /**
     * Right Rotation (LL Case)
     *        y                x
     *       / \              / \
     *      x   T3    =>     T1  y
     *     / \                  / \
     *    T1  T2               T2  T3
     */
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /**
     * Left Rotation (RR Case)
     *      x                  y
     *     / \                / \
     *    T1  y      =>      x   T3
     *       / \            / \
     *      T2  T3         T1  T2
     */
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // ==================== INSERT ====================

    public void insert(int data) {
        root = insertRec(root, data);
    }

    private AVLNode insertRec(AVLNode node, int data) {
        // 1. Perform normal BST insertion
        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.data) {
            node.left = insertRec(node.left, data);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
        } else {
            return node; // Duplicate not allowed
        }

        // 2. Update height
        updateHeight(node);

        // 3. Get balance factor
        int balance = getBalance(node);

        // 4. If unbalanced, perform rotations

        // Left Left Case (LL)
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        // Right Right Case (RR)
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

        // Left Right Case (LR)
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case (RL)
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // ==================== DELETE ====================

    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private AVLNode deleteRec(AVLNode node, int data) {
        // 1. Perform standard BST delete
        if (node == null) {
            return null;
        }

        if (data < node.data) {
            node.left = deleteRec(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRec(node.right, data);
        } else {
            // Node found
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    // No child case
                    node = null;
                } else {
                    // One child case
                    node = temp;
                }
            } else {
                // Two children case
                AVLNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = deleteRec(node.right, temp.data);
            }
        }

        if (node == null) {
            return null;
        }

        // 2. Update height
        updateHeight(node);

        // 3. Get balance factor
        int balance = getBalance(node);

        // 4. Balance the node if needed

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ==================== SEARCH ====================

    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(AVLNode node, int data) {
        if (node == null) {
            return false;
        }

        if (data == node.data) {
            return true;
        } else if (data < node.data) {
            return searchRec(node.left, data);
        } else {
            return searchRec(node.right, data);
        }
    }

    // ==================== TRAVERSALS ====================

    public void inorder() {
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(AVLNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + " ");
            inorderRec(node.right);
        }
    }

    public void preorder() {
        System.out.print("Preorder: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    // ==================== UTILITIES ====================

    public int getHeight() {
        return height(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Menampilkan tree dengan balance factor
     */
    public void displayVisual() {
        System.out.println("\nVisual AVL Tree (nilai[balance factor]):");
        displayVisualRec(root, "", true);
    }

    private void displayVisualRec(AVLNode node, String prefix, boolean isLast) {
        if (node != null) {
            int balance = getBalance(node);
            System.out.println(prefix + (isLast ? "└── " : "├── ") +
                             node.data + "[" + balance + "]");
            displayVisualRec(node.left, prefix + (isLast ? "    " : "│   "), false);
            displayVisualRec(node.right, prefix + (isLast ? "    " : "│   "), true);
        }
    }

    /**
     * Cek apakah tree balanced
     */
    public boolean isBalanced() {
        return isBalancedRec(root);
    }

    private boolean isBalancedRec(AVLNode node) {
        if (node == null) {
            return true;
        }

        int balance = getBalance(node);
        if (Math.abs(balance) > 1) {
            return false;
        }

        return isBalancedRec(node.left) && isBalancedRec(node.right);
    }

    public static void main(String[] args) {
        System.out.println("=== AVL TREE (Self-Balancing BST) ===\n");

        AVLTree avl = new AVLTree();

        // Insert values yang akan menyebabkan rotasi
        int[] values = {10, 20, 30, 40, 50, 25};

        System.out.println("Inserting values sequentially:");
        for (int val : values) {
            System.out.println("\n--- Insert " + val + " ---");
            avl.insert(val);
            avl.displayVisual();
            System.out.println("Height: " + avl.getHeight() + ", Balanced: " + avl.isBalanced());
        }

        System.out.println("\n\n--- Final Tree ---");
        avl.inorder();
        avl.preorder();
        avl.displayVisual();

        System.out.println("\n--- Search ---");
        System.out.println("Search 25: " + avl.search(25));
        System.out.println("Search 15: " + avl.search(15));

        System.out.println("\n--- Comparison with Regular BST ---");
        System.out.println("Jika insert 10, 20, 30, 40, 50 ke BST biasa:");
        System.out.println("BST akan menjadi linked list (height = 4)");
        System.out.println("AVL menjaga balance, height = " + avl.getHeight());

        System.out.println("\n--- Delete Operations ---");
        System.out.println("Deleting 40...");
        avl.delete(40);
        avl.inorder();
        avl.displayVisual();
        System.out.println("Balanced: " + avl.isBalanced());

        System.out.println("\nDeleting 30...");
        avl.delete(30);
        avl.inorder();
        avl.displayVisual();
        System.out.println("Balanced: " + avl.isBalanced());
    }
}
