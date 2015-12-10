package br.sc.senai.lovely.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senai.lovely.dominio.Agenda;
import br.sc.senai.lovely.dominio.Cliente;
import br.sc.senai.lovely.dominio.Funcionario;





public class AgendaDao extends Dao  {
	
	
	private final String INSERT = "INSERT INTO agenda(data, hora, procedimento, idfuncionario, idcliente) VALUES(?,?,?,?,?)";
	private final String SELECT = "SELECT * FROM agenda";
	private final String UPDATE = "UPDATE agenda SET  data = ?, hora = ?, procedimento = ?, idfuncionario = ?, idcliente = ? WHERE idAgendamento = ?";
	private final String DELETE = "DELETE FROM agenda WHERE idAgendamento = ?";
	private final String SELECT_ID = "SELECT * FROM agenda WHERE idAgendamento = ?";

	

	public void salvar(Agenda agenda ) throws Exception {
		if(agenda.getIdAgendamento() ==0){
			inserir(agenda);
		}else{
			alterar(agenda);
		}
		
	}

	

	
	public void alterar(Agenda agenda) throws Exception {
		try{
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			parseAgenda(agenda, ps);
			ps.setLong(6, agenda.getIdAgendamento());			
				
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o a agenda");
			
		}
		
	}

	private void inserir(Agenda agenda) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			parseAgenda(agenda, ps);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o agendamento");
		}
		
	}
	
	public void excluir(Long idAgendamento) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(1, idAgendamento);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o usuario");
		}
		
	}

	
	public List<Agenda> listarTodos() throws Exception {
		List<Agenda> agendamento = new ArrayList<Agenda>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				Agenda agenda = parseAgenda(rs);
				
				agendamento.add(agenda);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o select da locação: " + ex);
		
		}
			return agendamento;
	}

	public Agenda buscarPorId(Long idAgendamento) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, idAgendamento);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Agenda agenda = parseAgenda(rs);
				return agenda;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return null;
	}

	
	private void parseAgenda(Agenda agenda, PreparedStatement ps)
			throws SQLException {
		ps.setObject(1, agenda.getData());
		ps.setObject(2, agenda.getHora());
		ps.setString(3, agenda.getProcedimento());
		ps.setLong(4, agenda.getFuncionario().getIdFuncionario());
		ps.setLong(5, agenda.getCliente().getIdCliente());
	}
	
	private Agenda parseAgenda(ResultSet rs) throws SQLException {
		Agenda agenda = new Agenda();
		agenda.setFuncionario(new Funcionario());
		agenda.setCliente(new Cliente());
		agenda.setIdAgendamento(rs.getLong("idAgendamento"));
		agenda.setData(rs.getDate("data"));
		agenda.setHora(rs.getTime("hora"));
		agenda.setProcedimento(rs.getString("procedimento"));
		agenda.getFuncionario().setIdFuncionario(rs.getLong("idFuncionario"));
		agenda.getCliente().setIdCliente(rs.getLong("idCliente"));
		return agenda;
	}
}
