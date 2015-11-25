package br.sc.senai.lovely.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.sc.senai.lovely.dominio.Produto;
import br.sc.senai.lovely.model.ProdutoRn;

@ManagedBean

public class ProdutoMb {
	private Produto produto;
	private ProdutoRn rn;
	private List<Produto> produtos ;
	
	@PostConstruct
	public void init(){
		rn = new ProdutoRn();
		if (produto == null) {
			produto = new Produto();
		}
		
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoRn getRn() {
		return rn;
	}

	public void setRn(ProdutoRn rn) {
		this.rn = rn;
	}

	public List<Produto> getProdutos() throws Exception{
		if(produtos == null){
			produtos = rn.listar();
 		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
		
	public String salvar(){
		try {
			rn.salvar(produto);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "listarProduto";
	}
	
	public String excluir(String idParam){
		Long idFuncionario = Long.parseLong(idParam);
		try {
			rn.excluir(idFuncionario);
			produtos = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String editar(String idParam) throws Exception{
		Long id = Long.parseLong(idParam);
		produto = rn.buscarPorId(id);
		return "cadastroProduto";
	}
}
