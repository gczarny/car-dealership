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