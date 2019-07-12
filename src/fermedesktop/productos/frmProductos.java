/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fermedesktop.productos;

import controlador.ControladorFamiliaProducto;
import controlador.ControladorProducto;
import controlador.ControladorProveedor;
import controlador.ControladorTipoProducto;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.FamiliaProducto;
import modelo.Producto;
import modelo.Proveedor;
import modelo.TipoProducto;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * Formulario de administración de productos. Cuenta con dos secciones separadas por un Tab Panel. La primera sección es para mostrar la información de los productos, la segunda
 * es el mantenedor.
 * @author Ricardo Carcassón
 */
public class frmProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmProductos
     */
    Hashtable tablaproveedores = new Hashtable();
    Hashtable tablafamilias = new Hashtable();
    Hashtable tablatipos = new Hashtable();
    int idProducto = 0;
    public frmProductos() {
        initComponents();
        CargarDatos();
        //Estableciendo fecha mínima seleccionable en el control Calendar
        Date fecha = new Date();
        calVencimiento.setMinSelectableDate(fecha);
        //Cargando Hashtables
        CargarTablas();
        CambiarStatusControles(false);
    }
    
    /**
     * Método para cargar los Hashtables de los proveedores, familias y tipos de producto. La finalidad de esto es poder buscar de una manera rápida los identificadores
     * de cada registro para la generación del código final del producto sin la necesidad de conectarse a la base de datos.
     */
    private void CargarTablas(){
        //Carga de HashTable para Proveedores
        ControladorProveedor ctrP = new ControladorProveedor();
        ArrayList<Proveedor> listaproveedores = ctrP.ObtenerProveedores();
        for (int i=0; i<listaproveedores.size(); i++){
            tablaproveedores.put(listaproveedores.get(i).getNombre(), listaproveedores.get(i).getId());
        }
        //Carga de Hastable para Familias de productos
        ControladorFamiliaProducto ctrFP = new ControladorFamiliaProducto();
        ArrayList<FamiliaProducto> listafamilias = ctrFP.ObtenerFamilias();
        for (int i=0; i<listafamilias.size(); i++){
            tablafamilias.put(listafamilias.get(i).getDescripcion(),listafamilias.get(i).getId());
        }
        //Carga de hashtable para tipos de productos
        ControladorTipoProducto ctrTP = new ControladorTipoProducto();
        ArrayList<TipoProducto> listatipos = ctrTP.ObtenerTipos();
        for (int i=0; i<listatipos.size(); i++){
            tablatipos.put(listatipos.get(i).getDescripcion(), listatipos.get(i).getId());
        }
    }
    
    /**
     * Método para cargar la información de los productos búscados en el jTable de la primera sección. También se forzará el ancho mínimo y máximo de la columna ID para que
     * no sea visible al usuario.
     */
    private void CargarDatos(){
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.setColumnIdentifiers(getColumnas());
        ControladorProducto ctrlP = new ControladorProducto();
        ArrayList<Producto> listaproductos = ctrlP.ObtenerProductos(txtCodigo.getText());
        for (int i=0; i<listaproductos.size();i++){
            modelo.addRow(new Object[]{listaproductos.get(i).getId(),listaproductos.get(i).getId_producto(),listaproductos.get(i).getDescripcion(),
            listaproductos.get(i).getPrecio(),listaproductos.get(i).getStock(),listaproductos.get(i).getStock_critico()});
        }
        jTablaProductos.setModel(modelo);
        jTablaProductos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jTablaProductos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellHeaderRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tcr.setBackground(Color.white);
        jTablaProductos.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTablaProductos.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTablaProductos.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTablaProductos.getColumn("ID").setMaxWidth(0);
        jTablaProductos.getColumn("ID").setMinWidth(0);
        jTablaProductos.getColumn("ID").setPreferredWidth(0);        
    }
    
    /**
     * Método para generar los nombres de las columnas de la jTable
     * @return Un array con los identificadores.
     */
    private String[] getColumnas(){
        String[] columnas = new String[]{"ID","Código Producto","Descripción","Precio","Stock","Stock crítico"};
        return columnas;
    }
    
    /**
     * Método que convertirá el identificador al formato correspondiente de 3 dígitos.
     * @param codigo Código a transformar
     * @return Código transformado a 3 dígitos
     */
    private String TransformarID(int codigo){
        String cadena = "";
        if (codigo>=1 && codigo<=9){
            cadena = "00" + codigo;
        }else if (codigo>=10 && codigo<=99){
            cadena = "0" + codigo;
        }else{
            cadena = "" + codigo;
        }
        return cadena;
    }

    /**
     * Método que generará el código final del producto de largo 17
     * @return Un String con el código generado.
     */
    private String GenerarCodigoProducto() {
        int idProveedor = (int)tablaproveedores.get(cmbProveedor.getSelectedItem().toString());
        int idFamilia = (int)tablafamilias.get(cmbFamilia.getSelectedItem().toString());
        int idTipo = (int)tablatipos.get(cmbTipoProducto.getSelectedItem().toString());
        
        String proveedor = TransformarID(idProveedor);
        ControladorFamiliaProducto ctrFP = new ControladorFamiliaProducto();
        String familia = ctrFP.ObtenerCodigoFamilia(idFamilia);
        
        String vencimiento="";
        if (calVencimiento.isEnabled()){
            vencimiento = new SimpleDateFormat("ddMMyyyy").format(calVencimiento.getDate());
        }else{
            vencimiento = "00000000";
        }
        
        ControladorTipoProducto ctrTP = new ControladorTipoProducto();
        String tipo = TransformarID(ctrTP.ObtenerSecuencia(idTipo));
        
        return proveedor+familia+vencimiento+tipo;
        
    }
    
    /**
     * Método para reinicializar los campos del formulario al guardar o cancelar
     */
    private void LimpiarCampos(){
        cmbProveedor.removeAllItems();
        cmbFamilia.removeAllItems();
        cmbTipoProducto.removeAllItems();
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtStockCritico.setText("");
        calVencimiento.setEnabled(true);
        chkNoVence.setSelected(false);
        
    }

    /**
     * Metodo que permite cambiar el estado HABILITADO de los controles
     * @param status boolean para indicar que estado tendrán
     */
    private void CambiarStatusControles(boolean status) {
        cmbTipoProducto.setEnabled(status);
        cmbProveedor.setEnabled(status);
        cmbFamilia.setEnabled(status);
        calVencimiento.setEnabled(status);
        chkNoVence.setEnabled(status);
                
        btnBuscarProveedores.setEnabled(status);
        btnBuscarFamilias.setEnabled(status);
        btnBuscarTipos.setEnabled(status);
        btnEliminar.setEnabled(status);
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
        jLabel2 = new javax.swing.JLabel();
        jPanelPrincipal = new javax.swing.JTabbedPane();
        jPanelBusqueda = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaProductos = new javax.swing.JTable();
        btnAyuda = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jPanelGestion = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbFamilia = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        calVencimiento = new com.toedter.calendar.JCalendar();
        chkNoVence = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        cmbTipoProducto = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtStockCritico = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAyuda2 = new javax.swing.JButton();
        btnBuscarProveedores = new javax.swing.JButton();
        btnBuscarFamilias = new javax.swing.JButton();
        btnBuscarTipos = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Administrador de Productos");
        setFrameIcon(null);

        jLabel1.setText("Ingrese parte o el código completo que desee buscar y haga click en Buscar. El sistema buscara todas las coincidencias.");

        jLabel2.setText("Para ver más detalles haga doble click sobre el producto, para ingresar un nuevo producto: Gestión -> Nuevo:");

        jLabel3.setText("Código:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        jTablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTablaProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablaProductos.getTableHeader().setReorderingAllowed(false);
        jTablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaProductos);
        jTablaProductos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAyuda.setText("Ayuda");

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAyuda)))
                .addContainerGap())
        );
        jPanelBusquedaLayout.setVerticalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAyuda)
                        .addComponent(btnBuscar))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelPrincipal.addTab("Búsqueda", jPanelBusqueda);

        jLabel4.setText("Proveedor:");

        cmbProveedor.setEditable(true);

        jLabel5.setText("Familia:");

        cmbFamilia.setEditable(true);
        cmbFamilia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFamiliaItemStateChanged(evt);
            }
        });
        cmbFamilia.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbFamiliaInputMethodTextChanged(evt);
            }
        });

        jLabel6.setText("Vencimiento:");

        chkNoVence.setText("Sin vencimiento");
        chkNoVence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNoVenceActionPerformed(evt);
            }
        });

        jLabel7.setText("Tipo Producto:");

        cmbTipoProducto.setEditable(true);

        jLabel8.setText("Descripción:");

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        jLabel9.setText("Precio:");

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        jLabel10.setText("Stock:");

        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });

        jLabel11.setText("Stock crítico:");

        txtStockCritico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockCriticoKeyTyped(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
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

        btnAyuda2.setText("Ayuda");

        btnBuscarProveedores.setText("Buscar Proveedores");
        btnBuscarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedoresActionPerformed(evt);
            }
        });

        btnBuscarFamilias.setText("Buscar Familias");
        btnBuscarFamilias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFamiliasActionPerformed(evt);
            }
        });

        btnBuscarTipos.setText("Buscar Tipos");
        btnBuscarTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTiposActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGestionLayout = new javax.swing.GroupLayout(jPanelGestion);
        jPanelGestion.setLayout(jPanelGestionLayout);
        jPanelGestionLayout.setHorizontalGroup(
            jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGestionLayout.createSequentialGroup()
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelGestionLayout.createSequentialGroup()
                                .addComponent(calVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkNoVence))
                            .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion)
                            .addGroup(jPanelGestionLayout.createSequentialGroup()
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStockCritico, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                            .addComponent(cmbFamilia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscarFamilias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscarTipos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 126, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGestionLayout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAyuda2)))
                .addContainerGap())
        );
        jPanelGestionLayout.setVerticalGroup(
            jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarProveedores))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarFamilias)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(chkNoVence)
                    .addComponent(calVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarTipos))
                .addGap(18, 18, 18)
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtStockCritico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar)
                    .addComponent(btnAyuda2)
                    .addComponent(btnNuevo))
                .addContainerGap())
        );

        jPanelPrincipal.addTab("Gestión", jPanelGestion);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        if (txtCodigo.getText().length()==17){
            evt.consume();
        }else{
            if (evt.getKeyChar()<'0' || evt.getKeyChar()>'9'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTiposActionPerformed
        // TODO add your handling code here:
        String familia = cmbFamilia.getSelectedItem().toString();
        if (familia.length()!=0){
            String cadena = cmbTipoProducto.getEditor().getItem().toString();
            ControladorTipoProducto ctrTP = new ControladorTipoProducto();
            ArrayList<TipoProducto> tipos;
            int idFamilia = (int)tablafamilias.get(familia);
            if (cadena.length()!=0){
                tipos = ctrTP.FiltrarTipos(idFamilia, cadena);
            }else{
                tipos = ctrTP.ObtenerTiposPorID(idFamilia);
            }
            cmbTipoProducto.removeAllItems();
            for (int i=0;i<tipos.size();i++){
                cmbTipoProducto.addItem(tipos.get(i).getDescripcion());
            }
            cmbTipoProducto.showPopup();
        }else{
            JOptionPane.showMessageDialog(null, "No hay ninguna familia de producto seleccionada.","Error",JOptionPane.OK_OPTION);
            cmbFamilia.requestFocus();
        }

    }//GEN-LAST:event_btnBuscarTiposActionPerformed

    private void btnBuscarFamiliasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFamiliasActionPerformed
        // TODO add your handling code here:
        String cadena = cmbFamilia.getEditor().getItem().toString();
        ControladorFamiliaProducto ctrFP = new ControladorFamiliaProducto();
        ArrayList<FamiliaProducto> familias;
        if (cadena.length()!=0){
            familias = ctrFP.FiltroFamilias(cadena);
        }else{
            familias = ctrFP.ObtenerFamilias();
        }
        cmbFamilia.removeAllItems();
        for (int i=0;i<familias.size();i++){
            cmbFamilia.addItem(familias.get(i).getDescripcion());
        }
        cmbFamilia.showPopup();
        cmbTipoProducto.removeAllItems();        
    }//GEN-LAST:event_btnBuscarFamiliasActionPerformed

    private void btnBuscarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedoresActionPerformed
        // TODO add your handling code here:
        String cadena = cmbProveedor.getEditor().getItem().toString();
        ControladorProveedor ctrP = new ControladorProveedor();
        ArrayList<Proveedor> proveedores;
        if (cadena.length()!=0){
            proveedores = ctrP.FiltroProveedores(cadena);
        }else{
            proveedores = ctrP.ObtenerProveedores();
        }
        cmbProveedor.removeAllItems();
        for (int i=0;i<proveedores.size();i++){
            cmbProveedor.addItem(proveedores.get(i).getNombre());
        }
        cmbProveedor.showPopup();
    }//GEN-LAST:event_btnBuscarProveedoresActionPerformed

    private void txtStockCriticoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockCriticoKeyTyped
        // TODO add your handling code here:
        if (txtStockCritico.getText().length()==5){
            evt.consume();
        }else{
            if (evt.getKeyChar()<'0' || evt.getKeyChar()>'9'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtStockCriticoKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        // TODO add your handling code here:
        if (txtStock.getText().length()==7){
            evt.consume();
        }else{
            if (evt.getKeyChar()<'0' || evt.getKeyChar()>'9'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
        if (txtPrecio.getText().length()==7){
            evt.consume();
        }else{
            if (evt.getKeyChar()<'0' || evt.getKeyChar()>'9'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
        if (txtDescripcion.getText().length()==50){
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void chkNoVenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNoVenceActionPerformed
        // TODO add your handling code here:
        if (chkNoVence.isSelected()){
            calVencimiento.setEnabled(false);
        }else{
            calVencimiento.setEnabled(true);
            Date fecha = new Date();
            calVencimiento.setMinSelectableDate(fecha);
        }
    }//GEN-LAST:event_chkNoVenceActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (cmbFamilia.getSelectedIndex()==-1 || cmbProveedor.getSelectedIndex()==-1 || cmbTipoProducto.getSelectedIndex() == -1 || txtDescripcion.getText()=="" || txtPrecio.getText()=="" ||
                txtStock.getText()=="" || txtStockCritico.getText()==""){
            JOptionPane.showMessageDialog(null, "Falta información por completar del formulario.","Error",JOptionPane.OK_OPTION);
        }else{
            if (Integer.parseInt(txtPrecio.getText())<=0){
                JOptionPane.showMessageDialog(null, "Precio no puede ser menor o igual a 0.","Error",JOptionPane.ERROR_MESSAGE);
            }else if (Integer.parseInt(txtStock.getText())<=0){
                JOptionPane.showMessageDialog(null, "Stock no puede ser menor o igual a 0.","Error",JOptionPane.ERROR_MESSAGE);
            }else if (Integer.parseInt(txtStockCritico.getText())<=0){
                JOptionPane.showMessageDialog(null, "Stock critico no puede ser menor o igual a 0.","Error",JOptionPane.ERROR_MESSAGE);
            }else if (Integer.parseInt(txtStockCritico.getText())>=Integer.parseInt(txtStock.getText())){
                JOptionPane.showMessageDialog(null, "El stock crítico no puede ser mayor o igual al stock del producto.","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                if (idProducto==0){
                    int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro de guardar el nuevo producto?", "Guardar", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_NO_OPTION){
                        int idTipo = (int)tablatipos.get(cmbTipoProducto.getSelectedItem().toString());
                        Producto p = new Producto();
                        p.setId_producto(GenerarCodigoProducto());
                        p.setDescripcion(txtDescripcion.getText());
                        p.setPrecio(Integer.parseInt(txtPrecio.getText()));
                        p.setStock(Integer.parseInt(txtStock.getText()));
                        p.setStock_critico(Integer.parseInt(txtStockCritico.getText()));
                        p.setTipo_producto_id(idTipo);
                        ControladorProducto ctrP = new ControladorProducto();

                        if(ctrP.AgregarProducto(p)){
                            JOptionPane.showMessageDialog(null, "<html>Producto creado correctamente. El código generado es el siguiente: <b>"+GenerarCodigoProducto()+"</b>","Producto guardado",JOptionPane.INFORMATION_MESSAGE);
                            LimpiarCampos();
                            CambiarStatusControles(false);
                        }else{
                            JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar el producto.","Error",JOptionPane.OK_OPTION);
                        }
                    }                    
                }else{
                    int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro de actualizar los datos del producto?","Actualizar producto",JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_NO_OPTION){
                        int idTipo = (int)tablatipos.get(cmbTipoProducto.getSelectedItem().toString());
                        Producto p = new Producto();
                        p.setId(idProducto);
                        p.setDescripcion(txtDescripcion.getText());
                        p.setPrecio(Integer.parseInt(txtPrecio.getText()));
                        p.setStock(Integer.parseInt(txtStock.getText()));
                        p.setStock_critico(Integer.parseInt((txtStockCritico.getText())));
                        
                        ControladorProducto ctrP = new ControladorProducto();
                        
                        if(ctrP.ActualizarProducto(p)){
                            JOptionPane.showMessageDialog(null, "El producto ha sido actualizado.","Actualizar producto",JOptionPane.INFORMATION_MESSAGE);
                            LimpiarCampos();
                            jPanelPrincipal.setSelectedIndex(0);
                            txtCodigo.setText("");
                            CargarDatos();
                        }else{
                            JOptionPane.showMessageDialog(null, "Ocurrio un error al actualizar.","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbFamiliaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbFamiliaInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cmbFamiliaInputMethodTextChanged

    private void cmbFamiliaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFamiliaItemStateChanged
        // TODO add your handling code here:
        cmbTipoProducto.removeAllItems();
    }//GEN-LAST:event_cmbFamiliaItemStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        CargarDatos();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        LimpiarCampos();
        jPanelPrincipal.setSelectedIndex(0);
        CambiarStatusControles(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jTablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaProductosMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==2){
            int fila = jTablaProductos.getSelectedRow();
            if (fila>-1){
                DefaultTableModel modelo = (DefaultTableModel) jTablaProductos.getModel();
                txtDescripcion.setText((String)modelo.getValueAt(fila, 2));
                txtPrecio.setText(modelo.getValueAt(fila, 3).toString());
                txtStock.setText(modelo.getValueAt(fila,4).toString());
                txtStockCritico.setText(modelo.getValueAt(fila, 5).toString());
                
                String codigo = (String)modelo.getValueAt(fila, 1);
                
                //Descomprimiendo el código del producto
                int idProveedor = Integer.parseInt(codigo.substring(0, 3));
                String idFamilia = codigo.substring(3,6);
                String vencimiento = codigo.substring(6,14);
                int idSecuencia = Integer.parseInt(codigo.substring(14,17));
                
                if (vencimiento.equals("00000000")){
                    chkNoVence.setSelected(true);
                    calVencimiento.setEnabled(false);
                }else{
                    SimpleDateFormat fecha = new SimpleDateFormat("ddMMyyyy");
                    try {
                        calVencimiento.setDate(fecha.parse(vencimiento));
                    } catch (ParseException ex) {
                        Logger.getLogger(frmProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                //Buscando información de cada sección del código
                ControladorProveedor ctrP = new ControladorProveedor();
                ControladorFamiliaProducto ctrFP = new ControladorFamiliaProducto();
                ControladorTipoProducto ctrTP = new ControladorTipoProducto();
                cmbProveedor.removeAllItems();
                cmbProveedor.addItem(ctrP.ObtenerNombre(idProveedor));
                cmbProveedor.setSelectedIndex(0);
                
                cmbFamilia.removeAllItems();
                cmbFamilia.addItem(ctrFP.ObtenerDescripcion(idFamilia));
                cmbFamilia.setSelectedIndex(0);
                
                cmbTipoProducto.removeAllItems();
                cmbTipoProducto.addItem(ctrTP.ObtenerDescripcion(idSecuencia));
                cmbTipoProducto.setSelectedIndex(0);
                
                idProducto = Integer.parseInt(modelo.getValueAt(fila,0).toString());
                
                CambiarStatusControles(false);
                btnEliminar.setEnabled(true);
                
                jPanelPrincipal.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_jTablaProductosMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        idProducto = 0;
        LimpiarCampos();
        CambiarStatusControles(true);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el producto en pantalla?","Eliminar producto",JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_NO_OPTION){
            ControladorProducto ctrP = new ControladorProducto();
            if (ctrP.EliminarProducto(idProducto)){
                JOptionPane.showMessageDialog(null, "Producto eliminado satisfactoriamente","Eliminar producto",JOptionPane.INFORMATION_MESSAGE);
                LimpiarCampos();
                CambiarStatusControles(false);
                idProducto = 0;
                CargarDatos();
                jPanelPrincipal.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnAyuda2;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarFamilias;
    private javax.swing.JButton btnBuscarProveedores;
    private javax.swing.JButton btnBuscarTipos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private com.toedter.calendar.JCalendar calVencimiento;
    private javax.swing.JCheckBox chkNoVence;
    private javax.swing.JComboBox<String> cmbFamilia;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JComboBox<String> cmbTipoProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelGestion;
    private javax.swing.JTabbedPane jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaProductos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockCritico;
    // End of variables declaration//GEN-END:variables


}
