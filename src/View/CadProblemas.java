/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.DAOCatProblemas;
import DAO.DAOProblemas;
import Model.CatProblemas;
import Model.Problemas;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Colin
 */
public final class CadProblemas extends javax.swing.JFrame {

    /**
     * Creates new form CadProblemas
     */
    public CadProblemas() {
        initComponents();
        
        jTableProblemas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        /** habilita a ordenação por colunas */
        jTableProblemas.setRowSorter(new TableRowSorter());
        jTableProblemas.setAutoCreateRowSorter(true);
        
        carregaProblemas();
        carregarCatProblemas();
        
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
        jLabel4 = new javax.swing.JLabel();
        jTFproblema = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCBcategoria = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTFid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jBTnovo = new javax.swing.JButton();
        jBTeditar = new javax.swing.JButton();
        jBTsalvar = new javax.swing.JButton();
        jBTexcluir = new javax.swing.JButton();
        jBTcancelar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProblemas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Problemas");
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(450, 450));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cadastro de Problemas");

        jTFproblema.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFproblema.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Problema:");

        jCBcategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCBcategoria.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Categoria:");

        jTFid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFid.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("ID:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTFproblema, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTFid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jCBcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFproblema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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
                .addComponent(jBTsalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTexcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBTcancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableProblemas.setBackground(new java.awt.Color(204, 204, 204));
        jTableProblemas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableProblemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProblemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProblemasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableProblemas);
        if (jTableProblemas.getColumnModel().getColumnCount() > 0) {
            jTableProblemas.getColumnModel().getColumn(0).setMinWidth(55);
            jTableProblemas.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableProblemas.getColumnModel().getColumn(0).setMaxWidth(100);
            jTableProblemas.getColumnModel().getColumn(1).setMinWidth(200);
            jTableProblemas.getColumnModel().getColumn(1).setPreferredWidth(500);
            jTableProblemas.getColumnModel().getColumn(1).setMaxWidth(500);
            jTableProblemas.getColumnModel().getColumn(2).setMinWidth(200);
            jTableProblemas.getColumnModel().getColumn(2).setPreferredWidth(450);
            jTableProblemas.getColumnModel().getColumn(2).setMaxWidth(500);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(492, 466));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableProblemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProblemasMouseClicked
        int ra;
        ra = (int) jTableProblemas.getModel().getValueAt(jTableProblemas.getSelectedRow(),0);
        jTFid.setText(String.valueOf(jTableProblemas.getModel().getValueAt(jTableProblemas.getSelectedRow(),0)));
        jTFproblema.setText((String) jTableProblemas.getModel().getValueAt(jTableProblemas.getSelectedRow(),1));
        jCBcategoria.setSelectedItem(jTableProblemas.getModel().getValueAt(jTableProblemas.getSelectedRow(),2));
        //editar();
        jBTeditar.setEnabled(true);
        jBTnovo.setEnabled(false);
        
         // TODO add your handling code here:
    }//GEN-LAST:event_jTableProblemasMouseClicked

    private void jBTcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTcancelarActionPerformed
        cancelar();
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTcancelarActionPerformed

    private void jBTsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTsalvarActionPerformed
        
        // Consulta categorias e pega o id
        DAOCatProblemas catprob = new DAOCatProblemas(); 
        String cat = String.valueOf(jCBcategoria.getSelectedItem());
        int id = catprob.consultaCatProblemas(cat).getId();
        System.out.println("ID recuperado: "+ id);
        
        DAOProblemas daoprob = new DAOProblemas(); 
        Problemas prob = new Problemas();
        
        // Insere os valores no banco
                
