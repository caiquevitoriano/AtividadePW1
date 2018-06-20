package exerciciojdbc;

import controle.ClienteDaoImpl;
import controle.Conexao;
import controle.PedidoDaoImpl;
import controle.interfaceClienteDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
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
        
          
       
        
        
            int op = 0;
            do{
                
                System.out.println("O que Deseja: ");
                System.out.println("1 - Cadastrar cliente");
                System.out.println("2 - Listar cliente");
                System.out.println("3 - Remover cliente");
                System.out.println("4 - Atualizar cliente");
                
                System.out.println("5 - Cadastrar Pedido");
                System.out.println("6 - Listar Pedidos");
                System.out.println("7 - Remover Pedido");
                System.out.println("8 - Atualizar Pedido");
                
                System.out.println("9 - Sair.");

                op = scanner.nextInt();
               
                switch (op){
                case 1:
                    cadCliente();
                    break;
                case 2:
                    listCliente();
                    break;
                case 3:
                    delCliente();
                    break;
                case 4:
                    attCliente();
                    break;
                case 5:
                    cadPedido();
                    break;
                case 6:
                    listPedido();
                    break;
                case 7:
                    attPedido();
                    break;
                case 8:
                    attCliente();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.printf("Opçao Invalida, Tente Novamente\n");
                    break;
            } 
                
        }while (op != 9);
                
                
            
          
            
            
        
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
        
        System.out.println("Digite o Caminho da foto");
        cliente.setFoto(scanner.next());
        
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
        
        System.out.println("Digite o Caminho da foto");
        cliente.setFoto(scanner.next());
        
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
    
     private static Pedido cadPedido() throws SQLException {
        
        Pedido pedido = new Pedido();

        System.out.printf("---- Cadastro de Pedido:\n\n");
        
        
        System.out.println("Digite o Valor do Pedido");
        pedido.setValor(scanner.nextFloat());
        pedido.setCliente(cliente); 
        pedido.setData(LocalDate.now());       
        
        
        PedidoDaoImpl dao = new PedidoDaoImpl();
        dao.incluir(pedido); 
        
        
        return pedido;
    }
     
    private static ArrayList<Pedido> listPedido(){
        
        return new PedidoDaoImpl().listar();
        
    }
    
    private static Pedido delPedido(){
        
        Pedido pedido = new Pedido();
        
        System.out.println("Digite o ID do pedido: ");
        pedido.setId(scanner.nextInt());
        
        PedidoDaoImpl dao = new PedidoDaoImpl();
        dao.excluir(pedido);
        
        return pedido;  
        
    }
    
    private static Pedido attPedido(){
        
        Pedido pedido = new Pedido();
        
        pedido.setCliente(cliente); 
        pedido.setData(LocalDate.now());
        
        System.out.println("Digite o Id do Pedido");
        pedido.setId(scanner.nextInt());       
        
        System.out.println("Digite o Valor do Pedido");
        pedido.setValor(scanner.nextFloat());
               
        
        
        PedidoDaoImpl dao = new PedidoDaoImpl();
        dao.alterar(pedido); 
        
        
        return pedido;
    }
    

}
