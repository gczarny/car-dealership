create table service
(
    service_id   SERIAL         not null,
    service_code VARCHAR(32)    not NULL,
    description  VARCHAR(64)    not NULL,
    price        NUMERIC(19, 2) not NULL,
    primary key (service_id),
    unique(service_code)
);