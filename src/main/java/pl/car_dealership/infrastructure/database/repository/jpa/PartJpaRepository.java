package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.PartEntity;

import java.util.Optional;

@Repository
public interface PartJpaRepository extends JpaRepository<PartEntity, Long> {

    Optional<PartEntity> findBySerialNumber(String serialNumber);
}
