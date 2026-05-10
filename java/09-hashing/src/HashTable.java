class HashEntry {
    int key;
    int value;
    boolean isDeleted;

    public HashEntry(int key, int value) {
        this.key = key;
        this.value = value;
        this.isDeleted = false;
    }
}

public class HashTable {
    private HashEntry[] table;
    private int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new HashEntry[capacity];
        this.size = 0;
    }

    private int hash(int key) {
        return Math.abs(key % capacity);
    }

    public void put(int key, int value) {
        if (size >= capacity * 0.75) {
            resize();
        }

        int index = hash(key);
        int originalIndex = index;

        while (table[index] != null && !table[index].isDeleted) {
            if (table[index].key == key) {
                table[index].value = value;
                return;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) {
                resize();
                put(key, value);
                return;
            }
        }

        table[index] = new HashEntry(key, value);
        size++;
    }

    public Integer get(int key) {
        int index = hash(key);
        int originalIndex = index;

        while (table[index] != null) {
            if (!table[index].isDeleted && table[index].key == key) {
                return table[index].value;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) break;
        }
        return null;
    }

    public void remove(int key) {
        int index = hash(key);
        int originalIndex = index;

        while (table[index] != null) {
            if (!table[index].isDeleted && table[index].key == key) {
                table[index].isDeleted = true;
                size--;
                return;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) break;
        }
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    private void resize() {
        HashEntry[] oldTable = table;
        capacity *= 2;
        table = new HashEntry[capacity];
        size = 0;

        for (HashEntry entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                put(entry.key, entry.value);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void display() {
        System.out.println("\nHash Table:");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && !table[i].isDeleted) {
                System.out.println("[" + i + "] " + table[i].key + " -> " + table[i].value);
            } else {
                System.out.println("[" + i + "] -");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== HASH TABLE OOP ===\n");
        HashTable ht = new HashTable(10);
        ht.put(1, 100);
        ht.put(2, 200);
        ht.put(11, 1100);
        ht.put(12, 1200);
        ht.display();
        System.out.println("\nGet key 2: " + ht.get(2));
        System.out.println("Get key 11: " + ht.get(11));
        ht.remove(2);
        System.out.println("\nAfter remove key 2:");
        ht.display();
    }
}
