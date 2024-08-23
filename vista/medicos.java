/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class medicos extends javax.swing.JFrame {
    
    //VARIABLES GLOBALES DE TIPO DE LAS CLASES DE REFERENCIA
    Connection conn;
    Statement st;
    ResultSet rs;
    String query;
    PreparedStatement pst;
    DefaultTableModel dmt;
    
    

    //constructor
    public medicos() {
        initComponents();
        //probarConexion();
        this.setLocationRelativeTo(null); // para qeu al ejecutar, la ventana aparezca en el centro de la pantalla
        listarMedicos(); //m+etodo para listar todos los médicos
          agregarMedicos();
          eliminarMedicos();
          modificarMedico();
          
       
    }
    
    //método para listar médicos
    
    private void listarMedicos(){
        
        query = "select *  from  tbl_medicos"; // esta sentencia permite mostrar los datos que ya hayan sido ingresados a la base de datos
        conn = conexion.getConexion();//obtiene el objeto de conecion de la BD
        try{
            
            st = conn.createStatement();//inicia objeto que ejecuta la sentencia
            //Ejecuta sentencia en BD y devuelve registros en forma de tabla
            rs = st.executeQuery(query); //rs referencia los registros consultados
            // crea el vector de encabezado del jTable del formulario
            Object vect[]={"Id Medico","Nombre","Apellido"};
            //Define el modelo de datos con el encabezado de la tabla
            dmt =  new DefaultTableModel(null,vect); //inicialioza la tabla donde salen los registros
            //Leer los registros consultados hasta el final
                    while (rs.next()) {
                    vect[0]=rs.getInt("MedIdentificacion");//Obtener dato de columna de la base de datos 
                    vect[1]=rs.getString("MedNombres");
                    vect[2]=rs.getString("MedApellidos");
                    dmt.addRow(vect);//Agrega registro al modelo de datos
                    }
                  tablaMedicos.setModel(dmt); //// asigna data al Jtable del formulario
                            }catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.toString());
}
    
    }
    //Método agregar 
     private void agregarMedicos(){
         
         //preparar sentencia 
         query = "insert into tbl_medicos(MedIdentificacion, MedNombres, MedApellidos) VALUES (?, ?, ?)";
         //obtener la coneción 
         conn =  conexion.getConexion();
         
         try {
            // Preparar la sentencia
            
        
           pst = conn.prepareStatement(query);
            // Establecer los valores de los parámetros
            pst.setInt(1, Integer.parseInt(txtIdMed.getText()));
            pst.setString(2, txtNomMed.getText());
            pst.setString(3, txtApeMed.getText());
            
            // Ejecutar la sentencia
            pst.executeUpdate();
            
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Médico agregado con éxito!");
            
            // Listar los médicos para actualizar la tabla
            listarMedicos();
        } catch (Exception e) {
            // Manejar errores de SQL
            JOptionPane.showMessageDialog(null, e.toString());
        } 
         limpiar();
     
     }
     
      //método parfa limpiar las cajas de texto cuando ya se guarda
     private void limpiar(){
     
     txtIdMed.setText("");
     txtNomMed.setText("");
     txtApeMed.setText("");
     txtIdMed.setEnabled(true);//permite que la caja del id quede habilitada cuando se limpien los datos
     }
     
     //metodo eliminar 
     
     private void eliminarMedicos(){
         
         //preparar sentencia 
         query = "delete from tbl_medicos where MedIdentificacion = ?";
         
         //obtner la conexión 
         
         conn = conexion.getConexion();
         try{
        pst = conn.prepareStatement(query);
        // Establecer el valor del parámetro
        pst.setInt(1, Integer.parseInt(txtIdMed.getText()));
        
        // Ejecutar la sentencia
        int filasAfectadas = pst.executeUpdate();
        
        // Mostrar mensaje de éxito o de error si no se encontró el ID
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Médico eliminado con éxito!");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un médico con ese ID");
        }
        
        // Listar los médicos para actualizar la tabla
        listarMedicos();
         }catch(Exception e) {
            // Manejar errores de SQL
           JOptionPane.showMessageDialog(null, e.toString());
        } 
     
     }
     
     
     // método modificar 
     
     private void modificarMedico()
     {
         query = "UPDATE tbl_medicos SET MedNombres = ?, MedApellidos = ? WHERE MedIdentificacion = ?";
         
          //obtner la conexión 
         
         conn = conexion.getConexion();
         
    try {
        // Preparar la sentencia
        pst = conn.prepareStatement(query);
        // Establecer los valores de los parámetros
        pst.setString(1,  txtNomMed.getText());
        pst.setString(2, txtApeMed.getText());
        pst.setInt(3, Integer.parseInt(txtIdMed.getText()));
    
        
        // Ejecutar la sentencia
        int filasAfectadas = pst.executeUpdate();
        
        // Mostrar mensaje de éxito o de error si no se encontró el ID
        if (filasAfectadas == 1) {
            JOptionPane.showMessageDialog(null, "Médico modificado con éxito!");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un médico con ese ID");
        }
        
        // Listar los médicos para actualizar la tabla
       
    } catch (Exception e) {
        // Manejar errores de SQL
        JOptionPane.showMessageDialog(null, e.toString());
    } 
     listarMedicos();
     limpiar();
     }    
     
   


   public void probarConexion(){
Connection con;
con = conexion.getConexion();
if (con!=null) {
JOptionPane.showMessageDialog(null, "Conexión exitosa");
} else {
JOptionPane.showMessageDialog(null, "Error en la conexión");
}
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdMed = new javax.swing.JTextField();
        txtApeMed = new javax.swing.JTextField();
        txtNomMed = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btbModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMedicos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 215, 109));

        jLabel1.setText("Nombre");

        jLabel2.setText("id medico");

        jLabel3.setText("Apellido");

        txtIdMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdMed, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(txtApeMed)
                            .addComponent(txtNomMed))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApeMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(238, 215, 109));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btbModificar.setText("Modificar");
        btbModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnAgregar)
                .addGap(41, 41, 41)
                .addComponent(btbModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addGap(41, 41, 41)
                .addComponent(btnNuevo)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btbModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo))
                .addGap(19, 19, 19))
        );

        jPanel3.setBackground(new java.awt.Color(238, 215, 109));

        tablaMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id Médico", "Nombre", "Apellido"
            }
        ));
        tablaMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMedicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaMedicos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel4.setBackground(new java.awt.Color(238, 215, 109));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setActionCommand("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(50, 50, 50))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here: 
         agregarMedicos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btbModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbModificarActionPerformed
        // TODO add your handling code here:
        modificarMedico();
    }//GEN-LAST:event_btbModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
         eliminarMedicos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtIdMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdMedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMedActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tablaMedicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMedicosMouseClicked
        // TODO add your handling code here:
        
        // esto permite que cuando se le de click a las columnas aparezca en la caja detos el id,nombre y apellido
        int fila;
        
        fila = tablaMedicos.getSelectedRow();
        if(fila == -1){
        JOptionPane.showMessageDialog(this, "selecionar una fila");
        }else {
            String id = tablaMedicos.getValueAt(fila,0).toString();
            String nom = tablaMedicos.getValueAt(fila,1).toString();
            String apell = tablaMedicos.getValueAt(fila,2).toString();
            txtIdMed.setText(id);
            txtNomMed.setText(nom);
            txtApeMed.setText(apell);
            txtIdMed.setEnabled(false); //para inhabilitar la caja de texto del id
            
        
        }
    }//GEN-LAST:event_tablaMedicosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        
        // acá dodne estamoe es el método keypreesed el cual permite qeu vaya saliendi poco a poco lo que se esta buscando
         String nom = txtBuscar.getText();
        query = "select *  from  tbl_medicos where MedNombres like '%" + nom + "%'"; // esta sentencia permite mostrar los datos que ya hayan sido ingresados a la base de datos
        conn = conexion.getConexion();//obtiene el objeto de conecion de  la BD
         try{
            
            st = conn.createStatement();//inicia objeto que ejecuta la sentencia
            //Ejecuta sentencia en BD y devuelve registros en forma de tabla
            rs = st.executeQuery(query); //rs referencia los registros consultados
            // crea el vector de encabezado del jTable del formulario
            Object vect[]={"Id Medico","Nombre","Apellido"};
            //Define el modelo de datos con el encabezado de la tabla
            dmt =  new DefaultTableModel(null,vect); //inicialioza la tabla donde salen los registros
            //Leer los registros consultados hasta el final
                    while (rs.next()) {
                    vect[0]=rs.getInt("MedIdentificacion");//Obtener dato de columna de la base de datos 
                    vect[1]=rs.getString("MedNombres");
                    vect[2]=rs.getString("MedApellidos");
                    dmt.addRow(vect);//Agrega registro al modelo de datos
                    }
                  tablaMedicos.setModel(dmt); //// asigna data al Jtable del formulario
                            }catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.toString());
    }//GEN-LAST:event_txtBuscarKeyPressed
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
            java.util.logging.Logger.getLogger(medicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(medicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(medicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(medicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new medicos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbModificar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMedicos;
    private javax.swing.JTextField txtApeMed;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtIdMed;
    private javax.swing.JTextField txtNomMed;
    // End of variables declaration//GEN-END:variables
}
