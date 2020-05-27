drop database lorentiBD;
create database if not exists lorentiBD;
use lorentiBD;

create table cliente(
cli_id integer not null auto_increment,
cli_cpf varchar(11) not null unique,
cli_nome varchar(40) not null,
cli_email varchar(45) not null unique,
cli_sexo char not null,
cli_dataNascimento date not null,
cli_dataCadastro datetime default current_timestamp,
primary key (cli_id)
);
create table funcionario(
func_id smallint not null unique,
func_nome varchar(40) not null,
func_cpf varchar(11) not null unique,
func_email varchar(45)not null unique,
func_celular varchar (11)not null unique,
func_sexo char not null,
func_cargo varchar (40) not null,
func_datanascimento date not null,
func_user varchar (40) not null unique,
func_senha varchar(40)not null,
primary key (func_id)
);
create table Unidade(
unid_id integer auto_increment not null unique,
unid_nome varchar(40) not null ,
unid_endereco varchar(50) not null,
unid_estado varchar(2) not null,
primary key (unid_id)
);
create table produto(
prod_id integer auto_increment not null unique,
prod_nome varchar(40) not null,
prod_qtd integer not null,
prod_preco decimal(10,2) not null,
prod_descr varchar(60) ,
primary key (prod_id)
);
create table venda(
venda_id integer auto_increment not null unique,
venda_data_venda datetime default current_timestamp,
venda_func smallint not null,
venda_val_total numeric(10,2) not null,
venda_cli_id integer not null,
venda_qnt integer not null,
primary key (venda_id),
foreign key (venda_func) references funcionario(func_id),
foreign key (venda_cli_id) references cliente(cli_id)
);
create table itens (
it_produto integer not null,
it_valor_prod numeric(10,2) not null,
it_qtd smallint not null,
it_venda integer not null,
foreign key (it_produto) references produto(prod_id),
foreign key (it_venda) references venda(venda_id)
);
