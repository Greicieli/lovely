package br.sc.senai.lovely.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.TipoDeProduto;

public class TipoDeProdutoDao extends Dao {

	private Connection connection;
	private final String INSERT = "INSERT INTO tipoDeProduto (tipoDeProduto) VALUES (?)";
	private final String UPDATE = "UPDATE tipoDeProduto SET tipoDeProduto = ? WHERE idTipoDeProduto = ?";
	private final String DELETE = "DELETE FROM tipoDeProduto WHERE idTipoDeProduto = ?";
	private final String SELECT = "SELECT * FROM tipoDeProduto";
	private final String SELECT_ID = "SELECT * FROM tipoDeProduto WHERE idTipoDeProduto = ?";

	public void salvar(TipoDeProduto tipoDeProduto) throws Exception {
		if (tipoDeProduto.getIdTipoDeProduto() == 0) {
			inserir(tipoDeProduto);
		} else {
			alterar(tipoDeProduto);
		}
	}

	private void inserir(TipoDeProduto tipoDeProduto) throws Exception {
		try {

			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setString(1, tipoDeProduto.getTipoDeProduto());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar");
		}

	}

	public void alterar(TipoDeProduto tipoDeProduto) {
		try {
			PreparedStatement ps = null;

			ps = connection.prepareStatement(UPDATE);
			ps.setString(1, tipoDeProduto.getTipoDeProduto());
			ps.setLong(2, tipoDeProduto.getIdTipoDeProduto());

			ps.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Erro ao executar o update: " + ex);
		} finally {

		}

	}

	public void excluir(TipoDeProduto tipoDeProduto) {
		try {
			PreparedStatement ps = null;

			ps = connection.prepareStatement(DELETE);

			ps.setLong(1, tipoDeProduto.getIdTipoDeProduto());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {

		}

	}

	public List<TipoDeProduto> listarTodos() {
		List<TipoDeProduto> tipoDeProdutos = new ArrayList<TipoDeProduto>();
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();

			while (rs.next()) {
				TipoDeProduto tipoDeProduto = new TipoDeProduto();
				tipoDeProduto.setIdTipoDeProduto(rs.getInt("idTipoDeProduto"));
				tipoDeProduto.setTipoDeProduto(rs.getString("tipoDeProduto"));
				tipoDeProdutos.add(tipoDeProduto);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o select do tipoDeProduto: "
					+ ex);
		} finally {

		}
		return tipoDeProdutos;

	}

	public TipoDeProduto buscarPorId(int id) {
		TipoDeProduto tipoDeProduto = null;
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = connection.prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				tipoDeProduto = new TipoDeProduto();
				tipoDeProduto.setIdTipoDeProduto(rs.getInt("idTipoDeProduto"));
				tipoDeProduto.setTipoDeProduto(rs.getString("tipoDeProduto"));
			}

		} catch (Exception ex) {
			System.out.println("Erro ao executar o select por id: " + ex);
		} finally {

		}
		return tipoDeProduto;
	}

}
