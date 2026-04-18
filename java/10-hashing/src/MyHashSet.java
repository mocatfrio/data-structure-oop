import java.util.ArrayList;
import java.util.List;

public class MyHashSet<E> {
    private Object[] table;
    private boolean[] occupied;
    private int capacity;
    private int size;

    public MyHashSet() {
        this.capacity = 16;
        this.table = new Object[capacity];
        this.occupied = new boolean[capacity];
        this.size = 0;
    }

    public MyHashSet(int capacity) {
        this.capacity = capacity;
        this.table = new Object[capacity];
        this.occupied = new boolean[capacity];
        this.size = 0;
    }

    private int hash(E element) {
        return (element == null) ? 0 : Math.abs(element.hashCode() % capacity);
    }

    public boolean add(E element) {
        if (contains(element)) return false;

        if (size >= capacity * 0.75) {
            resize();
        }

        int index = hash(element);
        while (occupied[index]) {
            index = (index + 1) % capacity;
        }

        table[index] = element;
        occupied[index] = true;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean contains(E element) {
        int index = hash(element);
        int originalIndex = index;

        while (occupied[index]) {
            E current = (E) table[index];
            if (current == element || (element != null && element.equals(current))) {
                return true;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) break;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(E element) {
        int index = hash(element);
        int originalIndex = index;

        while (occupied[index]) {
            E current = (E) table[index];
            if (current == element || (element != null && element.equals(current))) {
                occupied[index] = false;
                size--;
                return true;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) break;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Object[] oldTable = table;
        boolean[] oldOccupied = occupied;
        int oldCapacity = capacity;

        capacity *= 2;
        table = new Object[capacity];
        occupied = new boolean[capacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldOccupied[i]) {
                add((E) oldTable[i]);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
            occupied[i] = false;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public List<E> toList() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (occupied[i]) {
                list.add((E) table[i]);
            }
        }
        return list;
    }

    public void display() {
        System.out.println("Set: " + toList());
        System.out.println("Size: " + size);
    }

    public static void main(String[] args) {
        System.out.println("=== MY HASHSET ===\n");
        MyHashSet<String> set = new MyHashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple");
        set.display();

        System.out.println("\nContains Banana: " + set.contains("Banana"));
        System.out.println("Contains Grape: " + set.contains("Grape"));

        set.remove("Banana");
        System.out.println("\nAfter remove Banana:");
        set.display();
    }
}
