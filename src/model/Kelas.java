package model;

public class Kelas {
    private int idKelas;
    private String namaKelas;

    public Kelas() {}

    public Kelas(int idKelas, String namaKelas) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
    }

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    @Override
    public String toString() {
        return namaKelas; // agar bisa ditampilkan langsung di JComboBox
    }
}
