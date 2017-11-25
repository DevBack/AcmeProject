package br.com.model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
	
	private int id;
	private String nome;
	private List<Funcionario> funcionarios;
	
	public Departamento(){
		
		this.funcionarios = new ArrayList<Funcionario>();
	}	
	
	public void addFuncionario(Funcionario funcionario){
		funcionario.setDepartamento(this);
		funcionarios.add(funcionario);	
	}
	
	public void deleteFuncionario(Funcionario funcionario) {
		funcionario.setDepartamento(null);
		funcionarios.remove(funcionario);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
}
