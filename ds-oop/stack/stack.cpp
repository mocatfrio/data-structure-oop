#include <iostream>
#include <vector>
#include <chrono>

using namespace std;

class Stack
{
private:
    vector<int> stack;

public:
    // Menambahkan elemen ke stack
    void push(int value)
    {
        stack.push_back(value);
        cout << "Pushed: " << value << endl;
    }

    // Menghapus elemen dari stack
    void pop()
    {
        if (isEmpty())
        {
            cout << "Stack kosong! Tidak ada elemen untuk di-pop." << endl;
            return;
        }
        int value = stack.back();
        stack.pop_back();
        cout << "Popped: " << value << endl;
    }

    // Melihat elemen teratas stack
    int peek()
    {
        if (isEmpty())
        {
            cout << "Stack kosong! Tidak ada elemen untuk di-peek." << endl;
            return -1; // Nilai default jika stack kosong
        }
        return stack.back();
    }

    bool contains(int value)
    {
        for (int i = 0; i < stack.size(); i++)
        {
            if (stack[i] == value)
            {
                return true;
            }
        }
        return false;
    }

    bool isFull()
    {
        // Dalam konteks vector, stack tidak penuh kecuali memori habis.
        // Kita bisa menguji alokasi memori.
        try
        {
            vector<int> test;
            test.reserve(stack.size() + 1);
            return false;
        }
        catch (const bad_alloc &)
        {
            return true;
        }
    }

    // Mengecek apakah stack kosong
    bool isEmpty()
    {
        return stack.empty();
    }

    // Menampilkan semua elemen dalam stack
    void display()
    {
        if (isEmpty())
        {
            cout << "Stack kosong!" << endl;
            return;
        }
        cout << "Isi stack: ";
        for (int i = stack.size() - 1; i >= 0; i--)
        {
            cout << stack[i] << " ";
        }
        cout << endl;
    }

    void destroy()
    {
        stack.clear();
        cout << "Stack telah dihapus." << endl;
    }

    int count()
    {
        return stack.size();
    }

    void change(int position, int newValue)
    {
        if (position < 1 || position > stack.size())
        {
            cout << "Posisi tidak valid!" << endl;
            return;
        }
        stack[position - 1] = newValue;
        cout << "Data pada posisi " << position << " telah diubah menjadi " << newValue << "." << endl;
    }
};

int main()
{
    // Mengukur waktu eksekusi fungsi

    // Mencatat waktu mulai
    auto start = std::chrono::high_resolution_clock::now();

    Stack s;

    // Operasi pada stack
    s.push(10);
    s.push(20);
    s.push(30);
    s.display();

    cout << "Peek: " << s.peek() << endl;

    s.pop();
    s.display();

    s.pop();
    s.pop();
    s.pop(); // Pop pada stack kosong

    cout << "Apakah stack kosong? " << (s.isEmpty() ? "Ya" : "Tidak") << endl;

    s.push(10);
    s.push(20);
    s.push(30);
    s.display();

    cout << "\nJumlah elemen: " << s.count() << endl;

    cout << "\nUbah data pada posisi 2 menjadi 50:" << endl;
    s.change(2, 50);
    s.display();

    cout << "\nApakah stack penuh? " << (s.isFull() ? "Ya" : "Tidak") << endl;

    cout << "\nApakah stack mengandung 20? " << (s.contains(20) ? "Ya" : "Tidak") << endl;
    cout << "Apakah stack mengandung 100? " << (s.contains(100) ? "Ya" : "Tidak") << endl;

    cout << "\nHapus semua elemen:" << endl;
    s.destroy();
    s.display();

    // Mencatat waktu selesai
    auto end = std::chrono::high_resolution_clock::now();

    // Hitung durasi eksekusi
    std::chrono::duration<double> duration = end - start;

    // Tampilkan hasil
    std::cout << "Waktu eksekusi: " << duration.count() << " detik" << std::endl;

    return 0;
}