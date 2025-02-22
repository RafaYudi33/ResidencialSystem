/*
-- Query: SELECT * FROM condominio.condominio
*/
INSERT INTO `condominio`.`condominio` (`id`,`agencia`,`bairro`,`cep`,`cidade`,`cnpj`,`contaCorrente`,`digitoAgencia`,`digitoContaCorrente`,`logradouro`,`nome`,`nossoNumero`,`numeroCarteira`,`numeroConvenio`,`uf`) 
VALUES (1,1234,'Centro','12345-678','Cidade A','00.000.000/0001-91',12345,'1','1','Rua Principal','Condomínio A',9050987,22,1234567,'SP');

/*
-- Query: SELECT * FROM condominio.proprietario
*/
INSERT INTO `condominio`.`proprietario` (`id`,`cpf`,`nome`,`rg`,`telefone`,`email`)
VALUES (1,'111.111.111-11','Proprietário 1','RG1111','(11) 1111-1111', 'rafayudi2010@hotmail.com'),
       (2,'222.222.222-22','Proprietário 2','RG2222','(22) 2222-2222', 'yudirafael33@gmail.com');

/*
-- Query: SELECT * FROM condominio.propriedade
*/
INSERT INTO `condominio`.`propriedade` (`id`,`bairro`,`cep`,`cidade`,`logradouro`,`numero`,`tamanho`,`uf`,`proprietario_id`) 
VALUES (1,'Bairro A','11111-111','Cidade A','Rua A',10,100,'SP',1),
       (2,'Bairro B','22222-222','Cidade B','Rua B',20,200,'SP',2),
       (3,'Bairro C','33333-333','Cidade C','Rua C',30,300,'SP',1),
       (4,'Bairro D','44444-444','Cidade D','Rua D',40,400,'SP',2);

/*
-- Query: SELECT * FROM condominio.propriedade_seq
*/
INSERT INTO `condominio`.`propriedade_seq` (`next_val`) 
VALUES (1), (5);

/*
-- Query: SELECT * FROM condominio.terreno
*/
INSERT INTO `condominio`.`terreno` (`estaEmConstrucao`,`id`) 
VALUES (0,1), (1,2);

/*
-- Query: SELECT * FROM condominio.unidaderesidencial
*/
INSERT INTO `condominio`.`unidaderesidencial` (`id`) 
VALUES (3), (4);

/*
-- Query: SELECT * FROM condominio.veiculo
*/
INSERT INTO `condominio`.`veiculo` (`id`,`cor`,`modelo`,`placa`) 
VALUES (1,'Azul','Carro A','AAA-1111'),
       (2,'Vermelho','Carro B','BBB-2222'),
       (3,'Preto','Carro C','CCC-3333'),
       (4,'Branco','Carro D','DDD-4444'),
       (5,'Cinza','Carro E','EEE-5555');

/*
-- Query: SELECT * FROM condominio.morador
*/
INSERT INTO `condominio`.`morador` (`id`,`cpf`,`nome`,`rg`,`telefone`,`unidade_residencial_id`) 
VALUES (1,'123.456.789-00','Morador 1','RG1001','(11) 9876-5432',3),
       (2,'234.567.890-11','Morador 2','RG2002','(11) 9876-5433',3),
       (3,'345.678.901-22','Morador 3','RG3003','(11) 9876-5434',4),
       (4,'456.789.012-33','Morador 4','RG4004','(11) 9876-5435',4);

/*
-- Query: SELECT * FROM condominio.morador_veiculo
*/
INSERT INTO `condominio`.`morador_veiculo` (`Morador_id`,`veiculos_id`) 
VALUES (1,1), (1,5), (2,2), (3,3), (4,4);

/*
-- Query: SELECT * FROM condominio.areadelazer
*/
INSERT INTO `condominio`.`areadelazer` (`id`,`nome`,`tamanho`,`tipo`) 
VALUES (1,'Piscina',100,'Esporte'),
       (2,'Salão de Festas',200,'Recreação'),
       (3,'Academia',50,'Exercício'),
       (4,'Quadra de Tênis',300,'Esporte'),
       (5,'Parque Infantil',150,'Recreação');
