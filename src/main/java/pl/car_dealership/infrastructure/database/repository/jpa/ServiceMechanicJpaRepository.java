package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.ServiceMechanicEntity;

@Repository
public interface ServiceMechanicJpaRepository extends JpaRepository<ServiceMechanicEntity, Long> {


}
