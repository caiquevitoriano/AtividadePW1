/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import modelo.Pedido;

/**
 *
 * @author caiq-
 */
public interface interfacePedidoDao {
    
    public boolean incluir(Pedido pedido);
    public boolean alterar(Pedido pedido);
    public boolean excluir(Pedido pedido);
    public ArrayList<Pedido> listar();
    
}
