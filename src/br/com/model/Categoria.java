/**
 * 
 */
package br.com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elton Lima
 *
 */
public class Categoria {

	private int id;
	private String descricao;
	private List<Produto> produtos;
	
	public Categoria() {
		
		this.produtos = new ArrayList<>();
		
	}
	
	public void addProduto(Produto produto) {
		this.produtos.add(produto);
		
	}
	
	public void deleteProduto(Produto Produto) {
		this.produtos.add(Produto);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
