package pl.car_dealership.domain;

import lombok.*;

import java.math.BigDecimal;


@With
@Value
@Builder
@EqualsAndHashCode(of = "vin")
@ToString(of = {"carToBuyId", "vin", "brand", "model", "year"})

public class CarToBuy {

    Long carToBuyId;
    String vin;
    String brand;
    String model;
    Integer year;
    String color;
    BigDecimal price;
    Invoice invoice;
}
