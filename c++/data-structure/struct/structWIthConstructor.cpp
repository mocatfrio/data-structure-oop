#include <iostream>
using namespace std;

struct Mahasiswa
{
  string nama;
  int umur;
  float ipk;

  Mahasiswa(string n, int u, float i)
  {
    nama = n;
    umur = u;
    ipk = i;
  }

  void tampilkanData()
  {
    cout << "Nama  : " << nama << endl;
    cout << "Umur  : " << umur << endl;
    cout << "IPK   : " << ipk << endl;
  }
};

int main()
{
  Mahasiswa m1("Citra", 22, 3.90);
  m1.tampilkanData();

  return 0;
}