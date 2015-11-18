package br.sc.senai.lovely.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.Produto;


public class ProdutoDao extends Dao{
	
	private Connection connection;
	private final String INSERT = "INSERT INTO produto(descricao, valor, quantidade, fk_idTipoDeProduto ) VALUES (?,?,?,?)";
	private final String SELECT = "SELECT * FROM produto"; 
	private final String UPDATE = "UPDATE produto SET descricao = ?, valor = ?,quantidade = ?, tipoDeProduto = ? WHERE idProduto = ?";
	private final String DELETE = "DELETE FROM produto WHERE idProduto = ?";
	
	ProdutoDao dao;
	
	public void salvar(Produto produto) {
		if (produto.getIdProduto() == 0) {
			dao.salvar(produto);
		} else {
			dao.alterar(produto);
		}
		
	}
			
	
	
	public void alterar(Produto produto) {
		try{
			PreparedStatement ps = null;
			
			
			ps = connection.prepareStatement(UPDATE);
			parseProduto(produto, ps);
			
			
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			
		}
		
	}



	private void parseProduto(Produto produto, PreparedStatement ps)
			throws SQLException {
		ps.setString(1, produto.getDescricao());
		ps.setDouble(2, produto.getValor());
		ps.setInt(3, produto.getQuantidade());
		ps.setInt(4, produto.getTipoDeProduto().getIdTipoDeProduto());
		ps.setInt(5, produto.getIdProduto());
	}
	
	public void excluir(Produto produto) {
		try {
			PreparedStatement ps = null;
			
			ps = connection.prepareStatement(DELETE);
			ps.setInt(1, produto.getIdProduto());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {
			
		}
		
		
	}
	
	public List<Produto> listarTodos() {
		List<Produto>produtos = new ArrayList<Produto>();
		try {
			PreparedStatement ps =null;
			ResultSet rs =null;
			
			
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Produto produto  = new Produto();
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setValor(rs.getDouble("valor"));
							
				//TipoDeProdutoDao tipoDeProdutoDao = TipoDeProdutoDao();
				//produto.setTipoDeProduto(tipoDeProdutoDao.buscarPorId(rs.getInt("idTipoDeProduto")));
				
				produtos.add(produto);
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao executar !!");
			
		}finally{
			
		}
		return produtos;
	} 
	
	
	public Produto buscarPorId(int idProduto) {
		// TODO Auto-generated method stub
		return null;
	}


}
