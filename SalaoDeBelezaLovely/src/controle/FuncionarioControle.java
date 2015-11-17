package controle;

import java.util.List;


import modelo.Funcionario;
import dao.DAOFactory;
import dao.FuncionarioDao;

public class FuncionarioControle  {
	
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
		
		FuncionarioDao funcionarioDao = DAOFactory.getFuncionarioDao();
		if(funcionario.getIdFuncionario()==0){
			funcionarioDao.salvar(funcionario);
		}else{
			funcionarioDao.alterar(funcionario);
		}
		
		
	}
	public void excluir(Funcionario funcionario){
		FuncionarioDao funcionarioDao = DAOFactory.getFuncionarioDao();
		funcionarioDao.excluir(funcionario);
	}
	
	public List<Funcionario>listarTodos(){
		FuncionarioDao funcionarioDao = DAOFactory.getFuncionarioDao();
		return funcionarioDao.listarTodos();		
	}
}
