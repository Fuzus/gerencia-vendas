create table if not exists produtos(
    id SERIAL PRIMARY KEY,
    description varchar(255),
    price numeric,
    stock int
);

create table if not exists clientes(
    id SERIAL,
    name VARCHAR(255)
);

create table if not exists pedidos(
    id SERIAL,
    client_id int,
    date timestamp,
    total_value numeric,
    status varchar(20),
    PRIMARY KEY (id),
    CONSTRAINT fk_client
        FOREIGN KEY (client_id)
            REFERENCES clientes(id)
            ON DELETE SET NULL
);

create table if not exists produtos_pedido(
    product_id int,
    product_description varchar(255),
    order_id int,
    quantity int,
    price numeric,
    total numeric,
    primary key (product_id, order_id),
    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
            REFERENCES pedidos(id),
    CONSTRAINT fk_pedido
        FOREIGN KEY (product_id)
            REFERENCES produtos(id)

);