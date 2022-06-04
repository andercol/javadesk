/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Ativos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOativos extends Conexao {
    
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public boolean adicionarAtivo (Ativos atv){
        
        sSql = "INSERT INTO ativos ("
                + "atv_tipo, "
                + "atv_descricao, "
                + "atv_modelo, "
                + "atv_fabricante, "
                + "atv_num_serie, "
                + "atv_nf, "
                + "fornecedor_for_id, "
                + "fornecedor_for_cnpj)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, atv.getTipo());
            pstmt.setString(2, atv.getDescricao());
            pstmt.setString(3, atv.getModelo());
            pstmt.setString(4, atv.getFabricante());
            pstmt.setString(5, atv.getNum_serie());
            pstmt.setString(6, atv.getNf());
            pstmt.setInt(7, atv.getFornecedor_for_id());
            pstmt.setString(8, atv.getFornecedor_for_cnpj());
            //pstmt.setInt(8, atv.getLocacao_ativos_loc_id());
            
            pstmt.executeUpdate();
            
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Ativo "+erro.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean updateAtivo (Ativos atv){
        
        sSql = "UPDATE ativos SET "
                + "atv_tipo = ?, "
                + "atv_descricao = ?, "
                + "atv_modelo = ?, "
                + "atv_fabricante = ?, "
                + "atv_num_serie = ?, "
                + "atv_nf = ?, "
                + "fornecedor_for_id = ?, "
                + "fornecedor_for_cnpj = ? "
                + "WHERE atv_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, atv.getTipo());
            pstmt.setString(2, atv.getDescricao());
            pstmt.setString(3, atv.getModelo());
            pstmt.setString(4, atv.getFabricante());
            pstmt.setString(5, atv.getNum_serie());
            pstmt.setString(6, atv.getNf());
            pstmt.setInt(7, atv.getFornecedor_for_id());
            pstmt.setString(8, atv.getFornecedor_for_cnpj());
            pstmt.setInt(9, atv.getId());
            
            pstmt.executeUpdate();
            
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar Ativo "+erro.getMessage());
            return false;
        }
        return true;
    }
    
    public Ativos consultaAtivos (int id){
        ResultSet rs;
        Ativos atv = new Ativos();
        sSql = "SELECT * FROM ativos WHERE atv_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                atv.setId(rs.getInt("atv_id"));
                atv.setTipo(rs.getString("atv_tipo"));
                atv.setDescricao(rs.getString("atv_descricao"));
                atv.setModelo(rs.getString("atv_modelo"));
                atv.setFabricante(rs.getString("atv_fabricante"));
                atv.setNum_serie(rs.getString("atv_num_serie"));
                atv.setNf(rs.getString("atv_nf"));
                atv.setFornecedor_for_id(rs.getInt("fornecedor_for_id"));
                atv.setFornecedor_for_cnpj(rs.getString("fornecedor_for_cnpj"));
                
                
            }else{
                //atv = null;
                System.out.println("Ativo não encontrado ");
            }
            
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Ativos "+erro.getMessage());
        }
        return atv;
    }
    
    public List<Ativos> listaAtivos(){
        List listatv = new ArrayList();
        ResultSet rs;
        Ativos atv;
        // também pode substituir os campos por * para pegar todos os campos das duas tabelas
        sSql = "SELECT `atv_id`,`atv_tipo`,`atv_descricao`, "
                + "`atv_modelo`, `atv_fabricante`, `atv_num_serie`, "
                + "`atv_nf`, `fornecedor_for_id`, `fornecedor_for_cnpj`, "
                + "`for_nome`, `loc_id` FROM ativos "
                + "LEFT JOIN fornecedor ON fornecedor_for_id = for_id "
                + "LEFT JOIN locacao_ativos on atv_id = ativos_atv_id "
                + "LEFT JOIN usuarios on usuarios_usu_id = usu_id";
        //sSql = " SELECT * FROM locacao_ativos, ativos INNER JOIN fornecedor on fornecedor_for_id = for_id";
        
        try {
            con = getConexao();
            pstmt =  con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                atv = new Ativos();
                atv.setId(rs.getInt("atv_id"));
                atv.setTipo(rs.getString("atv_tipo"));
                atv.setDescricao(rs.getString("atv_descricao"));
                atv.setModelo(rs.getString("atv_modelo"));
                atv.setFabricante(rs.getString("atv_fabricante"));
                atv.setNum_serie(rs.getString("atv_num_serie"));
                atv.setNf(rs.getString("atv_nf"));
                atv.setCnpjforn(rs.getString("fornecedor_for_cnpj"));
                atv.setFornecedor(rs.getString("for_nome"));
                atv.setFornecedor_for_id(rs.getInt("fornecedor_for_id"));
                atv.setFornecedor_for_cnpj(rs.getString("fornecedor_for_cnpj"));
                atv.setLocacao_ativos_loc_id(rs.getInt("loc_id"));
 
                listatv.add(atv);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Ativos "+erro.getMessage());
        }
        return listatv;
    }
    
    public List<Ativos> listaAtivosLocado(){
        List listatv = new ArrayList();
        ResultSet rs;
        Ativos atv;
        sSql = "SELECT `atv_id`,`atv_tipo`,`atv_descricao`, "
                + "`atv_modelo`, `atv_fabricante`, `atv_num_serie`, "
                + "`atv_nf`, `fornecedor_for_id`, `fornecedor_for_cnpj`, "
                + "`for_nome` FROM ativos "
                + "LEFT JOIN fornecedor ON fornecedor_for_id = for_id "
                + " WHERE atv_locado = 0";
        //sSql = " SELECT * FROM locacao_ativos, ativos INNER JOIN fornecedor on fornecedor_for_id = for_id";
        
        try {
            con = getConexao();
            pstmt =  con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                atv = new Ativos();
                atv.setId(rs.getInt("atv_id"));
                atv.setTipo(rs.getString("atv_tipo"));
                atv.setDescricao(rs.getString("atv_descricao"));
                atv.setModelo(rs.getString("atv_modelo"));
                atv.setFabricante(rs.getString("atv_fabricante"));
                atv.setNum_serie(rs.getString("atv_num_serie"));
                atv.setNf(rs.getString("atv_nf"));
                atv.setCnpjforn(rs.getString("fornecedor_for_cnpj"));
                atv.setFornecedor(rs.getString("for_nome"));
                atv.setFornecedor_for_id(rs.getInt("fornecedor_for_id"));
                atv.setFornecedor_for_cnpj(rs.getString("fornecedor_for_cnpj"));
                //atv.setLocacao_ativos_loc_id(rs.getInt("loc_id"));
 
                listatv.add(atv);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Ativos "+erro.getMessage());
        }
        return listatv;
    }
    
    
    public void excluirAtivos (int id){
        sSql = "DELETE FROM ativos WHERE atv_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir ativos "+erro.getMessage());
        }
    }

    public List<Ativos> listaTipos(){
        List listatipo = new ArrayList();
        ResultSet rs;
        Ativos atv;
        sSql = "SELECT DISTINCT atv_tipo FROM ativos";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                atv = new Ativos();
                atv.setTipo(rs.getString("atv_tipo"));
                
                listatipo.add(atv);       
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Tipos "+erro.getMessage());
        }
        return listatipo;
    }
 
}
