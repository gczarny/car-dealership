create table car_to_service
(
    car_to_service_id SERIAL      not null,
    vin               VARCHAR(20) not NULL,
    brand             VARCHAR(32) not NULL,
    model             VARCHAR(32) not NULL,
    production_year   SMALLINT    not NULL,
    primary key (car_to_service_id),
    unique (vin)
);
