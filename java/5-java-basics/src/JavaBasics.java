public class JavaBasics {
    public static void main(String[] args) {
        System.out.println("=== TIPE DATA PRIMITIF ===");
        byte b = 127;
        short s = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;
        float f = 3.14f;
        double d = 3.14159265359;
        char c = 'A';
        boolean bool = true;

        System.out.println("byte: " + b);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);
        System.out.println("char: " + c);
        System.out.println("boolean: " + bool);

        System.out.println("\n=== OPERATOR ===");
        int a = 10, x = 3;
        System.out.println("a + x = " + (a + x));
        System.out.println("a - x = " + (a - x));
        System.out.println("a * x = " + (a * x));
        System.out.println("a / x = " + (a / x));
        System.out.println("a % x = " + (a % x));

        System.out.println("\n=== KONDISIONAL ===");
        int nilai = 85;
        if (nilai >= 80) {
            System.out.println("Grade: A");
        } else if (nilai >= 70) {
            System.out.println("Grade: B");
        } else if (nilai >= 60) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: D");
        }

        System.out.println("\n=== PERULANGAN ===");
        System.out.print("For loop: ");
        for (int j = 1; j <= 5; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        System.out.print("While loop: ");
        int k = 1;
        while (k <= 5) {
            System.out.print(k + " ");
            k++;
        }
        System.out.println();

        System.out.println("\n=== ARRAY ===");
        int[] arr = {10, 20, 30, 40, 50};
        System.out.print("Array: ");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
