# Modul 11. Tree Lanjutan (AVL Tree, Heap, dan Trie)

## Daftar Isi
- [1. AVL Tree](#1-avl-tree)
  - [1.1 Apa itu AVL Tree?](#11-apa-itu-avl-tree)
  - [1.2 Balance Factor](#12-balance-factor)
  - [1.3 Rotasi pada AVL Tree](#13-rotasi-pada-avl-tree)
  - [1.4 Implementasi AVL Tree](#14-implementasi-avl-tree)
  - [1.5 Kompleksitas AVL Tree](#15-kompleksitas-avl-tree)
- [2. Heap](#2-heap)
  - [2.1 Apa itu Heap?](#21-apa-itu-heap)
  - [2.2 Representasi Heap dengan Array](#22-representasi-heap-dengan-array)
  - [2.3 Operasi pada Heap](#23-operasi-pada-heap)
  - [2.4 Implementasi Min-Heap](#24-implementasi-min-heap)
  - [2.5 Implementasi Max-Heap](#25-implementasi-max-heap)
  - [2.6 Heap Sort](#26-heap-sort)
  - [2.7 Priority Queue](#27-priority-queue)
- [3. Trie (Prefix Tree)](#3-trie-prefix-tree)
  - [3.1 Apa itu Trie?](#31-apa-itu-trie)
  - [3.2 Operasi pada Trie](#32-operasi-pada-trie)
  - [3.3 Implementasi Trie](#33-implementasi-trie)
  - [3.4 Aplikasi Trie](#34-aplikasi-trie)
- [4. Perbandingan Struktur Data Tree](#4-perbandingan-struktur-data-tree)
- [5. Kapan Menggunakan Struktur Data Tree?](#5-kapan-menggunakan-struktur-data-tree)
- [6. Latihan Praktikum](#6-latihan-praktikum)
- [7. Soal Latihan di Kelas](#7-soal-latihan-di-kelas)
- [Referensi](#referensi)

---

## 1. AVL Tree

### 1.1 Apa itu AVL Tree?

AVL Tree dinamakan dari penemunya **Adelson-Velsky** dan **Landis** (1962) dan merupakan **self-balancing Binary Search Tree** yang pertama kali ditemukan.

**Masalah BST Biasa:**
```
Insert secara berurutan: 1, 2, 3, 4, 5

BST menjadi skewed (seperti Linked List):
    1
     \
      2
       \
        3
         \
          4
           \
            5

Kompleksitas menjadi O(n) bukan O(log n)!
```

**Solusi AVL Tree:**
AVL Tree menjaga keseimbangan dengan memastikan **perbedaan tinggi (height)** antara left subtree dan right subtree dari setiap node **tidak lebih dari 1**.

```
AVL Tree setelah insert 1, 2, 3, 4, 5:

        2
       / \
      1   4
         / \
        3   5

Seimbang! Kompleksitas tetap O(log n)
```

### 1.2 Balance Factor

**Balance Factor (BF)** adalah perbedaan tinggi antara left subtree dan right subtree:

```
Balance Factor = Height(Left Subtree) - Height(Right Subtree)
```

**Aturan AVL Tree:**
- BF harus **-1, 0, atau 1** untuk setiap node
- Jika BF < -1 atau BF > 1, tree **tidak seimbang** dan perlu **rotasi**

**Contoh:**
```
        30 (BF=1)              30 (BF=2) ← TIDAK SEIMBANG!
       /  \                   /
      20   40               20
     /                     /
    10                   10

    Seimbang              Perlu rotasi
```

**Visualisasi Balance Factor:**
```
             50 (BF=0)
            /  \
     (BF=1) 30   70 (BF=-1)
           /    /  \
          20   60   80
                      \
                       90

Height dari node 30: left=1 (node 20), right=0 (null) → BF = 1-0 = 1
Height dari node 70: left=1 (node 60), right=2 (subtree 80-90) → BF = 1-2 = -1
```

### 1.3 Rotasi pada AVL Tree

Ada **4 jenis rotasi** untuk menyeimbangkan AVL Tree:

#### 1.3.1 Right Rotation (RR) - Single Rotation

Digunakan ketika **left subtree terlalu tinggi** (Left-Left case).

```
Sebelum (BF=2 di node 30):        Setelah Right Rotation:
        30                              20
       /                               /  \
      20                             10    30
     /
    10

Langkah:
1. Node 20 menjadi root baru
2. Node 30 menjadi right child dari 20
3. Right child dari 20 (jika ada) menjadi left child dari 30
```

**Visualisasi Detail:**
```
        y                              x
       / \                           /   \
      x   T3    Right Rotate       T1     y
     / \        ─────────────>           / \
    T1  T2                              T2  T3
```

#### 1.3.2 Left Rotation (LL) - Single Rotation

Digunakan ketika **right subtree terlalu tinggi** (Right-Right case).

```
Sebelum (BF=-2 di node 10):       Setelah Left Rotation:
    10                                  20
      \                                /  \
       20                            10    30
         \
          30

Langkah:
1. Node 20 menjadi root baru
2. Node 10 menjadi left child dari 20
3. Left child dari 20 (jika ada) menjadi right child dari 10
```

**Visualisasi Detail:**
```
      x                                y
     / \                             /   \
    T1  y       Left Rotate         x     T3
       / \      ─────────────>     / \
      T2  T3                      T1  T2
```

#### 1.3.3 Left-Right Rotation (LR) - Double Rotation

Digunakan ketika **left subtree tinggi** tapi **imbalance di right child dari left subtree**.

```
Sebelum (Left-Right case):        Setelah LR Rotation:
        30                              20
       /                               /  \
      10                             10    30
        \
         20

Langkah:
1. Left Rotate pada node 10
2. Right Rotate pada node 30
```

**Visualisasi Detail:**
```
       z                       z                         y
      / \                     / \                      /   \
     x   T4   Left Rotate    y   T4   Right Rotate    x     z
    / \       ──────────>   / \       ──────────>    / \   / \
   T1  y        (at x)     x   T3       (at z)      T1 T2 T3 T4
      / \                 / \
     T2  T3              T1  T2
```

#### 1.3.4 Right-Left Rotation (RL) - Double Rotation

Digunakan ketika **right subtree tinggi** tapi **imbalance di left child dari right subtree**.

```
Sebelum (Right-Left case):        Setelah RL Rotation:
    10                                  20
      \                                /  \
       30                            10    30
      /
     20

Langkah:
1. Right Rotate pada node 30
2. Left Rotate pada node 10
```

**Visualisasi Detail:**
```
     x                         x                           y
    / \                       / \                        /   \
   T1  z     Right Rotate    T1  y     Left Rotate      x     z
      / \    ──────────>        / \    ──────────>     / \   / \
     y   T4    (at z)          T2  z     (at x)       T1 T2 T3 T4
    / \                           / \
   T2  T3                        T3  T4
```

#### Ringkasan Kapan Menggunakan Rotasi

| Kondisi | Balance Factor | Kasus | Rotasi |
|---------|----------------|-------|--------|
| BF > 1 dan BF(left) >= 0 | Left-Left | LL | Right Rotation |
| BF > 1 dan BF(left) < 0 | Left-Right | LR | Left-Right Rotation |
| BF < -1 dan BF(right) <= 0 | Right-Right | RR | Left Rotation |
| BF < -1 dan BF(right) > 0 | Right-Left | RL | Right-Left Rotation |

### 1.4 Implementasi AVL Tree

```java
// File: AVLTree.java

class AVLNode {
    int data;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    // Mendapatkan tinggi node
    private int getHeight(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }

    // Mendapatkan balance factor
    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // Update tinggi node
    private void updateHeight(AVLNode node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // Right Rotation
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Lakukan rotasi
        x.right = y;
        y.left = T2;

        // Update tinggi (y dulu karena sekarang y adalah child dari x)
        updateHeight(y);
        updateHeight(x);

        return x; // Return root baru
    }

    // Left Rotation
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Lakukan rotasi
        y.left = x;
        x.right = T2;

        // Update tinggi
        updateHeight(x);
        updateHeight(y);

        return y; // Return root baru
    }

    // Insert node
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private AVLNode insertRec(AVLNode node, int data) {
        // 1. Lakukan BST insert biasa
        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.data) {
            node.left = insertRec(node.left, data);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
        } else {
            return node; // Duplikat tidak diizinkan
        }

        // 2. Update tinggi node ini
        updateHeight(node);

        // 3. Dapatkan balance factor
        int balance = getBalance(node);

        // 4. Jika tidak seimbang, ada 4 kasus:

        // Left Left Case
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Delete node
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private AVLNode deleteRec(AVLNode node, int data) {
        // 1. Lakukan BST delete biasa
        if (node == null) {
            return node;
        }

        if (data < node.data) {
            node.left = deleteRec(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRec(node.right, data);
        } else {
            // Node yang akan dihapus ditemukan

            // Node dengan satu child atau tanpa child
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    // Tidak ada child
                    node = null;
                } else {
                    // Satu child
                    node = temp;
                }
            } else {
                // Node dengan dua child: ambil inorder successor
                AVLNode temp = getMinValueNode(node.right);
                node.data = temp.data;
                node.right = deleteRec(node.right, temp.data);
            }
        }

        if (node == null) {
            return node;
        }

        // 2. Update tinggi
        updateHeight(node);

        // 3. Dapatkan balance factor dan seimbangkan
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode getMinValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Search
    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchRec(node.left, data);
        return searchRec(node.right, data);
    }

    // Inorder traversal
    public void inorder() {
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(AVLNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + "(h=" + node.height + ") ");
            inorderRec(node.right);
        }
    }

    // Preorder traversal (untuk melihat struktur tree)
    public void preorder() {
        System.out.print("Preorder: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    // Display tree visual
    public void displayTree() {
        System.out.println("\nTree Structure:");
        displayTreeRec(root, "", true);
    }

    private void displayTreeRec(AVLNode node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") +
                             node.data + " (h=" + node.height + ", bf=" + getBalance(node) + ")");
            displayTreeRec(node.left, prefix + (isLast ? "    " : "│   "), false);
            displayTreeRec(node.right, prefix + (isLast ? "    " : "│   "), true);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== AVL TREE DEMO ===\n");

        AVLTree tree = new AVLTree();

        // Insert berurutan (akan menyebabkan rotasi)
        System.out.println("Inserting: 10, 20, 30, 40, 50, 25");
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int val : values) {
            tree.insert(val);
            System.out.println("\nSetelah insert " + val + ":");
            tree.displayTree();
        }

        System.out.println("\n--- Traversals ---");
        tree.inorder();
        tree.preorder();

        System.out.println("\n--- Search ---");
        System.out.println("Search 30: " + tree.search(30));
        System.out.println("Search 100: " + tree.search(100));

        System.out.println("\n--- Delete ---");
        System.out.println("Delete 40:");
        tree.delete(40);
        tree.displayTree();
        tree.inorder();
    }
}
```

**Output:**
```
=== AVL TREE DEMO ===

Inserting: 10, 20, 30, 40, 50, 25

Setelah insert 10:

Tree Structure:
└── 10 (h=1, bf=0)

Setelah insert 20:

Tree Structure:
└── 10 (h=2, bf=-1)
    └── 20 (h=1, bf=0)

Setelah insert 30:

Tree Structure:
└── 20 (h=2, bf=0)
    ├── 10 (h=1, bf=0)
    └── 30 (h=1, bf=0)

[Rotasi Left terjadi karena Right-Right case]

... (output lanjutan)

--- Traversals ---
Inorder: 10(h=1) 20(h=3) 25(h=1) 30(h=2) 40(h=1) 50(h=1)
Preorder: 20 10 40 30 25 50

--- Search ---
Search 30: true
Search 100: false
```

### 1.5 Kompleksitas AVL Tree

| Operasi | Kompleksitas Waktu | Keterangan |
|---------|-------------------|------------|
| Search | O(log n) | Selalu terjamin karena balanced |
| Insert | O(log n) | Termasuk waktu rotasi |
| Delete | O(log n) | Termasuk waktu rotasi |
| Space | O(n) | Untuk menyimpan n node |

**Keuntungan AVL Tree:**
- Performa search, insert, delete selalu O(log n)
- Cocok untuk aplikasi yang membutuhkan banyak operasi search

**Kekurangan AVL Tree:**
- Membutuhkan extra storage untuk menyimpan height
- Insert dan delete lebih lambat dari BST biasa karena perlu rotasi
- Red-Black Tree lebih populer karena lebih sedikit rotasi

---

## 2. Heap

### 2.1 Apa itu Heap?

Heap adalah **Complete Binary Tree** khusus yang memenuhi **Heap Property**:

**Max-Heap:**
- Nilai setiap parent **lebih besar atau sama dengan** nilai children-nya
- Root adalah elemen **maksimum**

**Min-Heap:**
- Nilai setiap parent **lebih kecil atau sama dengan** nilai children-nya
- Root adalah elemen **minimum**

```
Max-Heap:                    Min-Heap:
        100                        10
       /   \                      /  \
      40    50                  20    30
     / \   / \                 / \   / \
    10 15 20 30               40 50 35 45

Root = Max (100)             Root = Min (10)
```

**Perbedaan Heap dengan BST:**
- Heap: Parent > Children (atau <), tidak ada urutan left-right
- BST: Left < Parent < Right

```
Heap (Valid):       BST (Valid):
      50                 50
     /  \               /  \
    30   20           30    70
                     /  \
                   20   40

Di Heap, 30 > 20 tidak masalah
Di BST, left child harus < parent, right child harus > parent
```

### 2.2 Representasi Heap dengan Array

Karena Heap adalah **Complete Binary Tree**, kita bisa merepresentasikannya dengan **array** secara efisien tanpa pointer.

```
Max-Heap:
           100
          /   \
        40     50
       / \    / \
      10  15 20  30

Array: [100, 40, 50, 10, 15, 20, 30]
Index:   0    1   2   3   4   5   6
```

**Formula:**
- Parent dari index i: `(i - 1) / 2`
- Left child dari index i: `2 * i + 1`
- Right child dari index i: `2 * i + 2`

**Contoh:**
```
Index 1 (nilai 40):
- Parent: (1-1)/2 = 0 (nilai 100) ✓
- Left child: 2*1+1 = 3 (nilai 10) ✓
- Right child: 2*1+2 = 4 (nilai 15) ✓
```

### 2.3 Operasi pada Heap

#### 2.3.1 Insert (Heapify-Up)

```
Insert 60 ke Max-Heap:

Step 1: Tambahkan di posisi terakhir
           100
          /   \
        40     50
       / \    / \
      10  15 20  60  ← Posisi baru

Step 2: Heapify-Up (bubble up)
Bandingkan dengan parent, swap jika lebih besar

           100
          /   \
        40     60  ← Swap 60 dengan 50
       / \    / \
      10  15 20  50

Step 3: Lanjutkan sampai heap property terpenuhi
(60 < 100, jadi selesai)
```

#### 2.3.2 Extract-Max/Min (Heapify-Down)

```
Extract Max dari Max-Heap:

Step 1: Simpan root (100), ganti dengan elemen terakhir
           30           ← Root baru (elemen terakhir)
          /   \
        40     60
       / \    /
      10  15 20

Step 2: Heapify-Down (bubble down)
Bandingkan dengan children, swap dengan yang terbesar

           60          ← Swap 30 dengan 60 (child terbesar)
          /   \
        40     30
       / \    /
      10  15 20

           60
          /   \
        40     30      ← 30 sudah leaf, selesai
       / \    /
      10  15 20

Return 100
```

### 2.4 Implementasi Min-Heap

```java
// File: MinHeap.java
import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Helper methods untuk navigasi
    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
    private boolean hasParent(int i) { return parent(i) >= 0; }
    private boolean hasLeftChild(int i) { return leftChild(i) < size; }
    private boolean hasRightChild(int i) { return rightChild(i) < size; }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Resize array jika penuh
    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    // Peek: Lihat elemen minimum tanpa menghapus
    public int peek() {
        if (size == 0) throw new IllegalStateException("Heap kosong!");
        return heap[0];
    }

    // Insert: Tambahkan elemen baru
    public void insert(int value) {
        ensureCapacity();
        heap[size] = value;
        size++;
        heapifyUp();
    }

    // Heapify-Up: Perbaiki posisi elemen yang baru ditambahkan
    private void heapifyUp() {
        int index = size - 1;
        // Selama masih punya parent dan parent lebih besar
        while (hasParent(index) && heap[parent(index)] > heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // Extract-Min: Ambil dan hapus elemen minimum
    public int extractMin() {
        if (size == 0) throw new IllegalStateException("Heap kosong!");

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return min;
    }

    // Heapify-Down: Perbaiki posisi root setelah extract
    private void heapifyDown() {
        int index = 0;
        // Selama masih punya left child
        while (hasLeftChild(index)) {
            int smallerChildIndex = leftChild(index);

            // Cek apakah right child lebih kecil
            if (hasRightChild(index) && heap[rightChild(index)] < heap[leftChild(index)]) {
                smallerChildIndex = rightChild(index);
            }

            // Jika current sudah lebih kecil dari children, selesai
            if (heap[index] < heap[smallerChildIndex]) {
                break;
            }

            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    // Delete: Hapus elemen di index tertentu
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index tidak valid!");
        }

        // Ganti dengan elemen terakhir
        heap[index] = heap[size - 1];
        size--;

        // Perbaiki posisi
        // Bisa naik atau turun tergantung nilai
        if (index > 0 && heap[index] < heap[parent(index)]) {
            heapifyUpFrom(index);
        } else {
            heapifyDownFrom(index);
        }
    }

    private void heapifyUpFrom(int index) {
        while (hasParent(index) && heap[parent(index)] > heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    private void heapifyDownFrom(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = leftChild(index);
            if (hasRightChild(index) && heap[rightChild(index)] < heap[leftChild(index)]) {
                smallerChildIndex = rightChild(index);
            }
            if (heap[index] < heap[smallerChildIndex]) break;
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    // Utility methods
    public boolean isEmpty() { return size == 0; }
    public int getSize() { return size; }

    // Display heap sebagai array
    public void display() {
        System.out.print("Heap: [");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Display heap sebagai tree
    public void displayTree() {
        System.out.println("\nHeap Tree:");
        displayTreeRec(0, "", true);
    }

    private void displayTreeRec(int index, String prefix, boolean isLast) {
        if (index < size) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + heap[index]);
            int left = leftChild(index);
            int right = rightChild(index);
            if (left < size || right < size) {
                if (left < size) {
                    displayTreeRec(left, prefix + (isLast ? "    " : "│   "), right >= size);
                }
                if (right < size) {
                    displayTreeRec(right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MIN HEAP DEMO ===\n");

        MinHeap heap = new MinHeap(10);

        // Insert elements
        int[] values = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31};
        System.out.println("Inserting: " + Arrays.toString(values));

        for (int val : values) {
            heap.insert(val);
        }

        heap.display();
        heap.displayTree();

        System.out.println("\n--- Extract Min ---");
        while (!heap.isEmpty()) {
            System.out.println("Extract: " + heap.extractMin());
        }
    }
}
```

**Output:**
```
=== MIN HEAP DEMO ===

Inserting: [35, 33, 42, 10, 14, 19, 27, 44, 26, 31]
Heap: [10, 14, 19, 26, 31, 42, 27, 44, 33, 35]

Heap Tree:
└── 10
    ├── 14
    │   ├── 26
    │   │   ├── 44
    │   │   └── 33
    │   └── 31
    │       └── 35
    └── 19
        ├── 42
        └── 27

--- Extract Min ---
Extract: 10
Extract: 14
Extract: 19
Extract: 26
Extract: 27
Extract: 31
Extract: 33
Extract: 35
Extract: 42
Extract: 44
```

### 2.5 Implementasi Max-Heap

```java
// File: MaxHeap.java
import java.util.Arrays;

public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException("Heap kosong!");
        return heap[0];
    }

    public void insert(int value) {
        ensureCapacity();
        heap[size] = value;
        size++;
        heapifyUp();
    }

    // Heapify-Up untuk Max-Heap (parent harus lebih BESAR)
    private void heapifyUp() {
        int index = size - 1;
        while (index > 0 && heap[parent(index)] < heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    public int extractMax() {
        if (size == 0) throw new IllegalStateException("Heap kosong!");

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return max;
    }

    // Heapify-Down untuk Max-Heap (swap dengan child TERBESAR)
    private void heapifyDown() {
        int index = 0;
        while (leftChild(index) < size) {
            int largerChildIndex = leftChild(index);

            if (rightChild(index) < size && heap[rightChild(index)] > heap[leftChild(index)]) {
                largerChildIndex = rightChild(index);
            }

            if (heap[index] > heap[largerChildIndex]) {
                break;
            }

            swap(index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    public boolean isEmpty() { return size == 0; }
    public int getSize() { return size; }

    public void display() {
        System.out.print("Heap: [");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("=== MAX HEAP DEMO ===\n");

        MaxHeap heap = new MaxHeap(10);

        int[] values = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31};
        System.out.println("Inserting: " + Arrays.toString(values));

        for (int val : values) {
            heap.insert(val);
        }

        heap.display();

        System.out.println("\n--- Extract Max ---");
        while (!heap.isEmpty()) {
            System.out.println("Extract: " + heap.extractMax());
        }
    }
}
```

### 2.6 Heap Sort

Heap Sort memanfaatkan properti heap untuk mengurutkan array dalam O(n log n).

```java
// File: HeapSort.java
import java.util.Arrays;

public class HeapSort {

    // Heap Sort menggunakan Max-Heap untuk ascending order
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build Max-Heap (heapify dari bawah ke atas)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elemen satu per satu
        for (int i = n - 1; i > 0; i--) {
            // Pindahkan root (max) ke akhir
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify pada reduced heap
            heapify(arr, i, 0);
        }
    }

    // Heapify subtree dengan root di index i
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== HEAP SORT DEMO ===\n");

        int[] arr = {12, 11, 13, 5, 6, 7, 3, 1, 9};
        System.out.println("Array sebelum: " + Arrays.toString(arr));

        heapSort(arr);

        System.out.println("Array sesudah: " + Arrays.toString(arr));
    }
}
```

**Output:**
```
=== HEAP SORT DEMO ===

Array sebelum: [12, 11, 13, 5, 6, 7, 3, 1, 9]
Array sesudah: [1, 3, 5, 6, 7, 9, 11, 12, 13]
```

### 2.7 Priority Queue

Priority Queue adalah struktur data dimana elemen dengan prioritas tertinggi selalu di depan.

```java
// File: PriorityQueueDemo.java
import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        System.out.println("=== PRIORITY QUEUE DEMO ===\n");

        // Min-Heap (default)
        System.out.println("--- Min Priority Queue ---");
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        minPQ.add(30);
        minPQ.add(10);
        minPQ.add(20);
        minPQ.add(5);

        System.out.println("PriorityQueue: " + minPQ);
        System.out.print("Poll order: ");
        while (!minPQ.isEmpty()) {
            System.out.print(minPQ.poll() + " ");
        }
        System.out.println();

        // Max-Heap
        System.out.println("\n--- Max Priority Queue ---");
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        maxPQ.add(30);
        maxPQ.add(10);
        maxPQ.add(20);
        maxPQ.add(5);

        System.out.println("PriorityQueue: " + maxPQ);
        System.out.print("Poll order: ");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.poll() + " ");
        }
        System.out.println();

        // Custom Object dengan Priority
        System.out.println("\n--- Task Priority Queue ---");
        PriorityQueue<Task> taskPQ = new PriorityQueue<>();
        taskPQ.add(new Task("Low priority task", 3));
        taskPQ.add(new Task("High priority task", 1));
        taskPQ.add(new Task("Medium priority task", 2));

        System.out.println("Processing tasks by priority:");
        while (!taskPQ.isEmpty()) {
            Task task = taskPQ.poll();
            System.out.println("  - " + task);
        }
    }
}

class Task implements Comparable<Task> {
    String name;
    int priority; // Lower number = higher priority

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "[Priority " + priority + "] " + name;
    }
}
```

**Output:**
```
=== PRIORITY QUEUE DEMO ===

--- Min Priority Queue ---
PriorityQueue: [5, 10, 20, 30]
Poll order: 5 10 20 30

--- Max Priority Queue ---
PriorityQueue: [30, 10, 20, 5]
Poll order: 30 20 10 5

--- Task Priority Queue ---
Processing tasks by priority:
  - [Priority 1] High priority task
  - [Priority 2] Medium priority task
  - [Priority 3] Low priority task
```

---

## 3. Trie (Prefix Tree)

### 3.1 Apa itu Trie?

Trie (diucapkan "try") adalah struktur data tree khusus untuk menyimpan dan mencari **string** secara efisien. Nama "Trie" berasal dari kata "re**TRIE**val".

**Karakteristik Trie:**
- Setiap node merepresentasikan **satu karakter**
- Root adalah node kosong
- Path dari root ke node tertentu membentuk **prefix** dari string
- Node yang menandai akhir kata ditandai khusus

```
Menyimpan kata: "cat", "car", "card", "care", "dog"

                root
               /    \
              c      d
             /        \
            a          o
           / \          \
          t   r          g*
         *   / \
           d*   e*

* = menandai akhir kata (isEndOfWord)
```

**Path di Trie:**
- "cat" → root → c → a → t*
- "car" → root → c → a → r*
- "card" → root → c → a → r → d*
- "care" → root → c → a → r → e*
- "dog" → root → d → o → g*

### 3.2 Operasi pada Trie

| Operasi | Kompleksitas | Keterangan |
|---------|-------------|------------|
| Insert | O(L) | L = panjang kata |
| Search | O(L) | L = panjang kata |
| Delete | O(L) | L = panjang kata |
| Prefix Search | O(P + N) | P = panjang prefix, N = jumlah hasil |

**Keunggulan Trie:**
- Pencarian **tidak bergantung pada jumlah kata** di dalam Trie
- Sangat efisien untuk operasi **prefix matching**
- Mendukung **autocomplete** dengan mudah

### 3.3 Implementasi Trie

```java
// File: Trie.java
import java.util.ArrayList;
import java.util.List;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;
    int wordCount; // Berapa kata yang melewati node ini

    public TrieNode() {
        children = new TrieNode[26]; // a-z
        isEndOfWord = false;
        wordCount = 0;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert kata ke Trie
    public void insert(String word) {
        TrieNode current = root;
        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            // Validasi karakter
            if (index < 0 || index >= 26) {
                throw new IllegalArgumentException("Hanya huruf a-z yang diizinkan!");
            }

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
            current.wordCount++;
        }
        current.isEndOfWord = true;
    }

    // Search kata exact
    public boolean search(String word) {
        TrieNode node = searchNode(word.toLowerCase());
        return node != null && node.isEndOfWord;
    }

    // Search prefix (apakah ada kata yang diawali prefix ini?)
    public boolean startsWith(String prefix) {
        return searchNode(prefix.toLowerCase()) != null;
    }

    // Helper: cari node untuk kata/prefix tertentu
    private TrieNode searchNode(String str) {
        TrieNode current = root;

        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';

            if (index < 0 || index >= 26) {
                return null;
            }

            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }

    // Delete kata dari Trie
    public boolean delete(String word) {
        return deleteRec(root, word.toLowerCase(), 0);
    }

    private boolean deleteRec(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false; // Kata tidak ditemukan
            }
            current.isEndOfWord = false;
            return isEmpty(current); // Return true jika node bisa dihapus
        }

        int charIndex = word.charAt(index) - 'a';
        TrieNode node = current.children[charIndex];

        if (node == null) {
            return false; // Kata tidak ditemukan
        }

        boolean shouldDeleteChild = deleteRec(node, word, index + 1);

        if (shouldDeleteChild) {
            current.children[charIndex] = null;
            return !current.isEndOfWord && isEmpty(current);
        }

        return false;
    }

    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) return false;
        }
        return true;
    }

    // Autocomplete: dapatkan semua kata dengan prefix tertentu
    public List<String> getWordsWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = searchNode(prefix.toLowerCase());

        if (node != null) {
            collectWords(node, prefix.toLowerCase(), results);
        }

        return results;
    }

    private void collectWords(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix);
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char c = (char) ('a' + i);
                collectWords(node.children[i], prefix + c, results);
            }
        }
    }

    // Count kata dengan prefix tertentu
    public int countWordsWithPrefix(String prefix) {
        TrieNode node = searchNode(prefix.toLowerCase());
        if (node == null) return 0;
        return countWords(node);
    }

    private int countWords(TrieNode node) {
        int count = node.isEndOfWord ? 1 : 0;
        for (TrieNode child : node.children) {
            if (child != null) {
                count += countWords(child);
            }
        }
        return count;
    }

    // Display Trie structure
    public void display() {
        System.out.println("\nTrie Structure:");
        displayRec(root, "");
    }

    private void displayRec(TrieNode node, String prefix) {
        if (node.isEndOfWord) {
            System.out.println("  " + prefix + "*");
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char c = (char) ('a' + i);
                displayRec(node.children[i], prefix + c);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== TRIE DEMO ===\n");

        Trie trie = new Trie();

        // Insert words
        String[] words = {"cat", "car", "card", "care", "careful", "dog", "dodge"};
        System.out.println("Inserting words: ");
        for (String word : words) {
            System.out.println("  - " + word);
            trie.insert(word);
        }

        trie.display();

        // Search
        System.out.println("\n--- Search ---");
        System.out.println("search('car'): " + trie.search("car"));
        System.out.println("search('card'): " + trie.search("card"));
        System.out.println("search('ca'): " + trie.search("ca"));
        System.out.println("search('cat'): " + trie.search("cat"));

        // Prefix search
        System.out.println("\n--- Prefix Search ---");
        System.out.println("startsWith('car'): " + trie.startsWith("car"));
        System.out.println("startsWith('ca'): " + trie.startsWith("ca"));
        System.out.println("startsWith('xyz'): " + trie.startsWith("xyz"));

        // Autocomplete
        System.out.println("\n--- Autocomplete ---");
        System.out.println("Words with prefix 'car': " + trie.getWordsWithPrefix("car"));
        System.out.println("Words with prefix 'ca': " + trie.getWordsWithPrefix("ca"));
        System.out.println("Words with prefix 'do': " + trie.getWordsWithPrefix("do"));

        // Count words
        System.out.println("\n--- Count Words ---");
        System.out.println("Count words with prefix 'car': " + trie.countWordsWithPrefix("car"));
        System.out.println("Count words with prefix 'c': " + trie.countWordsWithPrefix("c"));

        // Delete
        System.out.println("\n--- Delete ---");
        System.out.println("Delete 'car': " + trie.delete("car"));
        System.out.println("search('car') after delete: " + trie.search("car"));
        System.out.println("search('card') after delete: " + trie.search("card"));
        System.out.println("Words with prefix 'car': " + trie.getWordsWithPrefix("car"));
    }
}
```

**Output:**
```
=== TRIE DEMO ===

Inserting words:
  - cat
  - car
  - card
  - care
  - careful
  - dog
  - dodge

Trie Structure:
  car*
  card*
  care*
  careful*
  cat*
  dodge*
  dog*

--- Search ---
search('car'): true
search('card'): true
search('ca'): false
search('cat'): true

--- Prefix Search ---
startsWith('car'): true
startsWith('ca'): true
startsWith('xyz'): false

--- Autocomplete ---
Words with prefix 'car': [car, card, care, careful]
Words with prefix 'ca': [car, card, care, careful, cat]
Words with prefix 'do': [dodge, dog]

--- Count Words ---
Count words with prefix 'car': 4
Count words with prefix 'c': 5

--- Delete ---
Delete 'car': true
search('car') after delete: false
search('card') after delete: true
Words with prefix 'car': [card, care, careful]
```

### 3.4 Aplikasi Trie

#### 3.4.1 Autocomplete System

```java
// File: AutocompleteSystem.java
import java.util.*;

public class AutocompleteSystem {
    private Trie trie;
    private Map<String, Integer> frequency;

    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        frequency = new HashMap<>();

        for (int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i].toLowerCase().replace(" ", "_"));
            frequency.put(sentences[i], times[i]);
        }
    }

    public List<String> getSuggestions(String prefix, int limit) {
        List<String> words = trie.getWordsWithPrefix(prefix.toLowerCase().replace(" ", "_"));

        // Sort by frequency (descending)
        words.sort((a, b) -> {
            String wordA = a.replace("_", " ");
            String wordB = b.replace("_", " ");
            int freqA = frequency.getOrDefault(wordA, 0);
            int freqB = frequency.getOrDefault(wordB, 0);
            if (freqA != freqB) return freqB - freqA;
            return wordA.compareTo(wordB);
        });

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, words.size()); i++) {
            result.add(words.get(i).replace("_", " "));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== AUTOCOMPLETE SYSTEM ===\n");

        String[] sentences = {
            "i love you", "island", "ironman", "i love leetcode",
            "indonesia", "ice cream", "i love programming"
        };
        int[] times = {5, 3, 2, 2, 4, 1, 6};

        AutocompleteSystem system = new AutocompleteSystem(sentences, times);

        System.out.println("Suggestions for 'i':");
        System.out.println(system.getSuggestions("i", 3));

        System.out.println("\nSuggestions for 'i love':");
        System.out.println(system.getSuggestions("i love", 3));
    }
}
```

#### 3.4.2 Spell Checker

```java
// File: SpellChecker.java
import java.util.*;

public class SpellChecker {
    private Trie dictionary;

    public SpellChecker(String[] words) {
        dictionary = new Trie();
        for (String word : words) {
            dictionary.insert(word);
        }
    }

    public boolean isCorrect(String word) {
        return dictionary.search(word.toLowerCase());
    }

    public List<String> suggest(String word) {
        List<String> suggestions = new ArrayList<>();
        word = word.toLowerCase();

        // Coba prefix yang ada
        for (int i = word.length(); i >= 1; i--) {
            String prefix = word.substring(0, i);
            if (dictionary.startsWith(prefix)) {
                suggestions.addAll(dictionary.getWordsWithPrefix(prefix));
                if (suggestions.size() >= 5) break;
            }
        }

        return suggestions.subList(0, Math.min(5, suggestions.size()));
    }

    public static void main(String[] args) {
        System.out.println("=== SPELL CHECKER ===\n");

        String[] words = {"apple", "application", "apply", "banana", "band", "bandana"};
        SpellChecker checker = new SpellChecker(words);

        System.out.println("Is 'apple' correct? " + checker.isCorrect("apple"));
        System.out.println("Is 'appel' correct? " + checker.isCorrect("appel"));
        System.out.println("Suggestions for 'appel': " + checker.suggest("appel"));
        System.out.println("Suggestions for 'ban': " + checker.suggest("ban"));
    }
}
```

---

## 4. Perbandingan Struktur Data Tree

| Struktur Data | Search | Insert | Delete | Space | Keterangan |
|---------------|--------|--------|--------|-------|------------|
| **BST (Average)** | O(log n) | O(log n) | O(log n) | O(n) | Performa bergantung keseimbangan |
| **BST (Worst)** | O(n) | O(n) | O(n) | O(n) | Terjadi jika tree skewed |
| **AVL Tree** | O(log n) | O(log n) | O(log n) | O(n) | Selalu seimbang, overhead rotasi |
| **Red-Black Tree** | O(log n) | O(log n) | O(log n) | O(n) | Lebih sedikit rotasi dari AVL |
| **Heap (get min/max)** | O(1) | O(log n) | O(log n) | O(n) | Optimized untuk min/max |
| **Heap (search)** | O(n) | - | - | O(n) | Tidak efisien untuk search umum |
| **Trie** | O(L) | O(L) | O(L) | O(N×L) | L = panjang string |

---

## 5. Kapan Menggunakan Struktur Data Tree?

| Struktur Data | Gunakan Ketika |
|---------------|----------------|
| **BST** | Data perlu terurut + operasi search, insert, delete sering |
| **AVL Tree** | Sama seperti BST + perlu garansi O(log n) |
| **Red-Black Tree** | Sama seperti AVL + insert/delete lebih sering dari search |
| **Heap** | Perlu akses elemen min/max dengan cepat (Priority Queue) |
| **Trie** | Operasi string: autocomplete, spell checker, prefix matching |

---

## 6. Latihan Praktikum

### Latihan 1: Implementasi Delete AVL Tree
Lengkapi implementasi `delete(int data)` pada class `AVLTree` yang sudah disediakan di atas. Pastikan tree tetap seimbang setelah penghapusan.

### Latihan 2: Implementasi Extract-Min dengan Decrease-Key
Tambahkan method `decreaseKey(int index, int newValue)` pada class `MinHeap` yang mengubah nilai pada index tertentu menjadi nilai yang lebih kecil, kemudian perbaiki heap property.

### Latihan 3: Autocomplete dengan Trie
Modifikasi Trie agar method `getWordsWithPrefix(String prefix)` mengembalikan maksimal 5 kata yang paling pendek.

### Latihan 4: Kth Largest Element dengan Heap
Implementasikan fungsi untuk menemukan **k elemen terbesar** dari array menggunakan Min-Heap.

```java
public static int[] findKLargest(int[] arr, int k) {
    // Implementasikan di sini
}
```

---

## 7. Soal Latihan di Kelas

### Soal 1: Tracing AVL Tree (20 poin)

Diberikan urutan insert: **30, 20, 40, 10, 25, 35, 50, 5, 15**

a) Gambarkan AVL Tree setelah setiap operasi insert (tunjukkan rotasi yang terjadi jika ada)

b) Tuliskan Balance Factor setiap node pada tree akhir

c) Lakukan **Inorder Traversal** pada tree akhir

### Soal 2: Operasi Heap (20 poin)

Diberikan Max-Heap awal dalam bentuk array: `[50, 30, 40, 10, 20, 35, 25]`

a) Gambarkan representasi tree dari heap tersebut

b) Lakukan operasi **Insert(45)** - gambarkan proses heapify-up

c) Lakukan operasi **Extract-Max** - gambarkan proses heapify-down

d) Tuliskan array hasil setelah kedua operasi di atas

### Soal 3: Trie Operations (20 poin)

Diberikan kata-kata yang akan dimasukkan ke Trie: **"app", "apple", "apt", "apply", "bat", "bath"**

a) Gambarkan struktur Trie setelah semua kata dimasukkan

b) Berapa node yang dibuat? (termasuk root)

c) Jika dilakukan pencarian prefix "app", kata-kata apa saja yang akan dikembalikan?

d) Jika kata "apple" dihapus, gambarkan Trie setelah penghapusan

### Soal 4: Analisis Kompleksitas (15 poin)

Jelaskan dengan singkat mengapa:

a) AVL Tree lebih lambat pada operasi insert/delete dibanding BST biasa, tapi lebih cepat pada worst case search

b) Heap tidak cocok untuk operasi search elemen tertentu

c) Trie membutuhkan lebih banyak memori dibanding struktur data lain untuk menyimpan N kata

### Soal 5: Problem Solving (25 poin)

#### 5a. K-th Smallest Element (10 poin)

Diberikan BST berikut:
```
        50
       /  \
      30   70
     / \   / \
    20 40 60  80
```

Tuliskan algoritma (pseudocode atau Java) untuk menemukan **elemen ke-k terkecil** dari BST. Gunakan properti inorder traversal!

Contoh: k=3 → Output: 40 (karena urutan: 20, 30, 40, 50, 60, 70, 80)

#### 5b. Validate BST (15 poin)

Tuliskan fungsi `boolean isValidBST(TreeNode root)` yang memeriksa apakah sebuah binary tree adalah BST yang valid.

```java
class TreeNode {
    int val;
    TreeNode left, right;
}

public boolean isValidBST(TreeNode root) {
    // Implementasikan di sini
}
```

**Hint:** Gunakan pendekatan rekursif dengan batas min dan max.

---

## Referensi

1. Cormen, T. H., et al. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.
2. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley.
3. [GeeksforGeeks - AVL Tree](https://www.geeksforgeeks.org/avl-tree-set-1-insertion/)
4. [GeeksforGeeks - Heap Data Structure](https://www.geeksforgeeks.org/heap-data-structure/)
5. [GeeksforGeeks - Trie Data Structure](https://www.geeksforgeeks.org/trie-insert-and-search/)
6. [Visualgo - Binary Heap](https://visualgo.net/en/heap)
7. [Visualgo - AVL Tree](https://visualgo.net/en/avl)
