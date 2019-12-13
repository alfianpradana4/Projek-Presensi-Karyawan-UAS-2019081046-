/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_presensi_karyawan_2019081046;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *  Dibuat Oleh 
 * 
 *  Nama            :   Alfian Rifqi Pradana
 *  NIM             :   2019081046
 *  Prodi           :   Sistem Informasi - B
 *  Nama Project    :   Aplikasi Presensi Karyawan 
 *  Bagian Project  :   Bagian Presensi Karyawan
 * 
 */

public class Presensi extends javax.swing.JFrame {
    
        private String pattern = "dd MMMM yyyy, m:HH:ss";
        private SimpleDateFormat format;
        private Timer timer;
        private Date date;

        private Connection koneksi;
        
        

    /**
     * Creates new form Presensi
     */
    public Presensi() {
        
        initComponents();
        setLocationRelativeTo(this);
        KoneksiDatabase();
        waktu();
        AUTONOMORMASUK();
        AUTONOMORPULANG();
        TampilDataMasuk();
        TampilDataPulang();
        
    }
    
    private void TampilDataMasuk() {
        //membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Presensi Masuk");
        model.addColumn("NIK");
        model.addColumn("Nama Karyawan");
        model.addColumn("Jam Masuk");


        tbmasuk.setModel(model);

        //menampilkan data database kedalam tabel
        try {
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet data = stat.executeQuery("SELECT * FROM presensi_masuk");
            while (data.next()) {
                model.addRow(new Object[]{
                    data.getString("idmasuk"),
                    data.getString("nik"),
                    data.getString("nama"),
                    data.getString("scan_masuk"),


                });
                
                tbmasuk.setModel(model);
            }
        } catch (Exception e) {
            System.err.println("Terjadi Kesalahan :" + e);
        }
    }
    
