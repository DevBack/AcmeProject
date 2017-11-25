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
public class Fornecedor {

	private int id;
	private String cnpj;
	private Endereco endereco;
	private String telefone;
	private String email;
	private List<Produto> produtos;
	private String nome;
	private String razaoSocial;
	
	public Fornecedor() {
		
		this.produtos = new ArrayList<Produto>();
	}
	
	public void addProduto(Produto produto) {
		this.produtos.add(produto);
		
	}
	
	public void deleteProduto(Produto Produto) {
		this.produtos.remove(Produto);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
}
