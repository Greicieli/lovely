package br.sc.senai.lovely.model;

import java.util.List;






import br.sc.senai.lovely.dao.FuncionarioDao;
import br.sc.senai.lovely.dominio.Funcionario;

public class FuncionarioRn  {
	FuncionarioDao dao;
	
	public void salvar(Funcionario funcionario) throws Exception{
		if(funcionario.getNome().trim().isEmpty()){
			throw new Exception("O Nome é obrigatório!");
		}
		if(funcionario.getFuncao().trim().isEmpty()){
			throw new Exception("A função é obrigatório!");
		}
		if(funcionario.getEmail().trim().isEmpty()){
			throw new Exception("O e-mail é obrigatório!");
		}
		
		
		
		
		
	}
	public void excluir(Funcionario funcionario){
		dao.excluir(funcionario);
	}
	
	public List<Funcionario>listarTodos(){
		return dao.listarTodos();		
	}
}
