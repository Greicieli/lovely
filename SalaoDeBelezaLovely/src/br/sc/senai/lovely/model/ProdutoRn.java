package br.sc.senai.lovely.model;

import java.util.List;

import br.sc.senai.lovely.dao.ProdutoDao;
import br.sc.senai.lovely.dominio.Produto;

public class ProdutoRn {

	ProdutoDao dao;
	
	public void salvar(Produto produto) throws Exception{
		if(produto.getDescricao().trim().isEmpty()){
			throw new Exception("A decrição é obrigatório!");
		}

}
	
	public void excluir(Produto produto){
		dao.excluir(produto);
	}
	public List<Produto> listarTodos() {
		return dao.listarTodos();
	}

}
