DROP TABLE IF EXISTS service_mechanic CASCADE;
DROP TABLE IF EXISTS service_part CASCADE;
DROP TABLE IF EXISTS car_service_request CASCADE;
DROP TABLE IF EXISTS invoice CASCADE;
DROP TABLE IF EXISTS mechanic CASCADE;
DROP TABLE IF EXISTS part CASCADE;
DROP TABLE IF EXISTS service CASCADE;
DROP TABLE IF EXISTS car_to_service CASCADE;
DROP TABLE IF EXISTS car_to_buy CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS salesman CASCADE;

create table salesman
(
    salesman_id SERIAL      not null,
    name        VARCHAR(32) not NULL,
    surname     VARCHAR(32) not NULL,
    pesel       VARCHAR(32) not NULL,
    primary key (salesman_id)
);

create table address
(
    address_id   SERIAL      not null,
    country      VARCHAR(32) not NULL,
    city         VARCHAR(32) not NULL,
    postal_code  VARCHAR(32) not NULL,
    address_line VARCHAR(32) not NULL,
    primary key (address_id)
);

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

create table car_to_service
(
    car_to_service_id SERIAL      not null,
    vin               VARCHAR(20) not NULL,
    brand             VARCHAR(32) not NULL,
    model             VARCHAR(32) not NULL,
    year              SMALLINT    not NULL,
    color             VARCHAR(32) not NULL,
    primary key (car_to_service_id),
    unique (vin)
);

create table service
(
    service_id   SERIAL         not null,
    service_code VARCHAR(32)    not NULL,
    description  VARCHAR(64)    not NULL,
    price        NUMERIC(19, 2) not NULL,
    primary key (service_id)
);

create table part
(
    part_id       SERIAL         not null,
    serial_number VARCHAR(32)    not NULL,
    description   VARCHAR(64)    not NULL,
    price         NUMERIC(19, 2) not NULL,
    primary key (part_id),
    unique (serial_number)
);

create table mechanic
(
    mechanic_id SERIAL      not null,
    name        VARCHAR(32) not NULL,
    surname     VARCHAR(32) not NULL,
    pesel       VARCHAR(32) not NULL,
    primary key (mechanic_id)
);

create table invoice
(
    invoice_id     SERIAL      not null,
    invoice_number VARCHAR(64) not NULL,
    date_time      TIMESTAMP WITH TIME ZONE not NULL,
    car_to_buy_id  INT         not NULL,
    customer_id    INT         not NULL,
    salesman_id    INT         not NULL,
    primary key (invoice_id),
    UNIQUE (invoice_number),
    constraint fk_invoice_car
        foreign key (car_to_buy_id)
            references car_to_buy (car_to_buy_id),
    constraint fk_invoice_customer
        foreign key (customer_id)
            references customer (customer_id),
    constraint fk_invoice_salesman
        foreign key (salesman_id)
            references salesman (salesman_id)
);

create table car_service_request
(
    car_service_request_id     SERIAL      not null,
    car_service_request_number VARCHAR(32) not NULL,
    received_date_time         TIMESTAMP WITH TIME ZONE not NULL,
    completed_date_time        TIMESTAMP WITH TIME ZONE,
    customer_comment           TEXT,
    customer_id                INT         not NULL,
    car_to_service_id          INT         not NULL,
    primary key (car_service_request_id),
    unique (car_service_request_number),
    constraint fk_car_service_request_customer
        foreign key (customer_id)
            references customer (customer_id),
    constraint fk_car_service_request_car
        foreign key (car_to_service_id)
            references car_to_service (car_to_service_id)
);

create table service_part
(
    service_part_id        SERIAL   not null,
    quantity               SMALLINT not NULL,
    car_service_request_id INT      not NULL,
    part_id                INT      NOT NULL,
    primary key (service_part_id),
    constraint fk_service_part_service_request
        foreign key (car_service_request_id)
            references car_service_request (car_service_request_id),
    constraint fk_service_part_part
        foreign key (part_id)
            references part (part_id)
);

create table service_mechanic
(
    service_mechanic_id    SERIAL      not null,
    hours                  INT         not null,
    comment                VARCHAR(64) not NULL,
    car_service_request_id INT         not NULL,
    mechanic_id            INT         NOT NULL,
    service_id             INT         NOT NULL,
    primary key (service_mechanic_id),
    constraint fk_service_mechanic_service_request
        foreign key (car_service_request_id)
            references car_service_request (car_service_request_id),
    constraint fk_service_mechanic_part
        foreign key (mechanic_id)
            references mechanic (mechanic_id),
    constraint fk_service_mechanic_service
        foreign key (service_id)
            references service (service_id)
);

