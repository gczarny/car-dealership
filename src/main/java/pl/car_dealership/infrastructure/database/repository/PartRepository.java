package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.PartDAO;
import pl.car_dealership.domain.Part;
import pl.car_dealership.infrastructure.database.repository.jpa.PartJpaRepository;
import pl.car_dealership.infrastructure.database.repository.mapper.PartEntityMapper;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class PartRepository implements PartDAO {

    private final PartJpaRepository partJpaRepository;
    private final PartEntityMapper partEntityMapper;

    @Override
    public List<Part> findAll() {
        return partJpaRepository.findAll().stream()
                .map(partEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Part> findBySerialNumber(String serialNumber) {
        return partJpaRepository.findBySerialNumber(serialNumber)
                .map(partEntityMapper::mapFromEntity);
    }
}
