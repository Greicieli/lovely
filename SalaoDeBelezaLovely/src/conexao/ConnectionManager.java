package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static ConnectionManager cm = null;

	// Caminho para o Driver
	private static final String PATH_BRIVER = "com.mysql.jdbc.Driver";

	// Usuario do banco de dados
	private static String USR_BANCO = "root";

	// Senha do banco de dados
	private static String PASS_BANCO = "";

	// URL de conexão para o banco de dados
	private static final String CONN = "jdbc:mysql://localhost:3306/lovely";

	public static ConnectionManager getInstacne() {
		if (cm == null) {
			cm = new ConnectionManager();
		}
		return cm;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(PATH_BRIVER);
			// Obtendo a conexao com o banco de dados
			connection = DriverManager.getConnection(CONN, USR_BANCO,
					PASS_BANCO);
			System.out.println("Conectado");
		} catch (ClassNotFoundException ex) {
			System.out.println("Biblioteca não importada");
		} catch (SQLException ex) {
			System.out.println("Erro ao conectar ao banco de dados: " + ex);
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Erro ao fechar a conexão");
		}
	}
	public static void main(String[] args){
		ConnectionManager.getInstacne().getConnection();
	}

}
