
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * 
 * @author Felipe Pacheco Cerdas
 * @author Kendall Tenorio Chevez
 */
public class VentanaLogin extends javax.swing.JFrame {

  /**
   * Creates new form VentanaLogin
   */
  private String nombreObtenido;
  private boolean abierta;

  public VentanaLogin() {

    setLayout(new BorderLayout());
    setContentPane(new JLabel(new ImageIcon(new ImageIcon("poker.jpg").getImage().getScaledInstance(1030, 600, Image.SCALE_DEFAULT))));
    setLayout(new FlowLayout());
    nombreObtenido = "";
    abierta = true;

    initComponents();

    ImageIcon imagen = new ImageIcon(new ImageIcon("cards.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
    this.submit.setIcon(imagen);

    this.submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.submit.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() > 0) {
          String nombre = entradaNombre.getText();
          if (nombre.equals("")) {
            showMessageDialog(null, "No ha ingresado ningún nombre");
          } else {
            nombreObtenido = nombre;
          }
        }
      }
    });
  }
  /*
  * Le muestra un mensae de error por lo que hay dos Jugadores con el mismo nombre
  */
  public void notificarNombreRepetido() {
    showMessageDialog(null, "El nombre indicado ya ha sido escogido. Intentelo de nuevo.");
    nombreObtenido = "";
  }
  /*
  * Da el nombre del jugador
  */
  public String getNombreObtenido() {
    return nombreObtenido;
  }
  /*
  *Da un booleano si esta abierta
  */
  public boolean isAbierta() {
    return abierta;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cerrar = new javax.swing.JButton();
    entradaNombre = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    submit = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setUndecorated(true);

    cerrar.setBackground(new java.awt.Color(204, 0, 0));
    cerrar.setText("X");
    cerrar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cerrarActionPerformed(evt);
      }
    });

    entradaNombre.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

    jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
    jLabel1.setText("Inserte su nombre:");

    jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\felip\\Documents\\NetBeansProjects\\TransporteV2\\BlackJack\\bj.png")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cerrar))
          .addGroup(layout.createSequentialGroup()
            .addGap(85, 85, 85)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel2)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(entradaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 475, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(cerrar)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
        .addComponent(jLabel2)
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(entradaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel1))
            .addGap(12, 12, 12))
          .addComponent(submit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(114, 114, 114))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
    this.dispose();
    abierta = false;
  }//GEN-LAST:event_cerrarActionPerformed

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
      java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new VentanaLogin().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cerrar;
  private javax.swing.JTextField entradaNombre;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel submit;
  // End of variables declaration//GEN-END:variables
}
