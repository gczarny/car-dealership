package pl.car_dealership.business.dao;

import pl.car_dealership.infrastructure.database.entity.CarHistoryEntity;
import pl.car_dealership.infrastructure.database.entity.CarToBuyEntity;
import pl.car_dealership.infrastructure.database.entity.CarToServiceEntity;

import java.util.Optional;

public interface CarDAO {


    Optional<CarToBuyEntity> findCarToBuyByVin(String vin);

    Optional<CarToServiceEntity> findCarToServiceByVin(String vin);

    CarToServiceEntity saveCarToService(CarToServiceEntity entity);

    CarHistoryEntity findCarHistoryByVin(String vin);
}
