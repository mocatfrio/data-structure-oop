#include <iostream>
#include <map>

using namespace std;

map<string, map<string, string> > dataAkun;

// Hash Functiion
int hashFunction(string password)
{
  // utak atik disini
  return tolower(password[0]) - 97;
}

void registerAkun()
{
  string username, password, fullname;
  cout << "Masukkan Username: " << endl;
  cin >> username;
  cout << "Masukkan Password: " << endl;
  cin >> password;
  cout << "Masukkan Nama Lengkap: " << endl;
  cin >> fullname;

  string hash_key = to_string(hashFunction(password));

  map<string, string> dataDetail;
  dataDetail["username"] = username;
  dataDetail["fullname"] = fullname;

  // masukkan ke map
  dataAkun[hash_key] = dataDetail;
}

void printAkun()
{
  cout << endl;
  for (auto i = dataAkun.begin(); i != dataAkun.end(); i++)
  {
    cout << "Key: " << i->first << "\t"
         << " Username: " << i->second["username"] << " Fullname: " << i->second["fullname"] << endl;
  }
}

// Menu
void menu()
{
  while (true)
  {
    int input_user;

    cout << endl;
    cout << "==== MENU ====" << endl;
    cout << "1. Register" << endl;
    // cout << "2. Login" << endl;
    cout << "2. Exit" << endl;

    cout << "Pilih Menu: " << endl;
    cin >> input_user;

    if (input_user == 1)
    {
      registerAkun();
    }
    else if (input_user == 2)
    {
      printAkun();
      break;
    }
  }
}

int main()
{
  menu();
}