        private void TampilDataPulang() {
        //membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Presensi Pulang");
        model.addColumn("NIK");
        model.addColumn("Nama Karyawan");
        model.addColumn("Jam Pulang");


        tbpulang.setModel(model);

        //menampilkan data database kedalam tabel
        try {
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet data = stat.executeQuery("SELECT * FROM presensi_pulang");
            while (data.next()) {
                model.addRow(new Object[]{
                    data.getString("idpulang"),
                    data.getString("nik"),
                    data.getString("nama"),
                    data.getString("scan_pulang"),


                });
                
                tbpulang.setModel(model);
            }
        } catch (Exception e) {
            System.err.println("Terjadi Kesalahan :" + e);
        }
    }
    
    
     private void KoneksiDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
        }
        try {
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/dbkaryawan2019081046", "root", "");
            System.out.println("Koneksi Database Berhasil");
        } catch (SQLException se) {
            System.out.println("Koneksi Database Gagal :" + se);
        } catch (Exception e) {
        }
    }
     
     public void waktu(){
         
         Date d = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         
         String dd = sdf.format(d);
         txjmasuk.setText(dd);
         txjpulang.setText(dd);

     }

     private void AUTONOMORMASUK() {
        try {
            //--> melakukan eksekusi query untuk mengambil data dari tabel

            java.sql.Statement stat = koneksi.createStatement();
            ResultSet data = stat.executeQuery("SELECT MAX(RIGHT(idmasuk,6)) AS NO FROM presensi_masuk");

            while (data.next()) {
                if (data.first() == false) {
                    txidmasuk.setText("MK-000001");
                } else {
                    data.last();
                    int auto_id = data.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 6 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    txidmasuk.setText("MK-" + no);
                }
            }
            data.close();
            stat.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(),
                    "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }
     
          private void AUTONOMORPULANG() {
        
              try {
            //--> melakukan eksekusi query untuk mengambil data dari tabel

            java.sql.Statement stat = koneksi.createStatement();
            ResultSet data = stat.executeQuery("SELECT MAX(RIGHT(idpulang,6)) AS NO FROM presensi_pulang");

            while (data.next()) {
                if (data.first() == false) {
                    txidpulang.setText("PG-000001");
                } else {
                    data.last();
                    int auto_id = data.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    //MENGATUR jumlah 0
                    for (int j = 0; j < 6 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    txidpulang.setText("PG-" + no);
                }
            }
            data.close();
            stat.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(),
                    "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txuserk = new javax.swing.JTextField();
        txpwk = new javax.swing.JPasswordField();
        btnMasuk2 = new javax.swing.JButton();
        btcancel2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        Jpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txidmasuk = new javax.swing.JTextField();
        txnikmasuk = new javax.swing.JTextField();
        txnmasuk = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txjmasuk = new javax.swing.JTextField();
        btmasuk = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbmasuk = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Jpanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txidpulang = new javax.swing.JTextField();
        txnikpulang = new javax.swing.JTextField();
        txnpulang = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txjpulang = new javax.swing.JTextField();
        btpulang = new javax.swing.JToggleButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbpulang = new javax.swing.JTable();

        Login.setMinimumSize(new java.awt.Dimension(530, 380));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Login Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24))); // NOI18N
        jPanel5.setToolTipText("Login");
        jPanel5.setName("LOGIN"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("Username");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("Password");

        btnMasuk2.setText("Masuk");
        btnMasuk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasuk2ActionPerformed(evt);
            }
        });

        btcancel2.setText("Cancel");
        btcancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcancel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btcancel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMasuk2))
                    .addComponent(txuserk, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txpwk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txuserk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txpwk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMasuk2)
                    .addComponent(btcancel2))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login.getContentPane());
        Login.getContentPane().setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        Jpanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("DATA MASUK KARYAWAN");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID Presensi Masuk");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NIK");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Karyawan");

        txidmasuk.setEnabled(false);

        txnikmasuk.setEnabled(false);

        txnmasuk.setEnabled(false);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jam Masuk");

        txjmasuk.setEnabled(false);
        txjmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txjmasukActionPerformed(evt);
            }
        });

        btmasuk.setText("CETAK JAM MASUK");
        btmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmasukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelLayout = new javax.swing.GroupLayout(Jpanel);
        Jpanel.setLayout(JpanelLayout);
        JpanelLayout.setHorizontalGroup(
            JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(JpanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txjmasuk)
                            .addComponent(txnmasuk)
                            .addComponent(txnikmasuk)
                            .addComponent(txidmasuk, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btmasuk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        JpanelLayout.setVerticalGroup(
            JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txidmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txnikmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txnmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txjmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btmasuk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbmasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Presensi Masuk", "NIK ", "Nama Karyawan", "Jam Masuk"
            }
        ));
        tbmasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbmasukMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbmasuk);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addComponent(Jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("PRESENSI MASUK", jPanel1);

        Jpanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("DATA PULANG KARYAWAN");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("ID Presensi Pulang");

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("NIK");

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama Karyawan");

        txidpulang.setEnabled(false);

        txnikpulang.setEnabled(false);

        txnpulang.setEnabled(false);

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jam Pulang");

        txjpulang.setEnabled(false);

        btpulang.setText("CETAK JAM PULANG");
        btpulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btpulangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Jpanel2Layout = new javax.swing.GroupLayout(Jpanel2);
        Jpanel2.setLayout(Jpanel2Layout);
        Jpanel2Layout.setHorizontalGroup(
            Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(Jpanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txjpulang, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(txnpulang)
                            .addComponent(txnikpulang)
                            .addComponent(txidpulang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btpulang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        Jpanel2Layout.setVerticalGroup(
            Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addGap(55, 55, 55)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txidpulang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txnikpulang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txnpulang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txjpulang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btpulang)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tbpulang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Presensi Pulang", "NIK ", "Nama Karyawan", "Jam Pulang"
            }
        ));
        tbpulang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpulangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbpulang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addComponent(Jpanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("PRESENSI PULANG", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbmasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbmasukMouseClicked

  
    }//GEN-LAST:event_tbmasukMouseClicked

    private void tbpulangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpulangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpulangMouseClicked

    private void btmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmasukActionPerformed

        // TODO add your handling code here:
        
        String idmasuk = txidmasuk.getText();
        String nik = txnikmasuk.getText();
        String nama = txnmasuk.getText();
        String scanmasuk = txjmasuk.getText();
        
         if (txidmasuk.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Mohon Masukan Data Untuk Di Input", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                try (java.sql.Statement stat = koneksi.createStatement()) {
                    ResultSet data = stat.executeQuery("SELECT * FROM presensi_masuk WHERE idmasuk ='" + idmasuk + "'");
                    if (data.next()) {
                        JOptionPane.showMessageDialog(null, "ID Sudah Ada", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
                        txidmasuk.requestFocus();
                    } else {
                        String sql = "INSERT INTO presensi_masuk VALUES('" + idmasuk + "'"
                                + ",'" + nik + "'"
                                + ",'" + nama + "'"
                                + ",'" + scanmasuk + "')";
                        stat.executeUpdate(sql);

                        JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan", "SUKSES", JOptionPane.INFORMATION_MESSAGE);
                        TampilDataMasuk();

                    }
                }
            } catch (Exception exc) {
                System.err.println("Terjadi Kesalahan :" + exc);
            }
        }
        
        btmasuk.setEnabled(false);

                
    }//GEN-LAST:event_btmasukActionPerformed

    
    
    private void txjmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txjmasukActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txjmasukActionPerformed

    private void btnMasuk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasuk2ActionPerformed
        // TODO add your handling code here:

        try {
            String database="jdbc:mysql://localhost/dbkaryawan2019081046";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Statement stat = koneksi.createStatement();

            koneksi=DriverManager.getConnection(database,user,pass);

            String tNama = txuserk.getText();
            String tPW = txpwk.getText();

            ResultSet data = stat.executeQuery("SELECT * FROM karyawan WHERE username='" + tNama + "'AND password='" + tPW + "'");

            Login.setVisible(false);
            this.tbmasuk.setVisible(true);
            this.tbpulang.setVisible(true);
            this.btmasuk.setVisible(true);
            this.btpulang.setVisible(true);
            
            while(data.next()){
            Object[] ob = new Object[2];
            ob[0]=  data.getString(1);
            ob[1]= data.getString(2);
            
            txnikmasuk.setText((String) ob[0]);
            txnmasuk.setText((String) ob[1]);
            
            txnikpulang.setText((String) ob[0]);
            txnpulang.setText((String) ob[1]);
        }
        data.close(); stat.close();
            
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null, "koneksi gagal" +e.getMessage());
        } //catch
    }//GEN-LAST:event_btnMasuk2ActionPerformed

    private void btcancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcancel2ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin Ingin Keluar?", "Terima Kasih Atas Perhatiannya", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btcancel2ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        
            Login.setLocationRelativeTo(this);
            Login.setVisible(true);
            this.tbmasuk.setVisible(false);
            this.tbpulang.setVisible(false);
            this.btmasuk.setVisible(false);
            this.btpulang.setVisible(false);
            
    }//GEN-LAST:event_formComponentShown

    private void btpulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btpulangActionPerformed
        // TODO add your handling code here:
        
                
        String idpulang = txidpulang.getText();
        String nik = txnikpulang.getText();
        String nama = txnpulang.getText();
        String scanpulang = txjpulang.getText();
        
         if (txidmasuk.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Mohon Masukan Data Untuk Di Input", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                try (java.sql.Statement stat = koneksi.createStatement()) {
                    ResultSet data = stat.executeQuery("SELECT * FROM presensi_pulang WHERE idpulang ='" + idpulang + "'");
                    if (data.next()) {
                        JOptionPane.showMessageDialog(null, "ID Sudah Ada", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
                        txidmasuk.requestFocus();
                    } else {
                        String sql = "INSERT INTO presensi_pulang VALUES('" + idpulang + "'"
                                + ",'" + nik + "'"
                                + ",'" + nama + "'"
                                + ",'" + scanpulang + "')";
                        stat.executeUpdate(sql);

                        JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan", "SUKSES", JOptionPane.INFORMATION_MESSAGE);
                        
                        TampilDataPulang();

                    }
                }
            } catch (Exception exc) {
                System.err.println("Terjadi Kesalahan :" + exc);
            }
        }
        
        
        btpulang.setEnabled(false);

    }//GEN-LAST:event_btpulangActionPerformed

    
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
            java.util.logging.Logger.getLogger(Presensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Presensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Presensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Presensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Presensi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel;
    private javax.swing.JPanel Jpanel2;
    private javax.swing.JDialog Login;
    private javax.swing.JButton btcancel2;
    private javax.swing.JToggleButton btmasuk;
    private javax.swing.JButton btnMasuk2;
    private javax.swing.JToggleButton btpulang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbmasuk;
    private javax.swing.JTable tbpulang;
    private javax.swing.JTextField txidmasuk;
    private javax.swing.JTextField txidpulang;
    private javax.swing.JTextField txjmasuk;
    private javax.swing.JTextField txjpulang;
    private javax.swing.JTextField txnikmasuk;
    private javax.swing.JTextField txnikpulang;
    private javax.swing.JTextField txnmasuk;
    private javax.swing.JTextField txnpulang;
    private javax.swing.JPasswordField txpwk;
    private javax.swing.JTextField txuserk;
    // End of variables declaration//GEN-END:variables
}
