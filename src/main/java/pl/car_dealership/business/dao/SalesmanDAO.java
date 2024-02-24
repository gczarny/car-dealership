package pl.car_dealership.business.dao;

import pl.car_dealership.domain.Salesman;

import java.util.List;
import java.util.Optional;

public interface SalesmanDAO {

    List<Salesman> findAvailable();
    Optional<Salesman> findByPesel(String pesel);
}
