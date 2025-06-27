package controller;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.DBConnection;
import model.Presensi;

public class PresensiController {

    Connection conn = DBConnection.getConnection();

    public void insertPresensi(Presensi presensi) {
        try {
            String sql = "INSERT INTO presensi (id_siswa, tanggal, status) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, presensi.getIdSiswa());
            stmt.setDate(2, Date.valueOf(presensi.getTanggal()));
            stmt.setString(3, presensi.getStatus());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Presensi berhasil disimpan.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan presensi: " + e.getMessage());
        }
    }

    public void tampilkanPresensi(DefaultTableModel model, String filterTanggal) {
        try {
            String sql = "SELECT presensi.id_presensi, siswa.nama, presensi.tanggal, presensi.status " +
                         "FROM presensi " +
                         "JOIN siswa ON presensi.id_siswa = siswa.id_siswa " +
                         "WHERE presensi.tanggal = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(filterTanggal));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_presensi"),
                    rs.getString("nama"),
                    rs.getDate("tanggal"),
                    rs.getString("status")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan presensi: " + e.getMessage());
        }
    }

    public boolean isPresensiExist(int idSiswa, String tanggal) {
        try {
            String sql = "SELECT COUNT(*) FROM presensi WHERE id_siswa = ? AND tanggal = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSiswa);
            stmt.setDate(2, Date.valueOf(tanggal));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengecek presensi: " + e.getMessage());
        }
        return false;
    }
}
