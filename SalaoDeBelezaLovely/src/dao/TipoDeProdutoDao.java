package dao;

import modelo.TipoDeProduto;


public interface TipoDeProdutoDao extends DAO<TipoDeProduto> {

	public TipoDeProduto buscarPorId(int id); 

}
