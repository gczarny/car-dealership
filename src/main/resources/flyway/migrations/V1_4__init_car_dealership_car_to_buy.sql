create table car_to_buy
(
    car_to_buy_id SERIAL        not null,
    vin           VARCHAR(20)   not NULL,
    brand         VARCHAR(32)   not NULL,
    model         VARCHAR(32)   not NULL,
    year          SMALLINT      not NULL,
    color         VARCHAR(32),
    price         NUMERIC(7, 2) not NULL,
    primary key (car_to_buy_id),
    unique (vin)
);