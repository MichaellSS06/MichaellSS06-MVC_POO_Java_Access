
package Formularios;

import Clases.*;
import DAO.*;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class dlgModificarVentaDetalle extends javax.swing.JDialog {
    
    // VAriables
    DefaultTableModel tbmVentas;
    DAOVenta venta_dao = new DAOVenta();
    DAOVentaDetalle ventaDetalle_dao = new DAOVentaDetalle();
    DAOServicio servicio_dao = new DAOServicio();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(dlgModificarVentaDetalle.class.getName());

    /**
     * Creates new form dlgModificarVentaDetalle
     */
    public dlgModificarVentaDetalle(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarVentasDetalle();
        cargarVentas();
        cargarServicios();
         
        // Agregar ChangeListener a spCantidad
        spCantidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                calcularSubtotal();
            }
        });
        
        // Agregar ChangeListener a spPrecioUni
        spPrecioUni.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                calcularSubtotal();
            }
        });
    }
    
    private void calcularSubtotal() {
        try {
            // Obtener los valores de los spinners
            // Asegúrate de que spPrecioUni está configurado para double
            //int cantidad = ((Integer) spCantidad.getValue()).intValue();
            //double precioUni = ((Double) spPrecioUni.getValue()).doubleValue();
            int cantidad = Integer.parseInt(spCantidad.getValue().toString());
            double precioUni = Double.parseDouble(spPrecioUni.getValue().toString());

            // Calcular el subtotal
            double subtotalVariableDouble = cantidad * precioUni;

            // Convertir el double a String, formateándolo a dos decimales
            // y asignarlo al campo de texto
            txtSubtotal.setText(String.format("%.2f", subtotalVariableDouble));
        } catch (ClassCastException e) {
            // Manejar el error si los valores no son del tipo esperado
            System.err.println("Error de conversión de tipo en el JSpinner: " + e.getMessage());
            txtSubtotal.setText("Error");
        }
    }
    
    public void cargarVentasDetalle(){
                
        List<CVentaDetalle> listaVentasDetalle = ventaDetalle_dao.listaVentasDetalle();
        tbmVentas = (DefaultTableModel)tabVenta.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < listaVentasDetalle.size(); i++) {
            CVentaDetalle detalle = listaVentasDetalle.get(i);
            CServicio servicio = detalle.getServicio();

            // ✅ Construimos el texto completo del servicio
            String descripcionServicio = servicio.getNombre();

            if (servicio.getMarca() != null) {
                String marca = servicio.getMarca().getMarca();
                String modelo = servicio.getMarca().getModelo();
                int año = servicio.getMarca().getAño();
                descripcionServicio += " " + marca + " " + modelo + " " + año;
            }
        
            ob[0] = detalle.getId();
            ob[1] = detalle.getVenta().getId();
            ob[2] = descripcionServicio; // ✅ Texto completo
            ob[3] = detalle.getCantidad();
            ob[4] = detalle.getPrecio_unit();
            ob[5] = detalle.getSubtotal();
            tbmVentas.addRow(ob);
        }        
        tabVenta.setModel(tbmVentas);
    }
    
    public void cargarVentas(){
                
        List<CVenta> listaVentas = venta_dao.listaVentas();
        for (int i = 0; i < listaVentas.size(); i++) {
            Integer cod = listaVentas.get(i).getId();
            //Integer cod = listaVentas.get(i).getId();
            cbxVenta.addItem(cod.toString());
        }
    }
    
    public void cargarServicios(){
                
        List<CServicio> listaServicios = servicio_dao.listaServicios();
        for (int i = 0; i < listaServicios.size(); i++) {
            String marca = listaServicios.get(i).getMarca().getMarca();
            String modelo = listaServicios.get(i).getMarca().getModelo();
            int año = listaServicios.get(i).getMarca().getAño();
            String util = marca + " " + modelo + " " + año;
            cbxServicio.addItem(listaServicios.get(i).getNombre()+" "+util);
            Integer cod = listaServicios.get(i).getId();
            cbxCodigoServicio.addItem(cod.toString());
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

        jLabel1 = new javax.swing.JLabel();
        spPrecioUni = new javax.swing.JSpinner();
        spCantidad = new javax.swing.JSpinner();
        cbxVenta = new javax.swing.JComboBox<>();
        cbxCodigoServicio = new javax.swing.JComboBox<>();
        cbxServicio = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabVenta = new javax.swing.JTable();
        txtSubtotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Registro de Venta");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Cantidad:");

        spPrecioUni.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 0.1d));

        cbxVenta.setEnabled(false);
        cbxVenta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxVentaItemStateChanged(evt);
            }
        });
        cbxVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxVentaActionPerformed(evt);
            }
        });

        cbxCodigoServicio.setEnabled(false);

        cbxServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxServicioItemStateChanged(evt);
            }
        });

        tabVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VENTA", "SERVICIO", "CANTIDAD", "PRECIO UNITARIO", "SUBTOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabVenta);

        txtSubtotal.setEditable(false);
        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Subtotal:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Precio Unitario:");

        txtId.setEditable(false);
        txtId.setToolTipText("");
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Id:");

        btnModificar.setBackground(new java.awt.Color(0, 255, 204));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Servicio:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Venta:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("INGRESA DATOS DE REGISTRO DE VENTA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(211, 211, 211)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cbxVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spPrecioUni, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(423, 423, 423))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbxCodigoServicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addComponent(btnModificar)))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addGap(156, 156, 156))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(cbxVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxCodigoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spPrecioUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnModificar)
                        .addGap(16, 16, 16)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxVentaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxVentaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxVentaItemStateChanged

    private void cbxServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServicioItemStateChanged
        // TODO add your handling code here:
        int k = cbxServicio.getSelectedIndex();
        if(cbxCodigoServicio.getItemCount()>0)
        cbxCodigoServicio.setSelectedIndex(k);

        // Verifica que el combobox de códigos tenga un valor válido
        if (cbxCodigoServicio.getSelectedItem() != null) {
            try {
                int id_servicio = Integer.parseInt(cbxCodigoServicio.getSelectedItem().toString());

                // Llamar al DAO para obtener el servicio
                CServicio servicioSeleccionado = servicio_dao.obtenerServicio(id_servicio);

                if (servicioSeleccionado != null) {
                    // Asignar el precio al spinner
                    spPrecioUni.setValue(servicioSeleccionado.getPrecio());
                } else {
                    JOptionPane.showMessageDialog(this,
                        "No se encontró información del servicio con ID: " + id_servicio);
                }

            } catch (NumberFormatException e) {
                System.err.println("Error al convertir el ID del servicio: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_cbxServicioItemStateChanged

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        // TODO add your handling code here:
        if (txtId.getText().length()>0){
            Integer id = Integer.parseInt(txtId.getText());
            int cantidad = Integer.parseInt(spCantidad.getValue().toString());
            double precioUni = Double.parseDouble(spPrecioUni.getValue().toString());
            double subtotal = Double.parseDouble(txtSubtotal.getText());
            int id_servicio = Integer.parseInt(cbxCodigoServicio.getSelectedItem().toString());
            int id_venta = Integer.parseInt(cbxVenta.getSelectedItem().toString());

            if ((cantidad>0)&& (precioUni>0) && (subtotal>0)){
                try {
                // 1. Convertir el String "dd/MM/yyyy" a java.util.Date
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date fechaUtil = formato.parse("07/10/2025");

                // 2. Convertir java.util.Date a java.sql.Date
                //java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

                // 3. Crear el objeto CVenta con el java.sql.Date
                CVentaDetalle nuevaVentaDetalle = new CVentaDetalle(id, new CVenta(id_venta,fechaUtil,new CCliente(), 0), new CServicio(id_servicio, "", new CMarca(), 0.0, 0, true), cantidad, precioUni, subtotal);
                CVentaDetalle previaVentaDetalle = ventaDetalle_dao.obtenerVentaDetalle(id);
                double subtotalAnterior = previaVentaDetalle.getSubtotal()*-1;
                System.out.println(subtotalAnterior);
                boolean pre_actualizado= venta_dao.actualizarTotalVenta(id_venta, subtotalAnterior);
                System.out.println(pre_actualizado);
                // 4. Llamar al DAO con el objeto CVenta ya procesado
                    if (ventaDetalle_dao.modificarVentaDetalle(nuevaVentaDetalle)){                  
                        
                                            
                        boolean actualizado = venta_dao.actualizarTotalVenta(id_venta, subtotal);

                        if (actualizado) {
                        JOptionPane.showMessageDialog(this, 
                            "Detalle de venta agregado y total actualizado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(this, 
                                "Detalle guardado, pero no se pudo actualizar el total de la venta");
                        }

                        //
                        spCantidad.setValue(0);
                        spCantidad.requestFocus();
                        spPrecioUni.setValue(0);
                        //
                        cbxServicio.setSelectedIndex(0);
                        cbxCodigoServicio.setSelectedIndex(0);
                        cbxVenta.setSelectedIndex(0);
                        //
                        for (int i = tbmVentas.getRowCount()-1; i >=0; i--) {
                            tbmVentas.removeRow(i);
                        }
                        cargarVentasDetalle();
                    }
                    else
                    JOptionPane.showMessageDialog(this, "Error");
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(this, "El formato de la fecha es inválido. Use dd/mm/yyyy.");
                    System.err.println("Error de parseo de fecha: " + e.getMessage());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado.");
                    System.err.println("Error: " + e.getMessage());
                }
            }
            else
            JOptionPane.showMessageDialog(this, "Los campos deben ser válidos");
        } else
        JOptionPane.showMessageDialog(this, "Seleccione un servicio");
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tabVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabVentaMouseClicked
        // TODO add your handling code here:
        Integer k = tabVenta.getSelectedRow();

        txtId.setText(tabVenta.getValueAt(k, 0).toString());
        cbxVenta.setSelectedItem(tabVenta.getValueAt(k, 1).toString());
        cbxServicio.setSelectedItem(tabVenta.getValueAt(k, 2).toString());
        spCantidad.setValue(tabVenta.getValueAt(k, 3));
    }//GEN-LAST:event_tabVentaMouseClicked

    private void cbxVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxVentaActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                dlgModificarVentaDetalle dialog = new dlgModificarVentaDetalle(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxCodigoServicio;
    private javax.swing.JComboBox<String> cbxServicio;
    private javax.swing.JComboBox<String> cbxVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JSpinner spPrecioUni;
    private javax.swing.JTable tabVenta;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
