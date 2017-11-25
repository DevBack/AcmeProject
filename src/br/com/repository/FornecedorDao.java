/**
 * 
 */
package br.com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.model.Endereco;
import br.com.model.Fornecedor;
import br.com.util.ConnectionFactory;

/**
 * @author Elton Lima
 *
 */
public class FornecedorDao {

	private Connection connection = null;
	private EnderecoDao enderecoDao = null;
	
	public FornecedorDao() {
		
		this.connection = ConnectionFactory.getConnection();
		this.enderecoDao = new EnderecoDao();
	}
	
	public Integer create(Fornecedor fornecedor, Endereco endereco) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "INSERT INTO fornecedor (cnpj, id_endereco, nome, razao_social) VALUES (?, ?, ?, ?)";
		PreparedStatement statement = null;
		
		ResultSet resultSet = null;
		Integer generatedId = null;
		
		try {
			
			statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, fornecedor.getCnpj());
			statement.setInt(2, endereco.getId());
			statement.setString(3, fornecedor.getNome());
			statement.setString(4, fornecedor.getRazaoSocial());
			
			statement.executeUpdate();
			
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next()) {
				generatedId = resultSet.getInt(1);
				fornecedor.setId(generatedId);
			}
			
			JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!");
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Cadastrar Fornecedor. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);
			
		}
		
		return generatedId;
		
	}
	
	public List<Fornecedor> read(){
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "SELECT * FROM fornecedor";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Fornecedor> fornecedores = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(resultSet.getInt("id"));
				fornecedor.setCnpj(resultSet.getString("cnpj"));
				fornecedor.setEndereco(enderecoDao.search(fornecedor.getId()));
				fornecedor.setTelefone(resultSet.getString("telefone"));
				fornecedor.setEmail(resultSet.getString("email"));
				fornecedor.setNome(resultSet.getString("nome"));
				fornecedor.setRazaoSocial(resultSet.getString("razao_social"));
				
				fornecedores.add(fornecedor);
			}
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao lêr Fornecedores. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);

		}
		
		return fornecedores;	
	}
	
	public Fornecedor search(Integer produtoId){
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "SELECT * FROM fornecedor WHERE id = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		Fornecedor fornecedor = new Fornecedor();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, produtoId);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				fornecedor.setId(resultSet.getInt("id"));
				fornecedor.setCnpj(resultSet.getString("cnpj"));
				fornecedor.setEndereco(enderecoDao.search(fornecedor.getId()));
				fornecedor.setTelefone(resultSet.getString("telefone"));
				fornecedor.setEmail(resultSet.getString("email"));
				fornecedor.setNome(resultSet.getString("nome"));
				fornecedor.setRazaoSocial(resultSet.getString("razao_social"));
				
			}
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Buscar Fornecedor. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);

		}
		
		return fornecedor;	
	}
	
	public boolean update(Fornecedor fornecedor, Endereco endereco) {
		
			this.connection = ConnectionFactory.getConnection();
			
			String SQL = "UPDATE fornecedor SET cnpj = ?, id_endereco = ?, nome = ?, razao_social = ? WHERE id = ?";
			PreparedStatement statement = null;
			
			try {
				
				statement = connection.prepareStatement(SQL);
				statement.setString(1, fornecedor.getCnpj());
				statement.setInt(2, endereco.getId());
				statement.setString(3, fornecedor.getNome());
				statement.setString(4, fornecedor.getRazaoSocial());
				statement.setInt(5, fornecedor.getId());
				
				statement.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Fornecedor Atualizado com Sucesso!");
				return true;
				
			} catch (SQLException e) {
				
				System.err.println("Erro ao Atualizar Fornecedor. " + e);
				return false;
				
			}finally {
				
				ConnectionFactory.closeConnection(this.connection, statement);		
		}
	}
	
	public boolean delete(Fornecedor fornecedor) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "Delete * FROM fornecedor WHERE id = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, fornecedor.getId());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Fornecedor Excluído com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Excluir Fornecedor. " + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement);
			
		}
	}
}
