Cara Instalasi Sistem Presensi Sekolah

Kebutuhan Sistem
- Java JDK (disarankan JDK 8 ke atas)
- NetBeans IDE (disarankan versi 8 atau lebih baru)
- MySQL Server

Langkah-langkah Instalasi
- Clone Repository
- Download atau clone repository ini ke komputer Anda:
     git clone https://github.com/yudsetiawann/MAAFYA-Sistem-Presensi.git
- Atau, download sebagai ZIP lalu extract.

Import Project ke NetBeans
- Buka NetBeans IDE.
- Pilih menu File > Open Project.
- Arahkan ke folder hasil clone/extract, pilih folder project ini, lalu klik Open Project.

Konfigurasi Database
- Pastikan MySQL sudah berjalan.
- Buka aplikasi seperti phpMyAdmin atau MySQL Workbench.
- Import file database sistem_presensi.sql ke MySQL:
     Buat database baru (misal: presensi).
     Import file sistem_presensi.sql ke database tersebut.

Atur Koneksi Database pada Project
- Buka file DBConnection.java (di folder src/model/).
- Sesuaikan konfigurasi username, password, dan nama database sesuai setting MySQL di komputer Anda:
     String url = "jdbc:mysql://localhost:3306/presensi";
     String user = "root";
     String password = "";
- Simpan perubahan.
- Build & Jalankan Aplikasi
     Klik kanan pada project di NetBeans, pilih Clean and Build.
     Klik kanan lagi, pilih Run untuk menjalankan aplikasi.
     Login menggunakan akun yang tersedia (cek data user di database).

Catatan
- Jika terdapat error koneksi database, cek ulang konfigurasi pada DBConnection.java.
- Pastikan semua dependency sudah tersedia di Library NetBeans.
