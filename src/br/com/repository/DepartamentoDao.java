package br.com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.model.Departamento;
import br.com.util.ConnectionFactory;

public class DepartamentoDao {

	private Connection connection = null;
	private FuncionarioDao funcionarioDao = null;
	
	public DepartamentoDao() {
		
		this.connection = ConnectionFactory.getConnection();
		this.funcionarioDao = new FuncionarioDao();
	}
	
	public boolean create(Departamento departamento) {
		
		String SQL = "INSERT INTO departamento(nome) VALUES(?)";
		PreparedStatement statement = null;
	
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setString(1, departamento.getNome());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Departamento Salvo Com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Salvar Departamento!" + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(connection, statement);
			
		}
			
	}
	
	public List<Departamento> read(){
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "SELECT * FROM departamento";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Departamento> departamentos = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Departamento departamento = new Departamento();
				departamento.setId(resultSet.getInt("id"));
				departamento.setNome(resultSet.getString("nome"));
				departamento.setFuncionarios(funcionarioDao.search(departamento.getId()));
				departamentos.add(departamento);	
			}
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Consultar Funcionários dos Departamentos." + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);
		}
		
		return departamentos;
		
	}
	
	public boolean update(Departamento departamento) {
		
		String SQL = "UPDATE departamento SET nome WHERE id = ?";
		PreparedStatement statement = null;
	
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setLong(1, departamento.getId());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Departamento Atualizado Com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Atualizar Departamento!" + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(connection, statement);
			
		}		
	}
	
	public boolean delete(Departamento departamento) {
		
		String SQL = "DELETE * FROM departamento WHERE id = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setLong(1, departamento.getId());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Telefone Deletado Com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Deletar Departamento.");
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(connection, statement);
			
		}
		
	}

}
