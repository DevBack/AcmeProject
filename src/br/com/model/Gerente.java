package br.com.model;

public class Gerente extends Funcionario {

	public Gerente(){

	}

	public void calculaSalario(){
		super.salario += (super.salario*3);
	}
}
