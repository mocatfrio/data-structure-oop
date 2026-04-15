# Module 05: Linked List, Stack, dan Queue

## Daftar Isi
1. [Linked List](#linked-list)
   - [Linked List tanpa OOP](#linked-list-tanpa-oop)
   - [Linked List dengan OOP](#linked-list-dengan-oop)
2. [Stack](#stack)
   - [Stack tanpa OOP](#stack-tanpa-oop)
   - [Stack dengan OOP](#stack-dengan-oop)
3. [Queue](#queue)
   - [Queue tanpa OOP](#queue-tanpa-oop)
   - [Queue dengan OOP](#queue-dengan-oop)
4. [Latihan Hands-On](#latihan-hands-on)

---

## Linked List

### Konsep Dasar
Linked List adalah struktur data linear yang terdiri dari node-node yang saling terhubung. Setiap node memiliki:
- **Data**: nilai yang disimpan
- **Next**: referensi ke node berikutnya

**Keuntungan Linked List:**
- Ukuran dinamis
- Mudah untuk insert dan delete
- Tidak membutuhkan alokasi memori yang kontinu

**Kekurangan:**
- Akses random tidak efisien (harus traverse dari awal)
- Membutuhkan memori ekstra untuk pointer

### Linked List tanpa OOP

#### Step 1: Membuat Program Sederhana dengan Array (Pendekatan Prosedural)

```java
public class LinkedListNoOOP {
    // Menggunakan array untuk simulasi linked list
    static int[] data = new int[100];
    static int[] next = new int[100];
    static int head = -1;
    static int size = 0;

    // Menambah node di awal
    public static void insertAtBeginning(int value) {
        if (size >= 100) {
            System.out.println("List penuh!");
            return;
        }

        data[size] = value;
        next[size] = head;
        head = size;
        size++;

        System.out.println("Berhasil menambahkan " + value + " di awal");
    }

    // Menambah node di akhir
    public static void insertAtEnd(int value) {
        if (size >= 100) {
            System.out.println("List penuh!");
            return;
        }

        data[size] = value;
        next[size] = -1;

        if (head == -1) {
            head = size;
        } else {
            int current = head;
            while (next[current] != -1) {
                current = next[current];
            }
            next[current] = size;
        }
        size++;

        System.out.println("Berhasil menambahkan " + value + " di akhir");
    }

    // Menampilkan semua node
    public static void display() {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("List: ");
        int current = head;
        while (current != -1) {
            System.out.print(data[current] + " -> ");
            current = next[current];
        }
        System.out.println("null");
    }

    // Menghapus node dengan nilai tertentu
    public static void delete(int value) {
        if (head == -1) {
            System.out.println("List kosong!");
            return;
        }

        // Jika node yang dihapus adalah head
        if (data[head] == value) {
            System.out.println("Menghapus " + value + " dari awal");
            head = next[head];
            return;
        }

        // Cari node yang akan dihapus
        int current = head;
        while (next[current] != -1 && data[next[current]] != value) {
            current = next[current];
        }

        if (next[current] == -1) {
            System.out.println("Nilai " + value + " tidak ditemukan!");
            return;
        }

        next[current] = next[next[current]];
        System.out.println("Berhasil menghapus " + value);
    }

    // Mencari node
    public static boolean search(int value) {
        int current = head;
        while (current != -1) {
            if (data[current] == value) {
                return true;
            }
            current = next[current];
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== LINKED LIST TANPA OOP ===\n");

        // Test insert
        insertAtBeginning(10);
        insertAtBeginning(20);
        insertAtEnd(5);
        insertAtEnd(15);
        display();

        // Test search
        System.out.println("\nMencari 10: " + (search(10) ? "Ditemukan" : "Tidak ditemukan"));
        System.out.println("Mencari 100: " + (search(100) ? "Ditemukan" : "Tidak ditemukan"));

        // Test delete
        System.out.println();
        delete(20);
        display();

        delete(5);
        display();
    }
}
```

**Output:**
```
=== LINKED LIST TANPA OOP ===

Berhasil menambahkan 10 di awal
Berhasil menambahkan 20 di awal
Berhasil menambahkan 5 di akhir
Berhasil menambahkan 15 di akhir
List: 20 -> 10 -> 5 -> 15 -> null

Mencari 10: Ditemukan
Mencari 100: Tidak ditemukan

Menghapus 20 dari awal
List: 10 -> 5 -> 15 -> null
Berhasil menghapus 5
List: 10 -> 15 -> null
```

---

### Linked List dengan OOP

#### Step 2: Membuat Class Node dan LinkedList

```java
// File: Node.java
class Node {
    int data;
    Node next;

    // Constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// File: LinkedList.java
class LinkedList {
    private Node head;
    private int size;

    // Constructor
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Getter untuk size
    public int getSize() {
        return size;
    }

    // Method untuk cek apakah list kosong
    public boolean isEmpty() {
        return head == null;
    }

    // Insert di awal
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("Berhasil menambahkan " + data + " di awal");
    }

    // Insert di akhir
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
        System.out.println("Berhasil menambahkan " + data + " di akhir");
    }

    // Insert di posisi tertentu
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
        System.out.println("Berhasil menambahkan " + data + " di posisi " + position);
    }

    // Delete dari awal
    public void deleteFromBeginning() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        int deletedData = head.data;
        head = head.next;
        size--;
        System.out.println("Berhasil menghapus " + deletedData + " dari awal");
    }

    // Delete dari akhir
    public void deleteFromEnd() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        if (head.next == null) {
            int deletedData = head.data;
            head = null;
            size--;
            System.out.println("Berhasil menghapus " + deletedData + " dari akhir");
            return;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        int deletedData = current.next.data;
        current.next = null;
        size--;
        System.out.println("Berhasil menghapus " + deletedData + " dari akhir");
    }

    // Delete node dengan nilai tertentu
    public void deleteByValue(int value) {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        // Jika node yang dihapus adalah head
        if (head.data == value) {
            head = head.next;
            size--;
            System.out.println("Berhasil menghapus " + value);
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
        System.out.println("Berhasil menghapus " + value);
    }

    // Search node
    public boolean search(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Get data pada posisi tertentu
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

    // Display semua node
    public void display() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }

        System.out.print("List: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Reverse linked list
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
}

// File: LinkedListOOPDemo.java
public class LinkedListOOPDemo {
    public static void main(String[] args) {
        System.out.println("=== LINKED LIST DENGAN OOP ===\n");

        LinkedList list = new LinkedList();

        // Test insert
        list.insertAtBeginning(10);
        list.insertAtBeginning(20);
        list.insertAtEnd(5);
        list.insertAtEnd(15);
        list.display();
        System.out.println("Ukuran list: " + list.getSize());

        // Test insert di posisi tertentu
        System.out.println("\nInsert 25 di posisi 2:");
        list.insertAtPosition(25, 2);
        list.display();

        // Test search
        System.out.println("\nMencari 10: " + (list.search(10) ? "Ditemukan" : "Tidak ditemukan"));
        System.out.println("Mencari 100: " + (list.search(100) ? "Ditemukan" : "Tidak ditemukan"));

        // Test get
        System.out.println("\nData pada posisi 0: " + list.get(0));
        System.out.println("Data pada posisi 2: " + list.get(2));

        // Test delete
        System.out.println();
        list.deleteFromBeginning();
        list.display();

        System.out.println();
        list.deleteFromEnd();
        list.display();

        System.out.println();
        list.deleteByValue(25);
        list.display();

        // Test reverse
        System.out.println();
        list.reverse();
        list.display();
    }
}
```

**Output:**
```
=== LINKED LIST DENGAN OOP ===

Berhasil menambahkan 10 di awal
Berhasil menambahkan 20 di awal
Berhasil menambahkan 5 di akhir
Berhasil menambahkan 15 di akhir
List: 20 -> 10 -> 5 -> 15 -> null
Ukuran list: 4

Insert 25 di posisi 2:
Berhasil menambahkan 25 di posisi 2
List: 20 -> 10 -> 25 -> 5 -> 15 -> null

Mencari 10: Ditemukan
Mencari 100: Tidak ditemukan

Data pada posisi 0: 20
Data pada posisi 2: 25

Berhasil menghapus 20 dari awal
List: 10 -> 25 -> 5 -> 15 -> null

Berhasil menghapus 15 dari akhir
List: 10 -> 25 -> 5 -> null

Berhasil menghapus 25
List: 10 -> 5 -> null

List berhasil di-reverse
List: 5 -> 10 -> null
```

---

## Stack

### Konsep Dasar
Stack adalah struktur data linear yang mengikuti prinsip **LIFO (Last In First Out)**. Elemen terakhir yang dimasukkan adalah elemen pertama yang dikeluarkan.

**Operasi Stack:**
- **Push**: Menambahkan elemen ke top
- **Pop**: Menghapus elemen dari top
- **Peek/Top**: Melihat elemen paling atas tanpa menghapus
- **isEmpty**: Cek apakah stack kosong

**Aplikasi Stack:**
- Function call management
- Undo mechanism
- Expression evaluation
- Backtracking algorithms

### Stack tanpa OOP

#### Step 3: Implementasi Stack dengan Array

```java
public class StackNoOOP {
    static int[] stack = new int[100];
    static int top = -1;
    static int MAX_SIZE = 100;

    // Push elemen ke stack
    public static void push(int value) {
        if (top >= MAX_SIZE - 1) {
            System.out.println("Stack Overflow! Stack penuh.");
            return;
        }

        top++;
        stack[top] = value;
        System.out.println("Push: " + value);
    }

    // Pop elemen dari stack
    public static int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow! Stack kosong.");
            return -1;
        }

        int value = stack[top];
        top--;
        System.out.println("Pop: " + value);
        return value;
    }

    // Peek elemen teratas
    public static int peek() {
        if (top < 0) {
            System.out.println("Stack kosong!");
            return -1;
        }

        return stack[top];
    }

    // Cek apakah stack kosong
    public static boolean isEmpty() {
        return top < 0;
    }

    // Cek apakah stack penuh
    public static boolean isFull() {
        return top >= MAX_SIZE - 1;
    }

    // Tampilkan semua elemen
    public static void display() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
            return;
        }

        System.out.print("Stack (bottom to top): ");
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    // Mendapatkan ukuran stack
    public static int size() {
        return top + 1;
    }

    public static void main(String[] args) {
        System.out.println("=== STACK TANPA OOP ===\n");

        // Test push
        push(10);
        push(20);
        push(30);
        push(40);
        display();

        System.out.println("\nUkuran stack: " + size());
        System.out.println("Elemen teratas: " + peek());

        // Test pop
        System.out.println();
        pop();
        pop();
        display();

        System.out.println("\nStack kosong? " + isEmpty());
        System.out.println("Stack penuh? " + isFull());
    }
}
```

**Output:**
```
=== STACK TANPA OOP ===

Push: 10
Push: 20
Push: 30
Push: 40
Stack (bottom to top): 10 20 30 40

Ukuran stack: 4
Elemen teratas: 40

Pop: 40
Pop: 30
Stack (bottom to top): 10 20

Stack kosong? false
Stack penuh? false
```

---

### Stack dengan OOP

#### Step 4: Implementasi Stack dengan Class

```java
// File: Stack.java
class Stack {
    private int[] stack;
    private int top;
    private int maxSize;

    // Constructor
    public Stack(int size) {
        this.maxSize = size;
        this.stack = new int[maxSize];
        this.top = -1;
    }

    // Push elemen
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack Overflow! Stack penuh.");
            return;
        }

        stack[++top] = value;
        System.out.println("Push: " + value);
    }

    // Pop elemen
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Stack kosong.");
            return -1;
        }

        int value = stack[top--];
        System.out.println("Pop: " + value);
        return value;
    }

    // Peek elemen teratas
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
            return -1;
        }

        return stack[top];
    }

    // Cek apakah kosong
    public boolean isEmpty() {
        return top == -1;
    }

    // Cek apakah penuh
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // Get ukuran
    public int size() {
        return top + 1;
    }

    // Display stack
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
            return;
        }

        System.out.print("Stack (bottom to top): ");
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    // Clear stack
    public void clear() {
        top = -1;
        System.out.println("Stack dikosongkan");
    }
}

// File: StackOOPDemo.java
public class StackOOPDemo {
    public static void main(String[] args) {
        System.out.println("=== STACK DENGAN OOP ===\n");

        Stack stack = new Stack(5);

        // Test push
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.display();

        System.out.println("\nUkuran stack: " + stack.size());
        System.out.println("Elemen teratas: " + stack.peek());

        // Test pop
        System.out.println();
        stack.pop();
        stack.pop();
        stack.display();

        // Test status
        System.out.println("\nStack kosong? " + stack.isEmpty());
        System.out.println("Stack penuh? " + stack.isFull());

        // Test overflow
        System.out.println("\nMengisi stack hingga penuh:");
        stack.push(50);
        stack.push(60);
        stack.push(70);
        stack.push(80); // Overflow
        stack.display();

        System.out.println("\nStack penuh? " + stack.isFull());
    }
}
```

---

#### Step 5: Stack dengan Linked List (OOP)

```java
// File: StackNode.java
class StackNode {
    int data;
    StackNode next;

    public StackNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// File: StackLinkedList.java
class StackLinkedList {
    private StackNode top;
    private int size;

    // Constructor
    public StackLinkedList() {
        this.top = null;
        this.size = 0;
    }

    // Push elemen
    public void push(int value) {
        StackNode newNode = new StackNode(value);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Push: " + value);
    }

    // Pop elemen
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Stack kosong.");
            return -1;
        }

        int value = top.data;
        top = top.next;
        size--;
        System.out.println("Pop: " + value);
        return value;
    }

    // Peek elemen teratas
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
            return -1;
        }

        return top.data;
    }

    // Cek apakah kosong
    public boolean isEmpty() {
        return top == null;
    }

    // Get ukuran
    public int size() {
        return size;
    }

    // Display stack
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
            return;
        }

        System.out.print("Stack (top to bottom): ");
        StackNode current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

// File: StackLinkedListDemo.java
public class StackLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== STACK DENGAN LINKED LIST (OOP) ===\n");

        StackLinkedList stack = new StackLinkedList();

        // Test push
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.display();

        System.out.println("\nUkuran stack: " + stack.size());
        System.out.println("Elemen teratas: " + stack.peek());

        // Test pop
        System.out.println();
        stack.pop();
        stack.pop();
        stack.display();

        System.out.println("\nStack kosong? " + stack.isEmpty());
    }
}
```

---

## Queue

### Konsep Dasar
Queue adalah struktur data linear yang mengikuti prinsip **FIFO (First In First Out)**. Elemen pertama yang dimasukkan adalah elemen pertama yang dikeluarkan.

**Operasi Queue:**
- **Enqueue**: Menambahkan elemen di belakang (rear)
- **Dequeue**: Menghapus elemen dari depan (front)
- **Peek/Front**: Melihat elemen paling depan tanpa menghapus
- **isEmpty**: Cek apakah queue kosong

**Aplikasi Queue:**
- Task scheduling
- Print spooling
- BFS (Breadth First Search)
- Handling requests in web servers

### Queue tanpa OOP

#### Step 6: Implementasi Queue dengan Array

```java
public class QueueNoOOP {
    static int[] queue = new int[100];
    static int front = -1;
    static int rear = -1;
    static int MAX_SIZE = 100;

    // Enqueue elemen ke queue
    public static void enqueue(int value) {
        if (rear >= MAX_SIZE - 1) {
            System.out.println("Queue Overflow! Queue penuh.");
            return;
        }

        if (front == -1) {
            front = 0;
        }

        rear++;
        queue[rear] = value;
        System.out.println("Enqueue: " + value);
    }

    // Dequeue elemen dari queue
    public static int dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("Queue Underflow! Queue kosong.");
            return -1;
        }

        int value = queue[front];
        front++;

        // Reset queue jika kosong
        if (front > rear) {
            front = rear = -1;
        }

        System.out.println("Dequeue: " + value);
        return value;
    }

    // Peek elemen depan
    public static int peek() {
        if (front == -1 || front > rear) {
            System.out.println("Queue kosong!");
            return -1;
        }

        return queue[front];
    }

    // Cek apakah queue kosong
    public static boolean isEmpty() {
        return front == -1 || front > rear;
    }

    // Tampilkan semua elemen
    public static void display() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return;
        }

        System.out.print("Queue (front to rear): ");
        for (int i = front; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

    // Mendapatkan ukuran queue
    public static int size() {
        if (isEmpty()) {
            return 0;
        }
        return rear - front + 1;
    }

    public static void main(String[] args) {
        System.out.println("=== QUEUE TANPA OOP ===\n");

        // Test enqueue
        enqueue(10);
        enqueue(20);
        enqueue(30);
        enqueue(40);
        display();

        System.out.println("\nUkuran queue: " + size());
        System.out.println("Elemen depan: " + peek());

        // Test dequeue
        System.out.println();
        dequeue();
        dequeue();
        display();

        System.out.println("\nQueue kosong? " + isEmpty());

        // Test enqueue lagi
        System.out.println();
        enqueue(50);
        enqueue(60);
        display();
    }
}
```

**Output:**
```
=== QUEUE TANPA OOP ===

Enqueue: 10
Enqueue: 20
Enqueue: 30
Enqueue: 40
Queue (front to rear): 10 20 30 40

Ukuran queue: 4
Elemen depan: 10

Dequeue: 10
Dequeue: 20
Queue (front to rear): 30 40

Queue kosong? false

Enqueue: 50
Enqueue: 60
Queue (front to rear): 30 40 50 60
```

---

### Queue dengan OOP

#### Step 7: Implementasi Queue dengan Array (Circular Queue)

```java
// File: Queue.java
class Queue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int maxSize;

    // Constructor
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new int[maxSize];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    // Enqueue elemen
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue Overflow! Queue penuh.");
            return;
        }

        if (front == -1) {
            front = 0;
        }

        rear = (rear + 1) % maxSize;
        queue[rear] = value;
        size++;
        System.out.println("Enqueue: " + value);
    }

    // Dequeue elemen
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! Queue kosong.");
            return -1;
        }

        int value = queue[front];

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % maxSize;
        }

        size--;
        System.out.println("Dequeue: " + value);
        return value;
    }

    // Peek elemen depan
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return -1;
        }

        return queue[front];
    }

    // Cek apakah kosong
    public boolean isEmpty() {
        return front == -1;
    }

    // Cek apakah penuh
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // Get ukuran
    public int size() {
        return size;
    }

    // Display queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return;
        }

        System.out.print("Queue (front to rear): ");
        int i = front;
        while (true) {
            System.out.print(queue[i] + " ");
            if (i == rear) break;
            i = (i + 1) % maxSize;
        }
        System.out.println();
    }
}

// File: QueueOOPDemo.java
public class QueueOOPDemo {
    public static void main(String[] args) {
        System.out.println("=== QUEUE DENGAN OOP (Circular Queue) ===\n");

        Queue queue = new Queue(5);

        // Test enqueue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.display();

        System.out.println("\nUkuran queue: " + queue.size());
        System.out.println("Elemen depan: " + queue.peek());

        // Test dequeue
        System.out.println();
        queue.dequeue();
        queue.dequeue();
        queue.display();

        // Test circular
        System.out.println("\nTest circular:");
        queue.enqueue(50);
        queue.enqueue(60);
        queue.enqueue(70);
        queue.display();

        System.out.println("\nQueue penuh? " + queue.isFull());
    }
}
```

---

#### Step 8: Queue dengan Linked List (OOP)

```java
// File: QueueNode.java
class QueueNode {
    int data;
    QueueNode next;

    public QueueNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// File: QueueLinkedList.java
class QueueLinkedList {
    private QueueNode front;
    private QueueNode rear;
    private int size;

    // Constructor
    public QueueLinkedList() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Enqueue elemen
    public void enqueue(int value) {
        QueueNode newNode = new QueueNode(value);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
        System.out.println("Enqueue: " + value);
    }

    // Dequeue elemen
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! Queue kosong.");
            return -1;
        }

        int value = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        System.out.println("Dequeue: " + value);
        return value;
    }

    // Peek elemen depan
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return -1;
        }

        return front.data;
    }

    // Cek apakah kosong
    public boolean isEmpty() {
        return front == null;
    }

    // Get ukuran
    public int size() {
        return size;
    }

    // Display queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
            return;
        }

        System.out.print("Queue (front to rear): ");
        QueueNode current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

// File: QueueLinkedListDemo.java
public class QueueLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("=== QUEUE DENGAN LINKED LIST (OOP) ===\n");

        QueueLinkedList queue = new QueueLinkedList();

        // Test enqueue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.display();

        System.out.println("\nUkuran queue: " + queue.size());
        System.out.println("Elemen depan: " + queue.peek());

        // Test dequeue
        System.out.println();
        queue.dequeue();
        queue.dequeue();
        queue.display();

        System.out.println("\nQueue kosong? " + queue.isEmpty());

        // Test enqueue lagi
        System.out.println();
        queue.enqueue(50);
        queue.enqueue(60);
        queue.enqueue(70);
        queue.display();
    }
}
```

---

## Latihan Hands-On

### Latihan 1: Palindrome Checker dengan Stack
Buat program untuk mengecek apakah sebuah string adalah palindrome menggunakan Stack.

```java
public class PalindromeChecker {
    public static boolean isPalindrome(String str) {
        Stack stack = new Stack(str.length());
        String cleanStr = str.toLowerCase().replaceAll("[^a-z0-9]", "");

        // Push semua karakter ke stack
        for (int i = 0; i < cleanStr.length(); i++) {
            stack.push(cleanStr.charAt(i));
        }

        // Compare dengan string asli
        for (int i = 0; i < cleanStr.length(); i++) {
            if (cleanStr.charAt(i) != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] testCases = {"radar", "hello", "level", "A man a plan a canal Panama"};

        for (String test : testCases) {
            System.out.println("\"" + test + "\" adalah palindrome? " +
                             isPalindrome(test));
        }
    }
}
```

### Latihan 2: Reverse String dengan Stack
Buat program untuk membalikkan string menggunakan Stack.

```java
public class ReverseString {
    public static String reverse(String str) {
        StackLinkedList stack = new StackLinkedList();

        // Push semua karakter
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }

        // Pop semua karakter untuk membentuk string terbalik
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append((char) stack.pop());
        }

        return reversed.toString();
    }

    public static void main(String[] args) {
        String original = "Hello World";
        String reversed = reverse(original);

        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);
    }
}
```

### Latihan 3: Task Scheduler dengan Queue
Simulasi task scheduler sederhana menggunakan Queue.

```java
class Task {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return name + " (Priority: " + priority + ")";
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        QueueLinkedList taskQueue = new QueueLinkedList();

        // Menambahkan tasks
        System.out.println("=== TASK SCHEDULER ===\n");
        System.out.println("Menambahkan tasks ke queue:");

        taskQueue.enqueue(new Task("Task 1: Backup Database", 1));
        taskQueue.enqueue(new Task("Task 2: Update System", 2));
        taskQueue.enqueue(new Task("Task 3: Send Emails", 3));
        taskQueue.enqueue(new Task("Task 4: Generate Reports", 4));

        // Memproses tasks
        System.out.println("\nMemproses tasks:");
        while (!taskQueue.isEmpty()) {
            Task task = (Task) taskQueue.dequeue();
            System.out.println("Processing: " + task);

            // Simulasi processing time
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nSemua tasks selesai!");
    }
}
```

### Latihan 4: Browser History dengan Stack
Simulasi browser history menggunakan dua Stack (back dan forward).

```java
class BrowserHistory {
    private StackLinkedList backStack;
    private StackLinkedList forwardStack;
    private String currentPage;

    public BrowserHistory(String homepage) {
        backStack = new StackLinkedList();
        forwardStack = new StackLinkedList();
        currentPage = homepage;
    }

    public void visit(String url) {
        backStack.push(currentPage);
        currentPage = url;
        // Clear forward stack
        forwardStack = new StackLinkedList();
        System.out.println("Mengunjungi: " + url);
    }

    public void back() {
        if (backStack.isEmpty()) {
            System.out.println("Tidak ada halaman sebelumnya");
            return;
        }

        forwardStack.push(currentPage);
        currentPage = (String) backStack.pop();
        System.out.println("Kembali ke: " + currentPage);
    }

    public void forward() {
        if (forwardStack.isEmpty()) {
            System.out.println("Tidak ada halaman berikutnya");
            return;
        }

        backStack.push(currentPage);
        currentPage = (String) forwardStack.pop();
        System.out.println("Maju ke: " + currentPage);
    }

    public void showCurrent() {
        System.out.println("Halaman saat ini: " + currentPage);
    }
}

public class BrowserHistoryDemo {
    public static void main(String[] args) {
        System.out.println("=== BROWSER HISTORY SIMULATOR ===\n");

        BrowserHistory browser = new BrowserHistory("google.com");
        browser.showCurrent();

        System.out.println();
        browser.visit("facebook.com");
        browser.visit("youtube.com");
        browser.visit("github.com");
        browser.showCurrent();

        System.out.println();
        browser.back();
        browser.back();
        browser.showCurrent();

        System.out.println();
        browser.forward();
        browser.showCurrent();
    }
}
```

### Latihan 5: Print Queue Management
Simulasi print queue management.

```java
class PrintJob {
    String documentName;
    int pages;

    public PrintJob(String documentName, int pages) {
        this.documentName = documentName;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return documentName + " (" + pages + " pages)";
    }
}

public class PrintQueueManager {
    public static void main(String[] args) {
        System.out.println("=== PRINT QUEUE MANAGER ===\n");

        QueueLinkedList printQueue = new QueueLinkedList();

        // Menambahkan print jobs
        System.out.println("Menambahkan print jobs:");
        printQueue.enqueue(new PrintJob("Document1.pdf", 5));
        printQueue.enqueue(new PrintJob("Report.docx", 10));
        printQueue.enqueue(new PrintJob("Presentation.pptx", 20));
        printQueue.enqueue(new PrintJob("Spreadsheet.xlsx", 3));

        System.out.println("Total jobs dalam queue: " + printQueue.size());

        // Memproses print jobs
        System.out.println("\nMemproses print jobs:");
        int jobNumber = 1;
        while (!printQueue.isEmpty()) {
            PrintJob job = (PrintJob) printQueue.dequeue();
            System.out.println("Job #" + jobNumber + ": Printing " + job);
            jobNumber++;

            // Simulasi printing time
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nSemua print jobs selesai!");
    }
}
```

---

## Kompleksitas Struktur Data

### Apa itu Kompleksitas?

Kompleksitas adalah ukuran yang digunakan untuk menganalisis efisiensi algoritma dan struktur data. Ada dua jenis kompleksitas utama:

1. **Time Complexity (Kompleksitas Waktu)**: Mengukur berapa lama waktu yang dibutuhkan untuk menjalankan operasi
2. **Space Complexity (Kompleksitas Ruang)**: Mengukur berapa banyak memori yang dibutuhkan

### Big O Notation

Big O Notation adalah notasi matematika untuk menggambarkan batas atas (upper bound) dari kompleksitas algoritma. Berikut notasi yang umum digunakan:

| Notasi | Nama | Contoh |
|--------|------|--------|
| O(1) | Constant | Akses array dengan index |
| O(log n) | Logarithmic | Binary search |
| O(n) | Linear | Linear search |
| O(n log n) | Linearithmic | Merge sort, Quick sort |
| O(n²) | Quadratic | Bubble sort, Selection sort |
| O(2ⁿ) | Exponential | Recursive Fibonacci |

**Urutan dari tercepat ke terlambat:**
```
O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(2ⁿ) < O(n!)
```

### Kompleksitas Linked List

| Operasi | Singly Linked List | Doubly Linked List | Penjelasan |
|---------|-------------------|-------------------|------------|
| **Access (by index)** | O(n) | O(n) | Harus traverse dari head |
| **Search** | O(n) | O(n) | Harus cek setiap node |
| **Insert at Beginning** | O(1) | O(1) | Hanya update pointer head |
| **Insert at End** | O(n) / O(1)* | O(1) | *O(1) jika ada tail pointer |
| **Insert at Position** | O(n) | O(n) | Harus traverse ke posisi |
| **Delete at Beginning** | O(1) | O(1) | Hanya update pointer head |
| **Delete at End** | O(n) | O(1) | Singly harus cari node sebelum tail |
| **Delete by Value** | O(n) | O(n) | Harus cari node terlebih dahulu |

**Space Complexity:** O(n) - membutuhkan memori untuk n node

**Contoh Analisis:**

```java
// Insert at Beginning - O(1)
public void insertAtBeginning(int data) {
    Node newNode = new Node(data);  // O(1)
    newNode.next = head;            // O(1)
    head = newNode;                 // O(1)
    size++;                         // O(1)
}
// Total: O(1) - tidak bergantung pada jumlah elemen

// Search - O(n)
public boolean search(int value) {
    Node current = head;
    while (current != null) {        // Loop maksimal n kali
        if (current.data == value) {
            return true;
        }
        current = current.next;
    }
    return false;
}
// Total: O(n) - worst case harus cek semua elemen
```

### Kompleksitas Stack

| Operasi | Array-based | Linked List-based | Penjelasan |
|---------|-------------|-------------------|------------|
| **Push** | O(1)* | O(1) | *Amortized, bisa O(n) jika resize |
| **Pop** | O(1) | O(1) | Hanya update top pointer |
| **Peek/Top** | O(1) | O(1) | Langsung akses top |
| **isEmpty** | O(1) | O(1) | Cek kondisi |
| **isFull** | O(1) | N/A | Linked list tidak punya batas |
| **Search** | O(n) | O(n) | Harus traverse stack |

**Space Complexity:**
- Array-based: O(n) - fixed size atau dynamic
- Linked List-based: O(n) - sesuai jumlah elemen

**Contoh Analisis:**

```java
// Push dengan Array - O(1) amortized
public void push(int value) {
    if (isFull()) {
        // Jika array dinamis: resize O(n), tapi jarang terjadi
        return;
    }
    stack[++top] = value;  // O(1)
}

// Push dengan Linked List - O(1)
public void push(int value) {
    StackNode newNode = new StackNode(value);  // O(1)
    newNode.next = top;                         // O(1)
    top = newNode;                              // O(1)
    size++;                                     // O(1)
}
// Total: O(1) - selalu konstan
```

### Kompleksitas Queue

| Operasi | Array-based | Circular Array | Linked List-based | Penjelasan |
|---------|-------------|----------------|-------------------|------------|
| **Enqueue** | O(1)* | O(1) | O(1) | *O(n) jika perlu shift |
| **Dequeue** | O(n)** | O(1) | O(1) | **Perlu shift semua elemen |
| **Peek/Front** | O(1) | O(1) | O(1) | Langsung akses front |
| **isEmpty** | O(1) | O(1) | O(1) | Cek kondisi |
| **isFull** | O(1) | O(1) | N/A | Linked list tidak terbatas |

**Space Complexity:** O(n)

**Mengapa Circular Queue Lebih Efisien?**

```java
// Linear Queue - Dequeue O(n)
public int dequeue() {
    int value = queue[front];
    // Shift semua elemen ke kiri - O(n)
    for (int i = 0; i < rear; i++) {
        queue[i] = queue[i + 1];
    }
    rear--;
    return value;
}

// Circular Queue - Dequeue O(1)
public int dequeue() {
    int value = queue[front];
    front = (front + 1) % maxSize;  // O(1)
    size--;
    return value;
}
```

### Perbandingan Implementasi Array vs Linked List

| Aspek | Array | Linked List |
|-------|-------|-------------|
| **Memory** | Kontinu, fixed size | Non-kontinu, dynamic |
| **Access by Index** | O(1) | O(n) |
| **Insert/Delete at Beginning** | O(n) | O(1) |
| **Insert/Delete at End** | O(1) amortized | O(1) dengan tail pointer |
| **Memory Overhead** | Rendah | Tinggi (pointer tambahan) |
| **Cache Performance** | Bagus (locality) | Buruk (scattered) |
| **Resizing** | Mahal (copy all) | Tidak perlu |

### Contoh Perhitungan Kompleksitas

**Soal 1: Reverse Linked List**

```java
public void reverse() {
    Node prev = null;
    Node current = head;
    Node next = null;

    while (current != null) {    // Loop n kali
        next = current.next;     // O(1)
        current.next = prev;     // O(1)
        prev = current;          // O(1)
        current = next;          // O(1)
    }
    head = prev;                 // O(1)
}
// Time Complexity: O(n) - traverse semua node sekali
// Space Complexity: O(1) - hanya menggunakan 3 variabel pointer
```

**Soal 2: Palindrome Check dengan Stack**

```java
public boolean isPalindrome(String str) {
    Stack stack = new Stack(str.length());

    // Push semua karakter - O(n)
    for (int i = 0; i < str.length(); i++) {
        stack.push(str.charAt(i));
    }

    // Pop dan compare - O(n)
    for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) != stack.pop()) {
            return false;
        }
    }
    return true;
}
// Time Complexity: O(n) + O(n) = O(2n) = O(n)
// Space Complexity: O(n) - untuk stack
```

**Soal 3: BFS dengan Queue**

```java
public void bfs(int start) {
    Queue queue = new Queue(vertices);
    boolean[] visited = new boolean[vertices];

    visited[start] = true;
    queue.enqueue(start);

    while (!queue.isEmpty()) {           // O(V) - setiap vertex
        int current = queue.dequeue();

        for (int neighbor : adj[current]) {  // O(E) - total semua edge
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.enqueue(neighbor);
            }
        }
    }
}
// Time Complexity: O(V + E) - V vertices + E edges
// Space Complexity: O(V) - untuk queue dan visited array
```

### Tips Optimasi

1. **Gunakan implementasi yang tepat:**
   - Stack/Queue dengan banyak operasi → Linked List
   - Perlu akses random → Array

2. **Pertimbangkan trade-off:**
   - Memory vs Speed
   - Simplicity vs Efficiency

3. **Hindari operasi O(n) yang tidak perlu:**
   - Gunakan tail pointer untuk insert di akhir
   - Gunakan circular queue daripada linear queue

4. **Amortized Analysis:**
   - Beberapa operasi mahal tapi jarang (seperti resize array)
   - Rata-rata tetap efisien

### Latihan Analisis Kompleksitas

1. **Apa kompleksitas waktu untuk menghapus semua elemen dari stack dengan n elemen?**
   - Jawaban: O(n) - karena pop O(1) dilakukan n kali

2. **Apa kompleksitas waktu untuk mencari elemen minimum dalam linked list?**
   - Jawaban: O(n) - harus traverse semua node

3. **Apa kompleksitas ruang untuk menyimpan queue dengan linked list berisi n elemen?**
   - Jawaban: O(n) - n node, masing-masing berisi data dan pointer

---

## Ringkasan

### Perbedaan Utama Tanpa OOP vs Dengan OOP

| Aspek | Tanpa OOP | Dengan OOP |
|-------|-----------|------------|
| **Struktur** | Menggunakan array dan variabel global | Menggunakan class dan object |
| **Enkapsulasi** | Data dan method terpisah | Data dan method tergabung dalam class |
| **Reusability** | Sulit digunakan ulang | Mudah dibuat multiple instance |
| **Maintenance** | Sulit dimaintain | Lebih mudah dimaintain |
| **Scalability** | Terbatas | Lebih scalable |

### Kapan Menggunakan Stack vs Queue?

**Stack (LIFO):**
- Undo/Redo operations
- Function call stack
- Expression evaluation
- Backtracking problems
- Depth First Search (DFS)

**Queue (FIFO):**
- Task scheduling
- Resource sharing (printer queue)
- Breadth First Search (BFS)
- Asynchronous data transfer
- Handling requests in order

### Tips Praktis

1. **Linked List** cocok untuk data yang sering berubah ukurannya
2. **Stack** cocok untuk operasi yang membutuhkan akses elemen terakhir
3. **Queue** cocok untuk operasi yang membutuhkan pemrosesan berurutan
4. Gunakan **OOP** untuk project yang kompleks dan membutuhkan reusability
5. Gunakan **non-OOP** untuk script sederhana atau pembelajaran konsep dasar

---

## Tugas Praktikum

1. Implementasikan **Double Linked List** dengan OOP
2. Buat **Priority Queue** menggunakan Linked List
3. Implementasikan **Deque (Double-ended Queue)** dengan array circular
4. Buat aplikasi **Calculator** dengan Stack untuk evaluasi ekspresi infix
5. Buat **Playlist Manager** menggunakan Linked List dengan fitur shuffle menggunakan Queue

Selamat belajar!
