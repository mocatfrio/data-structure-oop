import java.util.ArrayList;
import java.util.List;

class MapEntry<K, V> {
    K key;
    V value;
    int hash;
    MapEntry<K, V> next;

    public MapEntry(K key, V value, int hash) {
        this.key = key;
        this.value = value;
        this.hash = hash;
        this.next = null;
    }
}

public class MyHashMap<K, V> {
    private MapEntry<K, V>[] buckets;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.buckets = new MapEntry[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.buckets = new MapEntry[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode());
    }

    public V put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }

        int hash = hash(key);
        int index = hash & (capacity - 1);

        MapEntry<K, V> entry = buckets[index];
        while (entry != null) {
            if (entry.hash == hash && (entry.key == key || (key != null && key.equals(entry.key)))) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            entry = entry.next;
        }

        MapEntry<K, V> newEntry = new MapEntry<>(key, value, hash);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
        return null;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = hash & (capacity - 1);

        MapEntry<K, V> entry = buckets[index];
        while (entry != null) {
            if (entry.hash == hash && (entry.key == key || (key != null && key.equals(entry.key)))) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public V remove(K key) {
        int hash = hash(key);
        int index = hash & (capacity - 1);

        MapEntry<K, V> entry = buckets[index];
        MapEntry<K, V> prev = null;

        while (entry != null) {
            if (entry.hash == hash && (entry.key == key || (key != null && key.equals(entry.key)))) {
                if (prev == null) {
                    buckets[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            MapEntry<K, V> entry = buckets[i];
            while (entry != null) {
                if (entry.value == value || (value != null && value.equals(entry.value))) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    public List<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            MapEntry<K, V> entry = buckets[i];
            while (entry != null) {
                keys.add(entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }

    public List<V> values() {
        List<V> vals = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            MapEntry<K, V> entry = buckets[i];
            while (entry != null) {
                vals.add(entry.value);
                entry = entry.next;
            }
        }
        return vals;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        MapEntry<K, V>[] oldBuckets = buckets;
        capacity *= 2;
        buckets = new MapEntry[capacity];
        size = 0;

        for (MapEntry<K, V> bucket : oldBuckets) {
            MapEntry<K, V> entry = bucket;
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
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
            buckets[i] = null;
        }
        size = 0;
    }

    public void display() {
        System.out.println("\nMyHashMap:");
        for (K key : keySet()) {
            System.out.println("  " + key + " -> " + get(key));
        }
        System.out.println("Size: " + size);
    }

    public static void main(String[] args) {
        System.out.println("=== MY HASHMAP ===\n");
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Alice", 95);
        map.put("Bob", 87);
        map.put("Charlie", 92);
        map.put("Diana", 88);
        map.display();

        System.out.println("\nGet Alice: " + map.get("Alice"));
        System.out.println("Contains Bob: " + map.containsKey("Bob"));
        System.out.println("Contains value 92: " + map.containsValue(92));

        map.put("Alice", 98);
        System.out.println("\nAfter update Alice to 98:");
        map.display();

        map.remove("Charlie");
        System.out.println("\nAfter remove Charlie:");
        map.display();

        System.out.println("\nKeys: " + map.keySet());
        System.out.println("Values: " + map.values());
    }
}
