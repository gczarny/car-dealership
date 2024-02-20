package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.car_dealership.infrastructure.database.entity.ServiceMechanicEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMechanicJpaRepository extends JpaRepository<ServiceMechanicEntity, Integer> {


}
