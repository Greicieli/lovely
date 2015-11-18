package br.sc.senai.lovely.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.Funcionario;


public class FuncionarioDao extends Dao {
	
	private Connection connection;
	private final String INSERT = "INSERT INTO funcionario( nome,  funcao, email) VALUES(?,?,?)";
	private final String SELECT = "SELECT * FROM funcionario";
	private final String UPDATE = "UPDATE cliente SET nome = ?, funcao = ?, email = ?, WHERE idFuncionario = ?";
	private final String DELETE = "DELETE FROM funcionario WHERE idFuncionario = ?";
	
	FuncionarioDao dao;
	
	public void salvar(Funcionario funcionario) {
		if(funcionario.getIdFuncionario()==0){
			dao.salvar(funcionario);
		}else{
			dao.alterar(funcionario);
		}
	}
	
	public void alterar(Funcionario funcionario) {
		try{
			PreparedStatement ps = null;
			
			
			ps = connection.prepareStatement(UPDATE);
			parseFuncionario(funcionario, ps);
			
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			
		}
		
	}


	private void parseFuncionario(Funcionario funcionario, PreparedStatement ps)
			throws SQLException {
		ps.setString(1, funcionario.getNome());
		ps.setString(2, funcionario.getFuncao());
		ps.setString(3, funcionario.getEmail());
		ps.setInt(4, funcionario.getIdFuncionario());
	}

	
	public void excluir(Funcionario funcionario) {
		try {
			PreparedStatement ps = null;
			
			ps = connection.prepareStatement(DELETE);
			ps.setLong(1, funcionario.getIdFuncionario());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {
			
		}
		
	}

	
	public List<Funcionario> listarTodos() {
		List<Funcionario>funcionarios = new ArrayList<Funcionario>();
		try {
			PreparedStatement ps =null;
			ResultSet rs =null;
			
			
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();
			
			while(rs.next()){
			Funcionario funcionario = new Funcionario();
			parseFuncionario(funcionarios, rs, funcionario);
			
				}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao executar !!" + ex);
			
		}finally{
			
		}
		return funcionarios;
	}


	private void parseFuncionario(List<Funcionario> funcionarios, ResultSet rs,
			Funcionario funcionario) throws SQLException {
		funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setFuncao(rs.getString("funcao"));
		funcionario.setEmail(rs.getString("email"));
		funcionarios.add(funcionario);
	}

	
		
	public Funcionario buscarPorId(int idFuncionario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
