# Modul 15. Sorting Algorithms

## Daftar Isi
- [1. Pengenalan Sorting](#1-pengenalan-sorting)
  - [1.1 Apa itu Sorting?](#11-apa-itu-sorting)
  - [1.2 Mengapa Sorting Penting?](#12-mengapa-sorting-penting)
  - [1.3 Klasifikasi Algoritma Sorting](#13-klasifikasi-algoritma-sorting)
  - [1.4 Kriteria Pemilihan Algoritma Sorting](#14-kriteria-pemilihan-algoritma-sorting)
- [2. Bubble Sort](#2-bubble-sort)
  - [2.1 Konsep Bubble Sort](#21-konsep-bubble-sort)
  - [2.2 Algoritma Bubble Sort](#22-algoritma-bubble-sort)
  - [2.3 Implementasi Bubble Sort](#23-implementasi-bubble-sort)
  - [2.4 Optimasi Bubble Sort](#24-optimasi-bubble-sort)
  - [2.5 Kompleksitas Bubble Sort](#25-kompleksitas-bubble-sort)
- [3. Selection Sort](#3-selection-sort)
  - [3.1 Konsep Selection Sort](#31-konsep-selection-sort)
  - [3.2 Algoritma Selection Sort](#32-algoritma-selection-sort)
  - [3.3 Implementasi Selection Sort](#33-implementasi-selection-sort)
  - [3.4 Kompleksitas Selection Sort](#34-kompleksitas-selection-sort)
- [4. Insertion Sort](#4-insertion-sort)
  - [4.1 Konsep Insertion Sort](#41-konsep-insertion-sort)
  - [4.2 Algoritma Insertion Sort](#42-algoritma-insertion-sort)
  - [4.3 Implementasi Insertion Sort](#43-implementasi-insertion-sort)
  - [4.4 Kompleksitas Insertion Sort](#44-kompleksitas-insertion-sort)
- [5. Merge Sort](#5-merge-sort)
  - [5.1 Konsep Merge Sort](#51-konsep-merge-sort)
  - [5.2 Algoritma Merge Sort](#52-algoritma-merge-sort)
  - [5.3 Implementasi Merge Sort](#53-implementasi-merge-sort)
  - [5.4 Kompleksitas Merge Sort](#54-kompleksitas-merge-sort)
- [6. Quick Sort](#6-quick-sort)
  - [6.1 Konsep Quick Sort](#61-konsep-quick-sort)
  - [6.2 Algoritma Quick Sort](#62-algoritma-quick-sort)
  - [6.3 Implementasi Quick Sort](#63-implementasi-quick-sort)
  - [6.4 Pemilihan Pivot](#64-pemilihan-pivot)
  - [6.5 Kompleksitas Quick Sort](#65-kompleksitas-quick-sort)
- [7. Counting Sort](#7-counting-sort)
  - [7.1 Konsep Counting Sort](#71-konsep-counting-sort)
  - [7.2 Algoritma Counting Sort](#72-algoritma-counting-sort)
  - [7.3 Implementasi Counting Sort](#73-implementasi-counting-sort)
  - [7.4 Kompleksitas Counting Sort](#74-kompleksitas-counting-sort)
- [8. Radix Sort](#8-radix-sort)
  - [8.1 Konsep Radix Sort](#81-konsep-radix-sort)
  - [8.2 Algoritma Radix Sort](#82-algoritma-radix-sort)
  - [8.3 Implementasi Radix Sort](#83-implementasi-radix-sort)
  - [8.4 Kompleksitas Radix Sort](#84-kompleksitas-radix-sort)
- [9. Perbandingan Algoritma Sorting](#9-perbandingan-algoritma-sorting)
- [10. Stability dalam Sorting](#10-stability-dalam-sorting)
- [11. Kapan Menggunakan Algoritma Mana?](#11-kapan-menggunakan-algoritma-mana)
- [12. Sorting di Java Collections](#12-sorting-di-java-collections)
- [13. Latihan Praktikum](#13-latihan-praktikum)
- [14. Soal Latihan di Kelas](#14-soal-latihan-di-kelas)
- [Referensi](#referensi)

---

## 1. Pengenalan Sorting

### 1.1 Apa itu Sorting?

**Sorting** adalah proses mengatur elemen-elemen dalam suatu koleksi data ke dalam urutan tertentu, baik **ascending** (menaik) maupun **descending** (menurun).

```
Array tidak terurut:    [64, 25, 12, 22, 11]

Ascending (menaik):     [11, 12, 22, 25, 64]
Descending (menurun):   [64, 25, 22, 12, 11]
```

### 1.2 Mengapa Sorting Penting?

1. **Pencarian Lebih Efisien**
   - Data terurut memungkinkan Binary Search O(log n)
   - Tanpa sorting, hanya bisa Linear Search O(n)

2. **Pengorganisasian Data**
   - Menampilkan data secara terstruktur
   - Memudahkan analisis dan visualisasi

3. **Preprocessing untuk Algoritma Lain**
   - Merge operations
   - Finding duplicates
   - Closest pair problems

4. **Aplikasi Dunia Nyata**
   - Database queries (ORDER BY)
   - File management
   - Ranking systems
   - Scheduling

### 1.3 Klasifikasi Algoritma Sorting

```
Algoritma Sorting
├── Comparison-Based (Berbasis Perbandingan)
│   ├── Simple Sorts (O(n²))
│   │   ├── Bubble Sort
│   │   ├── Selection Sort
│   │   └── Insertion Sort
│   │
│   └── Efficient Sorts (O(n log n))
│       ├── Merge Sort
│       ├── Quick Sort
│       └── Heap Sort
│
└── Non-Comparison Based
    ├── Counting Sort - O(n + k)
    ├── Radix Sort - O(d × (n + k))
    └── Bucket Sort - O(n + k)
```

### 1.4 Kriteria Pemilihan Algoritma Sorting

| Kriteria | Penjelasan |
|----------|------------|
| **Time Complexity** | Seberapa cepat algoritma berjalan |
| **Space Complexity** | Berapa banyak memori tambahan yang dibutuhkan |
| **Stability** | Apakah urutan elemen dengan nilai sama dipertahankan |
| **In-place** | Apakah sorting dilakukan tanpa memori tambahan signifikan |
| **Adaptivity** | Apakah performa lebih baik untuk data yang hampir terurut |
| **Data Size** | Ukuran data yang akan di-sort |
| **Data Type** | Jenis data (integer, string, object) |

---

## 2. Bubble Sort

### 2.1 Konsep Bubble Sort

**Bubble Sort** adalah algoritma sorting paling sederhana yang bekerja dengan cara membandingkan dan menukar pasangan elemen yang berdekatan secara berulang hingga seluruh array terurut.

**Analogi:** Seperti gelembung (bubble) yang naik ke permukaan air - elemen terbesar "menggelembung" ke akhir array.

```
Pass 1: Elemen terbesar "naik" ke posisi terakhir
Pass 2: Elemen terbesar kedua "naik" ke posisi kedua terakhir
... dan seterusnya
```

### 2.2 Algoritma Bubble Sort

```
BubbleSort(arr, n):
    for i = 0 to n-2:
        for j = 0 to n-2-i:
            if arr[j] > arr[j+1]:
                swap(arr[j], arr[j+1])
```

**Visualisasi:**
```
Array awal: [64, 34, 25, 12, 22, 11, 90]

Pass 1:
[64, 34, 25, 12, 22, 11, 90]  → Compare 64, 34 → Swap
[34, 64, 25, 12, 22, 11, 90]  → Compare 64, 25 → Swap
[34, 25, 64, 12, 22, 11, 90]  → Compare 64, 12 → Swap
[34, 25, 12, 64, 22, 11, 90]  → Compare 64, 22 → Swap
[34, 25, 12, 22, 64, 11, 90]  → Compare 64, 11 → Swap
[34, 25, 12, 22, 11, 64, 90]  → Compare 64, 90 → No swap
[34, 25, 12, 22, 11, 64, 90]  → 90 sudah di posisi akhir ✓

Pass 2:
[34, 25, 12, 22, 11, 64, 90]  → Compare dan swap...
[25, 12, 22, 11, 34, 64, 90]  → 64 sudah di posisi ✓

... lanjutkan sampai terurut

Hasil: [11, 12, 22, 25, 34, 64, 90]
```

### 2.3 Implementasi Bubble Sort

```java
// File: BubbleSort.java
import java.util.Arrays;

public class BubbleSort {

    // Bubble Sort dasar
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Bubble Sort dengan visualisasi
    public static void bubbleSortVisualized(int[] arr) {
        int n = arr.length;
        int passCount = 0;
        int swapCount = 0;

        System.out.println("Array awal: " + Arrays.toString(arr));
        System.out.println();

        for (int i = 0; i < n - 1; i++) {
            passCount++;
            System.out.println("=== Pass " + passCount + " ===");

            for (int j = 0; j < n - 1 - i; j++) {
                System.out.print("Compare arr[" + j + "]=" + arr[j] +
                               " dengan arr[" + (j+1) + "]=" + arr[j+1] + " → ");

                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                    System.out.println("Swap!");
                } else {
                    System.out.println("No swap");
                }
                System.out.println("  Array: " + Arrays.toString(arr));
            }
            System.out.println("Setelah Pass " + passCount + ": " + Arrays.toString(arr));
            System.out.println();
        }

        System.out.println("Array terurut: " + Arrays.toString(arr));
        System.out.println("Total passes: " + passCount);
        System.out.println("Total swaps: " + swapCount);
    }

    public static void main(String[] args) {
        System.out.println("=== BUBBLE SORT ===\n");

        // Contoh dengan visualisasi
        int[] arr1 = {64, 34, 25, 12, 22};
        bubbleSortVisualized(arr1);

        // Contoh tanpa visualisasi
        System.out.println("\n--- Test Case Lainnya ---");
        int[] arr2 = {5, 1, 4, 2, 8};
        System.out.println("Sebelum: " + Arrays.toString(arr2));
        bubbleSort(arr2);
        System.out.println("Sesudah: " + Arrays.toString(arr2));
    }
}
```

### 2.4 Optimasi Bubble Sort

```java
// File: BubbleSortOptimized.java
import java.util.Arrays;

public class BubbleSortOptimized {

    // Optimasi 1: Early termination jika sudah terurut
    public static void bubbleSortOptimized(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // Jika tidak ada swap di pass ini, array sudah terurut
            if (!swapped) {
                System.out.println("Early termination at pass " + (i + 1));
                break;
            }
        }
    }

    // Optimasi 2: Track posisi swap terakhir
    public static void bubbleSortOptimized2(int[] arr) {
        int n = arr.length;
        int lastSwapIndex;

        while (n > 1) {
            lastSwapIndex = 0;

            for (int j = 0; j < n - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    lastSwapIndex = j + 1;
                }
            }

            // Elemen setelah lastSwapIndex sudah terurut
            n = lastSwapIndex;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== BUBBLE SORT OPTIMIZED ===\n");

        // Array yang hampir terurut
        int[] arr1 = {1, 2, 3, 5, 4};
        System.out.println("Array hampir terurut: " + Arrays.toString(arr1));
        bubbleSortOptimized(arr1);
        System.out.println("Hasil: " + Arrays.toString(arr1));

        System.out.println();

        // Array yang sudah terurut
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Array sudah terurut: " + Arrays.toString(arr2));
        bubbleSortOptimized(arr2);
        System.out.println("Hasil: " + Arrays.toString(arr2));
    }
}
```

### 2.5 Kompleksitas Bubble Sort

| Case | Time Complexity | Penjelasan |
|------|-----------------|------------|
| **Best** | O(n) | Array sudah terurut (dengan optimasi) |
| **Average** | O(n²) | Elemen tersebar acak |
| **Worst** | O(n²) | Array terurut terbalik |

| Space | In-place | Stable |
|-------|----------|--------|
| O(1) | Ya | Ya |

**Kapan Menggunakan Bubble Sort?**
- ✅ Data sangat kecil (n < 10)
- ✅ Data hampir terurut
- ✅ Untuk pembelajaran/edukasi
- ❌ Data besar (sangat lambat)

---

## 3. Selection Sort

### 3.1 Konsep Selection Sort

**Selection Sort** bekerja dengan cara memilih (select) elemen terkecil dari bagian yang belum terurut dan menempatkannya di posisi yang benar.

**Analogi:** Seperti menyortir kartu - cari kartu terkecil, taruh di depan, cari terkecil berikutnya, taruh di posisi kedua, dst.

```
[64, 25, 12, 22, 11]
 ↑
 Mulai dari sini, cari minimum dari seluruh array

[11, 25, 12, 22, 64]  → 11 adalah minimum, swap dengan posisi 0
     ↑
     Cari minimum dari posisi 1 sampai akhir

[11, 12, 25, 22, 64]  → 12 adalah minimum, swap dengan posisi 1
...
```

### 3.2 Algoritma Selection Sort

```
SelectionSort(arr, n):
    for i = 0 to n-2:
        minIndex = i
        for j = i+1 to n-1:
            if arr[j] < arr[minIndex]:
                minIndex = j
        swap(arr[i], arr[minIndex])
```

### 3.3 Implementasi Selection Sort

```java
// File: SelectionSort.java
import java.util.Arrays;

public class SelectionSort {

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Cari index elemen minimum
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap elemen minimum dengan elemen di posisi i
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void selectionSortVisualized(int[] arr) {
        int n = arr.length;

        System.out.println("Array awal: " + Arrays.toString(arr));
        System.out.println();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            System.out.println("=== Iterasi " + (i + 1) + " ===");
            System.out.println("Mencari minimum dari index " + i + " sampai " + (n-1));

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            System.out.println("Minimum ditemukan: " + arr[minIndex] + " di index " + minIndex);

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                System.out.println("Swap arr[" + i + "] dengan arr[" + minIndex + "]");
            } else {
                System.out.println("Tidak perlu swap (sudah di posisi benar)");
            }

            System.out.println("Array sekarang: " + Arrays.toString(arr));
            System.out.println();
        }

        System.out.println("Array terurut: " + Arrays.toString(arr));
    }

    // Selection Sort untuk mencari maximum (descending)
    public static void selectionSortDescending(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SELECTION SORT ===\n");

        int[] arr1 = {64, 25, 12, 22, 11};
        selectionSortVisualized(arr1);

        System.out.println("\n--- Descending Order ---");
        int[] arr2 = {64, 25, 12, 22, 11};
        System.out.println("Sebelum: " + Arrays.toString(arr2));
        selectionSortDescending(arr2);
        System.out.println("Sesudah: " + Arrays.toString(arr2));
    }
}
```

### 3.4 Kompleksitas Selection Sort

| Case | Time Complexity | Penjelasan |
|------|-----------------|------------|
| **Best** | O(n²) | Tetap harus mencari minimum |
| **Average** | O(n²) | Sama untuk semua input |
| **Worst** | O(n²) | Sama untuk semua input |

| Space | In-place | Stable |
|-------|----------|--------|
| O(1) | Ya | **Tidak** (bisa dimodifikasi) |

**Keunggulan Selection Sort:**
- Jumlah swap minimal: O(n)
- Cocok jika operasi swap mahal

**Kelemahan:**
- Selalu O(n²) bahkan untuk array terurut
- Tidak stable secara default

---

## 4. Insertion Sort

### 4.1 Konsep Insertion Sort

**Insertion Sort** bekerja seperti cara kita menyortir kartu di tangan - ambil satu kartu dan sisipkan di posisi yang benar di antara kartu yang sudah terurut.

**Analogi:** Menyortir kartu remi:
1. Kartu pertama sudah "terurut"
2. Ambil kartu kedua, bandingkan dengan pertama, sisipkan di posisi benar
3. Ambil kartu ketiga, sisipkan di posisi benar di antara kartu 1-2
4. ...dan seterusnya

```
[5, 2, 4, 6, 1, 3]

Iterasi 1: [5] sudah terurut, ambil 2
           [2, 5] - sisipkan 2 sebelum 5

Iterasi 2: [2, 5] sudah terurut, ambil 4
           [2, 4, 5] - sisipkan 4 antara 2 dan 5

Iterasi 3: [2, 4, 5] sudah terurut, ambil 6
           [2, 4, 5, 6] - 6 sudah di posisi benar

Iterasi 4: [2, 4, 5, 6] sudah terurut, ambil 1
           [1, 2, 4, 5, 6] - sisipkan 1 di awal

Iterasi 5: [1, 2, 4, 5, 6] sudah terurut, ambil 3
           [1, 2, 3, 4, 5, 6] - sisipkan 3 antara 2 dan 4
```

### 4.2 Algoritma Insertion Sort

```
InsertionSort(arr, n):
    for i = 1 to n-1:
        key = arr[i]
        j = i - 1

        // Geser elemen yang lebih besar dari key
        while j >= 0 AND arr[j] > key:
            arr[j + 1] = arr[j]
            j = j - 1

        arr[j + 1] = key
```

### 4.3 Implementasi Insertion Sort

```java
// File: InsertionSort.java
import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Geser elemen yang lebih besar dari key ke kanan
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Sisipkan key di posisi yang benar
            arr[j + 1] = key;
        }
    }

    public static void insertionSortVisualized(int[] arr) {
        int n = arr.length;

        System.out.println("Array awal: " + Arrays.toString(arr));
        System.out.println();

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            System.out.println("=== Iterasi " + i + " ===");
            System.out.println("Key: " + key + " (elemen di index " + i + ")");
            System.out.println("Bagian terurut: " + Arrays.toString(Arrays.copyOfRange(arr, 0, i)));

            int shifts = 0;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                shifts++;
            }

            arr[j + 1] = key;

            System.out.println("Geser " + shifts + " elemen");
            System.out.println("Sisipkan " + key + " di index " + (j + 1));
            System.out.println("Array sekarang: " + Arrays.toString(arr));
            System.out.println();
        }

        System.out.println("Array terurut: " + Arrays.toString(arr));
    }

    // Insertion Sort dengan Binary Search untuk mencari posisi
    public static void insertionSortBinary(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];

            // Binary search untuk mencari posisi
            int pos = binarySearch(arr, 0, i - 1, key);

            // Geser elemen
            for (int j = i - 1; j >= pos; j--) {
                arr[j + 1] = arr[j];
            }

            arr[pos] = key;
        }
    }

    private static int binarySearch(int[] arr, int low, int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                return mid + 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("=== INSERTION SORT ===\n");

        int[] arr1 = {12, 11, 13, 5, 6};
        insertionSortVisualized(arr1);

        System.out.println("\n--- Binary Insertion Sort ---");
        int[] arr2 = {37, 23, 0, 17, 12, 72, 31};
        System.out.println("Sebelum: " + Arrays.toString(arr2));
        insertionSortBinary(arr2);
        System.out.println("Sesudah: " + Arrays.toString(arr2));
    }
}
```

### 4.4 Kompleksitas Insertion Sort

| Case | Time Complexity | Penjelasan |
|------|-----------------|------------|
| **Best** | O(n) | Array sudah terurut (tidak ada shifting) |
| **Average** | O(n²) | Elemen tersebar acak |
| **Worst** | O(n²) | Array terurut terbalik |

| Space | In-place | Stable |
|-------|----------|--------|
| O(1) | Ya | Ya |

**Kapan Menggunakan Insertion Sort?**
- ✅ Data kecil (n < 50)
- ✅ Data hampir terurut (adaptive)
- ✅ Online algorithm (data datang satu per satu)
- ✅ Stable sorting dibutuhkan
- ❌ Data besar dan acak

---

## 5. Merge Sort

### 5.1 Konsep Merge Sort

**Merge Sort** adalah algoritma **Divide and Conquer** yang bekerja dengan:
1. **Divide**: Bagi array menjadi dua bagian
2. **Conquer**: Sort masing-masing bagian secara rekursif
3. **Combine**: Gabungkan (merge) dua bagian yang sudah terurut

```
         [38, 27, 43, 3, 9, 82, 10]
                    ↓ Divide
        [38, 27, 43, 3]    [9, 82, 10]
              ↓                  ↓
      [38, 27]  [43, 3]    [9, 82]  [10]
         ↓         ↓          ↓       ↓
      [38] [27] [43] [3]  [9] [82]  [10]
         ↓         ↓          ↓       ↓
      [27, 38]  [3, 43]   [9, 82]   [10]
              ↓                  ↓
        [3, 27, 38, 43]    [9, 10, 82]
                    ↓ Merge
         [3, 9, 10, 27, 38, 43, 82]
```

### 5.2 Algoritma Merge Sort

```
MergeSort(arr, left, right):
    if left < right:
        mid = (left + right) / 2

        // Divide
        MergeSort(arr, left, mid)
        MergeSort(arr, mid + 1, right)

        // Conquer (Merge)
        Merge(arr, left, mid, right)

Merge(arr, left, mid, right):
    // Buat array temporary
    // Copy elemen ke temp
    // Gabungkan kembali secara terurut
```

### 5.3 Implementasi Merge Sort

```java
// File: MergeSort.java
import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Sort bagian kiri
            mergeSortHelper(arr, left, mid);

            // Sort bagian kanan
            mergeSortHelper(arr, mid + 1, right);

            // Merge kedua bagian
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // Ukuran dua subarray
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Buat array temporary
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data ke array temporary
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // Merge kembali ke arr
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy sisa elemen leftArr (jika ada)
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy sisa elemen rightArr (jika ada)
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Merge Sort dengan visualisasi
    public static void mergeSortVisualized(int[] arr, int left, int right, int depth) {
        String indent = "  ".repeat(depth);

        if (left < right) {
            System.out.println(indent + "MergeSort(" +
                Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)) + ")");

            int mid = left + (right - left) / 2;

            mergeSortVisualized(arr, left, mid, depth + 1);
            mergeSortVisualized(arr, mid + 1, right, depth + 1);

            System.out.println(indent + "Merge: " +
                Arrays.toString(Arrays.copyOfRange(arr, left, mid + 1)) + " + " +
                Arrays.toString(Arrays.copyOfRange(arr, mid + 1, right + 1)));

            merge(arr, left, mid, right);

            System.out.println(indent + "Result: " +
                Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MERGE SORT ===\n");

        // Dengan visualisasi
        int[] arr1 = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Array awal: " + Arrays.toString(arr1));
        System.out.println("\nProses Merge Sort:");
        mergeSortVisualized(arr1, 0, arr1.length - 1, 0);
        System.out.println("\nArray terurut: " + Arrays.toString(arr1));

        // Tanpa visualisasi
        System.out.println("\n--- Test Case Lainnya ---");
        int[] arr2 = {12, 11, 13, 5, 6, 7};
        System.out.println("Sebelum: " + Arrays.toString(arr2));
        mergeSort(arr2);
        System.out.println("Sesudah: " + Arrays.toString(arr2));
    }
}
```

### 5.4 Kompleksitas Merge Sort

| Case | Time Complexity | Penjelasan |
|------|-----------------|------------|
| **Best** | O(n log n) | Konsisten |
| **Average** | O(n log n) | Konsisten |
| **Worst** | O(n log n) | Konsisten |

| Space | In-place | Stable |
|-------|----------|--------|
| O(n) | **Tidak** | Ya |

**Keunggulan Merge Sort:**
- Performa konsisten O(n log n)
- Stable sort
- Cocok untuk Linked List (tidak perlu extra space)
- Parallelizable

**Kelemahan:**
- Membutuhkan O(n) extra space
- Tidak cache-friendly untuk array

---

## 6. Quick Sort

### 6.1 Konsep Quick Sort

**Quick Sort** adalah algoritma **Divide and Conquer** yang bekerja dengan memilih **pivot** dan mempartisi array sehingga:
- Semua elemen lebih kecil dari pivot di sebelah kiri
- Semua elemen lebih besar dari pivot di sebelah kanan

```
[10, 80, 30, 90, 40, 50, 70]
                          ↑ pivot = 70

Partisi:
[10, 30, 40, 50, 70, 90, 80]
              ↑
        pivot di posisi benar

Lalu rekursif sort kiri [10, 30, 40, 50] dan kanan [90, 80]
```

### 6.2 Algoritma Quick Sort

```
QuickSort(arr, low, high):
    if low < high:
        // Partisi dan dapatkan posisi pivot
        pivotIndex = Partition(arr, low, high)

        // Sort bagian kiri dan kanan pivot
        QuickSort(arr, low, pivotIndex - 1)
        QuickSort(arr, pivotIndex + 1, high)

Partition(arr, low, high):
    pivot = arr[high]  // Pilih elemen terakhir sebagai pivot
    i = low - 1

    for j = low to high - 1:
        if arr[j] < pivot:
            i++
            swap(arr[i], arr[j])

    swap(arr[i + 1], arr[high])
    return i + 1
```

### 6.3 Implementasi Quick Sort

```java
// File: QuickSort.java
import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    // Quick Sort dengan pivot di akhir (Lomuto partition)
    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    // Lomuto Partition Scheme
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Hoare Partition Scheme (lebih efisien)
    private static int partitionHoare(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return j;
            }

            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Quick Sort dengan visualisasi
    public static void quickSortVisualized(int[] arr, int low, int high, int depth) {
        String indent = "  ".repeat(depth);

        if (low < high) {
            System.out.println(indent + "QuickSort(" +
                Arrays.toString(Arrays.copyOfRange(arr, low, high + 1)) + ")");
            System.out.println(indent + "Pivot: " + arr[high]);

            int pivotIndex = partition(arr, low, high);

            System.out.println(indent + "Setelah partisi: " +
                Arrays.toString(Arrays.copyOfRange(arr, low, high + 1)));
            System.out.println(indent + "Pivot di index: " + pivotIndex);
            System.out.println();

            quickSortVisualized(arr, low, pivotIndex - 1, depth + 1);
            quickSortVisualized(arr, pivotIndex + 1, high, depth + 1);
        }
    }

    // Quick Sort dengan random pivot (menghindari worst case)
    public static void quickSortRandomized(int[] arr, int low, int high) {
        if (low < high) {
            // Random pivot
            Random rand = new Random();
            int randomIndex = low + rand.nextInt(high - low + 1);
            swap(arr, randomIndex, high);

            int pivotIndex = partition(arr, low, high);

            quickSortRandomized(arr, low, pivotIndex - 1);
            quickSortRandomized(arr, pivotIndex + 1, high);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== QUICK SORT ===\n");

        // Dengan visualisasi
        int[] arr1 = {10, 80, 30, 90, 40, 50, 70};
        System.out.println("Array awal: " + Arrays.toString(arr1));
        System.out.println("\nProses Quick Sort:");
        quickSortVisualized(arr1, 0, arr1.length - 1, 0);
        System.out.println("Array terurut: " + Arrays.toString(arr1));

        // Randomized Quick Sort
        System.out.println("\n--- Randomized Quick Sort ---");
        int[] arr2 = {3, 6, 8, 10, 1, 2, 1};
        System.out.println("Sebelum: " + Arrays.toString(arr2));
        quickSortRandomized(arr2, 0, arr2.length - 1);
        System.out.println("Sesudah: " + Arrays.toString(arr2));
    }
}
```

### 6.4 Pemilihan Pivot

| Strategi | Kelebihan | Kekurangan |
|----------|-----------|------------|
| **Elemen pertama** | Simple | Worst case untuk sorted array |
| **Elemen terakhir** | Simple | Worst case untuk sorted array |
| **Elemen tengah** | Lebih baik untuk sorted | Masih bisa worst case |
| **Random** | Menghindari worst case | Overhead random |
| **Median of three** | Balance baik | Sedikit kompleks |

### 6.5 Kompleksitas Quick Sort

| Case | Time Complexity | Penjelasan |
|------|-----------------|------------|
| **Best** | O(n log n) | Pivot selalu di tengah |
| **Average** | O(n log n) | Random distribution |
| **Worst** | O(n²) | Pivot selalu min/max (sorted array) |

| Space | In-place | Stable |
|-------|----------|--------|
| O(log n) | Ya | **Tidak** |

**Keunggulan Quick Sort:**
- In-place (space efficient)
- Cache-friendly
- Tercepat dalam praktik untuk data acak

**Kelemahan:**
- Worst case O(n²)
- Tidak stable

---

## 7. Counting Sort

### 7.1 Konsep Counting Sort

**Counting Sort** adalah algoritma **non-comparison** yang menghitung frekuensi kemunculan setiap nilai, lalu menggunakan informasi tersebut untuk menempatkan elemen di posisi yang benar.

**Batasan:** Hanya bekerja untuk integer dengan range terbatas.

```
Array: [4, 2, 2, 8, 3, 3, 1]
Range: 1-8

Count array:
Index:  0  1  2  3  4  5  6  7  8
Count: [0, 1, 2, 2, 1, 0, 0, 0, 1]
        ↑  ↑  ↑  ↑  ↑           ↑
        0× 1× 2× 2× 1×          1×

Kumulatif:
Index:  0  1  2  3  4  5  6  7  8
Count: [0, 1, 3, 5, 6, 6, 6, 6, 7]

Build output dari belakang untuk stability
```

### 7.2 Algoritma Counting Sort

```
CountingSort(arr, n, max):
    // Buat count array
    count[0...max] = 0

    // Hitung frekuensi
    for i = 0 to n-1:
        count[arr[i]]++

    // Kumulatif count
    for i = 1 to max:
        count[i] += count[i-1]

    // Build output array (dari belakang untuk stability)
    for i = n-1 downto 0:
        output[count[arr[i]] - 1] = arr[i]
        count[arr[i]]--

    // Copy ke original array
    copy output to arr
```

### 7.3 Implementasi Counting Sort

```java
// File: CountingSort.java
import java.util.Arrays;

public class CountingSort {

    public static void countingSort(int[] arr) {
        if (arr.length == 0) return;

        // Cari nilai max dan min
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];

        // Hitung frekuensi
        for (int num : arr) {
            count[num - min]++;
        }

        // Kumulatif count
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Build output (dari belakang untuk stability)
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy ke array asli
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void countingSortVisualized(int[] arr) {
        System.out.println("Array awal: " + Arrays.toString(arr));

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        System.out.println("Min: " + min + ", Max: " + max + ", Range: " + range);

        int[] count = new int[range];
        int[] output = new int[arr.length];

        // Hitung frekuensi
        for (int num : arr) {
            count[num - min]++;
        }
        System.out.println("\nCount array (frekuensi): " + Arrays.toString(count));

        // Kumulatif count
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        System.out.println("Count array (kumulatif): " + Arrays.toString(count));

        // Build output
        System.out.println("\nBuilding output:");
        for (int i = arr.length - 1; i >= 0; i--) {
            int value = arr[i];
            int pos = count[value - min] - 1;
            output[pos] = value;
            count[value - min]--;
            System.out.println("  Tempatkan " + value + " di posisi " + pos);
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
        System.out.println("\nArray terurut: " + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        System.out.println("=== COUNTING SORT ===\n");

        int[] arr1 = {4, 2, 2, 8, 3, 3, 1};
        countingSortVisualized(arr1);

        System.out.println("\n--- Test dengan range lebih besar ---");
        int[] arr2 = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Sebelum: " + Arrays.toString(arr2));
        countingSort(arr2);
        System.out.println("Sesudah: " + Arrays.toString(arr2));
    }
}
```

### 7.4 Kompleksitas Counting Sort

| Case | Time Complexity | Penjelasan |
|------|-----------------|------------|
| **Best** | O(n + k) | k = range nilai |
| **Average** | O(n + k) | Konsisten |
| **Worst** | O(n + k) | Konsisten |

| Space | In-place | Stable |
|-------|----------|--------|
| O(n + k) | Tidak | Ya |

**Kapan Menggunakan Counting Sort?**
- ✅ Integer dengan range kecil (k ≈ n atau k < n log n)
- ✅ Stable sorting dibutuhkan
- ❌ Range besar (memori boros)
- ❌ Data floating point atau string

---

## 8. Radix Sort

### 8.1 Konsep Radix Sort

**Radix Sort** mengurutkan angka digit per digit, dari digit paling tidak signifikan (LSD - Least Significant Digit) atau paling signifikan (MSD - Most Significant Digit).

**Ide:** Gunakan stable sort (biasanya Counting Sort) untuk setiap digit.

```
Array: [170, 45, 75, 90, 802, 24, 2, 66]

Sort by 1s digit (satuan):
[170, 90, 802, 2, 24, 45, 75, 66]

Sort by 10s digit (puluhan):
[802, 2, 24, 45, 66, 170, 75, 90]

Sort by 100s digit (ratusan):
[2, 24, 45, 66, 75, 90, 170, 802]

Terurut!
```

### 8.2 Algoritma Radix Sort

```
RadixSort(arr):
    max = findMax(arr)

    // Sort berdasarkan setiap digit
    for exp = 1; max/exp > 0; exp *= 10:
        CountingSortByDigit(arr, exp)
```

### 8.3 Implementasi Radix Sort

```java
// File: RadixSort.java
import java.util.Arrays;

public class RadixSort {

    // Radix Sort untuk positive integers
    public static void radixSort(int[] arr) {
        if (arr.length == 0) return;

        int max = Arrays.stream(arr).max().getAsInt();

        // Sort berdasarkan setiap digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // digit 0-9

        // Hitung frekuensi digit
        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        // Kumulatif count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build output (dari belakang untuk stability)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy ke array asli
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void radixSortVisualized(int[] arr) {
        System.out.println("Array awal: " + Arrays.toString(arr));

        int max = Arrays.stream(arr).max().getAsInt();
        int digitCount = String.valueOf(max).length();

        System.out.println("Max: " + max + ", Jumlah digit: " + digitCount);
        System.out.println();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            System.out.println("=== Sort by digit posisi " + exp + " ===");

            System.out.print("Digit values: [");
            for (int i = 0; i < arr.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print((arr[i] / exp) % 10);
            }
            System.out.println("]");

            countingSortByDigit(arr, exp);
            System.out.println("Hasil: " + Arrays.toString(arr));
            System.out.println();
        }

        System.out.println("Array terurut: " + Arrays.toString(arr));
    }

    // Radix Sort untuk strings (fixed length)
    public static void radixSortStrings(String[] arr, int maxLength) {
        // Sort dari karakter terakhir ke pertama (LSD)
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            countingSortByChar(arr, pos);
        }
    }

    private static void countingSortByChar(String[] arr, int pos) {
        int n = arr.length;
        String[] output = new String[n];
        int[] count = new int[256]; // ASCII

        for (String s : arr) {
            char c = pos < s.length() ? s.charAt(pos) : 0;
            count[c]++;
        }

        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            char c = pos < arr[i].length() ? arr[i].charAt(pos) : 0;
            output[count[c] - 1] = arr[i];
            count[c]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        System.out.println("=== RADIX SORT ===\n");

        int[] arr1 = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSortVisualized(arr1);

        System.out.println("\n--- Test Case Lainnya ---");
        int[] arr2 = {329, 457, 657, 839, 436, 720, 355};
        System.out.println("Sebelum: " + Arrays.toString(arr2));
        radixSort(arr2);
        System.out.println("Sesudah: " + Arrays.toString(arr2));

        System.out.println("\n--- Radix Sort Strings ---");
        String[] words = {"cow", "dog", "sea", "rug", "row", "mob", "box", "tab"};
        System.out.println("Sebelum: " + Arrays.toString(words));
        radixSortStrings(words, 3);
        System.out.println("Sesudah: " + Arrays.toString(words));
    }
}
```

### 8.4 Kompleksitas Radix Sort

| Case | Time Complexity | Penjelasan |
|------|-----------------|------------|
| **Best** | O(d × (n + k)) | d = jumlah digit, k = base (10) |
| **Average** | O(d × (n + k)) | Konsisten |
| **Worst** | O(d × (n + k)) | Konsisten |

| Space | In-place | Stable |
|-------|----------|--------|
| O(n + k) | Tidak | Ya |

**Kapan Menggunakan Radix Sort?**
- ✅ Integer dengan jumlah digit terbatas
- ✅ Strings dengan panjang seragam
- ✅ n besar dan d kecil
- ❌ Floating point
- ❌ d >> log n

---

## 9. Perbandingan Algoritma Sorting

### Tabel Perbandingan Lengkap

| Algoritma | Best | Average | Worst | Space | Stable | In-place |
|-----------|------|---------|-------|-------|--------|----------|
| **Bubble Sort** | O(n) | O(n²) | O(n²) | O(1) | ✅ | ✅ |
| **Selection Sort** | O(n²) | O(n²) | O(n²) | O(1) | ❌ | ✅ |
| **Insertion Sort** | O(n) | O(n²) | O(n²) | O(1) | ✅ | ✅ |
| **Merge Sort** | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ | ❌ |
| **Quick Sort** | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ | ✅ |
| **Heap Sort** | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ | ✅ |
| **Counting Sort** | O(n + k) | O(n + k) | O(n + k) | O(n + k) | ✅ | ❌ |
| **Radix Sort** | O(d(n + k)) | O(d(n + k)) | O(d(n + k)) | O(n + k) | ✅ | ❌ |

### Visualisasi Kompleksitas

```
Untuk n = 1000:

O(n²):       1,000,000 operasi
O(n log n):  ~10,000 operasi
O(n + k):    ~1,000 + k operasi

Speedup O(n log n) vs O(n²): 100x lebih cepat!
```

---

## 10. Stability dalam Sorting

### Apa itu Stable Sort?

**Stable Sort** adalah algoritma sorting yang mempertahankan urutan relatif elemen dengan nilai yang sama.

```
Data: [(A, 2), (B, 1), (C, 2), (D, 1)]
Sort by number:

Stable:   [(B, 1), (D, 1), (A, 2), (C, 2)]
           ↑       ↑       ↑       ↑
          B dan D mempertahankan urutan asli
          A dan C mempertahankan urutan asli

Unstable: [(D, 1), (B, 1), (C, 2), (A, 2)]
          Urutan bisa berubah
```

### Mengapa Stability Penting?

1. **Multi-key sorting**
   - Sort by nama, lalu by tanggal
   - Stability menjaga urutan nama untuk tanggal sama

2. **Database operations**
   - Secondary index sorting

3. **Visualization**
   - Animasi yang konsisten

### Algoritma Stable vs Unstable

| Stable | Unstable |
|--------|----------|
| Bubble Sort | Selection Sort |
| Insertion Sort | Quick Sort |
| Merge Sort | Heap Sort |
| Counting Sort | |
| Radix Sort | |

---

## 11. Kapan Menggunakan Algoritma Mana?

### Decision Flowchart

```
                    Mulai
                      ↓
                n < 50?
               /       \
             Ya         Tidak
             ↓            ↓
       Insertion    Data hampir
         Sort        terurut?
                    /       \
                  Ya         Tidak
                  ↓            ↓
             Insertion   Integer dengan
               Sort      range kecil?
                        /          \
                      Ya            Tidak
                      ↓               ↓
               Counting/      Memory concern?
               Radix Sort    /            \
                           Ya             Tidak
                           ↓               ↓
                      Quick Sort      Merge Sort
```

### Rekomendasi Praktis

| Situasi | Rekomendasi | Alasan |
|---------|-------------|--------|
| Data kecil (n < 50) | Insertion Sort | Simple, overhead rendah |
| Data hampir terurut | Insertion Sort | O(n) best case |
| General purpose | Quick Sort | Tercepat rata-rata |
| Guaranteed O(n log n) | Merge Sort | Konsisten |
| Limited memory | Heap Sort | In-place O(1) |
| Stability needed | Merge Sort | Stable + efficient |
| Integer, range kecil | Counting Sort | O(n + k) |
| Integer, digit sedikit | Radix Sort | O(d × n) |
| Linked List | Merge Sort | Tidak perlu random access |

---

## 12. Sorting di Java Collections

### Built-in Sorting Methods

```java
// File: JavaSortingDemo.java
import java.util.*;

public class JavaSortingDemo {
    public static void main(String[] args) {
        // 1. Arrays.sort() untuk primitive arrays (Dual-Pivot Quick Sort)
        int[] primitiveArr = {5, 2, 8, 1, 9};
        Arrays.sort(primitiveArr);
        System.out.println("Primitive array sorted: " + Arrays.toString(primitiveArr));

        // 2. Arrays.sort() untuk Object arrays (TimSort - hybrid Merge+Insertion)
        Integer[] objectArr = {5, 2, 8, 1, 9};
        Arrays.sort(objectArr);
        System.out.println("Object array sorted: " + Arrays.toString(objectArr));

        // 3. Collections.sort() untuk List (TimSort)
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        Collections.sort(list);
        System.out.println("List sorted: " + list);

        // 4. Descending order
        Arrays.sort(objectArr, Collections.reverseOrder());
        System.out.println("Descending: " + Arrays.toString(objectArr));

        // 5. Custom Comparator
        String[] words = {"banana", "apple", "cherry", "date"};
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        System.out.println("By length: " + Arrays.toString(words));

        // 6. Sorting Objects
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35)
        );

        // Sort by age
        Collections.sort(people, Comparator.comparingInt(p -> p.age));
        System.out.println("By age: " + people);

        // Sort by name
        people.sort(Comparator.comparing(p -> p.name));
        System.out.println("By name: " + people);

        // Multiple criteria
        people.sort(Comparator
            .comparingInt((Person p) -> p.age)
            .thenComparing(p -> p.name));
        System.out.println("By age, then name: " + people);

        // 7. Parallel Sort (untuk array besar)
        int[] largeArr = new int[10000];
        Random rand = new Random();
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = rand.nextInt(10000);
        }
        Arrays.parallelSort(largeArr);
        System.out.println("Parallel sorted (first 10): " +
            Arrays.toString(Arrays.copyOf(largeArr, 10)));
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
```

### TimSort di Java

Java menggunakan **TimSort** untuk `Arrays.sort(Object[])` dan `Collections.sort()`:
- Hybrid dari Merge Sort dan Insertion Sort
- Stable
- O(n log n) worst case
- O(n) untuk data hampir terurut
- Digunakan juga di Python

---

## 13. Latihan Praktikum

### Latihan 1: Implementasi Bubble Sort dengan Counter
Modifikasi Bubble Sort untuk menghitung jumlah perbandingan dan swap yang dilakukan.

### Latihan 2: Selection Sort untuk Object
Implementasikan Selection Sort untuk mengurutkan array `Student` berdasarkan nilai (score).

### Latihan 3: Insertion Sort Descending
Modifikasi Insertion Sort untuk mengurutkan secara descending.

### Latihan 4: Merge Two Sorted Arrays
Implementasikan fungsi untuk menggabungkan dua array yang sudah terurut menjadi satu array terurut.

### Latihan 5: Quick Sort dengan Median-of-Three Pivot
Implementasikan Quick Sort yang menggunakan median dari elemen pertama, tengah, dan terakhir sebagai pivot.

### Latihan 6: Counting Sort untuk Nilai Negatif
Modifikasi Counting Sort agar bisa menangani array dengan nilai negatif.

### Latihan 7: Sort Colors (Dutch National Flag)
Diberikan array dengan nilai 0, 1, 2 (merah, putih, biru). Sort in-place dalam satu pass.

---

## 14. Soal Latihan di Kelas

### Soal 1: Tracing Algoritma (20 poin)

Diberikan array: `[29, 10, 14, 37, 13]`

a) Tunjukkan langkah-langkah **Bubble Sort** (setiap pass)

b) Tunjukkan langkah-langkah **Selection Sort** (setiap iterasi)

c) Tunjukkan langkah-langkah **Insertion Sort** (setiap penyisipan)

### Soal 2: Merge Sort Tracing (15 poin)

Diberikan array: `[38, 27, 43, 3, 9, 82, 10]`

a) Gambarkan pohon rekursi Merge Sort

b) Tunjukkan proses merge untuk menggabungkan `[3, 27, 38, 43]` dan `[9, 10, 82]`

c) Berapa total operasi merge yang dilakukan?

### Soal 3: Quick Sort Analysis (20 poin)

Diberikan array: `[3, 7, 8, 5, 2, 1, 9, 5, 4]` dengan pivot = elemen terakhir

a) Tunjukkan hasil partisi pertama

b) Apa worst case untuk Quick Sort? Berikan contoh input!

c) Bagaimana cara menghindari worst case?

### Soal 4: Counting dan Radix Sort (20 poin)

a) Untuk array `[4, 2, 2, 8, 3, 3, 1]`:
   - Tunjukkan count array setelah counting
   - Tunjukkan count array setelah kumulatif

b) Untuk array `[170, 45, 75, 90, 802, 24, 2, 66]`:
   - Tunjukkan hasil setiap pass Radix Sort

c) Kapan Counting Sort lebih baik dari Quick Sort? Jelaskan dengan contoh!

### Soal 5: Implementasi (25 poin)

a) Implementasikan fungsi `boolean isSorted(int[] arr)` yang mengecek apakah array sudah terurut.

b) Implementasikan fungsi `int[] mergeKSorted(int[][] arrays)` yang menggabungkan k array terurut menjadi satu array terurut.

c) Implementasikan **Cocktail Shaker Sort** (Bidirectional Bubble Sort) yang melakukan bubble sort dari dua arah.

```java
public static void cocktailSort(int[] arr) {
    // Implementasikan di sini
}
```

---

## Referensi

1. Cormen, T. H., et al. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.
2. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley.
3. [GeeksforGeeks - Sorting Algorithms](https://www.geeksforgeeks.org/sorting-algorithms/)
4. [Visualgo - Sorting](https://visualgo.net/en/sorting)
5. [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)
6. [Java Arrays.sort() Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html)
7. [TimSort - Python's Sorting Algorithm](https://en.wikipedia.org/wiki/Timsort)
