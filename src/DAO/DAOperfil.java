/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Colin
 */
public class DAOperfil extends Conexao{
    
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public void inseirPerfil(Perfil perf){
        sSql = "INSERT INTO perfil (perf_perfil, perf_descricao) VALUES(?,?)";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,perf.getPerfil());
            pstmt.setString(2, perf.getDescricao());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Perfil "+erro.getMessage());
        }
    }
    
    
    public void updatePerfil(Perfil perf){
        sSql = "UPDATE perfil SET perf_perfil = ?, perf_descricao = ? WHERE perf_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,perf.getPerfil());
            pstmt.setString(2, perf.getDescricao());
            pstmt.setInt(3, perf.getId());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar Perfil "+erro.getMessage());
        }
    }
    
    
    public void exluirPerfil (int id){
        sSql = "DELETE FROM perfil WHERE perf_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1,id);
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao Excluir Perfil "+erro.getMessage());
        }
    }
    
    public Perfil consultarPerfil(int id){
        Perfil perfil = new Perfil();
        ResultSet rs;
        sSql = "SELECT * FROM perfil WHERE perf_id = ?";
        
        try {
            con = getConexao();
            pstmt.setInt(1, id);
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            
            if (rs.next()){
                perfil.setId(rs.getInt("perf_id"));
                perfil.setPerfil(rs.getString("perf_perfil"));
                perfil.setDescricao(rs.getString("perf_descricao"));
            }
            else{
                perfil = null;
                System.out.println("Perfil Não encontrado");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar Perfil "+erro.getMessage());
        }
        
        return perfil;
    }
    
    public Perfil consultarNomePerfil(String perf){
        Perfil perfil = new Perfil();
        ResultSet rs;
        sSql = "SELECT * FROM perfil WHERE perf_perfil = '"+perf+"'";
        
        try {
            con = getConexao();
            //pstmt.setString(1, perf);
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            
            if (rs.next()){
                perfil.setId(rs.getInt("perf_id"));
                perfil.setPerfil(rs.getString("perf_perfil"));
                perfil.setDescricao(rs.getString("perf_descricao"));
            }
            else{
                perfil = null;
                System.out.println("Perfil Não encontrado");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar Perfil "+erro.getMessage());
        }
        
        return perfil;
    }
    
    public List<Perfil> listaPerfil(){
        List listaPerfil = new ArrayList();
        ResultSet rs;
        Perfil perf;
        sSql = "SELECT * FROM perfil";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                perf = new Perfil();
                perf.setId(rs.getInt("perf_id"));
                perf.setPerfil(rs.getString("perf_perfil"));
                perf.setDescricao(rs.getString("perf_descricao"));
                listaPerfil.add(perf);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Perfis "+erro.getMessage());
        }
        return listaPerfil;
    }
}
