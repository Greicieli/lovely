package br.sc.senai.lovely.model;

import java.util.List;

import br.sc.senai.lovely.dao.ProdutoDao;
import br.sc.senai.lovely.dominio.Produto;

public class ProdutoRn {

	ProdutoDao dao;
	
	public ProdutoRn(){
		dao = new ProdutoDao();
	}
	
	 public void salvar(Produto produto) throws Exception{
		if(produto.getDescricao().trim().isEmpty()){
			throw new Exception("A descrição é um campo obrigatório!");
		}
		
		dao.salvar(produto);
	}
	
	public void excluir(Long idProduto)throws Exception{
		dao.excluir(idProduto);
	}
	
	public List<Produto> listar() throws Exception {
		return dao.listarTodos();
	}
	
	public Produto buscarPorId(Long idProduto) throws Exception{
		return dao.buscarPorId(idProduto);
	}
	
	
}
