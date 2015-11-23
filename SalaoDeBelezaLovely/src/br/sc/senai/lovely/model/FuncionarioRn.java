package br.sc.senai.lovely.model;

import java.util.List;

import br.sc.senai.lovely.dao.FuncionarioDao;
import br.sc.senai.lovely.dominio.Funcionario;


public class FuncionarioRn  {
	FuncionarioDao dao;
	
	public FuncionarioRn(){
		dao = new FuncionarioDao();
	}
	
	public void salvar(Funcionario funcionario) throws Exception{
		if(funcionario.getNome().trim().isEmpty()){
			throw new Exception("O Nome � obrigat�rio!");
		}
		if(funcionario.getFuncao().trim().isEmpty()){
			throw new Exception("A fun��o � obrigat�rio!");
		}
		if(funcionario.getEmail().trim().isEmpty()){
			throw new Exception("O e-mail � obrigat�rio!");
		}
		
		dao.salvar(funcionario);
	}
	
	public void excluir(Long idFuncionario)throws Exception{
		dao.excluir(idFuncionario);
	}
	
	public List<Funcionario> listar() throws Exception {
		return dao.listarTodos();
	}
	
	public Funcionario buscarPorId(Long idFuncionario) throws Exception{
		return dao.buscarPorId(idFuncionario);
	}
}
