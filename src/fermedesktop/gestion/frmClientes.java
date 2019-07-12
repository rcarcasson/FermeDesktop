/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fermedesktop.gestion;

import controlador.ControladorAcceso;
import controlador.ControladorCliente;
import controlador.ControladorUsuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import metodos.Funciones;
import modelo.Acceso;
import modelo.Usuario;

/**
 *
 * @author v-carica
 */
public class frmClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmClientes
     */
    int idCliente = 0;
    public frmClientes() {
        initComponents();
        CargarDatos();
        CambiarEstado(false);
    }

    
    /**
     * Método para cargar los datos de los clientes en la tabla a desplegar.
     */
    private void CargarDatos(){
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.setColumnIdentifiers(getColumnas());
        ControladorCliente ctrlC = new ControladorCliente();
        ArrayList<Usuario> lista = ctrlC.ObtenerClientes(txtCadena.getText());
        for (int i=0; i<lista.size();i++){
            String tipocliente = "";
            if (lista.get(i).getTipo_cliente()=='B'){
                tipocliente = "Boleta";
            }else{
                tipocliente = "Factura";
            }
            modelo.addRow(new Object[]{lista.get(i).getId(),lista.get(i).getRut(),lista.get(i).getNombre(),
            lista.get(i).getDireccion(),lista.get(i).getTelefono(),lista.get(i).getMail(),tipocliente});
        }
        jTablaClientes.setModel(modelo);
        jTablaClientes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jTablaClientes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        jTablaClientes.getColumn("ID").setMaxWidth(0);
        jTablaClientes.getColumn("ID").setMinWidth(0);
        jTablaClientes.getColumn("ID").setPreferredWidth(0);        
    }
    
    /**
     * Método para generar los nombres de las columnas de la jTable
     * @return Un array con los identificadores.
     */
    private String[] getColumnas(){
        String[] columnas = new String[]{"ID","RUT","Nombre","Dirección","Teléfono","Mail","Tipo"};
        return columnas;
    }
    
    private void Limpiar(){
        txtRut.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        opEmpresa.setSelected(false);
        opNatural.setSelected(false);
        txtPassword1.setText("");
        txtPassword2.setText("");
        lbCorreo.setText("");
        lbClave.setText("");
    }
    
    private boolean ValidarCampos(){
        char[] clave1 = txtPassword1.getPassword();
        char[] clave2 = txtPassword2.getPassword();
        Funciones f = new Funciones();
        String cl1 = f.ObtenerClave(clave1), cl2 = f.ObtenerClave(clave2);
        
        if (idCliente==0){
            if (txtRut.getText().length()==0 || txtNombre.getText().length()==0 || txtDireccion.getText().length()==0 ||
                    txtTelefono.getText().length()==0 || txtCorreo.getText().length()==0 || cl1.length()==0 || cl2.length()==0 || 
                    (!opEmpresa.isSelected() && !opNatural.isSelected() )){
                JOptionPane.showMessageDialog(null, "Debe completar los datos del formulario para continuar.","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }else{
                if (clave1.length == clave2.length){
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden. Verifique e intente nuevamente.","Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }            
        }else{
            if (txtRut.getText().length()==0 || txtNombre.getText().length()==0 || txtDireccion.getText().length()==0 ||
                    txtTelefono.getText().length()==0 || txtCorreo.getText().length()==0 || 
                    (!opEmpresa.isSelected() && !opNatural.isSelected() )){
                JOptionPane.showMessageDialog(null, "Debe completar los datos del formulario para continuar.","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }else{
                if (clave1.length == clave2.length){
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden. Verifique e intente nuevamente.","Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }            
            
        }
    }
    
    private void CambiarEstado(boolean estado){
        txtRut.setEnabled(estado);
        txtNombre.setEnabled(estado);
        txtDireccion.setEnabled(estado);
        txtTelefono.setEnabled(estado);
        txtCorreo.setEnabled(estado);
        opEmpresa.setEnabled(estado);
        opNatural.setEnabled(estado);
        txtPassword1.setEnabled(estado);
        txtPassword2.setEnabled(estado);
        btnGuardar.setEnabled(estado);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opGrupoTipo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabPrincipal = new javax.swing.JTabbedPane();
        jPanelBusqueda = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaClientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtCadena = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanelGestion = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbCorreo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPassword1 = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        txtPassword2 = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        opNatural = new javax.swing.JRadioButton();
        opEmpresa = new javax.swing.JRadioButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAyuda2 = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtRut = new javax.swing.JTextField();
        lbClave = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Gestión de Clientes");
        setFrameIcon(null);

        jLabel1.setText("Ingrese el rut o parte del nombre del cliente para realizar una búsqueda. El sistema buscará todos los registros coincidentes para desplegarlos.");

        jLabel2.setText("Haga doble click sobre un resultado para visualizar y ver los datos del cliente. Para ingresar un nuevo cliente: Gestón -> Nuevo");

        jTabPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabPrincipalMouseClicked(evt);
            }
        });

        jTablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTablaClientes.getTableHeader().setReorderingAllowed(false);
        jTablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaClientes);
        jTablaClientes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel3.setText("RUT/Nombre:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBusquedaLayout = new javax.swing.GroupLayout(jPanelBusqueda);
        jPanelBusqueda.setLayout(jPanelBusquedaLayout);
        jPanelBusquedaLayout.setHorizontalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(0, 669, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelBusquedaLayout.setVerticalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCadena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabPrincipal.addTab("Búsqueda", jPanelBusqueda);

        jLabel4.setText("Rut:");
        jLabel4.setToolTipText("");

        jLabel5.setText("Nombre:");

        txtNombre.setNextFocusableComponent(txtDireccion);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel6.setText("Dirección:");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel7.setText("Teléfono:");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel8.setText("Mail:");

        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Datos de cuenta");

        jLabel11.setText("Usuario:");

        lbCorreo.setForeground(new java.awt.Color(255, 51, 102));

        jLabel12.setText("Clave:");

        txtPassword1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassword1KeyTyped(evt);
            }
        });

        jLabel13.setText("Repetir clave:");

        txtPassword2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassword2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtPassword2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                .addComponent(txtPassword1, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lbCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("Cliente tipo:");

        opGrupoTipo.add(opNatural);
        opNatural.setText("Natural");

        opGrupoTipo.add(opEmpresa);
        opEmpresa.setText("Empresa");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
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

        btnAyuda2.setText("Ayuda");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtRut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRutFocusLost(evt);
            }
        });
        txtRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutActionPerformed(evt);
            }
        });
        txtRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRutKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRutKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelGestionLayout = new javax.swing.GroupLayout(jPanelGestion);
        jPanelGestion.setLayout(jPanelGestionLayout);
        jPanelGestionLayout.setHorizontalGroup(
            jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelGestionLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelGestionLayout.createSequentialGroup()
                            .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelGestionLayout.createSequentialGroup()
                                    .addComponent(opNatural)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(opEmpresa)))))
                    .addGroup(jPanelGestionLayout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAyuda2)))
                .addGap(18, 18, 18)
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelGestionLayout.setVerticalGroup(
            jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelGestionLayout.createSequentialGroup()
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(opNatural)
                            .addComponent(opEmpresa))))
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGestionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnEliminar)
                            .addComponent(btnCancelar)
                            .addComponent(btnAyuda2)
                            .addComponent(btnGuardar)))
                    .addGroup(jPanelGestionLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lbClave, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jTabPrincipal.addTab("Gestión", jPanelGestion);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabPrincipal)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTabPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        CambiarEstado(true);
        btnNuevo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnNuevo.setEnabled(false);
        Limpiar();
        idCliente = 0;
        txtRut.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (ValidarCampos()){
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea guardar los datos del cliente?","Guardar",JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_NO_OPTION){
                Usuario u = new Usuario();
                u.setId(idCliente);              
                u.setRut(txtRut.getText());
                u.setNombre(txtNombre.getText());
                u.setDireccion(txtDireccion.getText());
                u.setMail(txtCorreo.getText());
                u.setTelefono(txtTelefono.getText());
                if (opEmpresa.isSelected()){
                    u.setTipo_cliente('F');
                }else{
                    u.setTipo_cliente('B');
                }
                if (idCliente == 0){
                    ControladorCliente ctrC = new ControladorCliente();
                    if (ctrC.AgregarCliente(u)){
                        Funciones f = new Funciones();
                        ControladorAcceso ctrA = new ControladorAcceso();
                        Acceso a = new Acceso();
                        a.setUsuario(txtCorreo.getText());
                        a.setClave(f.Encriptar(f.ObtenerClave(txtPassword1.getPassword())));
                        a.setProveedor_id(0);
                        a.setUsuario_id(ctrA.ObtenerIDUsuario(txtCorreo.getText()));

                        if (ctrA.AgregarUsuario(a)){
                            JOptionPane.showMessageDialog(null, "Cliente generado correctamente.","Crear cliente",JOptionPane.INFORMATION_MESSAGE);
                            Limpiar();
                            CargarDatos();
                            CambiarEstado(false);
                            btnNuevo.setEnabled(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ocurrio un error al generar el cliente.","Crear cliente",JOptionPane.ERROR_MESSAGE);
                    }         
                }else{
                    ControladorCliente ctrC = new ControladorCliente();
                    if (ctrC.ModificarCliente(u)){
                        Funciones f = new Funciones();
                        ControladorAcceso ctrA = new ControladorAcceso();
                        Acceso a = new Acceso();
                        a.setUsuario(txtCorreo.getText());
                        String clave = f.ObtenerClave(txtPassword1.getPassword());
                        if (clave.length()!=0){
                            a.setClave(f.Encriptar(clave));
                        }
                        a.setUsuario_id(idCliente);
                        if (ctrA.ModificarUsuario(a)){
                            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.","Modificar cliente",JOptionPane.INFORMATION_MESSAGE);
                            Limpiar();
                            CargarDatos();
                            CambiarEstado(false);
                            idCliente = 0;
                            jTabPrincipal.setSelectedIndex(0);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ocurrio un error al modificar al cliente.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                txtRut.requestFocus();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtRutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutKeyTyped
        // TODO add your handling code here:
        if (txtRut.getText().length() == 10){
            evt.consume();
        }
    }//GEN-LAST:event_txtRutKeyTyped

    private void txtRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutActionPerformed

    private void txtRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtRutKeyPressed

    private void txtRutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtRutKeyReleased

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        // TODO add your handling code here:
        lbCorreo.setText(txtCorreo.getText());
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        // TODO add your handling code here:
        if (txtCorreo.getText().length() == 50){
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtRutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRutFocusLost
        // TODO add your handling code here:
        if (txtRut.getText().length() != 0){
            Funciones fn = new Funciones();
            if (!fn.ValidarRut(txtRut.getText())){
                JOptionPane.showMessageDialog(null, "El rut ingresado no es válido. Verifique e intente nuevamente.", "Error de rut", JOptionPane.ERROR_MESSAGE);
                txtRut.requestFocus();
                txtRut.selectAll();
            }
            ControladorUsuario ctrU = new ControladorUsuario();
            if (ctrU.ExisteRut(txtRut.getText())){
                JOptionPane.showMessageDialog(null, "Ya existe un usuario registrado con este rut","Rut existente",JOptionPane.INFORMATION_MESSAGE);
                txtRut.requestFocus();
                txtRut.selectAll();
            }
                    
            
        }

    }//GEN-LAST:event_txtRutFocusLost

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        // TODO add your handling code here:
        if (txtCorreo.getText().length() != 0){
            Funciones fn = new Funciones();
            if (!fn.ValidarMail(txtCorreo.getText())){
                JOptionPane.showMessageDialog(null, "El correo ingresado no posee un formato de mail válido. Verifique e intente nuevamente.","Error de mail",JOptionPane.ERROR_MESSAGE);
                txtCorreo.requestFocus();
                txtCorreo.selectAll();
            }
            ControladorUsuario ctrU = new ControladorUsuario();
            if (ctrU.ExisteCorreo(txtCorreo.getText())){
                JOptionPane.showMessageDialog(null, "Ya existe un usuario registrado con este correo.","Correo existente",JOptionPane.INFORMATION_MESSAGE);
                txtCorreo.requestFocus();
                txtCorreo.selectAll();
            }
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Limpiar();
        CambiarEstado(false);
        btnNuevo.setEnabled(true);
        jTabPrincipal.setSelectedIndex(0);
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if (txtNombre.getText().length() == 50){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
        if (txtDireccion.getText().length() == 50){
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        if (txtTelefono.getText().length() == 15){
            evt.consume();
        }else{
            if (evt.getKeyChar()<'0' || evt.getKeyChar()>'9'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtPassword1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassword1KeyTyped
        // TODO add your handling code here:
        Funciones fn = new Funciones();
        if (fn.ObtenerClave(txtPassword1.getPassword()).length() == 8){
            evt.consume();
        }
    }//GEN-LAST:event_txtPassword1KeyTyped

    private void txtPassword2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassword2KeyTyped
        // TODO add your handling code here:
        Funciones fn = new Funciones();
        if (fn.ObtenerClave(txtPassword2.getPassword()).length() == 8){
            evt.consume();
        }
    }//GEN-LAST:event_txtPassword2KeyTyped

    private void jTablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaClientesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2){
            int fila = jTablaClientes.getSelectedRow();
            if (fila != -1){
                DefaultTableModel modelo = (DefaultTableModel)jTablaClientes.getModel();
                idCliente = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                
                txtRut.setText(modelo.getValueAt(fila, 1).toString());
                txtNombre.setText(modelo.getValueAt(fila, 2).toString());
                txtDireccion.setText(modelo.getValueAt(fila, 3).toString());
                txtTelefono.setText(modelo.getValueAt(fila, 4).toString());
                txtCorreo.setText(modelo.getValueAt(fila, 5).toString());
                lbCorreo.setText(txtCorreo.getText());
                lbClave.setText("Dejar en blanco para no modificar clave de usuario.");
                String tipocliente = (modelo.getValueAt(fila, 6).toString());
                if (tipocliente.equals("Boleta")){
                    opNatural.setSelected(true);
                }else{
                    opEmpresa.setSelected(true);
                }
                CambiarEstado(true);
                btnCancelar.setEnabled(true);
                btnEliminar.setEnabled(true);
                jTabPrincipal.setSelectedIndex(1);
            }
        }
        
    }//GEN-LAST:event_jTablaClientesMouseClicked

    private void jTabPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabPrincipalMouseClicked
        // TODO add your handling code here:
        if (jTabPrincipal.getSelectedIndex()==0){
            Limpiar();
            CambiarEstado(false);
            btnNuevo.setEnabled(true);
            idCliente = 0;
        }
    }//GEN-LAST:event_jTabPrincipalMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar a este cliente?","Eliminar cliente",JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_NO_OPTION){
            ControladorCliente ctrC = new ControladorCliente();
            ControladorAcceso ctrA = new ControladorAcceso();
            if (ctrA.EliminarUsuario(idCliente)){
                if (ctrC.EliminarCliente(idCliente)){
                    JOptionPane.showMessageDialog(null, "Cliente eliminado satisfactoriamente del sistema.","Cliente eliminado",JOptionPane.INFORMATION_MESSAGE);
                    idCliente = 0;
                    Limpiar();
                    CambiarEstado(false);
                    btnNuevo.setEnabled(true);
                    CargarDatos();
                    jTabPrincipal.setSelectedIndex(0);
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el cliente.","Error",JOptionPane.ERROR_MESSAGE);
                }                
            }else{
                JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar la cuenta de acceso del cliente.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        CargarDatos();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyuda2;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelGestion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabPrincipal;
    private javax.swing.JTable jTablaClientes;
    private javax.swing.JLabel lbClave;
    private javax.swing.JLabel lbCorreo;
    private javax.swing.JRadioButton opEmpresa;
    private javax.swing.ButtonGroup opGrupoTipo;
    private javax.swing.JRadioButton opNatural;
    private javax.swing.JTextField txtCadena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword1;
    private javax.swing.JPasswordField txtPassword2;
    private javax.swing.JTextField txtRut;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
