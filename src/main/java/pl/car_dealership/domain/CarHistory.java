package pl.car_dealership.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.List;

@Value
@Builder
@ToString(of = "carVin")
public class CarHistory {

    String carVin;
    List<ServiceRequest> carServiceRequests;

    @Value
    @Builder
    @ToString(of = {"carServiceRequestNumber", "completionDateTime", "customerComment"})
    public static class ServiceRequest {
        String carServiceRequestNumber;
        OffsetDateTime receivedDateTime;
        OffsetDateTime completionDateTime;
        String customerComment;
        List<Service> services;
        List<Part> parts;
    }
}