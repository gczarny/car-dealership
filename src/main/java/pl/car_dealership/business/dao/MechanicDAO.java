package pl.car_dealership.business.dao;

import pl.car_dealership.domain.Mechanic;

import java.util.Optional;

public interface MechanicDAO {

    Optional<Mechanic> findByPesel(String pesel);
}
