public class HashTableNoOOP {
    static final int TABLE_SIZE = 10;
    static int[] table = new int[TABLE_SIZE];
    static boolean[] occupied = new boolean[TABLE_SIZE];
    static int size = 0;

    public static int hash(int key) {
        return Math.abs(key % TABLE_SIZE);
    }

    public static void insert(int key) {
        if (size >= TABLE_SIZE) {
            System.out.println("Hash Table penuh!");
            return;
        }
        int index = hash(key);
        int originalIndex = index;

        while (occupied[index]) {
            if (table[index] == key) {
                System.out.println("Key " + key + " sudah ada");
                return;
            }
            index = (index + 1) % TABLE_SIZE;
            if (index == originalIndex) {
                System.out.println("Hash Table penuh!");
                return;
            }
        }

        table[index] = key;
        occupied[index] = true;
        size++;
        System.out.println("Insert " + key + " di index " + index);
    }

    public static int search(int key) {
        int index = hash(key);
        int originalIndex = index;

        while (occupied[index]) {
            if (table[index] == key) return index;
            index = (index + 1) % TABLE_SIZE;
            if (index == originalIndex) break;
        }
        return -1;
    }

    public static void delete(int key) {
        int index = search(key);
        if (index == -1) {
            System.out.println("Key " + key + " tidak ditemukan!");
            return;
        }
        occupied[index] = false;
        size--;
        System.out.println("Delete " + key + " dari index " + index);
    }

    public static void display() {
        System.out.println("\nHash Table:");
        for (int i = 0; i < TABLE_SIZE; i++) {
            String value = occupied[i] ? String.valueOf(table[i]) : "-";
            System.out.println("[" + i + "] " + value);
        }
        System.out.println("Size: " + size);
    }

    public static double getLoadFactor() {
        return (double) size / TABLE_SIZE;
    }

    public static void main(String[] args) {
        System.out.println("=== HASH TABLE TANPA OOP ===\n");
        insert(15);
        insert(25);
        insert(35);
        insert(10);
        insert(22);
        display();
        System.out.println("\nLoad Factor: " + getLoadFactor());
        System.out.println("\nSearch 25: index " + search(25));
        delete(25);
        display();
    }
}
