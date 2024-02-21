package pl.car_dealership.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "serviceCode")
@ToString(of = {"serviceId", "serviceCode", "description", "price"})
public class Service {

    Long serviceId;
    String serviceCode;
    String description;
    BigDecimal price;
    Set<ServiceMechanic> serviceMechanics;
}
