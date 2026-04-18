import java.util.*;

public class JavaMapDemo {
    public static void main(String[] args) {
        System.out.println("=== JAVA HASHMAP ===");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        System.out.println("HashMap: " + scores);
        System.out.println("Get Alice: " + scores.get("Alice"));
        System.out.println("Contains Bob: " + scores.containsKey("Bob"));
        scores.put("Bob", 90);
        System.out.println("After update Bob: " + scores);
        scores.remove("Charlie");
        System.out.println("After remove Charlie: " + scores);

        System.out.println("\n=== ITERATION ===");
        scores.put("Diana", 88);
        System.out.println("Keys: " + scores.keySet());
        System.out.println("Values: " + scores.values());
        System.out.println("Entries:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\n=== JAVA HASHSET ===");
        Set<String> fruits = new HashSet<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Apple");
        System.out.println("HashSet: " + fruits);
        System.out.println("Size: " + fruits.size());
        System.out.println("Contains Banana: " + fruits.contains("Banana"));
        fruits.remove("Banana");
        System.out.println("After remove: " + fruits);

        System.out.println("\n=== LINKEDHASHMAP (Insertion Order) ===");
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("Zebra", 1);
        linkedMap.put("Apple", 2);
        linkedMap.put("Mango", 3);
        System.out.println("LinkedHashMap: " + linkedMap);

        System.out.println("\n=== TREEMAP (Sorted) ===");
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zebra", 1);
        treeMap.put("Apple", 2);
        treeMap.put("Mango", 3);
        System.out.println("TreeMap: " + treeMap);

        System.out.println("\n=== TREESET (Sorted Set) ===");
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(50);
        treeSet.add(20);
        treeSet.add(80);
        treeSet.add(10);
        System.out.println("TreeSet: " + treeSet);
    }
}
