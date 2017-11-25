package br.com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.model.Categoria;
import br.com.util.ConnectionFactory;

/**
 * @author Elton Lima
 *
 */
public class CategoriaDao {

	Connection connection = null;
	
	public CategoriaDao() {
		
		this.connection = ConnectionFactory.getConnection();
		
	}
	
	public boolean create(Categoria categoria) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "INSERT INTO categoria (descricao) VALUES (?)";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setString(1, categoria.getDescricao());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Categoria Criada com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Criar Categoria. " + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement);
			
		}		
	  }
	
	public List<Categoria> read() {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "SELECT * FROM categoria";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Categoria> categorias = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Categoria categoria = new Categoria();
				
				categoria.setId(resultSet.getInt("id"));
				categoria.setDescricao(resultSet.getString("descricao"));
				categorias.add(categoria);
				
			}
			
		} catch (SQLException e) {
		
			System.err.println("Erro ao Consultar Categorias. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);
			
		}
		
		return categorias;
	}
	
public Categoria search(Integer produtoId) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "SELECT * FROM categoria WHERE id = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		Categoria categoria = new Categoria();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, produtoId);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				categoria.setId(resultSet.getInt("id"));
				categoria.setDescricao(resultSet.getString("descricao"));
			}
			
		} catch (SQLException e) {
		
			System.err.println("Erro ao Consultar Categoria. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);	
		}
		
		return categoria;
    }
	
	public boolean update(Categoria categoria) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = " UPDATE categoria SET descricao = ? WHERE id = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(2, categoria.getId());
			statement.setString(1, categoria.getDescricao());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Categoria Atualizada Com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Atualizar Categoria. " + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement);
			
		}
	}
	
	public boolean delete(Categoria categoria) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = " DELETE * FROM categoria WHERE id = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, categoria.getId());
			statement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Categoria Excluída com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Excluir Categoria. " + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement);
			
		}
	}
}
