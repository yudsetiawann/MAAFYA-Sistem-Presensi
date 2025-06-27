package model;

public class Siswa {
    private int idSiswa;
    private String nama;
    private String nisn;
    private int idKelas;

    public Siswa() {}

    public Siswa(int idSiswa, String nama, String nisn, int idKelas) {
        this.idSiswa = idSiswa;
        this.nama = nama;
        this.nisn = nisn;
        this.idKelas = idKelas;
    }

    public int getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(int idSiswa) {
        this.idSiswa = idSiswa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        this.idKelas = idKelas;
    }
}
