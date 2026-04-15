# Module 05: Linked List dan Variasinya

## Daftar Isi
1. [Pengenalan Linked List](#pengenalan-linked-list)
2. [Single Linked List](#single-linked-list)
   - [Single Linked List tanpa OOP](#single-linked-list-tanpa-oop)
   - [Single Linked List dengan OOP](#single-linked-list-dengan-oop)
3. [Double Linked List](#double-linked-list)
   - [Double Linked List tanpa OOP](#double-linked-list-tanpa-oop)
   - [Double Linked List dengan OOP](#double-linked-list-dengan-oop)
4. [Circular Single Linked List](#circular-single-linked-list)
   - [Circular Single Linked List tanpa OOP](#circular-single-linked-list-tanpa-oop)
   - [Circular Single Linked List dengan OOP](#circular-single-linked-list-dengan-oop)
5. [Circular Double Linked List](#circular-double-linked-list)
   - [Circular Double Linked List tanpa OOP](#circular-double-linked-list-tanpa-oop)
   - [Circular Double Linked List dengan OOP](#circular-double-linked-list-dengan-oop)
6. [Kompleksitas Linked List](#kompleksitas-linked-list)
7. [Latihan Hands-On](#latihan-hands-on)

---

## Pengenalan Linked List

### Apa itu Linked List?

Linked List adalah struktur data linear yang terdiri dari kumpulan node yang saling terhubung melalui pointer/referensi. Berbeda dengan array yang menyimpan data secara kontinu di memori, linked list menyimpan data secara tersebar dan dihubungkan dengan pointer.

### Komponen Linked List

Setiap node dalam linked list minimal memiliki:
- **Data**: Nilai yang disimpan
- **Pointer/Next**: Referensi ke node berikutnya

### Jenis-jenis Linked List

| Jenis | Deskripsi | Karakteristik |
|-------|-----------|---------------|
| **Single Linked List** | Setiap node memiliki satu pointer ke node berikutnya | Traversal satu arah |
| **Double Linked List** | Setiap node memiliki pointer ke node sebelum dan sesudahnya | Traversal dua arah |
| **Circular Single Linked List** | Node terakhir menunjuk ke node pertama | Membentuk lingkaran |
| **Circular Double Linked List** | Kombinasi double dan circular | Lingkaran dua arah |

### Keuntungan Linked List
- Ukuran dinamis (tidak perlu deklarasi ukuran di awal)
- Efisien untuk insert dan delete di awal/tengah
- Tidak ada pemborosan memori

### Kekurangan Linked List
- Tidak mendukung akses random (harus traverse dari awal)
- Membutuhkan memori ekstra untuk menyimpan pointer
- Cache performance kurang optimal

---

## Single Linked List

### Konsep Single Linked List

Single Linked List adalah jenis linked list paling sederhana dimana setiap node hanya memiliki satu pointer yang menunjuk ke node berikutnya.

```
[HEAD] -> [Data|Next] -> [Data|Next] -> [Data|Next] -> NULL
```

### Single Linked List tanpa OOP

```java
public class SingleLinkedListNoOOP {
    // Menggunakan array untuk simulasi linked list
    static int[] data = new int[100];
    static int[] next = new int[100];
    static int head = -1;
    static int size = 0;
    static int freeIndex = 0;

    // Mendapatkan index kosong
    public static int getNewIndex() {
        if (freeIndex >= 100) {
            return -1;
        }
        return freeIndex++;
    }

    // Insert di awal
    public static void insertAtBeginning(int value) {
        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;
        next[newIndex] = head;
        head = newIndex;
        size++;
        System.out.println("Insert di awal: " + value);
    }

    // Insert di akhir
    public static void insertAtEnd(int value) {
        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;
        next[newIndex] = -1;

        if (head == -1) {
            head = newIndex;
        } else {
            int current = head;
            while (next[current] != -1) {
                current = next[current];
            }
            next[current] = newIndex;
        }
        size++;
        System.out.println("Insert di akhir: " + value);
    }

    // Insert di posisi tertentu
    public static void insertAtPosition(int value, int position) {
        if (position < 0 || position > size) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (position == 0) {
            insertAtBeginning(value);
            return;
        }

        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;

        int current = head;
        for (int i = 0; i < position - 1; i++) {
            current = next[current];
        }

        next[newIndex] = next[current];
        next[current] = newIndex;
        size++;
        System.out.println("Insert " + value + " di posisi " + position);
    }

    // Delete di awal
    public static void deleteAtBeginning() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        int deletedValue = data[head];
        head = next[head];
        size--;
        System.out.println("Delete di awal: " + deletedValue);
    }

    // Delete di akhir
    public static void deleteAtEnd() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        if (next[head] == -1) {
            int deletedValue = data[head];
            head = -1;
            size--;
            System.out.println("Delete di akhir: " + deletedValue);
            return;
        }

        int current = head;
        while (next[next[current]] != -1) {
            current = next[current];
        }

        int deletedValue = data[next[current]];
        next[current] = -1;
        size--;
        System.out.println("Delete di akhir: " + deletedValue);
    }

    // Delete berdasarkan nilai
    public static void deleteByValue(int value) {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        if (data[head] == value) {
            head = next[head];
            size--;
            System.out.println("Delete nilai: " + value);
            return;
        }

        int current = head;
        while (next[current] != -1 && data[next[current]] != value) {
            current = next[current];
        }

        if (next[current] == -1) {
            System.out.println("Nilai " + value + " tidak ditemukan!");
            return;
        }

        next[current] = next[next[current]];
        size--;
        System.out.println("Delete nilai: " + value);
    }

    // Search
    public static int search(int value) {
        int current = head;
        int position = 0;
        while (current != -1) {
            if (data[current] == value) {
                return position;
            }
            current = next[current];
            position++;
        }
        return -1;
    }

    // Display
    public static void display() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("List: ");
        int current = head;
        while (current != -1) {
            System.out.print(data[current]);
            if (next[current] != -1) {
                System.out.print(" -> ");
            }
            current = next[current];
        }
        System.out.println(" -> NULL");
        System.out.println("Size: " + size);
    }

    // Reverse
    public static void reverse() {
        int prev = -1;
        int current = head;
        int nextNode;

        while (current != -1) {
            nextNode = next[current];
            next[current] = prev;
            prev = current;
            current = nextNode;
        }
        head = prev;
        System.out.println("List berhasil di-reverse");
    }

    public static void main(String[] args) {
        System.out.println("=== SINGLE LINKED LIST TANPA OOP ===\n");

        // Test insert
        insertAtBeginning(10);
        insertAtBeginning(20);
        insertAtEnd(5);
        insertAtEnd(15);
        display();

        // Test insert di posisi
        System.out.println();
        insertAtPosition(25, 2);
        display();

        // Test search
        System.out.println();
        int pos = search(25);
        System.out.println("Posisi nilai 25: " + (pos != -1 ? pos : "Tidak ditemukan"));
        pos = search(100);
        System.out.println("Posisi nilai 100: " + (pos != -1 ? pos : "Tidak ditemukan"));

        // Test delete
        System.out.println();
        deleteAtBeginning();
        display();

        System.out.println();
        deleteAtEnd();
        display();

        System.out.println();
        deleteByValue(25);
        display();

        // Test reverse
        System.out.println();
        reverse();
        display();
    }
}
```

**Output:**
```
=== SINGLE LINKED LIST TANPA OOP ===

Insert di awal: 10
Insert di awal: 20
Insert di akhir: 5
Insert di akhir: 15
List: 20 -> 10 -> 5 -> 15 -> NULL
Size: 4

Insert 25 di posisi 2
List: 20 -> 10 -> 25 -> 5 -> 15 -> NULL
Size: 5

Posisi nilai 25: 2
Posisi nilai 100: Tidak ditemukan

Delete di awal: 20
List: 10 -> 25 -> 5 -> 15 -> NULL
Size: 4

Delete di akhir: 15
List: 10 -> 25 -> 5 -> NULL
Size: 3

Delete nilai: 25
List: 10 -> 5 -> NULL
Size: 2

List berhasil di-reverse
List: 5 -> 10 -> NULL
Size: 2
```

---

### Single Linked List dengan OOP

```java
// File: Node.java
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// File: SingleLinkedList.java
class SingleLinkedList {
    private Node head;
    private int size;

    public SingleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Getter
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Insert di awal - O(1)
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("Insert di awal: " + data);
    }

    // Insert di akhir - O(n)
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Insert di akhir: " + data);
    }

    // Insert di posisi tertentu - O(n)
    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (position == 0) {
            insertAtBeginning(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
        System.out.println("Insert " + data + " di posisi " + position);
    }

    // Delete di awal - O(1)
    public void deleteAtBeginning() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        int deletedData = head.data;
        head = head.next;
        size--;
        System.out.println("Delete di awal: " + deletedData);
    }

    // Delete di akhir - O(n)
    public void deleteAtEnd() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        if (head.next == null) {
            int deletedData = head.data;
            head = null;
            size--;
            System.out.println("Delete di akhir: " + deletedData);
            return;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        int deletedData = current.next.data;
        current.next = null;
        size--;
        System.out.println("Delete di akhir: " + deletedData);
    }

    // Delete berdasarkan nilai - O(n)
    public void deleteByValue(int value) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        if (head.data == value) {
            head = head.next;
            size--;
            System.out.println("Delete nilai: " + value);
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Nilai " + value + " tidak ditemukan!");
            return;
        }

        current.next = current.next.next;
        size--;
        System.out.println("Delete nilai: " + value);
    }

    // Delete di posisi tertentu - O(n)
    public void deleteAtPosition(int position) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        if (position < 0 || position >= size) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (position == 0) {
            deleteAtBeginning();
            return;
        }

        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        int deletedData = current.next.data;
        current.next = current.next.next;
        size--;
        System.out.println("Delete di posisi " + position + ": " + deletedData);
    }

    // Search - O(n)
    public int search(int value) {
        Node current = head;
        int position = 0;
        while (current != null) {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    // Get data di posisi tertentu - O(n)
    public int get(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Posisi tidak valid!");
        }

        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Update data di posisi tertentu - O(n)
    public void update(int position, int newData) {
        if (position < 0 || position >= size) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        int oldData = current.data;
        current.data = newData;
        System.out.println("Update posisi " + position + ": " + oldData + " -> " + newData);
    }

    // Reverse - O(n)
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        System.out.println("List berhasil di-reverse");
    }

    // Display - O(n)
    public void display() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("List: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> NULL");
        System.out.println("Size: " + size);
    }

    // Clear list - O(1)
    public void clear() {
        head = null;
        size = 0;
        System.out.println("List dikosongkan");
    }
}

// File: SingleLinkedListDemo.java
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== SINGLE LINKED LIST DENGAN OOP ===\n");

        SingleLinkedList list = new SingleLinkedList();

        // Test insert
        list.insertAtBeginning(10);
        list.insertAtBeginning(20);
        list.insertAtEnd(5);
        list.insertAtEnd(15);
        list.display();

        // Test insert di posisi
        System.out.println();
        list.insertAtPosition(25, 2);
        list.display();

        // Test get dan update
        System.out.println();
        System.out.println("Data di posisi 2: " + list.get(2));
        list.update(2, 30);
        list.display();

        // Test search
        System.out.println();
        int pos = list.search(30);
        System.out.println("Posisi nilai 30: " + (pos != -1 ? pos : "Tidak ditemukan"));

        // Test delete
        System.out.println();
        list.deleteAtBeginning();
        list.display();

        System.out.println();
        list.deleteAtEnd();
        list.display();

        System.out.println();
        list.deleteByValue(30);
        list.display();

        System.out.println();
        list.deleteAtPosition(1);
        list.display();

        // Test reverse
        System.out.println();
        list.insertAtEnd(100);
        list.insertAtEnd(200);
        list.display();
        list.reverse();
        list.display();
    }
}
```

**Output:**
```
=== SINGLE LINKED LIST DENGAN OOP ===

Insert di awal: 10
Insert di awal: 20
Insert di akhir: 5
Insert di akhir: 15
List: 20 -> 10 -> 5 -> 15 -> NULL
Size: 4

Insert 25 di posisi 2
List: 20 -> 10 -> 25 -> 5 -> 15 -> NULL
Size: 5

Data di posisi 2: 25
Update posisi 2: 25 -> 30
List: 20 -> 10 -> 30 -> 5 -> 15 -> NULL
Size: 5

Posisi nilai 30: 2

Delete di awal: 20
List: 10 -> 30 -> 5 -> 15 -> NULL
Size: 4

Delete di akhir: 15
List: 10 -> 30 -> 5 -> NULL
Size: 3

Delete nilai: 30
List: 10 -> 5 -> NULL
Size: 2

Delete di posisi 1: 5
List: 10 -> NULL
Size: 1

Insert di akhir: 100
Insert di akhir: 200
List: 10 -> 100 -> 200 -> NULL
Size: 3
List berhasil di-reverse
List: 200 -> 100 -> 10 -> NULL
Size: 3
```

---

## Double Linked List

### Konsep Double Linked List

Double Linked List adalah linked list dimana setiap node memiliki dua pointer: satu menunjuk ke node sebelumnya (prev) dan satu menunjuk ke node berikutnya (next). Ini memungkinkan traversal dua arah.

```
NULL <- [Prev|Data|Next] <-> [Prev|Data|Next] <-> [Prev|Data|Next] -> NULL
         ^                                              ^
        HEAD                                           TAIL
```

### Keuntungan Double Linked List
- Traversal bisa dilakukan dari dua arah
- Delete node lebih mudah (tidak perlu track node sebelumnya)
- Operasi di tail menjadi O(1) jika ada tail pointer

### Kekurangan Double Linked List
- Membutuhkan memori ekstra untuk pointer prev
- Operasi insert dan delete lebih kompleks

---

### Double Linked List tanpa OOP

```java
public class DoubleLinkedListNoOOP {
    // Array untuk simulasi double linked list
    static int[] data = new int[100];
    static int[] next = new int[100];
    static int[] prev = new int[100];
    static int head = -1;
    static int tail = -1;
    static int size = 0;
    static int freeIndex = 0;

    public static int getNewIndex() {
        if (freeIndex >= 100) return -1;
        return freeIndex++;
    }

    // Insert di awal
    public static void insertAtBeginning(int value) {
        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;
        next[newIndex] = head;
        prev[newIndex] = -1;

        if (head != -1) {
            prev[head] = newIndex;
        } else {
            tail = newIndex;
        }

        head = newIndex;
        size++;
        System.out.println("Insert di awal: " + value);
    }

    // Insert di akhir
    public static void insertAtEnd(int value) {
        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;
        next[newIndex] = -1;
        prev[newIndex] = tail;

        if (tail != -1) {
            next[tail] = newIndex;
        } else {
            head = newIndex;
        }

        tail = newIndex;
        size++;
        System.out.println("Insert di akhir: " + value);
    }

    // Insert di posisi tertentu
    public static void insertAtPosition(int value, int position) {
        if (position < 0 || position > size) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (position == 0) {
            insertAtBeginning(value);
            return;
        }

        if (position == size) {
            insertAtEnd(value);
            return;
        }

        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        // Traverse ke posisi
        int current = head;
        for (int i = 0; i < position; i++) {
            current = next[current];
        }

        data[newIndex] = value;
        next[newIndex] = current;
        prev[newIndex] = prev[current];

        next[prev[current]] = newIndex;
        prev[current] = newIndex;

        size++;
        System.out.println("Insert " + value + " di posisi " + position);
    }

    // Delete di awal
    public static void deleteAtBeginning() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        int deletedValue = data[head];
        head = next[head];

        if (head != -1) {
            prev[head] = -1;
        } else {
            tail = -1;
        }

        size--;
        System.out.println("Delete di awal: " + deletedValue);
    }

    // Delete di akhir
    public static void deleteAtEnd() {
        if (tail == -1) {
            System.out.println("List kosong!");
            return;
        }

        int deletedValue = data[tail];
        tail = prev[tail];

        if (tail != -1) {
            next[tail] = -1;
        } else {
            head = -1;
        }

        size--;
        System.out.println("Delete di akhir: " + deletedValue);
    }

    // Delete berdasarkan nilai
    public static void deleteByValue(int value) {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        int current = head;
        while (current != -1 && data[current] != value) {
            current = next[current];
        }

        if (current == -1) {
            System.out.println("Nilai " + value + " tidak ditemukan!");
            return;
        }

        if (current == head) {
            deleteAtBeginning();
            return;
        }

        if (current == tail) {
            deleteAtEnd();
            return;
        }

        next[prev[current]] = next[current];
        prev[next[current]] = prev[current];
        size--;
        System.out.println("Delete nilai: " + value);
    }

    // Display maju
    public static void displayForward() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Forward:  NULL <- ");
        int current = head;
        while (current != -1) {
            System.out.print(data[current]);
            if (next[current] != -1) {
                System.out.print(" <-> ");
            }
            current = next[current];
        }
        System.out.println(" -> NULL");
    }

    // Display mundur
    public static void displayBackward() {
        if (tail == -1) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Backward: NULL <- ");
        int current = tail;
        while (current != -1) {
            System.out.print(data[current]);
            if (prev[current] != -1) {
                System.out.print(" <-> ");
            }
            current = prev[current];
        }
        System.out.println(" -> NULL");
    }

    public static void main(String[] args) {
        System.out.println("=== DOUBLE LINKED LIST TANPA OOP ===\n");

        insertAtBeginning(10);
        insertAtBeginning(20);
        insertAtEnd(5);
        insertAtEnd(15);

        System.out.println();
        displayForward();
        displayBackward();
        System.out.println("Size: " + size);

        System.out.println();
        insertAtPosition(25, 2);
        displayForward();

        System.out.println();
        deleteAtBeginning();
        displayForward();

        System.out.println();
        deleteAtEnd();
        displayForward();

        System.out.println();
        deleteByValue(25);
        displayForward();
        displayBackward();
    }
}
```

**Output:**
```
=== DOUBLE LINKED LIST TANPA OOP ===

Insert di awal: 10
Insert di awal: 20
Insert di akhir: 5
Insert di akhir: 15

Forward:  NULL <- 20 <-> 10 <-> 5 <-> 15 -> NULL
Backward: NULL <- 15 <-> 5 <-> 10 <-> 20 -> NULL
Size: 4

Insert 25 di posisi 2
Forward:  NULL <- 20 <-> 10 <-> 25 <-> 5 <-> 15 -> NULL

Delete di awal: 20
Forward:  NULL <- 10 <-> 25 <-> 5 <-> 15 -> NULL

Delete di akhir: 15
Forward:  NULL <- 10 <-> 25 <-> 5 -> NULL

Delete nilai: 25
Forward:  NULL <- 10 <-> 5 -> NULL
Backward: NULL <- 5 <-> 10 -> NULL
```

---

### Double Linked List dengan OOP

```java
// File: DNode.java
class DNode {
    int data;
    DNode prev;
    DNode next;

    public DNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

// File: DoubleLinkedList.java
class DoubleLinkedList {
    private DNode head;
    private DNode tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Insert di awal - O(1)
    public void insertAtBeginning(int data) {
        DNode newNode = new DNode(data);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
        System.out.println("Insert di awal: " + data);
    }

    // Insert di akhir - O(1)
    public void insertAtEnd(int data) {
        DNode newNode = new DNode(data);

        if (tail == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
        System.out.println("Insert di akhir: " + data);
    }

    // Insert di posisi tertentu - O(n)
    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (position == 0) {
            insertAtBeginning(data);
            return;
        }

        if (position == size) {
            insertAtEnd(data);
            return;
        }

        DNode newNode = new DNode(data);
        DNode current = head;

        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;

        size++;
        System.out.println("Insert " + data + " di posisi " + position);
    }

    // Insert sebelum node tertentu - O(n)
    public void insertBefore(int target, int data) {
        DNode current = head;
        while (current != null && current.data != target) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Target " + target + " tidak ditemukan!");
            return;
        }

        DNode newNode = new DNode(data);

        if (current == head) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }

        size++;
        System.out.println("Insert " + data + " sebelum " + target);
    }

    // Insert setelah node tertentu - O(n)
    public void insertAfter(int target, int data) {
        DNode current = head;
        while (current != null && current.data != target) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Target " + target + " tidak ditemukan!");
            return;
        }

        DNode newNode = new DNode(data);

        if (current == tail) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            newNode.prev = current;
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;
        }

        size++;
        System.out.println("Insert " + data + " setelah " + target);
    }

    // Delete di awal - O(1)
    public void deleteAtBeginning() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        int deletedData = head.data;

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        System.out.println("Delete di awal: " + deletedData);
    }

    // Delete di akhir - O(1)
    public void deleteAtEnd() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        int deletedData = tail.data;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        System.out.println("Delete di akhir: " + deletedData);
    }

    // Delete berdasarkan nilai - O(n)
    public void deleteByValue(int value) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        DNode current = head;
        while (current != null && current.data != value) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Nilai " + value + " tidak ditemukan!");
            return;
        }

        if (current == head) {
            deleteAtBeginning();
            return;
        }

        if (current == tail) {
            deleteAtEnd();
            return;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        System.out.println("Delete nilai: " + value);
    }

    // Search - O(n)
    public int search(int value) {
        DNode current = head;
        int position = 0;
        while (current != null) {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    // Display maju - O(n)
    public void displayForward() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Forward:  NULL <- ");
        DNode current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println(" -> NULL");
    }

    // Display mundur - O(n)
    public void displayBackward() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Backward: NULL <- ");
        DNode current = tail;
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println(" -> NULL");
    }

    // Reverse - O(n)
    public void reverse() {
        DNode temp = null;
        DNode current = head;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        // Swap head dan tail
        temp = head;
        head = tail;
        tail = temp;

        System.out.println("List berhasil di-reverse");
    }

    // Get head data
    public int getHead() {
        if (isEmpty()) throw new RuntimeException("List kosong!");
        return head.data;
    }

    // Get tail data
    public int getTail() {
        if (isEmpty()) throw new RuntimeException("List kosong!");
        return tail.data;
    }
}

// File: DoubleLinkedListDemo.java
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== DOUBLE LINKED LIST DENGAN OOP ===\n");

        DoubleLinkedList list = new DoubleLinkedList();

        // Test insert
        list.insertAtBeginning(10);
        list.insertAtBeginning(20);
        list.insertAtEnd(5);
        list.insertAtEnd(15);

        System.out.println();
        list.displayForward();
        list.displayBackward();
        System.out.println("Size: " + list.getSize());
        System.out.println("Head: " + list.getHead() + ", Tail: " + list.getTail());

        // Test insert di posisi
        System.out.println();
        list.insertAtPosition(25, 2);
        list.displayForward();

        // Test insert before/after
        System.out.println();
        list.insertBefore(25, 100);
        list.insertAfter(25, 200);
        list.displayForward();

        // Test delete
        System.out.println();
        list.deleteAtBeginning();
        list.displayForward();

        System.out.println();
        list.deleteAtEnd();
        list.displayForward();

        System.out.println();
        list.deleteByValue(25);
        list.displayForward();
        list.displayBackward();

        // Test reverse
        System.out.println();
        list.reverse();
        list.displayForward();
        list.displayBackward();
    }
}
```

**Output:**
```
=== DOUBLE LINKED LIST DENGAN OOP ===

Insert di awal: 10
Insert di awal: 20
Insert di akhir: 5
Insert di akhir: 15

Forward:  NULL <- 20 <-> 10 <-> 5 <-> 15 -> NULL
Backward: NULL <- 15 <-> 5 <-> 10 <-> 20 -> NULL
Size: 4
Head: 20, Tail: 15

Insert 25 di posisi 2
Forward:  NULL <- 20 <-> 10 <-> 25 <-> 5 <-> 15 -> NULL

Insert 100 sebelum 25
Insert 200 setelah 25
Forward:  NULL <- 20 <-> 10 <-> 100 <-> 25 <-> 200 <-> 5 <-> 15 -> NULL

Delete di awal: 20
Forward:  NULL <- 10 <-> 100 <-> 25 <-> 200 <-> 5 <-> 15 -> NULL

Delete di akhir: 15
Forward:  NULL <- 10 <-> 100 <-> 25 <-> 200 <-> 5 -> NULL

Delete nilai: 25
Forward:  NULL <- 10 <-> 100 <-> 200 <-> 5 -> NULL
Backward: NULL <- 5 <-> 200 <-> 100 <-> 10 -> NULL

List berhasil di-reverse
Forward:  NULL <- 5 <-> 200 <-> 100 <-> 10 -> NULL
Backward: NULL <- 10 <-> 100 <-> 200 <-> 5 -> NULL
```

---

## Circular Single Linked List

### Konsep Circular Single Linked List

Circular Single Linked List adalah variasi dari single linked list dimana node terakhir menunjuk kembali ke node pertama (head), sehingga membentuk lingkaran.

```
     +--------------------------------------------------+
     |                                                  |
     v                                                  |
[HEAD] -> [Data|Next] -> [Data|Next] -> [Data|Next] ---+
```

### Keuntungan Circular Linked List
- Dapat mengakses node manapun dari node manapun
- Berguna untuk aplikasi yang membutuhkan siklus berulang (round-robin)
- Tidak ada null reference (kecuali list kosong)

### Aplikasi Circular Linked List
- Round-robin scheduling
- Multiplayer game turn management
- Music playlist repeat
- Buffer management

---

### Circular Single Linked List tanpa OOP

```java
public class CircularSingleLinkedListNoOOP {
    static int[] data = new int[100];
    static int[] next = new int[100];
    static int head = -1;
    static int tail = -1;
    static int size = 0;
    static int freeIndex = 0;

    public static int getNewIndex() {
        if (freeIndex >= 100) return -1;
        return freeIndex++;
    }

    // Insert di awal
    public static void insertAtBeginning(int value) {
        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;

        if (head == -1) {
            head = tail = newIndex;
            next[newIndex] = newIndex; // Point to itself
        } else {
            next[newIndex] = head;
            next[tail] = newIndex;
            head = newIndex;
        }

        size++;
        System.out.println("Insert di awal: " + value);
    }

    // Insert di akhir
    public static void insertAtEnd(int value) {
        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;

        if (head == -1) {
            head = tail = newIndex;
            next[newIndex] = newIndex;
        } else {
            next[newIndex] = head;
            next[tail] = newIndex;
            tail = newIndex;
        }

        size++;
        System.out.println("Insert di akhir: " + value);
    }

    // Delete di awal
    public static void deleteAtBeginning() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        int deletedValue = data[head];

        if (head == tail) {
            head = tail = -1;
        } else {
            head = next[head];
            next[tail] = head;
        }

        size--;
        System.out.println("Delete di awal: " + deletedValue);
    }

    // Delete di akhir
    public static void deleteAtEnd() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        int deletedValue = data[tail];

        if (head == tail) {
            head = tail = -1;
        } else {
            int current = head;
            while (next[current] != tail) {
                current = next[current];
            }
            tail = current;
            next[tail] = head;
        }

        size--;
        System.out.println("Delete di akhir: " + deletedValue);
    }

    // Display
    public static void display() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("List: ");
        int current = head;
        do {
            System.out.print(data[current]);
            current = next[current];
            if (current != head) {
                System.out.print(" -> ");
            }
        } while (current != head);
        System.out.println(" -> (back to " + data[head] + ")");
        System.out.println("Size: " + size);
    }

    // Traverse n kali (untuk demonstrasi circular)
    public static void traverse(int n) {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Traverse " + n + " langkah: ");
        int current = head;
        for (int i = 0; i < n; i++) {
            System.out.print(data[current] + " ");
            current = next[current];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== CIRCULAR SINGLE LINKED LIST TANPA OOP ===\n");

        insertAtBeginning(10);
        insertAtBeginning(20);
        insertAtEnd(5);
        insertAtEnd(15);
        display();

        // Demonstrasi circular
        System.out.println();
        traverse(10);

        System.out.println();
        deleteAtBeginning();
        display();

        System.out.println();
        deleteAtEnd();
        display();
    }
}
```

**Output:**
```
=== CIRCULAR SINGLE LINKED LIST TANPA OOP ===

Insert di awal: 10
Insert di awal: 20
Insert di akhir: 5
Insert di akhir: 15
List: 20 -> 10 -> 5 -> 15 -> (back to 20)
Size: 4

Traverse 10 langkah: 20 10 5 15 20 10 5 15 20 10

Delete di awal: 20
List: 10 -> 5 -> 15 -> (back to 10)
Size: 3

Delete di akhir: 15
List: 10 -> 5 -> (back to 10)
Size: 2
```

---

### Circular Single Linked List dengan OOP

```java
// File: CNode.java
class CNode {
    int data;
    CNode next;

    public CNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// File: CircularSingleLinkedList.java
class CircularSingleLinkedList {
    private CNode head;
    private CNode tail;
    private int size;

    public CircularSingleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Insert di awal - O(1)
    public void insertAtBeginning(int data) {
        CNode newNode = new CNode(data);

        if (head == null) {
            head = tail = newNode;
            newNode.next = newNode;
        } else {
            newNode.next = head;
            tail.next = newNode;
            head = newNode;
        }

        size++;
        System.out.println("Insert di awal: " + data);
    }

    // Insert di akhir - O(1)
    public void insertAtEnd(int data) {
        CNode newNode = new CNode(data);

        if (head == null) {
            head = tail = newNode;
            newNode.next = newNode;
        } else {
            newNode.next = head;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
        System.out.println("Insert di akhir: " + data);
    }

    // Insert di posisi tertentu - O(n)
    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (position == 0) {
            insertAtBeginning(data);
            return;
        }

        if (position == size) {
            insertAtEnd(data);
            return;
        }

        CNode newNode = new CNode(data);
        CNode current = head;

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;
        System.out.println("Insert " + data + " di posisi " + position);
    }

    // Delete di awal - O(1)
    public void deleteAtBeginning() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        int deletedData = head.data;

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }

        size--;
        System.out.println("Delete di awal: " + deletedData);
    }

    // Delete di akhir - O(n)
    public void deleteAtEnd() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        int deletedData = tail.data;

        if (head == tail) {
            head = tail = null;
        } else {
            CNode current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            tail.next = head;
        }

        size--;
        System.out.println("Delete di akhir: " + deletedData);
    }

    // Delete berdasarkan nilai - O(n)
    public void deleteByValue(int value) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        if (head.data == value) {
            deleteAtBeginning();
            return;
        }

        CNode current = head;
        while (current.next != head && current.next.data != value) {
            current = current.next;
        }

        if (current.next == head) {
            System.out.println("Nilai " + value + " tidak ditemukan!");
            return;
        }

        if (current.next == tail) {
            tail = current;
        }

        current.next = current.next.next;
        size--;
        System.out.println("Delete nilai: " + value);
    }

    // Search - O(n)
    public int search(int value) {
        if (isEmpty()) return -1;

        CNode current = head;
        int position = 0;

        do {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        } while (current != head);

        return -1;
    }

    // Display - O(n)
    public void display() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("List: ");
        CNode current = head;
        do {
            System.out.print(current.data);
            current = current.next;
            if (current != head) {
                System.out.print(" -> ");
            }
        } while (current != head);
        System.out.println(" -> (back to " + head.data + ")");
        System.out.println("Size: " + size);
    }

    // Traverse n langkah - demonstrasi circular
    public void traverse(int steps) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Traverse " + steps + " langkah: ");
        CNode current = head;
        for (int i = 0; i < steps; i++) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Split list menjadi dua
    public CircularSingleLinkedList[] split() {
        CircularSingleLinkedList[] result = new CircularSingleLinkedList[2];
        result[0] = new CircularSingleLinkedList();
        result[1] = new CircularSingleLinkedList();

        if (isEmpty() || size == 1) {
            System.out.println("Tidak bisa split list dengan size <= 1");
            return result;
        }

        CNode slow = head;
        CNode fast = head;

        // Find middle using slow and fast pointer
        while (fast.next != head && fast.next.next != head) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Buat dua circular list
        CNode current = head;
        while (current != slow.next) {
            result[0].insertAtEnd(current.data);
            current = current.next;
        }

        while (current != head) {
            result[1].insertAtEnd(current.data);
            current = current.next;
        }

        System.out.println("List berhasil di-split");
        return result;
    }

    // Josephus Problem - simulasi
    public int josephus(int k) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return -1;
        }

        CNode current = head;

        while (size > 1) {
            // Move k-1 steps
            for (int i = 0; i < k - 1; i++) {
                current = current.next;
            }

            System.out.println("Eliminasi: " + current.data);

            // Delete current node
            if (current == head) {
                deleteAtBeginning();
                current = head;
            } else {
                CNode prev = head;
                while (prev.next != current) {
                    prev = prev.next;
                }
                prev.next = current.next;
                if (current == tail) {
                    tail = prev;
                }
                current = current.next;
                size--;
            }
        }

        System.out.println("Pemenang: " + head.data);
        return head.data;
    }
}

// File: CircularSingleLinkedListDemo.java
public class CircularSingleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== CIRCULAR SINGLE LINKED LIST DENGAN OOP ===\n");

        CircularSingleLinkedList list = new CircularSingleLinkedList();

        // Test insert
        list.insertAtBeginning(10);
        list.insertAtBeginning(20);
        list.insertAtEnd(5);
        list.insertAtEnd(15);
        list.display();

        // Test circular traversal
        System.out.println();
        list.traverse(10);

        // Test insert di posisi
        System.out.println();
        list.insertAtPosition(25, 2);
        list.display();

        // Test search
        System.out.println();
        int pos = list.search(25);
        System.out.println("Posisi nilai 25: " + (pos != -1 ? pos : "Tidak ditemukan"));

        // Test delete
        System.out.println();
        list.deleteAtBeginning();
        list.display();

        System.out.println();
        list.deleteAtEnd();
        list.display();

        System.out.println();
        list.deleteByValue(25);
        list.display();

        // Demo Josephus Problem
        System.out.println("\n=== JOSEPHUS PROBLEM ===");
        CircularSingleLinkedList josephusList = new CircularSingleLinkedList();
        for (int i = 1; i <= 7; i++) {
            josephusList.insertAtEnd(i);
        }
        System.out.println("Peserta awal:");
        josephusList.display();
        System.out.println("\nProses eliminasi dengan k=3:");
        josephusList.josephus(3);
    }
}
```

**Output:**
```
=== CIRCULAR SINGLE LINKED LIST DENGAN OOP ===

Insert di awal: 10
Insert di awal: 20
Insert di akhir: 5
Insert di akhir: 15
List: 20 -> 10 -> 5 -> 15 -> (back to 20)
Size: 4

Traverse 10 langkah: 20 10 5 15 20 10 5 15 20 10

Insert 25 di posisi 2
List: 20 -> 10 -> 25 -> 5 -> 15 -> (back to 20)
Size: 5

Posisi nilai 25: 2

Delete di awal: 20
List: 10 -> 25 -> 5 -> 15 -> (back to 10)
Size: 4

Delete di akhir: 15
List: 10 -> 25 -> 5 -> (back to 10)
Size: 3

Delete nilai: 25
List: 10 -> 5 -> (back to 10)
Size: 2

=== JOSEPHUS PROBLEM ===
Peserta awal:
List: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> (back to 1)
Size: 7

Proses eliminasi dengan k=3:
Eliminasi: 3
Eliminasi: 6
Eliminasi: 2
Eliminasi: 7
Eliminasi: 5
Eliminasi: 1
Pemenang: 4
```

---

## Circular Double Linked List

### Konsep Circular Double Linked List

Circular Double Linked List menggabungkan fitur Double Linked List dan Circular Linked List. Setiap node memiliki pointer prev dan next, dan node terakhir terhubung ke node pertama (begitu juga sebaliknya).

```
    +--------------------------------------------------------+
    |                                                        |
    v                                                        |
[HEAD] <-> [Prev|Data|Next] <-> [Prev|Data|Next] <-> [TAIL] -+
    |                                                    ^
    +----------------------------------------------------+
```

### Keuntungan
- Traversal dua arah
- Dapat mengakses node manapun dari node manapun
- Operasi di head dan tail adalah O(1)

---

### Circular Double Linked List tanpa OOP

```java
public class CircularDoubleLinkedListNoOOP {
    static int[] data = new int[100];
    static int[] next = new int[100];
    static int[] prev = new int[100];
    static int head = -1;
    static int tail = -1;
    static int size = 0;
    static int freeIndex = 0;

    public static int getNewIndex() {
        if (freeIndex >= 100) return -1;
        return freeIndex++;
    }

    // Insert di awal
    public static void insertAtBeginning(int value) {
        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;

        if (head == -1) {
            head = tail = newIndex;
            next[newIndex] = newIndex;
            prev[newIndex] = newIndex;
        } else {
            next[newIndex] = head;
            prev[newIndex] = tail;
            prev[head] = newIndex;
            next[tail] = newIndex;
            head = newIndex;
        }

        size++;
        System.out.println("Insert di awal: " + value);
    }

    // Insert di akhir
    public static void insertAtEnd(int value) {
        int newIndex = getNewIndex();
        if (newIndex == -1) {
            System.out.println("List penuh!");
            return;
        }

        data[newIndex] = value;

        if (head == -1) {
            head = tail = newIndex;
            next[newIndex] = newIndex;
            prev[newIndex] = newIndex;
        } else {
            next[newIndex] = head;
            prev[newIndex] = tail;
            next[tail] = newIndex;
            prev[head] = newIndex;
            tail = newIndex;
        }

        size++;
        System.out.println("Insert di akhir: " + value);
    }

    // Delete di awal
    public static void deleteAtBeginning() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        int deletedValue = data[head];

        if (head == tail) {
            head = tail = -1;
        } else {
            head = next[head];
            prev[head] = tail;
            next[tail] = head;
        }

        size--;
        System.out.println("Delete di awal: " + deletedValue);
    }

    // Delete di akhir
    public static void deleteAtEnd() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        int deletedValue = data[tail];

        if (head == tail) {
            head = tail = -1;
        } else {
            tail = prev[tail];
            next[tail] = head;
            prev[head] = tail;
        }

        size--;
        System.out.println("Delete di akhir: " + deletedValue);
    }

    // Display maju
    public static void displayForward() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Forward:  ");
        int current = head;
        do {
            System.out.print(data[current]);
            current = next[current];
            if (current != head) {
                System.out.print(" <-> ");
            }
        } while (current != head);
        System.out.println(" <-> (back to " + data[head] + ")");
    }

    // Display mundur
    public static void displayBackward() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Backward: ");
        int current = tail;
        do {
            System.out.print(data[current]);
            current = prev[current];
            if (current != tail) {
                System.out.print(" <-> ");
            }
        } while (current != tail);
        System.out.println(" <-> (back to " + data[tail] + ")");
    }

    public static void main(String[] args) {
        System.out.println("=== CIRCULAR DOUBLE LINKED LIST TANPA OOP ===\n");

        insertAtBeginning(10);
        insertAtBeginning(20);
        insertAtEnd(5);
        insertAtEnd(15);

        System.out.println();
        displayForward();
        displayBackward();
        System.out.println("Size: " + size);

        System.out.println();
        deleteAtBeginning();
        displayForward();

        System.out.println();
        deleteAtEnd();
        displayForward();
        displayBackward();
    }
}
```

**Output:**
```
=== CIRCULAR DOUBLE LINKED LIST TANPA OOP ===

Insert di awal: 10
Insert di awal: 20
Insert di akhir: 5
Insert di akhir: 15

Forward:  20 <-> 10 <-> 5 <-> 15 <-> (back to 20)
Backward: 15 <-> 5 <-> 10 <-> 20 <-> (back to 15)
Size: 4

Delete di awal: 20
Forward:  10 <-> 5 <-> 15 <-> (back to 10)

Delete di akhir: 15
Forward:  10 <-> 5 <-> (back to 10)
Backward: 5 <-> 10 <-> (back to 5)
```

---

### Circular Double Linked List dengan OOP

```java
// File: CDNode.java
class CDNode {
    int data;
    CDNode prev;
    CDNode next;

    public CDNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

// File: CircularDoubleLinkedList.java
class CircularDoubleLinkedList {
    private CDNode head;
    private CDNode tail;
    private int size;

    public CircularDoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Insert di awal - O(1)
    public void insertAtBeginning(int data) {
        CDNode newNode = new CDNode(data);

        if (head == null) {
            head = tail = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }

        size++;
        System.out.println("Insert di awal: " + data);
    }

    // Insert di akhir - O(1)
    public void insertAtEnd(int data) {
        CDNode newNode = new CDNode(data);

        if (head == null) {
            head = tail = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            tail.next = newNode;
            head.prev = newNode;
            tail = newNode;
        }

        size++;
        System.out.println("Insert di akhir: " + data);
    }

    // Insert di posisi tertentu - O(n)
    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (position == 0) {
            insertAtBeginning(data);
            return;
        }

        if (position == size) {
            insertAtEnd(data);
            return;
        }

        CDNode newNode = new CDNode(data);
        CDNode current = head;

        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;

        size++;
        System.out.println("Insert " + data + " di posisi " + position);
    }

    // Delete di awal - O(1)
    public void deleteAtBeginning() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        int deletedData = head.data;

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }

        size--;
        System.out.println("Delete di awal: " + deletedData);
    }

    // Delete di akhir - O(1)
    public void deleteAtEnd() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        int deletedData = tail.data;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }

        size--;
        System.out.println("Delete di akhir: " + deletedData);
    }

    // Delete berdasarkan nilai - O(n)
    public void deleteByValue(int value) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        if (head.data == value) {
            deleteAtBeginning();
            return;
        }

        if (tail.data == value) {
            deleteAtEnd();
            return;
        }

        CDNode current = head.next;
        while (current != head && current.data != value) {
            current = current.next;
        }

        if (current == head) {
            System.out.println("Nilai " + value + " tidak ditemukan!");
            return;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        System.out.println("Delete nilai: " + value);
    }

    // Search - O(n)
    public int search(int value) {
        if (isEmpty()) return -1;

        CDNode current = head;
        int position = 0;

        do {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        } while (current != head);

        return -1;
    }

    // Display maju - O(n)
    public void displayForward() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Forward:  ");
        CDNode current = head;
        do {
            System.out.print(current.data);
            current = current.next;
            if (current != head) {
                System.out.print(" <-> ");
            }
        } while (current != head);
        System.out.println(" <-> (back to " + head.data + ")");
    }

    // Display mundur - O(n)
    public void displayBackward() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Backward: ");
        CDNode current = tail;
        do {
            System.out.print(current.data);
            current = current.prev;
            if (current != tail) {
                System.out.print(" <-> ");
            }
        } while (current != tail);
        System.out.println(" <-> (back to " + tail.data + ")");
    }

    // Traverse maju n langkah
    public void traverseForward(int steps) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Traverse forward " + steps + " langkah: ");
        CDNode current = head;
        for (int i = 0; i < steps; i++) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Traverse mundur n langkah
    public void traverseBackward(int steps) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("Traverse backward " + steps + " langkah: ");
        CDNode current = tail;
        for (int i = 0; i < steps; i++) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    // Rotate kiri (head ke arah tail)
    public void rotateLeft(int positions) {
        if (isEmpty() || size == 1 || positions == 0) return;

        positions = positions % size;
        for (int i = 0; i < positions; i++) {
            head = head.next;
            tail = tail.next;
        }
        System.out.println("Rotate left " + positions + " posisi");
    }

    // Rotate kanan (tail ke arah head)
    public void rotateRight(int positions) {
        if (isEmpty() || size == 1 || positions == 0) return;

        positions = positions % size;
        for (int i = 0; i < positions; i++) {
            head = head.prev;
            tail = tail.prev;
        }
        System.out.println("Rotate right " + positions + " posisi");
    }

    // Reverse
    public void reverse() {
        if (isEmpty() || size == 1) return;

        CDNode current = head;
        CDNode temp;

        do {
            temp = current.next;
            current.next = current.prev;
            current.prev = temp;
            current = temp;
        } while (current != head);

        temp = head;
        head = tail;
        tail = temp;

        System.out.println("List berhasil di-reverse");
    }
}

// File: CircularDoubleLinkedListDemo.java
public class CircularDoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== CIRCULAR DOUBLE LINKED LIST DENGAN OOP ===\n");

        CircularDoubleLinkedList list = new CircularDoubleLinkedList();

        // Test insert
        list.insertAtBeginning(10);
        list.insertAtBeginning(20);
        list.insertAtEnd(5);
        list.insertAtEnd(15);

        System.out.println();
        list.displayForward();
        list.displayBackward();
        System.out.println("Size: " + list.getSize());

        // Test insert di posisi
        System.out.println();
        list.insertAtPosition(25, 2);
        list.displayForward();

        // Test traverse
        System.out.println();
        list.traverseForward(10);
        list.traverseBackward(10);

        // Test search
        System.out.println();
        int pos = list.search(25);
        System.out.println("Posisi nilai 25: " + (pos != -1 ? pos : "Tidak ditemukan"));

        // Test rotate
        System.out.println();
        list.rotateLeft(2);
        list.displayForward();

        System.out.println();
        list.rotateRight(2);
        list.displayForward();

        // Test delete
        System.out.println();
        list.deleteAtBeginning();
        list.displayForward();

        System.out.println();
        list.deleteAtEnd();
        list.displayForward();

        System.out.println();
        list.deleteByValue(25);
        list.displayForward();
        list.displayBackward();

        // Test reverse
        System.out.println();
        list.insertAtEnd(100);
        list.insertAtEnd(200);
        list.displayForward();
        list.reverse();
        list.displayForward();
        list.displayBackward();
    }
}
```

**Output:**
```
=== CIRCULAR DOUBLE LINKED LIST DENGAN OOP ===

Insert di awal: 10
Insert di awal: 20
Insert di akhir: 5
Insert di akhir: 15

Forward:  20 <-> 10 <-> 5 <-> 15 <-> (back to 20)
Backward: 15 <-> 5 <-> 10 <-> 20 <-> (back to 15)
Size: 4

Insert 25 di posisi 2
Forward:  20 <-> 10 <-> 25 <-> 5 <-> 15 <-> (back to 20)

Traverse forward 10 langkah: 20 10 25 5 15 20 10 25 5 15
Traverse backward 10 langkah: 15 5 25 10 20 15 5 25 10 20

Posisi nilai 25: 2

Rotate left 2 posisi
Forward:  25 <-> 5 <-> 15 <-> 20 <-> 10 <-> (back to 25)

Rotate right 2 posisi
Forward:  20 <-> 10 <-> 25 <-> 5 <-> 15 <-> (back to 20)

Delete di awal: 20
Forward:  10 <-> 25 <-> 5 <-> 15 <-> (back to 10)

Delete di akhir: 15
Forward:  10 <-> 25 <-> 5 <-> (back to 10)

Delete nilai: 25
Forward:  10 <-> 5 <-> (back to 10)
Backward: 5 <-> 10 <-> (back to 5)

Insert di akhir: 100
Insert di akhir: 200
Forward:  10 <-> 5 <-> 100 <-> 200 <-> (back to 10)
List berhasil di-reverse
Forward:  200 <-> 100 <-> 5 <-> 10 <-> (back to 200)
Backward: 10 <-> 5 <-> 100 <-> 200 <-> (back to 10)
```

---

## Kompleksitas Linked List

### Tabel Kompleksitas Waktu

| Operasi | Single LL | Double LL | Circular Single LL | Circular Double LL |
|---------|-----------|-----------|-------------------|-------------------|
| **Insert at Beginning** | O(1) | O(1) | O(1) | O(1) |
| **Insert at End** | O(n)* | O(1) | O(1) | O(1) |
| **Insert at Position** | O(n) | O(n) | O(n) | O(n) |
| **Delete at Beginning** | O(1) | O(1) | O(1) | O(1) |
| **Delete at End** | O(n) | O(1) | O(n) | O(1) |
| **Delete by Value** | O(n) | O(n) | O(n) | O(n) |
| **Search** | O(n) | O(n) | O(n) | O(n) |
| **Access by Index** | O(n) | O(n) | O(n) | O(n) |
| **Reverse** | O(n) | O(n) | O(n) | O(n) |

*O(1) jika menggunakan tail pointer

### Kompleksitas Ruang

| Jenis Linked List | Space per Node | Total Space |
|-------------------|----------------|-------------|
| Single Linked List | data + 1 pointer | O(n) |
| Double Linked List | data + 2 pointer | O(n) |
| Circular Single LL | data + 1 pointer | O(n) |
| Circular Double LL | data + 2 pointer | O(n) |

### Penjelasan Kompleksitas

**Insert at Beginning - O(1):**
```java
// Hanya operasi konstan
Node newNode = new Node(data);  // O(1)
newNode.next = head;            // O(1)
head = newNode;                 // O(1)
```

**Insert at End - O(n) vs O(1):**
```java
// Tanpa tail pointer - O(n)
Node current = head;
while (current.next != null) {   // O(n) - traverse semua node
    current = current.next;
}
current.next = newNode;

// Dengan tail pointer - O(1)
tail.next = newNode;
tail = newNode;
```

**Search - O(n):**
```java
// Worst case: elemen ada di akhir atau tidak ada
Node current = head;
while (current != null) {  // O(n) - cek semua node
    if (current.data == value) return position;
    current = current.next;
}
```

---

## Latihan Hands-On

### Latihan 1: Merge Two Sorted Linked Lists

```java
public class MergeSortedLists {
    // Merge dua sorted list menjadi satu sorted list
    public static Node mergeSorted(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Append sisa
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;

        return dummy.next;
    }

    public static void main(String[] args) {
        SingleLinkedList list1 = new SingleLinkedList();
        list1.insertAtEnd(1);
        list1.insertAtEnd(3);
        list1.insertAtEnd(5);

        SingleLinkedList list2 = new SingleLinkedList();
        list2.insertAtEnd(2);
        list2.insertAtEnd(4);
        list2.insertAtEnd(6);

        System.out.println("List 1:");
        list1.display();
        System.out.println("List 2:");
        list2.display();

        // Merge (implementasi perlu akses ke head)
        System.out.println("\nSetelah merge: sorted list gabungan");
    }
}
```

### Latihan 2: Detect Cycle in Linked List

```java
public class DetectCycle {
    // Floyd's Cycle Detection Algorithm
    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) return false;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // Find cycle start
    public static Node findCycleStart(Node head) {
        if (head == null || head.next == null) return null;

        Node slow = head;
        Node fast = head;

        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Found cycle, now find start
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== DETECT CYCLE ===");
        // Test dengan circular linked list akan selalu return true
        // Test dengan regular linked list akan return false
    }
}
```

### Latihan 3: Find Middle of Linked List

```java
public class FindMiddle {
    // Using slow and fast pointer
    public static Node findMiddle(Node head) {
        if (head == null) return null;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        for (int i = 1; i <= 7; i++) {
            list.insertAtEnd(i);
        }

        System.out.println("List:");
        list.display();
        System.out.println("Elemen tengah: " + findMiddle(/* head */).data);
    }
}
```

### Latihan 4: Remove Nth Node From End

```java
public class RemoveNthFromEnd {
    public static void removeNthFromEnd(SingleLinkedList list, int n) {
        Node dummy = new Node(0);
        // dummy.next = list.getHead(); // perlu akses ke head

        Node first = dummy;
        Node second = dummy;

        // Maju n+1 langkah
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Maju bersamaan
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Hapus node
        second.next = second.next.next;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        for (int i = 1; i <= 5; i++) {
            list.insertAtEnd(i);
        }

        System.out.println("List awal:");
        list.display();
        System.out.println("Setelah hapus node ke-2 dari belakang:");
        // removeNthFromEnd(list, 2);
        list.display();
    }
}
```

### Latihan 5: Playlist Music Player (Circular Double Linked List)

```java
class Song {
    String title;
    String artist;
    int duration; // dalam detik

    public Song(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return title + " - " + artist + " (" + duration + "s)";
    }
}

public class MusicPlaylist {
    // Implementasi menggunakan Circular Double Linked List
    // Fitur: next, previous, shuffle, repeat

    public static void main(String[] args) {
        System.out.println("=== MUSIC PLAYLIST ===\n");

        CircularDoubleLinkedList playlist = new CircularDoubleLinkedList();

        // Tambah lagu (gunakan encoding integer untuk simplifikasi)
        playlist.insertAtEnd(1); // Song 1
        playlist.insertAtEnd(2); // Song 2
        playlist.insertAtEnd(3); // Song 3
        playlist.insertAtEnd(4); // Song 4

        System.out.println("Playlist:");
        playlist.displayForward();

        System.out.println("\nSimulasi play 8 lagu berturut-turut:");
        playlist.traverseForward(8);

        System.out.println("\nSimulasi previous 4 lagu:");
        playlist.traverseBackward(4);
    }
}
```

---

## Ringkasan

### Perbandingan Jenis Linked List

| Fitur | Single LL | Double LL | Circular Single | Circular Double |
|-------|-----------|-----------|-----------------|-----------------|
| Traversal | Satu arah | Dua arah | Satu arah, infinite | Dua arah, infinite |
| Memory/Node | Rendah | Tinggi | Rendah | Tinggi |
| Insert/Delete di akhir | O(n)* | O(1) | O(1) | O(1) |
| Kompleksitas implementasi | Mudah | Sedang | Sedang | Kompleks |
| Use case | General | Browser history | Round-robin | Music player |

*O(1) dengan tail pointer

### Kapan Menggunakan Jenis Linked List yang Mana?

**Single Linked List:**
- Data yang hanya perlu di-traverse satu arah
- Memori terbatas
- Operasi insert/delete di awal banyak

**Double Linked List:**
- Perlu traverse dua arah
- Browser history (back/forward)
- Undo/redo operations

**Circular Single Linked List:**
- Round-robin scheduling
- Multiplayer game turn
- Buffer circular sederhana

**Circular Double Linked List:**
- Music/video playlist dengan repeat
- Navigasi carousel
- Cache management (LRU)

---

## Tugas Praktikum

1. **Implementasikan method `getNthFromLast(int n)`** pada Single Linked List yang mengembalikan node ke-n dari belakang

2. **Buat program untuk mendeteksi dan menghapus loop** dalam linked list yang ter-corrupt

3. **Implementasikan Skip List** - variasi linked list dengan multiple levels untuk search O(log n)

4. **Buat LRU Cache** menggunakan Circular Double Linked List dan HashMap

5. **Implementasikan Polynomial Addition** menggunakan Linked List dimana setiap node menyimpan coefficient dan exponent

Selamat belajar!
