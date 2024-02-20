package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.car_dealership.infrastructure.database.entity.ServicePartEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePartJpaRepository extends JpaRepository<ServicePartEntity, Integer> {


}
