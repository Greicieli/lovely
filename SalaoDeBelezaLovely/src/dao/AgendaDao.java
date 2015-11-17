package dao;

import modelo.Agenda;

public interface AgendaDao extends DAO <Agenda> {
	
	public Agenda buscarPorId(int idAgendamento);


}
