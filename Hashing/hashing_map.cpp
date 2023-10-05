#include <iostream>
#include <map>
using namespace std;

// Sebuah sistem login sederhana

map<string, string> dataAkun;

// Hash
int hashFunction(string password)
{
  // Tugas: utak atik bagian sini!
  return tolower(password[0]) - 97;
}

void printAll()
{
  for (auto i = dataAkun.begin(); i != dataAkun.end(); i++)
  {
    cout << "Key: " << i->first << " \t\t\t"
         << "Value: " << i->second << endl;
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
    // cout << "2. Login" << endl;
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

      string index = to_string(hashFunction(password));
      dataAkun[index] = username;

      // if (dataAkun[index].username.empty() && dataAkun[index].password.empty())
      // {
      //   dataAkun[index].username = username;
      //   dataAkun[index].password = password;
      // }
      // else
      // {
      //   cout << "Akun sudah ada!" << endl;
      // }
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
