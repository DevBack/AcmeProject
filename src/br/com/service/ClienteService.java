package br.com.service;

import java.util.List;
import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.repository.ClienteDao;

/**
 * @author Elton Lima
 *
 */
public class ClienteService {

	private ClienteDao clienteDao;
	
	public ClienteService() {
		
		this.clienteDao = new ClienteDao();
	}
	
	public void save(Cliente cliente, Endereco endereco) {
		
		clienteDao.create(cliente, endereco);
	}
	
	public List<Cliente> list(){
		
		return this.clienteDao.read();
	}
	
	public void edit(Cliente cliente, Endereco endereco) {
		
		clienteDao.update(cliente, endereco);
	}
	
	public void delete(Cliente cliente) {
		
		clienteDao.delete(cliente);
	}
}
