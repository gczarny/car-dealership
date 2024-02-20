package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.CarToServiceDAO;
import pl.car_dealership.domain.CarHistory;
import pl.car_dealership.domain.CarToService;
import pl.car_dealership.infrastructure.database.entity.CarToServiceEntity;
import pl.car_dealership.infrastructure.database.repository.jpa.CarToBuyJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.CarToServiceJpaRepository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class CarToServiceRepository implements CarToServiceDAO {

    private final CarToServiceJpaRepository carToServiceJpaRepository;
    private CarToServiceMapper carToServiceMapper;

    @Override
    public Optional<CarToService> findCarToServiceByVin(String vin) {
        return carToServiceJpaRepository.findByVin(vin)
                .map(carToServiceMapper::mapFromEntity);
    }

    @Override
    public CarToService saveCarToService(CarToService carToService) {
        CarToServiceEntity carToServiceEntity = carToServiceMapper.mapToEntity(entity);
        CarToServiceEntity saved = carToServiceJpaRepository.save(carToServiceEntity);
        return carToServiceMapper.mapFromEntity(saved);
    }

    @Override
    public CarHistory findCarHistoryByVin(String vin) {
        CarToServiceEntity carHistoryByVin = carToServiceJpaRepository.findCarHistoryByVin(vin);
        return carToServiceMapper.mapFromEntity(vin, carHistoryByVin);
    }
}
