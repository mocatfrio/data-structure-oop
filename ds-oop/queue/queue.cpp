#include <iostream>
#include <vector>

using namespace std;

class Queue {
private:
    vector<int> queue;
    size_t maxSize=100; // Optional: Define a maximum size for the queue

public:
    // Menambahkan elemen ke queue
    void enqueue(int value) {
        queue.push_back(value);
        cout << "Enqueued: " << value << endl;
    }

    // Menghapus elemen dari queue
    void dequeue() {
        if (isEmpty()) {
            cout << "Queue kosong! Tidak ada elemen untuk di-dequeue." << endl;
            return;
        }
        int value = queue.front();
        queue.erase(queue.begin());
        cout << "Dequeued: " << value << endl;
    }

    // Melihat elemen pertama dalam queue
    int peek() {
        if (isEmpty()) {
            cout << "Queue kosong! Tidak ada elemen untuk di-peek." << endl;
            return -1; // Nilai default jika queue kosong
        }
        return queue.front();
    }

    // Mengecek apakah elemen ada dalam queue
    bool contains(int value)
    {
        for (size_t i = 0; i < queue.size(); i++)
        { // Changed to indexed loop
            if (queue[i] == value)
            {
                return true;
            }
        }
        return false;
    }

    // Mengecek apakah queue penuh
    bool isFull() {
        return queue.size() >= maxSize;
    }

    // Mengecek apakah queue kosong
    bool isEmpty()
    {
        return queue.empty();
    }
    
    // Menghitung jumlah elemen dalam queue
    int count() {
        return queue.size();
    }

    // Menghapus semua elemen dalam queue
    void destroy() {
        queue.clear();
        cout << "Queue telah dihapus." << endl;
    }

    // Menampilkan semua elemen dalam queue
    void display() {
        if (isEmpty()) {
            cout << "Queue kosong!" << endl;
            return;
        }
        cout << "Isi queue: ";
        for (int i = 0; i < queue.size(); i++) {
            cout << queue[i] << " ";
        }
        cout << endl;
    }
};

int main() {
    Queue q;

    // Operasi pada queue
    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);
    q.display();
    cout << endl;

    cout << "Peek: " << q.peek() << endl;
    q.display();
    cout << endl;

    q.dequeue();
    q.display();
    cout << endl;

    cout << "Apakah queue kosong? " << (q.isEmpty() ? "Ya" : "Tidak") << endl;
    cout << endl;
    cout << "Apakah queue penuh? " << (q.isFull() ? "Ya" : "Tidak") << endl;
    cout << endl;
    cout << "Jumlah Queue = " << q.count() << endl;

    q.dequeue();
    q.dequeue();
    q.dequeue(); // Dequeue pada queue kosong

    cout << "Apakah queue kosong? " << (q.isEmpty() ? "Ya" : "Tidak") << endl;
    cout << endl;

    cout << "Apakah queue mengandung 20? " << (q.contains(20) ? "Ya" : "Tidak") << endl;
    cout << endl;

    cout << "Jumlah elemen dalam queue: " << q.count() << endl;
    cout << endl;

    q.destroy();
    q.display();

    return 0;
}
