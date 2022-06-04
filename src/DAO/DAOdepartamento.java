/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Departamento;
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

public class DAOdepartamento extends Conexao{
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public void inserirDepartamento(Departamento dep){
        sSql = "INSERT INTO departamento (dep_nome, usuarios_usu_id) VALUES(?,?)";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,dep.getNome());
            pstmt.setInt(2, dep.getUsuarios_usu_id());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Departamento "+erro.getMessage());
        }
    }
    
    public void updateDepartamento(Departamento dep){
        sSql = "UPDATE departamento SET dep_nome = ?, usuarios_usu_id = ? WHERE dep_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,dep.getNome());
            pstmt.setInt(2, dep.getUsuarios_usu_id());
            pstmt.setInt(3, dep.getId());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Departamento "+erro.getMessage());
        }
    }
    
    public void exluirDepartamento (int id){
        sSql = "DELETE FROM departamento WHERE dep_id = ?";
        System.out.println("ID recebido: "+id);
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1,id);
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao Excluir Departamento "+erro.getMessage());
        }
    }
    
    public Departamento consultarDepartamento(int id){
        Departamento dep = new Departamento();
        ResultSet rs;
        //sSql = "SELECT * FROM departamento WHERE perf_id = ?";
        sSql = "select  "
                + "dep_id, "
                + "dep_nome, "
                + "usuarios_usu_id, "
                + "usu_nome, "
                + "usu_email "
                + "from departamento d inner join usuarios u where u.usu_id = d.usuarios_usu_id and dep_id = ?;";
       
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()){
                dep.setId(rs.getInt("dep_id"));
                dep.setNome(rs.getString("dep_nome"));
                dep.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                dep.setNomeresp("usu_nome");
                dep.setEmailresp("usu_email");
            }
            else{
                dep = null;
                System.out.println("Departamento Não encontrado");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar Departamento "+erro.getMessage());
        }
        
        return dep;
    }
    
    public Departamento consultarNomeDepartamento(String depto){
        Departamento dep = new Departamento();
        ResultSet rs;
        //sSql = "SELECT * FROM departamento WHERE perf_id = ?";
        sSql = "select  "
                + "dep_id, "
                + "dep_nome, "
                + "usuarios_usu_id, "
                + "usu_nome, "
                + "usu_email "
                + "from departamento d inner join usuarios u where u.usu_id = d.usuarios_usu_id and dep_nome = '"+depto+"'";
       
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            //pstmt.setString(1, depto);
            rs = pstmt.executeQuery();
            
            if (rs.next()){
                dep.setId(rs.getInt("dep_id"));
                dep.setNome(rs.getString("dep_nome"));
                dep.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                dep.setNomeresp("usu_nome");
                dep.setEmailresp("usu_email");
            }
            else{
                dep = null;
                System.out.println("Departamento Não encontrado");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar Departamento "+erro.getMessage());
        }
        
        return dep;
    }
    
    
    public List<Departamento> listaDepartamento(){
        List listaDep = new ArrayList();
        ResultSet rs;
        Departamento dep;
        //sSql = "SELECT * FROM departamento";
        sSql = "select  "
                + "dep_id, "
                + "dep_nome, "
                + "usuarios_usu_id, "
                + "usu_nome, "
                + "usu_email "
                + "from departamento d inner join usuarios u where u.usu_id = d.usuarios_usu_id ";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                dep = new Departamento();
                dep.setId(rs.getInt("dep_id"));
                dep.setNome(rs.getString("dep_nome"));
                dep.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                dep.setNomeresp(rs.getString("usu_nome"));
                dep.setEmailresp(rs.getString("usu_email"));
                listaDep.add(dep);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Departamentos "+erro.getMessage());
        }
        return listaDep;
    }    
    
    
}
