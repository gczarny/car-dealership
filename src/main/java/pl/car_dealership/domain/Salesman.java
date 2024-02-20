package pl.car_dealership.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "salesmanId")
@ToString(of = {"salesmanId", "name", "surname", "pesel"})
public class Salesman {

    Long salesmanId;
    String name;
    String surname;
    String pesel;
    Set<Invoice> invoices;

}
