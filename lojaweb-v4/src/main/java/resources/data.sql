
insert into produto (descricao, preco) values ('SSD Kingston 1TB', 436.90);
insert into produto (descricao, preco) values ('Teclado USB', 34.90);
insert into produto (descricao, preco) values ('Mouse Microsoft', 190.00);    

/* password: "abc" (sem as aspas) */
INSERT INTO usuario (username, password, salt) VALUES ('doug', '1db6b248784f7866500bd34571f2a60fbd3b743e', 'c3ec5fcc-4148-4b2a-86fd-b6b4c9fe928c');
INSERT INTO usuario (username, password, salt) VALUES ('roger', '2af95119c8c3a604b457ce7c4b17c9fdf2dcc034', '78ae0789-9ad5-4f0a-bc2d-20447e2b295c');

insert into cliente (usuario, nome, email, logradouro, bairro, cidade, estado, cep) 
values (1, 'Douglas Funnie', 'funnie@jumbo.com', 'Avenida José Parisi', 'Vila Velosa', 'Araraquara', 'SP', '14806000');


select * from produto;
select * from usuario;
select * from cliente; 
