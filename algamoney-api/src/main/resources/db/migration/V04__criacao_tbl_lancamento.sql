CREATE TABLE lancamento (
	codigo BIGINT(20) AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	categoria_codigo BIGINT(20) NOT NULL,
	pessoa_codigo BIGINT(20) NOT NULL,
   
    CONSTRAINT pk_lancamento PRIMARY KEY (codigo),
	CONSTRAINT fk_lancamento_categoria FOREIGN KEY (categoria_codigo) REFERENCES categoria(codigo),
	CONSTRAINT fk_lancamento_pessoa FOREIGN KEY (pessoa_codigo) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_codigo, pessoa_codigo) 
       values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);