package pl.car_dealership.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "serviceMechanicId")
@ToString(of = {"serviceMechanicId", "hours", "comment"})
public class ServiceMechanic {

    Long serviceMechanicId;
    Integer hours;
    String comment;
    CarServiceRequest carServiceRequest;
    Mechanic mechanic;
    Service service;
}
