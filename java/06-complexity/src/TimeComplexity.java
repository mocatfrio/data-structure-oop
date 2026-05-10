public class TimeComplexity {

    public static int constantTime(int[] arr) {
        return arr[0];
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("=== TIME COMPLEXITY EXAMPLES ===\n");

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("O(1) - Constant: arr[0] = " + constantTime(arr));

        System.out.println("\nO(n) - Linear Search: find 7 at index " + linearSearch(arr, 7));

        System.out.println("\nO(log n) - Binary Search: find 7 at index " + binarySearch(arr, 7));

        int[] unsorted = {64, 34, 25, 12, 22, 11, 90};
        System.out.print("\nO(n^2) - Bubble Sort: ");
        bubbleSort(unsorted);
        for (int val : unsorted) System.out.print(val + " ");
        System.out.println();

        System.out.println("\nO(n!) - Factorial(5) = " + factorial(5));

        System.out.print("\nO(2^n) - Fibonacci: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
    }
}
