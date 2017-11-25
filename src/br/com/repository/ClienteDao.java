package br.com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.util.ConnectionFactory;

/**
 * @author Elton Lima
 *
 */
public class ClienteDao {

	private Connection connection = null;
	private EnderecoDao enderecoDao = null;
	
	public ClienteDao() {
		
		this.connection = ConnectionFactory.getConnection();
		this.enderecoDao = new EnderecoDao();
	}
	
	public Integer create(Cliente cliente, Endereco endereco) {
		
		this.connection = ConnectionFactory.getConnection();
		String SQL = "INSERT INTO cliente (nome, cpf, id_endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		
		ResultSet resultSet = null;
		Integer generatedId = null;
		
		try {
			
			statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCpf());
			statement.setInt(3, endereco.getId());
			statement.setString(4, cliente.getTelefone());
			statement.setString(5, cliente.getEmail());
			
			statement.executeUpdate();
			
			resultSet = statement.getGeneratedKeys();
			
			while(resultSet.next()) {
				generatedId = resultSet.getInt(1);
				cliente.setId(generatedId);
			}
			
			JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Cadastrar Criente. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);
		}
		
		return generatedId;	
	}
	
	public List<Cliente> read(){
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "SELECT * FROM cliente";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Cliente> clientes = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				Cliente cliente = new Cliente();
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setCpf(resultSet.getString("cpf"));
				cliente.setEndereco(enderecoDao.search(cliente.getId()));
				cliente.setTelefone(resultSet.getString("telefone"));
				cliente.setEmail(resultSet.getString("email"));
				
				clientes.add(cliente);
				
			}
			
		} catch (SQLException e) {
		
			System.err.println("Erro ao Consultar Endereço. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);
			
		}
		
		return clientes;
	}
	
	public boolean update(Cliente cliente, Endereco endereco) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "UPDATE cliente SET nome = ?, cpf = ?, id_endereco = ?, telefone = ?, email = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCpf());
			statement.setInt(3, endereco.getId());
			statement.setString(4, cliente.getTelefone());
			statement.setString(5, cliente.getEmail());
			
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cliente Atualizado com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Atualizar Cliente. " + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement);
		}
	}
	
	public boolean delete(Cliente cliente) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "DELETE * FROM cliente WHERE id = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, cliente.getId());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cliente Excluído com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Excluir Cliente. " + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement);
			
		}
	}
}
