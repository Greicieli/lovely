package br.sc.senai.lovely.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.sc.senai.lovely.dominio.Login;



public class LoginDao extends Dao {
	
	private static final String SELECT_USUARIO = "SELECT * FROM login WHERE usuario =?";
	
	private Login parseUser(ResultSet rs) throws SQLException {
		Login login = new Login();
		login.setUsuario(rs.getString("usuario"));
		login.setIdLogin(rs.getInt("idLogin"));
		login.setSenha(rs.getString("senha"));
		return login;
	}
	
	public Login buscarPorUsuario(String usuario) throws Exception {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_USUARIO);
			preparedStatement.setString(1, usuario);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				return parseUser(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
