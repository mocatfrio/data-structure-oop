class ChainNode {
    int key;
    int value;
    ChainNode next;

    public ChainNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

public class HashTableChaining {
    private ChainNode[] table;
    private int capacity;
    private int size;

    public HashTableChaining(int capacity) {
        this.capacity = capacity;
        this.table = new ChainNode[capacity];
        this.size = 0;
    }

    private int hash(int key) {
        return Math.abs(key % capacity);
    }

    public void put(int key, int value) {
        int index = hash(key);
        ChainNode current = table[index];

        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        ChainNode newNode = new ChainNode(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
    }

    public Integer get(int key) {
        int index = hash(key);
        ChainNode current = table[index];

        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(int key) {
        int index = hash(key);
        ChainNode current = table[index];
        ChainNode prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int getSize() {
        return size;
    }

    public void display() {
        System.out.println("\nHash Table (Chaining):");
        for (int i = 0; i < capacity; i++) {
            System.out.print("[" + i + "]");
            ChainNode current = table[i];
            while (current != null) {
                System.out.print(" -> (" + current.key + ":" + current.value + ")");
                current = current.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== HASH TABLE CHAINING ===\n");
        HashTableChaining ht = new HashTableChaining(5);
        ht.put(1, 100);
        ht.put(6, 600);
        ht.put(11, 1100);
        ht.put(2, 200);
        ht.put(7, 700);
        ht.display();
        System.out.println("\nGet key 6: " + ht.get(6));
        System.out.println("Get key 11: " + ht.get(11));
        ht.remove(6);
        System.out.println("\nAfter remove key 6:");
        ht.display();
    }
}
