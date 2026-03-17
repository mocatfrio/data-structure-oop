#include <iostream>
using namespace std;

struct Mahasiswa
{
  string nama;
  int umur;
  float ipk;

  void tampilkanData()
  {
    cout << "Nama  : " << nama << endl;
    cout << "Umur  : " << umur << endl;
    cout << "IPK   : " << ipk << endl;
  }
};

int main()
{
  Mahasiswa m1;
  m1.nama = "Budi";
  m1.umur = 21;
  m1.ipk = 3.80;

  m1.tampilkanData();

  return 0;
}