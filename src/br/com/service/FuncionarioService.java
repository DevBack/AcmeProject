package br.com.service;

import java.util.List;
import br.com.model.Departamento;
import br.com.model.Dependente;
import br.com.model.Email;
import br.com.model.Funcionario;
import br.com.model.Telefone;
import br.com.repository.DependenteDao;
import br.com.repository.EmailDao;
import br.com.repository.FuncionarioDao;
import br.com.repository.TelefoneDao;

/**
 * @author Elton Lima
 *
 */
public class FuncionarioService {

	private FuncionarioDao funcionarioDao;
	private DependenteDao dependenteDao;
	private EmailDao emailDao;
	private TelefoneDao telefoneDao;
	
	public FuncionarioService() {
		
		this.funcionarioDao = new FuncionarioDao();
		this.dependenteDao = new DependenteDao();
		this.emailDao = new EmailDao();
		this.telefoneDao = new TelefoneDao();

	}
	
	public void save(Departamento departamento, Funcionario funcionario) {
		
		Integer key = funcionarioDao.create(departamento.getId(), funcionario);

		for(Email email : funcionario.getEmails()) {
			emailDao.create(key, email);
		}
			
		for(Telefone telefone : funcionario.getTelefones()) {
			telefoneDao.create(key, telefone);
		}
		
		for(Dependente dependente : funcionario.getDependentes()) {
			dependenteDao.create(key, dependente);
		}
				
	}
	
	public List<Funcionario> list() {
		
		return this.funcionarioDao.read();
	}
	
	public void edit(Funcionario funcionario) {
		
		funcionarioDao.update(funcionario);
	
		for(Email email : funcionario.getEmails()) {
			emailDao.update(funcionario.getId(), email);
		}
		
		for(Telefone telefone : funcionario.getTelefones()) {
			telefoneDao.update(funcionario.getId(), telefone);
		}
		
		for(Dependente dependente : funcionario.getDependentes()) {
			dependenteDao.update(funcionario.getId(), dependente);
		}
				
	}
	
	public void delete(Funcionario funcionario) {
		
		funcionarioDao.delete(funcionario);
		
		for(Dependente dependente : funcionario.getDependentes()) {
			dependenteDao.delete(funcionario.getId(), dependente);
		}
		
		for(Telefone telefone : funcionario.getTelefones()) {
			telefoneDao.delete(funcionario.getId(), telefone);
		}
		
		for(Email email : funcionario.getEmails()) {
			emailDao.delete(funcionario.getId(), email);
		}
	}
}
