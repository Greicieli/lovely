package visao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import controle.AgendaControle;
import controle.ClienteControle;
import controle.FuncionarioControle;
import modelo.Agenda;
import modelo.Cliente;
import modelo.Funcionario;



@ManagedBean(name="agendaBean")
@SessionScoped

public class AgendaBean {
	
	private Agenda agenda;
	private AgendaControle controleAgenda;
	private ClienteControle controleCliente;
	private FuncionarioControle controleFuncionario;
	private List<Agenda> agendas;
	private Agenda agendaSelecionada;
	
	public AgendaBean(){
	agenda = new Agenda();
	controleAgenda = new AgendaControle();
	controleCliente = new ClienteControle();
	controleFuncionario = new FuncionarioControle();
	
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public AgendaControle getControleAgenda() {
		return controleAgenda;
	}

	public void setControleAgenda(AgendaControle controleAgenda) {
		this.controleAgenda = controleAgenda;
	}

	public ClienteControle getControleCliente() {
		return controleCliente;
	}

	public void setControleCliente(ClienteControle controleCliente) {
		this.controleCliente = controleCliente;
	}

	public FuncionarioControle getControleFuncionario() {
		return controleFuncionario;
	}

	public void setControleFuncionario(FuncionarioControle controleFuncionario) {
		this.controleFuncionario = controleFuncionario;
	}

	public List<Agenda> getAgendas() {
		if (agendas== null) {
			agendas = controleAgenda.listarTodos();
		}
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
	
	public String excluir() {
		controleAgenda.excluir(agendaSelecionada);
		agendas.remove(agendaSelecionada);
		agendaSelecionada = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agenda removida com sucesso!", ""));
		return null;
	}
	
	public List<Funcionario> listarFuncionarios() {
		return controleFuncionario.listarTodos();
	}
	
	public List<Cliente> listarClientes() {
		return controleCliente.listarTodos();
	}
	
	public String voltar() {
		return "listarAgenda";
	}

}


