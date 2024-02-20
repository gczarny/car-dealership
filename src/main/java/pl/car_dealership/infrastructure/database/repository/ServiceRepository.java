package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.ServiceDAO;
import pl.car_dealership.domain.Service;
import pl.car_dealership.domain.Service;
import pl.car_dealership.infrastructure.database.repository.jpa.ServiceJpaRepository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class ServiceRepository implements ServiceDAO {

    private final ServiceJpaRepository serviceJpaRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public Optional<Service> findByServiceCode(String service) {
        return serviceJpaRepository.findByByServiceCode(service)
                .map(serviceMapper::mapFromEntity);
    }
}
