package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.CarToBuyEntity;

import java.util.Optional;

@Repository
public interface CarToBuyJpaRepository extends JpaRepository<CarToBuyEntity, Long> {

    Optional<CarToBuyEntity> findByVin(String vin);
}
