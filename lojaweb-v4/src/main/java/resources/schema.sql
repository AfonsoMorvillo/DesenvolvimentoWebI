drop database if exists loja;
create database loja;
use loja;


create table produto (
        id int not null auto_increment,
        descricao varchar(40),
        preco decimal(8, 2),
        primary key (id)
);

create table usuario (
        id int not null auto_increment,
        username varchar(20) not null,
        password varchar(40) not null,
        salt varchar(40) not null,
        primary key (id)
);
alter table usuario add constraint usuario_username_uniq unique(username);

create table cliente (
	id int not null auto_increment,
	usuario int not null references usuario(id) on delete cascade,	
	nome varchar(40) not null,
	email varchar(40),
	logradouro varchar(40),
	bairro varchar(20),
	cidade varchar(30),
	estado char(2),
	cep char(8),	
	primary key (id)
);
