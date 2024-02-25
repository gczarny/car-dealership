package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.CarToServiceDAO;
import pl.car_dealership.domain.CarHistory;
import pl.car_dealership.domain.CarToService;
import pl.car_dealership.infrastructure.database.entity.CarToServiceEntity;
import pl.car_dealership.infrastructure.database.repository.jpa.CarToServiceJpaRepository;
import pl.car_dealership.infrastructure.database.repository.mapper.CarToServiceEntityMapper;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class CarToServiceRepository implements CarToServiceDAO {

    private final CarToServiceJpaRepository carToServiceJpaRepository;
    private final CarToServiceEntityMapper carToServiceEntityMapper;

    @Override
    public List<CarToService> findAll() {
        return carToServiceJpaRepository.findAll().stream()
            .map(carToServiceEntityMapper::mapFromEntity)
            .toList();
    }

    @Override
    public Optional<CarToService> findCarToServiceByVin(String vin) {
        return carToServiceJpaRepository.findOptionalByVin(vin)
                .map(carToServiceEntityMapper::mapFromEntity);
    }

    @Override
    public CarToService saveCarToService(CarToService carToService) {
        CarToServiceEntity carToServiceEntity = carToServiceEntityMapper.mapToEntity(carToService);
        CarToServiceEntity saved = carToServiceJpaRepository.save(carToServiceEntity);
        return carToServiceEntityMapper.mapFromEntity(saved);
    }

    @Override
    public CarHistory findCarHistoryByVin(String vin) {
        CarToServiceEntity carHistoryByVin = carToServiceJpaRepository.findByVin(vin);
        return carToServiceEntityMapper.mapFromEntity(vin, carHistoryByVin);
    }
}
