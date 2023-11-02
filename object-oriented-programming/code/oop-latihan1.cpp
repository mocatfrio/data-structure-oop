#include <iostream>
using namespace std;

class Mahasiswa {
  public:
  string Name;

  void printName() {
    cout << "Nama Mahasiswa: " << Name;
  }
};

int main() {
  Mahasiswa mhs1;

  mhs1.Name = "Rahmat Budi";

  mhs1.printName();
  return 0;
}