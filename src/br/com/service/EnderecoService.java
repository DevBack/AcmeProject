/**
 * 
 */
package br.com.service;

import java.util.List;
import br.com.model.Endereco;
import br.com.repository.EnderecoDao;

/**
 * @author Elton Lima
 *
 */
public class EnderecoService {

	EnderecoDao enderecoDao = null;
	
	public EnderecoService() {
		
		this.enderecoDao = new EnderecoDao();
		
	}
	
	public void save(Endereco endereco) {
		enderecoDao.create(endereco);
		
	}
	
	public List<Endereco> list(){
		return this.enderecoDao.read();
		
	}
	
	public void edit(Endereco endereco) {
		enderecoDao.update(endereco);
		
	}
	
	public void delete(Endereco endereco) {
		enderecoDao.delete(endereco);
		
	}
}

