import java.util.Stack;

/**
 * ExpressionTree - Tree untuk merepresentasikan ekspresi matematika
 *
 * Struktur:
 * - Leaf nodes berisi operand (angka)
 * - Internal nodes berisi operator (+, -, *, /)
 *
 * Contoh: (3 + 5) * 2
 *           *
 *          / \
 *         +   2
 *        / \
 *       3   5
 *
 * Traversal menghasilkan:
 * - Inorder: 3 + 5 * 2 (infix notation)
 * - Preorder: * + 3 5 2 (prefix notation)
 * - Postorder: 3 5 + 2 * (postfix notation)
 */
class ExprNode {
    String value;
    ExprNode left;
    ExprNode right;

    public ExprNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public boolean isOperator() {
        return value.equals("+") || value.equals("-") ||
               value.equals("*") || value.equals("/");
    }
}

public class ExpressionTree {
    private ExprNode root;

    public ExpressionTree() {
        this.root = null;
    }

    public ExprNode getRoot() {
        return root;
    }

    /**
     * Membangun expression tree dari postfix expression
     * Contoh: "3 5 + 2 *" untuk (3 + 5) * 2
     */
    public void buildFromPostfix(String postfix) {
        Stack<ExprNode> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            ExprNode node = new ExprNode(token);

            if (isOperator(token)) {
                // Pop dua operand dari stack
                node.right = stack.pop();
                node.left = stack.pop();
            }

            stack.push(node);
        }

        root = stack.pop();
    }

    /**
     * Membangun expression tree dari prefix expression
     * Contoh: "* + 3 5 2" untuk (3 + 5) * 2
     */
    public void buildFromPrefix(String prefix) {
        Stack<ExprNode> stack = new Stack<>();
        String[] tokens = prefix.split(" ");

        // Process dari kanan ke kiri
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            ExprNode node = new ExprNode(token);

            if (isOperator(token)) {
                // Pop dua operand dari stack
                node.left = stack.pop();
                node.right = stack.pop();
            }

            stack.push(node);
        }

        root = stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
               token.equals("*") || token.equals("/");
    }

    // ==================== TRAVERSALS ====================

    /**
     * Inorder traversal - menghasilkan infix expression
     */
    public String infix() {
        return infixRec(root);
    }

    private String infixRec(ExprNode node) {
        if (node == null) {
            return "";
        }

        if (!node.isOperator()) {
            return node.value;
        }

        return "(" + infixRec(node.left) + " " + node.value + " " +
               infixRec(node.right) + ")";
    }

    /**
     * Preorder traversal - menghasilkan prefix expression
     */
    public String prefix() {
        StringBuilder sb = new StringBuilder();
        prefixRec(root, sb);
        return sb.toString().trim();
    }

    private void prefixRec(ExprNode node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.value).append(" ");
            prefixRec(node.left, sb);
            prefixRec(node.right, sb);
        }
    }

    /**
     * Postorder traversal - menghasilkan postfix expression
     */
    public String postfix() {
        StringBuilder sb = new StringBuilder();
        postfixRec(root, sb);
        return sb.toString().trim();
    }

    private void postfixRec(ExprNode node, StringBuilder sb) {
        if (node != null) {
            postfixRec(node.left, sb);
            postfixRec(node.right, sb);
            sb.append(node.value).append(" ");
        }
    }

    // ==================== EVALUATION ====================

    /**
     * Mengevaluasi expression tree dan mengembalikan hasilnya
     */
    public double evaluate() {
        return evaluateRec(root);
    }

    private double evaluateRec(ExprNode node) {
        if (node == null) {
            return 0;
        }

        // Jika leaf node (operand), kembalikan nilainya
        if (!node.isOperator()) {
            return Double.parseDouble(node.value);
        }

        // Evaluasi subtree kiri dan kanan
        double leftVal = evaluateRec(node.left);
        double rightVal = evaluateRec(node.right);

        // Apply operator
        switch (node.value) {
            case "+": return leftVal + rightVal;
            case "-": return leftVal - rightVal;
            case "*": return leftVal * rightVal;
            case "/": return leftVal / rightVal;
            default: return 0;
        }
    }

    // ==================== VISUALIZATION ====================

    public void displayVisual() {
        System.out.println("\nExpression Tree:");
        displayVisualRec(root, "", true);
    }

    private void displayVisualRec(ExprNode node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.value);
            displayVisualRec(node.left, prefix + (isLast ? "    " : "│   "), false);
            displayVisualRec(node.right, prefix + (isLast ? "    " : "│   "), true);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== EXPRESSION TREE ===\n");

        // Contoh 1: (3 + 5) * 2
        System.out.println("--- Expression: (3 + 5) * 2 ---");
        ExpressionTree tree1 = new ExpressionTree();
        tree1.buildFromPostfix("3 5 + 2 *");

        tree1.displayVisual();
        System.out.println("\nInfix: " + tree1.infix());
        System.out.println("Prefix: " + tree1.prefix());
        System.out.println("Postfix: " + tree1.postfix());
        System.out.println("Result: " + tree1.evaluate());

        // Contoh 2: (10 - 5) / (3 + 2)
        System.out.println("\n--- Expression: (10 - 5) / (3 + 2) ---");
        ExpressionTree tree2 = new ExpressionTree();
        tree2.buildFromPostfix("10 5 - 3 2 + /");

        tree2.displayVisual();
        System.out.println("\nInfix: " + tree2.infix());
        System.out.println("Prefix: " + tree2.prefix());
        System.out.println("Postfix: " + tree2.postfix());
        System.out.println("Result: " + tree2.evaluate());

        // Contoh 3: Build dari prefix
        System.out.println("\n--- Build from Prefix: + * 2 3 4 ---");
        System.out.println("Expression: (2 * 3) + 4");
        ExpressionTree tree3 = new ExpressionTree();
        tree3.buildFromPrefix("+ * 2 3 4");

        tree3.displayVisual();
        System.out.println("\nInfix: " + tree3.infix());
        System.out.println("Prefix: " + tree3.prefix());
        System.out.println("Postfix: " + tree3.postfix());
        System.out.println("Result: " + tree3.evaluate());

        // Contoh 4: Ekspresi kompleks
        System.out.println("\n--- Complex Expression: ((2 + 3) * 4) - (6 / 2) ---");
        ExpressionTree tree4 = new ExpressionTree();
        tree4.buildFromPostfix("2 3 + 4 * 6 2 / -");

        tree4.displayVisual();
        System.out.println("\nInfix: " + tree4.infix());
        System.out.println("Result: " + tree4.evaluate());
    }
}
