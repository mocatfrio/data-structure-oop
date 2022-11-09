#include <iostream>
using namespace std;

class Mahasiswa {
  // Attributes 
  private:
    string Name;
    string NRP;

  // Methods 
  public:
    void printName() {
      cout << "Nama Mahasiswa: " << Name << endl;
      cout << "NRP: " << Name << endl;
      cout << endl;
    }
    void setName(string name) {
      Name = name;
    }

    // Constructor with parameter
    Mahasiswa(string name, string nrp) {
      Name = name;
      NRP = nrp;
    }
};

int main() {
  Mahasiswa mhs1("Rahmat Budi", "10151522");
  mhs1.printName();

  mhs1.setName("Akbar");
  mhs1.printName();
  return 0;
}