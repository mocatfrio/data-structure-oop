#include <iostream>
#include <string>
#include <array>

using namespace std;

#define MAX_MHS 50

struct Mahasiswa
{
  string nama;
  string nrp;
  int umur;
  bool jenis_kelamin;
};

int main()
{
  // define struct array
  struct Mahasiswa mhs[MAX_MHS];

  Mahasiswa *mhs = new Mahasiswa[MAX_MHS];

  // define dummy data
  int jumlah_mhs = 10;

  for (int i = 0; i < jumlah_mhs; i++)
  {
    mhs[i].nama = "Nama " + to_string(i + 1);
    mhs[i].nrp = "0511151900" + to_string(i + 1);
    mhs[i].umur = 17 + (rand() % 20);
    mhs[i].jenis_kelamin = (rand() % 2);

    cout << "==== Mahasiswa " + to_string(i + 1) + " ====" << endl;
    cout << "Nama: " << mhs[i].nama << endl;
    cout << "NRP: " << mhs[i].nrp << endl;
    cout << "Umur: " << mhs[i].umur << endl;
    cout << "Jenis Kelamin: " << (mhs[i].jenis_kelamin == 0 ? "Perempuan" : "Laki-Laki") << endl;
    cout << endl;
  }

  // int length_arr = (sizeof(mhs) / sizeof(*mhs));

  // cout << length_arr << endl;

  int input_user = 1;

  while (input_user > 0 && input_user < 5)
  {
    cout << "Menu: " << endl;
    cout << "1. Menambah Data " << endl;
    cout << "2. Menghapus Data " << endl;
    cout << "3. Mengubah Data " << endl;
    cout << "4. Menampilkan Data " << endl
         << endl;
    cout << "5. Keluar " << endl;

    cout << "Pilih: " << endl;

    cin >> input_user;

    // if (input_user == 4)
    // {
    //   for (int i = 0; i <)
    //     ; i++)
    //   {
    //     cout << "==== Mahasiswa " + to_string(i + 1) + " ====" << endl;
    //     cout << "Nama: " << mhs[i].nama << endl;
    //     cout << "NRP: " << mhs[i].nrp << endl;
    //     cout << "Umur: " << mhs[i].umur << endl;
    //     cout << "Jenis Kelamin: " << (mhs[i].jenis_kelamin == 0 ? "Perempuan" : "Laki-Laki") << endl;
    //     cout << endl;
    //   }
    // }
  }

  return 0;
};
