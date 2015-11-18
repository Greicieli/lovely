package br.sc.senai.lovely.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.sc.senai.lovely.dominio.Produto;
import br.sc.senai.lovely.dominio.TipoDeProduto;
import br.sc.senai.lovely.model.ProdutoRn;
import br.sc.senai.lovely.model.TipoDeProdutoRn;

@ManagedBean(name="produtoBean")
@SessionScoped

public class ProdutoBean {
	private Produto produto;
	private ProdutoRn controleProduto;
	private TipoDeProdutoRn controleTipoDeProduto;
	private Produto produtoSelecionado;
	private List<Produto> produtos ;
	
	public ProdutoBean() {
		produto  = new Produto();
		controleProduto  = new ProdutoRn();
		controleTipoDeProduto = new TipoDeProdutoRn();
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public ProdutoRn getControle() {
		return controleProduto;
	}


	public void setControle(ProdutoRn controle) {
		this.controleProduto = controle;
	}


	public TipoDeProdutoRn getControleTipoDeProduto() {
		return controleTipoDeProduto;
	}


	public void setControleTipoDeProduto(TipoDeProdutoRn controleTipoDeProduto) {
		this.controleTipoDeProduto = controleTipoDeProduto;
	}


	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}


	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}


	public List<Produto> getProdutos() {
		if (produtos== null) {
			produtos = controleProduto.listarTodos();
		}
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}


	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		try {
			controleProduto.salvar(produto);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto cadastrado com sucesso!", "");
			context.addMessage(null, message);
			produto = new Produto();
			produtos = null;
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			context.addMessage(null, message);
		}
		
		return null;
		
	}
	
	public String novo() {
		produto= new Produto();
		return "cadastroProduto";
	}
	
	public String alterar() {
		produto= produtoSelecionado;
		produtoSelecionado = null;
		return "cadastroProduto";
	}
	
	public String excluir() {
		controleProduto.excluir(produtoSelecionado);
		produtos.remove(produtoSelecionado);
		produtoSelecionado = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto removido com sucesso!", ""));
		return null;
	}
	
	public List<TipoDeProduto> listarTipoDeProdutos() {
		return controleTipoDeProduto.listarTodos();
	}
	
	public String voltar() {
		return "listarProduto";
	}
	
	
}
