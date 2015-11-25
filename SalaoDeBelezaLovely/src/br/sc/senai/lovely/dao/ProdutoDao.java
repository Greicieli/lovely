package br.sc.senai.lovely.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.Produto;

public class ProdutoDao extends Dao {

	
	private final String INSERT = "INSERT INTO produto(descricao, valor, quantidade) VALUES (?,?,?)";
	private final String SELECT = "SELECT * FROM produto";
	private final String UPDATE = "UPDATE produto SET descricao = ?, valor = ?,quantidade = ? WHERE idProduto = ?";
	private final String DELETE = "DELETE FROM produto WHERE idProduto = ?";
	private final String SELECT_ID = "SELECT * FROM produto WHERE idProduto = ?";

	
	
	public void salvar(Produto produto) throws Exception {
		if (produto.getIdProduto() == null) {
			inserir(produto);
		}else{
			alterar(produto);
		}
		
	}


	private void inserir(Produto produto) throws Exception {
		try {

			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setString(1, produto.getDescricao());
			ps.setInt(2, produto.getQuantidade());
			ps.setDouble(3, produto.getValor());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar");
		}

	}

	public void alterar(Produto produto) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			parseProduto(produto, ps);
			ps.setLong(4, produto.getIdProduto());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o usuario");
		}

	}

	private void parseProduto(Produto produto, PreparedStatement ps)
			throws SQLException {
			parseProduto(produto, ps);
	}

	public void excluir(Long idProduto) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(1, idProduto);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a executar o delete: " + e);
			throw new Exception("Erro ao tentar excluir");
		}
	}

	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getLong("idProduto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setValor(rs.getDouble("valor"));


				produtos.add(produto);
			}

		} catch (SQLException ex) {
			System.out.println("Erro ao executar !!");

		} finally {

		}
		return produtos;
	}

	public Produto buscarPorId(Long idProduto) {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(SELECT_ID);
			preparedStatement.setLong(1, idProduto);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				parseProduto(resultSet);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void parseProduto(ResultSet rs) throws SQLException {
		Produto produto = new Produto();

		produto.setIdProduto(rs.getLong("idProduto"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setQuantidade(rs.getInt("quantidade"));
		produto.setValor(rs.getDouble("valor"));
	}

	
}
