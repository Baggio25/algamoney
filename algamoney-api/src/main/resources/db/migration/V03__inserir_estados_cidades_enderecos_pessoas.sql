INSERT INTO estado (nome, sigla) VALUES ('Paraná', 'PR');
INSERT INTO estado (nome, sigla) VALUES ('Santa Catarina', 'SC');
INSERT INTO estado (nome, sigla) VALUES ('Rio Grande do Sul', 'RS');
INSERT INTO estado (nome, sigla) VALUES ('São Paulo', 'SP');

INSERT INTO cidade (nome, estado_codigo) VALUES ('Dois Vizinhos', 
                        (SELECT codigo FROM estado WHERE nome = 'Paraná'));

INSERT INTO endereco (logradouro, complemento, bairro, cep, cidade_codigo) VALUES ("Rua XV de novembro", "Casa", "Margarida Galvan", "85660-000", 
                        (SELECT codigo FROM cidade WHERE nome = 'Dois Vizinhos'));

INSERT INTO pessoa (nome, ativo, endereco_codigo) VALUES ( "Pessoa Teste", true, 1); 						
						
