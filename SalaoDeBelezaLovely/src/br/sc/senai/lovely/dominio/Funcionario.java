package br.sc.senai.lovely.dominio;

public class Funcionario {
	
	 private Long idFuncionario;
	 private String nome;
	 private String funcao;
	 private String email;

	

	public Long getIdFuncionario() {
		return idFuncionario;
	}


	
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}


	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFuncao() {
		return funcao;
	}
	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	 
	
}