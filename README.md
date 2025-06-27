Sistem Presensi Sederhana (Java NetBeans & MySQL)

1. Deskripsi Singkat
  Aplikasi Sistem Presensi Sederhana ini dikembangkan menggunakan Java (NetBeans) dan MySQL. Tujuan utama aplikasi ini adalah untuk memudahkan proses pencatatan kehadiran siswa dan pengelolaan data di lingkungan sekolah secara digital dan terstruktur.

2. Struktur Project
  Project ini menggunakan konsep MVC (Model-View-Controller), sehingga memudahkan pengembangan dan maintenance aplikasi.
Struktur package pada source code antara lain:
    a. controller
      Berisi file kontroler untuk mengatur logika aplikasi seperti PresensiController.java dan SiswaController.java.
    b. model
      Berisi kelas-kelas untuk pengelolaan data dan koneksi database, misalnya: DBConnection.java, Presensi.java, Kelas.java, Siswa.java, dll.
    c. view
      Berisi tampilan antarmuka pengguna (form) seperti:
        - LoginForm
        - DashboardAdmin
        - DashboardGuru
        - PresensiForm
        - SiswaForm
        - KelasForm
        - UserForm
        - LaporanView

3. Fitur Utama
    a. Login Sistem:
      User dapat login sebagai admin atau guru untuk mengakses fitur sesuai hak akses.
    b. Dashboard:
      Tersedia dashboard khusus untuk admin maupun guru, menampilkan menu utama aplikasi.
    Manajemen Siswa & Kelas:
        - Admin dapat menambah, mengedit, menghapus data siswa dan kelas.
        - Fitur filter dan pencarian data.
    c. Presensi Siswa:
      Admin dan Guru dapat mencatat kehadiran siswa per kelas, serta mengupdate status (Hadir, Izin, Sakit, Alpa).
    d. Laporan Presensi:
      Laporan kehadiran siswa dapat ditampilkan dan diekspor.
    e. Manajemen User:
      Admin dapat mengelola data user (username, password, role admin/guru).

Tampilan Aplikasi
    a. Login: Form login user dengan username & password.
    b. Dashboard: Menu utama dengan navigasi ke fitur-fitur penting.
    c. Input Data Siswa & Kelas: Form untuk input/edit data siswa dan kelas, dengan tabel data.
    d. Presensi: Form pencatatan kehadiran per kelas & tanggal.
    e. Laporan: Laporan kehadiran siswa yang dapat difilter berdasarkan kelas & tanggal.
    f. Manajemen User: Input dan daftar user dengan pengaturan role.
