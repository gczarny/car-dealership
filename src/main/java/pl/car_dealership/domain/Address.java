package pl.car_dealership.domain;


import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "country", "city", "postalCode", "address"})
public class Address {

    Long addressId;
    String country;
    String city;
    String postalCode;
    String address;
    Customer customer;
}
