/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.DAOCatProblemas;
import Model.CatProblemas;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Colin
 */
public class CadCatProblemas extends javax.swing.JFrame {

    /**
     * Creates new form CadStatus
     */
    public CadCatProblemas() {
        initComponents();
        carregaCatProblemas();
        jTableCatProblemas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        /** habilita a ordenação por colunas */
        jTableCatProblemas.setRowSorter(new TableRowSorter());
        jTableCatProblemas.setAutoCreateRowSorter(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTFCategoria = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFid = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jBTnovo = new javax.swing.JButton();
        jBTeditar = new javax.swing.JButton();
        jBTsalvar = new javax.swing.JButton();
        jBTexcluir = new javax.swing.JButton();
        jBTcancelar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCatProblemas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Categorias de Problemas");
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(450, 450));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cadastro de Categoria de Problemas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Categoria");

        jTFCategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFCategoria.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("ID:");

        jTFid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFid.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTFCategoria)
                        .addGap(53, 53, 53)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTFid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBTnovo.setText("Novo");
        jBTnovo.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jBTnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTnovoActionPerformed(evt);
            }
        });

        jBTeditar.setText("Editar");
        jBTeditar.setEnabled(false);
        jBTeditar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jBTeditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTeditarActionPerformed(evt);
            }
        });

        jBTsalvar.setText("Salvar");
        jBTsalvar.setEnabled(false);
        jBTsalvar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jBTsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTsalvarActionPerformed(evt);
            }
        });

        jBTexcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancel.png"))); // NOI18N
        jBTexcluir.setText("Excluir");
        jBTexcluir.setEnabled(false);
        jBTexcluir.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jBTexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTexcluirActionPerformed(evt);
            }
        });

        jBTcancelar.setText("Cancelar");
        jBTcancelar.setEnabled(false);
        jBTcancelar.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jBTcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTcancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBTnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTeditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTsalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTexcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTcancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTeditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTexcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableCatProblemas.setBackground(new java.awt.Color(204, 204, 204));
        jTableCatProblemas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableCatProblemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCatProblemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCatProblemasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCatProblemas);
        if (jTableCatProblemas.getColumnModel().getColumnCount() > 0) {
            jTableCatProblemas.getColumnModel().getColumn(0).setMinWidth(50);
            jTableCatProblemas.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableCatProblemas.getColumnModel().getColumn(0).setMaxWidth(100);
            jTableCatProblemas.getColumnModel().getColumn(1).setMinWidth(400);
            jTableCatProblemas.getColumnModel().getColumn(1).setPreferredWidth(400);
            jTableCatProblemas.getColumnModel().getColumn(1).setMaxWidth(500);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(516, 413));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableCatProblemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCatProblemasMouseClicked
        int ra;
        ra = (int) jTableCatProblemas.getModel().getValueAt(jTableCatProblemas.getSelectedRow(),0);
        jTFid.setText(String.valueOf(jTableCatProblemas.getModel().getValueAt(jTableCatProblemas.getSelectedRow(),0)));
        jTFCategoria.setText((String) jTableCatProblemas.getModel().getValueAt(jTableCatProblemas.getSelectedRow(),1));
        
        //editar();
        jBTeditar.setEnabled(true);
        jBTcancelar.setEnabled(true);
        jBTnovo.setEnabled(false);
        
        
         // TODO add your handling code here:
    }//GEN-LAST:event_jTableCatProblemasMouseClicked

    private void jBTcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTcancelarActionPerformed
        cancelar();
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTcancelarActionPerformed

    private void jBTsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTsalvarActionPerformed
        
        DAOCatProblemas daocatprob = new DAOCatProblemas();
        CatProblemas catprob = new CatProblemas();
        
        catprob.setNome(jTFCategoria.getText());
        
        if(jTFid.getText().isEmpty()){
            daocatprob.inserirCatProblemas(catprob);
            JOptionPane.showMessageDialog(null, "Categoria salva com sucesso!");
        }else{
            catprob.setId(Integer.parseInt(jTFid.getText()));
            daocatprob.updateCatProblemas(catprob);
            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
        }
        
        cancelar();
        carregaCatProblemas();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTsalvarActionPerformed

    private void jBTnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTnovoActionPerformed
        editar();
        clear();
        jBTeditar.setEnabled(false);
        jBTexcluir.setEnabled(false);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTnovoActionPerformed

    private void jBTeditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTeditarActionPerformed
        editar();
        jBTnovo.setEnabled(false);
        jBTeditar.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTeditarActionPerformed

    private void jBTexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTexcluirActionPerformed
        String msg = "Tem certeza que deseja excluir a Categoria "+jTFCategoria.getText()+" ?";
        int result = JOptionPane.showConfirmDialog(null, msg, "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
        
        if(result == JOptionPane.YES_OPTION){
            DAOCatProblemas daocatprob = new DAOCatProblemas();
            daocatprob.exluirCatProblemas(Integer.parseInt(jTFid.getText()));
            JOptionPane.showMessageDialog(null, "Categoria excluida com sucesso!");
            carregaCatProblemas();
            clear(); cancelar();
        }else{
            JOptionPane.showMessageDialog(null, "Ação Cancelada!");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTexcluirActionPerformed

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
            java.util.logging.Logger.getLogger(CadCatProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadCatProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadCatProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadCatProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadCatProblemas().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTcancelar;
    private javax.swing.JButton jBTeditar;
    private javax.swing.JButton jBTexcluir;
    private javax.swing.JButton jBTnovo;
    private javax.swing.JButton jBTsalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFCategoria;
    private javax.swing.JTextField jTFid;
    private javax.swing.JTable jTableCatProblemas;
    // End of variables declaration//GEN-END:variables

    public void carregaCatProblemas(){
        /**  Carrega ComboBox Status*/
        
        DAOCatProblemas daocatprob = new DAOCatProblemas();
        CatProblemas catprob;
        List<CatProblemas> listacatprob; 
        listacatprob = daocatprob.listaCatProblemas();
        
        jTableCatProblemas.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTableCatProblemas.getColumnModel().getColumn(1).setPreferredWidth(50);
        
        DefaultTableModel model = (DefaultTableModel) jTableCatProblemas.getModel();
        model.setNumRows(0);
        
        for(int i = 0; i<listacatprob.size(); i++){
           catprob = listacatprob.get(i);
           model.addRow(new Object[] {catprob.getId(), catprob.getNome()});
           
        }
    }
    
    public void editar(){
        jTFid.setEnabled(false);
        jTFCategoria.setEnabled(true);
        jBTsalvar.setEnabled(true);
        jBTcancelar.setEnabled(true);
        jBTexcluir.setEnabled(true);
        jBTnovo.setEnabled(false);
        jBTeditar.setEnabled(true);
    }
    
    public void cancelar(){
        jTFid.setEnabled(false);
        jTFCategoria.setEnabled(false);
        jBTsalvar.setEnabled(false);
        jBTcancelar.setEnabled(false);
        jBTexcluir.setEnabled(false);
        jBTeditar.setEnabled(false);
        jBTnovo.setEnabled(true);
    }
    
    public void clear(){
        jTFid.setText(null);
        jTFCategoria.setText(null);
    }
}
