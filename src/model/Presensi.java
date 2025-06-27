package model;

public class Presensi {
    private int idPresensi;
    private int idSiswa;
    private String tanggal; // Format: "yyyy-MM-dd"
    private String status;  // 'Hadir', 'Izin', 'Sakit', 'Alpa'

    // Constructor kosong
    public Presensi() {}

    // Constructor lengkap
    public Presensi(int idPresensi, int idSiswa, String tanggal, String status) {
        this.idPresensi = idPresensi;
        this.idSiswa = idSiswa;
        this.tanggal = tanggal;
        this.status = status;
    }

    // Getter dan Setter
    public int getIdPresensi() {
        return idPresensi;
    }

    public void setIdPresensi(int idPresensi) {
        this.idPresensi = idPresensi;
    }

    public int getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(int idSiswa) {
        this.idSiswa = idSiswa;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
