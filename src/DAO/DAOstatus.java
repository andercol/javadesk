/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Status;
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
public class DAOstatus extends Conexao {

    PreparedStatement pstmt;
    Connection con;
    String sSql;

    public void inserirStatus(Status stat) {

        try {
            con = getConexao();
            pstmt = con.prepareStatement("INSERT INTO status (stat_status) VALUE (?)");
            pstmt.setString(1, stat.getStatus());

            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Status " + erro.getMessage());
        }
    }
    
    public void updateStatus(Status stat) {
        sSql = "UPDATE status SET stat_id = ?, stat_status = ? WHERE stat_id = ?";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, stat.getId());
            pstmt.setString(2, stat.getStatus());
            pstmt.setInt(3, stat.getId());

            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir Status " + erro.getMessage());
        }
    }

    public void excluirStatus(int id) {

        sSql = "DELETE FROM status WHERE stat_id = ?";

        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            con.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao Exluir Status " + erro.getMessage());
        }
    }

    public Status consultaStatus(int id) {
        sSql = "SELECT * FROM status";
        ResultSet rs;
        Status stat = new Status();

        try {

            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                stat.setId(rs.getInt("stat_id"));
                stat.setStatus(rs.getString("stat.status"));
            } else {
                System.out.println("Status não cadastrado");
                stat = null;
            }

            pstmt.close();
            rs.close();
            con.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Status " + erro.getMessage());
        }
        return stat;
    }

    public List<Status> listaStatus() {
        List listaStatus = new ArrayList();
        ResultSet rs;
        Status stat;
        sSql = "SELECT * FROM Status";
        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                stat = new Status();
                stat.setId(rs.getInt(1));
                stat.setStatus(rs.getString(2));
                listaStatus.add(stat);
            }
            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar Status " + erro.getMessage());
        }
        return listaStatus;
    }

}
