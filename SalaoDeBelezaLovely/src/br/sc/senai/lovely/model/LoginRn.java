package br.sc.senai.lovely.model;

import br.sc.senai.lovely.dao.LoginDao;
import br.sc.senai.lovely.dominio.Login;





public class LoginRn {
	private LoginDao dao;
	
	 public LoginRn() {
		dao = new LoginDao();
	}
	 public Login buscarPorUsuario (String usuario) throws Exception {
			return dao.buscarPorUsuario(usuario);
		}
	

}
