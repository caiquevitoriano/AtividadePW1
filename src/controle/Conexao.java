/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author caiq-
 */
public class Conexao {
    
    public static Connection Conectar() throws SQLException{
        
        String url = "jdbc:postgresql://localhost:5433/atividadePW1";
        String user = "postgres";
        String password = "2718";             
        
        Connection con = DriverManager.getConnection(url, user, password);
        
        return con;
    }
    
   
    
    

    
}
