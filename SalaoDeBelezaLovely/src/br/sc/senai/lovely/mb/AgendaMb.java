package br.sc.senai.lovely.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.sc.senai.lovely.dominio.Agenda;
import br.sc.senai.lovely.dominio.Cliente;
import br.sc.senai.lovely.dominio.Funcionario;
import br.sc.senai.lovely.model.AgendaRn;
import br.sc.senai.lovely.model.ClienteRn;
import br.sc.senai.lovely.model.FuncionarioRn;

@ManagedBean
public class AgendaMb {

	private Agenda agenda;
	private AgendaRn rnAgenda;
	private ClienteRn rnCliente;
	private FuncionarioRn rnFuncionario;
	private List<Agenda> agendas;
	private List<Cliente> listarCliente;
	private List<Funcionario> listarFuncionario;

	@PostConstruct
	public void init() {
		rnAgenda = new AgendaRn();
		agenda = new Agenda();
		rnCliente = new ClienteRn();
		rnFuncionario = new FuncionarioRn();
		try {
			listarClientes();
			listarFuncionarios();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public AgendaRn getRnAgenda() {
		return rnAgenda;
	}

	public void setRnAgenda(AgendaRn rnAgenda) {
		this.rnAgenda = rnAgenda;
	}

	public List<Agenda> getAgendas() throws Exception {
		listarAgenda();
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public ClienteRn getRnCliente() {
		return rnCliente;
	}

	public void setRnCliente(ClienteRn rnCliente) {
		this.rnCliente = rnCliente;
	}

	public FuncionarioRn getRnFuncionario() {
		return rnFuncionario;
	}

	public void setRnFuncionario(FuncionarioRn rnFuncionario) {
		this.rnFuncionario = rnFuncionario;
	}

	public List<Cliente> getListarCliente() {
		return listarCliente;
	}

	public void setListarCliente(List<Cliente> listarCliente) {
		this.listarCliente = listarCliente;
	}

	public List<Funcionario> getListarFuncionario() {
		return listarFuncionario;
	}

	public void setListarFuncionario(List<Funcionario> listarFuncionario) {
		this.listarFuncionario = listarFuncionario;
	}

	
	
	public String salvar() {
		try {
			rnAgenda.salvar(agenda);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "listarAgenda";
	}

	public String excluir(String idParam) {
		Long idAgendamento = Long.parseLong(idParam);
		try {
			rnAgenda.excluir(idAgendamento);
			agendas = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String editar(String idParam) throws Exception {
		agenda = null;
		Long idAgendamento = Long.parseLong(idParam);
		for(int i=0;i<agendas.size();i++){
			Long id =agendas.get(i).getIdAgendamento();
			if (idAgendamento.intValue()==id.intValue()){
				agenda = agendas.get(i);
				i=100000;
			}
		}
		if (agenda!=null){
			return "cadastroAgenda";
		}
		return null;
		
	}

	public void listarFuncionarios() throws Exception {
		listarFuncionario = rnFuncionario.listar();
		if(listarFuncionario == null){
			listarFuncionario = new ArrayList<Funcionario>();
		}
	}

	public void listarClientes() throws Exception {
		listarCliente = rnCliente.listarTodos();
		if (listarCliente==null){
			listarCliente = new ArrayList<Cliente>();
		}
	}
	
	public void listarAgenda(){
		try {
			agendas = rnAgenda.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(agendas == null){
			agendas = new ArrayList<Agenda>();
 		}
		for(int i=0;i<agendas.size();i++){
			try {
				agendas.get(i).setCliente(rnCliente.buscarPorId(agendas.get(i).getCliente().getIdCliente()));
				agendas.get(i).setFuncionario(rnFuncionario.buscarPorId(agendas.get(i).getFuncionario().getIdFuncionario()));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
}
