package Vista.ComponentesBD;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager.LookAndFeelInfo;

public class frmPruebas extends JFrame {
   private BDCategorias bDCategorias;
   private BDCategorias bDCategorias1;
   private BDClientes bDClientes1;
   private JButton jButton1;
   private JButton jButton2;
   private JScrollPane jScrollPane1;

   public frmPruebas() {
      this.initComponents();
   }

   private void initComponents() {
      this.bDCategorias1 = new BDCategorias();
      this.jScrollPane1 = new JScrollPane();
      this.bDClientes1 = new BDClientes();
      this.jButton1 = new JButton();
      this.bDCategorias = new BDCategorias();
      this.jButton2 = new JButton();
      this.setDefaultCloseOperation(3);
      this.jScrollPane1.setViewportView(this.bDClientes1);
      this.jButton1.setText("verCliente");
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            frmPruebas.this.jButton1ActionPerformed(evt);
         }
      });
      this.jButton2.setText("ver Dscto");
      this.jButton2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            frmPruebas.this.jButton2ActionPerformed(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(this.bDCategorias1, -2, -1, -2)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jButton1).addGap(41, 41, 41).addComponent(this.bDCategorias, -2, -1, -2).addGap(27, 27, 27).addComponent(this.jButton2))).addContainerGap(128, 32767)));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(this.bDCategorias1, -2, -1, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1, -2, -1, -2).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.bDCategorias, -2, -1, -2).addComponent(this.jButton2))).addContainerGap(32, 32767)));
      this.pack();
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      String Cliente = this.bDClientes1.getIdCliente();
   }

   private void jButton2ActionPerformed(ActionEvent evt) {
      System.out.println(this.bDCategorias.getDescuento());
   }

   public static void main(String[] args) {
      try {
         LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            LookAndFeelInfo info = var1[var3];
            if ("Nimbus".equals(info.getName())) {
               UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException var5) {
         Logger.getLogger(frmPruebas.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (InstantiationException var6) {
         Logger.getLogger(frmPruebas.class.getName()).log(Level.SEVERE, (String)null, var6);
      } catch (IllegalAccessException var7) {
         Logger.getLogger(frmPruebas.class.getName()).log(Level.SEVERE, (String)null, var7);
      } catch (UnsupportedLookAndFeelException var8) {
         Logger.getLogger(frmPruebas.class.getName()).log(Level.SEVERE, (String)null, var8);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new frmPruebas()).setVisible(true);
         }
      });
   }
}
