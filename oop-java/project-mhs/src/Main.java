class Mahasiswa {
    // Attribute
    String mahasiswa_name;

    // Method
    void printname() {
        System.out.println("Nama Mahasiswa: " + mahasiswa_name);
    }
}

public class Main {
    public static void main(String[] args) {
        // Instansiasi objek
        Mahasiswa mhs1 = new Mahasiswa();

        // Mengisi nama mahasiswa
        mhs1.mahasiswa_name = "Rahmat Budi";

        // Menampilkan nama mahasiswa
        mhs1.printname();
    }
}