#include <iostream>
using namespace std;

class Mahasiswa {
  private:
  string Name;

  public:
  void printName() {
    cout << "Nama Mahasiswa: " << Name;
  }
  void setName(string name) {
    Name = name;
  }
};

int main() {
  Mahasiswa mhs1;

  mhs1.setName("Rahmat Budi");
  mhs1.printName();
  return 0;
}