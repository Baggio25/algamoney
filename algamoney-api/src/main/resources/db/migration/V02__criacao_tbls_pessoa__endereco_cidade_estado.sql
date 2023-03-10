CREATE TABLE estado ( 
   codigo BIGINT(20) AUTO_INCREMENT, 
   nome VARCHAR(50) NOT NULL,
   sigla VARCHAR(2) NOT NULL,
   
   CONSTRAINT pk_estado PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cidade ( 
   codigo BIGINT(20) AUTO_INCREMENT, 
   nome VARCHAR(50) NOT NULL,
   estado_codigo BIGINT(20) NOT NULL,
   
   CONSTRAINT pk_cidade PRIMARY KEY (codigo),
   CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_codigo) REFERENCES estado(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE endereco ( 
   codigo BIGINT(20) AUTO_INCREMENT, 
   logradouro VARCHAR(200) NOT NULL,
   complemento VARCHAR(200) NOT NULL,
   bairro VARCHAR(200) NOT NULL,
   cep VARCHAR(200) NOT NULL,
   cidade_codigo BIGINT(20) NOT NULL,
   
   CONSTRAINT pk_endereco PRIMARY KEY (codigo),
   CONSTRAINT fk_endereco_cidade FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pessoa (
   codigo BIGINT(20) AUTO_INCREMENT, 
   nome VARCHAR(50) NOT NULL,
   ativo BOOLEAN DEFAULT TRUE,
   endereco_codigo BIGINT(20),
      
   CONSTRAINT pk_pessoa PRIMARY KEY (codigo),
   CONSTRAINT fk_endereco_pessoa FOREIGN KEY (endereco_codigo) REFERENCES endereco(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;