# Modul 14. Shortest Path dan Minimum Spanning Tree

## Daftar Isi
- [1. Pengenalan Shortest Path](#1-pengenalan-shortest-path)
- [2. Dijkstra's Algorithm](#2-dijkstras-algorithm)
  - [2.1 Konsep Dijkstra](#21-konsep-dijkstra)
  - [2.2 Algoritma Dijkstra](#22-algoritma-dijkstra)
  - [2.3 Implementasi Dijkstra](#23-implementasi-dijkstra)
  - [2.4 Kompleksitas Dijkstra](#24-kompleksitas-dijkstra)
- [3. Bellman-Ford Algorithm](#3-bellman-ford-algorithm)
  - [3.1 Konsep Bellman-Ford](#31-konsep-bellman-ford)
  - [3.2 Algoritma Bellman-Ford](#32-algoritma-bellman-ford)
  - [3.3 Implementasi Bellman-Ford](#33-implementasi-bellman-ford)
  - [3.4 Deteksi Negative Cycle](#34-deteksi-negative-cycle)
- [4. Floyd-Warshall Algorithm](#4-floyd-warshall-algorithm)
  - [4.1 Konsep Floyd-Warshall](#41-konsep-floyd-warshall)
  - [4.2 Algoritma Floyd-Warshall](#42-algoritma-floyd-warshall)
  - [4.3 Implementasi Floyd-Warshall](#43-implementasi-floyd-warshall)
- [5. Perbandingan Algoritma Shortest Path](#5-perbandingan-algoritma-shortest-path)
- [6. Minimum Spanning Tree (MST)](#6-minimum-spanning-tree-mst)
  - [6.1 Apa itu MST?](#61-apa-itu-mst)
  - [6.2 Properti MST](#62-properti-mst)
- [7. Prim's Algorithm](#7-prims-algorithm)
  - [7.1 Konsep Prim](#71-konsep-prim)
  - [7.2 Algoritma Prim](#72-algoritma-prim)
  - [7.3 Implementasi Prim](#73-implementasi-prim)
- [8. Kruskal's Algorithm](#8-kruskals-algorithm)
  - [8.1 Konsep Kruskal](#81-konsep-kruskal)
  - [8.2 Union-Find (Disjoint Set)](#82-union-find-disjoint-set)
  - [8.3 Implementasi Kruskal](#83-implementasi-kruskal)
- [9. Perbandingan Prim dan Kruskal](#9-perbandingan-prim-dan-kruskal)
- [10. Latihan Praktikum](#10-latihan-praktikum)
- [11. Soal Latihan di Kelas](#11-soal-latihan-di-kelas)
- [Referensi](#referensi)

---

## 1. Pengenalan Shortest Path

**Shortest Path Problem** adalah masalah menemukan jalur dengan total bobot terkecil antara dua vertex dalam weighted graph.

```
Graph:
    A ──5── B ──3── C
    │       │       │
    2       4       6
    │       │       │
    D ──1── E ──2── F

Shortest path dari A ke F:
- A → D → E → F: 2 + 1 + 2 = 5 ✓ (terpendek)
- A → B → C → F: 5 + 3 + 6 = 14
- A → B → E → F: 5 + 4 + 2 = 11
```

**Jenis-jenis Shortest Path:**

| Jenis | Deskripsi | Algoritma |
|-------|-----------|-----------|
| **Single Source** | Dari satu sumber ke semua vertex | Dijkstra, Bellman-Ford |
| **Single Destination** | Dari semua vertex ke satu tujuan | Reverse edges, lalu Single Source |
| **Single Pair** | Dari satu sumber ke satu tujuan | Dijkstra (berhenti saat sampai) |
| **All Pairs** | Dari setiap vertex ke setiap vertex | Floyd-Warshall |

---

## 2. Dijkstra's Algorithm

### 2.1 Konsep Dijkstra

**Dijkstra's Algorithm** (1956) adalah algoritma **greedy** untuk mencari shortest path dari satu sumber ke semua vertex lainnya. Hanya bekerja untuk graph dengan **bobot non-negatif**.

**Ide Utama:**
- Maintain jarak terpendek sementara ke setiap vertex
- Selalu pilih vertex dengan jarak terpendek yang belum diproses
- Update jarak neighbor jika ditemukan path yang lebih pendek (relaxation)

```
Analogi:
Bayangkan menuangkan air dari source vertex.
Air akan mengalir ke vertex-vertex terdekat terlebih dahulu.
Vertex pertama yang "basah" dari source adalah yang jaraknya paling dekat.
```

### 2.2 Algoritma Dijkstra

```
Dijkstra(graph, source):
    1. Inisialisasi:
       - dist[source] = 0
       - dist[v] = ∞ untuk semua v ≠ source
       - Masukkan semua vertex ke priority queue dengan dist sebagai key

    2. Selama priority queue tidak kosong:
       a. Ambil vertex u dengan dist minimum dari queue
       b. Untuk setiap neighbor v dari u:
          - Jika dist[u] + weight(u,v) < dist[v]:
            - dist[v] = dist[u] + weight(u,v)
            - parent[v] = u
            - Update v di priority queue

    3. Return dist[] dan parent[]
```

**Visualisasi Step-by-Step:**
```
Graph:
    A ──4── B
    │ ╲     │
    2   1   3
    │     ╲ │
    C ──5── D

Dijkstra dari A:

Initial:
  dist = {A:0, B:∞, C:∞, D:∞}
  PQ = [(A,0), (B,∞), (C,∞), (D,∞)]

Step 1: Process A (dist=0)
  Relax A→B: dist[B] = min(∞, 0+4) = 4
  Relax A→C: dist[C] = min(∞, 0+2) = 2
  Relax A→D: dist[D] = min(∞, 0+1) = 1
  dist = {A:0, B:4, C:2, D:1}
  PQ = [(D,1), (C,2), (B,4)]

Step 2: Process D (dist=1)
  Relax D→B: dist[B] = min(4, 1+3) = 4 (tidak berubah)
  Relax D→C: dist[C] = min(2, 1+5) = 2 (tidak berubah)
  dist = {A:0, B:4, C:2, D:1}
  PQ = [(C,2), (B,4)]

Step 3: Process C (dist=2)
  No improvement possible
  PQ = [(B,4)]

Step 4: Process B (dist=4)
  No improvement possible
  PQ = []

Final: dist = {A:0, B:4, C:2, D:1}
```

### 2.3 Implementasi Dijkstra

```java
// File: DijkstraAlgorithm.java
import java.util.*;

class Edge {
    int destination;
    int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class DijkstraAlgorithm {
    private Map<Integer, List<Edge>> adjacencyList;
    private int numVertices;

    public DijkstraAlgorithm(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
        adjacencyList.get(destination).add(new Edge(source, weight)); // Undirected
    }

    // Dijkstra menggunakan Priority Queue
    public int[] dijkstra(int source) {
        int[] dist = new int[numVertices];
        int[] parent = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        // Inisialisasi
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[source] = 0;

        // Priority Queue: (distance, vertex)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );
        pq.offer(new int[]{0, source});

        System.out.println("\nDijkstra dari vertex " + source + ":");
        System.out.println("Step | Process | dist[]");
        System.out.println("-----|---------|--------");

        int step = 0;
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0];
            int u = current[1];

            // Skip jika sudah diproses atau sudah ada jarak lebih baik
            if (visited[u]) continue;
            visited[u] = true;

            System.out.printf("%4d | %7d | %s%n", ++step, u, Arrays.toString(dist));

            // Relax semua neighbor
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                if (!visited[v] && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        // Print hasil
        System.out.println("\nShortest distances from vertex " + source + ":");
        for (int i = 0; i < numVertices; i++) {
            String distStr = dist[i] == Integer.MAX_VALUE ? "∞" : String.valueOf(dist[i]);
            System.out.println("  To vertex " + i + ": " + distStr);
        }

        // Print paths
        System.out.println("\nShortest paths:");
        for (int i = 0; i < numVertices; i++) {
            if (i != source) {
                System.out.print("  " + source + " to " + i + ": ");
                printPath(parent, i);
                System.out.println(" (distance: " + dist[i] + ")");
            }
        }

        return dist;
    }

    // Dijkstra untuk single pair (berhenti saat sampai tujuan)
    public int dijkstraSinglePair(int source, int destination) {
        int[] dist = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );
        pq.offer(new int[]{0, source});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[1];

            if (u == destination) {
                return dist[u]; // Berhenti saat sampai tujuan
            }

            if (visited[u]) continue;
            visited[u] = true;

            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                if (!visited[v] && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        return dist[destination];
    }

    private void printPath(int[] parent, int vertex) {
        if (parent[vertex] == -1) {
            System.out.print(vertex);
            return;
        }
        printPath(parent, parent[vertex]);
        System.out.print(" → " + vertex);
    }

    public void display() {
        System.out.println("\nGraph:");
        for (int v : adjacencyList.keySet()) {
            System.out.print(v + " → ");
            List<String> edges = new ArrayList<>();
            for (Edge e : adjacencyList.get(v)) {
                edges.add(e.destination + "(w=" + e.weight + ")");
            }
            System.out.println(String.join(", ", edges));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DIJKSTRA'S ALGORITHM ===");

        DijkstraAlgorithm graph = new DijkstraAlgorithm(6);

        // Membuat graph
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 3);

        graph.display();

        // Jalankan Dijkstra
        graph.dijkstra(0);

        // Single pair
        System.out.println("\n--- Single Pair ---");
        int distance = graph.dijkstraSinglePair(0, 5);
        System.out.println("Shortest distance from 0 to 5: " + distance);
    }
}
```

**Output:**
```
=== DIJKSTRA'S ALGORITHM ===

Graph:
0 → 1(w=4), 2(w=2)
1 → 0(w=4), 2(w=1), 3(w=5)
2 → 0(w=2), 1(w=1), 3(w=8), 4(w=10)
3 → 1(w=5), 2(w=8), 4(w=2), 5(w=6)
4 → 2(w=10), 3(w=2), 5(w=3)
5 → 3(w=6), 4(w=3)

Dijkstra dari vertex 0:
Step | Process | dist[]
-----|---------|--------
   1 |       0 | [0, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647]
   2 |       2 | [0, 4, 2, 2147483647, 2147483647, 2147483647]
   3 |       1 | [0, 3, 2, 10, 12, 2147483647]
   4 |       3 | [0, 3, 2, 8, 12, 2147483647]
   5 |       4 | [0, 3, 2, 8, 10, 14]
   6 |       5 | [0, 3, 2, 8, 10, 13]

Shortest distances from vertex 0:
  To vertex 0: 0
  To vertex 1: 3
  To vertex 2: 2
  To vertex 3: 8
  To vertex 4: 10
  To vertex 5: 13

Shortest paths:
  0 to 1: 0 → 2 → 1 (distance: 3)
  0 to 2: 0 → 2 (distance: 2)
  0 to 3: 0 → 2 → 1 → 3 (distance: 8)
  0 to 4: 0 → 2 → 1 → 3 → 4 (distance: 10)
  0 to 5: 0 → 2 → 1 → 3 → 4 → 5 (distance: 13)

--- Single Pair ---
Shortest distance from 0 to 5: 13
```

### 2.4 Kompleksitas Dijkstra

| Implementasi | Time Complexity | Space Complexity |
|--------------|-----------------|------------------|
| **Array (naive)** | O(V²) | O(V) |
| **Binary Heap** | O((V + E) log V) | O(V) |
| **Fibonacci Heap** | O(E + V log V) | O(V) |

**Catatan:**
- Binary Heap adalah yang paling praktis
- Untuk dense graph (E ≈ V²), array bisa lebih cepat
- Dijkstra TIDAK bekerja untuk negative edge weights

---

## 3. Bellman-Ford Algorithm

### 3.1 Konsep Bellman-Ford

**Bellman-Ford Algorithm** menyelesaikan shortest path problem untuk graph dengan **bobot negatif** (tapi tanpa negative cycle). Juga bisa mendeteksi negative cycle.

**Ide Utama:**
- Relax semua edge sebanyak V-1 kali
- Jika masih ada improvement setelah V-1 iterasi, ada negative cycle

```
Mengapa V-1 iterasi cukup?
- Shortest path paling banyak memiliki V-1 edge
- Setiap iterasi memperbaiki minimal satu edge di shortest path
- Jadi V-1 iterasi cukup untuk semua shortest path
```

### 3.2 Algoritma Bellman-Ford

```
Bellman-Ford(graph, source):
    1. Inisialisasi:
       - dist[source] = 0
       - dist[v] = ∞ untuk semua v ≠ source

    2. Ulangi V-1 kali:
       - Untuk setiap edge (u, v, weight):
         - Jika dist[u] + weight < dist[v]:
           - dist[v] = dist[u] + weight
           - parent[v] = u

    3. Deteksi negative cycle:
       - Untuk setiap edge (u, v, weight):
         - Jika dist[u] + weight < dist[v]:
           - Return "Negative cycle detected!"

    4. Return dist[] dan parent[]
```

**Visualisasi:**
```
Graph dengan negative edge:
    A ──5── B
    │       │
   -2       3
    │       │
    C ──2── D

Bellman-Ford dari A:

Initial: dist = {A:0, B:∞, C:∞, D:∞}

Iteration 1:
  Relax A→B: dist[B] = 5
  Relax A→C: dist[C] = -2
  Relax B→D: dist[D] = 8
  Relax C→D: dist[D] = min(8, -2+2) = 0

Iteration 2:
  No changes (sudah optimal)

Iteration 3:
  No changes

Final: dist = {A:0, B:5, C:-2, D:0}
```

### 3.3 Implementasi Bellman-Ford

```java
// File: BellmanFordAlgorithm.java
import java.util.*;

class WeightedEdge {
    int source;
    int destination;
    int weight;

    public WeightedEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + source + " → " + destination + ", w=" + weight + ")";
    }
}

public class BellmanFordAlgorithm {
    private int numVertices;
    private List<WeightedEdge> edges;

    public BellmanFordAlgorithm(int numVertices) {
        this.numVertices = numVertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new WeightedEdge(source, destination, weight));
    }

    public int[] bellmanFord(int source) {
        int[] dist = new int[numVertices];
        int[] parent = new int[numVertices];

        // Inisialisasi
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[source] = 0;

        System.out.println("\nBellman-Ford dari vertex " + source + ":");
        System.out.println("Iteration | dist[]");
        System.out.println("----------|--------");
        System.out.println("Initial   | " + formatDist(dist));

        // Relax semua edge V-1 kali
        for (int i = 0; i < numVertices - 1; i++) {
            boolean changed = false;

            for (WeightedEdge edge : edges) {
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    changed = true;
                }
            }

            System.out.printf("    %d     | %s%n", i + 1, formatDist(dist));

            // Early termination jika tidak ada perubahan
            if (!changed) {
                System.out.println("(No changes, stopping early)");
                break;
            }
        }

        // Deteksi negative cycle
        for (WeightedEdge edge : edges) {
            int u = edge.source;
            int v = edge.destination;
            int weight = edge.weight;

            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("\nNegative cycle detected!");
                return null;
            }
        }

        // Print hasil
        System.out.println("\nShortest distances from vertex " + source + ":");
        for (int i = 0; i < numVertices; i++) {
            String distStr = dist[i] == Integer.MAX_VALUE ? "∞" : String.valueOf(dist[i]);
            System.out.println("  To vertex " + i + ": " + distStr);
        }

        // Print paths
        System.out.println("\nShortest paths:");
        for (int i = 0; i < numVertices; i++) {
            if (i != source && dist[i] != Integer.MAX_VALUE) {
                System.out.print("  " + source + " to " + i + ": ");
                printPath(parent, i);
                System.out.println(" (distance: " + dist[i] + ")");
            }
        }

        return dist;
    }

    private String formatDist(int[] dist) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < dist.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(dist[i] == Integer.MAX_VALUE ? "∞" : dist[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    private void printPath(int[] parent, int vertex) {
        if (parent[vertex] == -1) {
            System.out.print(vertex);
            return;
        }
        printPath(parent, parent[vertex]);
        System.out.print(" → " + vertex);
    }

    public void display() {
        System.out.println("\nGraph (Edge List):");
        for (WeightedEdge edge : edges) {
            System.out.println("  " + edge);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== BELLMAN-FORD ALGORITHM ===");

        // Graph dengan negative edge (tanpa negative cycle)
        System.out.println("\n--- Graph dengan Negative Edge ---");
        BellmanFordAlgorithm graph1 = new BellmanFordAlgorithm(5);

        graph1.addEdge(0, 1, 4);
        graph1.addEdge(0, 2, 2);
        graph1.addEdge(1, 2, -3);  // Negative edge
        graph1.addEdge(1, 3, 2);
        graph1.addEdge(2, 3, 3);
        graph1.addEdge(2, 4, 5);
        graph1.addEdge(3, 4, 1);

        graph1.display();
        graph1.bellmanFord(0);

        // Graph dengan negative cycle
        System.out.println("\n\n--- Graph dengan Negative Cycle ---");
        BellmanFordAlgorithm graph2 = new BellmanFordAlgorithm(4);

        graph2.addEdge(0, 1, 1);
        graph2.addEdge(1, 2, -1);
        graph2.addEdge(2, 3, -1);
        graph2.addEdge(3, 1, -1);  // Creates negative cycle: 1 → 2 → 3 → 1 = -3

        graph2.display();
        graph2.bellmanFord(0);
    }
}
```

**Output:**
```
=== BELLMAN-FORD ALGORITHM ===

--- Graph dengan Negative Edge ---

Graph (Edge List):
  (0 → 1, w=4)
  (0 → 2, w=2)
  (1 → 2, w=-3)
  (1 → 3, w=2)
  (2 → 3, w=3)
  (2 → 4, w=5)
  (3 → 4, w=1)

Bellman-Ford dari vertex 0:
Iteration | dist[]
----------|--------
Initial   | [0, ∞, ∞, ∞, ∞]
    1     | [0, 4, 2, 6, 7]
    2     | [0, 4, 1, 4, 5]
    3     | [0, 4, 1, 4, 5]
(No changes, stopping early)

Shortest distances from vertex 0:
  To vertex 0: 0
  To vertex 1: 4
  To vertex 2: 1
  To vertex 3: 4
  To vertex 4: 5

Shortest paths:
  0 to 1: 0 → 1 (distance: 4)
  0 to 2: 0 → 1 → 2 (distance: 1)
  0 to 3: 0 → 1 → 2 → 3 (distance: 4)
  0 to 4: 0 → 1 → 2 → 3 → 4 (distance: 5)


--- Graph dengan Negative Cycle ---

Graph (Edge List):
  (0 → 1, w=1)
  (1 → 2, w=-1)
  (2 → 3, w=-1)
  (3 → 1, w=-1)

Bellman-Ford dari vertex 0:
Iteration | dist[]
----------|--------
Initial   | [0, ∞, ∞, ∞]
    1     | [0, 1, 0, -1]
    2     | [0, -2, -3, -4]
    3     | [0, -5, -6, -7]

Negative cycle detected!
```

### 3.4 Deteksi Negative Cycle

```
Negative Cycle adalah cycle dengan total bobot negatif.
Jika ada, shortest path tidak terdefinisi (bisa infinite negative).

Contoh:
    A ──1── B
    │       │
   -1      -1
    │       │
    C ─-1─ D

Cycle B → D → C → A → B = 1 + (-1) + (-1) + (-1) = -2
Setiap kali melewati cycle, total berkurang 2.
Path bisa terus memendek sampai -∞.
```

**Kompleksitas Bellman-Ford:**
- Time: O(V × E)
- Space: O(V)

---

## 4. Floyd-Warshall Algorithm

### 4.1 Konsep Floyd-Warshall

**Floyd-Warshall Algorithm** menyelesaikan **All-Pairs Shortest Path** - mencari shortest path antara setiap pasangan vertex.

**Ide Utama:**
- Dynamic Programming
- dist[i][j] = jarak terpendek dari i ke j
- Iterasi: untuk setiap vertex k, cek apakah path i→k→j lebih pendek dari path langsung i→j

### 4.2 Algoritma Floyd-Warshall

```
Floyd-Warshall(graph):
    1. Inisialisasi dist[i][j]:
       - dist[i][i] = 0
       - dist[i][j] = weight(i,j) jika ada edge
       - dist[i][j] = ∞ jika tidak ada edge

    2. Untuk setiap vertex k (0 sampai V-1):
       Untuk setiap vertex i (0 sampai V-1):
         Untuk setiap vertex j (0 sampai V-1):
           - Jika dist[i][k] + dist[k][j] < dist[i][j]:
             - dist[i][j] = dist[i][k] + dist[k][j]
             - next[i][j] = next[i][k]  // untuk reconstruct path

    3. Return dist[][]
```

**Visualisasi:**
```
Graph:
    0 ──3── 1
    │ ╲     │
    4   2   1
    │     ╲ │
    2 ──5── 3

Initial dist[][]:
     0    1    2    3
0 [  0    3    4    2  ]
1 [  3    0    ∞    1  ]
2 [  4    ∞    0    5  ]
3 [  2    1    5    0  ]

After k=0:
     0    1    2    3
0 [  0    3    4    2  ]
1 [  3    0    7    1  ]  (1→0→2 = 3+4 = 7)
2 [  4    7    0    5  ]  (2→0→1 = 4+3 = 7)
3 [  2    1    5    0  ]

... (lanjutkan untuk k=1, 2, 3)
```

### 4.3 Implementasi Floyd-Warshall

```java
// File: FloydWarshallAlgorithm.java
import java.util.*;

public class FloydWarshallAlgorithm {
    private int numVertices;
    private int[][] dist;
    private int[][] next;
    private final int INF = 100000000; // Gunakan nilai besar, bukan MAX_VALUE untuk menghindari overflow

    public FloydWarshallAlgorithm(int numVertices) {
        this.numVertices = numVertices;
        this.dist = new int[numVertices][numVertices];
        this.next = new int[numVertices][numVertices];

        // Inisialisasi
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
                next[i][j] = -1;
            }
        }
    }

    public void addEdge(int source, int destination, int weight) {
        dist[source][destination] = weight;
        dist[destination][source] = weight; // Undirected
        next[source][destination] = destination;
        next[destination][source] = source;
    }

    public void addDirectedEdge(int source, int destination, int weight) {
        dist[source][destination] = weight;
        next[source][destination] = destination;
    }

    public void floydWarshall() {
        System.out.println("\n=== FLOYD-WARSHALL ALGORITHM ===");
        System.out.println("\nInitial Distance Matrix:");
        printMatrix(dist);

        // Main algorithm
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }

            System.out.println("\nAfter considering vertex " + k + " as intermediate:");
            printMatrix(dist);
        }

        // Check for negative cycle
        for (int i = 0; i < numVertices; i++) {
            if (dist[i][i] < 0) {
                System.out.println("\nNegative cycle detected!");
                return;
            }
        }

        System.out.println("\nFinal Shortest Distances:");
        printMatrix(dist);

        // Print all shortest paths
        System.out.println("\nAll Shortest Paths:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j && dist[i][j] < INF) {
                    System.out.print("  " + i + " to " + j + ": ");
                    printPath(i, j);
                    System.out.println(" (distance: " + dist[i][j] + ")");
                }
            }
        }
    }

    private void printMatrix(int[][] matrix) {
        System.out.print("     ");
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%5d", i);
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%5d", i);
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] >= INF) {
                    System.out.print("    ∞");
                } else {
                    System.out.printf("%5d", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    private void printPath(int u, int v) {
        if (next[u][v] == -1) {
            System.out.print("(no path)");
            return;
        }

        System.out.print(u);
        while (u != v) {
            u = next[u][v];
            System.out.print(" → " + u);
        }
    }

    // Mendapatkan shortest distance
    public int getDistance(int u, int v) {
        return dist[u][v] >= INF ? -1 : dist[u][v];
    }

    // Mendapatkan path
    public List<Integer> getPath(int u, int v) {
        List<Integer> path = new ArrayList<>();
        if (next[u][v] == -1) return path;

        path.add(u);
        while (u != v) {
            u = next[u][v];
            path.add(u);
        }
        return path;
    }

    public static void main(String[] args) {
        FloydWarshallAlgorithm graph = new FloydWarshallAlgorithm(4);

        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 6);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 4);

        graph.floydWarshall();

        // Query specific paths
        System.out.println("\n--- Query Specific Paths ---");
        System.out.println("Distance from 0 to 3: " + graph.getDistance(0, 3));
        System.out.println("Path from 0 to 3: " + graph.getPath(0, 3));
    }
}
```

**Output:**
```
=== FLOYD-WARSHALL ALGORITHM ===

Initial Distance Matrix:
         0    1    2    3
    0    0    3    6    ∞
    1    3    0    2    1
    2    6    2    0    4
    3    ∞    1    4    0

After considering vertex 0 as intermediate:
         0    1    2    3
    0    0    3    6    ∞
    1    3    0    2    1
    2    6    2    0    4
    3    ∞    1    4    0

After considering vertex 1 as intermediate:
         0    1    2    3
    0    0    3    5    4
    1    3    0    2    1
    2    5    2    0    3
    3    4    1    3    0

After considering vertex 2 as intermediate:
         0    1    2    3
    0    0    3    5    4
    1    3    0    2    1
    2    5    2    0    3
    3    4    1    3    0

After considering vertex 3 as intermediate:
         0    1    2    3
    0    0    3    5    4
    1    3    0    2    1
    2    5    2    0    3
    3    4    1    3    0

Final Shortest Distances:
         0    1    2    3
    0    0    3    5    4
    1    3    0    2    1
    2    5    2    0    3
    3    4    1    3    0

All Shortest Paths:
  0 to 1: 0 → 1 (distance: 3)
  0 to 2: 0 → 1 → 2 (distance: 5)
  0 to 3: 0 → 1 → 3 (distance: 4)
  1 to 0: 1 → 0 (distance: 3)
  1 to 2: 1 → 2 (distance: 2)
  1 to 3: 1 → 3 (distance: 1)
  2 to 0: 2 → 1 → 0 (distance: 5)
  2 to 1: 2 → 1 (distance: 2)
  2 to 3: 2 → 1 → 3 (distance: 3)
  3 to 0: 3 → 1 → 0 (distance: 4)
  3 to 1: 3 → 1 (distance: 1)
  3 to 2: 3 → 1 → 2 (distance: 3)

--- Query Specific Paths ---
Distance from 0 to 3: 4
Path from 0 to 3: [0, 1, 3]
```

**Kompleksitas Floyd-Warshall:**
- Time: O(V³)
- Space: O(V²)

---

## 5. Perbandingan Algoritma Shortest Path

| Algoritma | Time | Space | Negative Edge | Negative Cycle | Use Case |
|-----------|------|-------|---------------|----------------|----------|
| **Dijkstra** | O((V+E) log V) | O(V) | No | No | Single source, positive weights |
| **Bellman-Ford** | O(V × E) | O(V) | Yes | Detects | Single source, negative edges |
| **Floyd-Warshall** | O(V³) | O(V²) | Yes | Detects | All pairs |

**Kapan Menggunakan Apa?**

| Situasi | Algoritma |
|---------|-----------|
| Graph dengan bobot positif, satu sumber | Dijkstra |
| Graph dengan bobot negatif, satu sumber | Bellman-Ford |
| Perlu semua pasangan shortest path | Floyd-Warshall |
| Graph sparse dengan bobot negatif | Bellman-Ford |
| Graph dense, semua pasangan | Floyd-Warshall |

---

## 6. Minimum Spanning Tree (MST)

### 6.1 Apa itu MST?

**Minimum Spanning Tree** adalah subset edge dari connected weighted undirected graph yang:
- Menghubungkan semua vertex (spanning)
- Tidak memiliki cycle (tree)
- Total bobot minimum

```
Graph:                     MST:
    A ──4── B              A ──4── B
    │ ╲     │
    2   3   5
    │     ╲ │
    C ──1── D              C ──1── D ──2── A
                                   │
                                   3 (A-D)

Total semua edge: 4+2+3+5+1 = 15
Total MST: 4+1+2 = 7 atau 3+1+2 = 6 (tergantung edge yang dipilih)

MST yang valid:
    A ──4── B
       ╲
        3
         ╲
    C ──1── D

Total: 4 + 3 + 1 = 8
```

### 6.2 Properti MST

1. **Unique untuk distinct weights**: Jika semua edge weight berbeda, MST unik
2. **V-1 edges**: MST selalu memiliki tepat V-1 edge
3. **Cut Property**: Edge dengan bobot minimum yang memotong dua komponen pasti di MST
4. **Cycle Property**: Edge dengan bobot maksimum di suatu cycle TIDAK di MST

**Aplikasi MST:**
- Network design (kabel, pipa)
- Clustering
- Approximation algorithm (TSP)
- Image segmentation

---

## 7. Prim's Algorithm

### 7.1 Konsep Prim

**Prim's Algorithm** adalah algoritma **greedy** yang membangun MST dengan cara:
- Mulai dari satu vertex
- Tambahkan edge dengan bobot minimum yang menghubungkan MST ke vertex baru
- Ulangi sampai semua vertex termasuk

Mirip dengan Dijkstra, tapi kriterianya berbeda:
- Dijkstra: total jarak dari source
- Prim: bobot edge individual

### 7.2 Algoritma Prim

```
Prim(graph, start):
    1. Inisialisasi:
       - key[start] = 0
       - key[v] = ∞ untuk semua v ≠ start
       - inMST[] = false untuk semua vertex

    2. Selama ada vertex yang belum di MST:
       a. Pilih vertex u dengan key minimum yang belum di MST
       b. Tambahkan u ke MST
       c. Untuk setiap neighbor v dari u:
          - Jika v belum di MST dan weight(u,v) < key[v]:
            - key[v] = weight(u,v)
            - parent[v] = u

    3. Return parent[] (edges of MST)
```

### 7.3 Implementasi Prim

```java
// File: PrimAlgorithm.java
import java.util.*;

public class PrimAlgorithm {
    private int numVertices;
    private Map<Integer, List<int[]>> adjacencyList; // {vertex: [(neighbor, weight), ...]}

    public PrimAlgorithm(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new int[]{destination, weight});
        adjacencyList.get(destination).add(new int[]{source, weight});
    }

    public List<int[]> primMST() {
        int[] key = new int[numVertices];      // Minimum weight edge ke vertex
        int[] parent = new int[numVertices];   // Parent di MST
        boolean[] inMST = new boolean[numVertices];

        // Inisialisasi
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0; // Mulai dari vertex 0

        // Priority Queue: (key, vertex)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );
        pq.offer(new int[]{0, 0});

        System.out.println("\n=== PRIM'S ALGORITHM ===\n");
        System.out.println("Step | Add Vertex | Edge Added | MST Weight So Far");
        System.out.println("-----|------------|------------|------------------");

        int mstWeight = 0;
        int step = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[1];

            if (inMST[u]) continue;
            inMST[u] = true;
            mstWeight += key[u];

            String edgeStr = parent[u] == -1 ? "(start)" :
                            "(" + parent[u] + " - " + u + ", w=" + key[u] + ")";
            System.out.printf("%4d | %10d | %10s | %d%n",
                            ++step, u, edgeStr, mstWeight);

            // Update key untuk neighbor
            for (int[] neighbor : adjacencyList.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.offer(new int[]{weight, v});
                }
            }
        }

        // Build MST edge list
        List<int[]> mstEdges = new ArrayList<>();
        for (int i = 1; i < numVertices; i++) {
            if (parent[i] != -1) {
                mstEdges.add(new int[]{parent[i], i, key[i]});
            }
        }

        // Print MST
        System.out.println("\n--- Minimum Spanning Tree ---");
        System.out.println("Edges:");
        for (int[] edge : mstEdges) {
            System.out.println("  " + edge[0] + " - " + edge[1] + " (weight: " + edge[2] + ")");
        }
        System.out.println("Total MST Weight: " + mstWeight);

        return mstEdges;
    }

    public void display() {
        System.out.println("\nGraph:");
        for (int v : adjacencyList.keySet()) {
            System.out.print(v + " → ");
            List<String> edges = new ArrayList<>();
            for (int[] e : adjacencyList.get(v)) {
                edges.add(e[0] + "(w=" + e[1] + ")");
            }
            System.out.println(String.join(", ", edges));
        }
    }

    public static void main(String[] args) {
        PrimAlgorithm graph = new PrimAlgorithm(6);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 6);

        graph.display();
        graph.primMST();
    }
}
```

**Output:**
```
Graph:
0 → 1(w=4), 2(w=3)
1 → 0(w=4), 2(w=1), 3(w=2)
2 → 0(w=3), 1(w=1), 3(w=4), 4(w=3)
3 → 1(w=2), 2(w=4), 4(w=2), 5(w=1)
4 → 2(w=3), 3(w=2), 5(w=6)
5 → 3(w=1), 4(w=6)

=== PRIM'S ALGORITHM ===

Step | Add Vertex | Edge Added | MST Weight So Far
-----|------------|------------|------------------
   1 |          0 |    (start) | 0
   2 |          2 | (0 - 2, w=3) | 3
   3 |          1 | (2 - 1, w=1) | 4
   4 |          3 | (1 - 3, w=2) | 6
   5 |          5 | (3 - 5, w=1) | 7
   6 |          4 | (3 - 4, w=2) | 9

--- Minimum Spanning Tree ---
Edges:
  0 - 2 (weight: 3)
  2 - 1 (weight: 1)
  1 - 3 (weight: 2)
  3 - 5 (weight: 1)
  3 - 4 (weight: 2)
Total MST Weight: 9
```

**Kompleksitas Prim:**
- Binary Heap: O((V + E) log V)
- Array: O(V²)

---

## 8. Kruskal's Algorithm

### 8.1 Konsep Kruskal

**Kruskal's Algorithm** adalah algoritma **greedy** yang membangun MST dengan cara:
- Sort semua edge berdasarkan bobot
- Tambahkan edge dengan bobot terkecil yang tidak membuat cycle
- Ulangi sampai MST memiliki V-1 edge

### 8.2 Union-Find (Disjoint Set)

Kruskal menggunakan **Union-Find** untuk mendeteksi cycle dengan efisien.

```java
// File: UnionFind.java

public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find dengan path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union by rank
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // Sudah dalam set yang sama (akan membuat cycle)
        }

        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```

### 8.3 Implementasi Kruskal

```java
// File: KruskalAlgorithm.java
import java.util.*;

class KruskalEdge implements Comparable<KruskalEdge> {
    int source;
    int destination;
    int weight;

    public KruskalEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(KruskalEdge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return source + " - " + destination + " (w=" + weight + ")";
    }
}

public class KruskalAlgorithm {
    private int numVertices;
    private List<KruskalEdge> edges;

    public KruskalAlgorithm(int numVertices) {
        this.numVertices = numVertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new KruskalEdge(source, destination, weight));
    }

    public List<KruskalEdge> kruskalMST() {
        System.out.println("\n=== KRUSKAL'S ALGORITHM ===\n");

        // Sort edges by weight
        Collections.sort(edges);
        System.out.println("Sorted Edges:");
        for (int i = 0; i < edges.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + edges.get(i));
        }

        // Initialize Union-Find
        UnionFind uf = new UnionFind(numVertices);

        List<KruskalEdge> mst = new ArrayList<>();
        int mstWeight = 0;

        System.out.println("\nProcessing Edges:");
        System.out.println("Edge | Action | Reason");
        System.out.println("-----|--------|--------");

        for (KruskalEdge edge : edges) {
            if (mst.size() == numVertices - 1) {
                break; // MST sudah lengkap
            }

            int u = edge.source;
            int v = edge.destination;

            // Cek apakah menambahkan edge akan membuat cycle
            if (uf.union(u, v)) {
                mst.add(edge);
                mstWeight += edge.weight;
                System.out.println(edge + " | Added  | No cycle");
            } else {
                System.out.println(edge + " | Skipped | Would create cycle");
            }
        }

        // Print MST
        System.out.println("\n--- Minimum Spanning Tree ---");
        System.out.println("Edges:");
        for (KruskalEdge edge : mst) {
            System.out.println("  " + edge);
        }
        System.out.println("Total MST Weight: " + mstWeight);

        return mst;
    }

    public void display() {
        System.out.println("\nGraph Edges:");
        for (KruskalEdge edge : edges) {
            System.out.println("  " + edge);
        }
    }

    public static void main(String[] args) {
        KruskalAlgorithm graph = new KruskalAlgorithm(6);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 6);

        graph.display();
        graph.kruskalMST();
    }
}
```

**Output:**
```
Graph Edges:
  0 - 1 (w=4)
  0 - 2 (w=3)
  1 - 2 (w=1)
  1 - 3 (w=2)
  2 - 3 (w=4)
  2 - 4 (w=3)
  3 - 4 (w=2)
  3 - 5 (w=1)
  4 - 5 (w=6)

=== KRUSKAL'S ALGORITHM ===

Sorted Edges:
  1. 1 - 2 (w=1)
  2. 3 - 5 (w=1)
  3. 1 - 3 (w=2)
  4. 3 - 4 (w=2)
  5. 0 - 2 (w=3)
  6. 2 - 4 (w=3)
  7. 0 - 1 (w=4)
  8. 2 - 3 (w=4)
  9. 4 - 5 (w=6)

Processing Edges:
Edge | Action | Reason
-----|--------|--------
1 - 2 (w=1) | Added  | No cycle
3 - 5 (w=1) | Added  | No cycle
1 - 3 (w=2) | Added  | No cycle
3 - 4 (w=2) | Added  | No cycle
0 - 2 (w=3) | Added  | No cycle

--- Minimum Spanning Tree ---
Edges:
  1 - 2 (w=1)
  3 - 5 (w=1)
  1 - 3 (w=2)
  3 - 4 (w=2)
  0 - 2 (w=3)
Total MST Weight: 9
```

**Kompleksitas Kruskal:**
- Time: O(E log E) atau O(E log V) (sorting edges)
- Space: O(V) untuk Union-Find

---

## 9. Perbandingan Prim dan Kruskal

| Aspek | Prim | Kruskal |
|-------|------|---------|
| **Approach** | Vertex-centric | Edge-centric |
| **Time (Binary Heap)** | O((V+E) log V) | O(E log E) |
| **Data Structure** | Priority Queue | Union-Find |
| **Graph Type** | Adjacency List | Edge List |
| **Dense Graph** | Lebih baik | - |
| **Sparse Graph** | - | Lebih baik |
| **Disconnected Graph** | Tidak bisa | Bisa (forest) |

**Kapan Menggunakan Apa?**
- **Prim**: Graph dense, sudah dalam bentuk adjacency list
- **Kruskal**: Graph sparse, edge list, atau perlu menangani disconnected graph

---

## 10. Latihan Praktikum

### Latihan 1: Dijkstra dengan Negative Check
Modifikasi Dijkstra untuk mendeteksi dan menolak graph dengan negative edge.

### Latihan 2: Path Reconstruction
Implementasikan fungsi untuk mencetak actual path (bukan hanya distance) untuk Bellman-Ford.

### Latihan 3: MST Weight Calculator
Buat fungsi yang menerima edge list dan mengembalikan total weight MST tanpa menyimpan edge-nya.

### Latihan 4: Second Best MST
Implementasikan algoritma untuk menemukan MST dengan total weight terbesar kedua.

### Latihan 5: Shortest Path di Grid
Implementasikan Dijkstra untuk grid 2D dengan obstacle dan cell berbobot berbeda.

---

## 11. Soal Latihan di Kelas

### Soal 1: Tracing Dijkstra (20 poin)

Diberikan weighted graph:
```
    A ──2── B ──3── C
    │       │       │
    4       1       2
    │       │       │
    D ──5── E ──1── F
```

a) Lakukan Dijkstra dari vertex A. Tuliskan dist[] dan vertex yang diproses di setiap step.

b) Apa shortest path dari A ke F? Berapa jaraknya?

c) Jika edge B-E diubah menjadi -1 (negative), apakah Dijkstra masih bekerja dengan benar? Jelaskan!

### Soal 2: Bellman-Ford (20 poin)

Diberikan directed weighted graph:
```
    A ──2──> B ──1──> C
    │        │        │
    │        │-3      │2
    ↓        ↓        ↓
    D <──4── E <──1── F
```

a) Lakukan Bellman-Ford dari A. Tunjukkan dist[] setelah setiap iterasi.

b) Apakah ada negative cycle? Jelaskan cara mendeteksinya!

c) Bandingkan hasil dengan Dijkstra. Apa perbedaannya?

### Soal 3: Floyd-Warshall (15 poin)

Diberikan graph dengan adjacency matrix:
```
     A  B  C  D
A [  0  3  ∞  7 ]
B [  3  0  2  ∞ ]
C [  ∞  2  0  1 ]
D [  7  ∞  1  0 ]
```

a) Tuliskan matrix hasil setelah mempertimbangkan vertex B sebagai intermediate.

b) Berapa shortest distance dari A ke D?

c) Apa path dari A ke D?

### Soal 4: Minimum Spanning Tree (25 poin)

Diberikan weighted undirected graph:
```
    A ──4── B ──3── C
    │╲      │     ╱│
    2  5    6   7  1
    │    ╲  │ ╱    │
    D ──2── E ──3── F
```

a) Lakukan Prim's algorithm dari vertex A. Tuliskan edge yang ditambahkan di setiap step.

b) Lakukan Kruskal's algorithm. Tuliskan urutan edge yang diproses dan mana yang ditambahkan ke MST.

c) Bandingkan hasil kedua algoritma. Apakah MST-nya sama?

d) Berapa total weight MST?

### Soal 5: Implementasi (20 poin)

a) Implementasikan fungsi `boolean hasNegativeCycle(List<Edge> edges, int numVertices)` menggunakan Bellman-Ford.

b) Implementasikan fungsi `int countMSTEdges(List<Edge> edges, int numVertices)` yang mengembalikan jumlah komponen jika graph disconnected, atau -1 jika connected (V-1 edges di MST).

c) Modifikasi Dijkstra untuk mengembalikan jumlah shortest path (bukan hanya satu path) dari source ke setiap vertex.

---

## Referensi

1. Cormen, T. H., et al. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.
2. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley.
3. [GeeksforGeeks - Dijkstra's Algorithm](https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/)
4. [GeeksforGeeks - Bellman-Ford Algorithm](https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/)
5. [GeeksforGeeks - Floyd-Warshall Algorithm](https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/)
6. [GeeksforGeeks - Prim's Algorithm](https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/)
7. [GeeksforGeeks - Kruskal's Algorithm](https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/)
8. [Visualgo - Single-Source Shortest Path](https://visualgo.net/en/sssp)
9. [Visualgo - Minimum Spanning Tree](https://visualgo.net/en/mst)
10. [CP-Algorithms - Shortest Path](https://cp-algorithms.com/graph/dijkstra.html)
