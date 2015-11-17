package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionManager;
import modelo.Produto;

public class ProdutoDaoImp implements ProdutoDao{
	
	private Connection connection;
	private final String INSERT = "INSERT INTO produto(descricao, valor, quantidade, fk_idTipoDeProduto ) VALUES (?,?,?,?)";
	private final String SELECT = "SELECT * FROM produto"; 
	private final String UPDATE = "UPDATE produto SET descricao = ?, valor = ?,quantidade = ?, tipoDeProduto = ? WHERE idProduto = ?";
	private final String DELETE = "DELETE FROM produto WHERE idProduto = ?";
	@Override
	public void salvar(Produto produto) {
		try{
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(INSERT);
			ps.setString(1, produto.getDescricao());
			ps.setDouble(2, produto.getValor());
			ps.setInt(3, produto.getQuantidade());
			ps.setInt(4, produto.getTipoDeProduto().getIdTipoDeProduto());
			
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			closeConnection();
		}
		
	}
			
	
	@Override
	public void alterar(Produto produto) {
		try{
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(UPDATE);
			ps.setString(1, produto.getDescricao());
			ps.setDouble(2, produto.getValor());
			ps.setInt(3, produto.getQuantidade());
			ps.setInt(4, produto.getTipoDeProduto().getIdTipoDeProduto());
			ps.setInt(5, produto.getIdProduto());
			
			
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			closeConnection();
		}
		
	}
	@Override
	public void excluir(Produto produto) {
		try {
			PreparedStatement ps = null;
			openConnection();
			ps = connection.prepareStatement(DELETE);
			ps.setInt(1, produto.getIdProduto());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {
			closeConnection();
		}
		
		
	}
	@Override
	public List<Produto> listarTodos() {
		List<Produto>produtos = new ArrayList<Produto>();
		try {
			PreparedStatement ps =null;
			ResultSet rs =null;
			openConnection();
			
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Produto produto  = new Produto();
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setValor(rs.getDouble("valor"));
							
				TipoDeProdutoDao tipoDeProdutoDao = DAOFactory.getTipoDeProdutoDao();
				produto.setTipoDeProduto(tipoDeProdutoDao.buscarPorId(rs.getInt("idTipoDeProduto")));
				
				produtos.add(produto);
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao executar !!");
			
		}finally{
			closeConnection();
		}
		return produtos;
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
	public Produto buscarPorId(int idProduto) {
		// TODO Auto-generated method stub
		return null;
	}


}
