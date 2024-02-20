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