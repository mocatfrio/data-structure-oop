public class MethodDemo {

    public static int add(int a, int b) {
        return a + b;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("=== METHOD OVERLOADING ===");
        System.out.println("add(5, 3) = " + add(5, 3));
        System.out.println("add(5.5, 3.3) = " + add(5.5, 3.3));
        System.out.println("add(1, 2, 3) = " + add(1, 2, 3));

        System.out.println("\n=== REKURSI ===");
        System.out.println("factorial(5) = " + factorial(5));
        System.out.print("fibonacci(10): ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();

        System.out.println("\n=== PRIME CHECK ===");
        for (int i = 1; i <= 20; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println("\n=== PASS BY REFERENCE ===");
        int[] arr = {1, 2, 3};
        System.out.println("Before swap: " + arr[0] + ", " + arr[2]);
        swap(arr, 0, 2);
        System.out.println("After swap: " + arr[0] + ", " + arr[2]);
    }
}
