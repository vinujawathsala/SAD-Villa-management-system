/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FrontOffice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import model.MySQL;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author TUF_Heat_
 */
public class Checkin extends JFrame {

    HashMap<String, String> pkgMap = new HashMap<>();

    private FrontOffice frontOffice;

    /**
     * Creates new form Checkin
     */
    public Checkin(FrontOffice frontOffice, boolean modal, boolean status) {
        this.frontOffice = frontOffice;
        initComponents();
        datePicker1.setEditor(jFormattedTextField1);
        datePicker2.setEditor(jFormattedTextField3);
        timePicker1.setEditor(jFormattedTextField2);
        timePicker1.setOrientation(SwingConstants.HORIZONTAL);
        timePicker2.setEditor(jFormattedTextField4);
        timePicker2.setOrientation(SwingConstants.HORIZONTAL);
        jButton1.setVisible(status);
        jButton2.setVisible(status);
        jButton7.setVisible(!status);
        if (status) {
            load();
        }
        loadPkg();
        loadPayMethod();
        loadRooms();
    }
    private void load() {
        this.frontOffice.getMap().forEach((key, value) -> {
            if (key.equals("Name")) {
                jTextField1.setText(String.valueOf(value));
            } else if (key.equals("Arrival Date/Time")) {
                String[] strings = String.valueOf(value).split(" ");
                datePicker1.setSelectedDate(LocalDate.parse(strings[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                timePicker1.setSelectedTime(LocalTime.parse(strings[1], DateTimeFormatter.ofPattern("HH:mm")));
            } else if (key.equals("Depature Date/Time")) {
                String[] strings = String.valueOf(value).split(" ");
                datePicker2.setSelectedDate(LocalDate.parse(strings[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                timePicker2.setSelectedTime(LocalTime.parse(strings[1], DateTimeFormatter.ofPattern("HH:mm")));
            } else if (key.equals("Nights")) {
                jTextField2.setText(String.valueOf(value));
            } else if (key.equals("Packs")) {
                jTextField3.setText(String.valueOf(value));
            } else if (key.equals("Room No")) {
                jComboBox2.setSelectedItem(value);
            } else if (key.equals("Package")) {
                jComboBox3.setSelectedItem(value);
            } else if (key.equals("Special Request")) {
                jTextField5.setText(value.toString());
            } else if (key.equals("Price")) {
                jTextField11.setText(String.valueOf(value));
            } else if (key.equals("Payment Method")) {
                jComboBox1.setSelectedItem(value);
            } else if (key.equals("Mobile")) {
                jTextField4.setText(String.valueOf(value));
            }
        });
    }

    private void loadPkg() {
        try {
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            ResultSet pkg = MySQL.executeSearch("SELECT * FROM `package`");

            while (pkg.next()) {
                vector.add(pkg.getString("name"));
                pkgMap.put(pkg.getString("name"), pkg.getString("id"));
            }
            jComboBox3.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception ex) {
            Logger.getLogger(Checkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadPayMethod() {
        try {
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            ResultSet pkg = MySQL.executeSearch("SELECT * FROM `payment_method`");

            while (pkg.next()) {
                vector.add(pkg.getString("name"));
                
            }
            jComboBox1.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception ex) {
            Logger.getLogger(Checkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadRooms() {
        try {
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            ResultSet pkg = MySQL.executeSearch("SELECT * FROM `rooms`");

            while (pkg.next()) {
                vector.add(pkg.getString("room_no"));
                
            }
            jComboBox2.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception ex) {
            Logger.getLogger(Checkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verify() {
        if (jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jFormattedTextField1.getText().isEmpty() | jFormattedTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Arrival Date/Time", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jFormattedTextField3.getText().isEmpty() | jFormattedTextField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Depature Date/Time", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Nights", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Packs", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Enter a Room No", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox3.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Enter a Package", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTextField5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Special Request", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTextField11.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Enter a Payment Method", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTextField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
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

        datePicker1 = new raven.datetime.component.date.DatePicker();
        datePicker2 = new raven.datetime.component.date.DatePicker();
        timePicker1 = new raven.datetime.component.time.TimePicker();
        timePicker2 = new raven.datetime.component.time.TimePicker();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 50));
        jPanel1.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 1, 24)); // NOI18N
        jLabel1.setText("CheckIn");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/close.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 584, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jLabel2.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel3.setText("Arrival Date/Time");

        jLabel4.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel4.setText("Depature Date/Time");

        jFormattedTextField3.setPreferredSize(new java.awt.Dimension(94, 22));

        jLabel5.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel5.setText("Nights");

        jLabel6.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel6.setText("Packs");

        jLabel7.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel7.setText("Room No");

        jLabel9.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel9.setText("Package");

        jLabel10.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel10.setText("Speacial Requests");

        jLabel14.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel14.setText("Price");

        jLabel15.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel15.setText("Payment Method");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setText(" ");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Centaur", 0, 14)); // NOI18N
        jLabel8.setText("Mobile");

        jButton7.setFont(new java.awt.Font("Microsoft Uighur", 0, 24)); // NOI18N
        jButton7.setText("Add Records");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Microsoft Uighur", 0, 24)); // NOI18N
        jButton1.setText("Update Record");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Microsoft Uighur", 0, 24)); // NOI18N
        jButton2.setText("Delete Record");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jFormattedTextField1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jTextField3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11)
                    .addComponent(jComboBox3, 0, 200, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jTextField5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.frontOffice.setMap(new HashMap());
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        verify();
        String name = jTextField1.getText();
        String pkg = jComboBox3.getSelectedItem().toString();
        String a_date = LocalDate.parse(jFormattedTextField1.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String a_time = jFormattedTextField2.getText();
        String d_date = LocalDate.parse(jFormattedTextField3.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String d_time = jFormattedTextField4.getText();
        String special = jTextField5.getText();
        String price = jTextField11.getText();
        String nights = jTextField2.getText();
        String packs = jTextField3.getText();
        String pay_method = jComboBox1.getSelectedItem().toString();
        String room = jComboBox2.getSelectedItem().toString();
        String mobile = jTextField4.getText();

        try {
            ResultSet room_id = MySQL.executeSearch("SELECT `room_id` FROM `rooms` WHERE `room_no` = '" + room + "'");
            room_id.next();
            ResultSet pkg_id = MySQL.executeSearch("SELECT `id` FROM `package` WHERE `name` = '" + pkg + "'");
            pkg_id.next();
            ResultSet method = MySQL.executeSearch("SELECT `id` FROM `payment_method` WHERE `name` = '" + pay_method + "'");
            method.next();
            MySQL.executeIUD("INSERT INTO `guest_stays` (`guest_name`, `mobile`, `arrival_date_time`, `departure_date_time`, `nights`, `packs`, `room_id`, `special_requests`, `package_id`, `price`, `payment_method`, `guest_status_id`) "
                    + "VALUES('" + name + "', '" + mobile + "', '" + a_date + "', '" + d_date + "', '" + nights + "', '" + packs + "', '" + room_id.getString("room_id") + "', '" + special + "', '" + pkg_id.getString("id") + "', '" + price + "', '" + method.getString("id") + "', '1' ) ");

        } catch (Exception ex) {
            Logger.getLogger(Checkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        verify();
        String name = jTextField1.getText();
        String pkg = jComboBox3.getSelectedItem().toString();
        String a_date = LocalDate.parse(jFormattedTextField1.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String a_time = jFormattedTextField2.getText();
        String d_date = LocalDate.parse(jFormattedTextField3.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String d_time = jFormattedTextField4.getText();
        String special = jTextField5.getText();
        String price = jTextField11.getText();
        String nights = jTextField2.getText();
        String packs = jTextField3.getText();
        String pay_method = jComboBox1.getSelectedItem().toString();
        String room = jComboBox2.getSelectedItem().toString();
        String mobile = jTextField4.getText();
        
        try {
            ResultSet room_id = MySQL.executeSearch("SELECT `room_id` FROM `rooms` WHERE `room_no` = '" + room + "'");
            room_id.next();
            ResultSet pkg_id = MySQL.executeSearch("SELECT `id` FROM `package` WHERE `name` = '" + pkg + "'");
            pkg_id.next();
            ResultSet method = MySQL.executeSearch("SELECT `id` FROM `payment_method` WHERE `name` = '" + pay_method + "'");
            method.next();
            MySQL.executeIUD("UPDATE `guest_stays` SET `guest_name` = '"+name+"', `mobile` = '"+mobile+"', `arrival_date_time` = '"+a_date+"', `departure_date_time` = '"+d_date+"', `nights` = '"+nights+"', `packs` = '"+packs+"', `room_id` = '"+room_id.getString("room_id")+"', `special_requests` = '"+special+"', `package_id` = '"+pkg_id.getString("id")+"', `price` = '"+price+"', `payment_method` = '"+method.getString("id")+"' WHERE `guest_name` = '"+name+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String name = jTextField1.getText();
        String room = jComboBox2.getSelectedItem().toString();
        String mobile = jTextField4.getText();
       
        
        int option = JOptionPane.showConfirmDialog(this, "Your Going to delete this meeting, are you sure?", "confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {

            try {
                MySQL.executeIUD("DELETE FROM `guest_stays` WHERE `guest_name` = '"+name+"' AND `mobile` = '"+mobile+"'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.datetime.component.date.DatePicker datePicker1;
    private raven.datetime.component.date.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private raven.datetime.component.time.TimePicker timePicker1;
    private raven.datetime.component.time.TimePicker timePicker2;
    // End of variables declaration//GEN-END:variables
}
