create table address
(
    address_id   SERIAL      not null,
    country      VARCHAR(32) not NULL,
    city         VARCHAR(32) not NULL,
    postal_code  VARCHAR(32) not NULL,
    address_line VARCHAR(32) not NULL,
    primary key (address_id)
);