package br.sc.senai.lovely.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.sc.senai.lovely.dominio.Login;
import br.sc.senai.lovely.model.LoginRn;


@SessionScoped
@ManagedBean
public class SessaoMB {
	private Login usuarioLogado;
	private LoginRn rn;
	private Login usuarioForm;

	@PostConstruct
	public void iniciar() {
		usuarioForm = new Login();
	}

	
	public Login getUsuarioForm() {
		return usuarioForm;
	}


	public void setUsuarioForm(Login usuarioForm) {
		this.usuarioForm = usuarioForm;
	}



	public String entrar() throws Exception  {
		LoginRn rn = new LoginRn();
		Login usuario = rn.buscarPorUsuario(usuarioForm.getUsuario());
		
		if ( usuario != null && usuarioForm.getUsuario().equalsIgnoreCase(usuario.getUsuario())
				&& usuarioForm.getSenha().equals(usuario.getSenha())) {
			usuarioLogado = usuarioForm;
			System.out.println("Entrou");
			return "/index";
			
		}
		
		System.out.println("N�o Entrou");
		return "";
	}

	public String sair() {
		usuarioLogado = null;
		return "/index?faces-redirect=true";
	}

	public Boolean estaLogado() {
		return usuarioLogado != null;
	}

	public Boolean estaLogadoAdmin() {
		return usuarioLogado != null && usuarioLogado.getAdmin();
	}
}