package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionManager;
import modelo.TipoDeProduto;

public class TipoDeProdutoDaoImp implements TipoDeProdutoDao {
	
	private Connection connection;
	private final String INSERT = "INSERT INTO tipoDeProduto (tipoDeProduto) VALUES (?)";
	private final String UPDATE = "UPDATE tipoDeProduto SET tipoDeProduto = ? WHERE idTipoDeProduto = ?";
	private final String DELETE = "DELETE FROM tipoDeProduto WHERE idTipoDeProduto = ?";
	private final String SELECT = "SELECT * FROM tipoDeProduto"; 
	private final String SELECT_ID = "SELECT * FROM tipoDeProduto WHERE idTipoDeProduto = ?";
		
	@Override
	public void salvar(TipoDeProduto tipoDeProduto) {
		try {
		PreparedStatement ps = null;
		openConnection();
		
		ps = connection.prepareStatement(INSERT);
		ps.setString(1, tipoDeProduto.getTipoDeProduto());
		
		ps.executeUpdate();
		
	} catch (SQLException ex) {
		System.out.println("Erro ao executar o insert: " + ex);
	} finally {
		closeConnection();
	}
}

	@Override
	public void alterar(TipoDeProduto tipoDeProduto) {
		try {
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(UPDATE);
			ps.setString(1, tipoDeProduto.getTipoDeProduto());
			ps.setInt(2, tipoDeProduto.getIdTipoDeProduto());
			
			ps.executeUpdate();
			
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o update: " + ex);
		} finally {
			closeConnection();
		}
		
	}

	@Override
	public void excluir(TipoDeProduto tipoDeProduto) {
		try{
			PreparedStatement ps = null;
			openConnection();
			ps = connection.prepareStatement(DELETE);
			
			ps.setInt(1, tipoDeProduto.getIdTipoDeProduto());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {
			closeConnection();
		}
		
	}

	@Override
	public List<TipoDeProduto> listarTodos() {
		List<TipoDeProduto> tipoDeProdutos = new ArrayList<TipoDeProduto>();
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			openConnection();
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				TipoDeProduto tipoDeProduto = new TipoDeProduto();
				tipoDeProduto.setIdTipoDeProduto(rs.getInt("idTipoDeProduto"));
				tipoDeProduto.setTipoDeProduto(rs.getString("tipoDeProduto"));
				tipoDeProdutos.add(tipoDeProduto);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o select do tipoDeProduto: " + ex);
		} finally {
			closeConnection();
		}
		return tipoDeProdutos;
		
	}

	@Override
	public void openConnection() {
		connection = ConnectionManager.getInstacne().getConnection();
		
	}

	@Override
	public void closeConnection() {
		if (!isConnectionClosed()) {
			ConnectionManager.getInstacne().closeConnection(connection);
		}
		
	}

	@Override
	public boolean isConnectionClosed() {
		try {
			if (connection.isClosed()) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Conexão com problema!");
		}
		return false;
	}

	@Override
	public TipoDeProduto buscarPorId(int id) {
		TipoDeProduto tipoDeProduto = null;
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			openConnection();
			ps = connection.prepareStatement(SELECT_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()){
				tipoDeProduto = new TipoDeProduto();
				tipoDeProduto.setIdTipoDeProduto(rs.getInt("idTipoDeProduto"));
				tipoDeProduto.setTipoDeProduto(rs.getString("tipoDeProduto"));
			}
			
		} catch (Exception ex) {
			System.out.println("Erro ao executar o select por id: " + ex);
		}finally{
			closeConnection();
		}
		return tipoDeProduto;
	}

		
}
