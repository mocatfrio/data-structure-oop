# Module 10: Rekursi

## Daftar Isi
1. [Pengenalan Rekursi](#pengenalan-rekursi)
   - [Apa itu Rekursi?](#apa-itu-rekursi)
   - [Komponen Rekursi](#komponen-rekursi)
   - [Cara Kerja Rekursi](#cara-kerja-rekursi)
   - [Call Stack](#call-stack)
2. [Rekursi Dasar](#rekursi-dasar)
   - [Factorial](#factorial)
   - [Fibonacci](#fibonacci)
   - [Sum 1 to N](#sum-1-to-n)
   - [Power (Pangkat)](#power-pangkat)
   - [GCD](#gcd)
3. [Rekursi pada Array](#rekursi-pada-array)
   - [Sum Array](#sum-array)
   - [Find Max/Min](#find-maxmin)
   - [Linear Search](#linear-search)
   - [Binary Search](#binary-search)
4. [Rekursi pada String](#rekursi-pada-string)
   - [Reverse String](#reverse-string)
   - [Palindrome Check](#palindrome-check)
   - [Count Character](#count-character)
5. [Rekursi pada Linked List](#rekursi-pada-linked-list)
   - [Print & Reverse Print](#print--reverse-print)
   - [Length & Sum](#length--sum)
   - [Reverse List](#reverse-list)
6. [Backtracking](#backtracking)
   - [Konsep Backtracking](#konsep-backtracking)
   - [Generate Subsets](#generate-subsets)
   - [Generate Permutations](#generate-permutations)
   - [N-Queens Problem](#n-queens-problem)
7. [Tail Recursion](#tail-recursion)
   - [Apa itu Tail Recursion?](#apa-itu-tail-recursion)
   - [Menggunakan Accumulator](#menggunakan-accumulator)
8. [Rekursi vs Iterasi](#rekursi-vs-iterasi)
9. [Tips dan Best Practices](#tips-dan-best-practices)
10. [Latihan Praktikum](#latihan-praktikum)

---

## Pengenalan Rekursi

### Apa itu Rekursi?

**Rekursi** adalah teknik pemrograman dimana sebuah fungsi **memanggil dirinya sendiri** untuk menyelesaikan masalah yang lebih kecil dari masalah aslinya.

```
Rekursi = Memecah masalah besar menjadi masalah kecil yang SERUPA
```

### Analogi Rekursi

Bayangkan kamu ingin menghitung jumlah orang dalam antrian panjang:

```
Cara Iteratif:
- Hitung satu per satu dari depan ke belakang
- 1, 2, 3, 4, ... n

Cara Rekursif:
- Tanya orang di depanmu: "Berapa nomormu?"
- Jika dia menjawab "5", maka nomormu adalah 6
- Setiap orang melakukan hal yang sama ke orang di depannya
- Orang paling depan menjawab "1" (BASE CASE)
```

### Komponen Rekursi

Setiap fungsi rekursif HARUS memiliki dua komponen:

```
┌─────────────────────────────────────────────────────────┐
│                    FUNGSI REKURSIF                      │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  1. BASE CASE (Kondisi Berhenti)                        │
│     - Kondisi dimana rekursi berhenti                   │
│     - Mengembalikan nilai tanpa memanggil diri sendiri  │
│     - WAJIB ADA, tanpa ini = infinite recursion!        │
│                                                         │
│  2. RECURSIVE CASE (Kasus Rekursif)                     │
│     - Memanggil diri sendiri dengan input lebih kecil   │
│     - Mendekatkan ke base case                          │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

### Cara Kerja Rekursi

```java
int factorial(int n) {
    // BASE CASE
    if (n <= 1) {
        return 1;
    }
    // RECURSIVE CASE
    return n * factorial(n - 1);
}
```

**Visualisasi factorial(4):**

```
factorial(4)
│
├── 4 * factorial(3)
│        │
│        ├── 3 * factorial(2)
│        │        │
│        │        ├── 2 * factorial(1)
│        │        │        │
│        │        │        └── return 1  ← BASE CASE
│        │        │
│        │        └── return 2 * 1 = 2
│        │
│        └── return 3 * 2 = 6
│
└── return 4 * 6 = 24

Hasil: 24
```

### Call Stack

Rekursi menggunakan **call stack** untuk menyimpan setiap pemanggilan fungsi:

```
                    CALL STACK
                ┌───────────────┐
                │ factorial(1)  │ ← TOP (dipanggil terakhir)
                ├───────────────┤
                │ factorial(2)  │
                ├───────────────┤
                │ factorial(3)  │
                ├───────────────┤
                │ factorial(4)  │ ← BOTTOM (dipanggil pertama)
                └───────────────┘

Setiap pemanggilan disimpan di stack sampai base case tercapai,
lalu hasil dikembalikan dari atas ke bawah.
```

**Stack Overflow:**
```
Jika tidak ada base case atau base case tidak tercapai:
- Stack terus bertambah tanpa henti
- Menyebabkan StackOverflowError
```

---

## Rekursi Dasar

### Factorial

```
n! = n × (n-1) × (n-2) × ... × 2 × 1
5! = 5 × 4 × 3 × 2 × 1 = 120

Base case: 0! = 1, 1! = 1
Recursive case: n! = n × (n-1)!
```

```java
public static int factorial(int n) {
    // Base case
    if (n <= 1) {
        return 1;
    }
    // Recursive case
    return n * factorial(n - 1);
}

// Contoh pemanggilan
System.out.println(factorial(5)); // Output: 120
```

### Fibonacci

```
Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
F(n) = F(n-1) + F(n-2)

Base case: F(0) = 0, F(1) = 1
Recursive case: F(n) = F(n-1) + F(n-2)
```

```java
public static int fibonacci(int n) {
    // Base cases
    if (n == 0) return 0;
    if (n == 1) return 1;

    // Recursive case
    return fibonacci(n - 1) + fibonacci(n - 2);
}

// Contoh
for (int i = 0; i < 10; i++) {
    System.out.print(fibonacci(i) + " ");
}
// Output: 0 1 1 2 3 5 8 13 21 34
```

**Visualisasi Fibonacci(5):**

```
                    fib(5)
                   /      \
               fib(4)    fib(3)
              /    \      /    \
          fib(3)  fib(2) fib(2) fib(1)
          /   \    /  \   /  \
      fib(2) fib(1) ... ... ...

Banyak perhitungan berulang! (inefficient)
```

### Sum 1 to N

```
sum(n) = 1 + 2 + 3 + ... + n
sum(5) = 1 + 2 + 3 + 4 + 5 = 15

Base case: sum(1) = 1
Recursive case: sum(n) = n + sum(n-1)
```

```java
public static int sumToN(int n) {
    if (n == 1) {
        return 1;
    }
    return n + sumToN(n - 1);
}
```

### Power (Pangkat)

```
power(base, exp) = base^exp
power(2, 4) = 2^4 = 16

Base case: x^0 = 1
Recursive case: x^n = x × x^(n-1)
```

```java
public static int power(int base, int exponent) {
    if (exponent == 0) {
        return 1;
    }
    return base * power(base, exponent - 1);
}

// Versi optimized O(log n)
public static int powerOptimized(int base, int exponent) {
    if (exponent == 0) return 1;

    int half = powerOptimized(base, exponent / 2);

    if (exponent % 2 == 0) {
        return half * half;
    } else {
        return base * half * half;
    }
}
```

### GCD

```
GCD menggunakan algoritma Euclidean:
gcd(a, b) = gcd(b, a % b)

Base case: gcd(a, 0) = a
```

```java
public static int gcd(int a, int b) {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}

// gcd(48, 18) = gcd(18, 12) = gcd(12, 6) = gcd(6, 0) = 6
```

---

## Rekursi pada Array

### Pola Umum Rekursi Array

```java
void processArray(int[] arr, int index) {
    // Base case: sudah melewati semua elemen
    if (index >= arr.length) {
        return;
    }

    // Process current element
    process(arr[index]);

    // Recurse on rest
    processArray(arr, index + 1);
}
```

### Sum Array

```java
public static int sumArray(int[] arr, int index) {
    // Base case
    if (index >= arr.length) {
        return 0;
    }
    // Current + sum of rest
    return arr[index] + sumArray(arr, index + 1);
}

// Cara pakai
int[] arr = {1, 2, 3, 4, 5};
System.out.println(sumArray(arr, 0)); // Output: 15
```

### Find Max/Min

```java
public static int findMax(int[] arr, int index) {
    // Base case: last element
    if (index == arr.length - 1) {
        return arr[index];
    }

    // Bandingkan current dengan max dari rest
    int maxRest = findMax(arr, index + 1);
    return Math.max(arr[index], maxRest);
}
```

### Linear Search

```java
public static int linearSearch(int[] arr, int target, int index) {
    // Base case: tidak ditemukan
    if (index >= arr.length) {
        return -1;
    }

    // Ditemukan
    if (arr[index] == target) {
        return index;
    }

    // Cari di sisa array
    return linearSearch(arr, target, index + 1);
}
```

### Binary Search

```java
public static int binarySearch(int[] arr, int target, int left, int right) {
    // Base case: tidak ditemukan
    if (left > right) {
        return -1;
    }

    int mid = left + (right - left) / 2;

    if (arr[mid] == target) {
        return mid;
    } else if (arr[mid] > target) {
        return binarySearch(arr, target, left, mid - 1);
    } else {
        return binarySearch(arr, target, mid + 1, right);
    }
}
```

---

## Rekursi pada String

### Reverse String

```java
public static String reverseString(String str) {
    // Base case
    if (str.length() <= 1) {
        return str;
    }

    // Pindahkan karakter pertama ke belakang
    return reverseString(str.substring(1)) + str.charAt(0);
}

// reverseString("hello")
// = reverseString("ello") + "h"
// = reverseString("llo") + "e" + "h"
// = reverseString("lo") + "l" + "e" + "h"
// = reverseString("o") + "l" + "l" + "e" + "h"
// = "o" + "l" + "l" + "e" + "h"
// = "olleh"
```

### Palindrome Check

```java
public static boolean isPalindrome(String str) {
    // Base case
    if (str.length() <= 1) {
        return true;
    }

    // Compare first and last
    if (str.charAt(0) != str.charAt(str.length() - 1)) {
        return false;
    }

    // Check middle substring
    return isPalindrome(str.substring(1, str.length() - 1));
}

// isPalindrome("radar") = true
// isPalindrome("hello") = false
```

### Count Character

```java
public static int countChar(String str, char c) {
    if (str.isEmpty()) {
        return 0;
    }

    int count = (str.charAt(0) == c) ? 1 : 0;
    return count + countChar(str.substring(1), c);
}

// countChar("hello", 'l') = 2
```

---

## Rekursi pada Linked List

### Mengapa Penting?

```
Linked List dan Tree adalah struktur REKURSIF secara alami:
- Linked List = Node + Linked List (sisanya)
- Binary Tree = Node + Left Subtree + Right Subtree

Menguasai rekursi pada Linked List = Persiapan untuk Tree!
```

### Print & Reverse Print

```java
// Print list: 1 -> 2 -> 3 -> null
public static void printList(Node head) {
    if (head == null) {
        System.out.println("null");
        return;
    }

    System.out.print(head.data + " -> ");
    printList(head.next);
}

// Print reverse: 3 2 1
public static void printReverse(Node head) {
    if (head == null) {
        return;
    }

    printReverse(head.next);  // Recurse first
    System.out.print(head.data + " ");  // Then print
}
```

**Visualisasi printReverse:**

```
printReverse(1 -> 2 -> 3)
│
├── printReverse(2 -> 3)
│   │
│   ├── printReverse(3)
│   │   │
│   │   ├── printReverse(null)
│   │   │   └── return
│   │   │
│   │   └── print(3)  ← Output: "3"
│   │
│   └── print(2)  ← Output: "2"
│
└── print(1)  ← Output: "1"

Hasil: "3 2 1"
```

### Length & Sum

```java
public static int length(Node head) {
    if (head == null) {
        return 0;
    }
    return 1 + length(head.next);
}

public static int sum(Node head) {
    if (head == null) {
        return 0;
    }
    return head.data + sum(head.next);
}
```

### Reverse List

```java
public static Node reverseList(Node head) {
    // Base case: empty or single node
    if (head == null || head.next == null) {
        return head;
    }

    // Reverse the rest
    Node newHead = reverseList(head.next);

    // Put current at the end
    head.next.next = head;
    head.next = null;

    return newHead;
}
```

**Visualisasi Reverse List:**

```
Original: 1 -> 2 -> 3 -> null

reverseList(1 -> 2 -> 3)
│
├── newHead = reverseList(2 -> 3)
│   │
│   ├── newHead = reverseList(3)
│   │   └── return 3 (base case)
│   │
│   │   2.next.next = 2  →  3 -> 2
│   │   2.next = null    →  3 -> 2 -> null
│   │
│   └── return 3 -> 2
│
│   1.next.next = 1  →  3 -> 2 -> 1
│   1.next = null    →  3 -> 2 -> 1 -> null
│
└── return 3 -> 2 -> 1

Hasil: 3 -> 2 -> 1 -> null
```

---

## Backtracking

### Konsep Backtracking

**Backtracking** adalah teknik untuk mencoba semua kemungkinan solusi dan "mundur" (backtrack) ketika menemukan jalan buntu.

```
Pola Backtracking:
1. Pilih (choose)
2. Eksplorasi (explore)
3. Batalkan pilihan jika tidak berhasil (unchoose/backtrack)
```

```java
void backtrack(state, choices) {
    if (isSolution(state)) {
        addToResults(state);
        return;
    }

    for (choice in choices) {
        if (isValid(choice)) {
            makeChoice(choice);        // CHOOSE
            backtrack(newState);       // EXPLORE
            undoChoice(choice);        // UNCHOOSE (backtrack)
        }
    }
}
```

### Generate Subsets

```java
// Generate semua subset: {1,2} -> {}, {1}, {2}, {1,2}

public static void generateSubsets(int[] arr, int index,
                                   List<Integer> current,
                                   List<List<Integer>> result) {
    // Base case
    if (index == arr.length) {
        result.add(new ArrayList<>(current));
        return;
    }

    // Pilihan 1: TIDAK termasuk elemen ini
    generateSubsets(arr, index + 1, current, result);

    // Pilihan 2: TERMASUK elemen ini
    current.add(arr[index]);                          // Choose
    generateSubsets(arr, index + 1, current, result); // Explore
    current.remove(current.size() - 1);               // Unchoose
}
```

### Generate Permutations

```java
// Generate semua permutasi: {1,2,3} -> 6 permutasi

public static void generatePermutations(int[] arr, int start,
                                        List<List<Integer>> result) {
    if (start == arr.length) {
        List<Integer> perm = new ArrayList<>();
        for (int num : arr) perm.add(num);
        result.add(perm);
        return;
    }

    for (int i = start; i < arr.length; i++) {
        swap(arr, start, i);                    // Choose
        generatePermutations(arr, start + 1, result); // Explore
        swap(arr, start, i);                    // Unchoose
    }
}
```

### N-Queens Problem

```java
// Letakkan N queens di papan NxN tanpa saling menyerang

public static boolean solveNQueens(int[][] board, int row) {
    int n = board.length;

    // Base case: semua queen ditempatkan
    if (row == n) {
        return true;
    }

    // Coba setiap kolom
    for (int col = 0; col < n; col++) {
        if (isSafe(board, row, col)) {
            board[row][col] = 1;              // Choose

            if (solveNQueens(board, row + 1)) { // Explore
                return true;
            }

            board[row][col] = 0;              // Unchoose (backtrack)
        }
    }

    return false;
}
```

**Contoh solusi 4-Queens:**

```
. Q . .
. . . Q
Q . . .
. . Q .
```

---

## Tail Recursion

### Apa itu Tail Recursion?

**Tail Recursion** adalah rekursi dimana pemanggilan rekursif adalah operasi **TERAKHIR** dalam fungsi.

```java
// NON-TAIL recursive
int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);  // Ada operasi (n *) SETELAH rekursi
}

// TAIL recursive
int factorialTail(int n, int acc) {
    if (n <= 1) return acc;
    return factorialTail(n - 1, n * acc);  // Rekursi adalah operasi TERAKHIR
}
```

### Menggunakan Accumulator

```java
// Non-tail: hasil dikumpulkan saat "naik"
// Tail: hasil dikumpulkan dalam accumulator saat "turun"

// SUM - Non-tail
int sumNonTail(int n) {
    if (n == 0) return 0;
    return n + sumNonTail(n - 1);  // + setelah rekursi
}

// SUM - Tail (dengan accumulator)
int sumTail(int n, int acc) {
    if (n == 0) return acc;
    return sumTail(n - 1, acc + n);  // Rekursi terakhir
}

int sum(int n) {
    return sumTail(n, 0);  // Wrapper
}
```

### Perbandingan Fibonacci

```java
// Non-tail: O(2^n) - sangat lambat
int fibNonTail(int n) {
    if (n <= 1) return n;
    return fibNonTail(n-1) + fibNonTail(n-2);
}

// Tail: O(n) - efisien
int fibTail(int n, int a, int b) {
    if (n == 0) return a;
    if (n == 1) return b;
    return fibTail(n - 1, b, a + b);
}

int fibonacci(int n) {
    return fibTail(n, 0, 1);
}
```

---

## Rekursi vs Iterasi

### Perbandingan

| Aspek | Rekursi | Iterasi |
|-------|---------|---------|
| **Readability** | Lebih natural untuk masalah rekursif | Lebih straightforward |
| **Memory** | Menggunakan call stack | Minimal (variabel loop) |
| **Performance** | Overhead function call | Biasanya lebih cepat |
| **Stack Overflow** | Mungkin terjadi | Tidak |
| **Debugging** | Bisa lebih sulit | Lebih mudah |

### Kapan Gunakan Rekursi?

```
GUNAKAN REKURSI untuk:
✓ Masalah dengan struktur rekursif natural (Tree, Graph, Linked List)
✓ Divide and conquer algorithms
✓ Backtracking problems
✓ Masalah yang lebih mudah dipahami secara rekursif

GUNAKAN ITERASI untuk:
✓ Loop sederhana
✓ Ketika performance critical
✓ Ketika kedalaman rekursi bisa sangat besar
✓ Ketika masalah mudah diiterasi
```

### Konversi Rekursi ke Iterasi

Setiap rekursi dapat dikonversi ke iterasi menggunakan **explicit stack**:

```java
// Rekursif
void printReverse(Node head) {
    if (head == null) return;
    printReverse(head.next);
    System.out.print(head.data);
}

// Iteratif dengan Stack
void printReverseIterative(Node head) {
    Stack<Integer> stack = new Stack<>();
    Node current = head;

    while (current != null) {
        stack.push(current.data);
        current = current.next;
    }

    while (!stack.isEmpty()) {
        System.out.print(stack.pop());
    }
}
```

---

## Tips dan Best Practices

### 1. Selalu Definisikan Base Case

```java
// ❌ SALAH - tidak ada base case
int factorial(int n) {
    return n * factorial(n - 1);  // Infinite recursion!
}

// ✓ BENAR
int factorial(int n) {
    if (n <= 1) return 1;  // Base case
    return n * factorial(n - 1);
}
```

### 2. Pastikan Konvergen ke Base Case

```java
// ❌ SALAH - tidak menuju base case
int sum(int n) {
    if (n == 0) return 0;
    return n + sum(n + 1);  // n bertambah, tidak menuju 0!
}

// ✓ BENAR
int sum(int n) {
    if (n == 0) return 0;
    return n + sum(n - 1);  // n berkurang menuju 0
}
```

### 3. Gunakan Helper Function dengan Parameter

```java
// Public interface bersih
public int sumArray(int[] arr) {
    return sumArrayHelper(arr, 0);
}

// Private helper dengan index
private int sumArrayHelper(int[] arr, int index) {
    if (index >= arr.length) return 0;
    return arr[index] + sumArrayHelper(arr, index + 1);
}
```

### 4. Hindari Perhitungan Berulang

```java
// ❌ Fibonacci naif - O(2^n)
int fib(int n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);  // Banyak perhitungan berulang
}

// ✓ Dengan memoization - O(n)
int fib(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != 0) return memo[n];
    memo[n] = fib(n-1, memo) + fib(n-2, memo);
    return memo[n];
}
```

### 5. Pertimbangkan Stack Depth

```java
// Untuk n besar, gunakan iterasi atau tail recursion
// Java default stack size: ~512KB - 1MB
// Sekitar 5000-20000 recursive calls sebelum overflow
```

---

## Persiapan untuk Tree

Rekursi sangat penting untuk Tree karena:

```java
// Struktur Tree adalah REKURSIF:
class TreeNode {
    int data;
    TreeNode left;   // Subtree (juga Tree!)
    TreeNode right;  // Subtree (juga Tree!)
}

// Hampir semua operasi Tree menggunakan rekursi:

// Traversal
void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    print(node.data);
    inorder(node.right);
}

// Height
int height(TreeNode node) {
    if (node == null) return -1;
    return max(height(node.left), height(node.right)) + 1;
}

// Count nodes
int count(TreeNode node) {
    if (node == null) return 0;
    return 1 + count(node.left) + count(node.right);
}

// Search in BST
boolean search(TreeNode node, int target) {
    if (node == null) return false;
    if (node.data == target) return true;
    if (target < node.data) return search(node.left, target);
    return search(node.right, target);
}
```

---

## Latihan Praktikum

### Latihan 1: Hitung Digit

```java
// Hitung jumlah digit dalam angka
// countDigits(12345) = 5

public static int countDigits(int n) {
    if (n < 10) {
        return 1;
    }
    return 1 + countDigits(n / 10);
}
```

### Latihan 2: Sum of Digits

```java
// Jumlahkan semua digit
// sumDigits(123) = 1 + 2 + 3 = 6

public static int sumDigits(int n) {
    if (n < 10) {
        return n;
    }
    return (n % 10) + sumDigits(n / 10);
}
```

### Latihan 3: Check Array Sorted

```java
public static boolean isSorted(int[] arr, int index) {
    if (index >= arr.length - 1) {
        return true;
    }

    if (arr[index] > arr[index + 1]) {
        return false;
    }

    return isSorted(arr, index + 1);
}
```

### Latihan 4: Print Pattern

```java
// printStars(4) prints:
// ****
// ***
// **
// *

public static void printStars(int n) {
    if (n == 0) return;

    for (int i = 0; i < n; i++) {
        System.out.print("*");
    }
    System.out.println();

    printStars(n - 1);
}
```

### Latihan 5: Tower of Hanoi

```java
public static void hanoi(int n, char from, char to, char aux) {
    if (n == 1) {
        System.out.println("Move disk 1 from " + from + " to " + to);
        return;
    }

    hanoi(n - 1, from, aux, to);
    System.out.println("Move disk " + n + " from " + from + " to " + to);
    hanoi(n - 1, aux, to, from);
}

// hanoi(3, 'A', 'C', 'B');
```

---

## Ringkasan

### Key Points

1. **Rekursi = Fungsi memanggil dirinya sendiri**
2. **Dua komponen wajib: Base Case + Recursive Case**
3. **Call stack menyimpan state setiap pemanggilan**
4. **Tail recursion lebih efisien (rekursi di akhir)**
5. **Backtracking = try all + undo if fail**

### Pola Umum

```java
// Array
process(arr, index) {
    if (index >= length) return;
    // process arr[index]
    process(arr, index + 1);
}

// String
process(str) {
    if (str.isEmpty()) return;
    // process str.charAt(0)
    process(str.substring(1));
}

// Linked List
process(node) {
    if (node == null) return;
    // process node.data
    process(node.next);
}

// Tree (preview)
process(node) {
    if (node == null) return;
    process(node.left);
    // process node.data
    process(node.right);
}
```

---

## Tugas Praktikum

1. **Implementasikan fungsi `multiply(a, b)`** menggunakan rekursi (tanpa operator *)

2. **Buat fungsi untuk mengecek apakah string adalah anagram** secara rekursif

3. **Implementasikan Merge Sort** menggunakan rekursi

4. **Buat program Sudoku Solver** menggunakan backtracking

5. **Implementasikan fungsi untuk menghitung semua path dari (0,0) ke (m,n)** dalam grid dimana hanya bisa bergerak kanan atau bawah

Selamat belajar! Kuasai rekursi sebelum melanjutkan ke Tree!
