package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.PartDAO;
import pl.car_dealership.domain.Part;
import pl.car_dealership.infrastructure.database.repository.jpa.PartJpaRepository;
import pl.car_dealership.infrastructure.database.repository.mapper.PartEntityMapper;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class PartRepository implements PartDAO {

    private final PartJpaRepository partJpaRepository;
    private final PartEntityMapper partEntityMapper;

    @Override
    public Optional<Part> findBySerialNumber(String serialNumber) {
        return partJpaRepository.findByBySerialNumber(serialNumber)
                .map(partEntityMapper::mapFromEntity);
    }
}
