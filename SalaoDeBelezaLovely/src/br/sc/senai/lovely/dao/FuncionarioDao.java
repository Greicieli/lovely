package br.sc.senai.lovely.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.Funcionario;



public class FuncionarioDao extends Dao {
	
	
	private final String INSERT = "INSERT INTO funcionario( nome,  funcao, email) VALUES(?,?,?)";
	private final String SELECT = "SELECT * FROM funcionario";
	private final String UPDATE = "UPDATE cliente SET nome = ?, funcao = ?, email = ?, WHERE idFuncionario = ?";
	private final String DELETE = "DELETE FROM funcionario WHERE idFuncionario = ?";
	private final String SELECT_ID = "SELECT * FROM funcionario WHERE idFuncionario = ?";
	

	public void salvar(Funcionario funcionario) throws Exception {
		if (funcionario.getIdFuncionario() == null) {
			inserir(funcionario);
		} else {
			alterar(funcionario);
		}
	}
	
	private void alterar(Funcionario funcionario) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			parseFuncionario(funcionario, ps);
			ps.setLong(4, funcionario.getIdFuncionario());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o usuario");
		}
		
	}
		
	
	private void inserir(Funcionario funcionario) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			parseFuncionario(funcionario, ps);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o usuario");
		}
		
	}

	
	
	public void excluir(Long idFuncionario) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(4, idFuncionario);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a executar o delete: " + e);
			throw new Exception("Erro ao tentar excluir");
		}
	}
	public Funcionario buscarPorId(Long idFuncionario) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, idFuncionario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Funcionario funcionario = parseFuncionario(rs);
				return funcionario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return null;
	}

	public List<Funcionario> listarTodos() throws Exception {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = parseFuncionario(rs);
				funcionarios.add(funcionario);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return funcionarios;
	}

	private Funcionario parseFuncionario(ResultSet rs) throws SQLException {
		Funcionario funcionario = new Funcionario();
		funcionario.setIdFuncionario(rs.getLong("idFuncionario"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setFuncao(rs.getString("funcao"));
		funcionario.setEmail(rs.getString("email"));
		return funcionario;
	}
	
	
	private void parseFuncionario(Funcionario funcionario, PreparedStatement ps)throws SQLException {
		ps.setString(1, funcionario.getNome());
		ps.setString(2, funcionario.getFuncao());
		ps.setString(3, funcionario.getEmail());
		

	}


	

}
