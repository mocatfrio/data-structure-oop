class Mahasiswa {

  // Attribute dibuat private (enkapsulasi)
  private String nama;

  // Constructor untuk mengisi nama saat objek dibuat
  public Mahasiswa(String nama) {
    this.nama = nama;
  }

  // Method untuk menampilkan nama
  public void printname() {
    System.out.println("Nama Mahasiswa: " + nama);
  }

  // Getter method
  public String getNama() {
    return nama;
  }
}

public class MainModified {
  public static void main(String[] args) {

    // Membuat objek
    Mahasiswa mhs1 = new Mahasiswa("Rahmat Budi");
    Mahasiswa mhs2 = new Mahasiswa("Muhammad Ali");

    // Menampilkan menggunakan printname()
    mhs1.printname();
    mhs2.printname();

    System.out.println("----- Tanpa printname() -----");

    // Menampilkan tanpa printname(), menggunakan getter
    System.out.println("Nama Mahasiswa: " + mhs1.getNama());
    System.out.println("Nama Mahasiswa: " + mhs2.getNama());
  }
}