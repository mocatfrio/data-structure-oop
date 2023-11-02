#include <iostream>
#include <list>
#include <stack> 
#include <stdio.h>
using namespace std;

class Peta
{

private:
  // Property
  int jumlah_titik;
  list<int> *adjacency_list;
  int **adjacency_matrix;

public:
  // Constructor
  Peta(int jumlah_titik)
  {
    this->jumlah_titik = jumlah_titik;
    this->inisialisasiAdjList(jumlah_titik);
    this->inisialisasiAdjMatrix(jumlah_titik);
  }

  // Destructor
  ~Peta()
  {
    delete[] adjacency_list;
  }

  // Fungsi untuk inisialisasi adjacency list
  void inisialisasiAdjList(int jumlah_titik)
  {
    adjacency_list = new list<int>[jumlah_titik];
  }

  // Fungsi untuk inisialisasi adjacency matrix
  void inisialisasiAdjMatrix(int jumlah_titik)
  {
    adjacency_matrix = new int *[jumlah_titik];
    for (int i = 0; i < jumlah_titik; i++)
    {
      adjacency_matrix[i] = new int[jumlah_titik];
      for (int j = 0; j < jumlah_titik; j++)
      {
        adjacency_matrix[i][j] = 0; // Inisialisasi matriks dengan nilai 0(tidak ada edge)
      }
    }
  }

  // Fungsi untuk menambahkan koneksi dari titik awal ke tujuan
  void tambahLintasan(int titik_awal, int titik_tujuan)
  {
    // Update adjacency list 
    adjacency_list[titik_awal].push_back(titik_tujuan);
    
    // Update adjacency matrix
    adjacency_matrix[titik_awal][titik_tujuan] = 1;
    adjacency_matrix[titik_tujuan][titik_awal] = 1;
  }

  // Fungsi untuk menampilkan adjacency list
  void tampilkanAdjList()
  {
    list<int>::iterator i;

    for (int v = 0; v < jumlah_titik; v++)
    {
      cout << v << " -> ";
      for (i = adjacency_list[v].begin(); i != adjacency_list[v].end(); ++i)
      {
        cout << (*i);
        if (next(i, 1) != adjacency_list[v].end())
        {
          cout << " -> ";
        }
      }
      cout << endl;
    }
  }

  // Fungsi untuk menampilkan adjacency matrix
  void tampilkanAdjMatrix()
  {
    for (int i = 0; i < jumlah_titik; i++)
    {
      for (int j = 0; j < jumlah_titik; j++)
      {
        cout << adjacency_matrix[i][j] << " ";
      }
      cout << endl;
    }
  }
};

int main()
{
  cout << "Peta Rumah" << endl;
  int jumlah_titik = 5;

  // Ini "Object petaKu"
  // Object adalah instansiasi dari Class
  Peta petaKu(jumlah_titik);

  // Mendefinisikan data untuk graf Peta
  petaKu.tambahLintasan(0, 1); // dari titik 0 ke 1
  petaKu.tambahLintasan(0, 4); // dari titik 0 ke 4
  petaKu.tambahLintasan(1, 0);
  petaKu.tambahLintasan(1, 4);
  petaKu.tambahLintasan(1, 2);
  petaKu.tambahLintasan(1, 3);
  petaKu.tambahLintasan(2, 1);
  petaKu.tambahLintasan(2, 3);
  petaKu.tambahLintasan(3, 1);
  petaKu.tambahLintasan(3, 4);
  petaKu.tambahLintasan(3, 2);
  petaKu.tambahLintasan(4, 3);
  petaKu.tambahLintasan(4, 0);
  petaKu.tambahLintasan(4, 1);

  cout << endl;
  cout << "Adjacency List" << endl;
  petaKu.tampilkanAdjList();

  cout << endl;
  cout << "Adjacency Matrix" << endl;
  petaKu.tampilkanAdjMatrix();
}