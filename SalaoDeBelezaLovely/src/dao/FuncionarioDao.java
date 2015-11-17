package dao;

import modelo.Funcionario;

public interface FuncionarioDao extends DAO<Funcionario> {

	public Funcionario buscarPorId(int idFuncionario);
}
