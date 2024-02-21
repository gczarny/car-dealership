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