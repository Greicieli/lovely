package br.sc.senai.lovely.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.sc.senai.lovely.dominio.Cliente;
import br.sc.senai.lovely.model.ClienteRn;



@SessionScoped
@ManagedBean
public class SessaoMB {
	private Cliente clienteLogado;
	private ClienteRn rn;
	private Cliente usuarioForm;

	@PostConstruct
	public void iniciar() {
		usuarioForm = new Cliente();
	}

	
	

	public Cliente getUsuarioForm() {
		return usuarioForm;
	}


	public void setUsuarioForm(Cliente usuarioForm) {
		this.usuarioForm = usuarioForm;
	}

	public String entrar() throws Exception  {
		ClienteRn rn = new ClienteRn();
		Cliente cliente = rn.buscarPorEmail(usuarioForm.getEmail());
		
		if ( cliente != null && usuarioForm.getEmail().equalsIgnoreCase(cliente.getEmail())
				&& usuarioForm.getSenha().equals(cliente.getSenha())) {
			clienteLogado = cliente;
			System.out.println("Entrou");
			return "/index";
			
		}
		
		System.out.println("Nao Entrou");
		return "";
	}

	public String sair() {
		clienteLogado = null;
		return "/index?faces-redirect=true";
	}

	public Boolean estaLogado() {
		return clienteLogado != null;
	}

	
}