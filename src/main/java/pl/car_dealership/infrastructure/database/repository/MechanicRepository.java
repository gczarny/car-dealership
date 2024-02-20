package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.MechanicDAO;
import pl.car_dealership.domain.Mechanic;
import pl.car_dealership.infrastructure.database.repository.jpa.MechanicJpaRepository;
import pl.car_dealership.infrastructure.database.repository.mapper.MechanicEntityMapper;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class MechanicRepository implements MechanicDAO {

    private final MechanicJpaRepository mechanicJpaRepository;
    private final MechanicEntityMapper mechanicEntityMapper;

    @Override
    public Optional<Mechanic> findByPesel(String pesel) {
        return mechanicJpaRepository.findByPesel(pesel)
                .map(mechanicEntityMapper::mapFromEntity);
    }
}
