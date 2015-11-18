package br.sc.senai.lovely.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.Cliente;


public class ClienteDao extends Dao {
	
	private Connection connection;
	private final String INSERT = "INSERT INTO cliente(nome, telefone, email, endereco) VALUES(?,?,?,?)";
	private final String SELECT = "SELECT * FROM cliente";
	private final String UPDATE = "UPDATE cliente SET  nome = ?, telefone = ?, email = ?, endereco = ? WHERE idCliente = ?";
	private final String DELETE = "DELETE FROM cliente WHERE idcliente = ?";
	
	
	
	public void salvar(Cliente cliente) {
		if (cliente.getIdCliente() == 0) {
			salvar(cliente);
		} else {
			alterar(cliente);
		}
		
	}

	private void parseCliente(Cliente cliente, PreparedStatement ps)
			throws SQLException {
		ps.setString(1, cliente.getNome());
		ps.setInt(2, cliente.getTelefone());
		ps.setString(3, cliente.getEmail());
		ps.setString(4, cliente.getEndereco());
	}
	
	public void alterar(Cliente cliente) {
		try{
			PreparedStatement ps = null;
			
			
			ps = connection.prepareStatement(UPDATE);
			parseCliente(cliente, ps);
			ps.setInt(5, cliente.getIdCliente());
				
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			
		}
		
	}
	
	public void excluir(Cliente cliente) {
		try {
			PreparedStatement ps = null;
			
			ps = connection.prepareStatement(DELETE);
			ps.setLong(1, cliente.getIdCliente());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {
			
		}
		
	}
	
	public List<Cliente> listarTodos() {
		List<Cliente>clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement ps =null;
			ResultSet rs =null;
			
			
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Cliente cliente = parseCliente(rs);
				
				clientes.add(cliente);
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao executar !!");
			
		}finally{
			
		}
		return clientes;
	}

	private Cliente parseCliente(ResultSet rs) throws SQLException {
		Cliente cliente  = new Cliente();
		cliente.setIdCliente(rs.getInt("idCliente"));
		cliente.setNome(rs.getString("nome"));
		cliente.setTelefone(rs.getInt("telefone"));
		cliente.setEmail(rs.getString("email"));
		cliente.setEndereco(rs.getString("endereco"));
		return cliente;
	}
	
	
	public Cliente buscarPorId(int idCliente) {
		
		return null;
	}
	

}
