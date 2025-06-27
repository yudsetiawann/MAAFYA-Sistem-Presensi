package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/sistem_presensi";
                String user = "root";       // ganti jika pakai user lain
                String password = "";       // isi jika pakai password
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil");
            } catch (SQLException e) {
                System.err.println("Koneksi gagal: " + e.getMessage());
            }
        }
        return conn;
    }
}