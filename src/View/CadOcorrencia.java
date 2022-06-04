/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.DAOCatProblemas;
import DAO.DAOProblemas;
import DAO.DAOatendimentos;
import DAO.DAOocorrencia;
import DAO.DAOusuarios;
import Model.Atendimentos;
import Model.CatProblemas;
import Model.Ocorrencia;
import Model.Problemas;
import Model.Sessao;
import Model.Usuarios;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Familia
 */
public class CadOcorrencia extends javax.swing.JFrame {

    /**
     * Creates new form Ocorrencia
     */
    Usuarios usersessao;
    public CadOcorrencia() {
        initComponents();
        carregarCatProb();
        try {
            //Pega o usuário que está logado;
            usersessao = Sessao.getInstance().getUsersessao();
            
            jTFusuario.setText(usersessao.getNome());
            jTFemail.setText(usersessao.getEmail());
            jFTtelefone.setText(usersessao.getTelefone());
            jTFramal.setText(usersessao.getRamal());
            jCBdepartamento.addItem(usersessao.getDepartamento());
            jCBdepartamento.setSelectedItem(usersessao.getDepartamento());
            
            if(usersessao.getPerfil_perf_id()== 3){
                jBTatender.setEnabled(false);
                jBTfinalizar.setEnabled(false);
                jBTcomentar.setEnabled(false);
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel localizar o usuário");
        }
        
    }

    public CadOcorrencia(Ocorrencia oco) {
        
        //Pega o usuário que está logado;); 
        Usuarios usersessao;
        usersessao = Sessao.getInstance().getUsersessao();
        
        initComponents();
        carregarCatProb();
               
        if(usersessao.getPerfil_perf_id()== 3){
                jBTatender.setEnabled(false);
                jBTfinalizar.setEnabled(false);
            }else{
                jBTcomentar.setEnabled(true);
            }
        if(usersessao.getPerfil_perf_id() != 3 && oco.getStatus_stat_id() == 1){
            System.out.println("Atendente não é usuário e status da ocorrencia é igual a 1");
            jBTatender.setEnabled(true);
            jBTfinalizar.setEnabled(false);
        }else if(usersessao.getPerfil_perf_id() != 3 && oco.getStatus_stat_id() != 5){
            System.out.println("Atendente não é usuário e status da ocorrencia é diferente de 1");
            jBTatender.setEnabled(false);
            jBTfinalizar.setEnabled(true);
            jBTcomentar.setEnabled(true);
        }else{
            jBTatender.setEnabled(false);
            jBTfinalizar.setEnabled(false);
            jBTcomentar.setEnabled(false);
        }
        try{
            // constrói a primeira data
            DateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataStart = (Date) oco.getDataabertura();
            Date dataEnd = (Date) oco.getDatafechamento();
            // constrói a segunda data
            //fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataatual = new Date();
            String data = fm.format(dataatual);
            Date dataNow = (Date)fm.parse(data);
            
            // Obtem a diferença em semanas, dias, horas, minutos e segundos
            long segundos = 0;
            if(dataEnd == null){
                segundos = (dataNow.getTime() - dataStart.getTime()) / 1000;
                
            }else{
                segundos = (dataEnd.getTime() - dataStart.getTime()) / 1000;
            }
            
            //int semanas = (int)Math.floor(segundos / 604800);
            //segundos -= semanas * 604800;
            int dias = (int)Math.floor(segundos / 86400);
            segundos -= dias * 86400;
            int horas = (int)Math.floor(segundos / 3600);
            segundos -= horas * 3600;
            int minutos = (int)Math.floor(segundos / 60);
            segundos -= minutos * 60;

            // exibe o resultado
            //System.out.println("As duas datas tem " +semanas + " semanas, " + dias + " dias, " + horas + " horas, " + minutos + " minutos e " +segundos + " segundos de diferença");
            System.out.println("As duas datas tem "+ dias + " dias, " + horas + ":" + minutos + ":" +segundos);
            
            jFTtempo.setText(String.valueOf(dias+", "+horas+":"+minutos+":"+segundos));
        }
          catch(ParseException e){
            e.printStackTrace();
              System.out.println("Erro ao calcular o tempo de chamado.");
        }
            
        try {
            jTFidoco.setText(String.valueOf(oco.getId()));
            jCBcategoria.setSelectedItem(oco.getCatproblema());
            jCBproblema.setSelectedItem(oco.getProblema());
            jTPdescricao.setText(oco.getDescricao());
            jTFidequipto.setText(String.valueOf(oco.getAtivos_atv_id()));
            jFTdataopen.setText(String.valueOf(oco.getDataabertura()));
            jFTdataclose.setText(String.valueOf(oco.getDatafechamento()));
            jTFstatus.setText(String.valueOf(oco.getStatus()));
            
            //Usuarios usersessao;
            //usersessao = Sessao.getInstance().getUsersessao();
            DAOusuarios daouser = new DAOusuarios();
            Usuarios user = new Usuarios();
            user = daouser.consultaUsuario(oco.getUsuario_usu_id());
            jTFusuario.setText(user.getNome());
            jTFemail.setText(user.getEmail());
            jFTtelefone.setText(user.getTelefone());
            jTFramal.setText(user.getRamal());
            jCBdepartamento.addItem(user.getDepartamento());
            jCBdepartamento.setSelectedItem(user.getDepartamento());
            
            jCBcategoria.setEnabled(false);
            jCBproblema.setEnabled(false);
            jCBdepartamento.setEnabled(false);
            jTPdescricao.setEnabled(false);
            jTFidequipto.setEnabled(false);
            jBTconfirmar.setEnabled(false);
            jBTcancelar.setEnabled(false);
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar valores: "+ e);
        }
        if(oco.getId_atendente() != 0){
        DAOusuarios daouser = new DAOusuarios();
        Usuarios user;
            System.out.println("id do atendente: "+oco.getId_atendente());
            int id = oco.getId_atendente();
            user = daouser.consultaUsuario(id);
            jTFatendente.setText(user.getNome());
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

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLcategoria = new javax.swing.JLabel();
        jCBcategoria = new javax.swing.JComboBox<>();
        jCBproblema = new javax.swing.JComboBox<>();
        jLproblema = new javax.swing.JLabel();
        jLdescricao = new javax.swing.JLabel();
        jLidoco = new javax.swing.JLabel();
        jTFidoco = new javax.swing.JTextField();
        jLequipto = new javax.swing.JLabel();
        jTFidequipto = new javax.swing.JTextField();
        jTFusuario = new javax.swing.JTextField();
        jLusuario = new javax.swing.JLabel();
        jLdepto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTFramal = new javax.swing.JTextField();
        jLemail = new javax.swing.JLabel();
        jTFemail = new javax.swing.JTextField();
        jLdataopen = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTFatendente = new javax.swing.JTextField();
        jLdataclose = new javax.swing.JLabel();
        jFTtempo = new javax.swing.JFormattedTextField();
        jFTdataclose = new javax.swing.JFormattedTextField();
        jTFstatus = new javax.swing.JTextField();
        jLstatus1 = new javax.swing.JLabel();
        jBTconfirmar = new javax.swing.JButton();
        jFTtelefone = new javax.swing.JFormattedTextField();
        jCBdepartamento = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jFTdataopen = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTPdescricao = new javax.swing.JTextPane();
        jBTcancelar = new javax.swing.JButton();
        jBTatender = new javax.swing.JButton();
        jBTfinalizar = new javax.swing.JButton();
        jBTcomentar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorico = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Abertura de Ocorrência");
        setResizable(false);

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nova Ocorrência"));

        jLcategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLcategoria.setText("Categoria do Problema:");

        jCBcategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCBcategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a Categoria" }));
        jCBcategoria.setToolTipText("Selecione uma categoria de problema");
        jCBcategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBcategoriaItemStateChanged(evt);
            }
        });

