package br.sc.senai.lovely.dominio;


import java.util.Date;

public class Agenda {
	private long idAgendamento;
	private Date data;
	private Date hora;
	private String procedimento;
	private Funcionario funcionario;
	private Cliente cliente;
	
	
	public long getIdAgendamento() {
		return idAgendamento;
	}
	
	public void setIdAgendamento(long idAgendamento) {
		this.idAgendamento = idAgendamento;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}
	
	public String getProcedimento() {
		return procedimento;
	}
	
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

}
