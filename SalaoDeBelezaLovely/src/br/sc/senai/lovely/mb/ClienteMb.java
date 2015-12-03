package br.sc.senai.lovely.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import br.sc.senai.lovely.dominio.Cliente;
import br.sc.senai.lovely.model.ClienteRn;

@ManagedBean

public class ClienteMb {
	
	private Cliente cliente;
	private ClienteRn rn;
	private Cliente clienteSelecionado;
	private List<Cliente> clientes;
	
	@PostConstruct
	public void init(){
		rn = new ClienteRn();
		if (cliente == null) {
			cliente = new Cliente();
		}
		
	
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public ClienteRn getRn() {
		return rn;
	}

	public void setRn(ClienteRn rn) {
		this.rn = rn;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}


	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}


	public List<Cliente> getClientes() {
		if(clientes == null){
			clientes = rn.listarTodos();
 		}
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}



	public String salvar() {
		//criar condição de se usuario for igual 1 listar cliente se não voltar pro login      
		try {
			rn.salvar(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "listarCliente";
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
	
	public String excluir(String idParam ) {
		Long idFuncionario = Long.parseLong(idParam);
		try {
			rn.excluir(idFuncionario);
			clientes = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String editar(String idParam) throws Exception{
		Long id = Long.parseLong(idParam);
		cliente = rn.buscarPorId(id);
		return "cadastroCliente";
	}
	
	
	
}
	
	


