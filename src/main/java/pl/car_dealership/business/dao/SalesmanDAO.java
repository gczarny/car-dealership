package pl.car_dealership.business.dao;

import pl.car_dealership.infrastructure.database.entity.SalesmanEntity;

import java.util.Optional;

public interface SalesmanDAO {


    Optional<SalesmanEntity> findByPesel(String pesel);
}
