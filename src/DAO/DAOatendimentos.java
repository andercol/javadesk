/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Atendimentos;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOatendimentos extends Conexao {

    PreparedStatement pstmt;
    Connection con;
    String sSql;

    public void adicionarAtendimento(Atendimentos atd) {
        sSql = "INSERT INTO atendimentos (atend_data, atend_descricao, ocorrencia_oco_id, usuarios_usu_id) VALUES (?,?,?,?) ";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setTimestamp(1, atd.getData_atendimento());
            pstmt.setString(2, atd.getDescricao());
            pstmt.setInt(3, atd.getOcorrencia_oco_id());
            pstmt.setInt(4, atd.getUsusarios_usu_id());

            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Atendimento " + erro.getMessage());
        }

    }

    public void consultarAtendimento(int id) {
        sSql = "SELECT * FROM atendimentos WHERE atend_id = ?";
        ResultSet rs;
        Atendimentos atd = new Atendimentos();
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                atd.setId(rs.getInt("atend_id"));
                atd.setData_atendimento(rs.getTimestamp("atend_data"));
                atd.setDescricao(rs.getString("atend_descricao"));
                atd.setOcorrencia_oco_id(rs.getInt("ocorrencia_oco_id"));
                atd.setUsusarios_usu_id(rs.getInt("usuarios_usu_id"));

            } else {
                atd = null;
                System.out.println("Atendimento não encontrado");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar Atendimento " + erro.getMessage());
        }
    }
    
    public List<Atendimentos> listaAtendimentos(){
        List listaatd = new ArrayList();
        ResultSet rs;
        Atendimentos atd;
        sSql = "SELECT * FROM atendimentos";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                atd = new Atendimentos();
                atd.setId(rs.getInt("atend_id"));
                atd.setData_atendimento(rs.getTimestamp("atend_data"));
                atd.setDescricao(rs.getString("atend_descricao"));
                atd.setOcorrencia_oco_id(rs.getInt("ocorrencia_oco_id"));
                atd.setUsusarios_usu_id(rs.getInt("usuarios_usu_id"));
                
                listaatd.add(atd);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Lista de Atendimentos "+erro.getMessage());
        }
        return listaatd;
    }
    
    public List<Atendimentos> listaAtendimentos(int oco){
        List listaatd = new ArrayList();
        ResultSet rs;
        Atendimentos atd;
        sSql = "SELECT atend_id, atend_data, atend_descricao, ocorrencia_oco_id,usuarios_usu_id, usu_nome FROM atendimentos "
                + "INNER JOIN usuarios on usuarios_usu_id = usu_id WHERE ocorrencia_oco_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, oco);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                atd = new Atendimentos();
                atd.setId(rs.getInt("atend_id"));
                atd.setData_atendimento(rs.getTimestamp("atend_data"));
                atd.setDescricao(rs.getString("atend_descricao"));
                atd.setOcorrencia_oco_id(rs.getInt("ocorrencia_oco_id"));
                atd.setUsusarios_usu_id(rs.getInt("usuarios_usu_id"));
                atd.setUsuario(rs.getString("usu_nome"));
                
                listaatd.add(atd);
            }
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Lista de Atendimentos "+erro.getMessage());
        }
        return listaatd;
    }
    
}
