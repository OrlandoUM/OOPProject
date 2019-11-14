/*
**
	1 - Estabelecer Conexão
	2 - Criar canal de comunicação
	3 - Executar Instruções SQL
	4 - Processa o resultado
	5 - Fechar Conexão
	
*/
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CNXJDBC {
	
	private final static String DRIVER_CLASS = "org.hsqldb.jdbcDriver";
	private static Connection CNX = null;
	private static String  USUARIO = "SA";
	private static String SENHA = "";
	private static String PATHBASE = "C:\\Users\\orlan\\eclipse-workspace\\Projeto\\util\\Desafio";
	private final static String URL = "jdbc:hsqldb:file:" + PATHBASE + ";shutdow=true;hsqldb.write_delay=false;hsqldb.lock_file=false";
	
	
	public static Connection connect() {
		if(CNX == null) {
			try {
				Class.forName(DRIVER_CLASS);
				CNX = DriverManager.getConnection(URL, USUARIO, SENHA);
		
			} catch (ClassNotFoundException e) { //Erro Driver
				e.printStackTrace();
			} catch(SQLException e) { //Erro conexão
				e.printStackTrace();
			}
		
		}
		
		return CNX;
		
	}
	
	public static void closeConnection() {
		try {
			CNX.close();
			CNX = null;
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
