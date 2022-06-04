/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.LocacaoAtivos;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOlocacaoAtivos extends Conexao {
    
    PreparedStatement pstmt;
    Connection con;
    String sSql;
    
    public void adicionarLocacaoAtivos (LocacaoAtivos locacao){
        
        sSql = "INSERT INTO locacao_ativos ("
                + "loc_datalocacao, "
                + "loc_datadevolucao, "
                + "usuarios_usu_id, "
                + "ativos_atv_id) "
                + "VALUES (?,?,?,?)";
        System.out.println("Chegou no DAO");
        try {
            con = getConexao();
            pstmt = con.prepareCall(sSql);
            pstmt.setDate(1, (Date) locacao.getDatalocacao());
            pstmt.setDate(2, (Date) locacao.getDatadevolucao());
            pstmt.setInt(3,locacao.getUsuarios_usu_id());
            pstmt.setInt(4, locacao.getIdequipe());
            //pstmt.close();
            
            pstmt.executeUpdate();
            pstmt = con.prepareStatement("UPDATE ativos SET atv_locado = 1 WHERE atv_id = ?;");
            pstmt.setInt(1, locacao.getIdequipe());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao adicionar Locacao de Ativos "+erro.getMessage());
        }            
    }
    
    public void devolverEquip(int ideqpto, Timestamp datadev, int idloc){
        
        sSql = "UPDATE locacao_ativos INNER JOIN ativos on ativos_atv_id=atv_id "
                + "SET loc_datadevolucao = ?, atv_locado = ? WHERE atv_id = ? and loc_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setTimestamp(1, datadev);
            pstmt.setBoolean(2, false);
            pstmt.setInt(3, ideqpto);
            pstmt.setInt(4, idloc);
            
            System.out.println("devolverEquip: "+datadev+" id: "+ideqpto);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao devolver ativo: "+erro);
        }
    }
    
    public LocacaoAtivos consultarLocacao(int id){
        ResultSet rs;
        LocacaoAtivos locacao = new LocacaoAtivos();
        sSql = "SELECT * FROM `locacao_ativos` LEFT JOIN ativos on ativos_atv_id = atv_id "
                + "LEFT JOIN usuarios on usuarios_usu_id = usu_id "
                + "LEFT JOIN departamento on departamento_dep_id = dep_id "
                + "WHERE loc_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                locacao.setId(rs.getInt("loc_id"));
                locacao.setDatalocacao(rs.getDate("loc_datalocacao"));
                locacao.setDatadevolucao(rs.getDate("loc_datadevolucao"));
                locacao.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                locacao.setIdequipe(rs.getInt("ativos_atv_id"));
                locacao.setTipo(rs.getString("atv_tipo"));
                locacao.setDescricao(rs.getString("atv_descricao"));
                locacao.setModelo(rs.getString("atv_modelo"));
                locacao.setFabricante(rs.getString("atv_fabricante"));
                locacao.setNumserie(rs.getString("atv_num_serie"));
                locacao.setNome(rs.getString("usu_nome"));
                locacao.setSobrenome(rs.getString("usu_sobrenome"));
                locacao.setTelefone(rs.getString("usu_telefone"));
                locacao.setRamal(rs.getString("usu_ramal"));
                locacao.setEmail(rs.getString("usu_email"));
                locacao.setDepartamento(rs.getString("dep_nome"));
                
            }else{
                locacao = null;
                System.out.println("Locação de ativos não encontrada ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Locação de Ativos "+erro.getMessage());
        }
        return locacao;
    }
    
    public LocacaoAtivos consultarLocacaoUser(int id){
        ResultSet rs;
        LocacaoAtivos locacao = new LocacaoAtivos();
        sSql = "SELECT * FROM `locacao_ativos` RIGHT JOIN ativos on locacao_ativos_loc_id = loc_id "
                + "WHERE usuarios_usu_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                locacao.setId(rs.getInt("loc_id"));
                locacao.setDatalocacao(rs.getDate("loc_datalocacao"));
                locacao.setDatadevolucao(rs.getDate("loc_datadevolucao"));
                locacao.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                locacao.setIdequipe(rs.getInt("atv_id"));
                locacao.setTipo(rs.getString("atv_tipo"));
                locacao.setDescricao(rs.getString("atv_descricao"));
                locacao.setModelo(rs.getString("atv_modelo"));
                locacao.setFabricante(rs.getString("atv_fabricante"));
                locacao.setNumserie(rs.getString("atv_num_serie"));
                
            }else{
                locacao = null;
                System.out.println("Não foi encontrada locação deste usuário");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Locação de Ativos "+erro.getMessage());
        }
        return locacao;
    }
    
    public LocacaoAtivos consultarLocacaoEquip(int id){
        ResultSet rs;
        LocacaoAtivos locacao = new LocacaoAtivos();
        sSql = "SELECT * FROM ativos LEFT JOIN locacao_ativos on atv_id = ativos_atv_id "
                + "LEFT JOIN usuarios on usuarios_usu_id = usu_id "
                + "LEFT JOIN departamento on departamento_dep_id = dep_id "
                + "WHERE atv_id = ?";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                locacao.setId(rs.getInt("loc_id"));
                locacao.setDatalocacao(rs.getDate("loc_datalocacao"));
                locacao.setDatadevolucao(rs.getDate("loc_datadevolucao"));
                locacao.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                locacao.setIdequipe(rs.getInt("atv_id"));
                locacao.setTipo(rs.getString("atv_tipo"));
                locacao.setDescricao(rs.getString("atv_descricao"));
                locacao.setModelo(rs.getString("atv_modelo"));
                locacao.setFabricante(rs.getString("atv_fabricante"));
                locacao.setNumserie(rs.getString("atv_num_serie"));
                locacao.setNome(rs.getString("usu_nome"));
                locacao.setSobrenome(rs.getString("usu_sobrenome"));
                locacao.setTelefone(rs.getString("usu_telefone"));
                locacao.setRamal(rs.getString("usu_ramal"));
                locacao.setEmail(rs.getString("usu_email"));
                locacao.setDepartamento(rs.getString("dep_nome"));
                locacao.setLocado(rs.getBoolean("atv_locado"));
                
            }else{
                locacao = null;
                System.out.println("Locação de ativos não encontrada ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Locação de Ativos "+erro.getMessage());
        }
        return locacao;
    }
    
    public List<LocacaoAtivos> listaLocacaoAtivos(){
        List listalocacao = new ArrayList();
        ResultSet rs;
        LocacaoAtivos locacao;
        sSql = "SELECT * FROM locacao_ativos";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                locacao = new LocacaoAtivos();
                locacao.setId(rs.getInt("loc_id"));
                locacao.setDatalocacao(rs.getDate("loc_datalocacao"));
                locacao.setDatadevolucao(rs.getDate("loc_datadevolucao"));
                locacao.setUsuarios_usu_id(rs.getInt("usuarios_usu_id"));
                listalocacao.add(locacao);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Locacao "+erro.getMessage());
        }
        return listalocacao;
    }
    
    public List<LocacaoAtivos> listaEquipLocados(){
        List listalocacao = new ArrayList();
        ResultSet rs;
        LocacaoAtivos locacao;
        sSql = "SELECT loc_id, loc_datalocacao, loc_datadevolucao, "
                + "atv_id, atv_descricao, atv_tipo, atv_modelo, atv_fabricante, atv_num_serie, atv_locado, "
                + "usu_id, usu_nome, usu_sobrenome, usu_email, usu_telefone, usu_ramal, "
                + "dep_nome "
                + "FROM locacao_ativos l LEFT JOIN ativos a ON l.ativos_atv_id = a.atv_id "
                + "LEFT JOIN usuarios u ON l.usuarios_usu_id = u.usu_id "
                + "LEFT JOIN departamento d ON u.departamento_dep_id = d.dep_id WHERE atv_locado = 1;";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                locacao = new LocacaoAtivos();
                locacao.setId(rs.getInt("loc_id"));
                locacao.setDatalocacao(rs.getDate("loc_datalocacao"));
                locacao.setDatadevolucao(rs.getDate("loc_datadevolucao"));
                locacao.setUsuarios_usu_id(rs.getInt("usu_id"));
                locacao.setIdequipe(rs.getInt("atv_id"));
                locacao.setDescricao(rs.getString("atv_descricao"));
                locacao.setTipo(rs.getString("atv_tipo"));
                locacao.setModelo(rs.getString("atv_modelo"));
                locacao.setFabricante(rs.getString("atv_fabricante"));
                locacao.setNumserie(rs.getString("atv_num_serie"));
                locacao.setNome(rs.getString("usu_nome"));
                locacao.setSobrenome(rs.getString("usu_sobrenome"));
                locacao.setEmail(rs.getString("usu_email"));
                locacao.setTelefone(rs.getString("usu_telefone"));
                locacao.setRamal(rs.getString("usu_ramal"));
                locacao.setDepartamento(rs.getString("dep_nome"));
                locacao.setLocado(rs.getBoolean("atv_locado"));
                
                listalocacao.add(locacao);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Locacao "+erro.getMessage());
        }
        return listalocacao;
    }

    public List<LocacaoAtivos> listaEquipNaoLocados(){
        List listalocacao = new ArrayList();
        ResultSet rs;
        LocacaoAtivos locacao;
        sSql = "SELECT loc_id, loc_datalocacao, loc_datadevolucao, "
                + "atv_id, atv_descricao, atv_tipo, atv_modelo, atv_fabricante, atv_num_serie, atv_locado, "
                + "usu_id, usu_nome, usu_email, usu_telefone, usu_ramal, "
                + "dep_nome "
                + "FROM ativos a LEFT JOIN locacao_ativos l ON l.ativos_atv_id = a.atv_id "
                + "LEFT JOIN usuarios u ON l.usuarios_usu_id = u.usu_id "
                + "LEFT JOIN departamento d ON u.departamento_dep_id = d.dep_id "
                + "WHERE atv_locado = 0";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                locacao = new LocacaoAtivos();
                locacao.setId(rs.getInt("loc_id"));
                locacao.setDatalocacao(rs.getDate("loc_datalocacao"));
                locacao.setDatadevolucao(rs.getDate("loc_datadevolucao"));
                locacao.setUsuarios_usu_id(rs.getInt("usu_id"));
                locacao.setIdequipe(rs.getInt("atv_id"));
                locacao.setDescricao(rs.getString("atv_descricao"));
                locacao.setTipo(rs.getString("atv_tipo"));
                locacao.setModelo(rs.getString("atv_modelo"));
                locacao.setFabricante(rs.getString("atv_fabricante"));
                locacao.setNumserie(rs.getString("atv_num_serie"));
                locacao.setNome(rs.getString("usu_nome"));
                locacao.setEmail(rs.getString("usu_email"));
                locacao.setTelefone(rs.getString("usu_telefone"));
                locacao.setRamal(rs.getString("usu_ramal"));
                locacao.setDepartamento(rs.getString("dep_nome"));
                locacao.setLocado(rs.getBoolean("atv_locado"));
                
                listalocacao.add(locacao);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Locacao "+erro.getMessage());
        }
        return listalocacao;
    }


        public List<LocacaoAtivos> listaEquip(){
        List listalocacao = new ArrayList();
        ResultSet rs;
        LocacaoAtivos locacao;
        sSql = "SELECT loc_id, loc_datalocacao, loc_datadevolucao, "
                + "atv_id, atv_descricao, atv_tipo, atv_modelo, atv_fabricante, atv_num_serie, atv_locado, "
                + "usu_id, usu_nome, usu_sobrenome, usu_email, usu_telefone, usu_ramal, "
                + "dep_nome "
                + "FROM ativos a LEFT JOIN locacao_ativos l ON l.ativos_atv_id = a.atv_id "
                + "LEFT JOIN usuarios u ON l.usuarios_usu_id = u.usu_id "
                + "LEFT JOIN departamento d ON u.departamento_dep_id = d.dep_id";
        
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                locacao = new LocacaoAtivos();
                locacao.setId(rs.getInt("loc_id"));
                locacao.setDatalocacao(rs.getDate("loc_datalocacao"));
                locacao.setDatadevolucao(rs.getDate("loc_datadevolucao"));
                locacao.setUsuarios_usu_id(rs.getInt("usu_id"));
                locacao.setIdequipe(rs.getInt("atv_id"));
                locacao.setDescricao(rs.getString("atv_descricao"));
                locacao.setTipo(rs.getString("atv_tipo"));
                locacao.setModelo(rs.getString("atv_modelo"));
                locacao.setFabricante(rs.getString("atv_fabricante"));
                locacao.setNumserie(rs.getString("atv_num_serie"));
                locacao.setNome(rs.getString("usu_nome"));
                locacao.setSobrenome(rs.getString("usu_sobrenome"));
                locacao.setEmail(rs.getString("usu_email"));
                locacao.setTelefone(rs.getString("usu_telefone"));
                locacao.setRamal(rs.getString("usu_ramal"));
                locacao.setDepartamento(rs.getString("dep_nome"));
                locacao.setLocado(rs.getBoolean("atv_locado"));
                
                listalocacao.add(locacao);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Locacao "+erro.getMessage());
        }
        return listalocacao;
    }
        
}
