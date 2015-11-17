package controle;

import java.util.List;

import dao.AgendaDao;
import dao.DAOFactory;
import modelo.Agenda;

public class AgendaControle {
	
	public void salvar(Agenda agenda) throws Exception {
		if(agenda.getCliente()== null || agenda.getCliente().getIdCliente() == 0){
			throw new Exception("É obrigatório selecionar um cliente !");
		}
		if (agenda.getFuncionario() == null || agenda.getFuncionario().getIdFuncionario() == 0){
			throw new Exception("É obrigatório selecionar um cliente !");
		}
		
		if (agenda.getData() == null) {
			throw new Exception("A Data é obrigatória!");
		}
		if(agenda.getProcedimento() ==null){
			throw new Exception("O Procedimento é obrigatório");
		}
		
		AgendaDao agendaDao =DAOFactory.getAgendaDao();
		if(agenda.getIdAgendamento() ==0){
			agendaDao.salvar(agenda);
		}else{
			agendaDao.alterar(agenda);
		}
	}
		
	public void excluir(Agenda agenda) {
		AgendaDao agendaDao = DAOFactory.getAgendaDao();
		agendaDao.excluir(agenda);
	}
	
	public List<Agenda> listarTodos() {
		AgendaDao agendaDao = DAOFactory.getAgendaDao();
		return agendaDao.listarTodos();	
	}
	
}