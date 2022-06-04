/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Knowledge;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOknowledge extends Conexao {
    
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public void adicionarKnowledge(Knowledge know){
        sSql = "INSERT INTO knowbase ("
                + "knb_codigo_erro, "
                + "knb_descricao, "
                + "knb_motivo, "
                + "knb_resolucao, "
                + "usuarios_usu_id, "
                + "problemas_prob_id)"
                + "VALUES(?,?,?,?,?,?)";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,know.getCodigo_erro());
            pstmt.setString(2,know.getDescricao());
            pstmt.setString(3,know.getMotivo());
            pstmt.setString(4,know.getResolucao());
            pstmt.setInt(5,know.getUsuarios_usu_id());
            pstmt.setInt(6,know.getProblemas_prob_id());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao adicionar Knowledge "+erro.getMessage());
        }     
    }
    
    public void excluirKnowledge (int id){
        sSql = "DELETE FROM knowbase WHERE knb_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareCall(sSql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir Knowledge "+erro.getMessage());
        }
    }
    
    public Knowledge consultaKnowledge(int id){
        ResultSet rs;
        Knowledge know = new Knowledge();
        sSql = "SELECT * FROM knowbase WHERE knb_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs =  pstmt.executeQuery();
            
            if(rs.next()){
                know.setId(rs.getInt("knb_id"));
                know.setCodigo_erro(rs.getString("knb_codigo_erro"));
                know.setDescricao(rs.getString("knb_descricao"));
                know.setMotivo(rs.getString("knb_motivo"));
                know.setResolucao(rs.getString("knb_resolucao"));
                know.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                know.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                
            }else{
                know = null;
                System.out.println("Knowledge não encontrado ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Knowledge base "+erro.getMessage());
        }
        return know;
    }
    
    public List<Knowledge> listaKnowledge(){
        List listaknow = new ArrayList();
        ResultSet rs;
        Knowledge know;
        sSql = "SELECT * FROM knowbase";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs =  pstmt.executeQuery();
            
            while(rs.next()){
                know = new Knowledge();
                know.setId(rs.getInt("knb_id"));
                know.setCodigo_erro(rs.getString("knb_codigo_erro"));
                know.setDescricao(rs.getString("knb_descricao"));
                know.setMotivo(rs.getString("knb_motivo"));
                know.setResolucao(rs.getString("knb_resolucao"));
                know.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                know.setProblemas_prob_id(rs.getInt("problemas_prob_id"));
                listaknow.add(know);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Knowledge base "+erro.getMessage());
        }
        return listaknow;
    }
    
}
