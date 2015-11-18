package br.sc.senai.lovely.model;

import java.util.List;

import br.sc.senai.lovely.dao.AgendaDao;
import br.sc.senai.lovely.dominio.Agenda;



public class AgendaRn {
	private AgendaDao dao;
	
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
		
		
		dao.salvar(agenda);
	}
		
	
	public List<Agenda> listar() throws Exception {
		return dao.listarTodos();
	}

	//public Agenda buscarPorId(Long id) throws Exception{
		//return dao.buscarPorId(id);
	//}

	public void excluir(Long id) throws Exception {
		// dao.excluir(id);
	}
}