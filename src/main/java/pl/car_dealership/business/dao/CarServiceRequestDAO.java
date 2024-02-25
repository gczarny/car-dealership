package pl.car_dealership.business.dao;

import pl.car_dealership.domain.CarServiceRequest;

import java.util.List;
import java.util.Set;

public interface CarServiceRequestDAO {
    Set<CarServiceRequest> findActiveServiceRequestsByCarVin(String carVin);

    List<CarServiceRequest> findAvailable();

}