        jCBproblema.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCBproblema.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um Problema" }));
        jCBproblema.setToolTipText("Selecione um Problema");
        jCBproblema.setEnabled(false);

        jLproblema.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLproblema.setText("Problema:");

        jLdescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLdescricao.setText("Descrição:");

        jLidoco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLidoco.setText("ID Ocorrência:");

        jTFidoco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFidoco.setEnabled(false);

        jLequipto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLequipto.setText("ID do Equipto.:");

        jTFidequipto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTFusuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFusuario.setEnabled(false);

        jLusuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLusuario.setText("Usuário:");

        jLdepto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLdepto.setText("Departamento:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Telefone:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Ramal:");

        jTFramal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFramal.setEnabled(false);

        jLemail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLemail.setText("E-mail:");

        jTFemail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFemail.setEnabled(false);

        jLdataopen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLdataopen.setText("Data Abertura:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Técnico:");

        jTFatendente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFatendente.setEnabled(false);

        jLdataclose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLdataclose.setText("Data Fechamento:");

        jFTtempo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(""))));
        jFTtempo.setEnabled(false);
        jFTtempo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jFTdataclose.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss"))));
        jFTdataclose.setEnabled(false);
        jFTdataclose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTFstatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTFstatus.setEnabled(false);

        jLstatus1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLstatus1.setText("Status:");

        jBTconfirmar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBTconfirmar.setText("Confirmar");
        jBTconfirmar.setMinimumSize(new java.awt.Dimension(80, 30));
        jBTconfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTconfirmarActionPerformed(evt);
            }
        });

        try {
            jFTtelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTtelefone.setEnabled(false);
        jFTtelefone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCBdepartamento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCBdepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Departamento" }));
        jCBdepartamento.setEnabled(false);

        jLabel1.setText("Tempo de vida:");

        jFTdataopen.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss"))));
        jFTdataopen.setEnabled(false);
        jFTdataopen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTPdescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(jTPdescricao);

        jBTcancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBTcancelar.setText("Cancelar");
        jBTcancelar.setMinimumSize(new java.awt.Dimension(80, 30));

        jBTatender.setText("Atender");
        jBTatender.setEnabled(false);
        jBTatender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTatenderActionPerformed(evt);
            }
        });

        jBTfinalizar.setText("Finalizar");
        jBTfinalizar.setEnabled(false);
        jBTfinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTfinalizarActionPerformed(evt);
            }
        });

        jBTcomentar.setText("Comentar");
        jBTcomentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTcomentarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLidoco, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLcategoria, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLdescricao, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLequipto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLusuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLemail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLstatus1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLdataopen, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFusuario)
                                    .addComponent(jTFemail)
                                    .addComponent(jTFstatus)
                                    .addComponent(jFTdataopen)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTFidoco, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCBcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 79, Short.MAX_VALUE))
                                    .addComponent(jTFidequipto))
                                .addGap(44, 44, 44))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jBTconfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBTatender)
                                .addGap(18, 18, 18)
                                .addComponent(jBTcomentar)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLdepto))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jFTtelefone)
                                        .addComponent(jCBdepartamento, 0, 185, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTFramal)
                                        .addComponent(jTFatendente, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLdataclose)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jFTdataclose, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLproblema))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCBproblema, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFTtempo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jBTfinalizar)
                                .addGap(18, 18, 18)
                                .addComponent(jBTcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLidoco)
                    .addComponent(jTFidoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jFTtempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLcategoria)
                    .addComponent(jCBcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLproblema)
                    .addComponent(jCBproblema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLdescricao)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLequipto)
                            .addComponent(jTFidequipto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLusuario)
                            .addComponent(jTFusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLdepto)
                            .addComponent(jCBdepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jFTtelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLemail)
                    .addComponent(jTFemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFramal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLstatus1)
                        .addComponent(jTFstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTFatendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLdataopen)
                    .addComponent(jLdataclose)
                    .addComponent(jFTdataclose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTdataopen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTconfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTatender)
                    .addComponent(jBTfinalizar)
                    .addComponent(jBTcomentar))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ocorrência", jPanel3);

        jTableHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Data", "Usuário / Têcnico", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableHistorico);
        if (jTableHistorico.getColumnModel().getColumnCount() > 0) {
            jTableHistorico.getColumnModel().getColumn(0).setMinWidth(30);
            jTableHistorico.getColumnModel().getColumn(1).setMinWidth(80);
            jTableHistorico.getColumnModel().getColumn(2).setMinWidth(100);
            jTableHistorico.getColumnModel().getColumn(3).setMinWidth(500);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Histórico", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        setSize(new java.awt.Dimension(840, 594));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCBcategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBcategoriaItemStateChanged
        carregaProblemas();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBcategoriaItemStateChanged

    private void jBTconfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTconfirmarActionPerformed
        if(jCBcategoria.getSelectedIndex() == 0 ){
            JOptionPane.showMessageDialog(rootPane, "Selecione uma Categoria!");
        }else if(jCBproblema.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(rootPane, "Selecione um Problema");
        }else if(jTFidequipto.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Informe o ID do Equipamento!");
        }else if(jTPdescricao.getText().length() < 30){
            JOptionPane.showMessageDialog(rootPane, "O Campo Descrição deve conter pelo menos 30 caracteres!");
        }else{
        /** Pega usuario logado*/
            Usuarios usersessao;
            usersessao = Sessao.getInstance().getUsersessao();
        /** Istancia a Ocorrencia */
            DAOocorrencia daooco = new DAOocorrencia();
            Ocorrencia oco = new Ocorrencia(); 
            Ocorrencia ocoret = new Ocorrencia();
            
            //java.sql.Date - pega data //java.sql.Time - pega hora //java.sql.Timestamp - pega data e hora
            java.util.Date dt = new java.util.Date();
            Timestamp date = new java.sql.Timestamp(dt.getTime()); 
                        
            System.out.println("Data = "+date);
            //oco.setProblemas_prob_id(prob.getId());
            oco.setDescricao(jTPdescricao.getText());
            oco.setAtivos_atv_id(Integer.parseInt(jTFidequipto.getText()));
            oco.setDataabertura(date);
            oco.setUsuario_usu_id(usersessao.getId());
            try {
                ocoret = daooco.inserirOcorrencia(oco, "Aguardando Atendimento", String.valueOf(jCBproblema.getSelectedItem()));
                JOptionPane.showMessageDialog(rootPane, "Ocorrencia gravada com sucesso!");
                jTFidoco.setText(String.valueOf(ocoret.getId()));
                jCBcategoria.setSelectedItem(ocoret.getCatproblema());
                jCBproblema.setSelectedItem(ocoret.getProblema());
                jTPdescricao.setText(ocoret.getDescricao());
                jTFidequipto.setText(String.valueOf(ocoret.getAtivos_atv_id()));
                               
                //jFTclose.setText(String.valueOf((ocoret.getDataabertura()- date)));
                //Usuarios usersessao;
                usersessao = Sessao.getInstance().getUsersessao();
                jTFusuario.setText(usersessao.getNome());
                jTFemail.setText(usersessao.getEmail());
                jFTtelefone.setText(usersessao.getTelefone());
                jTFramal.setText(usersessao.getRamal());
                jCBdepartamento.addItem(usersessao.getDepartamento());
                jCBdepartamento.setSelectedItem(usersessao.getDepartamento());
                jTPdescricao.setEnabled(false);
                jTFidequipto.setEnabled(false);
                jCBcategoria.setEnabled(false);
                jCBproblema.setEnabled(false);
                /*dispose();
                CadOcorrencia cadoco = new CadOcorrencia(ocoret);
                cadoco.setVisible(true);*/
            } catch (Exception erro) {
                System.out.println("Erro ao gravar Ocorrencia!");
            }
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTconfirmarActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        System.out.println("CadOcorrencias jTabbedPane1FocusGained-------");
        DAOatendimentos daoatd = new DAOatendimentos();
        List<Atendimentos> listatd = new ArrayList();
        Atendimentos atd;
        listatd = daoatd.listaAtendimentos(Integer.parseInt(jTFidoco.getText()));
        
        DefaultTableModel model = (DefaultTableModel) jTableHistorico.getModel();
        model.setNumRows(0);
        
        for(int i = 0; i < listatd.size(); i++){
            atd = listatd.get(i);
            model.addRow(new Object[]{atd.getId(), atd.getData_atendimento(), atd.getUsuario(), atd.getDescricao()});
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jBTatenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTatenderActionPerformed
        int resp = JOptionPane.showConfirmDialog(rootPane, "Confirma o Atendimento:", "Confirmação",JOptionPane.YES_NO_OPTION);
        System.out.println("resp= "+resp);
        
        usersessao = Sessao.getInstance().getUsersessao();
        if(resp == 0){
            System.out.println("atualizar o status ----------");
            DAOatendimentos daoatd = new DAOatendimentos();
            Atendimentos atd = new Atendimentos();
            
            //registrar atendimento
            DAOocorrencia daooco = new DAOocorrencia();
            Ocorrencia ocoret = new Ocorrencia();
            System.out.println("ocorrencias");
            //update status da ocorrencia
            int idoco, iduser;
            idoco = Integer.parseInt(jTFidoco.getText());            
            iduser = usersessao.getId();
            //try {
                
                ocoret = daooco.updateStatus(idoco, 2, iduser );
                
            /*} 
            catch (Exception e) {
                System.out.println("Erro ao atualizar: "+e);
            }*/
            //try {
                java.util.Date dt = new java.util.Date();
                Timestamp date = new java.sql.Timestamp(dt.getTime()); 
                atd.setData_atendimento(date);
                atd.setDescricao("Em Atendimento");
                atd.setUsusarios_usu_id(iduser);
                atd.setOcorrencia_oco_id(idoco);
                daoatd.adicionarAtendimento(atd);
            //} catch (Exception e) {
            //}
            jTFstatus.setText(ocoret.getStatus());
            jTFatendente.setText(usersessao.getNome());
            jBTatender.setEnabled(false);
        }   
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTatenderActionPerformed

    private void jBTcomentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTcomentarActionPerformed
        
        int idoco = Integer.parseInt(jTFidoco.getText()); 
        Comentario coment = new Comentario(idoco);
        coment.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTcomentarActionPerformed

    private void jBTfinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTfinalizarActionPerformed
        
        usersessao = Sessao.getInstance().getUsersessao();        
        int idoco = Integer.parseInt(jTFidoco.getText());        
        EncerrarOco encoco = new EncerrarOco(idoco);        
        encoco.setVisible(true);
        dispose();
        /*
        usersessao = Sessao.getInstance().getUsersessao();
        DAOatendimentos daoatd = new DAOatendimentos();
        Atendimentos atd = new Atendimentos();
        DAOocorrencia daooco = new DAOocorrencia();
        Ocorrencia oco = new Ocorrencia();
        Ocorrencia ocoret = new Ocorrencia();
        
        String msg = JOptionPane.showInputDialog("Descreva o atendimento: ");
        try {
            int id = Integer.parseInt(jTFidoco.getText());
            System.out.println("Mensagem = "+msg);
            //Gera data em Timestamp
            java.util.Date dtm = new java.util.Date();
            Timestamp datetm = new java.sql.Timestamp(dtm.getTime());
 
            System.out.println("date = "+datetm);
            atd.setData_atendimento(datetm);
            atd.setDescricao("Em Atendimento");
            atd.setUsusarios_usu_id(usersessao.getId());
            atd.setOcorrencia_oco_id(id);
            daoatd.adicionarAtendimento(atd);
            
            int idoc;
            int iduser;
            System.out.println("id da ocorrencia: "+jTFidoco.getText());
            idoc = Integer.parseInt(jTFidoco.getText());            
            iduser = usersessao.getId();
            ocoret = daooco.updateStatus(idoc, 5, iduser, datetm);
        } catch (Exception e) {
        }            
        
        try {
            Atendimentos atds = new Atendimentos();
            //Gera data em Timestamp
            java.util.Date dtm = new java.util.Date();
            Timestamp datetm = new java.sql.Timestamp(dtm.getTime());
            
            atds.setData_atendimento(datetm);
            atds.setDescricao("Atendimento Finalizado");
            atds.setUsusarios_usu_id(usersessao.getId());
            atds.setOcorrencia_oco_id(Integer.parseInt(jTFidoco.getText()));
            daoatd.adicionarAtendimento(atds);
        } catch (Exception e) {
            System.out.println("Erro "+e);
        }
        jTFstatus.setText(ocoret.getStatus());
        jTFatendente.setText(usersessao.getNome());
        jBTatender.setEnabled(false);
        jBTfinalizar.setEnabled(false);
        */
// TODO add your handling code here:
    }//GEN-LAST:event_jBTfinalizarActionPerformed

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
            java.util.logging.Logger.getLogger(CadOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadOcorrencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTatender;
    private javax.swing.JButton jBTcancelar;
    private javax.swing.JButton jBTcomentar;
    private javax.swing.JButton jBTconfirmar;
    private javax.swing.JButton jBTfinalizar;
    private javax.swing.JComboBox<String> jCBcategoria;
    private javax.swing.JComboBox<String> jCBdepartamento;
    private javax.swing.JComboBox<String> jCBproblema;
    private javax.swing.JFormattedTextField jFTdataclose;
    private javax.swing.JFormattedTextField jFTdataopen;
    private javax.swing.JFormattedTextField jFTtelefone;
    private javax.swing.JFormattedTextField jFTtempo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLcategoria;
    private javax.swing.JLabel jLdataclose;
    private javax.swing.JLabel jLdataopen;
    private javax.swing.JLabel jLdepto;
    private javax.swing.JLabel jLdescricao;
    private javax.swing.JLabel jLemail;
    private javax.swing.JLabel jLequipto;
    private javax.swing.JLabel jLidoco;
    private javax.swing.JLabel jLproblema;
    private javax.swing.JLabel jLstatus1;
    private javax.swing.JLabel jLusuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFatendente;
    private javax.swing.JTextField jTFemail;
    private javax.swing.JTextField jTFidequipto;
    private javax.swing.JTextField jTFidoco;
    private javax.swing.JTextField jTFramal;
    private javax.swing.JTextField jTFstatus;
    private javax.swing.JTextField jTFusuario;
    private javax.swing.JTextPane jTPdescricao;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableHistorico;
    // End of variables declaration//GEN-END:variables

public void carregarCatProb(){
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

public void carregaProblemas(){
        /**  Carrega ComboBox Problemas*/
        
        DAOProblemas daoprob = new DAOProblemas();
        Problemas prob;
        List<Problemas> listaprob; 
        listaprob = daoprob.listaProblemasCat(String.valueOf(jCBcategoria.getSelectedItem()));
        jCBproblema.removeAllItems();
        if(jCBcategoria.getSelectedItem().equals("Selecione a Categoria")){
            jCBproblema.addItem("Selecione o Problema");
            jCBproblema.setEnabled(false);
        }else{
            jCBproblema.setEnabled(true);
            jCBproblema.addItem("Selecione o Problema");
            for(int i = 0; i < listaprob.size(); i++){
                prob = listaprob.get(i);
                jCBproblema.addItem(prob.getNome());
            }
        }
    }


}
