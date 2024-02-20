package pl.car_dealership.business.dao;

import pl.car_dealership.domain.CarToBuy;

import java.util.Optional;

public interface CarToBuyDAO {

    Optional<CarToBuy> findCarToBuyByVin(String vin);
}
