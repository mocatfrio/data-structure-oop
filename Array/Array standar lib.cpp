#include <iostream>
#include <Array>
using namespace std;

int main()
{
	//membuat array dengan standard library
	// array < tipe data, jumlah array > nama array
	
	array < int, 5 > nilai;
	
	for(int i=0; i<=4; i++)
	{
		nilai[i]=i;
		cout << "nilai [" << i << "] = " << nilai[i];
		cout << " Address : " << &nilai[i] << endl;
	}
	//cin.get();
	cout << endl;
	//ukuran array
	cout << nilai.size() << endl;
	//address awal dari array
	cout << "Address awal : " << nilai.begin() << endl;
	//address akhir dari array
	cout << "Address akhir : " << nilai.end()-1 << endl;
	return 0;
}
