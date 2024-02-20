package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.MechanicDAO;
import pl.car_dealership.domain.Mechanic;
import pl.car_dealership.infrastructure.database.repository.jpa.CarToBuyJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.MechanicJpaRepository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class MechanicRepository implements MechanicDAO {

    private final MechanicJpaRepository mechanicJpaRepository;
    private final MechanicMapper mechanicMapper;

    @Override
    public Optional<Mechanic> findByPesel(String pesel) {
        return mechanicJpaRepository.findByPesel(pesel)
                .map(mechanicMapper::mapFromEntity);
    }
}
