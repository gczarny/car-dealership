package pl.car_dealership.business.dao;

import pl.car_dealership.infrastructure.database.entity.MechanicEntity;

import java.util.Optional;

public interface MechanicDAO {

    Optional<MechanicEntity> findByPesel(String pesel);
}
