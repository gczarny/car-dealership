package pl.car_dealership.domain;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "carToServiceId")
@ToString(of = {"carToServiceId", "vin", "brand", "model", "year"})
public class CarToService {

    Long carToServiceId;
    String vin;
    String brand;
    String model;
    Integer year;
    Set<CarServiceRequest> carServiceRequests;

    public Boolean carBoughtHere() {
        return Objects.nonNull(vin)
            && Objects.isNull(brand)
            && Objects.isNull(model)
            && Objects.isNull(year);
    }
}
