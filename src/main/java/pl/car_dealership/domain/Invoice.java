package pl.car_dealership.domain;


import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "invoiceId")
@ToString(of = {"invoiceId", "invoiceNumber", "dateTime"})
public class Invoice {

    Long invoiceId;
    String invoiceNumber;
    OffsetDateTime dateTime;
    CarToBuy car;
    Customer customer;
    Salesman salesman;
}
