package br.sc.senai.lovely.model;

import java.util.List;

import br.sc.senai.lovely.dao.ClienteDao;
import br.sc.senai.lovely.dominio.Cliente;

public class ClienteRn {
	ClienteDao dao;
	
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
		
		
		
		
	}
	
	public void excluir(Cliente cliente){
		 dao.excluir(cliente);
	}
	public List<Cliente> listarTodos() {
		 return dao.listarTodos();
	}

}
