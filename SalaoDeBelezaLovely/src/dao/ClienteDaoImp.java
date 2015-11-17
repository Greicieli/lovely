package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionManager;
import modelo.Cliente;


public class ClienteDaoImp implements ClienteDao {
	
	private Connection connection;
	private final String INSERT = "INSERT INTO cliente(nome, telefone, email, endereco) VALUES(?,?,?,?)";
	private final String SELECT = "SELECT * FROM cliente";
	private final String UPDATE = "UPDATE cliente SET  nome = ?, telefone = ?, email = ?, endereco = ? WHERE idCliente = ?";
	private final String DELETE = "DELETE FROM cliente WHERE idcliente = ?";
	
	
	@Override
	public void salvar(Cliente cliente) {
		try{
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(INSERT);
			ps.setString(1, cliente.getNome());
			ps.setInt(2, cliente.getTelefone());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getEndereco());
				
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			closeConnection();
		}
		
	}
	@Override
	public void alterar(Cliente cliente) {
		try{
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(UPDATE);
			ps.setString(1, cliente.getNome());
			ps.setInt(2, cliente.getTelefone());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getEndereco());
			ps.setInt(5, cliente.getIdCliente());
				
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			closeConnection();
		}
		
	}
	@Override
	public void excluir(Cliente cliente) {
		try {
			PreparedStatement ps = null;
			openConnection();
			ps = connection.prepareStatement(DELETE);
			ps.setLong(1, cliente.getIdCliente());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {
			closeConnection();
		}
		
	}
	@Override
	public List<Cliente> listarTodos() {
		List<Cliente>clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement ps =null;
			ResultSet rs =null;
			openConnection();
			
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Cliente cliente  = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setTelefone(rs.getInt("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setEndereco(rs.getString("endereco"));
				
				clientes.add(cliente);
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao executar !!");
			
		}finally{
			closeConnection();
		}
		return clientes;
	}
	@Override
	public void openConnection() {
		connection = ConnectionManager.getInstacne().getConnection();		

		
	}
	@Override
	public void closeConnection() {
		if(!isConnectionClosed()){
			ConnectionManager.getInstacne().closeConnection(connection);
		}
		
	}
	@Override
	public boolean isConnectionClosed() {
		try{
			if(connection.isClosed()){
				return true;
			}
		}catch (SQLException ex) {
			System.out.println("Conexão com problema!");
		}
		return false;
	}
	@Override
	public Cliente buscarPorId(int idCliente) {
		
		return null;
	}
	

}
