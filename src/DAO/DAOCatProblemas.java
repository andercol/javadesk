/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.CatProblemas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOCatProblemas extends Conexao{
    
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public void inserirCatProblemas(CatProblemas catp){
        sSql = "INSERT INTO cat_problemas (cat_prob_nome) VALUES (?)";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,catp.getNome());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            
            System.out.println("Erro ao inserir Categoria de Problemas " +erro.getMessage());
            
        }
    }
    
    public void updateCatProblemas(CatProblemas catp){
        sSql = "UPDATE cat_problemas SET cat_prob_nome = ? WHERE cat_prob_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,catp.getNome());
            pstmt.setInt(2, catp.getId());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            
            System.out.println("Erro ao atualizar Categoria de Problemas " +erro.getMessage());
            
        }
    }
    
    public CatProblemas consultaCatProblemas(int id){
        ResultSet rs;
        CatProblemas catp = new CatProblemas();
        sSql = "SELECT * FROM cat_problemas WHERE cat_prob_id = ?";
        
        try {
            con = getConexao();
            pstmt =  con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                catp.setId(rs.getInt("cat_prob_id"));
                catp.setNome(rs.getString("cat_prob_id"));
            }else{
                catp = null;
                System.out.println("Categoria não encontrada ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Categoria de Problemas ");
        }
        return catp;
    }
    
    public void exluirCatProblemas(int id){
        sSql = "DELETE FROM cat_problemas WHERE cat_prob_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
                    
        } catch (SQLException erro) {
            System.out.println("Erro ao Excluir Categoria de Problemas "+erro.getMessage());
        }
    }
    
    public List<CatProblemas> listaCatProblemas(){
        List listcatprob = new ArrayList();
        CatProblemas catp;
        ResultSet rs;
        sSql = "SELECT * FROM cat_problemas";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                catp = new CatProblemas();
                catp.setId(rs.getInt("cat_prob_id"));
                catp.setNome(rs.getString("cat_prob_nome"));
                listcatprob.add(catp);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar lista de Categoria de problemas "+erro.getMessage());
        }
        return listcatprob;
    }
    
    
    public CatProblemas consultaCatProblemas(String nome){
        ResultSet rs;
        CatProblemas catp = new CatProblemas();
        sSql = "SELECT * FROM cat_problemas WHERE cat_prob_nome = ?";
        
        try {
            con = getConexao();
            pstmt =  con.prepareStatement(sSql);
            pstmt.setString(1, nome);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                catp.setId(rs.getInt("cat_prob_id"));
                catp.setNome(rs.getString("cat_prob_nome"));
            }else{
                catp = null;
                System.out.println("Categoria não encontrada ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Categoria de Problemas ");
        }
        return catp;
    }
    
    
}
