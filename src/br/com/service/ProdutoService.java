package br.com.service;

import java.util.List;
import br.com.model.Categoria;
import br.com.model.Fornecedor;
import br.com.model.Produto;
import br.com.repository.ProdutoDao;

/**
 * @author Elton Lima
 *
 */
public class ProdutoService {

	private ProdutoDao produtoDao;
	
	public ProdutoService() {
		
		this.produtoDao = new ProdutoDao();
		
	}
	
	public void save(Produto produto, Categoria categoria, Fornecedor fornecedor) {
		
		produtoDao.create(produto, categoria, fornecedor);
	}
	
	public List<Produto> list(){
		
		return this.produtoDao.read();
	}
	
	public void edit(Produto produto, Categoria categoria, Fornecedor fornecedor) {
		
		produtoDao.update(produto, categoria, fornecedor);
	}
	
	public void delete(Produto produto) {
		
		produtoDao.delete(produto);
	}
}
