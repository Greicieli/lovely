package dao;

import modelo.Produto;

public interface ProdutoDao extends DAO<Produto>{
	
	public Produto buscarPorId(int idProduto);

}
