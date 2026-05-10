# Modul 1. Paradigma Pemrograman

## Daftar Isi

- [Apa itu Paradigma Pemrograman?](#1-apa-itu-paradigma-pemrograman)
- [Klasifikasi Paradigma Pemrograman](#2-klasifikasi-paradigma-pemrograman)
  - [Paradigma Imperatif](#21-paradigma-imperatif)
  - [Paradigma Deklaratif](#22-paradigma-deklaratif)
- [Paradigma Pemrograman Utama](#3-paradigma-pemrograman-utama)
  - [Pemrograman Prosedural](#31-pemrograman-prosedural)
  - [Pemrograman Berorientasi Objek (OOP)](#32-pemrograman-berorientasi-objek-oop)
  - [Pemrograman Fungsional](#33-pemrograman-fungsional)
- [Perbandingan Paradigma Pemrograman](#4-perbandingan-paradigma-pemrograman)
- [Multi-Paradigm Programming](#5-multi-paradigm-programming)
- [Paradigma Pemrograman Lainnya](#6-paradigma-pemrograman-lainnya)
- [Kesimpulan](#7-kesimpulan)
- [Latihan Praktikum](#8-latihan-praktikum)
- [Referensi](#9-referensi)

## 1. Apa itu Paradigma Pemrograman?

<p align="center">
  <img src="image/img2.webp" />
</p>

**Paradigma pemrograman** adalah suatu pendekatan atau cara berpikir dalam menulis kode program komputer. Paradigma ini mencakup aturan, konsep, dan pola desain yang mengarahkan proses pengembangan perangkat lunak.

Setiap paradigma pemrograman memiliki **karakteristik uniknya sendiri**, dan mempengaruhi bagaimana suatu program:
- **Dikonstruksi** (bagaimana struktur kode ditulis)
- **Diorganisir** (bagaimana kode dikelompokkan dan diatur)
- **Berinteraksi dengan data** (bagaimana data diproses dan dimanipulasi)

### Mengapa Paradigma Pemrograman Penting?

1. **Membantu memilih pendekatan yang tepat** untuk menyelesaikan masalah tertentu
2. **Meningkatkan kualitas kode** dengan mengikuti prinsip-prinsip yang sudah teruji
3. **Mempermudah kolaborasi** karena ada standar cara berpikir yang sama
4. **Memudahkan maintenance** karena kode lebih terstruktur

## 2. Klasifikasi Paradigma Pemrograman

<p align="center">
  <img src="image/img3.webp" />
</p>

Pada dasarnya, paradigma pemrograman dapat diklasifikasikan menjadi 2 kategori besar:

```
Paradigma Pemrograman
├── Imperatif (HOW - bagaimana melakukannya)
│   ├── Prosedural
│   └── Object-Oriented (OOP)
│
└── Deklaratif (WHAT - apa yang diinginkan)
    ├── Fungsional
    └── Logika (Logic Programming)
```

### 2.1 Paradigma Imperatif

Paradigma imperatif berfokus pada **"BAGAIMANA"** melakukan sesuatu. Programmer memberikan instruksi langkah demi langkah kepada komputer.

**Karakteristik:**
- **Fokus pada langkah-langkah eksplisit**: Programmer menuliskan urutan perintah yang harus dijalankan
- **Perubahan state (keadaan)**: Variabel dapat berubah nilainya selama eksekusi program
- **Control flow eksplisit**: Menggunakan loop (`for`, `while`), kondisional (`if-else`), dan lompatan (`goto`, `break`)
- **Mutable data**: Data dapat dimodifikasi setelah dibuat

**Bahasa yang mendukung:** C, C++, Java, Python, JavaScript

**Contoh - Menghitung total dari array:**

```cpp
#include <iostream>
#include <vector>
using namespace std;

int main() {
    vector<int> angka = {10, 20, 30, 40};  // Data yang akan diproses
    int total = 0;  // STATE: variabel yang akan berubah nilainya

    // LANGKAH EKSPLISIT: instruksi detail untuk menghitung total
    for (int nilai : angka) {   // Iterasi setiap elemen
        total += nilai;         // MUTASI: mengubah nilai variabel total
    }

    cout << "Total: " << total << endl;  // Output: 100
    return 0;
}
```

**Analogi:** Seperti resep masak yang menjelaskan langkah demi langkah:
1. Panaskan minyak
2. Tumis bawang hingga harum
3. Masukkan daging, aduk rata
4. Tambahkan air, tunggu mendidih
5. dst.

### 2.2 Paradigma Deklaratif

Paradigma deklaratif berfokus pada **"APA"** yang ingin dicapai, tanpa perlu menjelaskan langkah-langkah detailnya.

**Karakteristik:**
- **Fokus pada hasil akhir**: Programmer mendeskripsikan hasil yang diinginkan
- **Tidak ada perubahan state**: Menghindari side effects dan mutable state
- **Abstraksi tingkat tinggi**: Detail implementasi disembunyikan
- **Immutable data**: Data tidak berubah setelah dibuat

**Bahasa yang mendukung:** SQL, Haskell, Prolog, HTML/CSS

**Contoh - Query database dengan SQL:**

```sql
-- Hanya mendeskripsikan APA yang diinginkan
SELECT nama, nilai_akhir
FROM mahasiswa
WHERE nilai_akhir > 80
ORDER BY nilai_akhir DESC;
```

Perhatikan bahwa kita tidak perlu menjelaskan:
- Bagaimana database melakukan iterasi
- Bagaimana data difilter
- Bagaimana sorting dilakukan

**Analogi:** Seperti memesan makanan di restoran:
- "Saya mau nasi goreng spesial, pedas, tanpa telur"
- Anda tidak perlu menjelaskan cara memasaknya

### 2.3 Perbandingan Imperatif vs Deklaratif

| Aspek | Imperatif | Deklaratif |
|-------|-----------|------------|
| **Fokus** | HOW (bagaimana) | WHAT (apa) |
| **State** | Mutable (dapat berubah) | Immutable (tidak berubah) |
| **Kontrol** | Eksplisit (loop, if-else) | Implisit (abstraksi) |
| **Side Effects** | Umum terjadi | Dihindari |
| **Debugging** | Perlu trace step-by-step | Lebih mudah diprediksi |
| **Contoh** | C, Java, Python | SQL, Haskell, Prolog |

## 3. Paradigma Pemrograman Utama

Untuk memahami perbedaan paradigma, kita akan menggunakan studi kasus yang sama:

> **Sistem Manajemen Nilai Mahasiswa**
>
> Seorang dosen ingin mengembangkan sistem untuk:
> 1. Menyimpan data mahasiswa (nama dan nilai-nilai)
> 2. Menghitung nilai akhir dengan bobot:
>    - Tugas: 10%
>    - Quiz: 10%
>    - UTS: 20%
>    - UAS: 30%
>    - Praktikum: 30%
> 3. Menampilkan hasil nilai akhir setiap mahasiswa

### 3.1 Pemrograman Prosedural

**Pemrograman prosedural** adalah paradigma imperatif yang berfokus pada **prosedur/fungsi** sebagai unit utama pengorganisasian kode.

**Karakteristik:**
- Program terdiri dari **fungsi-fungsi** yang dipanggil secara berurutan
- Data dan fungsi **terpisah** (data diproses oleh fungsi)
- Menggunakan **top-down approach** (dari masalah besar ke sub-masalah kecil)
- **Code reuse** melalui pemanggilan fungsi

**Bahasa yang mendukung:** C, Pascal, BASIC, Fortran

**Implementasi dalam C:**

```c
#include <stdio.h>

#define JUMLAH_MHS 3
#define JUMLAH_NILAI 5

// ==========================================
// DATA GLOBAL - terpisah dari fungsi (ciri prosedural)
// ==========================================
const char* nama_mhs[JUMLAH_MHS] = {"Rara", "Budi", "Eka"};
float nilai_mhs[JUMLAH_MHS][JUMLAH_NILAI];  // Array 2D: [mahasiswa][nilai]
float nilai_akhir_mhs[JUMLAH_MHS];

// ==========================================
// PROSEDUR: fungsi untuk memasukkan nilai
// ==========================================
void input_nilai() {
    for (int i = 0; i < JUMLAH_MHS; i++) {
        printf("Masukkan nilai untuk %s:\n", nama_mhs[i]);
        printf("  Tugas: "); scanf("%f", &nilai_mhs[i][0]);
        printf("  Quiz: "); scanf("%f", &nilai_mhs[i][1]);
        printf("  UTS: "); scanf("%f", &nilai_mhs[i][2]);
        printf("  UAS: "); scanf("%f", &nilai_mhs[i][3]);
        printf("  Praktikum: "); scanf("%f", &nilai_mhs[i][4]);
    }
}

// ==========================================
// FUNGSI: menghitung nilai akhir satu mahasiswa
// Bobot: Tugas 10%, Quiz 10%, UTS 20%, UAS 30%, Praktikum 30%
// ==========================================
float hitung_nilai_akhir(float nilai[]) {
    return (nilai[0] * 0.10) +   // Tugas
           (nilai[1] * 0.10) +   // Quiz
           (nilai[2] * 0.20) +   // UTS
           (nilai[3] * 0.30) +   // UAS
           (nilai[4] * 0.30);    // Praktikum
}

// ==========================================
// PROSEDUR: menghitung nilai akhir semua mahasiswa
// ==========================================
void hitung_semua_nilai() {
    for (int i = 0; i < JUMLAH_MHS; i++) {
        nilai_akhir_mhs[i] = hitung_nilai_akhir(nilai_mhs[i]);
    }
}

// ==========================================
// PROSEDUR: menampilkan hasil
// ==========================================
void tampilkan_hasil() {
    printf("\n=== HASIL NILAI AKHIR ===\n");
    for (int i = 0; i < JUMLAH_MHS; i++) {
        printf("%s => %.2f\n", nama_mhs[i], nilai_akhir_mhs[i]);
    }
}

int main() {
    // Urutan pemanggilan prosedur (top-down approach)
    input_nilai();          // 1. Input data
    hitung_semua_nilai();   // 2. Proses perhitungan
    tampilkan_hasil();      // 3. Output hasil

    return 0;
}
```

<p align="center">
  <img src="image/img1.jpg" />
</p>

**Kelebihan Prosedural:**
- Mudah dipahami untuk program sederhana
- Eksekusi efisien dan cepat
- Kontrol langsung terhadap memori dan hardware

**Kekurangan Prosedural:**
- Data dan fungsi terpisah, sulit dikelola untuk program besar
- Tidak ada mekanisme bawaan untuk menyembunyikan data
- Sulit untuk reuse code secara modular

### 3.2 Pemrograman Berorientasi Objek (OOP)

**Pemrograman Berorientasi Objek (OOP)** adalah paradigma yang mengorganisasi kode ke dalam **objek-objek** yang menggabungkan data (atribut) dan perilaku (method).

**Karakteristik:**
- **Objek** adalah unit dasar yang menggabungkan data dan fungsi
- **Class** adalah blueprint/template untuk membuat objek
- Mendukung **4 pilar OOP**: Encapsulation, Inheritance, Polymorphism, Abstraction

**Bahasa yang mendukung:** Java, C++, Python, C#, JavaScript (ES6+)

#### 3.2.1 4 Pilar OOP

| Pilar | Penjelasan | Analogi |
|-------|------------|---------|
| **Encapsulation** | Menyembunyikan detail internal dan hanya mengekspos interface yang diperlukan | Remote TV - kita tidak perlu tahu cara kerja internalnya |
| **Inheritance** | Class dapat mewarisi atribut dan method dari class lain | Anak mewarisi sifat dari orang tua |
| **Polymorphism** | Objek dapat memiliki banyak bentuk/perilaku berbeda | Tombol "Play" pada berbagai device |
| **Abstraction** | Menyederhanakan kompleksitas dengan menyembunyikan detail yang tidak perlu | Mobil - kita hanya perlu tahu cara mengemudi, bukan cara kerja mesin |

#### 3.2.2 Implementasi dalam C (Pseudo-OOP dengan Struct)

C tidak mendukung OOP secara native, tetapi kita bisa mensimulasikan dengan `struct`:

```c
#include <stdio.h>
#include <string.h>

#define JUMLAH_NILAI 5

// ==========================================
// STRUCT sebagai "Class" - menggabungkan data dalam satu kesatuan
// Ini adalah cara C mensimulasikan konsep objek
// ==========================================
typedef struct {
    char nama[50];              // Atribut: nama mahasiswa
    float nilai[JUMLAH_NILAI];  // Atribut: array nilai
    float nilai_akhir;          // Atribut: hasil perhitungan
} Mahasiswa;

// ==========================================
// "Method" untuk struct Mahasiswa
// Konvensi: NamaStruct_namaMethod(pointer ke struct)
// ==========================================
void Mahasiswa_hitungNilaiAkhir(Mahasiswa *mhs) {
    // Mengakses atribut menggunakan operator ->
    mhs->nilai_akhir = (mhs->nilai[0] * 0.10) +
                       (mhs->nilai[1] * 0.10) +
                       (mhs->nilai[2] * 0.20) +
                       (mhs->nilai[3] * 0.30) +
                       (mhs->nilai[4] * 0.30);
}

void Mahasiswa_tampilkan(Mahasiswa *mhs) {
    printf("%s => %.2f\n", mhs->nama, mhs->nilai_akhir);
}

int main() {
    // Membuat "objek" - inisialisasi struct dengan nilai
    Mahasiswa mhs1 = {"Rara", {85, 80, 78, 82, 90}, 0};
    Mahasiswa mhs2 = {"Budi", {75, 70, 80, 85, 78}, 0};
    Mahasiswa mhs3 = {"Eka", {90, 88, 92, 95, 88}, 0};

    // Array of struct (seperti array of objects)
    Mahasiswa daftar_mhs[] = {mhs1, mhs2, mhs3};
    int jumlah = 3;

    printf("=== HASIL NILAI AKHIR ===\n");
    for (int i = 0; i < jumlah; i++) {
        // Memanggil "method" dengan mengirim pointer ke objek
        Mahasiswa_hitungNilaiAkhir(&daftar_mhs[i]);
        Mahasiswa_tampilkan(&daftar_mhs[i]);
    }

    return 0;
}
```

#### 3.2.3 Implementasi OOP yang Sebenarnya dalam C++

```cpp
#include <iostream>
#include <string>
#include <vector>
using namespace std;

// ==========================================
// CLASS: Blueprint untuk membuat objek Mahasiswa
// ==========================================
class Mahasiswa {
private:
    // ENCAPSULATION: Atribut disembunyikan (private)
    // Tidak bisa diakses langsung dari luar class
    string nama;
    vector<float> nilai;
    float nilaiAkhir;

public:
    // CONSTRUCTOR: Dipanggil saat objek dibuat
    Mahasiswa(string nama, vector<float> nilai)
        : nama(nama), nilai(nilai), nilaiAkhir(0) {}

    // GETTER: Method untuk mengakses atribut private (bagian dari encapsulation)
    string getNama() const { return nama; }
    float getNilaiAkhir() const { return nilaiAkhir; }

    // METHOD: Behavior/perilaku objek
    void hitungNilaiAkhir() {
        nilaiAkhir = (nilai[0] * 0.10) +
                     (nilai[1] * 0.10) +
                     (nilai[2] * 0.20) +
                     (nilai[3] * 0.30) +
                     (nilai[4] * 0.30);
    }

    void tampilkan() const {
        cout << nama << " => " << nilaiAkhir << endl;
    }
};

int main() {
    // Membuat vector berisi objek Mahasiswa
    vector<Mahasiswa> daftarMhs = {
        Mahasiswa("Rara", {85, 80, 78, 82, 90}),
        Mahasiswa("Budi", {75, 70, 80, 85, 78}),
        Mahasiswa("Eka", {90, 88, 92, 95, 88})
    };

    cout << "=== HASIL NILAI AKHIR ===" << endl;
    for (auto& mhs : daftarMhs) {
        // Memanggil method pada setiap objek
        mhs.hitungNilaiAkhir();
        mhs.tampilkan();
    }

    return 0;
}
```

**Kelebihan OOP:**
- Modular dan mudah di-maintain untuk program besar
- Code reuse melalui inheritance
- Enkapsulasi melindungi data dari akses yang tidak sah
- Mendekati cara berpikir manusia (objek dunia nyata)

**Kekurangan OOP:**
- Overhead memori untuk objek-objek kecil
- Bisa menjadi over-engineered untuk masalah sederhana
- Learning curve lebih tinggi


### 3.3 Pemrograman Fungsional

**Pemrograman fungsional** adalah paradigma deklaratif yang memperlakukan komputasi sebagai evaluasi **fungsi matematika murni**.

**Karakteristik:**
- **Immutability**: Data tidak berubah setelah dibuat
- **Pure Functions**: Fungsi yang selalu menghasilkan output yang sama untuk input yang sama, tanpa side effects
- **First-class Functions**: Fungsi dapat disimpan dalam variabel, dikirim sebagai argumen, atau dikembalikan dari fungsi lain
- **Higher-order Functions**: Fungsi yang menerima fungsi lain sebagai parameter atau mengembalikan fungsi

**Bahasa yang mendukung:** Haskell, Erlang, Lisp, Clojure, dan fitur fungsional di Java 8+, Python, JavaScript

#### 3.3.1 Konsep Penting dalam Pemrograman Fungsional

| Konsep | Penjelasan | Contoh |
|--------|------------|--------|
| **Pure Function** | Tidak ada side effects, output hanya bergantung pada input | `int kuadrat(int x) { return x * x; }` |
| **Immutability** | Data tidak diubah, buat data baru | `newList = oldList.filter(...)` |
| **Higher-order Function** | Fungsi yang bekerja dengan fungsi lain | `map`, `filter`, `reduce` |
| **Lambda/Anonymous Function** | Fungsi tanpa nama | `x -> x * 2` |
| **Function Composition** | Menggabungkan fungsi-fungsi kecil | `f(g(x))` |

#### 3.3.2 Implementasi Fungsional dalam C++ Modern (C++11+)

```cpp
#include <iostream>
#include <vector>
#include <string>
#include <numeric>    // untuk accumulate (reduce)
#include <algorithm>  // untuk transform (map)
#include <functional>
using namespace std;

// ==========================================
// PURE FUNCTION: Output hanya bergantung pada input
// Tidak mengubah data asli (const reference)
// ==========================================
float hitungNilaiAkhir(const vector<float>& nilai) {
    const vector<float> bobot = {0.10, 0.10, 0.20, 0.30, 0.30};

    float total = 0;
    for (size_t i = 0; i < nilai.size() && i < bobot.size(); i++) {
        total += nilai[i] * bobot[i];
    }
    return total;
}

// ==========================================
// IMMUTABLE DATA: Atribut menggunakan const
// Data tidak bisa diubah setelah dibuat
// ==========================================
struct Mahasiswa {
    const string nama;
    const vector<float> nilai;

    Mahasiswa(string n, vector<float> v) : nama(n), nilai(v) {}
};

struct HasilMahasiswa {
    const string nama;
    const float nilaiAkhir;

    HasilMahasiswa(string n, float na) : nama(n), nilaiAkhir(na) {}
};

int main() {
    // Data input bersifat immutable (const)
    const vector<Mahasiswa> daftarMhs = {
        {"Rara", {85, 80, 78, 82, 90}},
        {"Budi", {75, 70, 80, 85, 78}},
        {"Eka", {90, 88, 92, 95, 88}}
    };

    // ==========================================
    // TRANSFORM (MAP): Transformasi setiap elemen ke bentuk baru
    // Menggunakan lambda expression (anonymous function)
    // ==========================================
    vector<HasilMahasiswa> hasil;
    transform(daftarMhs.begin(), daftarMhs.end(), back_inserter(hasil),
        [](const Mahasiswa& mhs) {  // Lambda: fungsi tanpa nama
            return HasilMahasiswa(mhs.nama, hitungNilaiAkhir(mhs.nilai));
        }
    );

    cout << "=== HASIL NILAI AKHIR ===" << endl;
    for (const auto& h : hasil) {
        cout << h.nama << " => " << h.nilaiAkhir << endl;
    }

    // ==========================================
    // FILTER: Menyaring data berdasarkan kondisi
    // ==========================================
    cout << "\n=== MAHASISWA DENGAN NILAI > 80 ===" << endl;
    for (const auto& h : hasil) {
        if (h.nilaiAkhir > 80) {
            cout << h.nama << " => " << h.nilaiAkhir << endl;
        }
    }

    // ==========================================
    // ACCUMULATE (REDUCE): Menggabungkan semua elemen menjadi satu nilai
    // ==========================================
    float totalNilai = accumulate(hasil.begin(), hasil.end(), 0.0f,
        [](float sum, const HasilMahasiswa& h) {
            return sum + h.nilaiAkhir;
        }
    );
    cout << "\nRata-rata nilai: " << (totalNilai / hasil.size()) << endl;

    return 0;
}
```

**Kelebihan Fungsional:**
- Mudah di-test (pure functions tidak bergantung pada state eksternal)
- Aman untuk concurrent/parallel programming (tidak ada shared mutable state)
- Kode lebih predictable dan mudah di-debug
- Cocok untuk data processing dan transformasi

**Kekurangan Fungsional:**
- Learning curve tinggi untuk programmer yang terbiasa dengan imperatif
- Bisa kurang efisien untuk operasi in-place
- Tidak semua masalah cocok dipecahkan secara fungsional

## 4. Perbandingan Paradigma Pemrograman

### 4.1 Tabel Perbandingan Umum

| Aspek | Prosedural | OOP | Fungsional |
|-------|------------|-----|------------|
| **Fokus Utama** | Prosedur/Fungsi | Objek dan Class | Fungsi Murni |
| **Struktur Data** | Terpisah dari fungsi | Terintegrasi dalam objek | Immutable |
| **State Management** | Global/Local variables | Encapsulated dalam objek | Tidak ada state (stateless) |
| **Reusability** | Melalui fungsi | Melalui inheritance/composition | Melalui higher-order functions |
| **Side Effects** | Umum terjadi | Dikontrol melalui encapsulation | Dihindari (pure functions) |
| **Contoh Bahasa** | C, Pascal, BASIC | Java, C++, Python | Haskell, Lisp, Erlang |
| **Cocok Untuk** | Script sederhana, sistem | Aplikasi besar, game, enterprise | Data processing, concurrent |

### 4.2 Kapan Menggunakan Paradigma Tertentu?

| Situasi | Rekomendasi | Alasan |
|---------|-------------|--------|
| Aplikasi enterprise berskala besar | OOP | Modular, maintainable, team collaboration |
| Script otomasi sederhana | Prosedural | Cepat, straightforward |
| Data processing & transformasi | Fungsional | Immutable, predictable, parallelizable |
| Game development | OOP | Entity management, inheritance untuk karakter |
| Web frontend modern | Component-Based + Fungsional | React/Vue menggunakan komponen + hooks |
| Backend API | OOP atau Fungsional | Bergantung pada kompleksitas dan tim |
| Embedded systems | Prosedural | Kontrol memori langsung, efisien |
| Machine Learning pipeline | Fungsional | Data transformation, immutable pipelines |

## 5. Multi-Paradigm Programming

Banyak bahasa pemrograman modern mendukung **multi-paradigma**, artinya programmer dapat memilih atau menggabungkan paradigma sesuai kebutuhan.

### 5.1 Java sebagai Bahasa Multi-Paradigma

Java pada awalnya dirancang untuk OOP, tetapi sejak Java 8+, Java mendukung pemrograman fungsional melalui **Lambda Expressions** dan **Stream API**.

```java
import java.util.Arrays;
import java.util.List;

public class MultiParadigmDemo {
    public static void main(String[] args) {
        List<String> namaMahasiswa = Arrays.asList("Rara", "Budi", "Eka", "Pratiwi", "Agung");

        // ==========================================
        // PENDEKATAN IMPERATIF: Loop dengan index
        // Kontrol eksplisit menggunakan counter
        // ==========================================
        System.out.println("=== Pendekatan Imperatif ===");
        for (int i = 0; i < namaMahasiswa.size(); i++) {
            System.out.println(namaMahasiswa.get(i).toUpperCase());
        }

        // ==========================================
        // PENDEKATAN OOP: Enhanced for-loop
        // Iterasi pada objek Collection
        // ==========================================
        System.out.println("\n=== Pendekatan OOP ===");
        for (String nama : namaMahasiswa) {
            System.out.println(nama.toUpperCase());
        }

        // ==========================================
        // PENDEKATAN FUNGSIONAL: Stream API + Lambda
        // - stream(): Membuat stream dari collection
        // - map(): Transformasi setiap elemen
        // - forEach(): Aksi untuk setiap elemen
        // - String::toUpperCase: Method reference (shorthand untuk lambda)
        // ==========================================
        System.out.println("\n=== Pendekatan Fungsional ===");
        namaMahasiswa.stream()
                     .map(String::toUpperCase)      // MAP: ubah ke uppercase
                     .forEach(System.out::println); // Cetak setiap elemen

        // ==========================================
        // CHAINING: Menggabungkan operasi fungsional
        // filter() -> map() -> forEach()
        // ==========================================
        System.out.println("\n=== Filter nama yang dimulai dengan huruf vokal ===");
        namaMahasiswa.stream()
                     .filter(nama -> "AEIOUaeiou".indexOf(nama.charAt(0)) != -1)  // FILTER
                     .map(String::toUpperCase)      // MAP
                     .forEach(System.out::println); // OUTPUT
    }
}
```

**Output:**

```
=== Pendekatan Imperatif ===
RARA
BUDI
EKA
PRATIWI
AGUNG

=== Pendekatan OOP ===
RARA
BUDI
EKA
PRATIWI
AGUNG

=== Pendekatan Fungsional ===
RARA
BUDI
EKA
PRATIWI
AGUNG

=== Filter nama yang dimulai dengan huruf vokal ===
EKA
AGUNG
```

### 5.2 Python sebagai Bahasa Multi-Paradigma

Python mendukung prosedural, OOP, dan fungsional secara native.

```python
# Data mahasiswa (list of dictionaries)
mahasiswa = [
    {"nama": "Rara", "nilai": [80, 85, 90, 88, 92]},
    {"nama": "Budi", "nilai": [75, 70, 78, 80, 82]},
    {"nama": "Eka", "nilai": [90, 92, 95, 88, 94]},
]

# ============================================
# 1. PENDEKATAN PROSEDURAL
# - Menggunakan loop eksplisit
# - Langkah demi langkah
# ============================================
def hitung_rata_rata_prosedural(data):
    hasil = []                      # Variabel untuk menyimpan hasil
    for mhs in data:                # Loop untuk setiap mahasiswa
        total = 0
        for n in mhs["nilai"]:      # Loop untuk setiap nilai
            total += n              # Akumulasi nilai
        rata_rata = total / len(mhs["nilai"])
        hasil.append({"nama": mhs["nama"], "rata_rata": rata_rata})
    return hasil

print("=== Prosedural ===")
print(hitung_rata_rata_prosedural(mahasiswa))

# ============================================
# 2. PENDEKATAN OOP
# - Menggabungkan data dan behavior dalam class
# - Encapsulation dengan atribut dan method
# ============================================
class Mahasiswa:
    def __init__(self, nama, nilai):    # Constructor
        self.nama = nama                # Atribut
        self.nilai = nilai

    def rata_rata(self):                # Method
        return sum(self.nilai) / len(self.nilai)

    def __repr__(self):                 # String representation
        return f"{self.nama}: {self.rata_rata():.2f}"

print("\n=== OOP ===")
# List comprehension untuk membuat objek dari data
mhs_objects = [Mahasiswa(m["nama"], m["nilai"]) for m in mahasiswa]
for mhs in mhs_objects:
    print(mhs)

# ============================================
# 3. PENDEKATAN FUNGSIONAL
# - Menggunakan higher-order functions: map, reduce, filter
# - Lambda expressions (anonymous functions)
# ============================================
from functools import reduce

def hitung_rata_rata_fungsional(data):
    return list(map(
        # Lambda: fungsi tanpa nama
        lambda mhs: {
            "nama": mhs["nama"],
            # reduce: menggabungkan semua nilai menjadi satu (total)
            "rata_rata": reduce(lambda a, b: a + b, mhs["nilai"]) / len(mhs["nilai"])
        },
        data  # Data yang akan di-map
    ))

print("\n=== Fungsional ===")
print(hitung_rata_rata_fungsional(mahasiswa))

# ============================================
# 4. PYTHONIC: List Comprehension
# - Cara Python yang lebih idiomatis
# - Lebih ringkas dan mudah dibaca
# ============================================
def hitung_rata_rata_pythonic(data):
    return [
        {"nama": m["nama"], "rata_rata": sum(m["nilai"]) / len(m["nilai"])}
        for m in data  # Iterasi dalam satu baris
    ]

print("\n=== Pythonic (List Comprehension) ===")
print(hitung_rata_rata_pythonic(mahasiswa))
```

## 6. Paradigma Pemrograman Lainnya

Selain tiga paradigma utama di atas, terdapat paradigma-paradigma lain yang digunakan untuk kasus khusus:

### 6.1 Event-Driven Programming

Berfokus pada **event** dan **event handler**. Program merespons event yang terjadi (klik, input, timer, dll).

**Digunakan pada:** GUI applications, web frontend, game loop, IoT

```html
<!DOCTYPE html>
<html>
<head>
    <title>Contoh Event-Driven Programming</title>
</head>
<body>

<button id="tombol">Tekan Saya</button>
<p id="output"></p>

<script>
    // ==========================================
    // EVENT LISTENER: Mendaftarkan handler untuk event tertentu
    // - 'click': jenis event yang didengarkan
    // - function(): handler yang dijalankan saat event terjadi
    // ==========================================
    document.getElementById('tombol').addEventListener('click', function() {
        // Event handler: kode yang dieksekusi saat tombol diklik
        document.getElementById('output').textContent = 'Tombol telah ditekan!';
    });

    // Event listener untuk keyboard (event global pada document)
    document.addEventListener('keypress', function(event) {
        // event.key: informasi tentang tombol yang ditekan
        console.log('Tombol keyboard: ' + event.key);
    });
</script>

</body>
</html>
```

### 6.2 Logic Programming

Berfokus pada **fakta** dan **aturan logika**. Program mendeskripsikan hubungan logis, dan sistem mencari solusi.

**Bahasa:** Prolog

```prolog
% ==========================================
% FAKTA: Pernyataan yang dianggap benar
% orang_tua(X, Y) berarti "X adalah orang tua dari Y"
% ==========================================
orang_tua(ani, budi).    % Ani adalah orang tua Budi
orang_tua(ani, citra).   % Ani adalah orang tua Citra
orang_tua(budi, deni).   % Budi adalah orang tua Deni

% ==========================================
% ATURAN: Definisi relasi berdasarkan fakta lain
% X adalah kakek/nenek dari Z jika:
%   - X adalah orang tua dari Y, DAN
%   - Y adalah orang tua dari Z
% ==========================================
kakek_nenek(X, Z) :- orang_tua(X, Y), orang_tua(Y, Z).

% ==========================================
% QUERY: Pertanyaan untuk mencari solusi
% ?- kakek_nenek(X, deni).
% Prolog akan mencari X yang memenuhi aturan
% Hasil: X = ani (karena ani -> budi -> deni)
% ==========================================
```

### 6.3 Reactive Programming

Berfokus pada **data streams** dan **propagation of change**. Ketika data berubah, semua yang bergantung padanya otomatis diperbarui.

**Library:** RxJS, RxJava, ReactiveX

```javascript
// Contoh dengan RxJS (Reactive Extensions for JavaScript)
import { fromEvent } from 'rxjs';
import { map, filter, debounceTime } from 'rxjs/operators';

// ==========================================
// OBSERVABLE: Membuat stream dari event input
// Setiap ketikan user menjadi data dalam stream
// ==========================================
const input = document.getElementById('search');
const stream = fromEvent(input, 'input')
    .pipe(
        // OPERATORS: Transformasi data dalam stream
        map(event => event.target.value),  // Ambil nilai input
        debounceTime(300),                 // Tunggu 300ms setelah user berhenti mengetik
        filter(text => text.length >= 3)   // Hanya proses jika >= 3 karakter
    );

// ==========================================
// SUBSCRIBE: Bereaksi terhadap data yang mengalir
// Setiap kali ada data baru yang lolos filter, callback dipanggil
// ==========================================
stream.subscribe(value => {
    console.log('Search:', value);
});
```

### 6.4 Component-Based Programming

Berfokus pada **komponen yang dapat digunakan ulang** (reusable components). Setiap komponen memiliki state dan behavior sendiri.

**Framework:** React, Vue.js, Angular

```jsx
// ==========================================
// FUNCTIONAL COMPONENT: Komponen sebagai fungsi
// Props: data yang diterima dari parent component
// ==========================================
function MahasiswaCard({ nama, nilai }) {  // Destructuring props
    // Logika perhitungan di dalam komponen
    const rataRata = nilai.reduce((a, b) => a + b, 0) / nilai.length;

    // JSX: Syntax mirip HTML untuk mendefinisikan UI
    return (
        <div className="card">
            <h3>{nama}</h3>                           {/* Render props */}
            <p>Rata-rata: {rataRata.toFixed(2)}</p>   {/* Render hasil kalkulasi */}
        </div>
    );
}

// ==========================================
// PARENT COMPONENT: Menggunakan child component
// ==========================================
function App() {
    // Data yang akan dirender
    const mahasiswa = [
        { nama: "Rara", nilai: [85, 90, 88] },
        { nama: "Budi", nilai: [75, 80, 78] }
    ];

    return (
        <div>
            {/* Map: Render komponen untuk setiap item */}
            {mahasiswa.map(mhs => (
                <MahasiswaCard
                    key={mhs.nama}  // Key unik untuk React
                    {...mhs}        // Spread: kirim semua properti sebagai props
                />
            ))}
        </div>
    );
}
```

## 7. Latihan Praktikum

### 7.1 Latihan 1: Identifikasi Paradigma

Untuk setiap kode berikut, identifikasi paradigma pemrograman yang digunakan dan jelaskan alasannya:

**Kode A:**

```java
int total = 0;                          // Inisialisasi variabel
for (int i = 0; i < arr.length; i++) {  // Loop dengan index
    total += arr[i];                    // Akumulasi nilai
}
```

**Kode B:**

```java
List<Integer> hasil = numbers.stream()           // Buat stream
                             .filter(n -> n > 10) // Filter: ambil n > 10
                             .map(n -> n * 2)     // Map: kalikan 2
                             .collect(Collectors.toList()); // Kumpulkan hasil
```

**Kode C:**

```java
class Kalkulator {
    private int hasil;                  // Atribut private (encapsulation)

    public void tambah(int angka) {     // Method untuk menambah
        this.hasil += angka;
    }

    public int getHasil() {             // Getter
        return this.hasil;
    }
}
```

### 7.2 Latihan 2: Konversi Paradigma

Diberikan kode prosedural berikut:

```c
// Fungsi prosedural untuk mencari nilai maksimum dalam array
int cari_maksimum(int arr[], int n) {
    int max = arr[0];               // Asumsi elemen pertama adalah max
    for (int i = 1; i < n; i++) {   // Iterasi dari elemen kedua
        if (arr[i] > max) {         // Bandingkan dengan max saat ini
            max = arr[i];           // Update max jika ditemukan yang lebih besar
        }
    }
    return max;                     // Kembalikan nilai maksimum
}
```

Konversikan kode di atas ke:

1. Pendekatan OOP menggunakan Java (buat class `ArrayHelper` dengan method `cariMaksimum`)
2. Pendekatan Fungsional menggunakan Java Stream API

### 7.3 Latihan 3: Studi Kasus - Sistem Perpustakaan

Buatlah desain sistem perpustakaan sederhana yang dapat:

- Menambah buku baru
- Meminjam buku
- Mengembalikan buku
- Mencari buku berdasarkan judul

Implementasikan dengan 2 pendekatan:

1. **Prosedural** menggunakan C atau Java (tanpa class)
2. **OOP** menggunakan Java atau C++ (dengan class `Buku`, `Anggota`, `Perpustakaan`)

### 7.4 Latihan 4: Analisis Kelebihan dan Kekurangan

Berdasarkan studi kasus sistem nilai mahasiswa di modul ini, analisis:

1. Apa kelebihan menggunakan OOP dibandingkan prosedural?
2. Kapan sebaiknya menggunakan pendekatan prosedural?
3. Bagaimana pendekatan fungsional dapat membantu dalam memproses data nilai?

### 7.5 Latihan 5: Multi-Paradigma

Buatlah program Java yang menggabungkan OOP dan Fungsional untuk:

1. Membuat class `Produk` dengan atribut `nama`, `harga`, dan `stok`
2. Membuat list berisi 5 objek `Produk`
3. Menggunakan Stream API untuk:
   - Filter produk dengan harga > 50000
   - Urutkan berdasarkan harga (ascending)
   - Tampilkan nama produk yang memenuhi kriteria


## 9. Referensi

1. Sebesta, R. W. (2016). *Concepts of Programming Languages* (11th ed.). Pearson.
2. Van Roy, P., & Haridi, S. (2004). *Concepts, Techniques, and Models of Computer Programming*. MIT Press.
3. [Programming Paradigms - GeeksforGeeks](https://www.geeksforgeeks.org/introduction-of-programming-paradigms/)
4. [What is a Programming Paradigm? - Medium](https://medium.com/@Ariobarxan/what-is-a-programming-paradigm-ec6c5879952b)
5. [Functional Programming Concepts - Mozilla Developer Network](https://developer.mozilla.org/en-US/docs/Glossary/Functional_programming)
6. [Object-Oriented Programming - Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/java/concepts/)
