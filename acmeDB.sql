create database acmeDB;

use acmeDB;

create table departamento(
	id int not null primary key auto_increment,
    nome varchar(100) not null
);

create table funcionario(
	id int not null primary key auto_increment,
    id_departamento int not null,
    nome varchar(150) not null,
    salario double not null,
    matricula varchar(10),
    foreign key(id_departamento) references departamento(id)
);

create table email(
	id int not null primary key auto_increment,
    id_funcionario int not null,
    descricao varchar(150) not null,
    foreign key(id_funcionario) references funcionario(id)
);

create table telefone(
	id int not null primary key auto_increment,
    id_funcionario int not null,
    ddd varchar(3),
    numero varchar(11),
    foreign key(id_funcionario) references funcionario(id)
);

create table dependente(
	id int not null primary key auto_increment,
    id_funcionario int not null,
    nome varchar(150),
    foreign key(id_funcionario) references funcionario(id)
);

create table endereco(
	id int not null primary key auto_increment,
    rua varchar(200) not null,
    numero int,
    complemento varchar(100),
    referencia varchar(200),
    cep varchar(11),
    bairro varchar(100),
    cidade varchar(100),
    estado varchar(100),
    pais varchar(100)
);

create table fornecedor(
	id int not null primary key auto_increment,
    cnpj varchar(20) not null unique,
    id_endereco int not null,
    telefone varchar(200) not null,
    email varchar(200) null,
    nome varchar(200) not null,
    razao_social varchar(200) not null,
    foreign key (id_endereco) references endereco(id)
);

create table cliente(
	id int not null primary key auto_increment,
    nome varchar(200) not null,
    cpf varchar(20) not null unique,
    id_endereco int not null,
    telefone varchar(20) null,
    email varchar(20) null,
    foreign key (id_endereco) references endereco(id)
);

create table categoria(
	id int not null primary key auto_increment,
    descricao varchar(100) not null
);

create table produto(
	id int not null primary key auto_increment,
    nome varchar(100) not null,
    descricao varchar(200),
    id_categoria int not null,
    id_fornecedor int not null,
    dataValidade varchar(11),
    preco double not null,
    quantidade int not null,
    foreign key (id_categoria) references categoria(id),
    foreign key (id_fornecedor) references fornecedor(id)
);

create table pedido(
	id int not null primary key auto_increment,
    id_produto int not null,
    id_cliente int not null,
    valor double not null,
    qtd int not null,
    total double not null,
    foreign key (id_produto) references produto(id),
    foreign key (id_cliente) references cliente(id)
);
