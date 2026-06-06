# Modul 12. Pengenalan Graph dan Representasi

## Daftar Isi
- [1. Apa itu Graph?](#1-apa-itu-graph)
  - [1.1 Definisi Graph](#11-definisi-graph)
  - [1.2 Perbedaan Graph dan Tree](#12-perbedaan-graph-dan-tree)
- [2. Terminologi Graph](#2-terminologi-graph)
  - [2.1 Komponen Dasar](#21-komponen-dasar)
  - [2.2 Jenis-Jenis Graph](#22-jenis-jenis-graph)
  - [2.3 Istilah Penting Lainnya](#23-istilah-penting-lainnya)
- [3. Representasi Graph](#3-representasi-graph)
  - [3.1 Adjacency Matrix](#31-adjacency-matrix)
  - [3.2 Adjacency List](#32-adjacency-list)
  - [3.3 Edge List](#33-edge-list)
  - [3.4 Perbandingan Representasi](#34-perbandingan-representasi)
- [4. Implementasi Graph](#4-implementasi-graph)
  - [4.1 Graph dengan Adjacency Matrix](#41-graph-dengan-adjacency-matrix)
  - [4.2 Graph dengan Adjacency List](#42-graph-dengan-adjacency-list)
  - [4.3 Weighted Graph](#43-weighted-graph)
- [5. Operasi Dasar pada Graph](#5-operasi-dasar-pada-graph)
- [6. Aplikasi Graph di Dunia Nyata](#6-aplikasi-graph-di-dunia-nyata)
- [7. Latihan Praktikum](#7-latihan-praktikum)
- [8. Soal Latihan di Kelas](#8-soal-latihan-di-kelas)
- [Referensi](#referensi)

---

## 1. Apa itu Graph?

### 1.1 Definisi Graph

**Graph** adalah struktur data non-linear yang terdiri dari:
- **Vertex (Node)**: Titik atau simpul dalam graph
- **Edge (Arc)**: Garis yang menghubungkan dua vertex

Secara matematis, graph **G** didefinisikan sebagai:
```
G = (V, E)

dimana:
- V = himpunan vertex (simpul)
- E = himpunan edge (sisi) yang menghubungkan pasangan vertex
```

**Contoh:**
```
Graph Pertemanan:

    Alice ─────── Bob
      │          /  │
      │        /    │
      │      /      │
    Carol ─────── Dave

V = {Alice, Bob, Carol, Dave}
E = {(Alice, Bob), (Alice, Carol), (Bob, Carol), (Bob, Dave), (Carol, Dave)}
```

### 1.2 Perbedaan Graph dan Tree

| Aspek | Tree | Graph |
|-------|------|-------|
| **Struktur** | Hierarkis (parent-child) | Non-hierarkis |
| **Root** | Ada satu root | Tidak ada konsep root |
| **Cycle** | Tidak ada cycle | Bisa ada cycle |
| **Edge** | n-1 edge untuk n node | Bebas, bisa 0 sampai n(n-1)/2 |
| **Konektivitas** | Selalu connected | Bisa connected atau disconnected |
| **Path** | Hanya satu path antar node | Bisa ada banyak path |

```
Tree:                          Graph:
      A                        A ─── B
     / \                       │ \   │
    B   C                      │  \  │
   / \                         C ─── D
  D   E

Tidak ada cycle              Bisa ada cycle (A-B-D-C-A)
```

---

## 2. Terminologi Graph

### 2.1 Komponen Dasar

**Vertex (Node/Simpul)**
```
Vertex mewakili entitas:
○ A    ○ B    ○ C

Setiap vertex bisa memiliki label dan data
```

**Edge (Sisi/Arc)**
```
Edge menghubungkan dua vertex:

Undirected Edge:     Directed Edge:
A ───── B            A ─────> B
(bisa dua arah)      (satu arah saja)
```

**Adjacent (Bertetangga)**
```
Dua vertex disebut adjacent jika terhubung langsung oleh edge.

A ─── B ─── C

A dan B: adjacent
B dan C: adjacent
A dan C: TIDAK adjacent (tidak terhubung langsung)
```

**Degree (Derajat)**
```
Degree = jumlah edge yang terhubung ke vertex

      B
      │
A ─── C ─── D
      │
      E

Degree(A) = 1
Degree(C) = 4
Degree(B) = 1
```

**Untuk Directed Graph:**
- **In-degree**: Jumlah edge yang masuk ke vertex
- **Out-degree**: Jumlah edge yang keluar dari vertex

```
    A ───> B ───> C
    │      ↑
    │      │
    ↓      │
    D ─────┘

In-degree(B) = 2 (dari A dan D)
Out-degree(B) = 1 (ke C)
```

### 2.2 Jenis-Jenis Graph

#### 2.2.1 Berdasarkan Arah Edge

**Undirected Graph (Graf Tak Berarah)**
```
Edge tidak memiliki arah, bisa dilalui dua arah.

    A ───── B
    │       │
    │       │
    C ───── D

Jika ada edge (A, B), maka bisa dari A ke B dan B ke A.
```

**Directed Graph (Graf Berarah / Digraph)**
```
Edge memiliki arah, hanya bisa dilalui satu arah.

    A ────> B
    │       │
    ↓       ↓
    C <──── D

Edge (A, B) berarti hanya bisa dari A ke B, TIDAK bisa dari B ke A.
```

#### 2.2.2 Berdasarkan Bobot Edge

**Unweighted Graph (Graf Tanpa Bobot)**
```
Setiap edge dianggap sama (bobot = 1).

    A ───── B
    │       │
    │       │
    C ───── D
```

**Weighted Graph (Graf Berbobot)**
```
Setiap edge memiliki bobot/nilai.

    A ──5── B
    │       │
    3       2
    │       │
    C ──4── D

Bobot bisa merepresentasikan: jarak, waktu, biaya, dll.
```

#### 2.2.3 Berdasarkan Konektivitas

**Connected Graph**
```
Setiap pasangan vertex terhubung (ada path).

    A ───── B
    │       │
    │       │
    C ───── D

Semua vertex bisa dijangkau dari vertex manapun.
```

**Disconnected Graph**
```
Ada vertex yang tidak terhubung.

    A ───── B        E ───── F
    │                │
    │                │
    C                G

Komponen 1: {A, B, C}
Komponen 2: {E, F, G}
```

**Strongly Connected (untuk Directed Graph)**
```
Setiap pasangan vertex terhubung DUA ARAH.

    A ────> B
    ↑       │
    │       ↓
    D <──── C

Dari vertex manapun, bisa mencapai vertex lainnya.
```

**Weakly Connected (untuk Directed Graph)**
```
Connected jika arah edge diabaikan.

    A ────> B
            │
            ↓
    D       C

Weakly connected (jika arah diabaikan, semuanya terhubung)
NOT strongly connected (tidak bisa dari C ke A)
```

#### 2.2.4 Graph Khusus

**Complete Graph (K_n)**
```
Setiap vertex terhubung ke semua vertex lainnya.

K4 (Complete graph dengan 4 vertex):

    A ─────── B
    │ ╲     ╱ │
    │   ╲ ╱   │
    │   ╱ ╲   │
    │ ╱     ╲ │
    C ─────── D

Jumlah edge = n(n-1)/2 = 4×3/2 = 6
```

**Bipartite Graph**
```
Vertex bisa dibagi menjadi 2 grup, edge hanya menghubungkan antar grup.

Grup 1: {A, B, C}     Grup 2: {X, Y}

    A ──────── X
    │ ╲      ╱
    │   ╲  ╱
    B ─────── Y
    │
    C ────────┘

Tidak ada edge antara vertex dalam grup yang sama.

Contoh: Matching mahasiswa dengan mata kuliah
```

**Cyclic vs Acyclic Graph**
```
Cyclic (ada cycle):           Acyclic (DAG - Directed Acyclic Graph):
    A ────> B                     A ────> B
    ↑       │                             │
    │       ↓                             ↓
    D <──── C                     D <──── C
                                          │
    Ada cycle: A→B→C→D→A                  ↓
                                          E

                                  Tidak ada cycle, sering digunakan untuk
                                  task scheduling, dependency management
```

### 2.3 Istilah Penting Lainnya

**Path (Jalur)**
```
Urutan vertex yang terhubung oleh edge.

    A ─── B ─── C ─── D ─── E

Path dari A ke E: A → B → C → D → E
Panjang path = 4 (jumlah edge)
```

**Simple Path**
```
Path tanpa vertex yang berulang.

A → B → C → D        ← Simple path (tidak ada vertex berulang)
A → B → C → B → D    ← BUKAN simple path (B muncul 2 kali)
```

**Cycle (Siklus)**
```
Path yang dimulai dan berakhir di vertex yang sama.

    A ─── B
    │     │
    │     │
    D ─── C

Cycle: A → B → C → D → A
```

**Self-Loop**
```
Edge yang menghubungkan vertex ke dirinya sendiri.

    A ─── B
    │     ↺ (self-loop pada B)
    │
    C
```

**Multiple Edges (Multi-graph)**
```
Lebih dari satu edge antara pasangan vertex yang sama.

    A ═══ B
      ═══

Dua edge berbeda antara A dan B.
```

---

## 3. Representasi Graph

### 3.1 Adjacency Matrix

**Adjacency Matrix** adalah matriks 2D berukuran V×V dimana:
- `matrix[i][j] = 1` jika ada edge dari vertex i ke vertex j
- `matrix[i][j] = 0` jika tidak ada edge

**Contoh Undirected Graph:**
```
Graph:                 Adjacency Matrix:
    0 ─── 1                0  1  2  3
    │ ╲   │           0 [  0  1  1  1 ]
    │   ╲ │           1 [  1  0  1  0 ]
    2 ─── 3           2 [  1  1  0  1 ]
                      3 [  1  0  1  0 ]

Matriks simetris karena undirected (edge dua arah)
```

**Contoh Directed Graph:**
```
Graph:                 Adjacency Matrix:
    0 ───> 1               0  1  2  3
    │      │          0 [  0  1  1  1 ]
    ↓      ↓          1 [  0  0  1  0 ]
    2 <─── 3          2 [  0  0  0  0 ]
                      3 [  0  0  1  0 ]

Matriks TIDAK simetris karena directed (edge satu arah)
```

**Contoh Weighted Graph:**
```
Graph:                 Adjacency Matrix (dengan bobot):
    0 ─5─ 1                0  1  2  3
    │     │           0 [  0  5  3  0 ]
    3     2           1 [  5  0  2  0 ]
    │     │           2 [  3  2  0  4 ]
    2 ─4─ 3           3 [  0  0  4  0 ]

0 = tidak ada edge
Nilai lain = bobot edge
```

**Kelebihan Adjacency Matrix:**
- Cek edge O(1): langsung akses `matrix[i][j]`
- Simple untuk graph dense (banyak edge)

**Kekurangan Adjacency Matrix:**
- Space O(V²) meskipun edge sedikit
- Iterasi neighbor O(V)
- Tidak efisien untuk graph sparse (sedikit edge)

### 3.2 Adjacency List

**Adjacency List** menyimpan daftar neighbor untuk setiap vertex.

**Contoh Undirected Graph:**
```
Graph:                 Adjacency List:
    0 ─── 1            0 → [1, 2, 3]
    │ ╲   │            1 → [0, 2]
    │   ╲ │            2 → [0, 1, 3]
    2 ─── 3            3 → [0, 2]
```

**Contoh Directed Graph:**
```
Graph:                 Adjacency List:
    0 ───> 1           0 → [1, 2, 3]
    │      │           1 → [2]
    ↓      ↓           2 → []
    2 <─── 3           3 → [2]
```

**Contoh Weighted Graph:**
```
Graph:                 Adjacency List (dengan bobot):
    0 ─5─ 1            0 → [(1,5), (2,3)]
    │     │            1 → [(0,5), (2,2)]
    3     2            2 → [(0,3), (1,2), (3,4)]
    │     │            3 → [(2,4)]
    2 ─4─ 3
                       Format: (vertex tujuan, bobot)
```

**Kelebihan Adjacency List:**
- Space O(V + E), efisien untuk sparse graph
- Iterasi neighbor O(degree)
- Mudah menambah vertex baru

**Kekurangan Adjacency List:**
- Cek edge O(degree), lebih lambat dari matrix
- Lebih kompleks untuk graph dense

### 3.3 Edge List

**Edge List** menyimpan semua edge sebagai daftar pasangan vertex.

```
Graph:                 Edge List:
    0 ─── 1            [(0,1), (0,2), (0,3), (1,2), (2,3)]
    │ ╲   │
    │   ╲ │            Untuk weighted:
    2 ─── 3            [(0,1,5), (0,2,3), (1,2,2), (2,3,4)]
                       Format: (source, destination, weight)
```

**Kelebihan Edge List:**
- Space O(E), paling efisien untuk graph sangat sparse
- Mudah untuk iterasi semua edge
- Cocok untuk algoritma seperti Kruskal

**Kekurangan Edge List:**
- Cek edge O(E)
- Cari neighbor O(E)

### 3.4 Perbandingan Representasi

| Operasi | Adjacency Matrix | Adjacency List | Edge List |
|---------|------------------|----------------|-----------|
| **Space** | O(V²) | O(V + E) | O(E) |
| **Add Vertex** | O(V²) | O(1) | O(1) |
| **Add Edge** | O(1) | O(1) | O(1) |
| **Remove Edge** | O(1) | O(degree) | O(E) |
| **Check Edge** | O(1) | O(degree) | O(E) |
| **Get Neighbors** | O(V) | O(degree) | O(E) |
| **Get All Edges** | O(V²) | O(V + E) | O(E) |

**Kapan Menggunakan Apa?**

| Representasi | Gunakan Ketika |
|--------------|----------------|
| **Adjacency Matrix** | Graph dense (banyak edge), sering cek keberadaan edge |
| **Adjacency List** | Graph sparse (sedikit edge), sering iterasi neighbor |
| **Edge List** | Perlu iterasi semua edge (Kruskal), graph sangat sparse |

**Rumus Menentukan Dense vs Sparse:**
```
Maksimum edge untuk undirected graph: V(V-1)/2
Maksimum edge untuk directed graph: V(V-1)

Sparse: E << V²
Dense: E mendekati V²
```

---

## 4. Implementasi Graph

### 4.1 Graph dengan Adjacency Matrix

```java
// File: GraphMatrix.java

public class GraphMatrix {
    private int[][] matrix;
    private int numVertices;
    private boolean isDirected;

    public GraphMatrix(int numVertices, boolean isDirected) {
        this.numVertices = numVertices;
        this.isDirected = isDirected;
        this.matrix = new int[numVertices][numVertices];
    }

    // Menambah edge
    public void addEdge(int source, int destination) {
        validateVertex(source);
        validateVertex(destination);

        matrix[source][destination] = 1;

        // Jika undirected, tambahkan juga sebaliknya
        if (!isDirected) {
            matrix[destination][source] = 1;
        }
    }

    // Menghapus edge
    public void removeEdge(int source, int destination) {
        validateVertex(source);
        validateVertex(destination);

        matrix[source][destination] = 0;

        if (!isDirected) {
            matrix[destination][source] = 0;
        }
    }

    // Cek apakah ada edge
    public boolean hasEdge(int source, int destination) {
        validateVertex(source);
        validateVertex(destination);
        return matrix[source][destination] == 1;
    }

    // Mendapatkan semua neighbor dari vertex
    public int[] getNeighbors(int vertex) {
        validateVertex(vertex);

        // Hitung jumlah neighbor
        int count = 0;
        for (int i = 0; i < numVertices; i++) {
            if (matrix[vertex][i] == 1) count++;
        }

        // Buat array neighbor
        int[] neighbors = new int[count];
        int index = 0;
        for (int i = 0; i < numVertices; i++) {
            if (matrix[vertex][i] == 1) {
                neighbors[index++] = i;
            }
        }
        return neighbors;
    }

    // Mendapatkan degree vertex
    public int getDegree(int vertex) {
        validateVertex(vertex);
        int degree = 0;
        for (int i = 0; i < numVertices; i++) {
            if (matrix[vertex][i] == 1) degree++;
        }
        return degree;
    }

    // Untuk directed graph: in-degree
    public int getInDegree(int vertex) {
        validateVertex(vertex);
        if (!isDirected) {
            throw new UnsupportedOperationException("In-degree hanya untuk directed graph!");
        }
        int inDegree = 0;
        for (int i = 0; i < numVertices; i++) {
            if (matrix[i][vertex] == 1) inDegree++;
        }
        return inDegree;
    }

    // Untuk directed graph: out-degree
    public int getOutDegree(int vertex) {
        validateVertex(vertex);
        if (!isDirected) {
            throw new UnsupportedOperationException("Out-degree hanya untuk directed graph!");
        }
        return getDegree(vertex);
    }

    // Validasi vertex
    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= numVertices) {
            throw new IllegalArgumentException("Vertex " + vertex + " tidak valid!");
        }
    }

    // Menampilkan adjacency matrix
    public void display() {
        System.out.println("\nAdjacency Matrix:");
        System.out.print("  ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Menampilkan graph secara visual
    public void displayGraph() {
        System.out.println("\nGraph (Adjacency List format):");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " → ");
            boolean first = true;
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] == 1) {
                    if (!first) System.out.print(", ");
                    System.out.print(j);
                    first = false;
                }
            }
            System.out.println();
        }
    }

    public int getNumVertices() {
        return numVertices;
    }

    public static void main(String[] args) {
        System.out.println("=== GRAPH DENGAN ADJACENCY MATRIX ===\n");

        // Undirected Graph
        System.out.println("--- Undirected Graph ---");
        GraphMatrix undirected = new GraphMatrix(5, false);
        undirected.addEdge(0, 1);
        undirected.addEdge(0, 2);
        undirected.addEdge(1, 2);
        undirected.addEdge(1, 3);
        undirected.addEdge(2, 4);
        undirected.addEdge(3, 4);

        undirected.display();
        undirected.displayGraph();

        System.out.println("\nDegree of vertex 1: " + undirected.getDegree(1));
        System.out.println("Has edge (1,3)? " + undirected.hasEdge(1, 3));
        System.out.println("Has edge (0,4)? " + undirected.hasEdge(0, 4));

        // Directed Graph
        System.out.println("\n--- Directed Graph ---");
        GraphMatrix directed = new GraphMatrix(4, true);
        directed.addEdge(0, 1);
        directed.addEdge(0, 2);
        directed.addEdge(1, 2);
        directed.addEdge(2, 3);
        directed.addEdge(3, 0);

        directed.display();
        directed.displayGraph();

        System.out.println("\nIn-degree of vertex 2: " + directed.getInDegree(2));
        System.out.println("Out-degree of vertex 0: " + directed.getOutDegree(0));
    }
}
```

**Output:**
```
=== GRAPH DENGAN ADJACENCY MATRIX ===

--- Undirected Graph ---

Adjacency Matrix:
  0 1 2 3 4
0 0 1 1 0 0
1 1 0 1 1 0
2 1 1 0 0 1
3 0 1 0 0 1
4 0 0 1 1 0

Graph (Adjacency List format):
0 → 1, 2
1 → 0, 2, 3
2 → 0, 1, 4
3 → 1, 4
4 → 2, 3

Degree of vertex 1: 3
Has edge (1,3)? true
Has edge (0,4)? false

--- Directed Graph ---

Adjacency Matrix:
  0 1 2 3
0 0 1 1 0
1 0 0 1 0
2 0 0 0 1
3 1 0 0 0

Graph (Adjacency List format):
0 → 1, 2
1 → 2
2 → 3
3 → 0

In-degree of vertex 2: 2
Out-degree of vertex 0: 2
```

### 4.2 Graph dengan Adjacency List

```java
// File: GraphList.java
import java.util.*;

public class GraphList {
    private Map<Integer, List<Integer>> adjacencyList;
    private boolean isDirected;

    public GraphList(boolean isDirected) {
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    // Menambah vertex
    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Menambah edge
    public void addEdge(int source, int destination) {
        // Pastikan kedua vertex ada
        addVertex(source);
        addVertex(destination);

        // Tambah edge
        adjacencyList.get(source).add(destination);

        // Jika undirected, tambahkan sebaliknya
        if (!isDirected) {
            adjacencyList.get(destination).add(source);
        }
    }

    // Menghapus edge
    public void removeEdge(int source, int destination) {
        List<Integer> sourceList = adjacencyList.get(source);
        List<Integer> destList = adjacencyList.get(destination);

        if (sourceList != null) {
            sourceList.remove(Integer.valueOf(destination));
        }

        if (!isDirected && destList != null) {
            destList.remove(Integer.valueOf(source));
        }
    }

    // Menghapus vertex
    public void removeVertex(int vertex) {
        // Hapus semua edge yang mengarah ke vertex ini
        adjacencyList.values().forEach(list -> list.remove(Integer.valueOf(vertex)));

        // Hapus vertex dari list
        adjacencyList.remove(vertex);
    }

    // Cek apakah ada edge
    public boolean hasEdge(int source, int destination) {
        List<Integer> neighbors = adjacencyList.get(source);
        return neighbors != null && neighbors.contains(destination);
    }

    // Mendapatkan neighbor
    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    // Mendapatkan degree
    public int getDegree(int vertex) {
        List<Integer> neighbors = adjacencyList.get(vertex);
        return neighbors != null ? neighbors.size() : 0;
    }

    // In-degree untuk directed graph
    public int getInDegree(int vertex) {
        if (!isDirected) {
            throw new UnsupportedOperationException("In-degree hanya untuk directed graph!");
        }
        int inDegree = 0;
        for (List<Integer> neighbors : adjacencyList.values()) {
            if (neighbors.contains(vertex)) {
                inDegree++;
            }
        }
        return inDegree;
    }

    // Out-degree untuk directed graph
    public int getOutDegree(int vertex) {
        if (!isDirected) {
            throw new UnsupportedOperationException("Out-degree hanya untuk directed graph!");
        }
        return getDegree(vertex);
    }

    // Mendapatkan semua vertex
    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    }

    // Mendapatkan jumlah vertex
    public int getNumVertices() {
        return adjacencyList.size();
    }

    // Mendapatkan jumlah edge
    public int getNumEdges() {
        int totalEdges = 0;
        for (List<Integer> neighbors : adjacencyList.values()) {
            totalEdges += neighbors.size();
        }
        // Untuk undirected graph, setiap edge dihitung 2 kali
        return isDirected ? totalEdges : totalEdges / 2;
    }

    // Menampilkan graph
    public void display() {
        System.out.println("\nGraph (Adjacency List):");
        for (int vertex : adjacencyList.keySet()) {
            System.out.print(vertex + " → ");
            List<Integer> neighbors = adjacencyList.get(vertex);
            if (neighbors.isEmpty()) {
                System.out.println("(no neighbors)");
            } else {
                System.out.println(neighbors);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== GRAPH DENGAN ADJACENCY LIST ===\n");

        // Undirected Graph
        System.out.println("--- Undirected Graph ---");
        GraphList undirected = new GraphList(false);
        undirected.addEdge(0, 1);
        undirected.addEdge(0, 2);
        undirected.addEdge(1, 2);
        undirected.addEdge(1, 3);
        undirected.addEdge(2, 4);
        undirected.addEdge(3, 4);

        undirected.display();

        System.out.println("\nVertices: " + undirected.getVertices());
        System.out.println("Number of vertices: " + undirected.getNumVertices());
        System.out.println("Number of edges: " + undirected.getNumEdges());
        System.out.println("Degree of vertex 1: " + undirected.getDegree(1));
        System.out.println("Neighbors of vertex 2: " + undirected.getNeighbors(2));

        // Directed Graph
        System.out.println("\n--- Directed Graph ---");
        GraphList directed = new GraphList(true);
        directed.addEdge(0, 1);
        directed.addEdge(0, 2);
        directed.addEdge(1, 2);
        directed.addEdge(2, 3);
        directed.addEdge(3, 0);

        directed.display();

        System.out.println("\nIn-degree of vertex 2: " + directed.getInDegree(2));
        System.out.println("Out-degree of vertex 0: " + directed.getOutDegree(0));

        // Menghapus edge
        System.out.println("\n--- Setelah removeEdge(0, 1) ---");
        directed.removeEdge(0, 1);
        directed.display();

        // Menambah vertex terpisah
        System.out.println("\n--- Setelah addVertex(5) ---");
        directed.addVertex(5);
        directed.display();
    }
}
```

**Output:**
```
=== GRAPH DENGAN ADJACENCY LIST ===

--- Undirected Graph ---

Graph (Adjacency List):
0 → [1, 2]
1 → [0, 2, 3]
2 → [0, 1, 4]
3 → [1, 4]
4 → [2, 3]

Vertices: [0, 1, 2, 3, 4]
Number of vertices: 5
Number of edges: 6
Degree of vertex 1: 3
Neighbors of vertex 2: [0, 1, 4]

--- Directed Graph ---

Graph (Adjacency List):
0 → [1, 2]
1 → [2]
2 → [3]
3 → [0]

In-degree of vertex 2: 2
Out-degree of vertex 0: 2

--- Setelah removeEdge(0, 1) ---

Graph (Adjacency List):
0 → [2]
1 → [2]
2 → [3]
3 → [0]

--- Setelah addVertex(5) ---

Graph (Adjacency List):
0 → [2]
1 → [2]
2 → [3]
3 → [0]
5 → (no neighbors)
```

### 4.3 Weighted Graph

```java
// File: WeightedGraph.java
import java.util.*;

class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "(" + source + " → " + destination + ", w=" + weight + ")";
    }
}

public class WeightedGraph {
    private Map<Integer, List<Edge>> adjacencyList;
    private boolean isDirected;

    public WeightedGraph(boolean isDirected) {
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination, int weight) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(new Edge(source, destination, weight));

        if (!isDirected) {
            adjacencyList.get(destination).add(new Edge(destination, source, weight));
        }
    }

    public void removeEdge(int source, int destination) {
        List<Edge> sourceEdges = adjacencyList.get(source);
        if (sourceEdges != null) {
            sourceEdges.removeIf(edge -> edge.destination == destination);
        }

        if (!isDirected) {
            List<Edge> destEdges = adjacencyList.get(destination);
            if (destEdges != null) {
                destEdges.removeIf(edge -> edge.destination == source);
            }
        }
    }

    public Integer getWeight(int source, int destination) {
        List<Edge> edges = adjacencyList.get(source);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.destination == destination) {
                    return edge.weight;
                }
            }
        }
        return null; // Edge tidak ditemukan
    }

    public List<Edge> getEdges(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public List<Edge> getAllEdges() {
        List<Edge> allEdges = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            for (Edge edge : adjacencyList.get(vertex)) {
                String key = Math.min(edge.source, edge.destination) + "-" +
                           Math.max(edge.source, edge.destination);
                if (isDirected || !seen.contains(key)) {
                    allEdges.add(edge);
                    seen.add(key);
                }
            }
        }
        return allEdges;
    }

    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    }

    public int getNumVertices() {
        return adjacencyList.size();
    }

    public void display() {
        System.out.println("\nWeighted Graph (Adjacency List):");
        for (int vertex : adjacencyList.keySet()) {
            System.out.print(vertex + " → ");
            List<Edge> edges = adjacencyList.get(vertex);
            if (edges.isEmpty()) {
                System.out.println("(no edges)");
            } else {
                List<String> edgeStrings = new ArrayList<>();
                for (Edge edge : edges) {
                    edgeStrings.add(edge.destination + "(w=" + edge.weight + ")");
                }
                System.out.println(String.join(", ", edgeStrings));
            }
        }
    }

    // Display sebagai adjacency matrix
    public void displayMatrix() {
        List<Integer> vertices = new ArrayList<>(adjacencyList.keySet());
        Collections.sort(vertices);
        int n = vertices.size();

        System.out.println("\nWeighted Adjacency Matrix:");
        System.out.print("    ");
        for (int v : vertices) {
            System.out.printf("%4d", v);
        }
        System.out.println();

        for (int i : vertices) {
            System.out.printf("%4d", i);
            for (int j : vertices) {
                Integer weight = getWeight(i, j);
                if (weight == null) {
                    System.out.print("   ∞");
                } else {
                    System.out.printf("%4d", weight);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== WEIGHTED GRAPH ===\n");

        // Undirected Weighted Graph
        System.out.println("--- Undirected Weighted Graph ---");
        WeightedGraph graph = new WeightedGraph(false);

        // Contoh: Jarak antar kota
        graph.addEdge(0, 1, 4);   // Kota 0 ke Kota 1: 4 km
        graph.addEdge(0, 2, 2);   // Kota 0 ke Kota 2: 2 km
        graph.addEdge(1, 2, 1);   // Kota 1 ke Kota 2: 1 km
        graph.addEdge(1, 3, 5);   // Kota 1 ke Kota 3: 5 km
        graph.addEdge(2, 3, 8);   // Kota 2 ke Kota 3: 8 km
        graph.addEdge(2, 4, 10);  // Kota 2 ke Kota 4: 10 km
        graph.addEdge(3, 4, 2);   // Kota 3 ke Kota 4: 2 km

        graph.display();
        graph.displayMatrix();

        System.out.println("\nAll edges:");
        for (Edge edge : graph.getAllEdges()) {
            System.out.println("  " + edge);
        }

        System.out.println("\nWeight from 1 to 3: " + graph.getWeight(1, 3));
        System.out.println("Weight from 0 to 4: " + graph.getWeight(0, 4)); // tidak ada edge langsung

        // Directed Weighted Graph
        System.out.println("\n--- Directed Weighted Graph ---");
        WeightedGraph dirGraph = new WeightedGraph(true);
        dirGraph.addEdge(0, 1, 10);
        dirGraph.addEdge(0, 2, 3);
        dirGraph.addEdge(1, 2, 1);
        dirGraph.addEdge(2, 1, 4);
        dirGraph.addEdge(2, 3, 2);

        dirGraph.display();
        dirGraph.displayMatrix();
    }
}
```

**Output:**
```
=== WEIGHTED GRAPH ===

--- Undirected Weighted Graph ---

Weighted Graph (Adjacency List):
0 → 1(w=4), 2(w=2)
1 → 0(w=4), 2(w=1), 3(w=5)
2 → 0(w=2), 1(w=1), 3(w=8), 4(w=10)
3 → 1(w=5), 2(w=8), 4(w=2)
4 → 2(w=10), 3(w=2)

Weighted Adjacency Matrix:
       0   1   2   3   4
   0   ∞   4   2   ∞   ∞
   1   4   ∞   1   5   ∞
   2   2   1   ∞   8  10
   3   ∞   5   8   ∞   2
   4   ∞   ∞  10   2   ∞

All edges:
  (0 → 1, w=4)
  (0 → 2, w=2)
  (1 → 2, w=1)
  (1 → 3, w=5)
  (2 → 3, w=8)
  (2 → 4, w=10)
  (3 → 4, w=2)

Weight from 1 to 3: 5
Weight from 0 to 4: null

--- Directed Weighted Graph ---

Weighted Graph (Adjacency List):
0 → 1(w=10), 2(w=3)
1 → 2(w=1)
2 → 1(w=4), 3(w=2)
3 → (no edges)

Weighted Adjacency Matrix:
       0   1   2   3
   0   ∞  10   3   ∞
   1   ∞   ∞   1   ∞
   2   ∞   4   ∞   2
   3   ∞   ∞   ∞   ∞
```

---

## 5. Operasi Dasar pada Graph

| Operasi | Deskripsi | Kompleksitas (Matrix) | Kompleksitas (List) |
|---------|-----------|----------------------|---------------------|
| `addVertex(v)` | Menambah vertex baru | O(V²) | O(1) |
| `addEdge(u, v)` | Menambah edge antara u dan v | O(1) | O(1) |
| `removeVertex(v)` | Menghapus vertex dan semua edge-nya | O(V²) | O(V + E) |
| `removeEdge(u, v)` | Menghapus edge antara u dan v | O(1) | O(degree) |
| `hasEdge(u, v)` | Cek apakah ada edge | O(1) | O(degree) |
| `getNeighbors(v)` | Mendapatkan semua neighbor | O(V) | O(degree) |
| `getDegree(v)` | Mendapatkan degree vertex | O(V) | O(1) atau O(degree) |
| `getNumVertices()` | Jumlah vertex | O(1) | O(1) |
| `getNumEdges()` | Jumlah edge | O(V²) | O(V + E) |

---

## 6. Aplikasi Graph di Dunia Nyata

### 6.1 Social Network

```
Graph Pertemanan Facebook/Instagram:

Vertex = User
Edge = Pertemanan/Following

    Alice ─────── Bob
      │ ╲       ╱ │
      │   ╲   ╱   │
      │     ╳     │
      │   ╱   ╲   │
      │ ╱       ╲ │
    Carol ────── Dave

Operasi yang sering dilakukan:
- Mencari teman dari teman (BFS dengan depth terbatas)
- Rekomendasi teman (mutual friends)
- Deteksi komunitas (clustering)
```

### 6.2 Peta dan Navigasi (Google Maps, Waze)

```
Vertex = Lokasi/Persimpangan
Edge = Jalan (weighted dengan jarak/waktu)

    [Rumah] ──5km── [Kantor]
       │              │
      3km            2km
       │              │
    [Mall] ───4km── [Resto]

Operasi:
- Shortest path (Dijkstra, A*)
- Alternatif rute
- Traffic estimation
```

### 6.3 Internet dan Jaringan Komputer

```
Vertex = Router/Server
Edge = Koneksi (weighted dengan bandwidth/latency)

    [Server A] ───── [Router 1] ───── [Server B]
         │               │
         │               │
    [Router 2] ─────────┘
         │
    [Client]

Operasi:
- Routing packets (shortest path)
- Load balancing
- Network topology analysis
```

### 6.4 Dependency Management

```
Graph Dependensi Package/Module:

    [App] ──────> [React]
      │              │
      │              ↓
      └────> [Webpack] ──> [Node.js]
                │
                ↓
           [Babel]

DAG (Directed Acyclic Graph) untuk:
- Build order (topological sort)
- Mendeteksi circular dependency
- Version resolution
```

### 6.5 Rekomendasi Produk

```
Bipartite Graph:

Users: {Alice, Bob, Carol}
Products: {Laptop, Phone, Headphone}

    Alice ──────── Laptop
      │ ╲        ╱
      │   ╲    ╱
      │     ╲╱
    Bob ──────── Phone
      │       ╱
      │     ╱
    Carol ───── Headphone

Jika Alice dan Bob sama-sama beli Laptop dan Phone,
dan Bob juga beli Headphone, maka Headphone bisa
direkomendasikan ke Alice.
```

### 6.6 Contoh Aplikasi Lainnya

| Domain | Vertex | Edge | Contoh Operasi |
|--------|--------|------|----------------|
| **Airline** | Bandara | Rute Penerbangan | Shortest flight path |
| **Biology** | Protein | Interaksi | Protein network analysis |
| **Games** | State | Move | Game tree, pathfinding |
| **Compiler** | Statement | Data flow | Optimization |
| **Web** | Page | Hyperlink | PageRank |

---

## 7. Latihan Praktikum

### Latihan 1: Implementasi Graph dengan String Vertex
Modifikasi `GraphList` agar bisa menggunakan String sebagai vertex, bukan hanya integer. Contoh penggunaan:
```java
GraphList<String> graph = new GraphList<>(false);
graph.addEdge("Jakarta", "Bandung");
graph.addEdge("Jakarta", "Surabaya");
```

### Latihan 2: Deteksi Self-Loop dan Multiple Edge
Tambahkan method pada `GraphList`:
- `hasSelfLoop(int vertex)`: mengembalikan true jika vertex memiliki self-loop
- `hasMultipleEdges(int u, int v)`: mengembalikan true jika ada lebih dari satu edge antara u dan v

### Latihan 3: Transpose Graph
Implementasikan method `transpose()` pada directed graph yang membalik arah semua edge.
```
Original:              Transpose:
    A ───> B               A <─── B
    │                      │
    ↓                      ↓
    C                      C
```

### Latihan 4: Complete Graph Generator
Implementasikan method `static GraphList createCompleteGraph(int n)` yang membuat complete graph dengan n vertex.

---

## 8. Soal Latihan di Kelas

### Soal 1: Terminologi Graph (15 poin)

Diberikan graph berikut:
```
    A ───── B ───── C
    │       │     ╱ │
    │       │   ╱   │
    D ───── E ─────  F
            │
            G
```

a) Sebutkan semua vertex yang adjacent dengan E

b) Hitung degree dari setiap vertex

c) Apakah graph ini connected? Jelaskan!

d) Sebutkan semua simple path dari A ke F

e) Apakah graph ini memiliki cycle? Jika ya, sebutkan satu cycle!

### Soal 2: Representasi Graph (20 poin)

Diberikan directed weighted graph:
```
    A ──3──> B
    │        │
    5        2
    │        │
    ↓        ↓
    C <──4── D ──1──> E
```

a) Gambarkan Adjacency Matrix untuk graph ini

b) Tuliskan Adjacency List untuk graph ini

c) Tuliskan Edge List untuk graph ini

d) Jika ada 100 vertex dan 150 edge, representasi mana yang lebih efisien dari segi memory? Jelaskan perhitungannya!

### Soal 3: Implementasi (25 poin)

Implementasikan class `BipartiteGraph` dengan method:
- `addLeftVertex(int v)`: menambah vertex ke grup kiri
- `addRightVertex(int v)`: menambah vertex ke grup kanan
- `addEdge(int left, int right)`: menambah edge (hanya boleh antara grup kiri dan kanan)
- `isValidBipartite()`: memvalidasi bahwa tidak ada edge dalam satu grup

### Soal 4: Analisis (15 poin)

Jelaskan dengan singkat:

a) Mengapa graph social network biasanya direpresentasikan dengan adjacency list, bukan adjacency matrix?

b) Untuk aplikasi GPS navigation, informasi apa saja yang perlu disimpan pada vertex dan edge?

c) Apa perbedaan antara strongly connected dan weakly connected pada directed graph? Berikan contoh masing-masing.

### Soal 5: Konversi Representasi (25 poin)

a) Tuliskan fungsi `int[][] listToMatrix(Map<Integer, List<Integer>> adjList, int n)` yang mengkonversi adjacency list ke adjacency matrix.

b) Tuliskan fungsi `Map<Integer, List<Integer>> matrixToList(int[][] matrix)` yang mengkonversi adjacency matrix ke adjacency list.

---

## Referensi

1. Cormen, T. H., et al. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.
2. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley.
3. [GeeksforGeeks - Graph Data Structure](https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/)
4. [Visualgo - Graph](https://visualgo.net/en/graphds)
5. [CP-Algorithms - Graph](https://cp-algorithms.com/graph/breadth-first-search.html)
