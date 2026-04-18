class Mahasiswa {
    private String nama;
    private double[] nilai;
    private double nilaiAkhir;

    public Mahasiswa(String nama) {
        this.nama = nama;
        this.nilai = new double[5];
    }

    public void setNilai(double tugas, double quiz, double uts, double uas, double praktikum) {
        nilai[0] = tugas;
        nilai[1] = quiz;
        nilai[2] = uts;
        nilai[3] = uas;
        nilai[4] = praktikum;
    }

    public void hitungNilaiAkhir() {
        nilaiAkhir = (nilai[0] * 0.10) + (nilai[1] * 0.10) +
                     (nilai[2] * 0.20) + (nilai[3] * 0.20) + (nilai[4] * 0.40);
    }

    public void tampilkan() {
        System.out.println(nama + " => " + nilaiAkhir);
    }

    public String getNama() { return nama; }
    public double getNilaiAkhir() { return nilaiAkhir; }
}

public class ProseduralVsOOP {

    public static double hitungNilaiProsedural(double[] nilai) {
        return (nilai[0] * 0.10) + (nilai[1] * 0.10) +
               (nilai[2] * 0.20) + (nilai[3] * 0.20) + (nilai[4] * 0.40);
    }

    public static void main(String[] args) {
        System.out.println("=== PENDEKATAN PROSEDURAL ===");
        String[] namaMhs = {"Rara", "Budi", "Eka"};
        double[][] nilaiMhs = {
            {80, 85, 78, 82, 88},
            {75, 70, 72, 78, 80},
            {90, 92, 88, 85, 95}
        };

        for (int i = 0; i < namaMhs.length; i++) {
            double nilaiAkhir = hitungNilaiProsedural(nilaiMhs[i]);
            System.out.println(namaMhs[i] + " => " + nilaiAkhir);
        }

        System.out.println("\n=== PENDEKATAN OOP ===");
        Mahasiswa[] mahasiswas = {
            new Mahasiswa("Rara"),
            new Mahasiswa("Budi"),
            new Mahasiswa("Eka")
        };

        mahasiswas[0].setNilai(80, 85, 78, 82, 88);
        mahasiswas[1].setNilai(75, 70, 72, 78, 80);
        mahasiswas[2].setNilai(90, 92, 88, 85, 95);

        for (Mahasiswa mhs : mahasiswas) {
            mhs.hitungNilaiAkhir();
            mhs.tampilkan();
        }
    }
}
