package dao;

import modelo.Cliente;

public interface ClienteDao extends DAO<Cliente> {
	
	public Cliente buscarPorId(int idCliente);

}