        if (jTFid.getText().isEmpty()){
            System.out.println("entrou no inserir");
            prob.setNome(jTFproblema.getText());
            prob.setCat_problemas_cat_prob_id(id);
            prob.setCategoria(String.valueOf(jCBcategoria.getSelectedItem()));
            daoprob.inserirProblemas(prob);
            JOptionPane.showMessageDialog(null, "Problema salvo com sucesso!");
        }else{
            System.out.println("entrou no update");
            prob.setNome(jTFproblema.getText());
            prob.setCat_problemas_cat_prob_id(id);
            prob.setId(Integer.parseInt(jTFid.getText()));
            prob.setCategoria(String.valueOf(jCBcategoria.getSelectedItem()));
            daoprob.updateProblemas(prob);
            JOptionPane.showMessageDialog(null, "Problema atualizado com sucesso!");
        }
        cancelar();
        carregaProblemas();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTsalvarActionPerformed

    private void jBTnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTnovoActionPerformed
        editar();
        clear();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTnovoActionPerformed

    private void jBTexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTexcluirActionPerformed
        
        DAOProblemas daoprob = new DAOProblemas(); 
        String msg = "Tem certeza de deseja excluir o Problema "+jTFproblema.getText()+" ?";
        int result = JOptionPane.showConfirmDialog(null, msg, "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);
        // Excluir os valores no banco
        if(result == JOptionPane.YES_OPTION){
            daoprob.exluirProblemas(Integer.parseInt(jTFid.getText()));
            cancelar();
            carregaProblemas();
            JOptionPane.showMessageDialog(null, "Problema excluído com sucesso!");
        }else{
            JOptionPane.showMessageDialog(null, "Ação Cancelada!");
        }
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jBTexcluirActionPerformed

    private void jBTeditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTeditarActionPerformed
        editar();
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTeditarActionPerformed

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
            java.util.logging.Logger.getLogger(CadProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadProblemas().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTcancelar;
    private javax.swing.JButton jBTeditar;
    private javax.swing.JButton jBTexcluir;
    private javax.swing.JButton jBTnovo;
    private javax.swing.JButton jBTsalvar;
    private javax.swing.JComboBox<String> jCBcategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFid;
    private javax.swing.JTextField jTFproblema;
    private javax.swing.JTable jTableProblemas;
    // End of variables declaration//GEN-END:variables

    public void carregaProblemas(){
        /**  Carrega Tabela Problemas*/
        
        DAOProblemas daoprob = new DAOProblemas();
        Problemas prob;
        List<Problemas> listaprob; 
        listaprob = daoprob.listaProblemas();
        
        DefaultTableModel model = (DefaultTableModel) jTableProblemas.getModel();
        model.setNumRows(0);
        jTableProblemas.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTableProblemas.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTableProblemas.getColumnModel().getColumn(2).setPreferredWidth(50);
        for(int i = 0; i<listaprob.size(); i++){
           prob = listaprob.get(i);
           model.addRow(new Object[] {prob.getId(), prob.getNome(), prob.getCategoria()});
           
        }
    }
    
    public void carregarCatProblemas(){
        /**  Carrega ComboBox Problemas*/
        
        DAOCatProblemas daocatprob = new DAOCatProblemas();
        CatProblemas catprob;
        List<CatProblemas> listacatprob; 
        listacatprob = daocatprob.listaCatProblemas();
 
        for(int i = 0; i<listacatprob.size(); i++){
           catprob = listacatprob.get(i);
           jCBcategoria.addItem(catprob.getNome());
           
        }
    }
    
    public void editar(){
        jTFid.setEnabled(false);
        jTFproblema.setEnabled(true);
        jBTsalvar.setEnabled(true);
        jBTcancelar.setEnabled(true);
        jBTnovo.setEnabled(false);
        jBTexcluir.setEnabled(true);
        jCBcategoria.setEnabled(true);
        jBTeditar.setEnabled(false);
    }
    
    public void cancelar(){
        jTFid.setEnabled(false);
        jTFproblema.setEnabled(false);
        jBTsalvar.setEnabled(false);
        jBTcancelar.setEnabled(false);
        jBTnovo.setEnabled(true);
        jBTexcluir.setEnabled(false);
        jCBcategoria.setEnabled(false);
        jBTeditar.setEnabled(false);
    }
    
    public void clear(){
        jTFid.setText(null);
        jTFproblema.setText(null);
    }
}
