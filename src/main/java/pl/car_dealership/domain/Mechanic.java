package pl.car_dealership.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "mechanicId")
@ToString(of = {"mechanicId", "name", "surname", "pesel"})
public class Mechanic {

    Long mechanicId;
    String name;
    String surname;
    String pesel;
    Set<ServiceMechanic> serviceMechanics;
}
