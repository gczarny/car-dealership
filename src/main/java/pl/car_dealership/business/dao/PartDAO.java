package pl.car_dealership.business.dao;

import pl.car_dealership.infrastructure.database.entity.PartEntity;

import java.util.Optional;

public interface PartDAO {
    Optional<PartEntity> findBySerialNumber(String serialNumber);
}
