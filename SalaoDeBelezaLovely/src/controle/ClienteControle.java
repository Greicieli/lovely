package controle;

import java.util.List;

import dao.ClienteDao;
import dao.DAOFactory;
import modelo.Cliente;

public class ClienteControle {
	public void salvar(Cliente cliente) throws Exception{
		if(cliente.getNome().trim().isEmpty()){
			throw new Exception("O Nome é obrigatório!");
		}
		if(cliente.getEndereco().trim().isEmpty()){
			throw new Exception("O endereço é obrigatório!");
		}
		if(cliente.getEmail().trim().isEmpty()){
			throw new Exception("O e-mail é obrigatório!");
		}
		
		ClienteDao clienteDao = DAOFactory.getClienteDao();
		if (cliente.getIdCliente() == 0) {
			clienteDao.salvar(cliente);
		} else {
			clienteDao.alterar(cliente);
		}
		
	}
	
	public void excluir(Cliente cliente){
		ClienteDao ClienteDao = DAOFactory.getClienteDao();
		ClienteDao.excluir(cliente);
	}
	public List<Cliente> listarTodos() {
		ClienteDao clienteDao = DAOFactory.getClienteDao();
		return clienteDao.listarTodos();
	}

}
