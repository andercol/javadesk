/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOfornecedor extends Conexao{
    
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public boolean adicionarFornecedor(Fornecedor forn){
        
        sSql = "INSERT INTO fornecedor ("
                + "for_cnpj, "
                + "for_nome, "
                + "for_endereco, "
                + "for_numero, "
                + "for_telefone, "
                + "for_email, "
                + "for_contato, "
                + "for_bairro, "
                + "for_cidade, "
                + "for_estado, "
                + "for_cep)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1,forn.getCnpj());
            pstmt.setString(2,forn.getNome());
            pstmt.setString(3,forn.getEndereco());
            pstmt.setString(4,forn.getNumero());
            pstmt.setString(5,forn.getTelefone());
            pstmt.setString(6,forn.getEmail());
            pstmt.setString(7,forn.getContato());
            pstmt.setString(8,forn.getBairro());
            pstmt.setString(9,forn.getCidade());
            pstmt.setString(10,forn.getEstado());
            pstmt.setString(11,forn.getCep());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Fornecedor "+erro.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean updateFornecedor(Fornecedor forn){
        
        sSql = "UPDATE fornecedor SET "
                + "for_cnpj = ?, "
                + "for_nome = ?, "
                + "for_endereco = ?, "
                + "for_numero = ?, "
                + "for_telefone = ?, "
                + "for_email = ?, "
                + "for_contato = ?, "
                + "for_bairro = ?, "
                + "for_cidade = ?, "
                + "for_estado = ?, "
                + "for_cep = ? WHERE for_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            
            pstmt.setString(1,forn.getCnpj());
            pstmt.setString(2,forn.getNome());
            pstmt.setString(3,forn.getEndereco());
            pstmt.setString(4,forn.getNumero());
            pstmt.setString(5,forn.getTelefone());
            pstmt.setString(6,forn.getEmail());
            pstmt.setString(7,forn.getContato());
            pstmt.setString(8, forn.getBairro());
            pstmt.setString(9, forn.getCidade());
            pstmt.setString(10, forn.getEstado());
            pstmt.setString(11, forn.getCep());
            pstmt.setInt(12,forn.getId());
            
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar Fornecedor "+erro.getMessage());
            return false;
        }
        return true;
    }
    
    public Fornecedor consultaFornecedor (int id){
        Fornecedor forn = new Fornecedor();
        ResultSet rs;
        sSql = "SELECT FROM fornecedor WHERE for_cnpj = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                forn.setId(rs.getInt("for_id"));
                forn.setCnpj(rs.getString("for_cnpj"));
                forn.setNome(rs.getString("for_nome"));
                forn.setEndereco(rs.getString("for_endereco"));
                forn.setNumero(rs.getString("for_numero"));
                forn.setTelefone(rs.getString("fo_telefone"));
                forn.setEmail(rs.getString("for_email"));
                forn.setContato(rs.getString("for_contato"));
                forn.setBairro(rs.getString("for_bairro"));
                forn.setCidade(rs.getString("for_cidade"));
                forn.setEstado(rs.getString("for_estado"));
                forn.setCep(rs.getString("for_cep"));
            }else{
                forn = null;
                System.out.println("Fornecedor não encontrado ");
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Fornecedor "+erro.getMessage());
        }
        return forn;
    }
    
    public Fornecedor consultaCNPJFornecedor (String cnpj){
        Fornecedor forn = new Fornecedor();
        ResultSet rs;
        sSql = "SELECT * FROM fornecedor WHERE for_cnpj = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, cnpj);
            rs = pstmt.executeQuery();
            if(rs.next()){
                forn.setId(rs.getInt("for_id"));
                forn.setCnpj(rs.getString("for_cnpj"));
                forn.setNome(rs.getString("for_nome"));
                forn.setEndereco(rs.getString("for_endereco"));
                forn.setNumero(rs.getString("for_numero"));
                forn.setTelefone(rs.getString("for_telefone"));
                forn.setEmail(rs.getString("for_email"));
                forn.setContato(rs.getString("for_contato"));
                forn.setBairro(rs.getString("for_bairro"));
                forn.setCidade(rs.getString("for_cidade"));
                forn.setEstado(rs.getString("for_estado"));
                forn.setCep(rs.getString("for_cep"));
            }else{
                forn = null;
                System.out.println("Fornecedor não encontrado 'DAO' ");
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Fornecedor "+erro.getMessage());
        }
        return forn;
    }
    
    public List<Fornecedor> listaFornecedor(){
        List listaforn = new ArrayList();
        ResultSet rs;
        Fornecedor forn;
        sSql = "SELECT * FROM fornecedor";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                forn = new Fornecedor();
                forn.setId(rs.getInt("for_id"));
                forn.setCnpj(rs.getString("for_cnpj"));
                forn.setNome(rs.getString("for_nome"));
                forn.setEndereco(rs.getString("for_endereco"));
                forn.setNumero(rs.getString("for_numero"));
                forn.setTelefone(rs.getString("for_telefone"));
                forn.setEmail(rs.getString("for_email"));
                forn.setContato(rs.getString("for_contato"));
                forn.setBairro(rs.getString("for_bairro"));
                forn.setCidade(rs.getString("for_cidade"));
                forn.setEstado(rs.getString("for_estado"));
                forn.setCep(rs.getString("for_cep"));
                listaforn.add(forn);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Fornecedores "+erro.getMessage());
        }
        return listaforn;
    }

    public void deleteFornecedor (String cnpj){
        sSql = "DELETE from Fornecedor WHERE for_cnpj = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, cnpj);
            pstmt.executeUpdate();
            //pstmt.close();
            //con.close();
            
            
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir Fornecedor "+erro);
        }
    }

}
