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