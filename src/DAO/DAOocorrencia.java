/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Ocorrencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOocorrencia extends Conexao{
    
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public Ocorrencia inserirOcorrencia (Ocorrencia oco, String status, String prob){
        ResultSet rs;
        Ocorrencia ocoret = new Ocorrencia();
        //sSql = "INSERT INTO ocorrencia (oco_descricao, oco_data_abertura, oco_data_fechamento, usuarios_usu_id, status_stat_id, problemas_prob_id, ativos_atv_id) VALUES (?,?,?,?,?,?,?,?)";
        sSql = "INSERT INTO ocorrencia "
                + "SET oco_descricao = ?, "
                + "oco_data_abertura = ?, "
                + "usuarios_usu_id = ?, "
                + "status_stat_id = (SELECT stat_id FROM status WHERE stat_status = ?), "
                + "problemas_prob_id = (SELECT prob_id FROM problemas WHERE prob_nome = ?), "
                + "ativos_atv_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,oco.getDescricao());
            pstmt.setTimestamp(2, (Timestamp) oco.getDataabertura());
            pstmt.setInt(3,oco.getUsuario_usu_id());
            pstmt.setString(4, status);
            pstmt.setString(5, prob);
            pstmt.setInt(6,oco.getAtivos_atv_id());
            
            pstmt.executeUpdate();
            
                                
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Ocorrencia "+erro.getMessage());
        }
        
        try {
            
            con = getConexao();
            
            pstmt = con.prepareStatement("SELECT oco_id, oco_descricao, oco_data_abertura, "
                    + "oco_data_fechamento, usuarios_usu_id, status_stat_id, stat_status, ativos_atv_id, "
                    + "prob_nome, cat_prob_nome, problemas_prob_id FROM ocorrencia "
                    + "INNER JOIN problemas p on problemas_prob_id = p.prob_id "
                    + "INNER JOIN cat_problemas c on p.cat_problemas_cat_prob_id = c.cat_prob_id "
                    + "INNER JOIN status s on status_stat_id = stat_id "
                    + "ORDER BY oco_data_abertura DESC LIMIT 1");
            
            rs = pstmt.executeQuery();
            if(rs.next()){
                ocoret.setId(rs.getInt("oco_id"));
                ocoret.setDescricao(rs.getString("oco_descricao"));
                ocoret.setDataabertura(rs.getTimestamp("oco_data_abertura"));
                ocoret.setDatafechamento(rs.getTimestamp("oco_data_fechamento"));
                ocoret.setUsuario_usu_id(rs.getInt("usuarios_usu_id"));
                ocoret.setStatus_stat_id(rs.getInt("status_stat_id"));
                ocoret.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                ocoret.setAtivos_atv_id(rs.getInt("ativos_atv_id"));
                ocoret.setProblema(rs.getString("prob_nome"));
                ocoret.setCatproblema(rs.getString("cat_prob_nome"));
                ocoret.setStatus(rs.getString("stat_status"));
            }else{
                ocoret = null;
                System.out.println("Ocorrencia não encontrada !!! ");
            }
            pstmt.close();
            con.close();    
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a nova Ocorrencia "+e.getMessage());
        }
        return ocoret;
    }
    
    public Ocorrencia updateStatus (int idoco, int idstat, int idatendente){
        ResultSet rs;
        Ocorrencia ocoret = new Ocorrencia();
        //sSql = "INSERT INTO ocorrencia (oco_descricao, oco_data_abertura, oco_data_fechamento, usuarios_usu_id, status_stat_id, problemas_prob_id, ativos_atv_id) VALUES (?,?,?,?,?,?,?,?)";
        sSql = "UPDATE ocorrencia SET status_stat_id = ?, id_atendente = ? WHERE oco_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1,idstat);
            pstmt.setInt(2, idatendente);
            pstmt.setInt(3, idoco);
                       
            pstmt.executeUpdate();
            
                                
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar Status da Ocorrencia "+erro.getMessage());
        }
        
        try {
            
            con = getConexao();
            pstmt = null;
            pstmt = con.prepareStatement("SELECT oco_id, oco_descricao, oco_data_abertura, "
                    + "oco_data_fechamento, id_atendente, usuarios_usu_id, "
                    + "status_stat_id, stat_status, ativos_atv_id, prob_nome, cat_prob_nome, "
                    + "problemas_prob_id FROM ocorrencia o "
                    + "INNER JOIN problemas p on problemas_prob_id = p.prob_id "
                    + "INNER JOIN cat_problemas c on p.cat_problemas_cat_prob_id = c.cat_prob_id "
                    + "INNER JOIN status s on status_stat_id = stat_id "
                    + "WHERE o.oco_id = ?");
            pstmt.setInt(1, idoco);
            
            rs = pstmt.executeQuery();
            if(rs.next()){
                ocoret.setId(rs.getInt("oco_id"));
                ocoret.setDescricao(rs.getString("oco_descricao"));
                ocoret.setDataabertura(rs.getTimestamp("oco_data_abertura"));
                ocoret.setDatafechamento(rs.getTimestamp("oco_data_fechamento"));
                ocoret.setUsuario_usu_id(rs.getInt("usuarios_usu_id"));
                ocoret.setStatus_stat_id(rs.getInt("status_stat_id"));
                ocoret.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                ocoret.setAtivos_atv_id(rs.getInt("ativos_atv_id"));
                ocoret.setProblema(rs.getString("prob_nome"));
                ocoret.setCatproblema(rs.getString("cat_prob_nome"));
                ocoret.setStatus(rs.getString("stat_status"));
                System.out.println("status no dao: "+rs.getString("stat_status"));
            }else{
                ocoret = null;
                System.out.println("Ocorrencia não encontrada !!! ");
            }
            pstmt.close();
            con.close();    
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a nova Ocorrencia "+e.getMessage());
        }
        return ocoret;
    }
    
    public Ocorrencia updateStatus (int idoco, int idstat, int idatendente, Timestamp data){
        ResultSet rs;
        Ocorrencia ocoret = new Ocorrencia();
        //sSql = "INSERT INTO ocorrencia (oco_descricao, oco_data_abertura, oco_data_fechamento, usuarios_usu_id, status_stat_id, problemas_prob_id, ativos_atv_id) VALUES (?,?,?,?,?,?,?,?)";
        sSql = "UPDATE ocorrencia SET status_stat_id = ?, id_atendente = ?, oco_data_fechamento = ? WHERE oco_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1,idstat);
            pstmt.setInt(2, idatendente);
            pstmt.setTimestamp(3, data);
            pstmt.setInt(4, idoco);            
                       
            pstmt.executeUpdate();
            
                                
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar Status da Ocorrencia "+erro.getMessage());
        }
        
        try {
            
            con = getConexao();
            pstmt = null;
            pstmt = con.prepareStatement("SELECT oco_id, oco_descricao, oco_data_abertura, "
                    + "oco_data_fechamento, id_atendente, usuarios_usu_id, "
                    + "status_stat_id, stat_status, ativos_atv_id, prob_nome, cat_prob_nome, "
                    + "problemas_prob_id FROM ocorrencia o "
                    + "INNER JOIN problemas p on problemas_prob_id = p.prob_id "
                    + "INNER JOIN cat_problemas c on p.cat_problemas_cat_prob_id = c.cat_prob_id "
                    + "INNER JOIN status s on status_stat_id = stat_id "
                    + "WHERE o.oco_id = ?");
            pstmt.setInt(1, idoco);
            
            rs = pstmt.executeQuery();
            if(rs.next()){
                ocoret.setId(rs.getInt("oco_id"));
                ocoret.setDescricao(rs.getString("oco_descricao"));
                ocoret.setDataabertura(rs.getTimestamp("oco_data_abertura"));
                ocoret.setDatafechamento(rs.getTimestamp("oco_data_fechamento"));
                ocoret.setUsuario_usu_id(rs.getInt("usuarios_usu_id"));
                ocoret.setStatus_stat_id(rs.getInt("status_stat_id"));
                ocoret.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                ocoret.setAtivos_atv_id(rs.getInt("ativos_atv_id"));
                ocoret.setProblema(rs.getString("prob_nome"));
                ocoret.setCatproblema(rs.getString("cat_prob_nome"));
                ocoret.setStatus(rs.getString("stat_status"));
                System.out.println("status no dao: "+rs.getString("stat_status"));
            }else{
                ocoret = null;
                System.out.println("Ocorrencia não encontrada !!! ");
            }
            pstmt.close();
            con.close();    
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a nova Ocorrencia "+e.getMessage());
        }
        return ocoret;
    }
    
    
    public Ocorrencia consultarOcorrencia(int id){
        
        //sSql = "SELECT * FROM ocorrencia WHERE oco_id = ?";
        sSql = "SELECT oco_id, oco_data_abertura, status_stat_id, stat_status, usuarios_usu_id, usu_nome, ativos_atv_id, "
                + "cat_prob_nome, prob_nome, problemas_prob_id, oco_descricao, oco_data_fechamento, id_atendente "
                + "FROM ocorrencia INNER JOIN status ON status_stat_id = stat_id "
                + "INNER JOIN usuarios on usuarios_usu_id = usu_id "
                + "INNER JOIN problemas on problemas_prob_id = prob_id "
                + "INNER JOIN cat_problemas on cat_problemas_cat_prob_id = cat_prob_id "
                + "WHERE oco_id = ?";
        
        ResultSet rs;
        Ocorrencia oco = new Ocorrencia();
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                oco.setId(rs.getInt("oco_id"));
                oco.setId(rs.getInt("oco_id"));
                oco.setDescricao(rs.getString("oco_descricao"));
                oco.setDataabertura(rs.getTimestamp("oco_data_abertura"));
                oco.setDatafechamento(rs.getTimestamp("oco_data_fechamento"));
                oco.setUsuario_usu_id(rs.getInt("usuarios_usu_id"));
                oco.setStatus_stat_id(rs.getInt("status_stat_id"));
                oco.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                oco.setAtivos_atv_id(rs.getInt("ativos_atv_id"));
                oco.setUsuario(rs.getString("usu_nome"));
                oco.setCatproblema(rs.getString("cat_prob_nome"));
                oco.setProblema(rs.getString("prob_nome"));
                oco.setStatus(rs.getString("stat_status"));
                oco.setId_atendente(rs.getInt("id_atendente"));
                
                //System.out.println("id no rs "+rs.getInt("id_atendente") );
            }else{
               oco = null;
                System.out.println("Ocorrencia não encontrada ");
            }
            
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Ocorrencia "+erro.getMessage());
        }
        return oco;
    }
    
    public List<Ocorrencia> listaOcorrencia(){
        List listaoco = new ArrayList();
        ResultSet rs;
        Ocorrencia oco;
        //sSql = "SELECT * FROM ocorrencia";
        sSql = "SELECT oco_id, oco_data_abertura, status_stat_id, stat_status, usuarios_usu_id, usu_nome, ativos_atv_id, "
                + "cat_prob_nome, prob_nome, problemas_prob_id, oco_descricao, oco_data_fechamento, id_atendente "
                + "FROM ocorrencia INNER JOIN status ON status_stat_id = stat_id "
                + "INNER JOIN usuarios on usuarios_usu_id = usu_id "
                + "INNER JOIN problemas on problemas_prob_id = prob_id "
                + "INNER JOIN cat_problemas on cat_problemas_cat_prob_id = cat_prob_id ";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                oco = new Ocorrencia();
                oco.setId(rs.getInt("oco_id"));
                oco.setDescricao(rs.getString("oco_descricao"));
                oco.setDataabertura(rs.getTimestamp("oco_data_abertura"));
                oco.setDatafechamento(rs.getTimestamp("oco_data_fechamento"));
                oco.setUsuario_usu_id(rs.getInt("usuarios_usu_id"));
                oco.setStatus_stat_id(rs.getInt("status_stat_id"));
                oco.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                oco.setAtivos_atv_id(rs.getInt("ativos_atv_id"));
                oco.setUsuario(rs.getString("usu_nome"));
                oco.setCatproblema(rs.getString("cat_prob_nome"));
                oco.setProblema(rs.getString("prob_nome"));
                oco.setStatus(rs.getString("stat_status"));
                oco.setId_atendente(rs.getInt("id_atendente"));
                
                listaoco.add(oco);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar lista de Ocorrencias "+erro.getMessage());
        }
        return listaoco;
    }
    
    public List<Ocorrencia> listaOcorrencia(int user){
        List listaoco = new ArrayList();
        ResultSet rs;
        Ocorrencia oco;
        //sSql = "SELECT * FROM ocorrencia";
        sSql = "SELECT oco_id, oco_data_abertura, status_stat_id, stat_status, usuarios_usu_id, usu_nome, ativos_atv_id, "
                + "cat_prob_nome, prob_nome, problemas_prob_id, oco_descricao, oco_data_fechamento, id_atendente "
                + "FROM ocorrencia INNER JOIN status ON status_stat_id = stat_id "
                + "INNER JOIN usuarios on usuarios_usu_id = usu_id "
                + "INNER JOIN problemas on problemas_prob_id = prob_id "
                + "INNER JOIN cat_problemas on cat_problemas_cat_prob_id = cat_prob_id "
                + "WHERE usuarios_usu_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, user);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                oco = new Ocorrencia();
                oco.setId(rs.getInt("oco_id"));
                oco.setDescricao(rs.getString("oco_descricao"));
                oco.setDataabertura(rs.getTimestamp("oco_data_abertura"));
                oco.setDatafechamento(rs.getTimestamp("oco_data_fechamento"));
                oco.setUsuario_usu_id(rs.getInt("usuarios_usu_id"));
                oco.setStatus_stat_id(rs.getInt("status_stat_id"));
                oco.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                oco.setAtivos_atv_id(rs.getInt("ativos_atv_id"));
                oco.setUsuario(rs.getString("usu_nome"));
                oco.setCatproblema(rs.getString("cat_prob_nome"));
                oco.setProblema(rs.getString("prob_nome"));
                oco.setStatus(rs.getString("stat_status"));
                oco.setId_atendente(rs.getInt("id_atendente"));
                
                listaoco.add(oco);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar lista de Ocorrencias "+erro.getMessage());
        }
        return listaoco;
    }
    
    public List<Ocorrencia> listaOcorrencia(int user, int stat){
        List listaoco = new ArrayList();
        ResultSet rs;
        Ocorrencia oco;
        //sSql = "SELECT * FROM ocorrencia";
        sSql = "SELECT oco_id, oco_data_abertura, status_stat_id, stat_status, usuarios_usu_id, usu_nome, ativos_atv_id, "
                + "cat_prob_nome, prob_nome, problemas_prob_id, oco_descricao, oco_data_fechamento, id_atendente "
                + "FROM ocorrencia INNER JOIN status ON status_stat_id = stat_id "
                + "INNER JOIN usuarios on usuarios_usu_id = usu_id "
                + "INNER JOIN problemas on problemas_prob_id = prob_id "
                + "INNER JOIN cat_problemas on cat_problemas_cat_prob_id = cat_prob_id "
                + "WHERE usuarios_usu_id = ? AND status_stat_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, user);
            pstmt.setInt(2, stat);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                oco = new Ocorrencia();
                oco.setId(rs.getInt("oco_id"));
                oco.setDescricao(rs.getString("oco_descricao"));
                oco.setDataabertura(rs.getTimestamp("oco_data_abertura"));
                oco.setDatafechamento(rs.getTimestamp("oco_data_fechamento"));
                oco.setUsuario_usu_id(rs.getInt("usuarios_usu_id"));
                oco.setStatus_stat_id(rs.getInt("status_stat_id"));
                oco.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                oco.setAtivos_atv_id(rs.getInt("ativos_atv_id"));
                oco.setUsuario(rs.getString("usu_nome"));
                oco.setCatproblema(rs.getString("cat_prob_nome"));
                oco.setProblema(rs.getString("prob_nome"));
                oco.setStatus(rs.getString("stat_status"));
                
                listaoco.add(oco);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar lista de Ocorrencias "+erro.getMessage());
        }
        return listaoco;
    }
    
    
    public List<Ocorrencia> listaOcorrenciastatus(int stat){
        List listaoco = new ArrayList();
        ResultSet rs;
        Ocorrencia oco;
        //sSql = "SELECT * FROM ocorrencia";
        sSql = "SELECT oco_id, oco_data_abertura, status_stat_id, stat_status, usuarios_usu_id, usu_nome, ativos_atv_id, "
                + "cat_prob_nome, prob_nome, problemas_prob_id, oco_descricao, oco_data_fechamento, id_atendente "
                + "FROM ocorrencia INNER JOIN status ON status_stat_id = stat_id "
                + "INNER JOIN usuarios on usuarios_usu_id = usu_id "
                + "INNER JOIN problemas on problemas_prob_id = prob_id "
                + "INNER JOIN cat_problemas on cat_problemas_cat_prob_id = cat_prob_id "
                + "WHERE status_stat_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, stat);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                oco = new Ocorrencia();
                oco.setId(rs.getInt("oco_id"));
                oco.setDescricao(rs.getString("oco_descricao"));
                oco.setDataabertura(rs.getTimestamp("oco_data_abertura"));
                oco.setDatafechamento(rs.getTimestamp("oco_data_fechamento"));
                oco.setUsuario_usu_id(rs.getInt("usuarios_usu_id"));
                oco.setStatus_stat_id(rs.getInt("status_stat_id"));
                oco.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                oco.setAtivos_atv_id(rs.getInt("ativos_atv_id"));
                oco.setUsuario(rs.getString("usu_nome"));
                oco.setCatproblema(rs.getString("cat_prob_nome"));
                oco.setProblema(rs.getString("prob_nome"));
                oco.setStatus(rs.getString("stat_status"));
                
                listaoco.add(oco);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar lista de Ocorrencias "+erro.getMessage());
        }
        return listaoco;
    }
}
