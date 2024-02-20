package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.car_dealership.infrastructure.database.entity.MechanicEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicJpaRepository extends JpaRepository<MechanicEntity, Integer> {


}
