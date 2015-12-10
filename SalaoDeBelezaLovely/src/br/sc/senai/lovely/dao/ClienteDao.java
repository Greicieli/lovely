package br.sc.senai.lovely.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.Cliente;


public class ClienteDao extends Dao {
	private static final String SELECT_USUARIO = "SELECT * FROM cliente WHERE email = ?";
	private final String INSERT = "INSERT INTO cliente(nome, telefone, email, endereco, senha) VALUES(?,?,?,?,?)";
	private final String SELECT = "SELECT * FROM cliente";
	private final String UPDATE = "UPDATE cliente SET  nome = ?, telefone = ?, email = ?, endereco = ?, senha = ? WHERE idCliente = ?";
	private final String DELETE = "DELETE FROM cliente WHERE idCliente = ?";
	private final String SELECT_ID = "SELECT * FROM cliente WHERE idCliente = ?";
	
	
	
	public void salvar(Cliente cliente)  throws Exception {
		if (cliente.getIdCliente() == 0) {
			inserir(cliente);
		} else {
			alterar(cliente);
		}
		
	}

	
	
	public void alterar(Cliente cliente) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			parseCliente(cliente, ps);
			ps.setLong(6, cliente.getIdCliente());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o usuario");
		}
		
		
	}
	
	private void inserir(Cliente cliente) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			parseCliente(cliente, ps);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o usuario");
		}
		
	}
	
	public void excluir(Long idCliente) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(1, idCliente);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a executar o delete: " + e);
			throw new Exception("Erro ao tentar excluir");
		}
		
	}
	
	public List<Cliente> listarTodos() throws Exception {
		List<Cliente>clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cliente = parseCliente(rs);
				clientes.add(cliente);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return clientes;
	}
	
		public Cliente buscarPorId(Long idCliente) {
			try {
				PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
				ps.setLong(1, idCliente);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Cliente cliente = parseCliente(rs);
					return cliente;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao executar o select de user: " + e);
			}
			return null;
			}
	
	
		public Cliente buscarPorEmail(String email) {
			try {
				PreparedStatement ps = getConnection().prepareStatement(SELECT_USUARIO);
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Cliente cliente = parseCliente(rs);
					return cliente;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao executar o select de user: " + e);
			}
			return null;
			}
	
	private Cliente parseCliente(ResultSet rs) throws SQLException {
		Cliente cliente  = new Cliente();
		cliente.setIdCliente(rs.getLong("idCliente"));
		cliente.setNome(rs.getString("nome"));
		cliente.setTelefone(rs.getString("telefone"));
		cliente.setEmail(rs.getString("email"));
		cliente.setEndereco(rs.getString("endereco"));
		cliente.setSenha(rs.getString("senha"));;
		cliente.setAdmin(rs.getString("admin"));
		return cliente;
	}
	private void parseCliente(Cliente cliente, PreparedStatement ps)
			throws SQLException {
		ps.setString(1, cliente.getNome());
		ps.setString(2, cliente.getTelefone());
		ps.setString(3, cliente.getEmail());
		ps.setString(4, cliente.getEndereco());
		ps.setString(5, cliente.getSenha());
		
		
	}
	
	
	

}
