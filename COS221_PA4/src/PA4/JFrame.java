/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PA4;
    import java.sql.*;
    import java.util.Vector;
    import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ruane
 */
public class JFrame extends javax.swing.JFrame {
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    /**
     * Creates new form JFrame
     */
    public JFrame(Connection conn) {
        this.pack();
        this.conn = conn;
        initComponents();
        staffTable();
        String filmsSQL = "SELECT title, release_year, rental_duration, language.name, rental_rate, replacement_cost, rating FROM film INNER JOIN language ON film.language_id=language.language_id";
        refreshFilmsTable(filmsSQL);
        reportTab();

    }
    
    private void staffTable(){
        try {
            // TODO add your handling code here:
            
            //String sql = "SELECT first_name, last_name, address, district, city, postal_code, phone, store, active FROM staff, address, city";
            stmt = conn.createStatement();
            String sql = "SELECT first_name,last_name, address.address, address.district, city.city, address.phone, store_id, active FROM staff "+
                    "INNER JOIN address ON staff.address_id=address.address_id INNER JOIN city ON address.city_id=city.city_id";
            rs = stmt.executeQuery(sql);
            StaffTable.setModel(buildTableModel(rs));
            
            StaffTable.getColumnModel().getColumn(0).setHeaderValue("FirstName");
            StaffTable.getColumnModel().getColumn(1).setHeaderValue("LastName");
            StaffTable.getColumnModel().getColumn(2).setHeaderValue("Address");
            StaffTable.getColumnModel().getColumn(3).setHeaderValue("District");
            StaffTable.getColumnModel().getColumn(4).setHeaderValue("City");
            StaffTable.getColumnModel().getColumn(5).setHeaderValue("Phone");
            StaffTable.getColumnModel().getColumn(6).setHeaderValue("Store");
            StaffTable.getColumnModel().getColumn(7).setHeaderValue("Active Status");
            
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    private void refreshFilmsTable(String sql) {
         try {
            // TODO add your handling code here:

            //String sql = "SELECT first_name, last_name, address, district, city, postal_code, phone, store, active FROM staff, address, city";
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(sql);
            FilmsTable.setModel(buildTableModel(rs));
            
            FilmsTable.getColumnModel().getColumn(0).setHeaderValue("Title");
            FilmsTable.getColumnModel().getColumn(1).setHeaderValue("Year");
            FilmsTable.getColumnModel().getColumn(2).setHeaderValue("Rental Duration");
            FilmsTable.getColumnModel().getColumn(3).setHeaderValue("Language");
            FilmsTable.getColumnModel().getColumn(4).setHeaderValue("Rental Rate");
            FilmsTable.getColumnModel().getColumn(5).setHeaderValue("Replacement Cost");
            FilmsTable.getColumnModel().getColumn(6).setHeaderValue("Rating");
            

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    private void reportTab(){
        try {

            stmt = conn.createStatement();
            String sql = "SELECT " +
                        "    CONCAT(c.city, ',', cy.country) AS store, " +
                        "    cat.name AS category, " +
                        "    COUNT(DISTINCT f.film_id) AS NumberOfMoviesPerGenre " +
                        "FROM " +
                        "    inventory AS i " +
                        "    INNER JOIN store AS s ON i.store_id = s.store_id " +
                        "    INNER JOIN address AS a ON s.address_id = a.address_id " +
                        "    INNER JOIN city AS c ON a.city_id = c.city_id " +
                        "    INNER JOIN country AS cy ON c.country_id = cy.country_id " +
                        "    INNER JOIN staff AS m ON s.manager_staff_id = m.staff_id " +
                        "    INNER JOIN film AS f ON i.film_id = f.film_id " +
                        "    INNER JOIN film_category AS fc ON f.film_id = fc.film_id " +
                        "    INNER JOIN category AS cat ON fc.category_id = cat.category_id " +
                        "    GROUP BY cat.category_id, store ";
            rs = stmt.executeQuery(sql);
            ReportTable.setModel(buildTableModel(rs));
            //ReportTable.getColumnModel().getColumn(0).setHeaderValue("Store");
            ReportTable.getColumnModel().getColumn(0).setHeaderValue("Genre");
            ReportTable.getColumnModel().getColumn(1).setHeaderValue("Number of Movies Per Genre");

            

        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    private void position(){
         SwingUtilities.invokeLater(() -> {
        this.pack(); // Automatically size the frame
            
        Point location = this.getLocationOnScreen();
         });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InputBox = new javax.swing.JDialog();
        JTabbedPane = new javax.swing.JTabbedPane();
        StaffPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StaffTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        AddFilm = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        FilmsTable = new javax.swing.JTable();
        BtnSearch = new javax.swing.JButton();
        SearchBy = new javax.swing.JComboBox<>();
        SearchText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ReportTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        javax.swing.GroupLayout InputBoxLayout = new javax.swing.GroupLayout(InputBox.getContentPane());
        InputBox.getContentPane().setLayout(InputBoxLayout);
        InputBoxLayout.setHorizontalGroup(
            InputBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        InputBoxLayout.setVerticalGroup(
            InputBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DVD Store (PA4)");
        setLocation(new java.awt.Point(500, 240));

        StaffPanel.setToolTipText("");
        StaffPanel.setName("StaffPanel"); // NOI18N
        StaffPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StaffPanelMouseEntered(evt);
            }
        });

        StaffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "FirstName", "LastName", "Address", "District", "City", "PostalCode", "Phone", "Store", "ActiveStatus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StaffTable.setToolTipText("");
        StaffTable.setAutoscrolls(false);
        StaffTable.setName("StaffTable"); // NOI18N
        jScrollPane1.setViewportView(StaffTable);

        jButton1.setText("Apply Filters");
        jButton1.setName("BtnApplyFilters"); // NOI18N

        jTextField1.setText("Filters");
        jTextField1.setName("EditFilters"); // NOI18N

        javax.swing.GroupLayout StaffPanelLayout = new javax.swing.GroupLayout(StaffPanel);
        StaffPanel.setLayout(StaffPanelLayout);
        StaffPanelLayout.setHorizontalGroup(
            StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(StaffPanelLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        StaffPanelLayout.setVerticalGroup(
            StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaffPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addGap(78, 78, 78))
        );

        JTabbedPane.addTab("Staff", null, StaffPanel, "");
        StaffPanel.getAccessibleContext().setAccessibleName("");

        jPanel2.setName("FilmsPanel"); // NOI18N

        AddFilm.setText("AddFilm");
        AddFilm.setName("BtnAddFilm"); // NOI18N
        AddFilm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddFilmMouseClicked(evt);
            }
        });

        FilmsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title", "Year", "Description", "Rating", "Length", "Rental_Rate"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        FilmsTable.setToolTipText("");
        FilmsTable.setName("FilmsTable"); // NOI18N
        jScrollPane2.setViewportView(FilmsTable);

        BtnSearch.setText("Search");
        BtnSearch.setName("Search"); // NOI18N
        BtnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSearchMouseClicked(evt);
            }
        });

        SearchBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "title", "release_year", "rating" }));
        SearchBy.setName("SearchBy"); // NOI18N
        SearchBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchByActionPerformed(evt);
            }
        });

        SearchText.setText("Search");
        SearchText.setName("Searchterm"); // NOI18N

        jLabel1.setText("Search By:");
        jLabel1.setName("LblSearchBy"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(AddFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddFilm)
                    .addComponent(BtnSearch)
                    .addComponent(SearchBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );

        JTabbedPane.addTab("Films", jPanel2);

        jPanel3.setName("ReportPanel"); // NOI18N

        ReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Store Name", "Genre", "Number_Movies"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ReportTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addGap(87, 87, 87))
        );

        JTabbedPane.addTab("Report", jPanel3);

        jPanel4.setName("NotificationPanel"); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Client Name", "Client Surname", "email", "Phone Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );

        JTabbedPane.addTab("Notifications", jPanel4);
        jPanel4.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StaffPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffPanelMouseEntered

        
        
    }//GEN-LAST:event_StaffPanelMouseEntered

    private void BtnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSearchMouseClicked
        // TODO add your handling code here:
        String criteria = SearchBy.getSelectedItem().toString();
        String value = '"'+SearchText.getText()+'"';
        String statement = "SELECT title, release_year, rental_duration, language.name, rental_rate, replacement_cost, rating"+
                " FROM film INNER JOIN language ON film.language_id = language.language_id WHERE "+criteria+" = "+value;
        
        refreshFilmsTable(statement);
    }//GEN-LAST:event_BtnSearchMouseClicked

    private void SearchByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchByActionPerformed

    private void AddFilmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddFilmMouseClicked

        String title = '"'+JOptionPane.showInputDialog("enter the film's title") +'"';
        String desc = '"'+JOptionPane.showInputDialog("enter a short description of the film")+'"';
        String year = JOptionPane.showInputDialog("enter the release year of the film");
        String lang = JOptionPane.showInputDialog("enter the language of the film");
        String rental_duration = JOptionPane.showInputDialog("enter the rental duration of the film");
        String rental_rate = JOptionPane.showInputDialog("enter the rental rate of the film");
        String replacement_cost = JOptionPane.showInputDialog("enter the replacement cost of the film");
        String length = JOptionPane.showInputDialog("enter the length of the film");
        String rating = '"'+JOptionPane.showInputDialog("enter the rating of the film")+'"';
        String special_features = '"'+JOptionPane.showInputDialog("enter any special features of the film")+'"';
        
        String sql = "INSERT INTO film (title,description,release_year,language_id,rental_duration,rental_rate,length,replacement_cost,rating,special_features) "+
                "VALUES ("+title+","+desc+","+year+",1,"+rental_duration+","+rental_rate+","+replacement_cost+","+
                length+","+rating+","+special_features+")";
        
        try {
            stmt.execute(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"There was an error in the input data");
        }
        
        
        String filmsSQL = "SELECT title, release_year, rental_duration, language.name, rental_rate, replacement_cost, rating FROM film INNER JOIN language ON film.language_id=language.language_id";
        refreshFilmsTable(filmsSQL);
    }//GEN-LAST:event_AddFilmMouseClicked

    
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}
    
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
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddFilm;
    private javax.swing.JButton BtnSearch;
    private javax.swing.JTable FilmsTable;
    private javax.swing.JDialog InputBox;
    private javax.swing.JTabbedPane JTabbedPane;
    private javax.swing.JTable ReportTable;
    private javax.swing.JComboBox<String> SearchBy;
    private javax.swing.JTextField SearchText;
    private javax.swing.JPanel StaffPanel;
    private javax.swing.JTable StaffTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
