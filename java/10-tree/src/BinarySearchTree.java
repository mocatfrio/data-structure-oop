/**
 * BinarySearchTree (BST) - Binary tree dengan properti:
 * - Semua node di subtree kiri memiliki nilai lebih kecil dari root
 * - Semua node di subtree kanan memiliki nilai lebih besar dari root
 *
 * Operasi dasar:
 * - Insert: O(log n) average, O(n) worst case
 * - Search: O(log n) average, O(n) worst case
 * - Delete: O(log n) average, O(n) worst case
 */
public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    // ==================== INSERT ====================

    /**
     * Insert nilai baru ke BST
     */
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private TreeNode insertRec(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }

        if (data < node.data) {
            node.left = insertRec(node.left, data);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
        }
        // Jika data sama, tidak insert (BST tidak menyimpan duplikat)

        return node;
    }

    /**
     * Insert iterative (tanpa rekursi)
     */
    public void insertIterative(int data) {
        TreeNode newNode = new TreeNode(data);

        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode current = root;
        TreeNode parent = null;

        while (current != null) {
            parent = current;
            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else {
                return; // Duplikat, tidak insert
            }
        }

        if (data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // ==================== SEARCH ====================

    /**
     * Mencari nilai dalam BST
     */
    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(TreeNode node, int data) {
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

    /**
     * Search iterative
     */
    public boolean searchIterative(int data) {
        TreeNode current = root;

        while (current != null) {
            if (data == current.data) {
                return true;
            } else if (data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    // ==================== DELETE ====================

    /**
     * Menghapus nilai dari BST
     * 3 kasus:
     * 1. Node adalah leaf - langsung hapus
     * 2. Node punya 1 anak - ganti dengan anaknya
     * 3. Node punya 2 anak - ganti dengan inorder successor (nilai terkecil di subtree kanan)
     */
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private TreeNode deleteRec(TreeNode node, int data) {
        if (node == null) {
            return null;
        }

        if (data < node.data) {
            node.left = deleteRec(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRec(node.right, data);
        } else {
            // Node ditemukan, hapus

            // Kasus 1 & 2: Node punya 0 atau 1 anak
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Kasus 3: Node punya 2 anak
            // Cari inorder successor (nilai terkecil di subtree kanan)
            node.data = findMinValue(node.right);
            // Hapus inorder successor
            node.right = deleteRec(node.right, node.data);
        }

        return node;
    }

    private int findMinValue(TreeNode node) {
        int minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }

    // ==================== TRAVERSALS ====================

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

    // ==================== UTILITIES ====================

    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree kosong!");
        }
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree kosong!");
        }
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return Math.max(heightRec(node.left), heightRec(node.right)) + 1;
    }

    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Cek apakah tree adalah valid BST
     */
    public boolean isValidBST() {
        return isValidBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBSTRec(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.data <= min || node.data >= max) {
            return false;
        }

        return isValidBSTRec(node.left, min, node.data) &&
               isValidBSTRec(node.right, node.data, max);
    }

    /**
     * Mencari Lowest Common Ancestor (LCA) dari dua node
     */
    public TreeNode findLCA(int n1, int n2) {
        return findLCARec(root, n1, n2);
    }

    private TreeNode findLCARec(TreeNode node, int n1, int n2) {
        if (node == null) {
            return null;
        }

        if (n1 < node.data && n2 < node.data) {
            return findLCARec(node.left, n1, n2);
        }

        if (n1 > node.data && n2 > node.data) {
            return findLCARec(node.right, n1, n2);
        }

        return node;
    }

    /**
     * Menampilkan tree secara visual
     */
    public void displayVisual() {
        System.out.println("\nVisual BST:");
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
        System.out.println("=== BINARY SEARCH TREE ===\n");

        BinarySearchTree bst = new BinarySearchTree();

        // Insert values
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        System.out.print("Inserting: ");
        for (int val : values) {
            System.out.print(val + " ");
            bst.insert(val);
        }
        System.out.println();

        System.out.println("\nTree struktur:");
        System.out.println("         50");
        System.out.println("       /    \\");
        System.out.println("      30     70");
        System.out.println("     / \\    /  \\");
        System.out.println("    20  40 60   80");

        bst.displayVisual();

        System.out.println("\n--- Traversals ---");
        bst.inorder();
        bst.preorder();
        bst.postorder();

        System.out.println("\n--- Search ---");
        System.out.println("Search 40: " + bst.search(40));
        System.out.println("Search 25: " + bst.search(25));
        System.out.println("Search 70: " + bst.searchIterative(70));

        System.out.println("\n--- Properties ---");
        System.out.println("Min: " + bst.findMin());
        System.out.println("Max: " + bst.findMax());
        System.out.println("Height: " + bst.height());
        System.out.println("Total nodes: " + bst.countNodes());
        System.out.println("Is valid BST: " + bst.isValidBST());

        System.out.println("\n--- LCA (Lowest Common Ancestor) ---");
        TreeNode lca = bst.findLCA(20, 40);
        System.out.println("LCA of 20 and 40: " + (lca != null ? lca.data : "null"));
        lca = bst.findLCA(20, 80);
        System.out.println("LCA of 20 and 80: " + (lca != null ? lca.data : "null"));

        System.out.println("\n--- Delete ---");
        System.out.println("Deleting 20 (leaf node)...");
        bst.delete(20);
        bst.inorder();
        bst.displayVisual();

        System.out.println("\nDeleting 30 (node with 1 child)...");
        bst.delete(30);
        bst.inorder();
        bst.displayVisual();

        System.out.println("\nDeleting 50 (node with 2 children - root)...");
        bst.delete(50);
        bst.inorder();
        bst.displayVisual();
    }
}
