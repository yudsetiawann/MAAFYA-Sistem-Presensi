package view;

import java.awt.Component;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import model.DBConnection;
import model.Kelas;
import model.Session;

public class PresensiForm extends javax.swing.JFrame {

    DefaultTableModel model;
    ArrayList<Kelas> daftarKelas = new ArrayList<>();

    public PresensiForm() {
        initComponents();
//        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        loadKelasToComboBox();
    }

    private void loadKelasToComboBox() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM kelas";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            daftarKelas.clear();
            jComboBoxKelas.removeAllItems();

            while (rs.next()) {
                Kelas k = new Kelas(rs.getInt("id_kelas"), rs.getString("nama_kelas"));
                daftarKelas.add(k);
                jComboBoxKelas.addItem(k.getNamaKelas());
            }

            jComboBoxKelas.setSelectedIndex(-1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load kelas: " + e.getMessage());
        }
    }

    private void loadSiswaByKelas(int idKelas) {
        model = new DefaultTableModel(new String[]{"No", "ID Siswa", "Nama", "NISN", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        jTablePresensi.setModel(model);

        // Sembunyikan kolom ID Siswa
        jTablePresensi.getColumnModel().getColumn(1).setMinWidth(0);
        jTablePresensi.getColumnModel().getColumn(1).setMaxWidth(0);
        jTablePresensi.getColumnModel().getColumn(1).setWidth(0);

        java.util.Date selectedDate = jDateChooser1.getDate();
        if (selectedDate == null) {
            // tanggal belum dipilih, kosongkan tabel saja
            return;
        }
        java.sql.Date today = new java.sql.Date(selectedDate.getTime());

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT s.id_siswa, s.nama, s.nisn, p.status " +
                         "FROM siswa s LEFT JOIN presensi p ON s.id_siswa = p.id_siswa AND p.tanggal = ? " +
                         "WHERE s.id_kelas = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, today);
            stmt.setInt(2, idKelas);
            ResultSet rs = stmt.executeQuery();

            int no = 1;
            while (rs.next()) {
                String status = rs.getString("status");
                if (status == null) status = "Hadir"; // default jika belum absen
                model.addRow(new Object[]{
                    no++,
                    rs.getInt("id_siswa"),
                    rs.getString("nama"),
                    rs.getString("nisn"),
                    status
                });
            }

            // Pasang ComboBox editor dan renderer
            String[] statusList = {"Hadir", "Izin", "Sakit", "Alpa"};
            JComboBox<String> comboBox = new JComboBox<>(statusList);
            jTablePresensi.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboBox));
            jTablePresensi.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
                String[] statusList = {"Hadir", "Izin", "Sakit", "Alpa"};
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JComboBox<String> comboBox = new JComboBox<>(statusList);
                    comboBox.setSelectedItem(value);
                    comboBox.setEnabled(false);
                    if (isSelected) {
                        comboBox.setBackground(table.getSelectionBackground());
                    } else {
                        comboBox.setBackground(table.getBackground());
                    }
                    return comboBox;
                }
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load siswa: " + e.getMessage());
        }
    }


    
    private boolean isPresensiExist(Connection conn, int idSiswa, java.sql.Date tanggal) {
        try {
            String sql = "SELECT COUNT(*) FROM presensi WHERE id_siswa = ? AND tanggal = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSiswa);
            stmt.setDate(2, tanggal);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal cek presensi: " + e.getMessage());
        }
        return false;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePresensi = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxKelas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButtonReload = new javax.swing.JButton();
        jButtonSimpan = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButtonLaporan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));

        jTablePresensi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTablePresensi);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Presensi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBoxKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxKelas.setSelectedIndex(-1);

        jLabel1.setText("Pilih Kelas:");

        jButtonReload.setText("Reload");
        jButtonReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReloadActionPerformed(evt);
            }
        });

        jButtonSimpan.setBackground(new java.awt.Color(51, 255, 51));
        jButtonSimpan.setText("Simpan");
        jButtonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimpanActionPerformed(evt);
            }
        });

        jLabel3.setText("Pilih Tanggal:");

        jButtonLaporan.setText("Home");
        jButtonLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLaporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonReload, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jComboBoxKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jButtonLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 194, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonReload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonLaporan))
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReloadActionPerformed
        // TODO add your handling code here:
        int index = jComboBoxKelas.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kelas terlebih dahulu.");
            return;
        }
        int idKelas = daftarKelas.get(index).getIdKelas();
        loadSiswaByKelas(idKelas);
    }//GEN-LAST:event_jButtonReloadActionPerformed

    private void jButtonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimpanActionPerformed
        // TODO add your handling code here:
        int rowCount = jTablePresensi.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang disimpan.");
            return;
        }

        int index = jComboBoxKelas.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kelas terlebih dahulu.");
            return;
        }

        java.util.Date selectedDate = jDateChooser1.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Pilih tanggal terlebih dahulu.");
            return;
        }
        java.sql.Date today = new java.sql.Date(selectedDate.getTime());


        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO presensi (id_siswa, tanggal, status) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (int i = 0; i < rowCount; i++) {
                int idSiswa = Integer.parseInt(jTablePresensi.getValueAt(i, 1).toString());
                String status = jTablePresensi.getValueAt(i, 4).toString();

                if (!isPresensiExist(conn, idSiswa, today)) {
                    stmt.setInt(1, idSiswa);
                    stmt.setDate(2, today);
                    stmt.setString(3, status);
                    stmt.addBatch(); // INSERT baru
                } else {
                    // lakukan UPDATE jika sudah ada
                    String updateSql = "UPDATE presensi SET status = ? WHERE id_siswa = ? AND tanggal = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setString(1, status);
                    updateStmt.setInt(2, idSiswa);
                    updateStmt.setDate(3, today);
                    updateStmt.executeUpdate();
                }
            }


            stmt.executeBatch(); // jalankan semua insert sekaligus
            JOptionPane.showMessageDialog(this, "Presensi berhasil disimpan.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan presensi: " + e.getMessage());
        }
    }//GEN-LAST:event_jButtonSimpanActionPerformed

    private void jButtonLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLaporanActionPerformed
        // TODO add your handling code here:
        if ("admin".equalsIgnoreCase(Session.role)) {
            new DashboardAdmin().setVisible(true);
        } else if ("guru".equalsIgnoreCase(Session.role)) {
            new DashboardGuru().setVisible(true);
        }

        this.dispose(); // Tutup form sekarang
    }//GEN-LAST:event_jButtonLaporanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PresensiForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PresensiForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PresensiForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PresensiForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PresensiForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLaporan;
    private javax.swing.JButton jButtonReload;
    private javax.swing.JButton jButtonSimpan;
    private javax.swing.JComboBox jComboBoxKelas;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePresensi;
    // End of variables declaration//GEN-END:variables
}
