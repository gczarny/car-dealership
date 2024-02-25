package pl.car_dealership.business.dao;

import pl.car_dealership.domain.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceDAO {

    Optional<Service> findByServiceCode(String serviceCode);
    List<Service> findAll();
}
