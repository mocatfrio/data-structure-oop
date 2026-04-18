import java.util.Arrays;

public class SpaceComplexity {

    public static int sumConstantSpace(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static int[] copyArrayLinearSpace(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public static int[][] create2DArray(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = i * n + j;
            }
        }
        return matrix;
    }

    public static int factorialRecursive(int n) {
        if (n <= 1) return 1;
        return n * factorialRecursive(n - 1);
    }

    public static int factorialIterative(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== SPACE COMPLEXITY EXAMPLES ===\n");

        int[] arr = {1, 2, 3, 4, 5};

        System.out.println("O(1) Space - Sum: " + sumConstantSpace(arr));

        int[] copy = copyArrayLinearSpace(arr);
        System.out.println("O(n) Space - Copy: " + Arrays.toString(copy));

        int[][] matrix = create2DArray(3);
        System.out.println("\nO(n^2) Space - 2D Array:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nRecursive vs Iterative:");
        System.out.println("Recursive factorial(5) [O(n) space]: " + factorialRecursive(5));
        System.out.println("Iterative factorial(5) [O(1) space]: " + factorialIterative(5));
    }
}
