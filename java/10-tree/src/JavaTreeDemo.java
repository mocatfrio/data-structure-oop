import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NavigableMap;

/**
 * JavaTreeDemo - Demonstrasi TreeMap dan TreeSet dari Java Collections
 *
 * TreeMap dan TreeSet di Java menggunakan Red-Black Tree internally,
 * yang merupakan self-balancing BST seperti AVL Tree.
 *
 * Keuntungan:
 * - Elemen tersortir secara otomatis
 * - Operasi O(log n) untuk insert, delete, search
 * - Menyediakan navigasi (floor, ceiling, higher, lower)
 */
public class JavaTreeDemo {

    public static void main(String[] args) {
        System.out.println("=== JAVA TREE COLLECTIONS ===\n");

        demoTreeSet();
        demoTreeMap();
    }

    public static void demoTreeSet() {
        System.out.println("--- TreeSet Demo ---\n");

        // TreeSet - Set yang tersortir
        TreeSet<Integer> treeSet = new TreeSet<>();

        // Insert elements (tidak perlu urut, otomatis tersortir)
        int[] values = {50, 30, 70, 20, 40, 60, 80, 35, 45};
        System.out.print("Adding: ");
        for (int val : values) {
            System.out.print(val + " ");
            treeSet.add(val);
        }
        System.out.println();

        // TreeSet otomatis tersortir
        System.out.println("TreeSet (sorted): " + treeSet);

        // Basic operations
        System.out.println("\n--- Basic Operations ---");
        System.out.println("Contains 40: " + treeSet.contains(40));
        System.out.println("Contains 25: " + treeSet.contains(25));
        System.out.println("Size: " + treeSet.size());
        System.out.println("First (min): " + treeSet.first());
        System.out.println("Last (max): " + treeSet.last());

        // Navigation operations
        System.out.println("\n--- Navigation Operations ---");
        System.out.println("Floor(45) - largest <= 45: " + treeSet.floor(45));
        System.out.println("Ceiling(45) - smallest >= 45: " + treeSet.ceiling(45));
        System.out.println("Lower(45) - largest < 45: " + treeSet.lower(45));
        System.out.println("Higher(45) - smallest > 45: " + treeSet.higher(45));

        System.out.println("Floor(42) - largest <= 42: " + treeSet.floor(42));
        System.out.println("Ceiling(42) - smallest >= 42: " + treeSet.ceiling(42));

        // Subset views
        System.out.println("\n--- Subset Views ---");
        System.out.println("HeadSet(50) - elements < 50: " + treeSet.headSet(50));
        System.out.println("TailSet(50) - elements >= 50: " + treeSet.tailSet(50));
        System.out.println("SubSet(30, 60) - 30 <= x < 60: " + treeSet.subSet(30, 60));

        // Descending order
        System.out.println("\n--- Descending Order ---");
        NavigableSet<Integer> descending = treeSet.descendingSet();
        System.out.println("Descending: " + descending);

        // Poll operations (removes elements)
        System.out.println("\n--- Poll Operations ---");
        System.out.println("PollFirst (remove min): " + treeSet.pollFirst());
        System.out.println("PollLast (remove max): " + treeSet.pollLast());
        System.out.println("After polls: " + treeSet);

        System.out.println();
    }

    public static void demoTreeMap() {
        System.out.println("--- TreeMap Demo ---\n");

        // TreeMap - Map yang tersortir berdasarkan key
        TreeMap<String, Integer> scores = new TreeMap<>();

        // Insert key-value pairs
        scores.put("Diana", 88);
        scores.put("Alice", 95);
        scores.put("Charlie", 92);
        scores.put("Bob", 87);
        scores.put("Eve", 91);

        // TreeMap otomatis tersortir berdasarkan key
        System.out.println("TreeMap (sorted by key):");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // Basic operations
        System.out.println("\n--- Basic Operations ---");
        System.out.println("Get Alice: " + scores.get("Alice"));
        System.out.println("Contains key 'Bob': " + scores.containsKey("Bob"));
        System.out.println("Contains value 92: " + scores.containsValue(92));
        System.out.println("Size: " + scores.size());
        System.out.println("First key: " + scores.firstKey());
        System.out.println("Last key: " + scores.lastKey());
        System.out.println("First entry: " + scores.firstEntry());
        System.out.println("Last entry: " + scores.lastEntry());

        // Navigation operations
        System.out.println("\n--- Navigation Operations ---");
        System.out.println("FloorKey('Carol'): " + scores.floorKey("Carol"));
        System.out.println("CeilingKey('Carol'): " + scores.ceilingKey("Carol"));
        System.out.println("LowerKey('Charlie'): " + scores.lowerKey("Charlie"));
        System.out.println("HigherKey('Charlie'): " + scores.higherKey("Charlie"));

        System.out.println("FloorEntry('Carol'): " + scores.floorEntry("Carol"));
        System.out.println("CeilingEntry('Carol'): " + scores.ceilingEntry("Carol"));

        // Submap views
        System.out.println("\n--- Submap Views ---");
        System.out.println("HeadMap('Charlie'): " + scores.headMap("Charlie"));
        System.out.println("TailMap('Charlie'): " + scores.tailMap("Charlie"));
        System.out.println("SubMap('Bob', 'Diana'): " + scores.subMap("Bob", "Diana"));

        // Descending order
        System.out.println("\n--- Descending Order ---");
        NavigableMap<String, Integer> descending = scores.descendingMap();
        System.out.println("Descending keys: " + descending.keySet());

        // Key and value collections
        System.out.println("\n--- Collections Views ---");
        System.out.println("Keys: " + scores.keySet());
        System.out.println("Values: " + scores.values());

        // Numeric TreeMap example
        System.out.println("\n--- Numeric Key TreeMap ---");
        TreeMap<Integer, String> idToName = new TreeMap<>();
        idToName.put(102, "Bob");
        idToName.put(101, "Alice");
        idToName.put(105, "Eve");
        idToName.put(103, "Charlie");
        idToName.put(104, "Diana");

        System.out.println("Sorted by ID: " + idToName);
        System.out.println("FloorEntry(103): " + idToName.floorEntry(103));
        System.out.println("CeilingEntry(103): " + idToName.ceilingEntry(103));

        System.out.println("\n--- Time Complexity ---");
        System.out.println("TreeMap/TreeSet operations:");
        System.out.println("  - put/add: O(log n)");
        System.out.println("  - get/contains: O(log n)");
        System.out.println("  - remove: O(log n)");
        System.out.println("  - first/last: O(log n)");
        System.out.println("  - floor/ceiling: O(log n)");
        System.out.println("\nNote: Menggunakan Red-Black Tree internally");
    }
}
