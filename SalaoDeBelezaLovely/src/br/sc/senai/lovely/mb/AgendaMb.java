package br.sc.senai.lovely.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.sc.senai.lovely.dominio.Agenda;
import br.sc.senai.lovely.dominio.Cliente;
import br.sc.senai.lovely.dominio.Funcionario;
import br.sc.senai.lovely.model.AgendaRn;
import br.sc.senai.lovely.model.ClienteRn;
import br.sc.senai.lovely.model.FuncionarioRn;



@ManagedBean(name="agendaBean")
@SessionScoped

public class AgendaMb {
	
	private Agenda agenda;
	private AgendaRn controleAgenda;
	private ClienteRn controleCliente;
	private FuncionarioRn controleFuncionario;
	private List<Agenda> agendas;
	private Agenda agendaSelecionada;
	
	public AgendaMb(){
	agenda = new Agenda();
	controleAgenda = new AgendaRn();
	controleCliente = new ClienteRn();
	controleFuncionario = new FuncionarioRn();
	
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public AgendaRn getControleAgenda() {
		return controleAgenda;
	}

	public void setControleAgenda(AgendaRn controleAgenda) {
		this.controleAgenda = controleAgenda;
	}

	public ClienteRn getControleCliente() {
		return controleCliente;
	}

	public void setControleCliente(ClienteRn controleCliente) {
		this.controleCliente = controleCliente;
	}

	public FuncionarioRn getControleFuncionario() {
		return controleFuncionario;
	}

	public void setControleFuncionario(FuncionarioRn controleFuncionario) {
		this.controleFuncionario = controleFuncionario;
	}

	public List<Agenda> getAgendas() {
		
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public Agenda getAgendaSelecionada() {
		return agendaSelecionada;
	}

	public void setAgendaSelecionada(Agenda agendaSelecionada) {
		this.agendaSelecionada = agendaSelecionada;
	}
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		try {
			controleAgenda.salvar(agenda);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agenda cadastrada com sucesso!", "");
			context.addMessage(null, message);
			agenda = new Agenda();
			agendas = null;
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			context.addMessage(null, message);
		}
		return null;
	}
	
	public String novo() {
		agenda = new Agenda();
		return "cadastroAgenda";
	}
	
	public String alterar() {
		agenda = agendaSelecionada;
		agendaSelecionada = null;
		return "cadastroAgenda";
	}
	
	
	
	//public List<Funcionario> listarFuncionarios() {
	//	return dao.listarTodos();
	//}
	
	public List<Cliente> listarClientes() {
		return controleCliente.listarTodos();
	}
	
	public String voltar() {
		return "listarAgenda";
	}

}


