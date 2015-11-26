package br.sc.senai.lovely.model;

import java.util.List;

import br.sc.senai.lovely.dao.ClienteDao;
import br.sc.senai.lovely.dao.FuncionarioDao;
import br.sc.senai.lovely.dominio.Cliente;
import br.sc.senai.lovely.dominio.Funcionario;

public class ClienteRn {
	ClienteDao dao;
	
	public  ClienteRn() {
		dao = new ClienteDao();
	}
	
	public void salvar(Cliente cliente) throws Exception{
		if(cliente.getNome().trim().isEmpty()){
			throw new Exception("O Nome � obrigat�rio!");
		}
		if(cliente.getEndereco().trim().isEmpty()){
			throw new Exception("O endere�o � obrigat�rio!");
		}
		if(cliente.getEmail().trim().isEmpty()){
			throw new Exception("O e-mail � obrigat�rio!");
		}
		
		
		dao.salvar(cliente);
		
	}
	
	public void excluir(Long idCliente) throws Exception{
		 dao.excluir(idCliente);
	}
	public List<Cliente> listarTodos() {
		 return dao.listarTodos();
	}
	
	public Cliente buscarPorId(Long idCliente) throws Exception{
		return dao.buscarPorId(idCliente);
	}

}
