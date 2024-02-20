package pl.car_dealership.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "servicePartId")
@ToString(of = {"servicePartId", "quantity"})
public class ServicePart {

    Long servicePartId;
    Integer quantity;
    CarServiceRequest carServiceRequest;
    Part part;

}
