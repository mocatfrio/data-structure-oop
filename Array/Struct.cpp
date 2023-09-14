#include <iostream>
#include <string>

using namespace std;

struct Pegawai
{
	string IDPeg;
	string NamaPeg;
	string NoHPPeg;
	string AlamatPeg;
};

int main()
{
	Pegawai Peg1;
	Pegawai Peg2;
	Pegawai Peg3;
		
	cout << "==========Pegawai 1==========" << endl;	
		
	Peg1.IDPeg = "IDPeg001";
	Peg1.NamaPeg = "Jono";
	Peg1.NoHPPeg = "0812345678";
	Peg1.AlamatPeg = "Perumdos ITS Blok U-7";
	
	cout << Peg1.IDPeg << endl;
	cout << Peg1.NamaPeg << endl;
	cout << Peg1.NoHPPeg << endl;
	cout << Peg1.AlamatPeg << endl;
	
	cout << "==========Pegawai 2==========" << endl;
	
	Peg2.IDPeg = "IDPeg002";
	Peg2.NamaPeg = "Joni";
	Peg2.NoHPPeg = "0812345679";
	Peg2.AlamatPeg = "Perumdos ITS Blok J-1";
	
	cout << Peg2.IDPeg << endl;
	cout << Peg2.NamaPeg << endl;
	cout << Peg2.NoHPPeg << endl;
	cout << Peg2.AlamatPeg << endl;
	
	cout << "==========Pegawai 3==========" << endl;
	
	Peg3.IDPeg = "IDPeg003";
	Peg3.NamaPeg = "Juna";
	Peg3.NoHPPeg = "0812324342";
	Peg3.AlamatPeg = "Perumdos ITS Blok A-10";
	
	cout << Peg3.IDPeg << endl;
	cout << Peg3.NamaPeg << endl;
	cout << Peg3.NoHPPeg << endl;
	cout << Peg3.AlamatPeg << endl;
	
	
}
