package pl.car_dealership.domain;


import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "customerId")
@ToString(of = {"customerId", "name", "surname", "email"})
public class Customer {
    Long customerId;
    String name;
    String surname;
    String phone;
    String email;
    Address address;
    Set<Invoice> invoices;
    Set<CarServiceRequest> carServiceRequests;

}
