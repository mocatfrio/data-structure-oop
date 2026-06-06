# Modul 13. Graph Traversal (BFS dan DFS)

## Daftar Isi
- [1. Pengenalan Graph Traversal](#1-pengenalan-graph-traversal)
- [2. Breadth-First Search (BFS)](#2-breadth-first-search-bfs)
  - [2.1 Konsep BFS](#21-konsep-bfs)
  - [2.2 Algoritma BFS](#22-algoritma-bfs)
  - [2.3 Implementasi BFS](#23-implementasi-bfs)
  - [2.4 Kompleksitas BFS](#24-kompleksitas-bfs)
  - [2.5 Aplikasi BFS](#25-aplikasi-bfs)
- [3. Depth-First Search (DFS)](#3-depth-first-search-dfs)
  - [3.1 Konsep DFS](#31-konsep-dfs)
  - [3.2 Algoritma DFS](#32-algoritma-dfs)
  - [3.3 Implementasi DFS](#33-implementasi-dfs)
  - [3.4 Kompleksitas DFS](#34-kompleksitas-dfs)
  - [3.5 Aplikasi DFS](#35-aplikasi-dfs)
- [4. Perbandingan BFS dan DFS](#4-perbandingan-bfs-dan-dfs)
- [5. Aplikasi Traversal: Connected Components](#5-aplikasi-traversal-connected-components)
- [6. Aplikasi Traversal: Cycle Detection](#6-aplikasi-traversal-cycle-detection)
- [7. Aplikasi Traversal: Topological Sort](#7-aplikasi-traversal-topological-sort)
- [8. Aplikasi Traversal: Bipartite Check](#8-aplikasi-traversal-bipartite-check)
- [9. Latihan Praktikum](#9-latihan-praktikum)
- [10. Soal Latihan di Kelas](#10-soal-latihan-di-kelas)
- [Referensi](#referensi)

---

## 1. Pengenalan Graph Traversal

**Graph Traversal** (penjelajahan graph) adalah proses mengunjungi semua vertex dalam graph secara sistematis. Dua algoritma traversal utama:

1. **Breadth-First Search (BFS)**: Menjelajah "melebar" - mengunjungi semua neighbor dulu sebelum ke level berikutnya
2. **Depth-First Search (DFS)**: Menjelajah "mendalam" - mengunjungi satu jalur sampai habis sebelum backtrack

```
Graph:
        0
       /|\
      1 2 3
     /|   |
    4 5   6

BFS dari 0: 0, 1, 2, 3, 4, 5, 6  (level by level)
DFS dari 0: 0, 1, 4, 5, 2, 3, 6  (depth first)
```

**Mengapa Penting?**
- Dasar untuk banyak algoritma graph lainnya
- Shortest path (BFS untuk unweighted)
- Cycle detection
- Connected components
- Topological sorting
- dan banyak lagi...

---

## 2. Breadth-First Search (BFS)

### 2.1 Konsep BFS

BFS menjelajahi graph **level by level**, dimulai dari source vertex. Menggunakan **Queue** untuk menyimpan vertex yang akan dikunjungi.

```
Graph:                    BFS dari vertex 0:
        0
       /|\                Level 0: 0
      1 2 3               Level 1: 1, 2, 3
     /|   |               Level 2: 4, 5, 6
    4 5   6

Urutan kunjungan: 0 → 1 → 2 → 3 → 4 → 5 → 6
```

**Analogi**: Seperti riak air saat melempar batu ke kolam - menyebar ke segala arah secara bertahap.

### 2.2 Algoritma BFS

```
BFS(graph, start):
    1. Buat Queue kosong dan array visited[]
    2. Tandai start sebagai visited
    3. Masukkan start ke Queue
    4. Selama Queue tidak kosong:
        a. Keluarkan vertex v dari Queue
        b. Proses v (cetak, simpan, dll)
        c. Untuk setiap neighbor u dari v:
           - Jika u belum visited:
             - Tandai u sebagai visited
             - Masukkan u ke Queue
```

**Visualisasi Step-by-Step:**
```
Graph:
    0 ─── 1 ─── 3
    │     │
    2 ─── 4

BFS dari 0:

Step 0: Queue = [0], Visited = {0}
        Dequeue 0, visit neighbors 1, 2
        Queue = [1, 2], Visited = {0, 1, 2}
        Output: 0

Step 1: Queue = [1, 2], Visited = {0, 1, 2}
        Dequeue 1, visit neighbors 3, 4
        Queue = [2, 3, 4], Visited = {0, 1, 2, 3, 4}
        Output: 0, 1

Step 2: Queue = [2, 3, 4], Visited = {0, 1, 2, 3, 4}
        Dequeue 2, neighbor 4 already visited
        Queue = [3, 4]
        Output: 0, 1, 2

Step 3: Queue = [3, 4]
        Dequeue 3, no unvisited neighbors
        Queue = [4]
        Output: 0, 1, 2, 3

Step 4: Queue = [4]
        Dequeue 4, no unvisited neighbors
        Queue = []
        Output: 0, 1, 2, 3, 4

Final: 0 → 1 → 2 → 3 → 4
```

### 2.3 Implementasi BFS

```java
// File: BFSTraversal.java
import java.util.*;

public class BFSTraversal {
    private Map<Integer, List<Integer>> adjacencyList;

    public BFSTraversal() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // Undirected
    }

    // BFS Basic - mengembalikan urutan traversal
    public List<Integer> bfs(int start) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);

            // Kunjungi semua neighbor yang belum visited
            for (int neighbor : adjacencyList.getOrDefault(vertex, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    // BFS dengan informasi level/jarak
    public Map<Integer, Integer> bfsWithDistance(int start) {
        Map<Integer, Integer> distance = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        distance.put(start, 0);
        queue.offer(start);

        System.out.println("\nBFS dengan Level:");

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            int currentDist = distance.get(vertex);
            System.out.println("Vertex " + vertex + " at level " + currentDist);

            for (int neighbor : adjacencyList.getOrDefault(vertex, new ArrayList<>())) {
                if (!distance.containsKey(neighbor)) {
                    distance.put(neighbor, currentDist + 1);
                    queue.offer(neighbor);
                }
            }
        }

        return distance;
    }

    // BFS untuk mencari shortest path (unweighted)
    public List<Integer> shortestPath(int start, int end) {
        if (start == end) {
            return Arrays.asList(start);
        }

        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        visited.add(start);
        queue.offer(start);
        parent.put(start, -1);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor : adjacencyList.getOrDefault(vertex, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, vertex);
                    queue.offer(neighbor);

                    if (neighbor == end) {
                        // Reconstruct path
                        return reconstructPath(parent, start, end);
                    }
                }
            }
        }

        return new ArrayList<>(); // Path tidak ditemukan
    }

    private List<Integer> reconstructPath(Map<Integer, Integer> parent, int start, int end) {
        List<Integer> path = new ArrayList<>();
        int current = end;

        while (current != -1) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    // BFS untuk semua vertex (termasuk disconnected components)
    public List<Integer> bfsAll() {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                bfsComponent(vertex, visited, result);
            }
        }

        return result;
    }

    private void bfsComponent(int start, Set<Integer> visited, List<Integer> result) {
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);

            for (int neighbor : adjacencyList.getOrDefault(vertex, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    public void display() {
        System.out.println("\nGraph:");
        for (int vertex : adjacencyList.keySet()) {
            System.out.println(vertex + " → " + adjacencyList.get(vertex));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== BREADTH-FIRST SEARCH (BFS) ===\n");

        BFSTraversal graph = new BFSTraversal();

        // Membuat graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        graph.display();

        // BFS dari vertex 0
        System.out.println("\n--- BFS dari vertex 0 ---");
        List<Integer> traversal = graph.bfs(0);
        System.out.println("Traversal: " + traversal);

        // BFS dengan distance
        System.out.println("\n--- BFS dengan Distance ---");
        Map<Integer, Integer> distances = graph.bfsWithDistance(0);
        System.out.println("Distances from 0: " + distances);

        // Shortest path
        System.out.println("\n--- Shortest Path ---");
        List<Integer> path = graph.shortestPath(0, 5);
        System.out.println("Shortest path from 0 to 5: " + path);
        System.out.println("Path length: " + (path.size() - 1) + " edges");

        // BFS dari vertex yang berbeda
        System.out.println("\n--- BFS dari vertex 3 ---");
        System.out.println("Traversal: " + graph.bfs(3));
    }
}
```

**Output:**
```
=== BREADTH-FIRST SEARCH (BFS) ===

Graph:
0 → [1, 2]
1 → [0, 3, 4]
2 → [0, 4]
3 → [1, 5]
4 → [1, 2, 5]
5 → [3, 4]

--- BFS dari vertex 0 ---
Traversal: [0, 1, 2, 3, 4, 5]

--- BFS dengan Distance ---

BFS dengan Level:
Vertex 0 at level 0
Vertex 1 at level 1
Vertex 2 at level 1
Vertex 3 at level 2
Vertex 4 at level 2
Vertex 5 at level 3
Distances from 0: {0=0, 1=1, 2=1, 3=2, 4=2, 5=3}

--- Shortest Path ---
Shortest path from 0 to 5: [0, 1, 3, 5]
Path length: 3 edges

--- BFS dari vertex 3 ---
Traversal: [3, 1, 5, 0, 4, 2]
```

### 2.4 Kompleksitas BFS

| Aspek | Kompleksitas | Keterangan |
|-------|-------------|------------|
| **Time** | O(V + E) | Setiap vertex dan edge dikunjungi sekali |
| **Space** | O(V) | Queue dan visited set |

**Penjelasan:**
- Setiap vertex masuk queue tepat sekali: O(V)
- Setiap edge diperiksa tepat sekali (atau dua kali untuk undirected): O(E)
- Total: O(V + E)

### 2.5 Aplikasi BFS

**1. Shortest Path (Unweighted Graph)**
```
BFS memberikan shortest path karena mengunjungi level by level.
Vertex pertama yang mencapai tujuan pasti melalui path terpendek.
```

**2. Level Order Traversal**
```
Seperti level order pada tree, BFS mengunjungi semua node
pada level yang sama sebelum ke level berikutnya.
```

**3. Social Network - Finding Connections**
```
"People you may know" (teman dari teman):
- BFS level 1: teman langsung
- BFS level 2: teman dari teman
- BFS level 3: teman dari teman dari teman
```

**4. Web Crawling**
```
Menjelajahi halaman web:
- Start dari satu URL
- Kunjungi semua link di halaman tersebut
- Lalu kunjungi link dari halaman-halaman tersebut
- Dan seterusnya...
```

**5. Broadcasting in Network**
```
Mengirim pesan ke semua node dalam jaringan:
- BFS memastikan pesan sampai ke semua node
- Level memberikan informasi berapa hop yang diperlukan
```

---

## 3. Depth-First Search (DFS)

### 3.1 Konsep DFS

DFS menjelajahi graph **sedalam mungkin** sebelum backtracking. Menggunakan **Stack** (atau rekursi) untuk menyimpan vertex yang akan dikunjungi.

```
Graph:                    DFS dari vertex 0:
        0
       /|\                Mulai dari 0
      1 2 3               Ke 1, lalu ke 4 (sedalamnya)
     /|   |               Backtrack, ke 5
    4 5   6               Backtrack ke 0, ke 2
                          Backtrack, ke 3, lalu ke 6

Satu kemungkinan urutan: 0 → 1 → 4 → 5 → 2 → 3 → 6
(tergantung urutan neighbor)
```

**Analogi**: Seperti menjelajahi labirin - terus maju sampai buntu, lalu kembali dan coba jalur lain.

### 3.2 Algoritma DFS

**Versi Rekursif:**
```
DFS(graph, vertex, visited):
    1. Tandai vertex sebagai visited
    2. Proses vertex
    3. Untuk setiap neighbor u dari vertex:
       - Jika u belum visited:
         - DFS(graph, u, visited)
```

**Versi Iteratif:**
```
DFS(graph, start):
    1. Buat Stack kosong dan array visited[]
    2. Push start ke Stack
    3. Selama Stack tidak kosong:
        a. Pop vertex v dari Stack
        b. Jika v belum visited:
           - Tandai v sebagai visited
           - Proses v
           - Push semua neighbor v ke Stack
```

**Visualisasi Step-by-Step (Rekursif):**
```
Graph:
    0 ─── 1 ─── 3
    │     │
    2 ─── 4

DFS dari 0 (neighbor diproses dari kecil ke besar):

Call DFS(0)
  Visit 0
  Call DFS(1)       [neighbor pertama dari 0]
    Visit 1
    Call DFS(3)     [neighbor pertama dari 1 yang belum visited]
      Visit 3
      (no unvisited neighbors)
      Return
    Call DFS(4)     [neighbor berikutnya dari 1]
      Visit 4
      Call DFS(2)   [neighbor dari 4 yang belum visited]
        Visit 2
        (all neighbors visited)
        Return
      Return
    Return
  Return

Urutan: 0 → 1 → 3 → 4 → 2
```

### 3.3 Implementasi DFS

```java
// File: DFSTraversal.java
import java.util.*;

public class DFSTraversal {
    private Map<Integer, List<Integer>> adjacencyList;

    public DFSTraversal() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // Undirected
    }

    // DFS Rekursif
    public List<Integer> dfsRecursive(int start) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited, result);
        return result;
    }

    private void dfsHelper(int vertex, Set<Integer> visited, List<Integer> result) {
        visited.add(vertex);
        result.add(vertex);

        for (int neighbor : adjacencyList.getOrDefault(vertex, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }

    // DFS Iteratif dengan Stack
    public List<Integer> dfsIterative(int start) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited.contains(vertex)) {
                visited.add(vertex);
                result.add(vertex);

                // Push neighbors dalam urutan terbalik agar diproses dari kecil ke besar
                List<Integer> neighbors = adjacencyList.getOrDefault(vertex, new ArrayList<>());
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return result;
    }

    // DFS dengan tracking path
    public boolean hasPath(int start, int end) {
        Set<Integer> visited = new HashSet<>();
        return hasPathDFS(start, end, visited);
    }

    private boolean hasPathDFS(int current, int end, Set<Integer> visited) {
        if (current == end) {
            return true;
        }

        visited.add(current);

        for (int neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (hasPathDFS(neighbor, end, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    // DFS untuk menemukan semua path
    public List<List<Integer>> findAllPaths(int start, int end) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        findAllPathsDFS(start, end, visited, currentPath, allPaths);
        return allPaths;
    }

    private void findAllPathsDFS(int current, int end, Set<Integer> visited,
                                  List<Integer> currentPath, List<List<Integer>> allPaths) {
        visited.add(current);
        currentPath.add(current);

        if (current == end) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (int neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    findAllPathsDFS(neighbor, end, visited, currentPath, allPaths);
                }
            }
        }

        // Backtrack
        visited.remove(current);
        currentPath.remove(currentPath.size() - 1);
    }

    // DFS untuk semua vertex (termasuk disconnected)
    public List<Integer> dfsAll() {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                dfsHelper(vertex, visited, result);
            }
        }

        return result;
    }

    // DFS dengan pre-order dan post-order timing
    public void dfsWithTiming(int start) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, int[]> timing = new HashMap<>(); // [discovery, finish]
        int[] time = {0};

        System.out.println("\nDFS dengan Timing:");
        dfsTimingHelper(start, visited, timing, time);

        System.out.println("\nTiming (discovery, finish):");
        for (int v : timing.keySet()) {
            int[] t = timing.get(v);
            System.out.println("Vertex " + v + ": (" + t[0] + ", " + t[1] + ")");
        }
    }

    private void dfsTimingHelper(int vertex, Set<Integer> visited,
                                  Map<Integer, int[]> timing, int[] time) {
        visited.add(vertex);
        time[0]++;
        timing.put(vertex, new int[]{time[0], 0});
        System.out.println("Discover vertex " + vertex + " at time " + time[0]);

        for (int neighbor : adjacencyList.getOrDefault(vertex, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsTimingHelper(neighbor, visited, timing, time);
            }
        }

        time[0]++;
        timing.get(vertex)[1] = time[0];
        System.out.println("Finish vertex " + vertex + " at time " + time[0]);
    }

    public void display() {
        System.out.println("\nGraph:");
        for (int vertex : adjacencyList.keySet()) {
            System.out.println(vertex + " → " + adjacencyList.get(vertex));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DEPTH-FIRST SEARCH (DFS) ===\n");

        DFSTraversal graph = new DFSTraversal();

        // Membuat graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        graph.display();

        // DFS Rekursif
        System.out.println("\n--- DFS Rekursif dari vertex 0 ---");
        List<Integer> recursive = graph.dfsRecursive(0);
        System.out.println("Traversal: " + recursive);

        // DFS Iteratif
        System.out.println("\n--- DFS Iteratif dari vertex 0 ---");
        List<Integer> iterative = graph.dfsIterative(0);
        System.out.println("Traversal: " + iterative);

        // Check path existence
        System.out.println("\n--- Path Existence ---");
        System.out.println("Path from 0 to 5? " + graph.hasPath(0, 5));
        System.out.println("Path from 3 to 2? " + graph.hasPath(3, 2));

        // Find all paths
        System.out.println("\n--- All Paths from 0 to 5 ---");
        List<List<Integer>> allPaths = graph.findAllPaths(0, 5);
        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }

        // DFS dengan timing
        graph.dfsWithTiming(0);
    }
}
```

**Output:**
```
=== DEPTH-FIRST SEARCH (DFS) ===

Graph:
0 → [1, 2]
1 → [0, 3, 4]
2 → [0, 4]
3 → [1, 5]
4 → [1, 2, 5]
5 → [3, 4]

--- DFS Rekursif dari vertex 0 ---
Traversal: [0, 1, 3, 5, 4, 2]

--- DFS Iteratif dari vertex 0 ---
Traversal: [0, 1, 3, 5, 4, 2]

--- Path Existence ---
Path from 0 to 5? true
Path from 3 to 2? true

--- All Paths from 0 to 5 ---
[0, 1, 3, 5]
[0, 1, 4, 5]
[0, 2, 4, 5]
[0, 2, 4, 1, 3, 5]

--- DFS dengan Timing ---

DFS dengan Timing:
Discover vertex 0 at time 1
Discover vertex 1 at time 2
Discover vertex 3 at time 3
Discover vertex 5 at time 4
Discover vertex 4 at time 5
Discover vertex 2 at time 6
Finish vertex 2 at time 7
Finish vertex 4 at time 8
Finish vertex 5 at time 9
Finish vertex 3 at time 10
Finish vertex 1 at time 11
Finish vertex 0 at time 12

Timing (discovery, finish):
Vertex 0: (1, 12)
Vertex 1: (2, 11)
Vertex 2: (6, 7)
Vertex 3: (3, 10)
Vertex 4: (5, 8)
Vertex 5: (4, 9)
```

### 3.4 Kompleksitas DFS

| Aspek | Kompleksitas | Keterangan |
|-------|-------------|------------|
| **Time** | O(V + E) | Sama seperti BFS |
| **Space** | O(V) | Stack/recursion depth dan visited set |

**Catatan tentang Space:**
- Rekursif: O(V) untuk call stack dalam worst case (graph linear)
- Iteratif: O(V) untuk explicit stack

### 3.5 Aplikasi DFS

**1. Cycle Detection**
```
DFS bisa mendeteksi cycle dengan memeriksa back edge
(edge ke vertex yang sudah ada di current path)
```

**2. Topological Sort**
```
Untuk DAG (Directed Acyclic Graph), DFS bisa menghasilkan
urutan topological dengan menyimpan finish time
```

**3. Connected Components**
```
Setiap kali DFS dipanggil dari vertex yang belum visited,
itu adalah komponen baru
```

**4. Path Finding**
```
DFS bisa menemukan SEMUA path antara dua vertex
(BFS hanya menemukan shortest path)
```

**5. Maze Solving**
```
Backtracking dalam DFS cocok untuk mencari jalan keluar labirin:
- Coba satu jalur sampai mentok
- Backtrack dan coba jalur lain
```

**6. Strongly Connected Components (SCC)**
```
Algoritma Kosaraju dan Tarjan menggunakan DFS
untuk menemukan SCC dalam directed graph
```

---

## 4. Perbandingan BFS dan DFS

| Aspek | BFS | DFS |
|-------|-----|-----|
| **Struktur Data** | Queue (FIFO) | Stack (LIFO) / Rekursi |
| **Strategi** | Level by level | Depth first |
| **Shortest Path** | Ya (unweighted) | Tidak dijamin |
| **Memory** | O(V) - bisa besar untuk graph lebar | O(V) - bisa besar untuk graph dalam |
| **Complete** | Ya | Ya |
| **Finding All Paths** | Tidak efisien | Lebih cocok |
| **Space untuk Tree** | O(width) | O(height) |

**Kapan Menggunakan BFS:**
- Mencari shortest path (unweighted)
- Level order traversal
- Finding nearest neighbor
- Broadcasting
- Bipartite check

**Kapan Menggunakan DFS:**
- Topological sort
- Cycle detection
- Path finding (semua path)
- Maze solving
- Strongly connected components
- Backtracking problems

```
Graph berbentuk Tree:
           1
         / | \
        2  3  4
       /|     |\
      5 6     7 8

BFS: Bagus jika solusi di level atas (width = 4, height = 3)
     Space: O(4) untuk queue

DFS: Bagus jika solusi di bawah (perlu explore dalam)
     Space: O(3) untuk stack

Graph Berbentuk Chain:
1 - 2 - 3 - 4 - 5 - 6 - 7 - 8

BFS: Space O(1) karena width = 1
DFS: Space O(8) karena depth = 8 (rekursi)
```

---

## 5. Aplikasi Traversal: Connected Components

**Connected Components** adalah sub-graph dimana setiap vertex terhubung satu sama lain.

```
Graph dengan 3 Connected Components:

Component 1:    Component 2:    Component 3:
  0 ─── 1         4 ─── 5           7
  │     │         │
  2 ─── 3         6
```

```java
// File: ConnectedComponents.java
import java.util.*;

public class ConnectedComponents {
    private Map<Integer, List<Integer>> adjacencyList;

    public ConnectedComponents() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    // Menemukan semua connected components menggunakan BFS
    public List<List<Integer>> findComponentsBFS() {
        List<List<Integer>> components = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                List<Integer> component = new ArrayList<>();
                bfs(vertex, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private void bfs(int start, Set<Integer> visited, List<Integer> component) {
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            component.add(vertex);

            for (int neighbor : adjacencyList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    // Menemukan semua connected components menggunakan DFS
    public List<List<Integer>> findComponentsDFS() {
        List<List<Integer>> components = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                List<Integer> component = new ArrayList<>();
                dfs(vertex, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private void dfs(int vertex, Set<Integer> visited, List<Integer> component) {
        visited.add(vertex);
        component.add(vertex);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, component);
            }
        }
    }

    // Cek apakah graph connected
    public boolean isConnected() {
        if (adjacencyList.isEmpty()) return true;

        int start = adjacencyList.keySet().iterator().next();
        Set<Integer> visited = new HashSet<>();
        dfs(start, visited, new ArrayList<>());

        return visited.size() == adjacencyList.size();
    }

    // Jumlah connected components
    public int countComponents() {
        return findComponentsDFS().size();
    }

    public static void main(String[] args) {
        System.out.println("=== CONNECTED COMPONENTS ===\n");

        ConnectedComponents graph = new ConnectedComponents();

        // Component 1
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(0, 3);

        // Component 2
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);

        // Component 3 (isolated vertex)
        graph.addVertex(7);

        System.out.println("Graph:");
        for (int v : graph.adjacencyList.keySet()) {
            System.out.println(v + " → " + graph.adjacencyList.get(v));
        }

        System.out.println("\n--- Finding Components ---");
        List<List<Integer>> components = graph.findComponentsDFS();
        System.out.println("Number of components: " + components.size());
        for (int i = 0; i < components.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + components.get(i));
        }

        System.out.println("\nIs graph connected? " + graph.isConnected());
    }
}
```

**Output:**
```
=== CONNECTED COMPONENTS ===

Graph:
0 → [1, 3]
1 → [0, 2]
2 → [1, 3]
3 → [2, 0]
4 → [5]
5 → [4, 6]
6 → [5]
7 → []

--- Finding Components ---
Number of components: 3
Component 1: [0, 1, 2, 3]
Component 2: [4, 5, 6]
Component 3: [7]

Is graph connected? false
```

---

## 6. Aplikasi Traversal: Cycle Detection

### 6.1 Cycle Detection pada Undirected Graph

```java
// File: CycleDetectionUndirected.java
import java.util.*;

public class CycleDetectionUndirected {
    private Map<Integer, List<Integer>> adjacencyList;

    public CycleDetectionUndirected() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    // Deteksi cycle menggunakan DFS
    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                if (hasCycleDFS(vertex, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleDFS(int vertex, int parent, Set<Integer> visited) {
        visited.add(vertex);

        for (int neighbor : adjacencyList.get(vertex)) {
            // Jika neighbor belum visited, explore
            if (!visited.contains(neighbor)) {
                if (hasCycleDFS(neighbor, vertex, visited)) {
                    return true;
                }
            }
            // Jika neighbor sudah visited dan bukan parent, ada cycle
            else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    // Deteksi cycle menggunakan BFS (Union-Find concept)
    public boolean hasCycleBFS() {
        Set<Integer> visited = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                if (hasCycleBFSHelper(vertex, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleBFSHelper(int start, Set<Integer> visited) {
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);
        parent.put(start, -1);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor : adjacencyList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, vertex);
                    queue.offer(neighbor);
                } else if (parent.get(vertex) != neighbor) {
                    // neighbor sudah visited dan bukan parent -> cycle!
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== CYCLE DETECTION (UNDIRECTED) ===\n");

        // Graph dengan cycle
        System.out.println("--- Graph dengan Cycle ---");
        CycleDetectionUndirected graph1 = new CycleDetectionUndirected();
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 0);  // Ini membuat cycle
        graph1.addEdge(2, 3);

        System.out.println("Has cycle (DFS)? " + graph1.hasCycle());
        System.out.println("Has cycle (BFS)? " + graph1.hasCycleBFS());

        // Graph tanpa cycle (Tree)
        System.out.println("\n--- Graph tanpa Cycle (Tree) ---");
        CycleDetectionUndirected graph2 = new CycleDetectionUndirected();
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(1, 4);

        System.out.println("Has cycle (DFS)? " + graph2.hasCycle());
        System.out.println("Has cycle (BFS)? " + graph2.hasCycleBFS());
    }
}
```

### 6.2 Cycle Detection pada Directed Graph

```java
// File: CycleDetectionDirected.java
import java.util.*;

public class CycleDetectionDirected {
    private Map<Integer, List<Integer>> adjacencyList;

    public CycleDetectionDirected() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
    }

    // Deteksi cycle menggunakan DFS dengan 3 warna
    // WHITE (0) = belum dikunjungi
    // GRAY (1) = sedang dalam proses (di stack rekursi)
    // BLACK (2) = selesai diproses
    public boolean hasCycle() {
        Map<Integer, Integer> color = new HashMap<>();

        // Inisialisasi semua vertex dengan WHITE
        for (int vertex : adjacencyList.keySet()) {
            color.put(vertex, 0);
        }

        for (int vertex : adjacencyList.keySet()) {
            if (color.get(vertex) == 0) {
                if (hasCycleDFS(vertex, color)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCycleDFS(int vertex, Map<Integer, Integer> color) {
        // Mark as GRAY (sedang diproses)
        color.put(vertex, 1);

        for (int neighbor : adjacencyList.get(vertex)) {
            // Jika neighbor GRAY, ada back edge (cycle)
            if (color.get(neighbor) == 1) {
                return true;
            }
            // Jika neighbor WHITE, explore
            if (color.get(neighbor) == 0) {
                if (hasCycleDFS(neighbor, color)) {
                    return true;
                }
            }
        }

        // Mark as BLACK (selesai)
        color.put(vertex, 2);
        return false;
    }

    // Versi dengan tracking cycle path
    public List<Integer> findCycle() {
        Map<Integer, Integer> color = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        List<Integer> cycle = new ArrayList<>();

        for (int vertex : adjacencyList.keySet()) {
            color.put(vertex, 0);
            parent.put(vertex, -1);
        }

        for (int vertex : adjacencyList.keySet()) {
            if (color.get(vertex) == 0) {
                int cycleStart = findCycleDFS(vertex, color, parent);
                if (cycleStart != -1) {
                    // Reconstruct cycle
                    int current = parent.get(cycleStart);
                    cycle.add(cycleStart);
                    while (current != cycleStart) {
                        cycle.add(current);
                        current = parent.get(current);
                    }
                    cycle.add(cycleStart);
                    Collections.reverse(cycle);
                    return cycle;
                }
            }
        }

        return cycle; // empty if no cycle
    }

    private int findCycleDFS(int vertex, Map<Integer, Integer> color,
                              Map<Integer, Integer> parent) {
        color.put(vertex, 1);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (color.get(neighbor) == 1) {
                parent.put(neighbor, vertex);
                return neighbor; // Found cycle
            }
            if (color.get(neighbor) == 0) {
                parent.put(neighbor, vertex);
                int result = findCycleDFS(neighbor, color, parent);
                if (result != -1) return result;
            }
        }

        color.put(vertex, 2);
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=== CYCLE DETECTION (DIRECTED) ===\n");

        // Graph dengan cycle
        System.out.println("--- Graph dengan Cycle ---");
        CycleDetectionDirected graph1 = new CycleDetectionDirected();
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 1);  // Back edge: cycle 1->2->3->1

        System.out.println("Has cycle? " + graph1.hasCycle());
        System.out.println("Cycle: " + graph1.findCycle());

        // DAG (tanpa cycle)
        System.out.println("\n--- DAG (tanpa Cycle) ---");
        CycleDetectionDirected graph2 = new CycleDetectionDirected();
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(2, 3);

        System.out.println("Has cycle? " + graph2.hasCycle());
    }
}
```

**Output:**
```
=== CYCLE DETECTION (DIRECTED) ===

--- Graph dengan Cycle ---
Has cycle? true
Cycle: [1, 2, 3, 1]

--- DAG (tanpa Cycle) ---
Has cycle? false
```

---

## 7. Aplikasi Traversal: Topological Sort

**Topological Sort** adalah pengurutan vertex pada DAG sehingga untuk setiap edge (u, v), vertex u muncul sebelum v.

```
DAG:
  5 → 0 ← 4
  ↓       ↓
  2 → 3 → 1

Beberapa kemungkinan topological order:
- 4, 5, 0, 2, 3, 1
- 4, 5, 2, 0, 3, 1
- 5, 4, 0, 2, 3, 1
- 5, 4, 2, 0, 3, 1
- 5, 2, 4, 0, 3, 1
- ...
```

```java
// File: TopologicalSort.java
import java.util.*;

public class TopologicalSort {
    private Map<Integer, List<Integer>> adjacencyList;

    public TopologicalSort() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
    }

    // Topological Sort menggunakan DFS
    public List<Integer> topologicalSortDFS() {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortDFSHelper(vertex, visited, stack);
            }
        }

        // Pop semua dari stack untuk mendapat urutan
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private void topologicalSortDFSHelper(int vertex, Set<Integer> visited, Stack<Integer> stack) {
        visited.add(vertex);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                topologicalSortDFSHelper(neighbor, visited, stack);
            }
        }

        // Push ke stack setelah semua descendant selesai
        stack.push(vertex);
    }

    // Topological Sort menggunakan Kahn's Algorithm (BFS)
    public List<Integer> topologicalSortBFS() {
        // Hitung in-degree setiap vertex
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int vertex : adjacencyList.keySet()) {
            inDegree.put(vertex, 0);
        }
        for (int vertex : adjacencyList.keySet()) {
            for (int neighbor : adjacencyList.get(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Masukkan semua vertex dengan in-degree 0 ke queue
        Queue<Integer> queue = new LinkedList<>();
        for (int vertex : inDegree.keySet()) {
            if (inDegree.get(vertex) == 0) {
                queue.offer(vertex);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);

            // Kurangi in-degree neighbor
            for (int neighbor : adjacencyList.get(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Jika result.size() != jumlah vertex, ada cycle
        if (result.size() != adjacencyList.size()) {
            throw new RuntimeException("Graph has cycle! Cannot do topological sort.");
        }

        return result;
    }

    // Menampilkan semua kemungkinan topological order
    public void printAllTopologicalOrders() {
        // Hitung in-degree
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int vertex : adjacencyList.keySet()) {
            inDegree.put(vertex, 0);
        }
        for (int vertex : adjacencyList.keySet()) {
            for (int neighbor : adjacencyList.get(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        System.out.println("All Topological Orders:");
        printAllOrdersHelper(inDegree, result, visited);
    }

    private void printAllOrdersHelper(Map<Integer, Integer> inDegree,
                                       List<Integer> result, Set<Integer> visited) {
        boolean allVisited = true;

        for (int vertex : adjacencyList.keySet()) {
            if (inDegree.get(vertex) == 0 && !visited.contains(vertex)) {
                allVisited = false;

                // Pilih vertex ini
                visited.add(vertex);
                result.add(vertex);

                // Kurangi in-degree neighbor
                for (int neighbor : adjacencyList.get(vertex)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                }

                // Rekursi
                printAllOrdersHelper(inDegree, result, visited);

                // Backtrack
                visited.remove(vertex);
                result.remove(result.size() - 1);
                for (int neighbor : adjacencyList.get(vertex)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) + 1);
                }
            }
        }

        if (allVisited) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== TOPOLOGICAL SORT ===\n");

        TopologicalSort graph = new TopologicalSort();

        // Contoh: Course prerequisites
        // 5 dan 4 tidak punya prerequisite
        // 0 butuh 5 dan 4
        // 2 butuh 5
        // 3 butuh 2
        // 1 butuh 3 dan 4

        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Graph (Course Prerequisites):");
        System.out.println("5 → 0, 2");
        System.out.println("4 → 0, 1");
        System.out.println("2 → 3");
        System.out.println("3 → 1");
        System.out.println("0, 1 → (none)");

        System.out.println("\n--- Topological Sort (DFS) ---");
        List<Integer> dfsSorted = graph.topologicalSortDFS();
        System.out.println("Order: " + dfsSorted);

        System.out.println("\n--- Topological Sort (BFS/Kahn's) ---");
        List<Integer> bfsSorted = graph.topologicalSortBFS();
        System.out.println("Order: " + bfsSorted);

        System.out.println("\n--- All Possible Orders ---");
        graph.printAllTopologicalOrders();
    }
}
```

**Output:**
```
=== TOPOLOGICAL SORT ===

Graph (Course Prerequisites):
5 → 0, 2
4 → 0, 1
2 → 3
3 → 1
0, 1 → (none)

--- Topological Sort (DFS) ---
Order: [5, 4, 2, 3, 1, 0]

--- Topological Sort (BFS/Kahn's) ---
Order: [4, 5, 0, 2, 3, 1]

--- All Possible Orders ---
[4, 5, 0, 2, 3, 1]
[4, 5, 2, 0, 3, 1]
[4, 5, 2, 3, 0, 1]
[4, 5, 2, 3, 1, 0]
[5, 2, 3, 4, 0, 1]
[5, 2, 3, 4, 1, 0]
[5, 2, 4, 0, 3, 1]
[5, 2, 4, 3, 0, 1]
[5, 2, 4, 3, 1, 0]
[5, 4, 0, 2, 3, 1]
[5, 4, 2, 0, 3, 1]
[5, 4, 2, 3, 0, 1]
[5, 4, 2, 3, 1, 0]
```

---

## 8. Aplikasi Traversal: Bipartite Check

**Bipartite Graph** adalah graph yang vertex-nya bisa dibagi menjadi 2 grup dimana tidak ada edge antar vertex dalam grup yang sama.

```
Bipartite:              NOT Bipartite:
  A ─── 1                 A ─── B
  │     │                 │   ╱
  │     │                 │ ╱
  B ─── 2                 C
  │
  C

Grup 1: {A, B, C}        Cycle dengan panjang ganjil
Grup 2: {1, 2}           tidak bisa di-bipartite
```

```java
// File: BipartiteCheck.java
import java.util.*;

public class BipartiteCheck {
    private Map<Integer, List<Integer>> adjacencyList;

    public BipartiteCheck() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    // Check bipartite menggunakan BFS (2-coloring)
    public boolean isBipartiteBFS() {
        Map<Integer, Integer> color = new HashMap<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!color.containsKey(vertex)) {
                if (!bfsCheck(vertex, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean bfsCheck(int start, Map<Integer, Integer> color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color.put(start, 0); // Warnai dengan warna 0

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            int currentColor = color.get(vertex);

            for (int neighbor : adjacencyList.get(vertex)) {
                if (!color.containsKey(neighbor)) {
                    // Warnai dengan warna berbeda
                    color.put(neighbor, 1 - currentColor);
                    queue.offer(neighbor);
                } else if (color.get(neighbor) == currentColor) {
                    // Neighbor punya warna sama -> tidak bipartite
                    return false;
                }
            }
        }

        return true;
    }

    // Check bipartite menggunakan DFS
    public boolean isBipartiteDFS() {
        Map<Integer, Integer> color = new HashMap<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!color.containsKey(vertex)) {
                if (!dfsCheck(vertex, 0, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfsCheck(int vertex, int c, Map<Integer, Integer> color) {
        color.put(vertex, c);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!color.containsKey(neighbor)) {
                if (!dfsCheck(neighbor, 1 - c, color)) {
                    return false;
                }
            } else if (color.get(neighbor) == c) {
                return false;
            }
        }

        return true;
    }

    // Mendapatkan pembagian grup jika bipartite
    public List<List<Integer>> getBipartition() {
        Map<Integer, Integer> color = new HashMap<>();
        List<Integer> group0 = new ArrayList<>();
        List<Integer> group1 = new ArrayList<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!color.containsKey(vertex)) {
                if (!bfsCheck(vertex, color)) {
                    return null; // Not bipartite
                }
            }
        }

        for (int vertex : color.keySet()) {
            if (color.get(vertex) == 0) {
                group0.add(vertex);
            } else {
                group1.add(vertex);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(group0);
        result.add(group1);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== BIPARTITE CHECK ===\n");

        // Graph Bipartite
        System.out.println("--- Bipartite Graph ---");
        BipartiteCheck graph1 = new BipartiteCheck();
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 3);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);

        System.out.println("Is bipartite (BFS)? " + graph1.isBipartiteBFS());
        System.out.println("Is bipartite (DFS)? " + graph1.isBipartiteDFS());

        List<List<Integer>> partition = graph1.getBipartition();
        if (partition != null) {
            System.out.println("Group 0: " + partition.get(0));
            System.out.println("Group 1: " + partition.get(1));
        }

        // Graph NOT Bipartite (triangle)
        System.out.println("\n--- Non-Bipartite Graph (Triangle) ---");
        BipartiteCheck graph2 = new BipartiteCheck();
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 0);

        System.out.println("Is bipartite (BFS)? " + graph2.isBipartiteBFS());
        System.out.println("Is bipartite (DFS)? " + graph2.isBipartiteDFS());
    }
}
```

**Output:**
```
=== BIPARTITE CHECK ===

--- Bipartite Graph ---
Is bipartite (BFS)? true
Is bipartite (DFS)? true
Group 0: [0, 2]
Group 1: [1, 3]

--- Non-Bipartite Graph (Triangle) ---
Is bipartite (BFS)? false
Is bipartite (DFS)? false
```

---

## 9. Latihan Praktikum

### Latihan 1: Level Sum dalam BFS
Modifikasi BFS untuk menghitung jumlah nilai vertex di setiap level.

### Latihan 2: DFS dengan Path Printing
Modifikasi DFS untuk mencetak path dari source ke setiap vertex yang dikunjungi.

### Latihan 3: Shortest Path dengan Obstacle
Implementasikan BFS untuk grid 2D dengan obstacle, dimana harus menemukan shortest path dari sudut kiri atas ke sudut kanan bawah.

### Latihan 4: Deteksi Bridge
Implementasikan algoritma untuk menemukan "bridge" (edge yang jika dihapus akan membuat graph disconnected).

### Latihan 5: Course Scheduler
Diberikan daftar course dan prerequisite-nya, gunakan topological sort untuk menentukan urutan mengambil course. Berikan error jika ada circular dependency.

---

## 10. Soal Latihan di Kelas

### Soal 1: Tracing BFS dan DFS (20 poin)

Diberikan graph:
```
    0 ─── 1 ─── 2
    │     │     │
    3 ─── 4 ─── 5
          │
          6
```

a) Lakukan BFS dari vertex 0. Tuliskan urutan kunjungan dan isi queue di setiap step.

b) Lakukan DFS (rekursif) dari vertex 0. Tuliskan urutan kunjungan dan call stack di setiap step.

c) Hitung jarak (jumlah edge) dari vertex 0 ke semua vertex lainnya.

### Soal 2: Connected Components (15 poin)

Diberikan edge list:
```
(0,1), (1,2), (3,4), (5,6), (6,7), (7,5)
```

a) Gambarkan graph-nya

b) Sebutkan semua connected components

c) Apakah component yang mengandung vertex 5 memiliki cycle? Jelaskan!

### Soal 3: Cycle Detection (20 poin)

Diberikan directed graph:
```
    A → B → C
    ↓   ↓   ↓
    D → E → F
        ↓
        A
```

a) Apakah graph ini memiliki cycle? Jika ya, sebutkan!

b) Jelaskan algoritma 3-color untuk mendeteksi cycle. Warna apa yang diberikan ke setiap vertex saat DFS?

c) Jika edge E→A dihapus, apakah masih ada cycle?

### Soal 4: Topological Sort (20 poin)

Diberikan daftar mata kuliah dan prerequisite:
```
- Kalkulus: tidak ada prerequisite
- Fisika: butuh Kalkulus
- Algoritma: tidak ada prerequisite
- Struktur Data: butuh Algoritma
- Machine Learning: butuh Struktur Data dan Kalkulus
- Computer Vision: butuh Machine Learning dan Fisika
```

a) Gambarkan DAG untuk dependency ini

b) Berikan satu urutan valid untuk mengambil semua mata kuliah

c) Apa yang terjadi jika Machine Learning juga menjadi prerequisite untuk Algoritma?

### Soal 5: Implementasi (25 poin)

a) Implementasikan method `int shortestDistance(int u, int v)` yang mengembalikan jarak terpendek (jumlah edge) antara dua vertex. Return -1 jika tidak ada path.

b) Implementasikan method `boolean isTree()` yang memeriksa apakah undirected graph adalah tree (connected dan tidak memiliki cycle).

c) Implementasikan method `List<Integer> findPath(int source, int dest)` menggunakan DFS yang mengembalikan satu path dari source ke dest (tidak harus shortest).

---

## Referensi

1. Cormen, T. H., et al. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.
2. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley.
3. [GeeksforGeeks - BFS](https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/)
4. [GeeksforGeeks - DFS](https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/)
5. [Visualgo - Graph Traversal](https://visualgo.net/en/dfsbfs)
6. [CP-Algorithms - DFS](https://cp-algorithms.com/graph/depth-first-search.html)
7. [CP-Algorithms - Topological Sort](https://cp-algorithms.com/graph/topological-sort.html)
