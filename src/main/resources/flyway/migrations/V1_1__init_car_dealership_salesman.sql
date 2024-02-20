create table salesman
(
    salesman_id SERIAL      not null,
    name        VARCHAR(32) not NULL,
    surname     VARCHAR(32) not NULL,
    pesel       VARCHAR(32) not NULL,
    primary key (salesman_id)
);