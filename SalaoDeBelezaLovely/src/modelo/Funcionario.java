package modelo;

public class Funcionario {
	
	int idFuncionario;
	String nome;
	String funcao;
	String email;
	
	public Funcionario(int idFuncionario, String nome, String funcao,
			String email) {
		super();
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.funcao = funcao;
		this.email = email;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
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


	public Funcionario(){
		
	}
	
	
}