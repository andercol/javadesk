/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Colin
 */
public class Conexao {
    
    static String usuario = "root";
    static String senha = "root";
    static String caminho = "jdbc:mysql://localhost:3306/JavaDesk";
    
    public Connection getConexao(){
        Connection con;
        con = null;
        
        try {
            con = DriverManager.getConnection(caminho, usuario, senha);
            
        } catch (SQLException erro) {
            System.out.println("Erro ao iniciar conexão ao bando de dados" + erro.getMessage());
        }
        return con;
    }
}
