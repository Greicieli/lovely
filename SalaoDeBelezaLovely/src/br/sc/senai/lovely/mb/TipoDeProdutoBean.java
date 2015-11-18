package br.sc.senai.lovely.mb;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.sc.senai.lovely.dominio.TipoDeProduto;
import br.sc.senai.lovely.model.TipoDeProdutoRn;

@ManagedBean(name="tipoDeProdutoBean")
@SessionScoped

public class TipoDeProdutoBean {
	
	private TipoDeProduto tipoDeProduto;
	private TipoDeProdutoRn controle;
	private TipoDeProduto  tipoDeProdutoSelecionado;
	private List<TipoDeProduto> tipoDeProdutos;

	public TipoDeProdutoBean(){
		this.tipoDeProduto = new TipoDeProduto();
		this.controle = new TipoDeProdutoRn();
	}

	public TipoDeProduto getTipoDeProduto() {
		return tipoDeProduto;
	}

	public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}

	public TipoDeProdutoRn getControle() {
		return controle;
	}

	public void setControle(TipoDeProdutoRn controle) {
		this.controle = controle;
	}

	
	public TipoDeProduto getTipoDeProdutoSelecionado() {
		return tipoDeProdutoSelecionado;
	}

	public void setTipoDeProdutoSelecionado(TipoDeProduto tipoDeProdutoSelecionado) {
		this.tipoDeProdutoSelecionado = tipoDeProdutoSelecionado;
	}

	public List<TipoDeProduto> getTipoDeProdutos() {
		if (tipoDeProdutos == null) {
			tipoDeProdutos = controle.listarTodos();
		}
		return tipoDeProdutos;
	}

	public void setTipoDeProdutos(List<TipoDeProduto> tipoDeProdutos) {
		this.tipoDeProdutos = tipoDeProdutos;
	}
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		try {
			controle.salvar(tipoDeProduto);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de produto cadastrado com sucesso!", "");
			context.addMessage(null, message);
			tipoDeProduto = new TipoDeProduto();
			tipoDeProdutos = null;
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			context.addMessage(null, message);
		}
		
		return null;
	}
	
	
	public String novo() {
		tipoDeProduto = new TipoDeProduto();
		return "cadastroTipoDeProduto";
	}
	
	public String alterar() {
		tipoDeProduto = tipoDeProdutoSelecionado;
		tipoDeProdutoSelecionado = null;
		return "cadastroTipoDeProduto";
	}
	
	public String excluir() {
		controle.excluir(tipoDeProdutoSelecionado);
		tipoDeProdutos.remove(tipoDeProdutoSelecionado);
		tipoDeProdutoSelecionado = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de produto removido com sucesso!", ""));
		return null;
	}
	
	public String voltar() {
		return "listarTipoDeProduto";
	}
	
}	