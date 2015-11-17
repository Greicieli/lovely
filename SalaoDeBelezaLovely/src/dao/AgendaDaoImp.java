package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionManager;
import modelo.Agenda;


public class AgendaDaoImp implements AgendaDao {
	
	private Connection connection;
	private final String INSERT = "INSERT INTO agenda(data, hora, procedimento, idfuncionario, idcliente) VALUES(?,?,?,?,?)";
	private final String SELECT = "SELECT * FROM agenda";
	private final String UPDATE = "UPDATE agenda SET  data = ?, hora = ?, procedimento = ?, idfuncionario = ?, idcliente = ? WHERE idagendamento = ?";
	private final String DELETE = "DELETE FROM agenda WHERE idagendamento = ?";

	 public AgendaDaoImp() {
		connection = ConnectionManager.getInstacne().getConnection();
	}

	@Override
	public void salvar(Agenda agenda) {
		try{
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(INSERT);
			ps.setObject(1, agenda.getData());
			ps.setObject(2, agenda.getHora());
			ps.setString(3, agenda.getProcedimento());
			ps.setInt(4, agenda.getFuncionario().getIdFuncionario());
			ps.setInt(5, agenda.getCliente().getIdCliente());
			
				
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			closeConnection();
		}
		
	}

	@Override
	public void alterar(Agenda agenda) {
		try{
			PreparedStatement ps = null;
			openConnection();
			
			ps = connection.prepareStatement(UPDATE);
			ps.setObject(1, agenda.getData());
			ps.setObject(2, agenda.getHora());
			ps.setString(3, agenda.getProcedimento());
			ps.setInt(4, agenda.getFuncionario().getIdFuncionario());
			ps.setInt(5, agenda.getCliente().getIdCliente());
			ps.setInt(6, agenda.getIdAgendamento());
			
				
			ps.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("Erro ao executar o insert" + ex);
		}finally{
			closeConnection();
		}
		
	}

	@Override
	public void excluir(Agenda agenda) {
		try {
			PreparedStatement ps = null;
			openConnection();
			ps = connection.prepareStatement(DELETE);
			ps.setLong(1, agenda.getIdAgendamento());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o delete: " + ex);
		} finally {
			closeConnection();
		}
		
	}

	@Override
	public List<Agenda> listarTodos() {
		List<Agenda> agendamento = new ArrayList<Agenda>();
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			openConnection();
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();

			while (rs.next()) {
				Agenda agenda = new Agenda();
				agenda.setIdAgendamento(rs.getInt("idagendamento"));
				agenda.setData(rs.getDate("data"));
				agenda.setHora(rs.getTime("hora"));
				agenda.setProcedimento(rs.getString("procedimento"));
				FuncionarioDao funcionarioDao = DAOFactory.getFuncionarioDao();
				agenda.setFuncionario(funcionarioDao.buscarPorId(rs.getInt("idfuncionario")));
				
				ClienteDao clienteDao = DAOFactory.getClienteDao();
				agenda.setCliente(clienteDao.buscarPorId(rs.getInt("idcliente")));
				
				agendamento.add(agenda);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o select da locação: " + ex);
		} finally {
			closeConnection();
		}
		return agendamento;
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
	public Agenda buscarPorId(int idAgendamento) {
		// TODO Auto-generated method stub
		return null;
	}

}
