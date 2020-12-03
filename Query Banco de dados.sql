create table predio (

	id_predio INT PRIMARY KEY,
	nome VARCHAR(200) NOT NULL,
	Demanda_contratada DECIMAL not null 
	
)

create table empresa (
	
	id_empresa INT PRIMARY KEY,
	nome_fantasia VARCHAR (200) NOT NULL,
	razao_social VARCHAR (200) NOT NULL,
	cnpj varchar (16) not null,
	consumo_medio_mensal decimal
)

create table sala (

	id_sala INT PRIMARY KEY,
	nome varchar(100) not null

)

create table equipamentos (

	id_equipamento INT PRIMARY KEY,
	nome VARCHAR(50) not null
)

create table medidor (
	
	id_medidor INT PRIMARY KEY

)

CREATE TABLE medicao (

	id_medicao INT PRIMARY KEY,
	valor decimal not  null,
	data_inicio TIMESTAMP,
	data_fim TIMESTAMP
)

ALTER TABLE sala
ADD CONSTRAINT fk_salaempresa FOREIGN KEY (id_sala) REFERENCES empresa(id_empresa)

ALTER TABLE sala
ADD CONSTRAINT fk_predioSala FOREIGN KEY (id_sala) REFERENCES predio(id_predio)

create table predio_empresa (

	fk_empresa_id_empresa int,
	fk_predio_id_predio int

)

alter table predio_empresa ADD CONSTRAINT fk_Empresa_predio
foreign key (fk_empresa_id_empresa)
references empresa (id_empresa)

alter table predio_empresa ADD CONSTRAINT fk_predio_empresa
foreign key (fk_predio_id_predio)
references predio (id_predio)

ALTER TABLE medidor
ADD CONSTRAINT fk_medidor_Medicao FOREIGN KEY (id_medidor) REFERENCES medicao(id_medicao)

ALTER TABLE equipamentos
ADD CONSTRAINT fk_equipamento_medidor FOREIGN KEY (id_equipamento) REFERENCES medidor(id_medidor)

ALTER TABLE equipamentos
ADD CONSTRAINT fk_equipamento_medicao FOREIGN KEY (id_equipamento) REFERENCES medicao(id_medicao)