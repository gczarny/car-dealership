package pl.car_dealership.business.dao;

import pl.car_dealership.domain.Mechanic;

import java.util.List;
import java.util.Optional;

public interface MechanicDAO {

    List<Mechanic> findAvailable();

    Optional<Mechanic> findByPesel(String pesel);
}
