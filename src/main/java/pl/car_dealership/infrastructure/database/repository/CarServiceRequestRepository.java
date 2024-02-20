package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.CarServiceRequestDAO;
import pl.car_dealership.domain.CarServiceRequest;
import pl.car_dealership.infrastructure.database.repository.jpa.CarServiceRequestJpaRepository;
import pl.car_dealership.infrastructure.database.repository.mapper.CarServiceRequestEntityMapper;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CarServiceRequestRepository implements CarServiceRequestDAO {

    private final CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private final CarServiceRequestEntityMapper carServiceRequestEntityMapper;
    @Override
    public Set<CarServiceRequest> findActiveServiceRequestsByCarVin(String carVin) {

        return carServiceRequestJpaRepository.findActiveServiceRequestsByCarVin(carVin).stream()
                .map(carServiceRequestEntityMapper::mapFromEntity)
                .collect(Collectors.toSet());
    }
}
