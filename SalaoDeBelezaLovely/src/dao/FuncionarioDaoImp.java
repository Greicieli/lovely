package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionManager;
import modelo.Funcionario;

public class FuncionarioDaoImp implements FuncionarioDao {
	
	private Connection connection;
	private final String INSERT = "INSERT INTO funcionario( nome,  funcao, email) VALUES(?,?,?)";
	private final String SELECT = "SELECT * FROM funcionario";
	private final String UPDATE = "UPDATE cliente SET nome = ?, funcao = ?, email = ?, WHERE idFuncionario = ?";
	private final String DELETE = "DELETE FROM funcionario WHERE idFuncionario = ?";
	

	@Override
	public void salvar(Funcionario funcionario) {
		try{
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(INSERT);
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getFuncao());
			ps.setString(3, funcionario.getEmail());
			
ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			closeConnection();
		}	
	}

	@Override
	public void alterar(Funcionario funcionario) {
		try{
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(UPDATE);
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getFuncao());
			ps.setString(3, funcionario.getEmail());
			ps.setInt(4, funcionario.getIdFuncionario());
			
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			closeConnection();
		}
		
	}

	@Override
	public void excluir(Funcionario funcionario) {
		try {
			PreparedStatement ps = null;
			openConnection();
			ps = connection.prepareStatement(DELETE);
			ps.setLong(1, funcionario.getIdFuncionario());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {
			closeConnection();
		}
		
	}

	@Override
	public List<Funcionario> listarTodos() {
		List<Funcionario>funcionarios = new ArrayList<Funcionario>();
		try {
			PreparedStatement ps =null;
			ResultSet rs =null;
			openConnection();
			
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();
			
			while(rs.next()){
			Funcionario funcionario = new Funcionario();
			funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setFuncao(rs.getString("funcao"));
			funcionario.setEmail(rs.getString("email"));
			funcionarios.add(funcionario);
			
				}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao executar !!" + ex);
			
		}finally{
			closeConnection();
		}
		return funcionarios;
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
	public Funcionario buscarPorId(int idFuncionario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
