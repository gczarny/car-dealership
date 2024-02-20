package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.CarToBuyDAO;
import pl.car_dealership.domain.CarToBuy;
import pl.car_dealership.infrastructure.database.repository.jpa.CarToBuyJpaRepository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class CarToBuyRepository implements CarToBuyDAO {

    private final CarToBuyJpaRepository carToBuyJpaRepository;
    private final CarToBuyMapper carToBuyMapper;

    @Override
    public Optional<CarToBuy> findCarToBuyByVin(String vin) {
        return carToBuyJpaRepository.findByVin(vin)
                .map(carToBuyMapper::mapFromEntity);
    }
}
