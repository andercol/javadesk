/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Problemas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colin
 */
public class DAOProblemas extends Conexao {

    PreparedStatement pstmt;
    Connection con;
    String sSql;

    public void inserirProblemas(Problemas prob) {

        sSql = "INSERT INTO problemas (prob_nome, cat_problemas_cat_prob_id) VALUES (?,?)";

        try {

            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, prob.getNome());
            pstmt.setInt(2, prob.getCat_problemas_cat_prob_id());

            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (SQLException erro) {

            System.out.println("Erro ao inserir Problema" + erro.getMessage());

        }
    }

    public Problemas consultaProblemas(int id) {
        ResultSet rs;
        Problemas prob = new Problemas();
        sSql = "select  prob_id, prob_nome, cat_problemas_cat_prob_id,  cat_prob_nome from problemas p inner join cat_problemas cp where cp.cat_prob_id = p.cat_problemas_cat_prob_id and prob_id = ?;";

        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                prob.setId(rs.getInt("prob_id"));
                prob.setNome(rs.getString("prob_nome"));
                prob.setCat_problemas_cat_prob_id(rs.getInt("cat_problemas_cat_prob_id"));
                prob.setCategoria(rs.getString("cat_prob_nome"));

            } else {
                prob = null;
                System.out.println("Erro, Problema não encontrado ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar Problemas " + erro.getMessage());
        }
        return prob;
    }

    public Problemas consultaProblemas(String problema) {
        ResultSet rs;
        Problemas prob = new Problemas();
        sSql = "select  prob_id, prob_nome, cat_problemas_cat_prob_id,  cat_prob_nome FROM problemas p "
                + "INNER JOIN cat_problemas cp WHERE cp.cat_prob_id = p.cat_problemas_cat_prob_id and prob_nome = ?;";

        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, problema);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                prob.setId(rs.getInt("prob_id"));
                prob.setNome(rs.getString("prob_nome"));
                prob.setCat_problemas_cat_prob_id(rs.getInt("cat_problemas_cat_prob_id"));
                prob.setCategoria(rs.getString("cat_prob_nome"));

            } else {
                prob = null;
                System.out.println("Erro, Problema não encontrado ");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao Consultar Problemas " + erro.getMessage());
        }
        return prob;
    }

    public void exluirProblemas(int id) {
        sSql = "DELETE FROM problemas WHERE prob_id = ?";

        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao Excluir Problemas " + erro.getMessage());
        }
    }

    public List<Problemas> listaProblemas() {
        List listaprob = new ArrayList();
        Problemas prob;
        ResultSet rs;
        sSql = "select  prob_id, prob_nome, cat_problemas_cat_prob_id,  cat_prob_nome from problemas p inner join cat_problemas cp where cp.cat_prob_id = p.cat_problemas_cat_prob_id;";

        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                prob = new Problemas();
                prob.setId(rs.getInt("prob_id"));
                prob.setNome(rs.getString("prob_nome"));
                prob.setCat_problemas_cat_prob_id(rs.getInt("cat_problemas_cat_prob_id"));
                prob.setCategoria(rs.getString("cat_prob_nome"));

                listaprob.add(prob);
            }
            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Problemas " + erro.getMessage());
        }
        return listaprob;
    }

    public List<Problemas> listaProblemasCat(String cat) {
        List listaprob = new ArrayList();
        Problemas prob;
        ResultSet rs;
        sSql = "SELECT prob_id, prob_nome, cat_problemas_cat_prob_id, cat_prob_nome "
                + "FROM problemas p "
                + "LEFT JOIN cat_problemas cp ON cp.cat_prob_id = p.cat_problemas_cat_prob_id "
                + "WHERE cp.cat_prob_nome = ?;";

        try {
            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, cat);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                prob = new Problemas();
                prob.setId(rs.getInt("prob_id"));
                prob.setNome(rs.getString("prob_nome"));
                prob.setCat_problemas_cat_prob_id(rs.getInt("cat_problemas_cat_prob_id"));
                prob.setCategoria(rs.getString("cat_prob_nome"));

                listaprob.add(prob);
            }
            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar lista de Problemas " + erro.getMessage());
        }
        return listaprob;
    }

    public void updateProblemas(Problemas prob) {

        sSql = "UPDATE problemas SET prob_nome = ?, cat_problemas_cat_prob_id = ? where prob_id = ?";

        try {

            con = getConexao();
            pstmt = con.prepareStatement(sSql);
            pstmt.setString(1, prob.getNome());
            pstmt.setInt(2, prob.getCat_problemas_cat_prob_id());
            pstmt.setInt(3, prob.getId());

            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (SQLException erro) {

            System.out.println("Erro ao atualizar Problema" + erro.getMessage());

        }
    }

}
