package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/revisao";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException("Falha na Conexão!" + e);
		}
	}
	
	public static void closeConnection(Connection connection) {
			
			try {				
				if(connection!=null) {
					connection.close();
				}				
			} catch (SQLException e) {
				throw new RuntimeException("Fechamento da Conexão Falhou!");
			}
	}
	
	public static void closeConnection(Connection connection, PreparedStatement statement) {
		
		try {				
			if(statement!=null) {
				statement.close();
			}				
		} catch (SQLException e) {
			throw new RuntimeException("Fechamento da Conexão Falhou!");
		}
		
		closeConnection(connection);
	}
	
public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
		
		try {				
			if(resultSet!=null) {
				resultSet.close();
			}				
		} catch (SQLException e) {
			
			throw new RuntimeException("Fechamento da Conexão Falhou!");
			
		}finally {
		
			closeConnection(connection, statement);
		
		}
	}
}
