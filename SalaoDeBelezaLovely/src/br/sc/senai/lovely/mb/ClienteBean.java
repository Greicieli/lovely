package br.sc.senai.lovely.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.sc.senai.lovely.dominio.Cliente;
import br.sc.senai.lovely.model.ClienteRn;
@ManagedBean(name="clienteBean")
@SessionScoped

public class ClienteBean {
	
	private Cliente cliente;
	private ClienteRn controle;
	private Cliente clienteSelecionado;
	private List<Cliente> clientes;
	
	public ClienteBean() {
		this.cliente = new Cliente();
		this.controle = new ClienteRn();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteRn getControle() {
		return controle;
	}

	public void setControle(ClienteRn controle) {
		this.controle = controle;
	}

	public List<Cliente> getClientes() {
		if (clientes == null) {
			clientes = controle.listarTodos();
		}
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
	
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		try {
			controle.salvar(cliente);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente cadastrado com sucesso!", "");
			context.addMessage(null, message);
			cliente = new Cliente();
			clientes = null;
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			context.addMessage(null, message);
		}
		
		return null;
		
	}
	
	public String novo() {
		cliente = new Cliente();
		return "cadastroCliente";
	}
	
	public String alterar() {
		cliente = clienteSelecionado;
		clienteSelecionado = null;
		return "cadastroCliente";
	}
	
	public String excluir() {
		controle.excluir(clienteSelecionado);
		clientes.remove(clienteSelecionado);
		clienteSelecionado = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente removido com sucesso!", ""));
		return null;
	}
	
	public String voltar() {
		return "listarCliente";
	}
	
	
	
}
	
	


