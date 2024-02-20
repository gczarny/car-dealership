create table customer
(
    customer_id SERIAL      not null,
    name        VARCHAR(32) not NULL,
    surname     VARCHAR(32) NOT NULL,
    phone       VARCHAR(32) not NULL,
    email       VARCHAR(32) not NULL,
    address_id  INT         not NULL,
    primary key (customer_id),
    unique (email),
    constraint fk_customer_address
        foreign key (address_id)
            references address (address_id)
);