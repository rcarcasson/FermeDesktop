/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fermedesktop.productos;

import controlador.ControladorFamiliaProducto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.FamiliaProducto;
/**
 * Formulario para la administración de las distintas familias de productos
 * @author Ricardo Carcassón
 */
public class frmFamiliaProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrame
     */
    boolean esNuevo = false;
    public frmFamiliaProductos() {
        initComponents();
        CargarDatos();
        InicializarBotones();  
    }

    /**
     * Este método carga la información de las familias en el jTable de la ventana, para ello se crea un modelo por defecto, se establecen los identificadores de las columnas, para luego
     * instanciar un objeto de la clase FamiliaProducto para cargar en un ArrayList las familias del sistema. Estas familias son cargadas al modelo del jTable para luego cargarlos en el control.
     * Junto con esto se fuerzan los anchos minimos y maximos de la columna ID para que no sea visible al usuario.
     */
    private void CargarDatos(){
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.setColumnIdentifiers(getColumnas());
        FamiliaProducto fm = new FamiliaProducto();
        ArrayList<FamiliaProducto> listafamilias = fm.ObtenerFamilias();
        for (int i=0; i<listafamilias.size();i++){
            modelo.addRow(new Object[]{listafamilias.get(i).getId(),listafamilias.get(i).getId_familia(),listafamilias.get(i).getDescripcion()});
        }
        jTablaFamilias.setModel(modelo);
        jTablaFamilias.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jTablaFamilias.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        jTablaFamilias.getColumn("ID").setMaxWidth(0);
        jTablaFamilias.getColumn("ID").setMinWidth(0);
        jTablaFamilias.getColumn("ID").setPreferredWidth(0);
    }
    
    /**
     * Este método inicializa los botones y cajas de texto a sus propiedades por defecto
     */
    private void InicializarBotones(){
        btnNuevo.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnGuardar.setEnabled(false);
        txtCodigo.setText("");
        txtCodigo.setEditable(false);
        txtDescripcion.setText("");
    }
    
    /**
     * Método para generar los identificadores de las columnas del control JTable
     * @return Un array de tipo string con los nombres de las columnas.
     */
    private String[] getColumnas(){
        String[] columnas = new String[]{"ID","Código Familia","Descripción"};
        return columnas;
    }
    
    /**
     * Método para convertir el identificador de la familia en el formato indicado de tres dígitos en caso de que el usuario, ingrese un código de 1 ó dos dígitos.
     * @param id El código a transformar
     * @return El código transformado en el formato requerido
     */
    private String TransformarID(String id){
        String resultado = "";
        if (id.length()==1){
            resultado = "00"+id;
        }else if (id.length()==2){
            resultado = "0"+id;
        }else if (id.length()==3){
            resultado = id;
        }
        return resultado;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaFamilias = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnAyuda = new javax.swing.JButton();

        setClosable(true);
        setTitle("Familia de Productos");
        setFrameIcon(null);

        jLabel1.setText("Seleccione un registro del listado para modificar o eliminar. Click en Nuevo para agregar:");

        jTablaFamilias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablaFamilias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablaFamilias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaFamiliasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaFamilias);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Código:");

        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        jLabel3.setText("Descripción:");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnAyuda.setText("Ayuda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(btnAyuda))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar)
                    .addComponent(btnCerrar)
                    .addComponent(btnAyuda))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        esNuevo = true;
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtCodigo.requestFocus();
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);
        txtCodigo.setEditable(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtCodigo.getText().length()==0 || txtDescripcion.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe completar los datos solicitados para poder guardar.","Error",JOptionPane.OK_OPTION);
        }else{
            if (esNuevo){
                int confirmar=JOptionPane.showConfirmDialog(null, "¿Desea guardar el nuevo registro?", "Confirmar guardado",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (JOptionPane.YES_NO_OPTION==confirmar){
                    FamiliaProducto fp = new FamiliaProducto();
                    fp.setId_familia(TransformarID(txtCodigo.getText()));
                    fp.setDescripcion(txtDescripcion.getText());
                    ControladorFamiliaProducto ctrFP = new ControladorFamiliaProducto();

                    if (!ctrFP.VerificarSiExiste(txtCodigo.getText(), txtDescripcion.getText())){
                        if (ctrFP.AgregarFamilia(fp)){
                            InicializarBotones();
                            CargarDatos();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ya existe un registro con dicho código o descripción. Verifique la información.", "Error", JOptionPane.OK_OPTION);
                        txtCodigo.requestFocus();
                        txtCodigo.selectAll();
                    }
                }else{
                    txtCodigo.requestFocus();
                    txtCodigo.selectAll();
                }
            }else{
                int confirmar=JOptionPane.showConfirmDialog(null, "¿Está seguro de actualizar la información?","Actualizar familia.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (JOptionPane.YES_NO_OPTION==confirmar){
                    ControladorFamiliaProducto ctrFP = new ControladorFamiliaProducto();
                    DefaultTableModel modeloactual = (DefaultTableModel) jTablaFamilias.getModel();
                    int id = (int)modeloactual.getValueAt(jTablaFamilias.getSelectedRow(),0);
                    if(ctrFP.ActualizarFamilia(id,txtDescripcion.getText())){
                        JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.","Actualizar familia",JOptionPane.INFORMATION_MESSAGE);
                        InicializarBotones();
                        CargarDatos();
                    }else{
                        JOptionPane.showMessageDialog(null,"Ocurrio un error al actualizar el registro","Actualizar familia",JOptionPane.OK_OPTION);
                        txtDescripcion.requestFocus();
                        txtDescripcion.selectAll();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro seleccionado?", "Eliminar registro", JOptionPane.YES_NO_OPTION);
        if (confirmar==JOptionPane.YES_NO_OPTION){
            int fila = jTablaFamilias.getSelectedRow();
            if (fila > -1){
                DefaultTableModel modeloactual= (DefaultTableModel) jTablaFamilias.getModel();
                int id = (int)modeloactual.getValueAt(fila,0);
                ControladorFamiliaProducto ctrFP = new ControladorFamiliaProducto();
                if (ctrFP.EliminarFamilia(id)){
                    JOptionPane.showMessageDialog(null, "Registro eliminado satisfactoriamente.", "Eliminar registro", JOptionPane.INFORMATION_MESSAGE);
                    InicializarBotones();
                    CargarDatos();
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar eliminar el registro.","Eliminar registro",JOptionPane.OK_OPTION);
                    InicializarBotones();
                    CargarDatos();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        InicializarBotones();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void jTablaFamiliasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaFamiliasMouseClicked
        // TODO add your handling code here:
        int fila = jTablaFamilias.getSelectedRow();
        if (fila > -1){
            DefaultTableModel modeloactual = (DefaultTableModel) jTablaFamilias.getModel();
            txtCodigo.setText((String)modeloactual.getValueAt(fila, 1));
            txtDescripcion.setText((String)modeloactual.getValueAt(fila, 2));
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
            esNuevo = false;
        }
    }//GEN-LAST:event_jTablaFamiliasMouseClicked

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        if (txtCodigo.getText().length()==3){
            evt.consume();
        }else{
            if (evt.getKeyChar()<'0' || evt.getKeyChar()>'9'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
        if (txtDescripcion.getText().length()==30){
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaFamilias;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
