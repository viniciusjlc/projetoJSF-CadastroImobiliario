-- DROP SCHEMA imobiliario;

CREATE SCHEMA imobiliario AUTHORIZATION postgres;

-- imobiliario.tipo_logradouro definition

-- Drop table

-- DROP TABLE imobiliario.tipo_logradouro;

CREATE TABLE imobiliario.tipo_logradouro (
	id serial NOT NULL,
	descricao varchar(200) NOT NULL,
	CONSTRAINT tipo_logradouro_pk PRIMARY KEY (id)
);


-- imobiliario.unidade_federativa definition

-- Drop table

-- DROP TABLE imobiliario.unidade_federativa;

CREATE TABLE imobiliario.unidade_federativa (
	id serial NOT NULL,
	nome varchar(100) NOT NULL,
	sigla bpchar(2) NULL,
	CONSTRAINT unidade_federativa_pk PRIMARY KEY (id)
);


-- imobiliario.usuarios definition

-- Drop table

-- DROP TABLE imobiliario.usuarios;

CREATE TABLE imobiliario.usuarios (
	id serial NOT NULL,
	email varchar(500) NOT NULL,
	nome varchar(1000) NOT NULL,
	senha varchar NOT NULL,
	CONSTRAINT usuarios_pk PRIMARY KEY (id)
);


-- imobiliario.cadastro_imobiliario definition

-- Drop table

-- DROP TABLE imobiliario.cadastro_imobiliario;

CREATE TABLE imobiliario.cadastro_imobiliario (
	id serial NOT NULL,
	cep varchar(8) NOT NULL,
	endereco varchar(1000) NULL,
	complemento varchar(1000) NULL,
	id_tipo_logradouro int4 NULL,
	numero varchar(100) NULL,
	bairro varchar(500) NULL,
	cidade varchar NULL,
	id_unidade_federativa int4 NULL,
	id_usuario int4 NULL,
	CONSTRAINT cadastro_imobiliario_pk PRIMARY KEY (id),
	CONSTRAINT cadastro_imobiliario_tipo_logradouro_fk FOREIGN KEY (id_tipo_logradouro) REFERENCES imobiliario.tipo_logradouro(id),
	CONSTRAINT unidade_federativa_fk FOREIGN KEY (id_unidade_federativa) REFERENCES imobiliario.unidade_federativa(id),
	CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES imobiliario.usuarios(id)
);