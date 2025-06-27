
package controller;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.DBConnection;
import model.Siswa;

public class SiswaController {

    Connection conn = DBConnection.getConnection();

    public void insertSiswa(Siswa siswa) {
        try {
            String sql = "INSERT INTO siswa (nama, nisn, id_kelas) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, siswa.getNama());
            stmt.setString(2, siswa.getNisn());
            stmt.setInt(3, siswa.getIdKelas());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan: " + e.getMessage());
        }
    }

    public void updateSiswa(Siswa siswa) {
        try {
            String sql = "UPDATE siswa SET nama = ?, nisn = ?, id_kelas = ? WHERE id_siswa = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, siswa.getNama());
            stmt.setString(2, siswa.getNisn());
            stmt.setInt(3, siswa.getIdKelas());
            stmt.setInt(4, siswa.getIdSiswa());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal memperbarui: " + e.getMessage());
        }
    }

    public void tampilkanData(DefaultTableModel model) {
        try {
            String sql = "SELECT siswa.id_siswa, nama, nisn, kelas.nama_kelas FROM siswa LEFT JOIN kelas ON siswa.id_kelas = kelas.id_kelas";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_siswa"),
                    rs.getString("nama"),
                    rs.getString("nisn"),
                    rs.getString("nama_kelas")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
        }
    }

    public void deleteSiswa(int idSiswa) {
        try {
            String sql = "DELETE FROM siswa WHERE id_siswa = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSiswa);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus: " + e.getMessage());
        }
    }
}
