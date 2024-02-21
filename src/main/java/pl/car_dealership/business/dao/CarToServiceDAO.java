package pl.car_dealership.business.dao;

import pl.car_dealership.domain.CarHistory;
import pl.car_dealership.domain.CarToService;

import java.util.Optional;

public interface CarToServiceDAO {

    Optional<CarToService> findCarToServiceByVin(String vin);

    CarToService saveCarToService(CarToService car);

    CarHistory findCarHistoryByVin(String vin);
}
