#include <iostream>
using namespace std;

int main()
{
	int nilai[5]={0,1,2,3,4};
	/*int nilai [5];
	nilai [0]= 0;
	nilai [1]= 1;
	nilai [2]= 2;
	nilai [3]= 3;
	nilai [4]= 4;
*/
	cout << &nilai[0] << " nilainya adalah " << nilai[0] << endl;
	cout << &nilai[1] << " nilainya adalah " << nilai[1] << endl;	
	cout << &nilai[2] << " nilainya adalah " << nilai[2] << endl;
	cout << &nilai[3] << " nilainya adalah " << nilai[3] << endl;
	cout << &nilai[4] << " nilainya adalah " << nilai[4] << endl;
	
	int *ptr = nilai;
	cout << " nilai *ptr adalah " << *ptr << endl;
	
	*(ptr+3)=6;
	cout << &nilai[0] << " nilainya adalah " << nilai[0] << endl;
	cout << &nilai[1] << " nilainya adalah " << nilai[1] << endl;	
	cout << &nilai[2] << " nilainya adalah " << nilai[2] << endl;
	cout << &nilai[3] << " nilainya adalah " << nilai[3] << endl;
	cout << &nilai[4] << " nilainya adalah " << nilai[4] << endl;
	
	cout << "Jumlah Array : " << sizeof(nilai)/sizeof(int);
	
	
}
