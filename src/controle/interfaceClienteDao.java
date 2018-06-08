package controle;

import java.util.ArrayList;
import modelo.Cliente;

public interface interfaceClienteDao {
    
    public boolean incluir(Cliente cliente);
    public boolean alterar(Cliente cliente);
    public boolean excluir(Cliente cliente);
    public ArrayList<Cliente> listar();
    
}
