package exerciciojdbc;

import controle.ClienteDaoImpl;
import controle.Conexao;
import controle.interfaceClienteDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Cliente;
import modelo.Pedido;

public class ExercicioJDBC {

    private static final Scanner scanner = new Scanner(System.in);
    private static Cliente cliente = null;
    private interfaceClienteDao daoCliente;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Fazendo a conexão  com o banco de dados
        

        ArrayList<Cliente> clientes = new ArrayList<>();
        
        //System.out.println(listCliente());
        
        Cliente attCliente = attCliente();
        
        //Criando a tabela cliente no banco de dados no postgre
        /* String sql1 = "DROP TABLE Pedido;"
        + "DROP TABLE Cliente;"
        + "CREATE TABLE Cliente(id SERIAL PRIMARY KEY, foto VARCHAR(100), "
        + "nome VARCHAR(50) NOT NULL, documento VARCHAR(15) NOT NULL, "
        + "saldo REAL NOT NULL, ativo CHARACTER);";        
        stmt.executeUpdate(sql1);
        //Criando a tabela Pedido no banco de dados no postgre
        String sql2 = "CREATE TABLE Pedido"+
        "(id INT PRIMARY KEY, data DATE, cliente INT, valor REAL,"+
        "FOREIGN KEY(cliente) REFERENCES Cliente(id) ON DELETE RESTRICT ON UPDATE CASCADE)";
        stmt.executeUpdate(sql2); */

         
       
    }

    private static Cliente cadCliente() throws SQLException {
        
                
        Cliente cliente = new Cliente();

        System.out.printf("---- Cadastro:\n\n");        
       
        
        System.out.println("Digite o nome");
        cliente.setNome(scanner.next());
        
        System.out.println("Digite seu Documento");
        cliente.setDocumento(scanner.next());
        
        System.out.println("Digite seu Saldo");
        cliente.setSaldo(scanner.nextDouble());
        
        System.out.println("Digite o ativo");
        cliente.setAtivo(scanner.next());  
        
        ClienteDaoImpl dao = new ClienteDaoImpl();
        dao.incluir(cliente);       
        
        return cliente;
    }
    
    private static Cliente attCliente(){
        
        Cliente cliente = new Cliente();
        
        System.out.printf("---- Atualizar:\n\n");
        
        System.out.println("Digite o ID do cliente: ");
        cliente.setId(scanner.nextInt());
        
        System.out.println("Digite o nome");
        cliente.setNome(scanner.next());
        
        System.out.println("Digite seu Documento");
        cliente.setDocumento(scanner.next());
        
        System.out.println("Digite seu Saldo");
        cliente.setSaldo(scanner.nextDouble());
        
        System.out.println("Digite o ativo");
        cliente.setAtivo(scanner.next());
        
        ClienteDaoImpl dao = new ClienteDaoImpl();
        dao.alterar(cliente);
        
        
        return cliente;
        
       
    }
    
    private static Cliente delCliente(){
        
        Cliente cliente = new Cliente();
        
        System.out.println("Digite o ID do cliente: ");
        cliente.setId(scanner.nextInt());
        
        ClienteDaoImpl dao = new ClienteDaoImpl();
        dao.excluir(cliente);
        
        return cliente;  
        
    }
    
    private static ArrayList<Cliente> listCliente(){
        
        return new ClienteDaoImpl().listar();
        
    }
    
     private static Cliente cadPedido() throws SQLException {
        
                
        Pedido pedido = new Pedido();

        System.out.printf("---- Cadastro de Pedido:\n\n");        
       
        
        
        
        System.out.println("Digite seu Documento");
        cliente.setDocumento(scanner.next());
        
        System.out.println("Digite seu Saldo");
        cliente.setSaldo(scanner.nextDouble());
        
        System.out.println("Digite o ativo");
        cliente.setAtivo(scanner.next());  
        
        ClienteDaoImpl dao = new ClienteDaoImpl();
        dao.incluir(cliente);       
        
        return cliente;
    }
    

}
