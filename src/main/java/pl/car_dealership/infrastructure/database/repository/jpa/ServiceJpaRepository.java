package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.ServiceEntity;

import java.util.Optional;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Long> {


    Optional<ServiceEntity> findByServiceCode(String serviceCode);
}
