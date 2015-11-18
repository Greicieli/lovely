package br.sc.senai.lovely.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.sc.senai.lovely.dominio.Funcionario;
import br.sc.senai.lovely.model.FuncionarioRn;

@ManagedBean(name="funcionarioBean")
@SessionScoped

public class FuncionarioBean {
	
	private Funcionario funcionario;
	private FuncionarioRn controle;
	private Funcionario funcionarioSelecionado;
	private List<Funcionario> funcionarios;
	
	public FuncionarioBean(){
		this.funcionario = new Funcionario();
		this.controle = new FuncionarioRn();
	
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public FuncionarioRn getControle() {
		return controle;
	}
	public void setControle(FuncionarioRn controle) {
		this.controle = controle;
	}
	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}
	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}
	public List<Funcionario> getFuncionarios() {
		if (funcionarios == null) {
			funcionarios = controle.listarTodos();
		}
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		try {
			controle.salvar(funcionario);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario cadastrado com sucesso!", "");
			context.addMessage(null, message);
			funcionario = new Funcionario();
			funcionarios = null;
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			context.addMessage(null, message);
		}
		
		return null;
		
	}
	
	public String novo() {
		funcionario = new Funcionario();
		return "cadastroFuncionario";
	}
	
	public String alterar() {
		funcionario = funcionarioSelecionado;
		funcionarioSelecionado = null;
		return "cadastroFuncionario";
	}
	
	public String excluir() {
		controle.excluir(funcionarioSelecionado);
		funcionarios.remove(funcionarioSelecionado);
		funcionarioSelecionado = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario removido com sucesso!", ""));
		return null;
	}
	
	public String voltar() {
		return "listarFuncionario";
	}
	
	

}


