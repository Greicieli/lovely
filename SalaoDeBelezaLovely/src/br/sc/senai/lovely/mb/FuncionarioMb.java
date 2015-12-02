
package br.sc.senai.lovely.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;



import br.sc.senai.lovely.dominio.Funcionario;
import br.sc.senai.lovely.model.FuncionarioRn;

@ManagedBean


public class FuncionarioMb {
	
	private List<Funcionario> funcionarios;
	private Funcionario funcionario;
	private FuncionarioRn rn;
	
	
	@PostConstruct
	public void init(){
		rn = new FuncionarioRn();
		funcionario = new Funcionario();
			
	}
	
	
	public List<Funcionario> getFuncionarios() throws Exception {
		if(funcionarios == null){
			funcionarios = rn.listar();
 		}
	
		return funcionarios;
	}


	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public FuncionarioRn getRn() {
		return rn;
	}


	public void setRn(FuncionarioRn rn) {
		this.rn = rn;
	}
	
	

	public String salvar(){
		try {
			rn.salvar(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "listarFuncionario";
	}
	
	public String excluir(String idParam){
		Long idFuncionario = Long.parseLong(idParam);
		try {
			rn.excluir(idFuncionario);
			funcionarios = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String editar(String idParam) throws Exception{
		Long id = Long.parseLong(idParam);
		funcionario = rn.buscarPorId(id);
		return "cadastroFuncionario";
	}
	
	
	
}


