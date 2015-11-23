package br.sc.senai.lovely.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.Agenda;


public class AgendaDao extends Dao  {
	
	private Connection connection;
	private final String INSERT = "INSERT INTO agenda(data, hora, procedimento, idfuncionario, idcliente) VALUES(?,?,?,?,?)";
	private final String SELECT = "SELECT * FROM agenda";
	private final String UPDATE = "UPDATE agenda SET  data = ?, hora = ?, procedimento = ?, idfuncionario = ?, idcliente = ? WHERE idagendamento = ?";
	private final String DELETE = "DELETE FROM agenda WHERE idagendamento = ?";

	

	public void salvar(Agenda agenda ) {
		if(agenda.getIdAgendamento() ==0){
			salvar(agenda);
		}else{
			alterar(agenda);
		}
		
	}

	private void parseAgenda(Agenda agenda, PreparedStatement ps)
			throws SQLException {
		ps.setObject(1, agenda.getData());
		ps.setObject(2, agenda.getHora());
		ps.setString(3, agenda.getProcedimento());
		ps.setLong(4, agenda.getFuncionario().getIdFuncionario());
		ps.setInt(5, agenda.getCliente().getIdCliente());
	}

	
	public void alterar(Agenda agenda) {
		try{
			PreparedStatement ps = null;
		
			
			ps = connection.prepareStatement(UPDATE);
			parseAgenda(agenda, ps);
			ps.setInt(6, agenda.getIdAgendamento());
			
				
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			
		}
		
	}

	
	public void excluir(Agenda agenda) {
		try {
			PreparedStatement ps = null;
			
			ps = connection.prepareStatement(DELETE);
			ps.setLong(1, agenda.getIdAgendamento());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o delete: " + ex);
		} finally {
		}
		
	}

	
	public List<Agenda> listarTodos() {
		List<Agenda> agendamento = new ArrayList<Agenda>();
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
		
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();

			while (rs.next()) {
				//FuncionarioDao funcionarioDao = dao.getFuncionarioDao();
				//ClienteDao clienteDao = dao.getClienteDao();
				Agenda agenda = new Agenda();
				agenda.setIdAgendamento(rs.getInt("idagendamento"));
				agenda.setData(rs.getDate("data"));
				agenda.setHora(rs.getTime("hora"));
				agenda.setProcedimento(rs.getString("procedimento"));
				//agenda.setFuncionario(funcionarioDao.buscarPorId(rs.getInt("idfuncionario")));
				//agenda.setCliente(clienteDao.buscarPorId(rs.getInt("idcliente")));
				
				agendamento.add(agenda);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o select da locação: " + ex);
		} finally {
			
		}
		return agendamento;
	}

	
	public Agenda buscarPorId(int idAgendamento) {
		// TODO Auto-generated method stub
		return null;
	}

}
