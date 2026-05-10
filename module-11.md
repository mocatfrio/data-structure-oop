# Modul 11. Tree Lanjutan (AVL, Heap, Trie)

## Daftar Isi
1. [AVL Tree](#avl-tree)
2. [Heap](#heap)
3. [Trie](#trie)
4. [Kompleksitas Struktur Data Tree](#kompleksitas-struktur-data-tree)
5. [Kapan Menggunakan Struktur Data Tree](#kapan-menggunakan-struktur-data-tree)

## AVL Tree

### Apa itu AVL Tree?
AVL Tree dinamakan dari penemunya (Adelson-Velsky dan Landis) dan merupakan self-balancing Binary Search Tree yang pertama kali ditemukan. Dalam AVL Tree, perbedaan tinggi (height) antara left subtree dan right subtree dari setiap node tidak boleh lebih dari 1 (balance factor = -1, 0, atau 1).

Jika setelah operasi insert atau delete tree menjadi tidak seimbang, AVL Tree akan melakukan **Rotasi** untuk mengembalikan keseimbangan.

### Rotasi AVL
Ada 4 jenis rotasi dasar pada AVL Tree:
1. **Left Rotation (LL)**
2. **Right Rotation (RR)**
3. **Left-Right Rotation (LR)**
4. **Right-Left Rotation (RL)**

### Mengapa AVL Tree?
Keuntungan utama AVL Tree adalah operasinya (Search, Insert, Delete) selalu terjamin memiliki kompleksitas waktu **O(log n)** dalam kondisi terburuk.

### Contoh Implementasi Struktur AVL Tree (OOP Java)

Berikut adalah contoh kerangka (*skeleton*) class untuk mengimplementasikan Node dan rotasi pada AVL Tree menggunakan OOP.

```java
class AVLNode {
    int data;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1; // Tinggi awal node baru selalu 1
    }
}

public class AVLTree {
    private AVLNode root;

    // Utility untuk mendapatkan tinggi node
    private int getHeight(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }

    // Mendapatkan balance factor untuk mengecek keseimbangan
    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // Contoh Rotasi Kanan (Right Rotate)
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Lakukan rotasi
        x.right = y;
        y.left = T2;

        // Update tinggi setelah rotasi
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x; // Return root yang baru
    }

    // Insert, Delete, dan rotasi lainnya diimplementasikan di sini...
}
```


## Heap

### Apa itu Heap?
Heap adalah Complete Binary Tree khusus yang memenuhi **Heap Property**:
- **Max-Heap**: Nilai dari setiap parent selalu lebih besar atau sama dengan child-nya. Root adalah elemen maksimum.
- **Min-Heap**: Nilai dari setiap parent selalu lebih kecil atau sama dengan child-nya. Root adalah elemen minimum.

### Operasi pada Heap
1. **Insert**: Menambahkan di posisi terakhir, lalu lakukan proses *Heapify-Up* (bubble up).
2. **Extract**: Mengambil root, menaruh elemen terakhir ke root, lalu lakukan proses *Heapify-Down* (bubble down).

### Kapan Menggunakan Heap?
Heap sangat efisien untuk mengimplementasikan **Priority Queue** dan algoritma **Heap Sort**. Kompleksitas pengambilan elemen maks/min adalah O(1) dan O(log n) untuk insert/delete.

### Contoh Implementasi Min-Heap (OOP Java)

Heap umumnya diimplementasikan menggunakan **Array** karena sifatnya yang merupakan *Complete Binary Tree*.

```java
public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return (2 * i) + 1; }
    private int rightChild(int i) { return (2 * i) + 2; }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(int value) {
        if (size == capacity) throw new IllegalStateException("Heap penuh!");
        
        // Masukkan elemen di posisi terakhir
        heap[size] = value;
        int current = size;
        size++;

        // Heapify-Up (perbaiki posisi jika elemen baru lebih kecil dari parent)
        while (current != 0 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
    
    // Metode Extract-Min dan Heapify-Down diimplementasikan di sini...
}
```


## Trie

### Apa itu Trie?
Trie (sering disebut Prefix Tree) adalah jenis tree pencarian yang node-nya menyimpan karakter-karakter penyusun sebuah string. Alih-alih menyimpan nilai utuh di setiap node, *path* dari root menuju suatu node merepresentasikan awalan (prefix) atau sebuah string penuh.

### Visualisasi Trie
Menyimpan kata "cat", "car", "dog":
```
        root
       /    \
      c      d
     /        \
    a          o
   / \          \
  t   r          g
```

### Kapan Menggunakan Trie?
Trie sangat optimal untuk skenario pencarian string dan prefix:
1. **Autocomplete**: Menyarankan kata-kata berdasarkan huruf yang sudah diketik.
2. **Spell Checker**: Memeriksa apakah kata terdapat di dalam kamus.
3. **Prefix Matching**: IP routing (Longest prefix matching).
Waktu pencarian di Trie adalah **O(L)** di mana L adalah panjang kata, independen dari seberapa banyak jumlah kata di dalam kamus.

### Contoh Implementasi Trie (OOP Java)

```java
class TrieNode {
    // Array berukuran 26 untuk merepresentasikan huruf 'a' sampai 'z'
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;

    public TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a'; // Asumsi hanya huruf kecil (a-z)
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current != null && current.isEndOfWord;
    }
}
```


## Kompleksitas Struktur Data Tree

Pemilihan struktur data Tree sangat bergantung pada kompleksitas waktu yang ditawarkan untuk operasi-operasi utamanya. Berikut adalah perbandingan kompleksitas waktu dan ruang untuk masing-masing jenis Tree:

| Struktur Data | Pencarian (Search) | Penyisipan (Insert) | Penghapusan (Delete) | Space Complexity | Keterangan Tambahan |
|---------------|--------------------|---------------------|----------------------|------------------|---------------------|
| **Binary Search Tree (Average)** | O(log n) | O(log n) | O(log n) | O(n) | Performa bergantung pada keseimbangan tree |
| **Binary Search Tree (Worst)** | O(n) | O(n) | O(n) | O(n) | Terjadi jika tree berubah menjadi *skewed* (seperti Linked List) |
| **AVL Tree** | O(log n) | O(log n) | O(log n) | O(n) | Selalu terjamin seimbang dengan perbedaan tinggi maksimal 1 |
| **Min/Max Heap** | O(n)* | O(log n) | O(log n)** | O(n) | *Pencarian elemen spesifik tidak efisien; **Menghapus root (elemen min/max) adalah O(log n) |
| **Trie** | O(L) | O(L) | O(L) | O(N * L) | `L` adalah panjang string, `N` adalah jumlah string. Efisien untuk string namun memakan memori |


## Kapan Menggunakan Struktur Data Tree?

| Struktur Data | Kasus Penggunaan (Use Case) Utama |
|---------------|----------------------------------|
| **Binary Search Tree** | Ketika data perlu sering dicari, dimasukkan, dihapus dan dipertahankan dalam keadaan terurut. |
| **AVL Tree / R-B Tree**| Sama seperti BST, namun ketika kita perlu garansi performa O(log n) di semua kasus. |
| **Heap** | Ketika kita perlu menemukan elemen minimum / maksimum dengan sangat cepat (Priority Queue). |
| **Trie** | Ketika kita berurusan dengan manipulasi string, dictionary, autocomplete, dan prefix matching. |

## 6. Latihan Praktikum

### Latihan 1: Implementasi Delete AVL Tree
Lanjutkan kode struktur `AVLTree` untuk mendukung operasi `delete(int data)` dengan penyesuaian rotasi (balancing) pasca-penghapusan.

### Latihan 2: Implementasi Max-Heap
Modifikasilah *skeleton code* Min-Heap yang diberikan agar menjadi **Max-Heap**, di mana elemen terbesar selalu berada di root.

### Latihan 3: Autocomplete Trie
Modifikasi Trie agar memiliki method `getWordsWithPrefix(String prefix)` yang mengembalikan daftar string yang memuat semua kata yang diawali dengan *prefix* tersebut.

## Referensi

1. Cormen, T. H., et al. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.
2. [GeeksforGeeks - AVL Tree](https://www.geeksforgeeks.org/avl-tree-set-1-insertion/)
3. [GeeksforGeeks - Trie Data Structure](https://www.geeksforgeeks.org/trie-insert-and-search/)
