create table part
(
    part_id       SERIAL         not null,
    serial_number VARCHAR(32)    not NULL,
    description   VARCHAR(64)    not NULL,
    price         NUMERIC(19, 2) not NULL,
    primary key (part_id),
    unique (serial_number)
);