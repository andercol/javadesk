/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Usuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOusuarios extends Conexao{
    
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public void adicionarUsuario (Usuarios user){
      System.out.println("user nome === "+user.getNome());
      sSql = "INSERT INTO usuarios("
              + "usu_nome, "
              + "usu_sobrenome, "
              + "usu_telefone, "
              + "usu_ramal, "
              + "usu_email, "
              + "usu_senha, "
              + "usu_status, "
              + "perfil_perf_id, "
              + "departamento_dep_id) "
              + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = getConexao(); 
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,user.getNome());
            pstmt.setString(2,user.getSobreNome());
            pstmt.setString(3,user.getTelefone());
            pstmt.setString(4,user.getRamal());
            pstmt.setString(5,user.getEmail());
            pstmt.setString(6,user.getSenha());
            pstmt.setBoolean(7,user.isStatus());
            pstmt.setInt(8,user.getPerfil_perf_id());
            pstmt.setInt(9,user.getDepartamento_dep_id());

            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
            System.out.println("Usuário adicionado com sucesso!!");
            
        } catch (SQLException erro) {
            System.out.println("Erro ao gravar usuario "+ erro.getMessage());
            
        }   
       
    }
    
    public void updateUsuario (Usuarios user){
      System.out.println("user ID == "+user.getId());
      sSql = "UPDATE usuarios SET "
              + "usu_nome = ?, "
              + "usu_sobrenome = ?, "
              + "usu_telefone = ?, "
              + "usu_ramal = ?, "
              + "usu_email = ?, "
              + "usu_senha = ?, "
              + "usu_status = ?, "
              + "perfil_perf_id = (SELECT perf_id FROM perfil WHERE perf_perfil = ? ), "
              + "departamento_dep_id = (SELECT dep_id FROM departamento WHERE dep_nome = ? ) WHERE usu_id = "+user.getId();
        try {
            con = getConexao(); 
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,user.getNome());
            pstmt.setString(2,user.getSobreNome());
            pstmt.setString(3,user.getTelefone());
            pstmt.setString(4,user.getRamal());
            pstmt.setString(5,user.getEmail());
            pstmt.setString(6,user.getSenha());
            pstmt.setBoolean(7,user.isStatus());
            pstmt.setString(8,user.getPerfil());
            pstmt.setString(9,user.getDepartamento());
            //pstmt.setInt(9,user.getId());

            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
            System.out.println("Usuário atualizado com sucesso!!");
            
        } catch (SQLException erro) {
            System.out.println("Erro ao gravar usuario "+ erro.getMessage());
            
        }   
       
    }
    
    
    public int updatePassword (String pass, int id){
    
      sSql = "UPDATE usuarios SET "
              + "usu_senha = ? WHERE usu_id = ?";
        try {
            con = getConexao(); 
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, pass);
            pstmt.setInt(2, id);
            
            //pstmt.setInt(9,user.getId());

            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
            System.out.println("Usuário atualizado com sucesso!!");
            return 1;
        } catch (SQLException erro) {
            System.out.println("Erro ao gravar usuario "+ erro.getMessage());
        }   
        return 0;
    }
    
    
    
    public void excluirUsuario(int id){
        
        sSql = "DELETE FROM usuarios WHERE usu_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareCall(sSql);
            pstmt.setInt(1,id);
            
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir usuario "+erro.getMessage());
        }
    }
    
    public Usuarios consultaUsuario(int id){
        sSql = "SELECT * FROM `usuarios` LEFT JOIN departamento on departamento_dep_id = dep_id where usu_id = ?";
        ResultSet rs;
        Usuarios user = new Usuarios();
        
        try { 
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt("usu_id"));
                user.setNome(rs.getString("usu_nome"));
                user.setSobreNome(rs.getString("usu_sobrenome"));
                user.setTelefone(rs.getString("usu_telefone"));
                user.setRamal(rs.getString("usu_ramal"));
                user.setEmail(rs.getString("usu_email"));
                user.setStatus(rs.getBoolean("usu_status"));
                user.setPerfil_perf_id(rs.getInt("perfil_perf_id"));
                user.setDepartamento_dep_id(rs.getInt("departamento_dep_id"));
                user.setDepartamento(rs.getString("dep_nome"));
            }else{
                //user = null;
                System.out.println("DAOUsuarios - ID de Usuario  não encontrado ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Usuário "+erro.getMessage());
        }
        return user;
    }
  
    public Usuarios consultaUsuario(String email){
        sSql = "SELECT * FROM usuarios WHERE usu_email = '"+ email+"'";
        ResultSet rs;
        Usuarios user = new Usuarios();

        try { 
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                
                user.setId(rs.getInt("usu_id"));
                user.setNome(rs.getString("usu_nome"));
                user.setSobreNome(rs.getString("usu_sobrenome"));
                user.setTelefone(rs.getString("usu_telefone"));
                user.setRamal(rs.getString("usu_ramal"));
                user.setEmail(rs.getString("usu_email"));
                user.setStatus(rs.getBoolean("usu_status"));
                user.setPerfil_perf_id(rs.getInt("perfil_perf_id"));
                user.setDepartamento_dep_id(rs.getInt("departamento_dep_id"));
            }else{
                user = null;
                System.out.println("Usuario não encontrado ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Usuário "+erro.getMessage());
        }
        return user;
    }
    
    public Usuarios consultaUsuario(String email, String senha){
        //sSql = "SELECT * FROM usuarios WHERE usu_email = ? AND usu_senha = ?";
        sSql = "SELECT usu_id, usu_nome, usu_sobrenome, usu_telefone, usu_ramal, usu_email, usu_senha, usu_status, "
                + "perfil_perf_id, departamento_dep_id, dep_nome, perf_perfil FROM usuarios "
                + "INNER JOIN departamento on departamento_dep_id = dep_id "
                + "INNER JOIN perfil on perfil_perf_id = perf_id "
                + "WHERE usu_email = ? AND usu_senha = ?";
        ResultSet rs;
        Usuarios user = new Usuarios();

        try { 
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            rs = pstmt.executeQuery();
            if(rs.next()){               
                user.setId(rs.getInt("usu_id"));
                user.setNome(rs.getString("usu_nome"));
                user.setSobreNome(rs.getString("usu_sobrenome"));
                user.setTelefone(rs.getString("usu_telefone"));
                user.setRamal(rs.getString("usu_ramal"));
                user.setEmail(rs.getString("usu_email"));
                user.setSenha(rs.getString("usu_senha"));
                user.setStatus(rs.getBoolean("usu_status"));
                user.setPerfil_perf_id(rs.getInt("perfil_perf_id"));
                user.setDepartamento_dep_id(rs.getInt("departamento_dep_id"));
                user.setDepartamento(rs.getString("dep_nome"));
                System.out.println("DAO dep_nome: "+rs.getString("dep_nome"));
                user.setPerfil(rs.getString("perf_perfil"));
            }else{
                user = null;
                System.out.println("Usuario não encontrado ");
                            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Usuário "+erro.getMessage());
        }
        return user;
    }
    
    public List<Usuarios> listaUsuarios(){
        
        List listaUsuarios = new ArrayList();
        ResultSet rs;
        Usuarios user;
        sSql = "SELECT usu_id, "
                + "usu_nome, "
                + "usu_sobrenome, "
                + "usu_telefone, "
                + "usu_ramal, "
                + "usu_email, "
                + "usu_status, "
                + "perfil_perf_id, "
                + "departamento_dep_id, "
                + "perf_perfil, "
                + "dep_nome "
                + "FROM Usuarios u, perfil p , departamento d "
                + "where p.perf_id = u.perfil_perf_id and d.dep_id = u.departamento_dep_id ";
        try {
            con = getConexao();
            pstmt =  con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                user = new Usuarios();
                user.setId(rs.getInt("usu_id"));
                user.setNome(rs.getString("usu_nome"));
                user.setSobreNome(rs.getString("usu_sobrenome"));
                user.setTelefone(rs.getString("usu_telefone"));
                user.setRamal(rs.getString("usu_ramal"));
                user.setEmail(rs.getString("usu_email"));
                user.setStatus(rs.getBoolean("usu_status"));
                user.setPerfil_perf_id(rs.getInt("perfil_perf_id"));
                user.setDepartamento_dep_id(rs.getInt("departamento_dep_id"));
                user.setDepartamento(rs.getString("dep_nome"));
                user.setPerfil(rs.getString("perf_perfil"));
                
                listaUsuarios.add(user);            
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Usuarios " + erro.getMessage());
        }
        return listaUsuarios;
    }

}
