-- DROP SCHEMA estoque;

CREATE SCHEMA estoque AUTHORIZATION postgres;

COMMENT ON SCHEMA estoque IS 'standard public schema';

-- Drop table

-- DROP TABLE estoque.produto;

CREATE TABLE produto (
	produto_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	descricao varchar NULL,
	CONSTRAINT produto_pk PRIMARY KEY (produto_id)
);
CREATE UNIQUE INDEX produto_produto_id_idx ON estoque.produto USING btree (produto_id);

-- Drop table

-- DROP TABLE estoque.filial;

CREATE TABLE filial (
	filial_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	cnpj varchar NOT NULL,
	CONSTRAINT filial_pk PRIMARY KEY (filial_id)
);
CREATE UNIQUE INDEX filial_filial_id_idx ON estoque.filial USING btree (filial_id);


-- Drop table

-- DROP TABLE estoque.estoque;

CREATE TABLE estoque (
	estoque_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	produto_id int8 NOT NULL,
	quantidade int4 NOT NULL DEFAULT 0,
	filial_id int8 NOT NULL,
	CONSTRAINT estoque_pk PRIMARY KEY (estoque_id),
	CONSTRAINT estoque_filial_fk FOREIGN KEY (filial_id) REFERENCES filial(filial_id),
	CONSTRAINT estoque_produto_fk FOREIGN KEY (produto_id) REFERENCES produto(produto_id)
);
CREATE INDEX estoque_estoque_id_idx ON estoque.estoque USING btree (estoque_id);

-- Drop table

-- DROP TABLE estoque.pedido_estoque;

CREATE TABLE pedido_estoque (
	pedido_estoque_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	filial_id int8 NOT NULL,
	is_entrada bool NOT NULL DEFAULT false,
	CONSTRAINT pedido_estoque_pk PRIMARY KEY (pedido_estoque_id),
	CONSTRAINT pedido_estoque_filial_fk FOREIGN KEY (filial_id) REFERENCES filial(filial_id)
);
CREATE UNIQUE INDEX pedido_estoque_pedido_estoque_id_idx ON estoque.pedido_estoque USING btree (pedido_estoque_id);

-- Drop table

-- DROP TABLE estoque.item_pedido_estoque;

CREATE TABLE item_pedido_estoque (
	item_pedido_estoque_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	pedido_estoque_id int8 NOT NULL,
	produto_id int8 NOT NULL,
	quantidade int4 NOT NULL,
	CONSTRAINT item_pedido_estoque_pk PRIMARY KEY (item_pedido_estoque_id),
	CONSTRAINT item_pedido_estoque_pedido_estoque_fk FOREIGN KEY (pedido_estoque_id) REFERENCES pedido_estoque(pedido_estoque_id),
	CONSTRAINT item_pedido_estoque_produto_fk FOREIGN KEY (produto_id) REFERENCES produto(produto_id)
);
CREATE UNIQUE INDEX item_pedido_estoque_item_pedido_estoque_id_idx ON estoque.item_pedido_estoque USING btree (item_pedido_estoque_id);
