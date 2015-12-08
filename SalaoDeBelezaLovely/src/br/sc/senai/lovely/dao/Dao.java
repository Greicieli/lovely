package br.sc.senai.lovely.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	private Connection conn;

	public Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/lovely", "root", "");
				System.out.println("Conectou");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return conn;
	}
}


	
