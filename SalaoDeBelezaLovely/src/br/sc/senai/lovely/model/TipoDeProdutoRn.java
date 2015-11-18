package br.sc.senai.lovely.model;

import java.util.List;

import br.sc.senai.lovely.dao.TipoDeProdutoDao;
import br.sc.senai.lovely.dominio.TipoDeProduto;

public class TipoDeProdutoRn {
	
	TipoDeProdutoDao dao;

	public void salvar(TipoDeProduto tipoDeProduto) throws Exception {
		if (tipoDeProduto.getTipoDeProduto() == null || tipoDeProduto.getTipoDeProduto().trim().isEmpty()) {
			throw new Exception("O tipo do produto é obrigatório!");
		}

}

	public void excluir(TipoDeProduto tipoDeProduto) {
		dao.excluir(tipoDeProduto);
	}

	public List<TipoDeProduto> listarTodos() {
		return dao.listarTodos();
	}

}
