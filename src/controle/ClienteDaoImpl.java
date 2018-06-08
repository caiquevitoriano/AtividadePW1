/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import modelo.Cliente;

/**
 *
 * @author caiq-
 */
public class ClienteDaoImpl implements interfaceClienteDao {

    @Override
    public boolean incluir(Cliente cliente) {

        try {

            Connection con = Conexao.Conectar();

            String sql = "INSERT INTO Cliente(foto, nome, documento, saldo, ativo) VALUES(?, ?, ?, ?, ?);";

            PreparedStatement stat = con.prepareStatement(sql);

            stat.setString(1, "foto");
            stat.setString(2, cliente.getNome());
            stat.setString(3, cliente.getDocumento());
            stat.setDouble(4, cliente.getSaldo());
            stat.setString(5, cliente.getAtivo());
            stat.executeUpdate();
            stat.close();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean alterar(Cliente cliente) {

        try {

            Connection con = Conexao.Conectar();

            String sql = String.format("UPDATE cliente SET nome = ?, documento = ?, saldo = ?, ativo = ? WHERE id = ? ");

            PreparedStatement stat = con.prepareStatement(sql);

           
            stat.setString(1, cliente.getNome());
            stat.setString(2, cliente.getDocumento());
            stat.setDouble(3, cliente.getSaldo());
            stat.setString(4, cliente.getAtivo());
            stat.setInt(5, cliente.getId());

            stat.executeUpdate();
            stat.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    @Override
    public boolean excluir(Cliente cliente) {

        try {

            Connection con = Conexao.Conectar();

            String sql = String.format("DELETE FROM cliente WHERE id = ?");

            PreparedStatement stat = con.prepareStatement(sql);

            stat.setInt(1, cliente.getId());

            stat.executeUpdate();
            stat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public ArrayList<Cliente> listar() {

        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            
            Connection con = Conexao.Conectar();
            
            String sql = String.format("SELECT * FROM cliente");
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT
            );
            
            ResultSet rs = stmt.executeQuery(sql);

            CachedRowSet rowSet = new CachedRowSetImpl();

            rowSet.populate(rs);
            
            while (rowSet.next())
                clientes.add(
                        new Cliente(
                                rowSet.getInt("id"),
                                rowSet.getString("nome"),
                                rowSet.getString("documento"),
                                rowSet.getDouble("saldo"),
                                rowSet.getString("ativo"),
                                rowSet.getString("foto")
                        )
                );
            
            rowSet.close();
            rs.close();
            stmt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return clientes;
        
    }

}
