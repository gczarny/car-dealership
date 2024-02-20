package pl.car_dealership.business.dao;

import pl.car_dealership.domain.Salesman;

import java.util.Optional;

public interface SalesmanDAO {


    Optional<Salesman> findByPesel(String pesel);
}
