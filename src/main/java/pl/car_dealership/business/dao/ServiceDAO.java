package pl.car_dealership.business.dao;

import pl.car_dealership.infrastructure.database.entity.ServiceEntity;

import java.util.Optional;

public interface ServiceDAO {
    Optional<ServiceEntity> findByServiceCode(String serviceCode);
}
