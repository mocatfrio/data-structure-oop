/**
 * TreeNode - Node untuk Binary Tree
 * Setiap node memiliki data, pointer ke anak kiri dan anak kanan
 */
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    public static void main(String[] args) {
        System.out.println("=== TREE NODE ===\n");

        // Membuat node manual
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Tree struktur:");
        System.out.println("       1");
        System.out.println("      / \\");
        System.out.println("     2   3");
        System.out.println("    / \\");
        System.out.println("   4   5");

        System.out.println("\nRoot: " + root);
        System.out.println("Left child of root: " + root.left);
        System.out.println("Right child of root: " + root.right);
    }
}
