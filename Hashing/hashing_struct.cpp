#include <iostream>
using namespace std;

// Sebuah sistem login sederhana

const int SIZE_ARR = 26;

// Hash Tab
struct HashTable
{
  string username, password;
} dataAkun[SIZE_ARR];

// Hash
int hashFunction(string password)
{
  // Tugas: utak atik bagian sini!
  return tolower(password[0]) - 97;
}

// Print all hash table
void printAll()
{
  for (int i = 0; i < SIZE_ARR; i++)
  {
    cout << i << " [ " << dataAkun[i].username << " - " << dataAkun[i].password << " ]" << endl;
  }
}

void menu()
{
  while (true)
  {
    int input_user;

    cout << endl;
    cout << "MENU" << endl;
    cout << "1. Register" << endl;
    cout << "2. Login" << endl;
    cout << "3. Exit" << endl
         << endl;

    cout << "Pilih: " << endl;
    cin >> input_user;
    cout << endl;

    if (input_user == 1)
    {
      string username, password;

      cout << "Masukkan Username: " << endl;
      cin >> username;
      cout << "Masukkan Password: " << endl;
      cin >> password;

      int index = hashFunction(password);

      if (dataAkun[index].username.empty() && dataAkun[index].password.empty())
      {
        dataAkun[index].username = username;
        dataAkun[index].password = password;
      }
      else
      {
        cout << "Akun sudah ada!" << endl;
      }
    }
    else if (input_user == 2)
    {
      // Menu Login
      cout << "Menu Login" << endl;
    }
    else if (input_user == 3)
    {
      printAll();

      break;
    }
    else
    {
      cout << "Invalid!" << endl;
    }
  }
}

int main()
{
  menu();
}
