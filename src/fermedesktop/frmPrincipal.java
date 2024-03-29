/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fermedesktop;

import fermedesktop.gestion.frmAdministradores;
import fermedesktop.gestion.frmClientes;
import fermedesktop.gestion.frmEmpleados;
import fermedesktop.gestion.frmProveedores;
import fermedesktop.productos.frmProductos;
import fermedesktop.productos.frmFamiliaProductos;
import fermedesktop.productos.frmTipoProductos;
import java.awt.Toolkit;

/**
 * Formulario principal del sistema. Este formulario es del tipo contenedora, donde cada una de las ventanas de sus menus serán cargadas como ventanas hijas.
 * @author Ricardo Carcassón
 */
public class frmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/tools.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuClientes = new javax.swing.JMenuItem();
        mnuProveedores = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuEmpleados = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuAdministradores = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuFamiliaProductos = new javax.swing.JMenuItem();
        jMenuTipoProductos = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ferreteria Ferme");

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );

        jMenu1.setText("Gestión");

        mnuClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        mnuClientes.setText("Clientes");
        mnuClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClientesActionPerformed(evt);
            }
        });
        jMenu1.add(mnuClientes);

        mnuProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK));
        mnuProveedores.setText("Proveedores");
        mnuProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProveedoresActionPerformed(evt);
            }
        });
        jMenu1.add(mnuProveedores);
        jMenu1.add(jSeparator1);

        mnuEmpleados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        mnuEmpleados.setText("Empleados");
        mnuEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEmpleadosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuEmpleados);
        jMenu1.add(jSeparator2);

        mnuAdministradores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        mnuAdministradores.setText("Administradores");
        mnuAdministradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAdministradoresActionPerformed(evt);
            }
        });
        jMenu1.add(mnuAdministradores);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Módulos");

        jMenu3.setText("Productos");

        jMenuFamiliaProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        jMenuFamiliaProductos.setText("Familia de Productos");
        jMenuFamiliaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFamiliaProductosActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuFamiliaProductos);

        jMenuTipoProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jMenuTipoProductos.setText("Tipo de Productos");
        jMenuTipoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTipoProductosActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuTipoProductos);
        jMenu3.add(jSeparator3);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Administrador de Productos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenu2.add(jMenu3);

        jMenuItem1.setText("Facturación");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Boleta");
        jMenu2.add(jMenuItem2);

        jMenuItem5.setText("Orden de Compra");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Informes");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Salir");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuFamiliaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFamiliaProductosActionPerformed
        // TODO add your handling code here:
        frmFamiliaProductos frmFP = new frmFamiliaProductos();
        desktop.add(frmFP);
        frmFP.setVisible(true);
    }//GEN-LAST:event_jMenuFamiliaProductosActionPerformed

    private void jMenuTipoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuTipoProductosActionPerformed
        // TODO add your handling code here:
        frmTipoProductos frmTP = new frmTipoProductos();
        desktop.add(frmTP);
        frmTP.setVisible(true);
    }//GEN-LAST:event_jMenuTipoProductosActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        frmProductos frmP = new frmProductos();
        desktop.add(frmP);
        frmP.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mnuClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClientesActionPerformed
        // TODO add your handling code here:
        frmClientes frmC = new frmClientes();
        desktop.add(frmC);
        frmC.setVisible(true);
    }//GEN-LAST:event_mnuClientesActionPerformed

    private void mnuProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProveedoresActionPerformed
        // TODO add your handling code here:
        frmProveedores frmProv = new frmProveedores();
        desktop.add(frmProv);
        frmProv.setVisible(true);
    }//GEN-LAST:event_mnuProveedoresActionPerformed

    private void mnuEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEmpleadosActionPerformed
        // TODO add your handling code here:
        frmEmpleados frmEmp = new frmEmpleados();
        desktop.add(frmEmp);
        frmEmp.setVisible(true);
    }//GEN-LAST:event_mnuEmpleadosActionPerformed

    private void mnuAdministradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAdministradoresActionPerformed
        // TODO add your handling code here:
        frmAdministradores frmAdm = new frmAdministradores();
        desktop.add(frmAdm);
        frmAdm.setVisible(true);
    }//GEN-LAST:event_mnuAdministradoresActionPerformed

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
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuFamiliaProductos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuTipoProductos;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem mnuAdministradores;
    private javax.swing.JMenuItem mnuClientes;
    private javax.swing.JMenuItem mnuEmpleados;
    private javax.swing.JMenuItem mnuProveedores;
    // End of variables declaration//GEN-END:variables
}
