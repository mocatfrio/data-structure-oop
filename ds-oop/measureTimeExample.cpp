#include <iostream>
#include <chrono>

// Fungsi contoh untuk diukur performanya
void exampleFunction()
{
  for (int i = 0; i < 1000000; ++i)
  {
    // Operasi sederhana
    int x = i * i;
  }
}

int main()
{
  // Mencatat waktu mulai
  auto start = std::chrono::high_resolution_clock::now();

  // Panggil fungsi yang ingin diukur
  exampleFunction();

  // Mencatat waktu selesai
  auto end = std::chrono::high_resolution_clock::now();

  // Hitung durasi eksekusi
  std::chrono::duration<double> duration = end - start;

  // Tampilkan hasil
  std::cout << "Waktu eksekusi: " << duration.count() << " detik" << std::endl;

  return 0;
}