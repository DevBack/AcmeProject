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
import br.com.model.Categoria;
import br.com.model.Fornecedor;
import br.com.model.Produto;
import br.com.util.ConnectionFactory;

/**
 * @author Elton Lima
 *
 */
public class ProdutoDao {

	Connection connection = null;
	CategoriaDao categoriaDao = null;
	FornecedorDao fornecedorDao = null;
	
	public ProdutoDao() {
		
		this.connection = ConnectionFactory.getConnection();
		this.categoriaDao = new CategoriaDao();
		this.fornecedorDao = new FornecedorDao();
	}
	
	public Integer create(Produto produto, Categoria categoria, Fornecedor fornecedor) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "INSERT INTO produto (nome, descricao, id_categoria, id_fornecedor, dataValidade, preco, quantidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		
		ResultSet resultSet = null;
		Integer generatedId = null;
		
		try {
			
			statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.setInt(3, categoria.getId());
			statement.setInt(4, fornecedor.getId());
			statement.setString(5, produto.getDataValidade());
			statement.setDouble(6, produto.getPreco());
			statement.setInt(7, produto.getQuantidade());
			
			statement.executeUpdate();
			
			resultSet = statement.getGeneratedKeys();
			
			while(resultSet.next()) {
				generatedId = resultSet.getInt(1);
				produto.setId(generatedId);
			}
			
			JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Cadastrar Produto. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);
		}
		
		return generatedId;
	}
	
	public List<Produto> read(){
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "SELECT * FROM produto";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Produto> produtos = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Produto produto = new Produto();
				produto.setId(resultSet.getInt("id"));
				produto.setNome(resultSet.getString("nome"));
				produto.setDescricao(resultSet.getString("descricao"));
				produto.setCategoria(categoriaDao.search(produto.getId()));
				produto.setFornecedor(fornecedorDao.search(produto.getId()));
				produto.setDataValidade(resultSet.getString("dataValidade"));
				produto.setPreco(resultSet.getDouble("preco"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				
				produtos.add(produto);
				
			}
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Consultar Produtos. " + e);
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);
		}
		
		return produtos;
	}
	
	public boolean update (Produto produto, Categoria categoria, Fornecedor fornecedor) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "UPDATE produto SET nome = ?, descricao = ?, id_categoria = ?, id_fornecedor = ?, dataValidade = ?, preco = ?, quantidade = ? WHERE id = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.setInt(3, categoria.getId());
			statement.setInt(4, fornecedor.getId());
			statement.setString(5, produto.getDataValidade());
			statement.setDouble(6, produto.getPreco());
			statement.setInt(7, produto.getQuantidade());
			statement.setInt(8, produto.getId());
			
			statement.executeUpdate();
		
			JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Atualizar Produto. " + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement, resultSet);
		}
		
	}
	
	public boolean delete(Produto produto) {
		
		this.connection = ConnectionFactory.getConnection();
		
		String SQL = "DELETE * FROM produto WHERE id = ?";
		PreparedStatement statement = null;
		
		try {
			
			statement = connection.prepareStatement(SQL);
			statement.setInt(1, produto.getId());
			statement.executeUpdate();
			
			System.out.println("Produto Excluído com Sucesso!");
			return true;
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao Excluir Produto. " + e);
			return false;
			
		}finally {
			
			ConnectionFactory.closeConnection(this.connection, statement);
		}
	}
}
