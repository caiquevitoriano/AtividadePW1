/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import modelo.Cliente;
import modelo.Pedido;

/**
 *
 * @author caiq-
 */
public class PedidoDaoImpl implements interfacePedidoDao{

    @Override
    public boolean incluir(Pedido pedido) {
        
        try {

            Connection con = Conexao.Conectar();

            String sql = "INSERT INTO pedido(data, cliente, valor) VALUES(?, ?, ?, ?, ?);";

            PreparedStatement stat = con.prepareStatement(sql);
            
            stat.setDate(1, Date.valueOf(pedido.getData()));
            stat.setInt(2, pedido.getId());
            stat.setDouble(3, pedido.getValor()); 
            
            stat.executeUpdate();
            stat.close();   

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;    }

    @Override
    public boolean alterar(Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Pedido pedido) {
    
        try {

            Connection con = Conexao.Conectar();

            String sql = String.format("DELETE FROM predido WHERE id = ?");

            PreparedStatement stat = con.prepareStatement(sql);

            stat.setInt(1, pedido.getId());

            stat.executeUpdate();
            stat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    }

    @Override
    public ArrayList<Pedido> listar() {
        
        ArrayList<Pedido> pedidos = new ArrayList();
        
        try{
            
            Connection con = Conexao.Conectar();
            
            String sql = String.format("SELECT * FROM pedido");
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT

            );
            
            ResultSet rs = stmt.executeQuery(sql);

            CachedRowSet rowSet = new CachedRowSetImpl();

            rowSet.populate(rs);
            
            while (rowSet.next())
                pedidos.add(
                        new Pedido(
                                rowSet.getInt("id"),
                                LocalDate.parse(rowSet.getString("data")),
                                rowSet.getDouble("valor")                               
                        )
                );
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
