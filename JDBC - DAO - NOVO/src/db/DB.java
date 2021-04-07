/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Murilo
 */


public class DB {
    
    private static final String USERNAME = "root";
    private static final String SENHA = "musica06";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/coursejdbc";
    
    private static Connection conn = null;
    
    public static Connection getConnection(){
        
        if(conn == null){
            
            try{
            
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, SENHA);
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        
        return conn;
    }
    
    public static void closeConnection(){
        if(conn != null){
            try{
            conn.close();
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
    
    public static void closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException ex) {
                throw new DbException(ex.getMessage());
            }
        }
    }
    
       public static void closeResuldSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DbException(ex.getMessage());
            }
        }
    }

    
}
