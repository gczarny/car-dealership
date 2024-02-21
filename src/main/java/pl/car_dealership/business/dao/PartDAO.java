package pl.car_dealership.business.dao;

import pl.car_dealership.domain.Part;

import java.util.Optional;

public interface PartDAO {

    Optional<Part> findBySerialNumber(String serialNumber);
}
